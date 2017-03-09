package com.spg.zkCenter.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spg.zkCenter.common.CommonConstants;
import com.spg.zkCenter.common.CommonEnum;
import com.spg.zkCenter.common.CommonUtil;
import com.spg.zkCenter.util.ZKConnector;

/**
 * 项目名称：zkCenter
 *
 * @description:
 * @author Wind-spg
 * @create_time：2017年2月16日 下午10:25:11
 * @version V1.0.0
 */
@Controller
@RequestMapping("newSystemCtrl")
public class NewSystemController extends BaseController
{
    private static final Log LOGGER = LogFactory.getLog(NewSystemController.class);
    
    @Resource
    ZKConnector zkConnector;

    @RequestMapping(value = "addNewSystem", produces =
    { "application/json; charset=UTF-8" })
    @ResponseBody
    public String addNewSystem(@RequestParam String chineseName, @RequestParam String englishName,
            @RequestParam String remark)
    {
        try
        {

            return buildSuccessResultInfo(null);
        } catch (Exception e)
        {
            LOGGER.error("", e);
            return buildFailedResultInfo(-1, e.getMessage());
        }
    }

    /**
     * @description: 系统配置管理下添加接入系统
     * @author: Wind-spg
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addAccessSystem", produces =
    { "application/json; charset=UTF-8" })
    public String addAccessSystem(@RequestParam String chineseName, @RequestParam String englishName,
            @RequestParam String remark)
    {
        CuratorFramework client = zkConnector.getClient();
        if (StringUtils.isBlank(englishName) || StringUtils.isBlank(chineseName))
        {
            throw new IllegalArgumentException("接入系统中文/英文名称为空，请重新添加！");
        }
        Map<String, String> systemData = new HashMap<String, String>();
        systemData.put("chineseName", chineseName);
        systemData.put("englishName", englishName);
        systemData.put("remark", remark);
        systemData.put("createDate", CommonUtil.date2String("yyyy-MM-dd HH:mm:ss", new Date()));
        try
        {
            String configPath = "/" + CommonEnum.ConfigClassfication.CONFIG_MANAGEMENT.getName() + "/"
                    + englishName;
            Stat stat = client.checkExists().forPath(configPath);
            if (null == stat)
            {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                        .withACL(Ids.OPEN_ACL_UNSAFE).forPath(configPath);
            } else
            {
                throw new RuntimeException("当前配置项已经存在，请勿重复添加！");
            }
            Stat resultStat = client.setData().forPath(configPath,
                    JSON.toJSONString(systemData, CommonConstants.SERIALIZER_FEATURES).getBytes());
            return buildSuccessResultInfo(resultStat);
        } catch (Exception e)
        {
            LOGGER.error("add config error!", e);
            return buildFailedResultInfo(-1, e.getMessage());
        } finally
        {
            CloseableUtils.closeQuietly(client);
        }
    }

    /**
     * @description: 查询系统配置管理下的接入系统数据
     * @author: Wind-spg
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryAccessSystem", produces =
    { "application/json; charset=UTF-8" })
    public String queryAccessSystem(@RequestParam String englishName)
    {
        CuratorFramework client = zkConnector.getClient();
        List<Object> systemData = new ArrayList<Object>();
        String zkPath = "/" + CommonEnum.ConfigClassfication.CONFIG_MANAGEMENT.getName();
        try
        {
            if (!StringUtils.isBlank(englishName))
            {
                zkPath += ("/" + englishName);
                systemData.add(new String(client.getData().forPath(zkPath)));
                return buildSuccessResultInfo(systemData);
            }
            if (null == client.checkExists().forPath(zkPath))
            {
                return buildSuccessResultInfo(systemData);
            }
            List<String> children = new ArrayList<String>();
            children = client.getChildren().forPath(zkPath);
            for (String child : children)
            {
                systemData.add(JSONObject.parse(client.getData().forPath(zkPath + "/" + child),
                        CommonConstants.DESERIALIZER_FEATURES));
            }
            return buildSuccessResultInfo(systemData);
        } catch (Exception e)
        {
            LOGGER.error("", e);
            return buildFailedResultInfo(-1, e.getMessage());
        } finally
        {
            CloseableUtils.closeQuietly(client);
        }
    }

    /**
     * @description: 删除接入系统<br>
     *               同时会删除此系统下所有配置数据
     * @author: Wind-spg
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteAccessSystem", produces =
    { "application/json; charset=UTF-8" })
    public String deleteAccessSystem(@RequestParam String englishName)
    {
        CuratorFramework client = zkConnector.getClient();
        try
        {
            if (StringUtils.isBlank(englishName))
            {
                throw new IllegalArgumentException("需要删除的系统英文名为空！");
            }
            String zkPath = "/" + CommonEnum.ConfigClassfication.CONFIG_MANAGEMENT.getName() + "/"
                    + englishName;
            if (null == client.checkExists().forPath(zkPath))
            {
                throw new IllegalArgumentException("接入系统不存在！");
            }
            client.delete().guaranteed().deletingChildrenIfNeeded().forPath(zkPath);
            return buildSuccessResultInfo(0);
        } catch (Exception e)
        {
            LOGGER.error("", e);
            return buildFailedResultInfo(-1, e.getMessage());
        } finally
        {
            CloseableUtils.closeQuietly(client);
        }
    }

}

package com.spg.zkCenter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.spg.zkCenter.common.CommonConstants;
import com.spg.zkCenter.common.CommonEnum;
import com.spg.zkCenter.pojo.ConfigBean;
import com.spg.zkCenter.util.ZKConnector;

/**
 * 项目名称：zkCenter
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年12月4日 下午1:03:41
 * @version V1.0.0
 */
@Controller
@RequestMapping(value = "configCtrl")
public class ConfigController extends BaseController
{
    private static final Log LOGGER = LogFactory.getLog(ConfigController.class);

    @Resource
    ZKConnector zkConnector;
    
    /**
     * @description: 跳转到配置项页面时
     * @author: Wind-spg
     * @return
     */
    @RequestMapping(value = "configMgr")
    public ModelAndView configMgr()
    {
        ModelAndView view = new ModelAndView("configMgr");
        CuratorFramework client = zkConnector.getClient();
        List<ConfigBean> resultBeans = new ArrayList<ConfigBean>();
        try
        {
            String zkPath = "/" + CommonEnum.ConfigClassfication.CONFIG_MANAGEMENT.getName();
            if (null != client.checkExists().forPath(zkPath))
            {
                List<String> children = client.getChildren().forPath(zkPath);
                if (children.contains("zookeeper"))
                {
                    children.remove("zookeeper");
                }
                if (children.size() > 0)
                {
                    // 默认只查第一个配置项
                    zkPath += ("/" + children.get(0));
                    children = client.getChildren().forPath(zkPath);
                    for (String path : children)
                    {
                        ConfigBean configBean = JSON.parseObject(client.getData().forPath(zkPath + "/" + path),
                                ConfigBean.class, CommonConstants.DESERIALIZER_FEATURES);
                        resultBeans.add(configBean);
                    }
                }
            }
        } catch (Exception e)
        {
            LOGGER.error("", e);
        } finally
        {
            CloseableUtils.closeQuietly(client);
        }
        view.addObject("configs", resultBeans);
        view.addObject("accessSystem", queryAccessSystem());
        return view;
    }

    /**
     * @description: 添加系统配置项
     * @author: Wind-spg
     * @param request
     * @param configBean
     * @return
     */
    @RequestMapping(value = "addConfig", produces =
    { "application/json; charset=UTF-8" })
    @ResponseBody
    public String addConfig(HttpServletRequest request, @ModelAttribute ConfigBean configBean)
    {
        CuratorFramework client = zkConnector.getClient();
        try
        {
            String systemName = request.getParameter("systemName");
            String configPath = "/" + CommonEnum.ConfigClassfication.CONFIG_MANAGEMENT.getName() + "/"
                    + systemName + "/" + configBean.getConfigName();
            configBean.setConfigPath(configPath);
            Stat stat = client.checkExists().forPath(configPath);
            if (null == stat)
            {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                        .withACL(Ids.OPEN_ACL_UNSAFE).forPath(configPath);
            } else
            {
                throw new RuntimeException("当前配置项已经存在，请勿重复添加！");
            }
            String nodeData = JSON.toJSONString(configBean, CommonConstants.SERIALIZER_FEATURES);
            Stat resultStat = client.setData().forPath(configPath, nodeData.getBytes());
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
     * @description: 修改系统配置项数据
     * @author: Wind-spg
     * @param request
     * @param configBean
     * @return
     */
    @RequestMapping(value = "updateConfig", produces =
    { "application/json; charset=UTF-8" })
    @ResponseBody
    public String updateConfig(HttpServletRequest request, @ModelAttribute ConfigBean configBean)
    {
        CuratorFramework client = zkConnector.getClient();
        try
        {
            String systemName = request.getParameter("systemName");
            String configPath = "/" + CommonEnum.ConfigClassfication.CONFIG_MANAGEMENT.getName() + "/"
                    + systemName + "/" + configBean.getConfigName();
            configBean.setConfigPath(configPath);
            String nodeData = JSON.toJSONString(configBean, CommonConstants.SERIALIZER_FEATURES);
            Stat resultStat = client.setData().forPath(configPath, nodeData.getBytes());
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
     * @description: 查询系统配置项数据，以json返回
     * @author: Wind-spg
     * @param systemName
     * @param configName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryConfig", produces =
    { "application/json; charset=UTF-8" })
    public String queryConfig(@RequestParam String systemName, @RequestParam String configName)
    {
        CuratorFramework client = zkConnector.getClient();
        try
        {
            String zkPath = "/" + CommonEnum.ConfigClassfication.CONFIG_MANAGEMENT.getName();
            List<ConfigBean> resultBeans = new ArrayList<ConfigBean>();
            List<String> children = new ArrayList<String>();
            if (StringUtils.isNotEmpty(systemName) && StringUtils.isNotEmpty(configName))
            {
                zkPath += ("/" + systemName + "/" + configName);
                if (null == client.checkExists().forPath(zkPath))
                {
                    return buildSuccessResultInfo(resultBeans);
                }
                ConfigBean configBean = JSON.parseObject(client.getData().forPath(zkPath), ConfigBean.class,
                        CommonConstants.DESERIALIZER_FEATURES);
                resultBeans.add(configBean);
                return buildSuccessResultInfo(resultBeans);
            }
            if (StringUtils.isNotEmpty(systemName) && StringUtils.isEmpty(configName))
            {
                zkPath += ("/" + systemName);
                if (null == client.checkExists().forPath(zkPath))
                {
                    return buildSuccessResultInfo(resultBeans);
                }
                children = client.getChildren().forPath(zkPath);
            } else
            {
                children = client.getChildren().forPath(zkPath);
                if (children.contains("zookeeper"))
                {
                    children.remove("zookeeper");
                }
                if (children.size() > 0)
                {
                    zkPath += ("/" + children.get(0));
                    if (null == client.checkExists().forPath(zkPath))
                    {
                        return buildSuccessResultInfo(resultBeans);
                    }
                    children = client.getChildren().forPath(zkPath);
                }
            }
            for (String path : children)
            {
                ConfigBean configBean = JSON.parseObject(client.getData().forPath(zkPath + "/" + path),
                        ConfigBean.class, CommonConstants.DESERIALIZER_FEATURES);
                resultBeans.add(configBean);
            }
            return buildSuccessResultInfo(resultBeans);
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
     * @description: 删除系统配置项
     * @author: Wind-spg
     * @param systemName
     * @param configName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteConfig", produces =
    { "application/json; charset=UTF-8" })
    public String deleteConfig(@RequestParam String systemName, @RequestParam String configName)
    {
        CuratorFramework client = zkConnector.getClient();
        try
        {
            if (StringUtils.isEmpty(systemName) || StringUtils.isEmpty(configName))
            {
                throw new IllegalArgumentException("系统名或配置项为空！");
            }
            client.delete().forPath(
                    "/" + CommonEnum.ConfigClassfication.CONFIG_MANAGEMENT.getName() + "/" + systemName + "/"
                            + configName);
            return buildSuccessResultInfo(1);
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
     * @description: 查询接入系统
     * @author: Wind-spg
     * @return
     */
    private List<Object> queryAccessSystem()
    {
        CuratorFramework client = zkConnector.getClient();
        List<String> children = new ArrayList<String>();
        List<Object> systemData = new ArrayList<Object>();
        String zkPath = "/" + CommonEnum.ConfigClassfication.CONFIG_MANAGEMENT.getName();
        try
        {
            if (null != client.checkExists().forPath(zkPath))
            {
                children = client.getChildren().forPath(zkPath);
                for (String child : children)
                {
                    systemData.add(JSON.parseObject(client.getData().forPath(zkPath + "/" + child), Map.class,
                            CommonConstants.DESERIALIZER_FEATURES));
                }
            }
        } catch (Exception e)
        {
            LOGGER.error("queryAccessSystem error!", e);
        }
        return systemData;
    }
}

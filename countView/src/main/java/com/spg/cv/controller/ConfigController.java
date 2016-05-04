package com.spg.cv.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.po.PVBean;
import com.spg.cv.service.ConfigService;

/**
 * 项目名称：countView
 *
 * @description: 配置信息管理
 * @author Wind-spg
 * @create_time：2016年4月29日 下午10:39:25
 * @version V1.0.0
 */
@Controller
@RequestMapping(value = "configCtrl")
public class ConfigController extends BaseController
{
    private static final Log LOGGER = LogFactory.getLog(ConfigController.class);

    @Resource
    ConfigService configService;

    /**
     * @description: 增加一个配置
     * @author: Wind-spg
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addConfig", method = RequestMethod.POST, produces =
    { "application/json;charset=UTF-8" })
    public String addConfig(HttpServletRequest request)
    {
        String dataType = request.getParameter("dataType");
        String englishName = request.getParameter("englishName");
        String chineseDesc = request.getParameter("chineseDesc");
        PVBean pvBean = new PVBean(englishName, chineseDesc);

        DataType newDataType = null;
        if (StringUtils.isNotEmpty(dataType))
        {
            try
            {
                newDataType = DataType.getEnumByCode(Integer.parseInt(dataType));
            } catch (Exception e)
            {
                LOGGER.error(e);
                return buildFailedResultInfo(-1, e.getMessage());
            }
        }
        if (StringUtils.isEmpty(englishName) || StringUtils.isEmpty(chineseDesc))
        {
            return buildFailedResultInfo(-1, "页面英文名或中文描述为空！");
        }
        Long result = configService.addConfig(newDataType, pvBean);
        return buildSuccessResultInfo(result);
    }

    /**
     * @description: 删除一个埋点配置项
     * @author: Wind-spg
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteConfig", method = RequestMethod.POST, produces =
    { "application/json;charset=UTF-8" })
    public String deleteConfig(HttpServletRequest request)
    {
        String dataType = request.getParameter("dataType");
        String englishName = request.getParameter("englishName");
        String chineseDesc = request.getParameter("chineseDesc");
        PVBean pvBean = new PVBean(englishName, chineseDesc);

        DataType newDataType = null;
        if (StringUtils.isNotEmpty(dataType))
        {
            try
            {
                newDataType = DataType.getEnumByCode(Integer.parseInt(dataType));
            } catch (Exception e)
            {
                LOGGER.error(e);
                return buildFailedResultInfo(-1, e.getMessage());
            }
        }
        if (StringUtils.isEmpty(englishName) || StringUtils.isEmpty(chineseDesc))
        {
            return buildFailedResultInfo(-1, "页面英文名或中文描述为空！");
        }
        Long result = configService.addConfig(newDataType, pvBean);
        return buildSuccessResultInfo(result);
    }

}

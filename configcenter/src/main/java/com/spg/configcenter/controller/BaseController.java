
/**
 * 
 */
package com.spg.configcenter.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.spg.configcenter.bean.BaseResultInfo;

/**
 * 项目名称：configcenter
 *
 * @description:
 *
 * @author spg
 *
 * @create_time：2014年9月3日 下午8:12:08
 *
 * @version V1.0.0
 *
 */
public class BaseController
{
    private static final Log LOGGER = LogFactory.getLog(BaseController.class);
    
    protected String buildSuccessData(Object resultData)
    {
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        baseResultInfo.setStatus(0);
        baseResultInfo.setMessage("success");
        baseResultInfo.setData(resultData);
        LOGGER.debug("exit function, " + baseResultInfo);
        return JSON.toJSONString(baseResultInfo);
    }
    
    protected String buildDefaultSuccessData()
    {
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        baseResultInfo.setStatus(0);
        baseResultInfo.setMessage("success");
        LOGGER.debug("exit function, " + baseResultInfo);
        return JSON.toJSONString(baseResultInfo);
    }
    
    protected String buildFailedData(int resultCode, String errorMsg)
    {
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        baseResultInfo.setStatus(resultCode);
        baseResultInfo.setMessage(errorMsg);
        LOGGER.debug("exit function, " + baseResultInfo);
        return JSON.toJSONString(baseResultInfo);
    }
    
    protected String buildDefaultFailedData(String errorMsg)
    {
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        baseResultInfo.setStatus(-1);
        if (null == errorMsg || "".equals(errorMsg.trim()))
        {
            baseResultInfo.setMessage("unkonwn error!");
        }else {
            baseResultInfo.setMessage(errorMsg);
        }
        LOGGER.debug("exit function, " + baseResultInfo);
        return JSON.toJSONString(baseResultInfo);
    }
    
}


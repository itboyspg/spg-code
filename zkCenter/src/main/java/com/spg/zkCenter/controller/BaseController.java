/**
 * 
 */
package com.spg.zkCenter.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.spg.zkCenter.common.CommonConstants;
import com.spg.zkCenter.pojo.BaseResultVo;

/**
 * 项目名称：countView
 * 
 * @description: 基础controller
 * @author Wind-spg
 * @create_time：2015年11月8日 下午4:08:06
 * @version V1.0.0
 */
public class BaseController
{
    private static final Log LOGGER = LogFactory.getLog(BaseController.class);

    /**
     * @description: 构造成功返回结果
     * @author: Wind-spg
     * @param resultData
     *            : 需要返回的数据，可选
     * @return
     */
    protected String buildSuccessResultInfo(Object resultData)
    {
        LOGGER.debug(String.format("enter function, %s", resultData));
        BaseResultVo resultVo = new BaseResultVo();
        resultVo.setResultData(resultData);
        resultVo.setResultMessage("success");
        String result = JSON.toJSONString(resultVo, CommonConstants.SERIALIZE_CONFIG,
                CommonConstants.SERIALIZER_FEATURES);
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

    /**
     * @description: 构造失败返回结果
     * @author: Wind-spg
     * @param resultCode
     *            :任意非0数字，代表失败
     * @param failedMsg
     *            ：失败信息
     * @return
     */
    protected String buildFailedResultInfo(int resultCode, String failedMsg)
    {
        BaseResultVo resultVo = new BaseResultVo(resultCode, failedMsg);
        return JSON.toJSONString(resultVo, CommonConstants.SERIALIZER_FEATURES);
    }

    protected String object2JsonString(Object object)
    {
        return JSON.toJSONString(object, CommonConstants.SERIALIZER_FEATURES);
    }

    /**
     * @description:公共跳转页面
     * @author: Wind-spg
     * @param request
     * @return
     */
    @RequestMapping(value = "forward")
    protected ModelAndView toTargetView(HttpServletRequest request)
    {
        String targetViewName = request.getParameter("target");
        return new ModelAndView(targetViewName);
    }

    /**
     * 获取当前月份的当前日期前所有日期，如，当前为11月7号，则返回1,2,3,4,5,6,7
     * 
     * @description:
     * @author: Wind-spg
     * @return
     */
    protected List<Integer> getBeforeDaysList()
    {
        Calendar cal = Calendar.getInstance();

        int nowDay = cal.get(Calendar.DAY_OF_MONTH);
        List<Integer> result = new ArrayList<Integer>(nowDay);
        for (int i = 1; i <= nowDay; i++)
        {
            result.add(i);
        }
        return result;
    }

    /**
     * @description:根据时间格式获取最近15天时间(当前时间之前)
     * @author: Wind-spg
     * @param pattern
     *            时间格式
     * @return
     */
    protected List<String> getLast15Days(String pattern)
    {
        List<String> result = new ArrayList<String>();
        DateFormat format = new SimpleDateFormat(pattern);
        for (int i = 14; i >= 0; i--)
        {
            result.add(format.format(new Date(System.currentTimeMillis() - (i * (24 * 60 * 60 * 1000)))));
        }
        return result;
    }
}

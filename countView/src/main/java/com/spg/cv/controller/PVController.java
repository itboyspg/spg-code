package com.spg.cv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spg.common.dateutil.MyDateUtil;
import com.spg.cv.common.CommonConstants;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.service.PageService;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年11月7日 下午12:40:49
 * @version V1.0.0
 */
@Controller
@RequestMapping("pvCtrl")
public class PVController extends BaseController
{
    private static final Log LOGGER = LogFactory.getLog(PVController.class);

    @Resource
    PageService pageManager;

    @ResponseBody
    @RequestMapping(value = "addPV", method = RequestMethod.POST, produces =
    { "application/json; charset=UTF-8" })
    public String addPageView(HttpServletRequest request)
    {
        String pageName = request.getParameter("pageName");
        String dataType = request.getParameter("dataType");
        LOGGER.debug(String.format("enter function, %s, %s", dataType, pageName));
        try
        {
            String key = MyDateUtil.getFormatDate(new Date(System.currentTimeMillis()), "yyyyMMdd")
                    + DataType.getEnumByCode(Integer.parseInt(dataType)).getName();
            Long result = pageManager.addPageView(key, pageName);
            return buildSuccessResultInfo(result);
        } catch (Exception e)
        {
            LOGGER.error("addPageView error!", e);
            return buildFailedResultInfo(-1, e.getMessage());
        }
    }

    /**
     * @description: 查询PV量
     * @author: Wind-spg
     * @param request
     * @return
     */
    @RequestMapping("pv")
    public ModelAndView queryPv(HttpServletRequest request)
    {
        ModelAndView view = new ModelAndView("pageView");
        // 获取x轴数据
        List<Integer> xData = getBeforeDaysList();
        view.addObject("xData", xData);

        view.addObject("legendMap", CommonConstants.COUNT_DATA_RELATION_MAP);
        // 每天统计数据
        List<String> everyDayData = null;
        // 每个统计维度下的统计结果
        Map<String, List<String>> resultData = new HashMap<String, List<String>>();

        String tempKey = null;
        String tempValue = null;
        for (String key : CommonConstants.COUNT_DATA_RELATION_MAP.keySet())
        {
            everyDayData = new ArrayList<String>();
            for (Integer days : xData)
            {
                tempKey = MyDateUtil.getFormatDate(new Date(System.currentTimeMillis()), "yyyyMM");
                if (days < 10)
                {
                    tempValue = pageManager.queryPVDataByKeyField(
                            tempKey + "0" + days + DataType.PAGE_VIEW.getName(), key);
                } else
                {
                    tempValue = pageManager.queryPVDataByKeyField(
                            tempKey + days + DataType.PAGE_VIEW.getName(), key);
                }
                everyDayData.add(tempValue);
            }
            resultData.put(key, everyDayData);
        }
        view.addObject("viewData", resultData);
        return view;
    }
}

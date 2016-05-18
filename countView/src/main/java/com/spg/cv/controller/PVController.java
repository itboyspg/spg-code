package com.spg.cv.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.spg.common.dateutil.MyDateUtil;
import com.spg.cv.common.CommonConstants;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.po.PVBean;
import com.spg.cv.service.ConfigService;
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
    PageService pageService;
    @Resource
    ConfigService configService;

    @RequestMapping("toPvView")
    public ModelAndView toPvView()
    {
        ModelAndView resultView = new ModelAndView("pageView");

        // X轴
        resultView.addObject("xAxis", getLast15Days("MM/dd"));

        List<String> configPvData = configService.getAllConfig(DataType.PAGE_VIEW);
        List<PVBean> pvBeanList = new ArrayList<PVBean>();
        for (String str : configPvData)
        {
            pvBeanList.add(JSON.parseObject(str, PVBean.class));
        }

        Map<String, Map<String, String>> allPVCountData = new HashMap<String, Map<String, String>>();
        List<String> last15Day = getLast15Days("yyyyMMdd");
        for (String key : last15Day)
        {
            allPVCountData.put(key, pageService.getAllPVData(key + DataType.PAGE_VIEW.getName()));
        }
        resultView.addObject("yAxisDatas", convertAxisData(pvBeanList, last15Day, allPVCountData));
        return resultView;
    }

    /**
     * @description: 将数据库数据结构转换为易于页面展示的数据结构<br>
     * @author: Wind-spg
     * @param legendList
     *            页面的中英文对应关系
     * @param yAxisKeys
     *            需要展示的时间段数据，如[20160503, 20160504, 20160505, 20160506]
     * @param sourceData
     *            某段时间内各页面的访问量数据，注意此处页面为页面英文描述
     * @return 返回的map中，key为某页面的中文描述，value为这个页面某段时间内的访问量数据
     */
    private Map<String, List<String>> convertAxisData(List<PVBean> legendList, List<String> yAxisKeys,
            Map<String, Map<String, String>> sourceData)
    {
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        for (PVBean pvBean : legendList)
        {
            List<String> xAxisData = new ArrayList<String>();
            String tempData = null;
            for (String yAxisKey : yAxisKeys)
            {
                tempData = sourceData.get(yAxisKey).get(pvBean.getEnglishName());
                if (StringUtils.isNotEmpty(tempData))
                {
                    xAxisData.add(tempData);
                } else
                {
                    xAxisData.add("0");
                }
            }
            result.put(pvBean.getChineseDescription(), xAxisData);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "addPV", method = RequestMethod.GET, produces =
    { "application/json; charset=UTF-8" })
    public String addPageView(HttpServletRequest request)
    {
        String pageName = request.getParameter("pageEnglishName");
        String dataType = request.getParameter("dataType");
        LOGGER.debug(String.format("enter function, %s, %s", dataType, pageName));
        try
        {
            String key = MyDateUtil.getFormatDate(new Date(System.currentTimeMillis()), "yyyyMMdd")
                    + DataType.getEnumByCode(Integer.parseInt(dataType)).getName();
            Long result = pageService.addPageView(key, pageName);
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
                    tempValue = pageService.queryPVDataByKeyField(
                            tempKey + "0" + days + DataType.PAGE_VIEW.getName(), key);
                } else
                {
                    tempValue = pageService.queryPVDataByKeyField(
                            tempKey + days + DataType.PAGE_VIEW.getName(), key);
                }
                everyDayData.add(tempValue);
            }
            resultData.put(key, everyDayData);
        }
        view.addObject("viewData", resultData);
        return view;
    }

    /**
     * @description:根据时间格式获取最近15天时间
     * @author: Wind-spg
     * @param pattern
     *            时间格式
     * @return
     */
    private List<String> getLast15Days(String pattern)
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

package com.spg.cv.controller;

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

    @RequestMapping("toPvBtnLinkView")
    public ModelAndView toPvBtnLinkView(HttpServletRequest request)
    {
        String strDataType = request.getParameter("dataType");
        ModelAndView resultView = new ModelAndView("pageBtnLinkView");

        // X轴
        resultView.addObject("xAxis", getLast15Days("MM/dd"));

        DataType dataType = DataType.getEnumByCode(Integer.parseInt(strDataType));
        List<String> configPvData = configService.getAllConfig(dataType);
        List<PVBean> pvBeanList = new ArrayList<PVBean>();
        for (String str : configPvData)
        {
            pvBeanList.add(JSON.parseObject(str, PVBean.class));
        }

        Map<String, Map<String, String>> allPVCountData = new HashMap<String, Map<String, String>>();
        List<String> last15Day = getLast15Days("yyyyMMdd");
        for (String key : last15Day)
        {
            allPVCountData.put(key, pageService.getAllPVData(key + dataType.getName()));
        }
        resultView.addObject("yAxisDatas", convertAxisData(pvBeanList, last15Day, allPVCountData));
        resultView.addObject("strDataType", strDataType);
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

}

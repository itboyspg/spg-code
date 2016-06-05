package com.spg.cv.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spg.common.common.CommonUtil;
import com.spg.common.dateutil.MyDateUtil;
import com.spg.common.httputil.MyHttpUtil;
import com.spg.common.pojo.MyHttpResponse;
import com.spg.cv.common.CommonConstants;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.dao.RedisListAPIUtil;
import com.spg.cv.dao.RedisMapAPIUtil;
import com.spg.cv.service.UserActiveService;

/**
 * 项目名称：countView
 *
 * @description: 用户量相关统计
 * @author Wind-spg
 * @create_time：2016年1月10日 下午2:28:44
 * @version V1.0.0
 */
@Controller
@RequestMapping(value = "userCtrl")
public class UserActiveController extends BaseController
{
    private static final Log LOGGER = LogFactory.getLog(UserActiveController.class);

    @Resource
    UserActiveService userActiveService;

    /**
     * @description: 一次用户活跃
     * @author: Wind-spg
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "userActive", produces =
    { "appliction/json; charset=UTF-8" })
    public String userActive(HttpServletRequest request)
    {
        String sessionId = request.getSession().getId();
        try
        {
            String key = MyDateUtil.getFormatDate(new Date(System.currentTimeMillis()), "yyyyMMdd")
                    + DataType.USER_ACTIVE.getName();
            Long result = userActiveService.addActiveUser(key, sessionId);
            return buildSuccessResultInfo(result);
        } catch (Exception e)
        {
            LOGGER.error("add user active error!", e);
            return buildFailedResultInfo(-100, e.getMessage());
        }
    }

    /**
     * @description: 查询活跃用户量
     * @author: Wind-spg
     * @param request
     * @return
     */
    @RequestMapping("queryUserActive")
    public ModelAndView queryActiveUser(HttpServletRequest request)
    {
        ModelAndView view = new ModelAndView("userActiveView");

        // 获取x轴数据
        List<String> xAxis = getLast15Days("MM/dd");
        view.addObject("xAxis", xAxis);

        List<String> last15Day = getLast15Days("yyyyMMdd");

        List<Long> userActiveData = new ArrayList<Long>();
        Long userCount = 0L;
        for (String key : last15Day)
        {
            userCount = userActiveService.queryOneDayActiveUserCount(key + DataType.USER_ACTIVE.getName());
            userActiveData.add(userCount);
        }

        view.addObject("yAxis", userActiveData);
        return view;
    }

    @RequestMapping(value = "userIpMap")
    public ModelAndView userIpMap(HttpServletRequest request)
    {
        ModelAndView view = new ModelAndView("userIPMapView");
        List<String> countrys = RedisListAPIUtil.queryListData("IpMapCountry");
        Map<String, Long> userIpMapData = new HashMap<String, Long>();
        for (String country : countrys)
        {
            List<String> citys = RedisListAPIUtil.queryListData("IpMap" + country);
            for (String city : citys)
            {
                Map<String, String> cityIpCount = RedisMapAPIUtil.hgetAll("IpMap" + city);
                Long count = 0L;
                Entry<String, String> entry = null;
                for (Iterator<Entry<String, String>> iterator = cityIpCount.entrySet().iterator(); iterator
                        .hasNext();)
                {
                    entry = iterator.next();
                    count += Long.valueOf(entry.getValue());
                }
                if (city.contains("_"))
                {
                    userIpMapData.put(city.split("_")[1], count);
                } else
                {
                    userIpMapData.put(city, count);
                }
            }
        }
        view.addObject("userIpMapData", JSON.toJSONString(userIpMapData));
        return view;
    }

    /**
     * @description: 用户IP地址解析并入库
     * @author: Wind-spg
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addUserIp", produces =
    { "application/json; charset=UTF-8" })
    public String addUserIp(HttpServletRequest request)
    {
        String userIp = CommonUtil.getRealIpAddr(request);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("ip", userIp));
        MyHttpResponse response = null;
        try
        {
            response = MyHttpUtil.doHttpGet(CommonConstants.TAOBAO_IP_ANALYSE_URL, params, 3000, 0);
        } catch (URISyntaxException e)
        {
            LOGGER.error("analyse ip error!", e);
        } catch (IOException e)
        {
            LOGGER.error("analyse ip error!", e);
        }
        if (null != response)
        {
            String responseBody = response.getResponseBody();
            JSONObject responseJson = JSONObject.parseObject(responseBody);
            // 0表示成功，1表示失败
            if (null != responseJson && 0 == responseJson.getInteger("code"))
            {
                // 国家，CN表示中国
                String countryId = responseJson.getJSONObject("data").getString("country_id");
                String country = responseJson.getJSONObject("data").getString("country");
                // 省
                String region = responseJson.getJSONObject("data").getString("region");
                // 市
                String city = responseJson.getJSONObject("data").getString("city");
                // 目前只处理中国的IP
                if ("CN".equalsIgnoreCase(countryId) || "TW".equalsIgnoreCase(countryId)
                        || "HK".equalsIgnoreCase(countryId) || "MO".equalsIgnoreCase(countryId))
                {
                    Long addResult = saveUserCity(countryId, country, region, city);
                    return buildSuccessResultInfo(addResult);
                } else
                {
                    return buildFailedResultInfo(-1, "目前暂时只支持记录中国IP地址");
                }
            } else
            {
                return buildFailedResultInfo(-1, "IP地址解析失败！");
            }
        } else
        {
            return buildFailedResultInfo(-1, "IP地址解析失败！");
        }
    }

    /**
     * @description: 保存用户IP所在城市信息
     * @author: Wind-spg
     * @param countryId
     * @param country
     * @param region
     * @param city
     * @return
     */
    private Long saveUserCity(String countryId, String country, String region, String city)
    {
        if ("TW".equalsIgnoreCase(countryId) || "HK".equalsIgnoreCase(countryId)
                || "MO".equalsIgnoreCase(countryId))
        {
            city = region;
        }
        // 先保存国家
        if (!RedisListAPIUtil.isInList("IpMapCountry", countryId + "_" + country))
        {
            RedisListAPIUtil.addToList("IpMapCountry", countryId + "_" + country);
        }
        // 再保存国家_省
        if (!RedisListAPIUtil.isInList("IpMap" + countryId + "_" + country, countryId + "_" + region))
        {
            RedisListAPIUtil.addToList("IpMap" + countryId + "_" + country, countryId + "_" + region);
        }
        // 再保存省_市IP数量
        Long addResult = RedisMapAPIUtil.hsetAndIncre("IpMap" + countryId + "_" + region, city, 1L);
        return addResult;
    }
}

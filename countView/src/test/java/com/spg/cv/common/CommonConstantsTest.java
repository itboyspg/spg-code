package com.spg.cv.common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSONObject;
import com.spg.common.dateutil.MyDateUtil;
import com.spg.common.httputil.MyHttpUtil;
import com.spg.common.pojo.MyHttpResponse;
import com.spg.cv.BaseTest;
import com.spg.cv.dao.RedisListAPIUtil;
import com.spg.cv.dao.RedisMapAPIUtil;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年6月2日 下午10:47:35
 * @version V1.0.0
 */
public class CommonConstantsTest extends BaseTest
{
    // private static final Log LOGGER =
    // LogFactory.getLog(CommonConstantsTest.class);

    private Map<String, String> ipMap = new HashMap<String, String>();

    /**
     * @description: 初始化全国省份IP
     * @author: Wind-spg
     */
    @Test
    public void initProvince()
    {
        // 先清空原来数据
        Jedis jedis = RedisPoolUtil.getJedis();
        Set<String> keys = jedis.keys("*IpMap*");
        Long deleteCount = 0L;
        for (String str : keys)
        {
            deleteCount += jedis.del(str);
        }
        System.out.println(deleteCount);
        RedisPoolUtil.release(jedis);

        ipMap.put("台湾", "59.125.39.5");
        ipMap.put("澳门", "122.100.160.253");
        ipMap.put("香港", "203.198.69.64");
        ipMap.put("上海", "58.24.0.17");
        ipMap.put("浙江", "124.160.75.205");
        ipMap.put("福建", "58.22.0.17");
        ipMap.put("广东", "58.60.0.16");
        ipMap.put("云南", "222.219.207.34");
        ipMap.put("西藏自治区", "219.243.238.17");
        ipMap.put("宁夏自治区", "59.76.207.255");
        ipMap.put("陕西", "58.195.128.0");
        ipMap.put("安徽", "218.22.113.123");
        ipMap.put("湖北", "58.19.0.0");
        ipMap.put("重庆", "58.17.128.16");
        ipMap.put("湖南", "58.20.0.16");
        ipMap.put("北京", "106.2.176.22");
        ipMap.put("河北", "218.12.170.132");
        ipMap.put("山东", "58.14.0.17");
        ipMap.put("天津", "221.238.175.184");
        ipMap.put("海南", "59.50.72.83");
        ipMap.put("吉林", "59.72.128.17");
        ipMap.put("青海", "221.207.12.133");
        ipMap.put("新疆维吾尔自治区", "58.207.64.0");
        ipMap.put("内蒙古自治区", "58.18.0.0");
        ipMap.put("黑龙江", "125.211.0.17");
        ipMap.put("四川", "218.89.51.147");
        ipMap.put("贵州", "58.16.0.16");
        ipMap.put("广西自治区", "58.82.112.17");
        ipMap.put("辽宁", "60.18.131.131");
        ipMap.put("江苏", "218.4.162.17");
        ipMap.put("甘肃", "61.178.152.175");
        ipMap.put("山西", "222.199.42.0");
        ipMap.put("河南", "58.207.200.17");
        ipMap.put("江西", "58.17.0.16");

        for (Iterator<Entry<String, String>> iterator = ipMap.entrySet().iterator(); iterator.hasNext();)
        {
            String userIp = iterator.next().getValue();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ip", userIp));
            MyHttpResponse response = null;
            try
            {
                response = MyHttpUtil.doHttpGet(CommonConstants.TAOBAO_IP_ANALYSE_URL, params, 3000, 0);
            } catch (URISyntaxException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
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
                        saveUserCity(countryId, country, region, city);
                    }
                }
            }
        }
    }

    private void saveUserCity(String countryId, String country, String region, String city)
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
        if (!RedisListAPIUtil.isInList("IpMapCity" + countryId + "_" + country, countryId + "_" + region))
        {
            RedisListAPIUtil.addToList("IpMapCity" + countryId + "_" + country, countryId + "_" + region);
        }
        // 再保存省_市IP数量
        for (int i = 14; i >= 0; i--)
        {
            String strDate = MyDateUtil.getFormatDate(new Date(System.currentTimeMillis()
                    - (i * (24 * 60 * 60 * 1000))), "yyyyMMdd");
            String key = strDate + "IpMapCityData" + countryId + "_" + region;
            RedisMapAPIUtil.hsetAndIncre(key, city, RandomUtils.nextLong(100, 100000));
        }
    }
}

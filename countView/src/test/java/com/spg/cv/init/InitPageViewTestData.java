package com.spg.cv.init;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.spg.cv.BaseTest;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.common.RedisPoolUtil;
import com.spg.cv.po.ConfigBean;
import com.spg.cv.service.CommonService;
import com.spg.cv.service.ConfigService;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年6月16日 下午10:32:15
 * @version V1.0.0
 */
public class InitPageViewTestData extends BaseTest
{
    // private static final Log LOGGER =
    // LogFactory.getLog(InitPageViewTestData.class);
    @Resource
    ConfigService configService;
    @Resource
    CommonService commonService;

    @Test
    public void testPageViewData()
    {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        // 先清空原来数据
        Jedis jedis = RedisPoolUtil.getJedis();
        Set<String> keys = jedis.keys("*" + DataType.PAGE_VIEW.getName());
        Long deleteCount = 0L;
        for (String str : keys)
        {
            deleteCount += jedis.del(str);
        }
        System.out.println(deleteCount);
        RedisPoolUtil.release(jedis);

        // 添加过去15天时间数据（量随机）
        for (int i = 14; i >= 0; i--)
        {
            String key = format.format(new Date(System.currentTimeMillis() - (i * (24 * 60 * 60 * 1000))));
            List<String> configPvDataList = configService.getAllConfig(DataType.PAGE_VIEW);
            List<ConfigBean> listObject = new ArrayList<ConfigBean>();
            for (String str : configPvDataList)
            {
                listObject.add(JSON.parseObject(str, ConfigBean.class));
            }
            for (ConfigBean configBean : listObject)
            {
                long count = RandomUtils.nextLong(500, (15 - i) * 1000);
                commonService.addData(key + DataType.PAGE_VIEW.getName(), configBean.getEnglishName(), count);
            }
        }
    }
}

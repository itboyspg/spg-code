package com.spg.cv.init;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.spg.cv.BaseTest;
import com.spg.cv.common.RedisPoolUtil;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.po.ConfigBean;
import com.spg.cv.service.ConfigService;
import com.spg.cv.service.PageService;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年6月16日 下午10:46:13
 * @version V1.0.0
 */
public class InitLinkClickTestData extends BaseTest
{
    // private static final Log LOGGER =
    // LogFactory.getLog(InitLinkClickTestData.class);
    @Resource
    PageService pageService;
    @Resource
    ConfigService configService;

    @Test
    public void testLinkClickData()
    {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        // 先清空原来数据
        Jedis jedis = RedisPoolUtil.getJedis();
        jedis.del("*" + DataType.LINK_CLICK_COUNT.getName());
        RedisPoolUtil.release(jedis);

        // 添加过去15天时间数据（量随机）
        for (int i = 14; i >= 0; i--)
        {
            String key = format.format(new Date(System.currentTimeMillis() - (i * (24 * 60 * 60 * 1000))));
            List<String> configPvDataList = configService.getAllConfig(DataType.LINK_CLICK_COUNT);
            List<ConfigBean> listObject = new ArrayList<ConfigBean>();
            for (String str : configPvDataList)
            {
                listObject.add(JSON.parseObject(str, ConfigBean.class));
            }
            for (ConfigBean configBean : listObject)
            {
                int count = RandomUtils.nextInt(500, (15 - i) * 1000);
                for (int j = 0; j < count; j++)
                {
                    pageService.addPageEvent(DataType.LINK_CLICK_COUNT,
                            key + DataType.LINK_CLICK_COUNT.getName(), configBean.getEnglishName());
                }
            }
        }
    }
}

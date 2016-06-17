package com.spg.cv.manager.impl;

import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Ignore;
import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.spg.cv.BaseTest;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.common.RedisPoolUtil;
import com.spg.cv.po.ConfigBean;
import com.spg.cv.service.ConfigService;
import com.spg.cv.service.PageService;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年11月14日 下午11:26:50
 * @version V1.0.0
 */
public class PageServiceImplTest extends BaseTest
{
    // private static final Log LOGGER =
    // LogFactory.getLog(PageManagerImplTest.class);

    @Resource
    PageService pageService;

    @Resource
    ConfigService configService;

    @Test
    public void testAddPageView()
    {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
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
                int count = RandomUtils.nextInt(1, (15 - i) * 100);
                for (int j = 0; j < count; j++)
                {
                    pageService.addPageEvent(DataType.PAGE_VIEW, key + DataType.PAGE_VIEW.getName(),
                            configBean.getEnglishName());
                }
            }
        }
    }

    @Test
    public void testDelete()
    {
        Jedis client = RedisPoolUtil.getJedis();
        Set<String> keys = client.keys("*UserActive");
        for (String str : keys)
        {
            client.del(str);
        }
        RedisPoolUtil.release(client);
        System.out.println(keys);
    }

    @Test
    @Ignore
    public void testQueryCountData()
    {
        fail("Not yet implemented");
    }

    @Test
    @Ignore
    public void testQueryCountByCountKey()
    {
        fail("Not yet implemented");
    }
}

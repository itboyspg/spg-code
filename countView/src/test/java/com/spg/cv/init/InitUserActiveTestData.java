package com.spg.cv.init;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.spg.common.strutil.MyStringUtil;
import com.spg.cv.BaseTest;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.common.RedisPoolUtil;
import com.spg.cv.service.CommonService;
import com.spg.cv.service.ConfigService;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年6月16日 下午10:46:13
 * @version V1.0.0
 */
public class InitUserActiveTestData extends BaseTest
{
    // private static final Log LOGGER =
    // LogFactory.getLog(InitLinkClickTestData.class);
    @Resource
    ConfigService configService;
    @Resource
    CommonService commonService;

    @Test
    public void testUserActiveData()
    {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        // 先清空原来数据
        Jedis jedis = RedisPoolUtil.getJedis();
        Set<String> keys = jedis.keys("*" + DataType.USER_ACTIVE.getName());
        Long deleteCount = 0L;
        for (String str : keys)
        {
            deleteCount += jedis.del(str);
        }
        System.out.println(deleteCount);
        RedisPoolUtil.release(jedis);

        // 添加过去15天时间数据（量随机）
        int tempCount = 50;
        for (int i = 14; i >= 0; i--)
        {
            String key = format.format(new Date(System.currentTimeMillis() - (i * (24 * 60 * 60 * 1000))));
            int count = RandomUtils.nextInt(50, (15 - i) * 50);
            // 为了测试，让本次数据大于上次数据
            while (count < tempCount)
            {
                count = RandomUtils.nextInt(50, (15 - i) * 50);
            }
            tempCount = count;
            for (int j = 0; j < count; j++)
            {
                String sessionId = MyStringUtil.randomString(10, true);
                commonService.addData(key + DataType.USER_ACTIVE.getName(), sessionId, 1L);
            }
        }
    }
}

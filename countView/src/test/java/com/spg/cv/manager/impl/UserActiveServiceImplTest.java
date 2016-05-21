package com.spg.cv.manager.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import com.spg.common.dateutil.MyDateUtil;
import com.spg.common.strutil.MyStringUtil;
import com.spg.cv.BaseTest;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.service.UserActiveService;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年1月10日 下午4:15:20
 * @version V1.0.0
 */
public class UserActiveServiceImplTest extends BaseTest
{
    // private static final Log LOGGER =
    // LogFactory.getLog(UserCountManagerImplTest.class);

    @Resource
    UserActiveService userCountManager;

    @Test
    public void testAddActiveUser()
    {
        for (int i = 14; i >= 0; i--)
        {
            String key = MyDateUtil.getFormatDate(new Date(System.currentTimeMillis()
                    - (i * (24 * 60 * 60 * 1000))), "yyyyMMdd");
            key += DataType.USER_ACTIVE.getName();
            int count = RandomUtils.nextInt(1, (15 - i) * 100);
            for (int j = 0; j < count; j++)
            {
                userCountManager.addActiveUser(key, MyStringUtil.randomString(8, true));
            }
        }
    }

    @Test
    public void testQueryActiveUserCount()
    {
    }

    @Test
    public void testQueryOneDayActiveUserCount()
    {
    }
}

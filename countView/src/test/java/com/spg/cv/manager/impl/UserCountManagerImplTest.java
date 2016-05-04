
package com.spg.cv.manager.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.spg.common.dateutil.MyDateUtil;
import com.spg.common.strutil.MyStringUtil;
import com.spg.cv.BaseTest;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.service.UserCountService;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年1月10日 下午4:15:20
 * @version V1.0.0
 */
public class UserCountManagerImplTest extends BaseTest
{
    private static final Log LOGGER = LogFactory.getLog(UserCountManagerImplTest.class);

    @Resource
    UserCountService userCountManager;
    
    @Test
    public void testAddActiveUser()
    {
        for (int i = 1; i < 10; i++)
        {
            String key = MyDateUtil.getFormatDate(new Date(System.currentTimeMillis()), "yyyyMM");
            if (i < 10)
            {
                key += "0";
            }
            key += i;
            key += DataType.USER_ACTIVE.getName();
            userCountManager.addActiveUser(key, MyStringUtil.randomString(8, true));
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


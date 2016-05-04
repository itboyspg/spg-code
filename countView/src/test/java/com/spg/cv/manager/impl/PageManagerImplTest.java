
package com.spg.cv.manager.impl;

import static org.junit.Assert.fail;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.spg.common.dateutil.MyDateUtil;
import com.spg.cv.BaseTest;
import com.spg.cv.common.CommonConstants;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.service.PageService;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年11月14日 下午11:26:50
 * @version V1.0.0
 */
public class PageManagerImplTest extends BaseTest
{
//    private static final Log LOGGER = LogFactory.getLog(PageManagerImplTest.class);

    @Resource
    PageService pageManager;
    
    @Test
    public void testAddPageView()
    {
        int random = 0;
        for (int i = 1; i < 30; i++)
        {
            for (String pageName : CommonConstants.COUNT_DATA_RELATION_MAP.keySet())
            {
                random = RandomUtils.nextInt(1, 1000);
                for (int j = 0; j < random; j++)
                {
                    String key = MyDateUtil.getFormatDate(new Date(System.currentTimeMillis()), "yyyyMM");
                    if (i < 10)
                    {
                        key += "0";
                    }
                    key += i;
                    key += DataType.PAGE_VIEW.getName();
                    pageManager.addPageView(key, pageName);
                }
            }
        }
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


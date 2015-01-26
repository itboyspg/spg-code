/**
 * 
 */
package com.spg.common.dateutil;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 项目名称：spg-common
 * 
 * @description:
 * @author Wind-spg
 * @create_time：2014年11月27日 上午10:08:27
 * @version V1.0.0
 * 
 */
public class MyDateUtilTest
{

    // 默认时间 2014-11-27 10:14:12
    private static final Date DEFAU_DATE = new Date(1417054452490L);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void testGetFormatDate()
    {
        Assert.assertEquals("2014-11-27 10:14:12", MyDateUtil.getFormatDate(DEFAU_DATE, "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void testGetDefaultFormatDate()
    {
        Assert.assertEquals("2014-11-27", MyDateUtil.getDefaultFormatDate(DEFAU_DATE));
    }

    @Test
    public void testGetDefaultFormatDateTime()
    {
        Assert.assertEquals("2014-11-27 10:14:12", MyDateUtil.getDefaultFormatDateTime(DEFAU_DATE));
    }

    @Test
    public void testGetDefaultFormatTime()
    {
        Assert.assertEquals("10:14:12", MyDateUtil.getDefaultFormatTime(DEFAU_DATE));
    }
    
    @Test
    public void testGetOtherDaysDate1()
    {
        System.out.println(MyDateUtil.getOtherDaysDate("yyyy-MM-dd", 1));
    }
    
    @Test
    public void testGetOtherDaysDate2()
    {
        System.out.println(MyDateUtil.getOtherDaysDate("yyyy-MM-dd", -365));
    }
    
    @Test
    public void testParse()
    {
        System.out.println(MyDateUtil.parseStringDateTime("2014-04-12", "yyyy-MM-dd"));
    }
}

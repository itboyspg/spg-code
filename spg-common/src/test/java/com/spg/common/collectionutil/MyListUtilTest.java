/**
 * 
 */
package com.spg.common.collectionutil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 项目名称：spg-common
 * 
 * @description:
 * @author Wind-spg
 * @create_time：2014年11月27日 下午7:12:07
 * @version V1.0.0
 * 
 */
public class MyListUtilTest
{
    // private static final Log LOGGER = LogFactory.getLog(MyListUtilTest.class);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void testSubList()
    {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            list.add(UUID.randomUUID().toString());
        }
        System.out.println(list);
        System.out.println(MyListUtil.subList(list, 5, 5));
    }
}

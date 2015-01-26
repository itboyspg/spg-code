/**
 * 
 */
package com.spg.common.collectionutil;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 项目名称：spg-common
 * 
 * @description:
 * @author Wind-spg
 * @create_time：2014年11月27日 上午9:33:45
 * @version V1.0.0
 * 
 */
public class MyMapUtilTest
{
    // private static final Log LOGGER = LogFactory.getLog(MyMapUtilTest.class);

    private static Map<String, String> map = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        map = new HashMap<String, String>();
        for (int i = 0; i < 5; i++)
        {
            map.put(String.valueOf(i + 1), RandomUtils.nextInt(0, 1000000) + "");
        }
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void testSortByValue()
    {
        Map<String, String> result = MyMapUtil.sortByValue(map, true);
        System.out.println(result);
    }

    @Test
    public void testSubMapIntInt()
    {
        Map<String, String> result = MyMapUtil.sortByValue(map, true);
        Map<String, String> resultMap = MyMapUtil.subMap(result, 3, 50);
        System.out.println(resultMap);
        String str = "abcdef";
        System.out.println(str.substring(3, 5));
    }
    
    @Test
    public void testSubMapInt()
    {
        Map<String, String> result = MyMapUtil.sortByValue(map, true);
        Map<String, String> resultMap = MyMapUtil.subMap(result, 50);
        System.out.println(resultMap);
        String str = "abcdef";
        System.out.println(str.substring(3));
    }
}

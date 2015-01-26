/**
 * 
 */
package com.spg.common.collectionutil;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Ordering;

/**
 * 项目名称：spg-common
 * 
 * @description:
 * @author Wind-spg
 * @create_time：2014年11月26日 下午5:44:42
 * @version V1.0.0
 * 
 */
public class ValueComparableMapTest
{
    // private static final Log LOGGER = LogFactory.getLog(ValueComparableMapTest.class);

    private static Map<String, String> map = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        map = new HashMap<String, String>();
        for (int i = 0; i < 5000; i++)
        {
            map.put(UUID.randomUUID().toString(), RandomUtils.nextInt(0, 1000000) + "");
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void test()
    {

        // Ordering<String> result = Ordering.natural().onResultOf(Functions.forMap(map));
        // System.out.println(result);

        System.out.println("time:" + System.currentTimeMillis());
        Map<String, String> resultEntries = MyMapUtil.sortByValue(map, true);
        System.out.println(resultEntries);
        System.out.println("time:" + System.currentTimeMillis());

        TreeMap<String, String> maps = new ValueComparableMap<String, String>(Ordering.natural());
        maps.putAll(map);
        System.out.println(maps);
        System.out.println("time:" + System.currentTimeMillis());
    }

}

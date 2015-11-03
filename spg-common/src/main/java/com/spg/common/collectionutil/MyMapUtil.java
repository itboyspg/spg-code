/**
 * 
 */
package com.spg.common.collectionutil;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

/**
 * 项目名称：spg-common
 * 
 * @description:
 * @author Wind-spg
 * @create_time：2014年11月26日 下午4:53:50
 * @version V1.0.0
 */
public final class MyMapUtil
{

    // private static final Log LOGGER = LogFactory.getLog(MyMapUtil.class);

    private MyMapUtil()
    {

    }

    /**
     * @description: 对map中的数据按value进行排序
     * @author: Wind-spg
     * @param map
     * @ascent 是否升序
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map,
            final boolean ascent)
    {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                if (ascent)
                {
                    return (o1.getValue()).compareTo(o2.getValue());
                } else
                {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * @description: 对map中的数据按value进行排序，map中的value必须是int类型
     * @author: Wind-spg
     * @param map
     * @ascent 是否升序
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByIntValue(Map<K, V> map,
            final boolean ascent)
    {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                if (ascent)
                {
                    return (Integer.valueOf(String.valueOf(o1.getValue())) - Integer.valueOf(String
                            .valueOf(o2.getValue())));
                } else
                {
                    return (Integer.valueOf(String.valueOf(o2.getValue())) - Integer.valueOf(String
                            .valueOf(o1.getValue())));
                }
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * @description: map截取，区间为前闭后开，如果end超过map大小，则end按map大小截取
     * @author: Wind-spg
     * @param sourceMap
     * @param start
     * @param end
     * @return
     */
    public static <K, V> Map<K, V> subMap(Map<K, V> sourceMap, int start, int end)
    {
        Map<K, V> result = new LinkedHashMap<>();
        int index = 0;
        if (MapUtils.isNotEmpty(sourceMap))
        {
            for (K k : sourceMap.keySet())
            {
                if (index >= start && index < end)
                {
                    result.put(k, sourceMap.get(k));
                }
                if (index >= end)
                {
                    break;
                }
                index++;
            }
        }
        return result;
    }

    /**
     * @description: map截取，从指定位置处截取到结尾
     * @author: Wind-spg
     * @param sourceMap
     * @param start
     * @return
     */
    public static <K, V> Map<K, V> subMap(Map<K, V> sourceMap, int start)
    {
        Map<K, V> result = new LinkedHashMap<>();
        int index = 0;
        if (MapUtils.isNotEmpty(sourceMap))
        {
            for (K k : sourceMap.keySet())
            {
                if (index >= start)
                {
                    result.put(k, sourceMap.get(k));
                }
                index++;
            }
        }
        return result;
    }

}

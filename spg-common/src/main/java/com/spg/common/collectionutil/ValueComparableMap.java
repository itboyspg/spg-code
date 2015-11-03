/**
 * 
 */
package com.spg.common.collectionutil;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.base.Functions;
import com.google.common.collect.Ordering;

/**
 * 项目名称：spg-common
 * 
 * @description: 自定义map，存入此map中是数据会按value值进行排序存放，[升序]
 * @author Wind-spg
 * @create_time：2014年11月26日 下午5:30:32
 * @version V1.0.0
 */
public class ValueComparableMap<K extends Comparable<K>, V> extends TreeMap<K, V>
{
    /**
     * {变量说明}
     */
    private static final long serialVersionUID = 1L;

    // A map for doing lookups on the keys for comparison so we don't get infinite loops
    private final Map<K, V> valueMap;

    public ValueComparableMap(final Ordering<? super V> partialValueOrdering)
    {
        this(partialValueOrdering, new HashMap<K, V>());
    }

    private ValueComparableMap(Ordering<? super V> partialValueOrdering, HashMap<K, V> valueMap)
    {
        super(partialValueOrdering // Apply the value ordering
                .onResultOf(Functions.forMap(valueMap)) // On the result of getting the value for the key from the
                                                        // map
                .compound(Ordering.natural())); // as well as ensuring that the keys don't get clobbered
        this.valueMap = valueMap;
    }

    public V put(K k, V v)
    {
        if (valueMap.containsKey(k))
        {
            // remove the key in the sorted set before adding the key again
            remove(k);
        }
        valueMap.put(k, v); // To get "real" unsorted values for the comparator
        return super.put(k, v); // Put it in value order
    }

    public void putAll(Map<? extends K, ? extends V> map)
    {
        valueMap.putAll(map); // To get "real" unsorted values for the comparator
        super.putAll(map); // Put it in value order
    }
}

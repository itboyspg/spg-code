/**
 * 
 */
package com.spg.common.collectionutil;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

/**
 * 项目名称：spg-common
 * 
 * @description:
 * @author Wind-spg
 * @create_time：2014年11月27日 下午7:03:06
 * @version V1.0.0
 */
public final class MyListUtil
{

    // private static final Log LOGGER = LogFactory.getLog(MyListUtil.class);

    public MyListUtil()
    {

    }

    /**
     * @description: 
     *               list集合截取，从start处开始截取，截取length长度子串，如果length大于源list大小，则截取到list大小处
     * @author: Wind-spg
     * @param sourceList
     * @param start
     *            开始小标
     * @param length
     *            截取长度
     * @return
     */
    public static <T> List<T> subList(List<T> sourceList, int start, int length)
    {
        if (CollectionUtils.isNotEmpty(sourceList))
        {
            if (start + length >= sourceList.size())
            {
                return sourceList.subList(start, sourceList.size());
            } else
            {
                return sourceList.subList(start, start + length);
            }
        } else
        {
            return new ArrayList<>();
        }
    }

}

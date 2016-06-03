/**
 * @Title: RedisListAPIUtil.java
 * @Description: redis list工具
 */
package com.spg.cv.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;

import com.spg.cv.common.RedisPoolUtil;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年4月29日 下午10:59:49
 * @version V1.0.0
 */
public final class RedisListAPIUtil
{
    private static final Log LOGGER = LogFactory.getLog(RedisListAPIUtil.class);

    private RedisListAPIUtil()
    {

    }

    /**
     * @description: 指定list中添加某值
     * @author: Wind-spg
     * @param key
     *            list名
     * @param values
     *            需要添加的值列表
     * @return
     */
    public static Long addToList(String key, String... values)
    {
        LOGGER.debug(String.format("enter function, %s, %s", key, values));
        Jedis client = RedisPoolUtil.getJedis();
        try
        {
            return client.rpush(key, values);
        } finally
        {
            RedisPoolUtil.release(client);
        }
    }

    /**
     * @description: 判断key是否存在
     * @author: Wind-spg
     * @param key
     * @return
     */
    public static Boolean existKey(String key)
    {
        LOGGER.debug(String.format("enter function, %s", key));
        Jedis client = RedisPoolUtil.getJedis();
        try
        {
            return client.exists(key);
        } finally
        {
            RedisPoolUtil.release(client);
        }
    }

    /**
     * @description: 判断list中是否包含某值，注意：数据量过大时慎用
     * @author: Wind-spg
     * @param key
     * @param field
     * @return
     */
    public static boolean isInList(String key, String field)
    {
        LOGGER.debug(String.format("enter function, %s", key));
        List<String> allData = queryListData(key);
        return allData.contains(field);
    }

    /**
     * @description: 删除某list中指定的所有值
     * @author: Wind-spg
     * @param key
     *            list名
     * @param value
     *            需要删除的值
     * @return
     */
    public static Long deleteList(String key, String value)
    {
        LOGGER.debug(String.format("enter function, %s, %s", key, value));
        Jedis client = RedisPoolUtil.getJedis();
        try
        {
            return client.lrem(key, 0, value);
        } finally
        {
            RedisPoolUtil.release(client);
        }
    }

    /**
     * @description: 查询list中所有数据
     * @author: Wind-spg
     * @param key
     *            需要查询的list
     * @return
     */
    public static List<String> queryListData(String key)
    {
        LOGGER.debug(String.format("enter function, %s", key));
        Jedis client = RedisPoolUtil.getJedis();
        try
        {
            return client.lrange(key, 0, -1);
        } finally
        {
            RedisPoolUtil.release(client);
        }
    }

    /**
     * @description: 修改list中所有数据(先删除再新增)
     * @author: Wind-spg
     * @param key
     * @param oldValue
     * @param newValue
     * @return
     */
    public static Long updateListData(String key, String oldValue, String newValue)
    {
        LOGGER.debug(String.format("enter function, %s, %s, %s", key, oldValue, newValue));
        Jedis client = RedisPoolUtil.getJedis();
        try
        {
            Long deleteCount = client.lrem(key, 0, oldValue);
            if (deleteCount > 0)
            {
                return client.lpush(key, newValue);
            }
        } finally
        {
            RedisPoolUtil.release(client);
        }
        return 0L;
    }

}

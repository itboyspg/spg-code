package com.spg.cv.dao;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

import com.spg.cv.common.RedisPoolUtil;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年11月7日 下午2:38:11
 * @version V1.0.0
 */
public final class RedisMapAPIUtil
{
    private static final Log LOGGER = LogFactory.getLog(RedisMapAPIUtil.class);

    private RedisMapAPIUtil()
    {
    }

    public static String set(String key, String value)
    {
        LOGGER.debug(String.format("enter function, %s, %s", key, value));
        Jedis jedis = RedisPoolUtil.getJedis();
        String result = null;
        try
        {
            result = jedis.set(key, value);
        } finally
        {
            RedisPoolUtil.release(jedis);
        }
        return result;
    }

    public static Long hsetAndIncre(String key, String field, Long value)
    {
        LOGGER.debug(String.format("enter function, %s, %s, %s", key, field, value));
        Jedis jedis = RedisPoolUtil.getJedis();
        Long result = null;
        try
        {
            if (jedis.hexists(key, field))
            {
                result = jedis.hincrBy(key, field, value);
            } else
            {
                result = jedis.hset(key, field, String.valueOf(value));
            }
        } finally
        {
            RedisPoolUtil.release(jedis);
        }
        return result;
    }

    public static ScanResult<Entry<String, String>> hscan(String key, String cursor)
    {
        LOGGER.debug(String.format("enter function, %s, %s", key, cursor));
        Jedis jedis = RedisPoolUtil.getJedis();
        ScanResult<Entry<String, String>> scanResult = null;
        try
        {
            scanResult = jedis.hscan(key, cursor);
        } finally
        {
            RedisPoolUtil.release(jedis);
        }
        return scanResult;
    }
    
    public static Long hlen(String key)
    {
        LOGGER.debug(String.format("enter function, %s", key));
        Jedis jedis = RedisPoolUtil.getJedis();
        Long scanResult = null;
        try
        {
            scanResult = jedis.hlen(key);
        } finally
        {
            RedisPoolUtil.release(jedis);
        }
        return scanResult;
    }

    public static String hget(String key, String field)
    {
        LOGGER.debug(String.format("enter function, %s, %s", key, field));
        Jedis jedis = RedisPoolUtil.getJedis();
        String scanResult = null;
        try
        {
            scanResult = jedis.hget(key, field);
        } finally
        {
            RedisPoolUtil.release(jedis);
        }
        return scanResult;
    }

    public static Long hset(String key, String field, String value)
    {
        LOGGER.debug(String.format("enter function, %s, %s", key, field));
        Jedis jedis = RedisPoolUtil.getJedis();
        Long scanResult = null;
        try
        {
            scanResult = jedis.hset(key, field, value);
        } finally
        {
            RedisPoolUtil.release(jedis);
        }
        return scanResult;
    }
    
    public static Map<String, String> hgetAll(String key)
    {
        LOGGER.debug(String.format("enter function, %s", key));
        Jedis jedis = RedisPoolUtil.getJedis();
        Map<String, String> scanResult = null;
        try
        {
            scanResult = jedis.hgetAll(key);
        } finally
        {
            RedisPoolUtil.release(jedis);
        }
        return scanResult;
    }
}

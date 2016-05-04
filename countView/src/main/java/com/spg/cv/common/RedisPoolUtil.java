package com.spg.cv.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年11月7日 下午1:21:24
 * @version V1.0.0
 */
public class RedisPoolUtil
{
    private static final Log LOGGER = LogFactory.getLog(RedisPoolUtil.class);

    private RedisPoolUtil()
    {
    }

    private static JedisPool jedisPool;

    public static Jedis getJedis() throws JedisException
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
        } catch (JedisException e)
        {
            LOGGER.error("failed:jedisPool getResource.", e);
            if (jedis != null)
            {
                jedisPool.close();
            }
            throw e;
        }
        return jedis;
    }

    public static void release(Jedis jedis)
    {
        if (jedis != null)
        {
            jedisPool.returnResourceObject(jedis);
        }
    }

    @Autowired(required = true)
    public void setJedisPool(JedisPool jedisPool)
    {
        RedisPoolUtil.jedisPool = jedisPool;
    }

}

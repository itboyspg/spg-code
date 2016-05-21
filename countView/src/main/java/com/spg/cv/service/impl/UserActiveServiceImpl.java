package com.spg.cv.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.spg.cv.dao.RedisMapAPIUtil;
import com.spg.cv.service.UserActiveService;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年1月10日 下午2:38:53
 * @version V1.0.0
 */
@Service("userActiveService")
public class UserActiveServiceImpl extends CommonServiceImpl implements UserActiveService
{
    private static final Log LOGGER = LogFactory.getLog(UserActiveServiceImpl.class);

    @Override
    public Long addActiveUser(String key, String sessionId)
    {
        LOGGER.debug(String.format("enter function, %s, %s", key, sessionId));
        Long result = addData(key, sessionId, 1L);
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

    @Override
    public String queryActiveUserCount(String key, String sessionId)
    {
        LOGGER.debug(String.format("enter function, %s, %s", key, sessionId));
        String result = queryDataByKeyField(key, sessionId);
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

    @Override
    public Long queryOneDayActiveUserCount(String key)
    {
        LOGGER.debug(String.format("enter function, %s", key));
        Long result = RedisMapAPIUtil.hlen(key);
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

}

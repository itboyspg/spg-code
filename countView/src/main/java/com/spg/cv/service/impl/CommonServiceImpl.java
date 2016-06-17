package com.spg.cv.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.spg.cv.dao.RedisMapAPIUtil;
import com.spg.cv.service.CommonService;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年1月10日 下午2:55:14
 * @version V1.0.0
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService
{
    private static final Log LOGGER = LogFactory.getLog(CommonServiceImpl.class);

    @Override
    public Long addData(String key, String field, Long incrCount)
    {
        LOGGER.debug(String.format("enter function, %s, %s, %s", key, field, incrCount));
        Long result = RedisMapAPIUtil.hsetAndIncre(key, field, incrCount);
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

    @Override
    public String queryDataByKeyField(String key, String field)
    {
        LOGGER.debug(String.format("enter function, %s, %s", key, field));
        String result = RedisMapAPIUtil.hget(key, field);
        LOGGER.debug(String.format("exit function, %s", result));
        return StringUtils.isEmpty(result) ? "0" : result;
    }
}

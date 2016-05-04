package com.spg.cv.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.spg.cv.common.CommonConstants;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.dao.RedisListAPIUtil;
import com.spg.cv.po.PVBean;
import com.spg.cv.service.ConfigService;

/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年4月29日 下午10:54:26
 * @version V1.0.0
 */
@Service(value = "configService")
public class ConfigServiceImpl implements ConfigService
{
    private static final Log LOGGER = LogFactory.getLog(ConfigServiceImpl.class);

    @Override
    public Long addConfig(DataType dataType, PVBean pvBean)
    {
        LOGGER.debug(String.format("enter function, %s, %s", dataType, pvBean));
        Long result = RedisListAPIUtil.addToList(dataType.getName(),
                JSON.toJSONString(pvBean, CommonConstants.SERIALIZER_FEATURES));
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

    @Override
    public Long deleteConfig(DataType dataType, PVBean pvBean)
    {
        LOGGER.debug(String.format("enter function"));
        Long result = RedisListAPIUtil.deleteList(dataType.getName(),
                JSON.toJSONString(pvBean, CommonConstants.SERIALIZER_FEATURES));
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

    @Override
    public List<String> getAllConfig(DataType dataType)
    {
        LOGGER.debug(String.format("enter function， %s", dataType));
        List<String> result = RedisListAPIUtil.queryListData(dataType.getName());
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }
}

package com.spg.cv.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.spg.cv.common.CommonConstants;
import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.dao.RedisListAPIUtil;
import com.spg.cv.po.ConfigBean;
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
    public Long addConfig(DataType dataType, ConfigBean configBean)
    {
        LOGGER.debug(String.format("enter function, %s, %s", dataType, configBean));
        Long result = RedisListAPIUtil.addToList(dataType.getConfigName(),
                JSON.toJSONString(configBean, CommonConstants.SERIALIZER_FEATURES));
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

    @Override
    public Long deleteConfig(DataType dataType, ConfigBean configBean)
    {
        LOGGER.debug(String.format("enter function"));
        Long result = RedisListAPIUtil.deleteList(dataType.getName(),
                JSON.toJSONString(configBean, CommonConstants.SERIALIZER_FEATURES));
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

    @Override
    public List<String> getAllConfig(DataType dataType)
    {
        LOGGER.debug(String.format("enter function， %s", dataType));
        List<String> result = RedisListAPIUtil.queryListData(dataType.getConfigName());
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

    @Override
    public Long deleteConfigs(DataType dataType, List<ConfigBean> configBeans)
    {
        LOGGER.debug(String.format("enter function, %s, %s", dataType, configBeans));
        Long result = 0L;
        if (CollectionUtils.isNotEmpty(configBeans))
        {
            for (ConfigBean bean : configBeans)
            {
                result += deleteConfig(dataType, bean);
            }
        }
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

    @Override
    public Long updateConfig(DataType dataType, ConfigBean oldConfigBean, ConfigBean newConfigBean)
    {
        LOGGER.debug(String.format("enter function, %s, %s, %s", dataType, oldConfigBean, newConfigBean));
        Long result = RedisListAPIUtil.updateListData(dataType.getConfigName(),
                JSON.toJSONString(oldConfigBean, CommonConstants.SERIALIZER_FEATURES),
                JSON.toJSONString(newConfigBean, CommonConstants.SERIALIZER_FEATURES));
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }
}

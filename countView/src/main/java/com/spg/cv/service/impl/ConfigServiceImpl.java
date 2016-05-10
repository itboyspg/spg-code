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

    @Override
    public Long deleteConfigs(DataType dataType, List<PVBean> pvBeans)
    {
        LOGGER.debug(String.format("enter function, %s, %s", dataType, pvBeans));
        Long result = 0L;
        if (CollectionUtils.isNotEmpty(pvBeans))
        {
            for (PVBean bean : pvBeans)
            {
                result += deleteConfig(dataType, bean);
            }
        }
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }

    @Override
    public Long updateConfig(DataType dataType, PVBean oldPvBean, PVBean newPvBean)
    {
        LOGGER.debug(String.format("enter function, %s, %s, %s", dataType, oldPvBean, newPvBean));
        Long result = RedisListAPIUtil.updateListData(dataType.getName(),
                JSON.toJSONString(oldPvBean, CommonConstants.SERIALIZER_FEATURES),
                JSON.toJSONString(newPvBean, CommonConstants.SERIALIZER_FEATURES));
        LOGGER.debug(String.format("exit function, %s", result));
        return result;
    }
}

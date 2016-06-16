
package com.spg.cv.service;

import java.util.List;

import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.po.ConfigBean;


/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年4月29日 下午10:48:03
 * @version V1.0.0
 */
public interface ConfigService
{

    Long addConfig(DataType dataType, ConfigBean configBean);
    
    Long deleteConfig(DataType dataType, ConfigBean configBean);
    
    Long deleteConfigs(DataType dataType, List<ConfigBean> configBeans);
    
    Long updateConfig(DataType dataType, ConfigBean oldConfigBean, ConfigBean newConfigBean);
    
    List<String> getAllConfig(DataType dataType);
    
}


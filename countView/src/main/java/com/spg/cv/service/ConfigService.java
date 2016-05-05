
package com.spg.cv.service;

import java.util.List;

import com.spg.cv.common.CommonEnum.DataType;
import com.spg.cv.po.PVBean;


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

    Long addConfig(DataType dataType, PVBean pvBean);
    
    Long deleteConfig(DataType dataType, PVBean pvBean);
    
    Long deleteConfigs(DataType dataType, List<PVBean> pvBeans);
    
    List<String> getAllConfig(DataType dataType);
    
}


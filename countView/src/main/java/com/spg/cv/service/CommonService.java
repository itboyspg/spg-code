
package com.spg.cv.service;


/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年1月10日 下午2:48:50
 * @version V1.0.0
 */
public interface CommonService
{

    /**
     * @description: 往redis中存统计量，如果没有，则存入，如果有，则递增incrCount量的数据
     * @author: Wind-spg
     * @param key
     * @param field
     * @param incrCount
     * @return
     */
    Long addData(String key, String field, Long incrCount);
    
    /**
     * @description: 根据key和field查询对应统计量
     * @author: Wind-spg
     * @param key
     * @param field
     * @return
     */
    String queryDataByKeyField(String key, String field);
}


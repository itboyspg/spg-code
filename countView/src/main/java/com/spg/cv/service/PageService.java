
package com.spg.cv.service;



/**
 * 项目名称：countView
 *
 * @description:
 * @author Wind-spg
 * @create_time：2015年11月7日 下午10:18:50
 * @version V1.0.0
 */
public interface PageService
{
    
    /**
     * @description: 添加一次pv访问量
     * @author: Wind-spg
     * @param key
     * @param pageName
     * @return
     */
    Long addPageView(String key, String pageName);
    
    /**
     * @description:根据key和field查询相应统计数据
     * @author: Wind-spg
     * @param key
     * @param field
     * @return
     */
    String queryPVDataByKeyField(String key, String field);
}


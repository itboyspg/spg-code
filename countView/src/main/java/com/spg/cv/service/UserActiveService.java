
package com.spg.cv.service;


/**
 * 项目名称：countView
 *
 * @description: 用户信息统计
 * @author Wind-spg
 * @create_time：2016年1月10日 下午2:31:07
 * @version V1.0.0
 */
public interface UserActiveService
{

    /**
     * 添加用户活跃
     * @description:
     * @author: Wind-spg
     * @param key
     * @param sessionId
     * @return
     */
    Long addActiveUser(String key, String sessionId);
    
    /**
     * @description: 根据key和filed查询相应用户活跃量
     * @author: Wind-spg
     * @param key
     * @param sessionId
     * @return
     */
    String queryActiveUserCount(String key, String sessionId);
    
    /**
     * @description: 查询某一天活跃用户量
     * @author: Wind-spg
     * @param key
     * @return
     */
    Long queryOneDayActiveUserCount(String key);
}


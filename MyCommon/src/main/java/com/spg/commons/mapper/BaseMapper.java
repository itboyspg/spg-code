/**
 * 
 */
package com.spg.commons.mapper;

import java.util.List;
import java.util.Map;

/**
 * 项目名称：MyCommon
 * 
 * @description:dao层基础接口
 * 
 * @author spg
 * 
 * @create_time：2014年9月1日 下午5:31:14
 * 
 * @version V1.0.0
 * @param <T>
 * 
 */
public interface BaseMapper<T>
{

    /**
     * 插入记录
     * 
     * @param object
     * @return
     * 
     */
    int insert(T object);

    /**
     * 批量插入
     * 
     * @param list
     * @return
     */
    int batchInsert(List<T> list);

    /**
     * 批量更新
     * 
     * @param list
     * @return
     */
    int batchUpdate(List<T> list);

    /**
     * 根据ID删除某一条记录
     * 
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据条件查询总数
     * 
     * @param params
     * @return
     */
    int count(Map<String, Object> params);

    /**
     * 批量删除
     * 
     * @param list
     * @return
     */
    int batchDelete(List<Long> list);

    /**
     * 更新对象
     * 
     * @param object
     * @return
     */
    int update(T object);

    /**
     * 根据条件查询列表
     * 
     * @param params
     * @return
     */
    List<T> list(Map<String, Object> params);

}

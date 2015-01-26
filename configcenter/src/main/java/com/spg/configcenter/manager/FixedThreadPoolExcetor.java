
/**
 * 
 */
package com.spg.configcenter.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目名称：configcenter
 *
 * @description:线程池管理类
 *
 * @author spg
 *
 * @create_time：2014年9月2日 下午12:01:16
 *
 * @version V1.0.0
 *
 */
public class FixedThreadPoolExcetor
{
    
    private static final Log LOGGER = LogFactory.getLog(FixedThreadPoolExcetor.class);
    
    private static final FixedThreadPoolExcetor THREADPOOL_EXCETOR = new FixedThreadPoolExcetor();

    /**
     * 初始化100个线程
     */
    private ExecutorService executorService = Executors.newFixedThreadPool(100);
    
    private FixedThreadPoolExcetor()
    {
        
    }
    
    public static FixedThreadPoolExcetor getInstance()
    {
        return THREADPOOL_EXCETOR;
    }
    
    public void addTask(Runnable task)
    {
        LOGGER.debug("add task");
        executorService.execute(task);
    }
    
}


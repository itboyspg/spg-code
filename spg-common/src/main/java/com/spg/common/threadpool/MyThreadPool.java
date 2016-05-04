/**
 * 
 */
package com.spg.common.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目名称：spg-common
 * 
 * @description: 线程池工具类<br/>
 *               注意线程池大小应该可配置，通过spring注入来进行初始化，同时tomcat关闭的时候，也应该shutdown线程池，
 *               保证线程池中的所有线程处理完任务
 * @author Wind-spg
 * @create_time：2014年10月11日 下午3:08:33
 * @version V1.0.0
 */
public class MyThreadPool
{

    private static final Log LOGGER = LogFactory.getLog(MyThreadPool.class);

    // 线程池最小线程数，最大线程数将是最小线程数两倍
    private int threadPoolSize = 2;

    // 每个线程默认存活时间，单位秒
    private int threadAliveTime = 60;

    // 线程池中任务队列
    private static BlockingQueue<Runnable> workQueue;

    private static ThreadPoolExecutor threadPoolExecutor = null;

    private static MyThreadPool threadPool = null;

    private MyThreadPool()
    {
    }

    public static synchronized MyThreadPool getInstance()
    {
        if (null == threadPool)
        {
            threadPool = new MyThreadPool();
        }
        return threadPool;
    }

    /**
     * @description:获取线程池实例
     * @author: Wind-spg
     * @return
     */
    public synchronized ThreadPoolExecutor geThreadPoolExecutor()
    {
        try
        {
            if (null == workQueue)
            {
                workQueue = new LinkedBlockingDeque<>(100);
            }
            if (null == threadPoolExecutor)
            {
                threadPoolExecutor = new ThreadPoolExecutor(threadPoolSize, threadPoolSize * 2,
                        threadAliveTime, TimeUnit.SECONDS, workQueue);
            }
        } catch (Exception e)
        {
            LOGGER.error("init thread pool error!", e);
        }
        return threadPoolExecutor;
    }

    /**
     * @description:关闭线程池
     * @author: Wind-spg
     */
    public void shutdownThreadPool()
    {
        if (null != threadPoolExecutor && !threadPoolExecutor.isShutdown())
        {
            LOGGER.info("shutdown threadpool!");
            threadPoolExecutor.shutdown();
        }
    }

    public void setThreadPoolSize(int threadPoolSize)
    {
        this.threadPoolSize = threadPoolSize;
    }

    public void setThreadAliveTime(int threadAliveTime)
    {
        this.threadAliveTime = threadAliveTime;
    }

}

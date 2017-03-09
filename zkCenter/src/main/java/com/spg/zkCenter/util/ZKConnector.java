package com.spg.zkCenter.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

/**
 * 项目名称：zkCenter
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年11月27日 下午2:44:01
 * @version V1.0.0
 */
public class ZKConnector
{
//    private static final Log LOGGER = LogFactory.getLog(ZKConnector.class);

    private String connectionString = "localhost:2181";

    private int sessionTimeoutMs = 10 * 1000;

    private int connectionTimeoutMs = 5 * 1000;

    public ZKConnector(String connectionString, int sessionTimeoutMs, int connectionTimeoutMs)
    {
        this.connectionString = connectionString;
        this.sessionTimeoutMs = sessionTimeoutMs;
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public CuratorFramework getClient()
    {
        CuratorFramework client = null;
        client = CuratorFrameworkFactory.builder().connectString(connectionString)
                .connectionTimeoutMs(connectionTimeoutMs).sessionTimeoutMs(sessionTimeoutMs)
                .retryPolicy(new RetryNTimes(3, 1000)).build();
        client.start();
        return client;
    }

    public String getConnectionString()
    {
        return connectionString;
    }

    public void setConnectionString(String connectionString)
    {
        this.connectionString = connectionString;
    }

    public int getSessionTimeoutMs()
    {
        return sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(int sessionTimeoutMs)
    {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public int getConnectionTimeoutMs()
    {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(int connectionTimeoutMs)
    {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

}

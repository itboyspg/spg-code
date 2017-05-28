package com.spg.zkCenter.common;

import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;

/**
 * 项目名称：zkCenter
 *
 * @description:
 * @author Wind-spg
 * @create_time：2017年5月20日 下午11:00:32
 * @version V1.0.0
 */
public class MongoConnection
{
    private static final Log LOGGER = LogFactory.getLog(MongoConnection.class);

    private final static String HOST = "spg.master";// 端口
    private final static int PORT = 27017;// 端口
//    private final static int POOLSIZE = 100;// 连接数量
//    private final static int BLOCKSIZE = 100; // 等待队列长度
    private static MongoClient mongo = null;

    private MongoConnection()
    {
    }

    static
    {
        initDBPrompties();
    }

    public static MongoDatabase getDB(String dbName)
    {
        return mongo.getDatabase(dbName);
    }

    /**
     * 初始化连接池
     */
    private static void initDBPrompties()
    {
        // 其他参数根据实际情况进行添加
        try
        {
            mongo = new MongoClient(HOST, PORT);
        } catch (MongoException e)
        {
            LOGGER.error("", e);
        }
    }
    
    public static Pattern getLikePattern(String keywords)
    {
        return Pattern.compile("^.*"+keywords+".*$");
    }
}

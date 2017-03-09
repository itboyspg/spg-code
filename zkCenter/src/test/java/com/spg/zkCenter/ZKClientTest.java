package com.spg.zkCenter;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.spg.zkCenter.util.ZKConnector;

/**
 * 项目名称：zkCenter
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年11月27日 下午10:09:55
 * @version V1.0.0
 */
public class ZKClientTest
{
    // private static final Log LOGGER = LogFactory.getLog(ZKClientTest.class);
    private String connectionString = "192.168.140.129:2181";

    private int connectionTimeoutMs = 3 * 1000;

    private int sessionTimeoutMs = 3 * 1000;

    ZKConnector client = null;

    @Test
    @Ignore
    public void testCreateZKClient()
    {
        fail("Not yet implemented");
    }

    @Before
    public void setUp() throws Exception
    {
        client = new ZKConnector(connectionString, sessionTimeoutMs, connectionTimeoutMs);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testCreateNode()
    {
        try
        {
            // client.createNode(CreateMode.PERSISTENT, Ids.OPEN_ACL_UNSAFE,
            // "/spg/spg/spg/spg/spg/spg", "hello spg".getBytes());
            // client.deleteNode("/spg");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreatePersistentNodeString()
    {
        try
        {
            // client.createPersistentNode("/spg");
            // client.deleteNode("/spg");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreatePersistentNodeStringByteArray()
    {
        try
        {
            // client.createPersistentNode("/spg", "hello".getBytes());
            // client.deleteNode("/spg");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteNode() throws Exception
    {
        // client.deleteNode("/spg/spg/spg/spg");
    }

    @Test
    public void testGetNodeData()
    {
        try
        {
            // byte[] result = client.getNodeData("/spg/spg/spg");
            // System.out.println(new String(result));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // @Test
    // public void testSetDataAsyncWithCallback()
    // {
    // try
    // {
    // ZKClient.setDataAsyncWithCallback(client.createZKClient(), new
    // BackgroundCallback()
    // {
    //
    // @Override
    // public void processResult(CuratorFramework client, CuratorEvent event)
    // throws Exception
    // {
    // System.out.println(event);
    // }
    // }, "/home", "hello".getBytes());
    // } catch (Exception e)
    // {
    // e.printStackTrace();
    // }
    // }

    @Test
    public void testSetData2Node() throws Exception
    {
        // client.setData2Node("/spg/spg/spg/spg/spg/spg",
        // "hello word".getBytes());
    }

    @Test
    public void testDataChanges() throws Exception
    {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        // System.out.println(client.watchData("intf", "test", dataMap));
        Thread.sleep(10 * 60 * 1000);
    }
}

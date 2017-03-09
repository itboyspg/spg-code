
package com.spg.zkCenter;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 项目名称：zkCenter
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年11月27日 下午10:13:15
 * @version V1.0.0
 */
public class ZKClientTest1
{

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void test()
    {
        fail("Not yet implemented");
    }
    
    
    @Test
    public void testDataChanges() throws Exception
    {
//        ZKClient.dataChanges("/home");
        Thread.sleep(10000);
    }
}


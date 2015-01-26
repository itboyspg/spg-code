/**
 * 
 */
package com.spg.configcenter.pushserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * 项目名称：configcenter
 * 
 * @description:
 * 
 * @author spg
 * 
 * @create_time：2014年8月28日 上午11:42:59
 * 
 * @version V1.0.0
 * 
 */
public class PushServerStartTest
{

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void test() throws InterruptedException
    {
        LOGGER.entry(123);
        for (int i = 0; i < 10; i++)
        {
            LOGGER.debug("1");
            Thread.sleep(3000);
        }
    }

}

/**
 * 
 */
package com.spg.common.logutil;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 项目名称：spg-common
 *
 * @description:
 * @author Wind-spg
 * @create_time：2014年7月14日 上午11:17:38
 * @version V1.0.0
 */
public class MyLogUtil
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static void enterFunction(Object... objects)
    {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        // for (StackTraceElement element : elements)
        // {
        // System.out.println(element.getClassName());
        // System.out.println(element.getLineNumber());
        // System.out.println(element.getMethodName());
        // }
        StringBuilder sBuilder = new StringBuilder("\r\nenter function ");
        if (LOGGER.isDebugEnabled())
        {
            sBuilder.append(elements[2].getClassName());
            sBuilder.append("." + elements[2].getMethodName());
            sBuilder.append(" " + elements[2].getLineNumber());
            LOGGER.debug(sBuilder.append(Arrays.toString(objects)));
        }
    }
}
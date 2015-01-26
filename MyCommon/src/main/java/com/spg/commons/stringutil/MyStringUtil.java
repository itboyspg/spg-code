/**
 * 
 */
package com.spg.commons.stringutil;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.spg.commons.logutil.MyLogUtil;

/**
 * 项目名称：MyCommon
 * 
 * @description:
 * 
 * @author spg
 * 
 * @create_time：2014年7月14日 上午8:50:55
 * 
 * @version V1.0.0
 * 
 */
public final class MyStringUtil
{

    private static final Logger LOGGER = LogManager.getLogger();

    private MyStringUtil()
    {

    }

    /**
     * 
     * 
     * @param objects
     * @return
     */
    public static String buildParams2String(Object... objects)
    {
        LOGGER.debug("enter function, " + Arrays.toString(objects));
        MyLogUtil.enterFunction(Arrays.toString(objects));
        StringBuilder sBuilder = null;
        if (null == objects || objects.length == 0)
        {
            return "";
        } else
        {
            sBuilder = new StringBuilder();
            int i = 1;
            for (Object object : objects)
            {
                if (null != object)
                {
                    sBuilder.append(", parm");
                    sBuilder.append(i);
                    sBuilder.append(":");
                    sBuilder.append(object);
                    i++;
                }
            }
            return sBuilder.toString();
        }
    }
}

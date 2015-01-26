/**
 * 
 */
package com.spg.common.strutil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目名称：spg-common
 * 
 * @description: 字符串过滤工具类，用于过滤非法消息：从配置文件中读取敏感字符串，过滤请求字符串中敏感字符串
 * @author Wind-spg
 * @create_time：2014年10月31日 上午8:55:51
 * @version V1.0.0
 * 
 */
public final class FilterStringUtil
{
    private static final Log LOGGER = LogFactory.getLog(FilterStringUtil.class);

    private static StringBuilder sBuilder = new StringBuilder();;
    /**
     * 初始化加载所有敏感词汇
     */
    static
    {
        Properties properties = new Properties();

        Reader reader;
        try
        {
            reader = new InputStreamReader(FilterStringUtil.class.getClassLoader().getResourceAsStream(
                    "sensitive.properties"), "UTF-8");
            properties.load(reader);

            Iterator<Entry<Object, Object>> iterator = properties.entrySet().iterator();

            Entry<Object, Object> entry = null;
            while (iterator.hasNext())
            {
                entry = iterator.next();
                sBuilder.append(entry.getValue()).append("|");
            }
            if (sBuilder.toString().isEmpty() || sBuilder.toString().endsWith("|"))
            {
                sBuilder.append("sex");
            }
        } catch (IOException e)
        {
            LOGGER.error("init sensitive data error!", e);
            e.printStackTrace();
        }
    }

    /**
     * @description: 将原字符串中的非法字符替换成目标字符串<br>
     *               如：sourceStr=我的信息包含色情信息;
     *               targetStr=[非法字符串]，则结果为：我的信息包含[非法字符串]信息
     * @author: Wind-spg
     * @param sourceStr
     * @param targetStr
     * @return
     */
    public static String filterSensitiveMsg(String sourceStr, String targetStr)
    {
        String regex = sBuilder.toString();
        return sourceStr.replaceAll(regex, targetStr);
    }

    /**
     * @description: 将原字符串中的非法字符替换成目标字符串<br>
     *               如：sourceStr=我的信息包含色情信息;则结果为：我的信息包含***信息
     * @author: Wind-spg
     * @param sourceStr
     * @param targetStr
     * @return
     */
    public static String filterSensitiveMsg2Star(String sourceStr)
    {
        String regex = sBuilder.toString();
        return sourceStr.replaceAll(regex, "***");
    }

}

/**
 * 
 */
package com.spg.common.md5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目名称：spg-common
 * 
 * @description:
 * @author Wind-spg
 * @create_time：2014年10月17日 上午11:55:46
 * @version V1.0.0
 * 
 */
public final class MD5Util
{
    private static final Log LOGGER = LogFactory.getLog(MD5Util.class);

    private MD5Util()
    {

    }

    /**
     * 
     * @description: 字符串计算MD5，计算方式：遍历map，将所有value值拼接，如果joinStr不为空，则之间用joinStr链接，
     *               privateKey拼接到最后，如：map中为a-aa
     *               b-bc，jonStr为#，则结果为对aa#bc#privateKey进行md5的结果
     *               ;注意，map中key值为无须，所以放进去顺序可能跟拼接顺序不一致
     * @author: Wind-spg
     * @param privateKey
     * @param encryptKeys
     *            可为空
     * @param joinStr
     *            可为空
     * @return
     */
    public static String encrypt(String privateKey, Map<String, String> encryptKeys, String joinStr)
    {
        LOGGER.debug(String.format("privateKey:%s, encryptKeys:%s, joinStr:%s", privateKey, encryptKeys,
                joinStr));
        if (null != encryptKeys && !encryptKeys.isEmpty())
        {
            if (null == joinStr)
            {
                joinStr = "";
            }
            if (null == privateKey)
            {
                privateKey = "";
            }

            Entry<String, String> entry = null;
            StringBuilder sBuilder = new StringBuilder();
            for (Iterator<Entry<String, String>> iterator = encryptKeys.entrySet().iterator(); iterator
                    .hasNext();)
            {
                entry = iterator.next();
                sBuilder.append(entry.getValue());
                sBuilder.append(joinStr);
            }
            sBuilder.append(privateKey);
            LOGGER.debug(String.format("at function:%s", sBuilder.toString()));
            return DigestUtils.md5Hex(sBuilder.toString());
        } else
        {
            throw new IllegalArgumentException("encrytkeys is empty!");
        }
    }
    
    /**
     * 
     * @description: 字符串计算MD5，计算方式：遍历list，将所有值拼接，如果joinStr不为空，则之间用joinStr链接，
     *               privateKey拼接到最后，如：list中为a b 1 4 a jonStr为#，则结果为对a#b#1#4#a#privateKey进行md5的结果
     * @author: Wind-spg
     * @param privateKey
     * @param encryptKeys
     *            可为空
     * @param joinStr
     *            可为空
     * @return
     */
    public static String encrypt(String privateKey, List<String> encryptKeys, String joinStr)
    {
        LOGGER.debug(String.format("privateKey:%s, encryptKeys:%s, joinStr:%s", privateKey, encryptKeys,
                joinStr));
        if (null != encryptKeys && !encryptKeys.isEmpty())
        {
            if (null == joinStr)
            {
                joinStr = "";
            }
            if (null == privateKey)
            {
                privateKey = "";
            }
            
            StringBuilder sBuilder = new StringBuilder();
            for (String string : encryptKeys)
            {
                sBuilder.append(string);
                sBuilder.append(joinStr);
            }
            sBuilder.append(privateKey);
            LOGGER.debug(String.format("at function:%s", sBuilder.toString()));
            return DigestUtils.md5Hex(sBuilder.toString());
        } else
        {
            throw new IllegalArgumentException("encrytkeys is empty!");
        }
    }

    /**
     * @description: 
     *               字符串计算MD5，计算方式：遍历map，将所有value值key的字典顺序拼接，如果joinStr不为空，则之间用joinStr链接
     *               , privateKey拼接到最后，<br>
     *               如：map中为a-aa,c-dd,b-bc;jonStr为#，则结果为对aa#bc#dd#
     *               privateKey进行md5的结果 ;<br>
     * @author: Wind-spg
     * @param privateKey
     * @param encryptKeys
     * @param joinStr
     * @return
     */
    public static String encryptByDictionary(String privateKey, Map<String, String> encryptKeys,
            String joinStr)
    {
        LOGGER.debug(String.format("privateKey:%s, encryptKeys:%s, joinStr:%s", privateKey, encryptKeys,
                joinStr));
        if (null != encryptKeys && !encryptKeys.isEmpty())
        {
            if (null == joinStr)
            {
                joinStr = "";
            }
            if (null == privateKey)
            {
                privateKey = "";
            }

            StringBuilder sBuilder = new StringBuilder();
            // Set<String> keys = encryptKeys.keySet();
            List<String> keys = new ArrayList<>(encryptKeys.keySet());
            Collections.sort(keys);
            for (String str : keys)
            {
                sBuilder.append(encryptKeys.get(str));
                sBuilder.append(joinStr);
            }
            sBuilder.append(privateKey);

            LOGGER.debug(String.format("at function:%s", sBuilder.toString()));
            return DigestUtils.md5Hex(sBuilder.toString());
        } else
        {
            throw new IllegalArgumentException("encrytkeys is empty!");
        }
    }

    /**
     * @description: 生成32位MD5结果
     * @author: Wind-spg
     * @param sourceStr
     * @return
     */
    public static String encrypt32(String sourceStr)
    {
        return DigestUtils.md5Hex(sourceStr);
    }
    
    /**
     * @description: 生成16位MD5结果
     * @author: Wind-spg
     * @param sourceStr
     * @return
     */
    public static String encrypt16(String sourceStr)
    {
        return DigestUtils.md5Hex(sourceStr).substring(8, 24);
    }
    
}

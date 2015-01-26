/**
 * 
 */
package com.spg.common.common;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目名称：spg-common
 * 
 * @description:
 * @author Wind-spg
 * @create_time：2014年11月19日 下午3:12:00
 * @version V1.0.0
 * 
 */
public final class CommonUtil
{

    private static final Log LOGGER = LogFactory.getLog(CommonUtil.class);

    private CommonUtil()
    {

    }

    /**
     * @description: 获取请求方真实IP
     * @author: Wind-spg
     * @param request
     * @return
     */
    public static String getRealIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        LOGGER.debug(ip);
        return ip;
    }

    /**
     * @description: url短码生成
     * @author: Wind-spg
     * @param key
     * @return
     */
    public static String[] generateUrlShortCode(String key)
    {
        LOGGER.debug("enter function, " + key);
        // 要使用生成的字符
        String[] chars = new String[]
        { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5" };
        String[] resCodes = null;
        try
        {
            // MD5 加密
            String hex = DigestUtils.md5Hex("kc" + key + "gl").toLowerCase();
            int subHexLen = hex.length() / 8;
            resCodes = new String[subHexLen];
            for (int i = 0; i < subHexLen; i++)
            {
                // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
                String sTempSubString = hex.substring(i * 8, i * 8 + 8);

                // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 ,
                // 如果不用long ，则会越界
                long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
                String outChars = "";
                for (int j = 0; j < 6; j++)
                {
                    // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                    long index = 0x0000001F & lHexLong;
                    // 把取得的字符相加
                    outChars += chars[(int) index];
                    // 每次循环按位右移 5 位
                    lHexLong = lHexLong >> 5;
                }
                // 把字符串存入对应索引的输出数组
                resCodes[i] = outChars;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        LOGGER.debug("exit function, " + Arrays.toString(resCodes));
        return resCodes;
    }
    
}

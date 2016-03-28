package com.spg.common.strutil;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目名称：spg-common
 *
 * @description:
 * @author Wind-spg
 * @create_time：2016年1月10日 下午4:20:21
 * @version V1.0.0
 */
public class MyStringUtil
{
    private static final Log LOGGER = LogFactory.getLog(MyStringUtil.class);

    // 字母吧
    private static final char[] ALPHABET =
    { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    // 字母数字
    private static final char[] ALPHABET_AND_NUM =
    { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', '0' };

    /**
     * @description:获取指定长度的字符串
     * @author: Wind-spg
     * @param length
     *            目标字符串长度
     * @param containNum
     *            是否包含数字
     * @return
     */
    public static String randomString(int length, boolean containNum)
    {
        LOGGER.debug(String.format("enter function, %s, %s", length, containNum));
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < length; i++)
        {
            if (containNum)
            {
                sBuilder.append(ALPHABET_AND_NUM[RandomUtils.nextInt(0, ALPHABET.length)]);
            } else
            {
                sBuilder.append(ALPHABET[RandomUtils.nextInt(0, ALPHABET.length)]);
            }
        }
        return sBuilder.toString();
    }
}

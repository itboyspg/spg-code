/**
 * 
 */
package com.spg.common.md5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * 项目名称：spg-common
 *
 * @description:
 * @author Wind-spg
 * @create_time：2014年10月17日 下午2:59:52
 * @version V1.0.0
 *
 */
public class MD5UtilTest
{
    // private static final Log LOGGER = LogFactory.getLog(MD5UtilTest.class);

    /**
     * @description:
     * @author: Wind-spg
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    @Test
    public void testEncryptMap()
    {
        String privateKey = "asdf23$qs";
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "1221");
        map.put("b", "dd");
        map.put("s", "sdf");
        String joinKey = "#";

        String result = MD5Util.encrypt(privateKey, map, joinKey);
        Assert.assertEquals("5a496a0842c5b35ad78a02c3195a000e", result);
    }

    @Test
    public void testEncryptList()
    {
        String privateKey = "asdf23$qs";
        List<String> map = new ArrayList<>();
        map.add("1221");
        map.add("dd");
        map.add("sdf");
        String joinKey = "#";

        String result = MD5Util.encrypt(privateKey, map, joinKey);
        Assert.assertEquals("8f3998c2a1303dfbf52fdabc9cb521e2", result);
    }

    @Test
    public void testEncryptByDictionary()
    {
        String privateKey = "asdf23$qs";
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "1221");
        map.put("b", "dd");
        map.put("s", "sdf");
        map.put("1", "234#5#@");
        String joinKey = "#";
        String result = MD5Util.encryptByDictionary(privateKey, map, joinKey);
        Assert.assertEquals("dce3c590c13670c70bbd670d656087fc", result);
    }
}

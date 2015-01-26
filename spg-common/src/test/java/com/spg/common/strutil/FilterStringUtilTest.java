
/**
 * 
 */
package com.spg.common.strutil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * 项目名称：spg-common
 *
 * @description:
 * @author Wind-spg
 * @create_time：2014年10月31日 上午10:06:14
 * @version V1.0.0
 *
 */
public class FilterStringUtilTest
{
    private static final Log LOGGER = LogFactory.getLog(FilterStringUtilTest.class);

    @Test
    public void testFilterSensitiveMsg()
    {
        String result = FilterStringUtil.filterSensitiveMsg("我是色情信息", "==");
        System.out.println(result);
        Assert.assertEquals("我是==信息", result);
    }

    @Test
    public void testFilterSensitiveMsg2Star()
    {
        String result = FilterStringUtil.filterSensitiveMsg2Star("我是色情信息");
        System.out.println(result);
        Assert.assertEquals("我是***信息", result);
    }
}


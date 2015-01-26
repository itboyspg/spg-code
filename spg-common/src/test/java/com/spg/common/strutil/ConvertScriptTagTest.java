
/**
 * 
 */
package com.spg.common.strutil;

import org.junit.Test;

/**
 * 项目名称：spg-common
 *
 * @description:
 * @author Wind-spg
 * @create_time：2014年11月1日 下午4:00:06
 * @version V1.0.0
 *
 */
public class ConvertScriptTagTest
{
//    private static final Log LOGGER = LogFactory.getLog(ConvertScriptTagTest.class);

    /**
     * Test method for {@link com.eebbk.sccommon.strutil.ConvertScriptTag#convertScript(java.lang.String)}.
     */
    @Test
    public void testConvertScript()
    {
        String sourceStr = "<html><head><script type=\"text/javascript\" src=\"loadxmldoc.js\"></script><script type=\"text/javascript\">alert(1);</script></head><body><script type=\"text/javascript\">xmlDoc=loadXMLDoc(\"books.xml\");document.write(\"xmlDoc is loaded, ready for use\");</script><h1>sadfafs</h1></body></html>";
        String result = ConvertScriptTag.convertScript(sourceStr);
        System.out.println(result);
    }
}


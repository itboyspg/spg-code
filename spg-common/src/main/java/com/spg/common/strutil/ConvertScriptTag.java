/**
 * 
 */
package com.spg.common.strutil;


/**
 * 项目名称：spg-common
 * 
 * @description: 将脚本中的标签转换成其对应的转义符<br>
 *  ============================<br>
 *  |  转义结果  |  特殊符号  |   说明       |<br>
 *  ============================<br>
 *  |  &amp; |    &   |    和        |<br>
 *  ============================<br>
 *  |  &lt;  |    <   |   小于       |<br>
 *  ============================<br>
 *  |  &gt;  |    >   |   大于       |<br>
 *  ============================<br>
 *  | &quot; |    "   |  双引号      |<br>
 *  ============================<br>
 *  | &apos; |    '   |  单引号      |<br>
 *  ============================<br>
 *  | &nbsp; |        |   空格       |<br>
 *  ============================<br>
 *  | &copy; |    ©   |  版权符      |<br>
 *  ============================<br>
 *  | &reg;  |    ®   |  注册符      |<br>
 *  ============================<br>
 * 
 * @author Wind-spg
 * @create_time：2014年11月1日 下午3:20:36
 * @version V1.0.0
 * 
 */
public final class ConvertScriptTag
{
    
//    private static final Log LOGGER = LogFactory.getLog(ConvertScriptTag.class);
    
    private ConvertScriptTag()
    {
        
    }
    
    /**
     * 
     * @description: 将原字符串中的特殊字符替换成转义符
     * @author: Wind-spg
     * @param sourceStr
     * @return
     */
    public static String convertScript(String sourceStr)
    {
        if (null == sourceStr || sourceStr.isEmpty())
        {
            return sourceStr;
        } else
        {
            sourceStr = sourceStr.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;").replaceAll("'", "&apos;").replaceAll(" ", "&nbsp;")
                    .replaceAll("©", "&copy;").replaceAll("®", "&reg;");
        }
        return sourceStr;
    }
    
}

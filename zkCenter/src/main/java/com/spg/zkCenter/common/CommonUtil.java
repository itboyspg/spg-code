package com.spg.zkCenter.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目名称：zkCenter
 *
 * @description:
 * @author Wind-spg
 * @create_time：2017年2月16日 下午10:45:14
 * @version V1.0.0
 */
public final class CommonUtil
{
//    private static final Log LOGGER = LogFactory.getLog(CommonUtil.class);

    public CommonUtil()
    {
    }

    public static String date2String(String pattern, Date date)
    {
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}


/**
 * 
 */
package com.spg.commons.datetimeutil;

import java.sql.Date;
import java.text.SimpleDateFormat;

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
 * @create_time：2014年7月14日 上午8:38:39
 *
 * @version V1.0.0
 *
 */
public final class MyDateTimeUtil
{
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	public static String getDateTime(String pattern, Date date)
	{
		LOGGER.debug("enter function", pattern, date);
		if (null == pattern || "".equals(pattern))
		{
			LOGGER.error("pattern is null");
			throw new IllegalArgumentException("pattern");
		}
		if (null == date)
		{
			throw new IllegalArgumentException("date");
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
}


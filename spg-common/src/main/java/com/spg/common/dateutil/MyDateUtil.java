/**
 * 
 */
package com.spg.common.dateutil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目名称：spg-common
 * 
 * @description: 时间日期格式化工具类
 * @author Wind-spg
 * @create_time：2014年11月27日 上午10:00:26
 * @version V1.0.0
 */
public final class MyDateUtil
{

    private static final Log LOGGER = LogFactory.getLog(MyDateUtil.class);

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    private static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    public MyDateUtil()
    {

    }

    /**
     * @description: 根据指定格式获取格式化日期
     * @author: Wind-spg
     * @param date
     * @param pattern
     * @return pattern形式字符串日期
     */
    public static String getFormatDate(Date date, String pattern)
    {
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * @description: 根据默认格式获取格式化日期，默认格式：yyyy-MM-dd
     * @author: Wind-spg
     * @param date
     * @return 2014-11-28
     */
    public static String getDefaultFormatDate(Date date)
    {
        DateFormat format = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return format.format(date);
    }

    /**
     * @description:根据默认格式获取格式化日期，默认格式：yyyy-MM-dd HH:mm:ss
     * @author: Wind-spg
     * @param date
     * @return 2014-11-28 12:12:12
     */
    public static String getDefaultFormatDateTime(Date date)
    {
        DateFormat format = new SimpleDateFormat(DEFAULT_DATE_TIME_PATTERN);
        return format.format(date);
    }

    /**
     * @description: 根据默认格式获取格式化时间，默认格式：HH:mm:ss
     * @author: Wind-spg
     * @param date
     * @return 12:12:12
     */
    public static String getDefaultFormatTime(Date date)
    {
        DateFormat format = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
        return format.format(date);
    }

    /**
     * @description: 按指定格式获取今天时间日期
     * @author: Wind-spg
     * @param pattern
     * @return
     */
    public static String getTodayDate(String pattern)
    {
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date());
    }

    /**
     * 
     * @description: 获取相对于当前时间的其他时间
     * 
     *               Examples: <blockquote>
     * 
     *               <pre>
     * 如果当前时间为2014-12-20
     * MyDateUtil.getOtherDaysDate("yyyy-MM-dd", 2) returns "2014-12-22"
     * MyDateUtil.getOtherDaysDate("yyyy-MM-dd", -2) returns "2014-12-18"
     * </pre>
     * 
     *               </blockquote>
     * 
     * @author: Wind-spg
     * @param pattern
     * @param days
     *            相对于当前日期的前后天数，往前为正数，往后为负数
     * @return
     */
    public static String getOtherDaysDate(String pattern, int days)
    {
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(System.currentTimeMillis() + (days * 24L * 60L * 60L * 1000L)));
    }

    /**
     * @description: 将字符串日期转化为java.sql.Timestamp
     * @author: Wind-spg
     * @param sourceDateTime
     * @param pattern
     * @return
     */
    public static Timestamp parseStringDateTime(String sourceDateTime, String pattern)
    {
        DateFormat format = new SimpleDateFormat(pattern);
        try
        {
            Date date = format.parse(sourceDateTime);
            return new Timestamp(date.getTime());
        } catch (ParseException e)
        {
            LOGGER.error("pase date error!", e);
        }
        return null;
    }

    /**
     * @description: 根据给定的时间格式，获取此时间格式中某月份中总共有多少天。如：2014-02-22，则返回28；
     * @author: Wind-spg
     * @param source 原时间
     * @param dateFormat 时间格式，如yyyy-MM
     * @return 某月中有多少天
     * @throws ParseException
     */
    public static int getDaysOfMouth(String source, String dateFormat) throws ParseException
    {
        int count = 0;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try
        {
            Date date = format.parse(source);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            count = calendar.getActualMaximum(Calendar.DATE);
        } catch (ParseException e)
        {
            LOGGER.error("parse error!", e);
            throw e;
        }
        return count;
    }
}

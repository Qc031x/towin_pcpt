package com.sgfm.base.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * 
 * 功能描述：完成与日期相关的各种操作
 *
 * <p>包括将日期格式化、从字符串中解析出对应的日期、对日期的加减操作等
 * @author maluming 2011-4-14
 * @see
 * @since 1.0
 */
public class DateUtil
{
    /**
     * 功能描述：按照给出格式解析出日期
     * 
     * @param dateStr String 字符型日期
     * @param format String 格式
     * @return Date 日期
     */
    public static Date parseDate(String dateStr, String format)
    {
    	Date date = null;
        try
        {
        	DateFormat df_parseDate = new SimpleDateFormat(format);
            String dt = dateStr;
            date = (Date) df_parseDate.parse(dt);
        }
        catch (Exception e)
        {
        }
        return date;
    }

    /**
     * 功能描述：格式化日期
     * 
     * @param dateStr String 字符型日期：YYYY-MM-DD 格式
     * @return Date
     */
    public static Date parseDate(String dateStr)
    {
        return parseDate(dateStr, "yyyy-MM-dd");
    }

    /**
     * 功能描述：格式化输出日期
     * 
     * @param date Date 日期
     * @param format String 格式
     * @return 字符型日期
     */
    public static String format(Date date, String format)
    {
        String result = "";
        try
        {
            if (date != null)
            {
            	DateFormat df_format = new SimpleDateFormat(format);
                result = df_format.format(date);
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return result;
    }

   
    /**
     * 功能描述：返回字符型日期
     * 
     * @param date 日期
     * @return 返回字符型日期 yyyy/MM/dd 格式
     */
    public static String getDate(Date date)
    {
        return format(date, "yyyy/MM/dd");
    }

    /**
     * 功能描述：返回字符型时间
     * 
     * @param date Date 日期
     * @return 返回字符型时间 HH:mm:ss 格式
     */
    public static String getTime(Date date)
    {
        return format(date, "HH:mm:ss");
    }

    /**
     * 功能描述：返回字符型日期时间
     * 
     * @param date Date 日期
     * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
     */
    public static String getDateTime(Date date)
    {
        return format(date, "yyyy/MM/dd HH:mm:ss");
    }
    
    public static String getMillisDateTime(Date date)
    {
        return format(date, "yyyy/MM/dd HH:mm:ss.SSS");
    }


    /**
     * 功能描述：取得指定月份的第一天
     * 
     * @param strdate String 字符型日期
     * @return String yyyy-MM-dd 格式
     */
    public static String getMonthBegin(String strdate)
    {
        Date date = parseDate(strdate);
        return format(date, "yyyy-MM") + "-01";
    }


    /**
     * 功能描述：常用的格式化日期
     * 
     * @param date Date 日期
     * @return String 日期字符串 yyyy-MM-dd格式
     */
    public static String formatDate(Date date)
    {
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    /**
     * 功能描述：以指定的格式来格式化日期
     * 
     * @param date Date 日期
     * @param format String 格式
     * @return String 日期字符串
     */
    public static String formatDateByFormat(Date date, String format)
    {
        String result = "";
        if (date != null)
        {
            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            }
            catch (Exception ex)
            {
            }
        }
        return result;
    }

    /**
     * 计算2个日期之间的相隔天数
     * @param d1  日期1
     * @param d2  日期2
     * @return 日期1和日期2相隔天数
     */
    public int getDaysBetween(Calendar d1, Calendar d2)
    {
        if (d1.after(d2))
        { 
            // swap dates so that d1 is start and d2 is end
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR)
                - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2)
        {
            d1 = (Calendar) d1.clone();
            do
            {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            }
            while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 计算2个日期之间的工作天数（去除周六周日）
     * @param d1  日期1
     * @param d2  日期2
     * @return 日期1和日期2之间的工作天数
     */
    public int getWorkingDay(Calendar d1, Calendar d2)
    {
        int result = -1;
        if (d1.after(d2))
        { 
            // swap dates so that d1 is start and d2 is end
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }

        //int betweendays = getDaysBetween(d1, d2);

        //int charge_date = 0;
        
        // 开始日期的日期偏移量
        int charge_start_date = 0;
        // 结束日期的日期偏移量
        int charge_end_date = 0;
        
        int stmp;
        int etmp;
        stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
        etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);
        
        // 日期不在同一个日期内        
        if (stmp != 0 && stmp != 6)
        {// 开始日期为星期六和星期日时偏移量为0
            charge_start_date = stmp - 1;
        }
        if (etmp != 0 && etmp != 6)
        {// 结束日期为星期六和星期日时偏移量为0
            charge_end_date = etmp - 1;
        }
        // }
        result = (getDaysBetween(this.getNextMonday(d1), this.getNextMonday(d2)) / 7)
                * 5 + charge_start_date - charge_end_date;
        // System.out.println("charge_start_date>" + charge_start_date);
        // System.out.println("charge_end_date>" + charge_end_date);
        // System.out.println("between day is-->" + betweendays);
        return result;
    }

    /**
     * 获取当前星期
     * @param date  当前日期
     * @param character  zh : 标识中文 ， en : 标识英文（默认）
     * @return 当前日期
     */
    public String getChineseWeek(Calendar date , String character)
    {
        String dayNames[] =
        { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

        if("zh".equals(character))
        {
        	dayNames[0] = "星期日";
        	dayNames[1] = "星期一";
        	dayNames[2] = "星期二";
        	dayNames[3] = "星期三";
        	dayNames[4] = "星期四";
        	dayNames[5] = "星期五";
        	dayNames[6] = "星期六";
        }
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);

        // System.out.println(dayNames[dayOfWeek - 1]);
        return dayNames[dayOfWeek - 1];

    }

    /**
     * 获得日期的下一个星期一的日期
     * 
     * @param date
     * @return
     */
    public Calendar getNextMonday(Calendar date)
    {
        Calendar result = null;
        result = date;
        do
        {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        }
        while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }

    /**
     * 计算两个日期之间的非工作日天数
     * @param d1 日期1
     * @param d2 日期2
     * @return 日期1与日期2之间的非工作天数
     */
    public int getHolidays(Calendar d1, Calendar d2)
    {
        return this.getDaysBetween(d1, d2) - this.getWorkingDay(d1, d2);

    }

    public static void main(String[] args)
    {
        DateUtil dt = new DateUtil();
        // System.out.println(d.toString());
        // System.out.println(formatDate(d).toString());
        // System.out.println(getMonthBegin(formatDate(d).toString()));
        // System.out.println(getMonthBegin("2008/07/19"));
        // System.out.println(getMonthEnd("2008/07/19"));
        
        System.out.println(DateUtil.getDateTime(null));
    }

}

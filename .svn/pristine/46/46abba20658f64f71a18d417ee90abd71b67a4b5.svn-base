package com.sgfm.datacenter.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.sgfm.datacenter.exception.AppException;
/**
 * 时区转换工具类。
 * @author ljl
 *
 */
public class TimeZoneUtils {
	
	private static final String YYYY_MM_DD_HH_MM_SS2 = "yyyy/MM/dd HH:mm:ss";
	private static final String YYYY_MM_DD_HH_MM2 = "yyyy/MM/dd HH:mm";
	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	private static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	
	/**数据中心默认时区*/
	private static final String DATA_CETNER_TIME_ZONE="GMT+8";

	public static void main(String[] args) {
//		String inputDate = "2011-03-13 14:59:59";
//	  	System.out.println(convertTimeZone("2011-03-13 14:59:59","GMT+8","GMT-5"));
//	  	System.out.println(convertTimeZone("2011-03-13 15:00:00","GMT+8","GMT-5"));
//	  	System.out.println();
//	  	System.out.println(convertTimeZone("2011-03-13 14:59:59","Asia/Shanghai","America/New_York"));
//	  	System.out.println(convertTimeZone("2011-03-13 15:00:00","Asia/Shanghai","America/New_York"));
	  	//System.out.println(otherTimeZone2UCT("2011-11-03 09:38"));
		
		getGmt8Date("8");
	  	

	}
	
	/**
	 * 将其它时区时间转换成标准时间。暂时不处理夏令时问题。
	 * @param sourceDate
	 * @return
	 */
	public static String otherTimeZone2UCT(String sourceDate){
		String sourceTimeZone=DATA_CETNER_TIME_ZONE;
		String dstTimeZone="UCT";
		return convertTimeZone(sourceDate, sourceTimeZone, dstTimeZone, null);
	}
	
	/**
	 * 将标准时间转换成其它时区时间。暂时不处理夏令时问题。<br>
	 * @param sourceDate
	 * @param 目标日期格式
	 * @return
	 */
	public static String uct2OtherTimeZone(String sourceDate){
		String sourceTimeZone="UCT";
		String dstTimeZone=DATA_CETNER_TIME_ZONE;
		return convertTimeZone(sourceDate, sourceTimeZone, dstTimeZone, null);
	}
	
	/**
	 * 将标准时间转换成其它时区时间。暂时不处理夏令时问题。
	 * @param sourceDate
	 * @param 目标日期格式
	 * @return
	 */
	public static String uct2OtherTimeZone(Date sourceDate,String style){
		String sourceTimeZone="UCT";
		String dstTimeZone=DATA_CETNER_TIME_ZONE;
		return convertTimeZone(sourceDate, sourceTimeZone, dstTimeZone, style);
	}

	/**
	 * 将美国东部时间转换成标准时间。暂时不处理夏令时问题。
	 * @param sourceDate
	 * @return
	 */
	public static String usaEastern2UCT(String sourceDate){
		String sourceTimeZone="GMT-5";
		String dstTimeZone="UCT";
		return convertTimeZone(sourceDate, sourceTimeZone, dstTimeZone, null);
	}
	
	/**
	 * 将标准时间转换成美国东部时间。暂时不处理夏令时问题。
	 * @param sourceDate
	 * @return
	 */
	public static String uct2UsaEastern(String sourceDate){
		String sourceTimeZone="UCT";
		String dstTimeZone="GMT-5";
		return convertTimeZone(sourceDate, sourceTimeZone, dstTimeZone, null);
	}

	/**
	 * 将标准时间转换成美国东部时间。暂时不处理夏令时问题。
	 * @param sourceDate
	 * @param 目标日期格式
	 * @return
	 */
	public static String uct2UsaEastern(Date sourceDate,String style){
		String sourceTimeZone="UCT";
		String dstTimeZone="GMT-5";
		return convertTimeZone(sourceDate, sourceTimeZone, dstTimeZone, style);
	}
	
	
	/**
	 * 将指定时区的日期转换成目标时区的日期并返回。
	 * @param sourceDate	  待转换的日期
	 * @param sourceTimeZone 待转换的日期时区
	 * @param dstTimeZone	目标时区
	 * @param dstStyle 输出目标日期格式
	 * @return 
	 */
	public static String convertTimeZone(String sourceDate, String sourceTimeZone, String dstTimeZone, String dstStyle) {
		if(sourceDate==null || sourceDate.trim().length()==0) return sourceDate;
		
    	TimeZone timeZoneSH = TimeZone.getTimeZone(sourceTimeZone);
    	TimeZone timeZoneNY = TimeZone.getTimeZone(dstTimeZone);
    	
    	SimpleDateFormat inputFormat = null;
    	inputFormat = getDateStyle(sourceDate);
    	inputFormat.setTimeZone(timeZoneSH);
    	Date date = null;
    	try{
    	    date = inputFormat.parse(sourceDate);
    	}catch (ParseException e){ 	}
    	         
    	SimpleDateFormat outputFormat = null;
    	if(dstStyle!=null){
    		outputFormat = new SimpleDateFormat(dstStyle);
    	} else{
			outputFormat = getDateStyle(sourceDate);
    	}
    	outputFormat.setTimeZone(timeZoneNY);
    	return outputFormat.format(date);
	}

	/**
	 * 返回输入日期的日期格式.
	 * @param sourceDate
	 * @return
	 */
	private static SimpleDateFormat getDateStyle(String sourceDate) {
		SimpleDateFormat inputFormat;
		if(sourceDate.trim().length()==16){
			if(sourceDate.indexOf("-")>0){
				inputFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM);
			}else if(sourceDate.indexOf("/")>0){
				inputFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM2);
			}else{
				throw AppException.createErrorException("日期格式不支持.");
			}
    	}else{
			if(sourceDate.indexOf("-")>0){
				inputFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
			}else if(sourceDate.indexOf("/")>0){
				inputFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS2);
			}else{
				throw AppException.createErrorException("日期格式不支持.");
			}
    	}
		return inputFormat;
	}
	
	/**
	 * 将指定时区的日期转换成目标时区的日期并返回。
	 * @param sourceDate	  待转换的日期
	 * @param sourceTimeZone 待转换的日期时区
	 * @param dstTimeZone	目标时区
	 * @return 
	 */
	public static String convertTimeZone(Date sourceDate, String sourceTimeZone, String dstTimeZone) {
		SimpleDateFormat outputFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		String date = outputFormat.format(sourceDate);
		return convertTimeZone(date, sourceTimeZone, dstTimeZone, null);
	}

	/**
	 * 将指定时区的日期转换成目标时区的日期并返回。
	 * @param sourceDate	  待转换的日期
	 * @param sourceTimeZone 待转换的日期时区
	 * @param dstTimeZone	目标时区
	 * @return 
	 */
	public static String convertTimeZone(Date sourceDate, String sourceTimeZone, String dstTimeZone, String dstStyle) {
		SimpleDateFormat outputFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		String date = outputFormat.format(sourceDate);
		return convertTimeZone(date, sourceTimeZone, dstTimeZone, dstStyle);
	}
	/**
	 * 返回当前时间（今天12天到明天12天的数组  取北京时间）
	 *  @param sourceDate	  显示的时区 8  表示北京时间
	 */
	public static String[] getGmt8Date(String sourceDate ) {
		String strDate=null;
		String strArr[]=new String[2];
		Calendar cal=Calendar.getInstance();
		Calendar calServerDate=Calendar.getInstance();
		if(sourceDate.equals("8")){
			
			try {
				SimpleDateFormat   inputFormat=new   java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				SimpleDateFormat   inputFormat1=new  java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				Date   date=new   java.util.Date(); 
				
				TimeZone timeZoneNY = TimeZone.getTimeZone("GMT+8");
				inputFormat.setTimeZone(timeZoneNY);
				//把服务器当前时间转换为GMT+8时间
				strDate = inputFormat.format(date);
				//得到当前北京时间的 小时数
			    int	hour=inputFormat.getCalendar().get(Calendar.HOUR_OF_DAY);	
			    //如果当前时间在12:00-23:59之间，则页面显示时间为 当天12点到明天12点
			    //System.out.println( "当前系统时间为： "+hour);
			    if((hour>=12)&&(hour<=23)){
			    	strArr[0]=strDate.split(" ")[0]+" 12:00:00";
					Date date1=inputFormat1.parse( strArr[0]);
					cal.setTime(date1);
					cal.add(cal.DAY_OF_YEAR, 1);
					strArr[1]=inputFormat1.format(cal.getTime());
			    }
			    else if((hour>=0)&&(hour<=11)){
			    	strArr[1]=strDate.split(" ")[0]+" 12:00:00";
					Date date1=inputFormat1.parse( strArr[1]);
					cal.setTime(date1);
					cal.add(cal.DAY_OF_YEAR, -1);
					strArr[0]=inputFormat1.format(cal.getTime());
			    }
				
			   // System.out.println( "开始时间:"+strArr[0]+"  结束时间:"+strArr[1]);
				
			/*	strArr[0]=strDate.split(" ")[0]+" 12:00:00";
				Date date1=inputFormat1.parse( strArr[0]);
				cal.setTime(date1);
				cal.add(cal.DAY_OF_YEAR, 1);
				strArr[1]=inputFormat1.format(cal.getTime());*/

				
				//System.out.println( "当前系统时间为： "+strDate.split(" ")[0]+" 12:00:00"+"\n"+strArr[1]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
				
		}
		return strArr;
	}
	
	/**
	 * 判断两个字符串型的时间是否相等，只精确到分。
	 * @param newDate
	 * @param oldDate
	 * @return
	 */
	public static boolean isSameDate(String newDate,String oldDate){
		newDate = newDate.trim().substring(0, 16);
		oldDate = oldDate.trim().substring(0, 16);
		return newDate.equals(oldDate);
	}
	
	/*public static String[] getGmt8Date(String sourceDate ) {
		String strDate=null;
		String strArr[]=new String[2];
		Calendar cal=Calendar.getInstance();
		if(sourceDate.equals("8")){
			
			try {
				SimpleDateFormat   inputFormat=new   java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				SimpleDateFormat   inputFormat1=new  java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				Date   date=new   java.util.Date(); 
				TimeZone timeZoneNY = TimeZone.getTimeZone("GMT+8");
				inputFormat.setTimeZone(timeZoneNY);
				strDate = inputFormat.format(date);	
				strArr[0]=strDate.split(" ")[0]+" 12:00:00";
				Date date1=inputFormat1.parse( strArr[0]);
				cal.setTime(date1);
				cal.add(cal.DAY_OF_YEAR, 1);
				strArr[1]=inputFormat1.format(cal.getTime());
				//strArr[1]=cal.getTime().toLocaleString();
				
				System.out.println( "当前系统时间为： "+strDate.split(" ")[0]+" 12:00:00"+"\n"+strArr[1]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
				
		}
		return strArr;
	}*/

}

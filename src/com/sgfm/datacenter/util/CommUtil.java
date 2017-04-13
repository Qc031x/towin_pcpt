package com.sgfm.datacenter.util;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;



public class CommUtil {
	
	//private static Logger logger = Logger.getLogger(SystemMgr.class.getName());

	
  public void setClobToString(List<HashMap<String,Object>> list,String clm){
	  
	 	for(int i=0;i<list.size();i++){
	       	if(list.get(i).get(clm) != null){
	       	 Map map1=	list.get(i);
	         Clob clob=(Clob)map1.get(clm);
	         //clob 转 String 
	         try {
	       		String clobString = clob.getSubString(1, (int) clob.length());
	       		map1.put(clm, clobString);
	       	} catch (SQLException e) {
	       	
	       		e.printStackTrace();
	       	}
	       	}
	    }
  }
  public void setClobToString2(List<Map> list,String clm){
	  
	 	for(int i=0;i<list.size();i++){
	       	if(list.get(i).get(clm) != null){
	       	 Map map1=	list.get(i);
	         Clob clob=(Clob)map1.get(clm);
	         //clob 转 String 
	         try {
	       		String clobString = clob.getSubString(1, (int) clob.length());
	       		map1.put(clm, clobString);
	       	} catch (SQLException e) {
	       	
	       		e.printStackTrace();
	       	}
	       	}
	    }
}
 
  public void setClobToStringMap(Map map,String clm){
	  Clob clob=(Clob)map.get(clm);
	  try {
		  	if(map.get(clm) != null){
		  		String clobString = clob.getSubString(1, (int) clob.length());
				map.put(clm, clobString);
		  	}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
  }
  
  public static String Html2Text(String inputString) {      
      String htmlStr = inputString; // 含html标签的字符串      
      String textStr = "";      
      java.util.regex.Pattern p_script;      
      java.util.regex.Matcher m_script;      
      java.util.regex.Pattern p_style;      
      java.util.regex.Matcher m_style;      
      java.util.regex.Pattern p_html;      
      java.util.regex.Matcher m_html;      
  
      java.util.regex.Pattern p_html1;      
      java.util.regex.Matcher m_html1;      
  
     try {      
          String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>      
          String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>      
          String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式      
          String regEx_html1 = "<[^>]+";      
          p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);      
          m_script = p_script.matcher(htmlStr);      
          htmlStr = m_script.replaceAll(""); // 过滤script标签      
  
          p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);      
          m_style = p_style.matcher(htmlStr);      
          htmlStr = m_style.replaceAll(""); // 过滤style标签      
  
          p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);      
          m_html = p_html.matcher(htmlStr);      
          htmlStr = m_html.replaceAll(""); // 过滤html标签      
  
          p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);      
          m_html1 = p_html1.matcher(htmlStr);      
          htmlStr = m_html1.replaceAll(""); // 过滤html标签      
  
          textStr = htmlStr;      
  
          textStr = textStr.substring(0, 70);
      } catch (Exception e) {      
          System.err.println("Html2Text: " + e.getMessage());      
      }      
      
      
     return textStr;// 返回文本字符串      
  }     
  
  /**
   * 根据参数获取一个月份的所有日期
   * @param date
   * @return
   * 康良玉
   */
  	public static List<Date> getAllTheDateOftheMonths(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);

		int month = cal.get(Calendar.MONTH);
		while(cal.get(Calendar.MONTH) == month){
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}
	
  /**
   * 获取下个月1号的日期
   * @return
   * 康良玉
   */
	public static Date nextMonthFirstDate1() {
	  Calendar calendar = Calendar.getInstance();
	  calendar.set(Calendar.DAY_OF_MONTH, 1);
	  calendar.add(Calendar.MONTH, 1);
	  return calendar.getTime();
  }
	
	public static Date nextMonthFirstDate2() {
		  Calendar calendar = Calendar.getInstance();
		  calendar.set(Calendar.DAY_OF_MONTH, 1);
		  calendar.add(Calendar.MONTH, 2);
		  return calendar.getTime();
	  }
	
	public static Date nextMonthFirstDate3() {
		  Calendar calendar = Calendar.getInstance();
		  calendar.set(Calendar.DAY_OF_MONTH, 1);
		  calendar.add(Calendar.MONTH, 3);
		  return calendar.getTime();
	  }

	public static Date nextMonthFirstDate4() {
		  Calendar calendar = Calendar.getInstance();
		  calendar.set(Calendar.DAY_OF_MONTH, 1);
		  calendar.add(Calendar.MONTH, 4);
		  return calendar.getTime();
	  }
	
	public static Date nextMonthFirstDate5() {
		  Calendar calendar = Calendar.getInstance();
		  calendar.set(Calendar.DAY_OF_MONTH, 1);
		  calendar.add(Calendar.MONTH, 5);
		  return calendar.getTime();
	  }
	
	public static Date nextMonthFirstDate6() {
		  Calendar calendar = Calendar.getInstance();
		  calendar.set(Calendar.DAY_OF_MONTH, 1);
		  calendar.add(Calendar.MONTH, 6);
		  return calendar.getTime();
	  }
	
	public static Date nextMonthFirstDate7() {
		  Calendar calendar = Calendar.getInstance();
		  calendar.set(Calendar.DAY_OF_MONTH, 1);
		  calendar.add(Calendar.MONTH, 7);
		  return calendar.getTime();
	  }
	
	public static Date nextMonthFirstDate8() {
		  Calendar calendar = Calendar.getInstance();
		  calendar.set(Calendar.DAY_OF_MONTH, 1);
		  calendar.add(Calendar.MONTH, 8);
		  return calendar.getTime();
	  }
  
	public static Date nextMonthFirstDate9() {
		  Calendar calendar = Calendar.getInstance();
		  calendar.set(Calendar.DAY_OF_MONTH, 1);
		  calendar.add(Calendar.MONTH, 9);
		  return calendar.getTime();
	  }
	
	public static Date nextMonthFirstDate10() {
		  Calendar calendar = Calendar.getInstance();
		  calendar.set(Calendar.DAY_OF_MONTH, 1);
		  calendar.add(Calendar.MONTH, 10);
		  return calendar.getTime();
	  }
	
	public static Date nextMonthFirstDate11() {
		  Calendar calendar = Calendar.getInstance();
		  calendar.set(Calendar.DAY_OF_MONTH, 1);
		  calendar.add(Calendar.MONTH, 11);
		  return calendar.getTime();
	  }
	
	public static List<Map> removeDuplicateWithOrder(List<Map> list) {
        Set set = new HashSet();
        List<Map> newList = new ArrayList<Map>();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add((Map) element);
        }
        return newList;
    }
	
	
    
    public static double toFixNumber(double coin){
    	BigDecimal bigDecimal = new BigDecimal(coin);
    	return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 汉字转换位汉语拼音首字母，英文字符不变，特殊字符丢失 支持多音字，生成方式如（长沙市长:cssc,zssz,zssc,cssz）
     * 
     * @param chines
     *            汉字
     * @return 拼音
     */ 
    public static String converterToFirstSpell(String chines) { 
        StringBuffer pinyinName = new StringBuffer(); 
        char[] nameChar = chines.toCharArray(); 
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); 
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
        for (int i = 0; i < nameChar.length; i++) { 
            if (nameChar[i] > 128) { 
                try { 
                    // 取得当前汉字的所有全拼 
                    String[] strs = PinyinHelper.toHanyuPinyinStringArray( 
                            nameChar[i], defaultFormat); 
                    if (strs != null) { 
                        for (int j = 0; j < 1; j++) { 
                            // 取首字母 
                            pinyinName.append(strs[j].charAt(0)); 
                            if (j != strs.length - 1) { 
                                pinyinName.append(","); 
                            } 
                        } 
                    } 
                    // else { 
                    // pinyinName.append(nameChar[i]); 
                    // } 
                } catch (BadHanyuPinyinOutputFormatCombination e) { 
                    e.printStackTrace(); 
                } 
            } else { 
                pinyinName.append(nameChar[i]); 
            } 
            pinyinName.append(" "); 
        } 
        // return pinyinName.toString(); 
        return parseTheChineseByObject(discountTheChinese(pinyinName.toString())); 
    } 
    
    /**
     * 去除多音字重复数据
     * 
     * @param theStr
     * @return
     */ 
    private static List<Map<String, Integer>> discountTheChinese(String theStr) { 
        // 去除重复拼音后的拼音列表 
        List<Map<String, Integer>> mapList = new ArrayList<Map<String, Integer>>(); 
        // 用于处理每个字的多音字，去掉重复 
        Map<String, Integer> onlyOne = null; 
        String[] firsts = theStr.split(" "); 
        // 读出每个汉字的拼音 
        for (String str : firsts) { 
            onlyOne = new Hashtable<String, Integer>(); 
            String[] china = str.split(","); 
            // 多音字处理 
            for (String s : china) { 
                Integer count = onlyOne.get(s); 
                if (count == null) { 
                    onlyOne.put(s, new Integer(1)); 
                } else { 
                    onlyOne.remove(s); 
                    count++; 
                    onlyOne.put(s, count); 
                } 
            } 
            mapList.add(onlyOne); 
        } 
        return mapList; 
    } 
    
    
    /**
     * 解析并组合拼音，对象合并方案(推荐使用)
     * 
     * @return
     */ 
    private static String parseTheChineseByObject( 
            List<Map<String, Integer>> list) { 
        Map<String, Integer> first = null; // 用于统计每一次,集合组合数据 
        // 遍历每一组集合 
        for (int i = 0; i < list.size(); i++) { 
            // 每一组集合与上一次组合的Map 
            Map<String, Integer> temp = new Hashtable<String, Integer>(); 
            // 第一次循环，first为空 
            if (first != null) { 
                // 取出上次组合与此次集合的字符，并保存 
                for (String s : first.keySet()) { 
                    for (String s1 : list.get(i).keySet()) { 
                        String str = s + s1; 
                        temp.put(str, 1); 
                    } 
                } 
                // 清理上一次组合数据 
                if (temp != null && temp.size() > 0) { 
                    first.clear(); 
                } 
            } else { 
                for (String s : list.get(i).keySet()) { 
                    String str = s; 
                    temp.put(str, 1); 
                } 
            } 
            // 保存组合数据以便下次循环使用 
            if (temp != null && temp.size() > 0) { 
                first = temp; 
            } 
        } 
        String returnStr = ""; 
        if (first != null) { 
            // 遍历取出组合字符串 
            for (String str : first.keySet()) { 
                returnStr += (str + ","); 
            } 
        } 
        if (returnStr.length() > 0) { 
            returnStr = returnStr.substring(0, returnStr.length() - 1); 
        } 
        return returnStr; 
    } 
    
    //拼接商品详情
    public static String getProductDec(String productDec){
    	return productDec.length()<25?productDec:productDec.substring(0,24)+"...";
    }
    
    //拼接商品名称
    public static String getBPname(String bname,String pname){
    	pname = pname.replace("（", "(");
    	pname = pname.replace("）", ")");
    	String bpname="【"+bname+"】"+pname;
    	if(bpname.length()>16){
    		if(bname.length()>6){
    			bpname ="【"+ bname.substring(0,6)+"】" + pname.substring(0,7) + "...";
    		} else{
    			bpname = bpname.substring(0,15) + "...";
    		}
    	}
    	return bpname;
    }
    
    public static String getBEname2(String bname,String ename){
    	String bename="【"+bname+"】"+ename;
    	if(bename.length()>15){
    		if(bname.length()>6){
    			bename ="【"+ bname.substring(0,6)+"】" + ename.substring(0,6)+"...";
    		} else{
    			bename = bename.substring(0,14)+"...";
    		}
    	}
    	return bename;
    }
    
    public static HashMap<String,Object> getSeoMap(HashMap<String,Object> map,String city){   	
    	if(map!=null){
    		map.put("TITLE", map.get("TITLE")==null?"":map.get("TITLE").toString().replace("[地区]", city));
    		map.put("KEYWORDS", map.get("KEYWORDS")==null?"":map.get("KEYWORDS").toString().replace("[地区]", city));
    		map.put("DESCRIPTION", map.get("DESCRIPTION")==null?"":map.get("DESCRIPTION").toString().replace("[地区]", city));
    		return map;
    	}else{
    		return new HashMap<String, Object>();
    	}
		
    }
    
    public static String formatDate(Date date, String pattern){
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	return sdf.format(date);
    }
    
    public static String createSixRandom(){
    	int max=999999;
        int min=100000;
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return String.valueOf(s);
    }
}

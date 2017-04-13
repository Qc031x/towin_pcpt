package com.sgfm.datacenter.util;

import java.util.Random;

/**
 * 字符串工具类。
 * @author ljl
 *
 */
public final class StringUtils {
    /**
     * 判断指定字符串是否为纯数字。
     * @param s
     * @return
     */
    public static boolean isDigit(String s){
        int z=0;//整数数量
        int k=0;//小数点数量
        int length = s.length();
		for(int i=0;i<length;i++){
        	char tempChar = s.charAt(i);
			if(tempChar>='0' && tempChar<='9'){
        		z++;
        	}else if(tempChar=='.'){
        		k++;
        	}
        }
		
        if(z==length){
        	return true;
        }else if(k==1){
        	return (z+k)==length;
        }else{
        	return false;
        }
    }
    
    /**
     * 如果字符串超过指定的长度，自动截取。
     * @param str
     * @param maxLength
     * @return
     */
    public static String getFilterStr(String str,Integer maxLength){
    	if(str!=null && str.length()>maxLength){
    		return str.substring(0, maxLength-1)+"...";
    	}
    	return str;
    }
    
    public static String getMessageValidate() {
		Random random = new Random();
		String s;
		s = "";
		for (int i = 0; i < 4; i++) {
				s = new StringBuffer().append(s).append(random.nextInt(9)+1).toString();
		}
		return s;
	}
    

	/**
	 * 去除字符串中间和两端存在的换行、和空格字符
	 * @param str
	 * @return
	 */
	public static String myTrim(String str){
    	if (null == str){
    		return null;
    	} 
    	String newStr = str.replaceAll("\\s+|\n|\r\n", "").trim();
    	return newStr;
	 }
	
	public static int getTruthAttr(int obj){
    	int k = 0;
		switch (obj) {
		case 0:
			k = 5;
			break;
		case 1:
			k = 6;
			break;
		default:
			k = 16;
			break;
		}
		return k;
    }
}

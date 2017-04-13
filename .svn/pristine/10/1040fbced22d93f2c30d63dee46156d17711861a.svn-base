package com.sgfm.datacenter.util.i18n;

import org.springframework.util.ObjectUtils;

import com.sgfm.base.util.StringUtil;


/**
 * 国际化资源工具类。<br>
 * @author 罗军林
 * @date 2011-6-13
 *
 */
public class I18nUtil {
	private final static String START_SYMBOL = "{";
	private final static String END_SYMBOL = "}";
	
	
	private I18nUtil() {
		super();
	}

	/**
	 * 返回组装后的文本。<br>
	 * 示例：{200}
	 * @param text
	 * @return
	 */
	public static String createTextChunk(Object text) {
		StringBuilder sb = new StringBuilder();
		if(text ==null ){
			sb.append(START_SYMBOL).append(END_SYMBOL);
		}else{
			if(text instanceof String){
				String temp = (String)text;
				if(!temp.trim().startsWith(START_SYMBOL)){
					sb.append(START_SYMBOL);
				}
				
				sb.append(text);
				
				if(!temp.trim().endsWith(END_SYMBOL)){
					sb.append(END_SYMBOL);
				}
			}else{
				sb.append(START_SYMBOL).append(text).append(END_SYMBOL);
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(createTextChunk("{abc}"));
	}
	
}

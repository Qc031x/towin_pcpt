package com.sgfm.datacenter.util;

import java.util.Date;

import net.sf.json.JsonConfig;

/**
 * JSON辅助工具类，数字类型为null时会自动转换为0,使用了本工具类后，数字类型将用生成 空串""，日期类型自动转换成文本.
 * 
 * @author 罗军林
 * 
 */
public class MyJsonConfig extends JsonConfig {
	private String dateStyle ;

	public MyJsonConfig() {
		this("yyyy/MM/dd HH:mm:ss");
	}

	public MyJsonConfig(String dateStyle) {
		super();
		this.dateStyle = dateStyle;
		registerJsonProcessor();
	}
	
	private void registerJsonProcessor() {
		this.registerJsonValueProcessor(Long.class, new MyJsonValueProcessor(this.dateStyle));
		this.registerJsonValueProcessor(Integer.class,new MyJsonValueProcessor(this.dateStyle));
		this.registerJsonValueProcessor(Short.class, new MyJsonValueProcessor(this.dateStyle));
		this.registerJsonValueProcessor(Double.class,new MyJsonValueProcessor(this.dateStyle));
		this.registerJsonValueProcessor(Float.class, new MyJsonValueProcessor(this.dateStyle));
		this.registerJsonValueProcessor(Date.class, new MyJsonValueProcessor(this.dateStyle));
		this.registerJsonValueProcessor(java.sql.Date.class, new MyJsonValueProcessor(this.dateStyle));
	}

	public String getDateStyle() {
		return dateStyle;
	}

}


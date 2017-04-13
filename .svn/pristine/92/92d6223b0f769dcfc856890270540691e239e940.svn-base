package com.sgfm.datacenter.util;

import java.math.BigDecimal;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class MyJsonValueProcessor implements JsonValueProcessor {
    private String dateStyle;

    public MyJsonValueProcessor(String dateStyle) {
        super();
        this.dateStyle = dateStyle;
    }

    @Override
    public Object processArrayValue(Object obj, JsonConfig jsonconfig) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object processObjectValue(String s, Object obj, JsonConfig jsonconfig) {
        if (obj == null) {
            return "";
        } else if (obj instanceof Date || obj instanceof java.sql.Date) {
            try {
            	return TimeZoneUtils.uct2OtherTimeZone((Date)obj,this.dateStyle);
            } catch (Exception e) {
                e.printStackTrace();
                return obj;
            }
        } else if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).toString();
        } else {
            return obj;
        }
    }
}
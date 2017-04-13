package com.sgfm.datacenter.util.i18n;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.sgfm.datacenter.util.NoDatabaseFieldAnnotation;

/**
 * 国际化类。实现了本接口，表示该类需要国际化。
 * 
 * @author 罗军林
 * 
 */

public abstract class Ii18n implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/** 存放修改之前的值。key 为字段名，值为国际化资源ID */
    @NoDatabaseFieldAnnotation
    private Map<String, String> i18n = new HashMap<String, String>();

    public Map<String, String> getI18n()
    {
        return i18n;
    }

    public void addI18n(String fieldName, String oldVal)
    {
        i18n.put(fieldName, oldVal);
    }

    @Override
    public String toString()
    {
        return "Ii18n [i18n=" + i18n + "]";
    }

}

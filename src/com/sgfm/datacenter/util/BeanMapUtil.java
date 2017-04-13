package com.sgfm.datacenter.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;
import com.sgfm.base.util.DateUtil;
//import com.sgfm.datacenter.entity.MatchType;
import com.sgfm.datacenter.exception.AppException;

/**
 * 将map中的值转换成具体的实例，并且会自动转换类型。<br>
 * 
 * @author 罗军林
 * 
 */
public class BeanMapUtil {
	private final static Logger log = Logger.getLogger(BeanMapUtil.class);
	
    /**
     * 将转换后的结果放入result中。
     * 
     * @param map
     * @param result
     */
    public static void convert(Map<String, Object> map, Object result) {
        DefaultTypeConverter converter = new DefaultTypeConverter();
        BeanMap b = BeanMap.create(result);
        for (final Object item : b.keySet()) {

            Object oldVal = map.get(item.toString().toUpperCase());
            if (oldVal == null) {
                oldVal = map.get(result.getClass().getSimpleName().toUpperCase() + "_" + item.toString().toUpperCase());
            }

            Object val = converter.convertValue(oldVal, b.getPropertyType(item + ""));
            if (b.getPropertyType(item + "").equals(Date.class) && String.class.equals(oldVal.getClass())) {
                val = DateUtil.parseDate((String) oldVal, "yyyy/MM/dd HH:mm:ss");
            }
            b.put(item, val);
        }
    }

    /**
     * 返回转换后的结果。
     * 
     * @param map
     * @param targetClass
     * @return
     */
    public static Object convert(final Map<String, Object> map, final Class targetClass) {
        try {
            final Object result = targetClass.newInstance();
            BeanMapUtil.convert(map, result);
            return result;
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据map中的值动态创建一个bean.
     * 
     * @param sourceMap
     * @param className
     * @return
     */
    public static DynaBean createDynaBean(final Map<String, ?> sourceMap, final String className) {
        try {
            final DynaProperty[] props = new DynaProperty[sourceMap.size()];
            int i = 0;
            for (final String key : sourceMap.keySet()) {
                props[i++] = new DynaProperty(key);
            }

            final BasicDynaClass dynaClass = new BasicDynaClass(className, BasicDynaBean.class, props);

            final DynaBean dynaBean = dynaClass.newInstance();
            for (final String key : sourceMap.keySet()) {
                dynaBean.set(key, sourceMap.get(key));
            }
            return dynaBean;
        } catch (final Exception e) {
        	log.error(e.getMessage(), e);
            throw AppException.createErrorException("dataManager.common.system.exception");
        }
    }

    /**
     * 根据map中的值动态创建一个bean.<br>
     * 主要用于数据库返回值时。
     * 
     * @param sourceMap
     * @param className
     * @return
     */
    public static DynaBean createDynaBeanAsSp(final Map<String, ?> sourceMap, final String className) {
        try {
            // final DynaProperty[] props = new DynaProperty[sourceMap.size()];
            List<DynaProperty> props = new ArrayList<DynaProperty>();
            int i = 0;
            for (String key : sourceMap.keySet()) {
                String[] temp = key.split("_");
                String newKey = key;
                if (temp.length == 2 && temp[0].equalsIgnoreCase(className)) {
                    newKey = temp[1];
                    props.add(new DynaProperty(newKey));
                }

            }

            if (props.size() == 0) {
                return null;
            }

            final BasicDynaClass dynaClass = new BasicDynaClass(className, BasicDynaBean.class, props.toArray(new DynaProperty[props.size()]));

            final DynaBean dynaBean = dynaClass.newInstance();
            for (String key : sourceMap.keySet()) {
                String[] temp = key.split("_");
                String newKey = key;
                if (temp.length == 2 && temp[0].equalsIgnoreCase(className)) {
                    newKey = temp[1];
                    dynaBean.set(newKey, sourceMap.get(key));
                }

            }
            return dynaBean;
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            throw AppException.createErrorException("dataManager.common.system.exception");
        }
    }

    /**
     * 将动态BEAN转换成map.
     * 
     * @param dynaBean
     * @return
     */
    public static Map<?, ?> convertDynaBeanAsMap(final DynaBean dynaBean) {
        final Map<Object, Object> map = new HashMap<Object, Object>();
        final DynaProperty[] pp = dynaBean.getDynaClass().getDynaProperties();
        for (final DynaProperty p : pp) {
            map.put(p.getName(), dynaBean.get(p.getName()));
        }
        return map;
    }

    /**
     * 将目标bean转换成map，并返回。
     * 
     * @param o
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map convertBeanMap(Object o) {
        Map<String, Object> temp = BeanMap.create(o);
        Map<Object, Object> result = new HashMap<Object, Object>(temp);
        return result;
    }

    /**
     * 将结果集转换成list对象。
     * 
     * @param result
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> resultSetAsMap(final ResultSet result) throws SQLException {
    	try{
	        final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        while (result.next()) {
	            final ResultSetMetaData meta = result.getMetaData();
	            final Map<String, Object> map = new HashMap<String, Object>();
	            for (int i = 1; i <= meta.getColumnCount(); i++) {
	                // System.out.println(meta.getColumnName(i));
	                map.put(meta.getColumnName(i), result.getObject(i));
	            }
	            list.add(map);
	        }
	        return list;
    	}finally{
    		result.close();
    	}
    }

    public static void main(String[] args) {
       /* Map<String, Object> map = new HashMap<String, Object>();
        map.put("MATCHTYPE_MATCHTYPEID", "123");
        map.put("MATCHTYPE_MATCHTYPENAME", "意大利足球");
        map.put("Inaa_MATCHTYPENAME", "ddddd");

        MatchType type = (MatchType) BeanMapUtil.convert(map, MatchType.class);
        DynaBean dd = BeanMapUtil.createDynaBeanAsSp(map, "matchType");
        System.out.println(dd);
        System.out.println(type);*/
    }

}

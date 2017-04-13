package com.sgfm.datacenter.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.opensymphony.xwork2.ActionContext;
import com.sgfm.base.util.Pager;
import com.sgfm.datacenter.util.i18n.I18nAnnotation;
import com.sgfm.datacenter.util.i18n.Ii18n;
import com.sgfm.datacenter.util.i18n.NoI18nAnnotation;

/**
 * 国际化拦截类。
 * 
 * @author 
 * 
 */
public class I18nIntercept {
   /* private static final Logger logger = Logger.getLogger(I18nIntercept.class);

    *//**当前语言编码*//*
    private static ThreadLocal<String> currentLanguage = new ThreadLocal<String>();

    *//**
     * 切入点拦截处理，如果有NoI18nAnnotation标识，该方法结果集不做国际化处理。
     * @param point
     * @return
     * @throws Throwable
     *//*
    public Object afterReturning(ProceedingJoinPoint point) throws Throwable {

        //判断是否存在NoI18nAnnotation
        MethodSignature signature = (MethodSignature) point.getSignature();
        boolean noI18n = signature.getMethod().isAnnotationPresent(NoI18nAnnotation.class);

        //继续执行原方法
        Object result = point.proceed();

        if (noI18n) {
            return result;
        }

        //对原方法结果集进行国际化处理
        return I18nIntercept.i18nOperate(result);
    }

    *//**
     * 国际化处理。<br>
     * 对以下几类结果集进行处理：<br>
     * <ul>
     * <li>Page:将提取其中的list中的有继承Ii18n的实体数据进行国际化</li>
     * <li>Collection：将提取其中的有继承Ii18n的实体数据进行国际化</li>
     * <li>Ii18n：对继承过Ii18n的实体进行国际化</li>
     * <ul>
     * @param result
     * @return
     * @throws IllegalAccessException
     *//*
    @SuppressWarnings("rawtypes")
    public static Object i18nOperate(final Object result) throws IllegalAccessException {
        I18nIntercept.logger.debug("-------------国际化：");
        long t1 = System.currentTimeMillis();

        String lanuage = I18nIntercept.getLanguage();
        Map<String, Map<String, String>> internationMap = AppCache.getInternationMap();
        if (result instanceof Pager) {
            Pager page = (Pager) result;
            I18nIntercept.changeCollection(page.getList(), lanuage, internationMap);
        } else if (result instanceof Collection) {
            I18nIntercept.changeCollection((Collection) result, lanuage, internationMap);
        } else {
            I18nIntercept.changeObject(result, lanuage, internationMap);
        }

        I18nIntercept.logger.debug("本次国际化所花费时间为：" + (System.currentTimeMillis() - t1));
        I18nIntercept.logger.debug(result);
        return result;
    }

    *//**
     * 对集合中的对象进行国际化。
     * 
     * @param result 待国际化的数据
     * @param lanuage 当前语言编码
     * @throws IllegalAccessException
     *//*
    @SuppressWarnings("rawtypes")
    private static void changeCollection(final Collection result, String lanuage, Map<String, Map<String, String>> internationMap) throws IllegalAccessException {
        for (final Object item : result) {
            I18nIntercept.changeObject(item, lanuage, internationMap);
        }
    }

    *//**
     * 对单个对象进行国际化.
     * 
     * @param result 待国际化数据
     * @param lanuage 当前语言编码
     * @throws IllegalAccessException
     *//*
    @SuppressWarnings("rawtypes")
    private static void changeObject(final Object result, String language, Map<String, Map<String, String>> internationMap) throws IllegalAccessException {
        if (result instanceof Ii18n) {
            final List<Field> fields = I18nIntercept.getAllFields(result.getClass());
            for (final Field field : fields) {

                if (field.getType() == List.class) {
                    field.setAccessible(true);
                    I18nIntercept.changeCollection((Collection) field.get(result), language, internationMap);
                }

                final boolean isHasAnnotaion = field.isAnnotationPresent(I18nAnnotation.class);
                if (isHasAnnotaion) {

                    field.setAccessible(true);
                    I18nIntercept.saveOldVal(result, field);

                    String oldVal = (String) field.get(result);
                    if (!SysUtils.isEmpty(oldVal)) {
                        oldVal = I18nIntercept.convertValue(language, oldVal, internationMap);
                        field.set(result, oldVal);
                    }

                }
            }
        }
    }

    *//**
     * 将修改之前的值保存到i18n变量中。
     * 
     * @param result
     * @param field
     * @throws IllegalAccessException
     *//*
    private static void saveOldVal(final Object result, final Field field) throws IllegalAccessException {
        try {
            Method method = result.getClass().getMethod("addI18n", String.class, String.class);
            method.invoke(result, field.getName(), field.get(result));
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    *//**
     * 将oldVal值转换为指定语言的数据。
     * 
     * @param language
     * @param oldVal
     * @return
     *//*
    public static String convertValue(final String language, final String oldVal) {
        Map<String, Map<String, String>> internationMap = AppCache.getInternationMap();
        return I18nIntercept.convertValue(language, oldVal, internationMap);
    }

    private static String convertValue(final String language, final String oldVal, Map<String, Map<String, String>> internationMap) {
        final Map<String, String> i18nMap = internationMap.get(oldVal);
        if (i18nMap != null) {
            String newVal = i18nMap.get(language);
            if (newVal == null || newVal.trim().length() == 0) {
                final String defaultLanguage = AppCache.getSystemParm(AppCache.DEFAULT_LANGUAGE).toUpperCase();
                newVal = i18nMap.get(defaultLanguage);
            }
            if (newVal == null || newVal.trim().length() == 0) {
                newVal = oldVal;
            }
            return newVal;
        } else {
            return I18nIntercept.getStr(oldVal, language, internationMap);
        }
    }

    *//**
     * 获取当前请求的语言代码。
     * 
     * @return
     *//*
	public static String getLanguage() {
		final HttpServletRequest request = I18nIntercept.getRequest();
		String language = null;
		if (request != null) {
			Locale locale = (Locale)request.getSession().getAttribute("WW_TRANS_I18N_LOCALE");
			if(locale!=null){
				language = locale.getLanguage();
			}
		} else {
			language = I18nIntercept.currentLanguage.get();
		}
		
		if (language == null || language.length() == 0) {
			language = AppCache.getSystemParm(AppCache.DEFAULT_LANGUAGE);
		}

		return language.toUpperCase();
	}

    *//**
     * 得到当前的request请求。
     * @return
     *//*
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        final ActionContext context = ActionContext.getContext();
        if (context != null) {
            request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        }
        return request;
    }

    public static void main(final String[] args) {

        final String contents = "{100} vs {200} {300} vs {500}  很高兴 {100}";
        // final I18nIntercept t = new I18nIntercept();
        // contents = t.getStr(contents);
        System.out.println("最后：" + contents);
        System.out.println(contents.getClass() == String.class);
    }

    *//**
     * 获取国际化后的数据。<br>
     * 对{200} vs {100}这样的数据进行国际化。
     * 
     * @param contents
     * @param language
     * @return
     *//*
    @SuppressWarnings( { "rawtypes", "unchecked" })
    private static String getStr(String contents, final String language, Map<String, Map<String, String>> internationMap) {
        Pattern p = Pattern.compile("\\{.+?\\}");
        Matcher m = p.matcher(contents);
        List list = new ArrayList();
        String key, str1;

        while (m.find()) {
            list.add(m.group());
        }

        for (int i = 0; i < list.size(); i++) {
            key = (String) list.get(i);
            str1 = key.substring(1, key.length() - 1);
            key = "\\{" + str1 + "\\}";
            String i18nVal = I18nIntercept.convertValue(language, str1, internationMap);
            contents = contents.replaceAll(key, i18nVal);
        }
        return contents;
    }

    *//**
     * 获取指定类的所有成员变量，包含父类的。
     * 
     * @param cls
     * @return
     *//*
    @SuppressWarnings("rawtypes")
    private static List<Field> getAllFields(Class cls) {
        List<Field> fields = new ArrayList<Field>();
        while (cls != Object.class) {
            Field[] temp = cls.getDeclaredFields();
            for (final Field item : temp) {
                fields.add(item);
            }

            cls = cls.getSuperclass();
        }
        return fields;
    }

    *//**
     * 设置当前请求的国际化语言。
     * @param language
     *//*
    public static void setCurrentLanguage(String language) {
        I18nIntercept.currentLanguage.set(language);
    }
*/
}

package com.sgfm.datacenter.filter;

import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sgfm.base.util.PropsLoader;
import com.sgfm.datacenter.util.SpringContext;
/**
 * 特殊字符拦截处理器，如果发现提交的请求中有配置的特殊字符都替换成新的字符。
 * @author ljl
 *
 */
public class SpecCharInterceptor implements Interceptor {
	private static final long serialVersionUID = 5897064096029859950L;
	private static Logger log = Logger.getLogger(SpecCharInterceptor.class);
	/**配置的特殊字符*/
	private String[] specChars = null;
	
	/**配置特殊字符的替代字符*/
	private String[] newSpecChars = null;
	
	/**是否需要拦截*/
	private boolean isHandle = false;

	@Override
	public void destroy() {	}

	@Override
	public void init() {
    	PropsLoader props = (PropsLoader) SpringContext.getBean("propsLoader");
    	String specChar =  props.props.getProperty("dataCenter.spec.chars");
    	String newSpecChar = props.props.getProperty("dataCenter.spec.new.chars");
    	
    	if(specChar!=null && newSpecChar!=null){
	    	specChars = specChar.split(",");
	    	newSpecChars = newSpecChar.split(",");
	    	if( specChar!=null && specChar.trim().length()>0 && specChars.length==newSpecChars.length){
	    		isHandle = true;
	    	} 
    	}
    	
    	if(isHandle){
    		log.info("特殊字符拦截处理器启动成功。");
    		log.info("这些特殊字符："+specChar+"将替换成："+newSpecChar);
    	}else{
    		log.info("特殊字符拦截处理器启动失败。原因可能是：特殊字符没有配置或特殊字符和新字符数量不一致。");
    	}
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		doSpecChar(arg0.getInvocationContext());
		String result = arg0.invoke();
		return result;
	}
	
    /**
     * 处理特殊字符，如果是文本域中含有配置文件中配置的字符，将把这些字符改为配置的其它字符。
     * @param servletRequest
     */
    @SuppressWarnings("unchecked")
	private void doSpecChar(ActionContext actionContext) {
    	if(isHandle){
			for (Object item : actionContext.getParameters().entrySet()) {
				Map.Entry<String, String[]> temp = (Map.Entry<String, String[]>) item;
				if (temp.getValue() != null) {
					String[] newValue = new String[temp.getValue().length];
					for (int i = 0; i < newValue.length; i++) {
						String s = temp.getValue()[i];
						for (int j = 0; j < specChars.length; j++) {
							s = s.replaceAll(specChars[j], newSpecChars[j]);
						}
						newValue[i] = s;
					}
					temp.setValue(newValue);
				}
			}
    	}
	}

}

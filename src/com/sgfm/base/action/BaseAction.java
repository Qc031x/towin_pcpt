package com.sgfm.base.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author LiuPing
 *Action基类，所有后续开发的Action都必须继承这个基类
 */
@Controller
@Scope("prototype")
public class BaseAction  extends ActionSupport implements SessionAware{

	public static final String INNERJSP = "innerjsp";
	public static final String OUTERJSP = "outerjsp";
	public static final String ACTION = "action";
	public static final String JSON = "json";
	public String dynaPath;
	public String jsonResult;
	private ActionContext context; // Webwork的ActionContext
	private Map<String, Object> sessionMap;
	/*
	 * 跳转到WEB-INF/content/下的某个文件夹下的jsp页面
	 * folder ： 文件夹名
	 * jspPage ：  目标jsp页面名，不带扩展名
	 * */
	protected String toContentView(String folder , String jspPage){
		if(folder == null || "".equals(folder)){
			dynaPath = jspPage;
		}else{
			dynaPath = new StringBuilder(folder).append("/").append(jspPage).toString();
		}
		return INNERJSP;
	}
	
	/*
	 * 跳转到应用根下面的  jsp页面
	 * jspPage ：  目标jsp页面名，不带扩展名
	 * */
	protected String toWebRootView(String jspPage){
		dynaPath = jspPage;
		return OUTERJSP;
	}
	
	/*
	 * 跳转到一个 Action动作中
	 * nameSpace ： 目标Action的命名空间
	 * action : 目标Action的名称，只写第除Action之前的字母，首字母小写
	 * method ： 目标Action的动作名（内部方法名）
	 * */
	protected String toAction(String nameSpace , String action , String method){
		nameSpace = nameSpace == null ? "":nameSpace;
		dynaPath = new StringBuilder(nameSpace)
		    .append("/")
		    .append(action)
		    .append(".")
		    .append(method)
		    .toString();
		return ACTION;
	}
	
	/*
	 * 跳转到命名空间为core的一个 Action动作中
	 * action : 目标Action的名称，只写第除Action之前的字母，首字母小写
	 * method ： 目标Action的动作名（内部方法名）
	 * */
	protected String toAction(String action , String method){
		dynaPath = new StringBuilder("core")
		    .append("/")
		    .append(action)
		    .append(".")
		    .append(method)
		    .toString();
		return ACTION;
	}
    /**
     * 获得的ActionContext
     */
    public ActionContext getActionContext() {
        return context == null ? context = ActionContext.getContext() : context;
    }

	/*
	 * 转向日历显示页面
	 **/
	public String toCalendar() {
		return toContentView("public", "calendar");
	}

	/**
	 *拦截器调用的方法 
	 */
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
	
	public Object getFromSession(String key){
		return sessionMap.get(key);
	}
	
	public void setToSession(String key , Object obj){
		sessionMap.put(key,obj);
	}
	 /**
     * 获得当前Action的名称
     */
    public String getActionName() {
        return getActionContext().getName();
    }
    
    
    protected Object getParameterValue(String parameter) {
        Object[] parameterArray = getParamenterArray(parameter);
        if (parameterArray != null && parameterArray.length == 1) {
            return parameterArray[0];
        } else {
            return null;
        }
    }

    /**
     * 根据指定的页面参数名称，获取页面传递来的参数值
     *
     * @param parameter
     * @return 数组对象
     */
    protected Object[] getParamenterArray(String parameter) {
        return (Object[]) (getActionContext().getParameters().get(parameter));
	}
   
    
    public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

}

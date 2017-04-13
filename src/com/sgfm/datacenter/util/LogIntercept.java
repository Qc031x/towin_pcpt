package com.sgfm.datacenter.util;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.sgfm.base.util.DateUtil;
/*import com.sgfm.datacenter.entity.Adminlog;
import com.sgfm.datacenter.service.stockmanager.AdminlogService;*/

@Component
public class LogIntercept {
	/*@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(LogIntercept.class);
	private Adminlog adminLog = new Adminlog();
	@Autowired
	private AdminlogService logservice;
	private Long time;

	public void before() throws Throwable {
		time = System.currentTimeMillis();
	}

	*//**
	 * 正常返回后
	 * 
	 * @throws Throwable
	 *//*
	public void afterReturning() throws Throwable {
		adminLog.setStatus(1);
		adminLog.setTotaltime(System.currentTimeMillis() - time);
		if (this.adminLog.isIsok()) { // 是否入库
			this.logservice.addLog(adminLog);
		}
	}

	*//**
	 * 异常情况
	 * 
	 * @throws Exception
	 *//*
	public void afterThrowing(Throwable throwable) throws Exception {
		String msg = throwable.getMessage();
		if (msg != null && msg.length() > 100) {
			msg = msg.substring(0, 100);
			adminLog.setOperation(msg);
		}
		adminLog.setStatus(0);
		adminLog.setTotaltime(System.currentTimeMillis() - time);
		if (this.adminLog.isIsok()) {// 是否入库
			this.logservice.addLog(adminLog);
		}
	}

	*//**
	 * 拦截演方法前后
	 * 
	 * @throws Throwable
	 *//*
	public Object around(ProceedingJoinPoint point) throws Throwable {
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		LogAnnotation an = method.getAnnotation(LogAnnotation.class);

		if (method.isAnnotationPresent(NoLogAnnotation.class)) {
			this.adminLog.setIsok(false);
			return point.proceed();
		}

		if (an != null) {
			this.adminLog.setDiscrible(an.describe());
			this.adminLog.setOperatetype(an.type());
			if (an.saveparam()) {
				StringBuffer sb = new StringBuffer(method.getName() + "@");
				Object[] param = point.getArgs();
				for (int i = 0; i < param.length; i++) {
					if (param[i] != null) {
						if (param[i].getClass().isPrimitive()) {
							sb.append(param[i]).append("@");
						} else if(param[i] instanceof Date){
							Date date=(Date)param[i];
							sb.append(DateUtil.format(date,"yyyy/MM/dd HH:mm:ss")).append("@");
						}else{
							if (param[i] instanceof String[]) {
								String[] pm = (String[]) param[i];
								for (int j = 0; j < pm.length; j++) {
									sb.append(pm[j]).append(",");
								}
							} else {
								sb.append(param[i].toString()).append("@");
							}
						}
					}
				}
				if (sb .length() > 100) {
					this.adminLog.setOperation(sb.substring(0, 100));
				}else{
					this.adminLog.setOperation(sb.toString());
				}
			}
		}else{
			this.adminLog.setOperation(method.getName());
		}
		this.adminLog.setOperator(AppCache.getCurrentUser().getLoginName());

		HttpServletRequest request = null;
		final ActionContext context = ActionContext.getContext();
		if (context != null) {
			request = (HttpServletRequest) context
					.get(ServletActionContext.HTTP_REQUEST);
			this.adminLog.setIp(getClientAddress(request));
		}
		this.adminLog.setOperatedate(new Date());
		return point.proceed();
	}

	*//**
	 * 获取客户端调用的IP
	 *//*
	private final String getClientAddress(HttpServletRequest request) {
		String address = request.getHeader("X-Forwarded-For");
		if (address != null) {
			return address;
		}
		return request.getRemoteAddr();
	}*/
}

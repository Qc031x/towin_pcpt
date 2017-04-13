package com.sgfm.base.strutsext;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

public class JsonResult extends StrutsResultSupport{

	private String contentType;
	@Override
	protected void doExecute(String arg0, ActionInvocation arg1)
			throws Exception {
		//获取响应对象
		HttpServletResponse response = (HttpServletResponse) arg1.getInvocationContext().get(HTTP_RESPONSE);

        if (contentType == null) {
            contentType = "application/json;charset=UTF-8";
        }
        response.setContentType(contentType);
        //获取jsonResult并发送响应
        PrintWriter out = response.getWriter();
        String jsonResult = (String) arg1.getStack().findValue("jsonResult");
        out.println(jsonResult);
        out.flush();
        out.close();
	}
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}

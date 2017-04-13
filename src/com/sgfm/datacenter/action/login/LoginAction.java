package com.sgfm.datacenter.action.login;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sgfm.base.action.BaseAction;
import com.sgfm.datacenter.SysConstant;
import com.sgfm.datacenter.memcached.CacheHashMap;
import com.sgfm.datacenter.memcached.MemcachedConstant;
import com.sgfm.datacenter.util.CookieUtil;
import com.sgfm.datacenter.util.JsonResponseResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CacheHashMap cacheHashMap;
	
	public String goCity(){
		
		try {
			List<HashMap<String,Object>> cityList = cacheHashMap.getValidCity(MemcachedConstant.TW_VALIDCITY);
			String allCity = JSONArray.fromObject(cityList).toString();
	        super.getRequest().setAttribute("allCity", allCity);
	    } catch (final Exception e) {
	        this.logger.error(e);
	    }
	
		return super.toContentView("public", "city");
		
	}
	
	public String updateCity(){
		JsonResponseResult responseResult = null;
		boolean addFlag = false;
		try {
			String city = super.getRequest().getParameter("city");
			String cityName = super.getRequest().getParameter("cityName");
			if(city == null){
				responseResult = JsonResponseResult.createFalied("city为Null！");
				super.jsonResult = JSONObject.fromObject(responseResult).toString();
				return BaseAction.JSON;
			}
			super.getRequest().getSession().setAttribute(SysConstant.SYS_CITYID, city);
			super.getRequest().getSession().setAttribute(SysConstant.SYS_CITYNAME, cityName);
			
			cityName   =   java.net.URLEncoder.encode(cityName,   "utf-8");   
			CookieUtil.addFrontCookie(super.getResponse(), SysConstant.COOKIE_CITYNAME, cityName, SysConstant.COOKIE_LIFRCYCLE_HOUR);
			
			addFlag = true;
			responseResult = JsonResponseResult.createSuccess();
		} catch (final Exception e) {
			e.printStackTrace();
			logger.error(e);
			responseResult = JsonResponseResult.createFalied("操作失败,请联系管理员！");
			super.jsonResult = JSONObject.fromObject(responseResult).toString();
		}

		responseResult.addData(addFlag);
		super.jsonResult = JSONArray.fromObject(addFlag).toString();
		return BaseAction.JSON;
	}
	
}

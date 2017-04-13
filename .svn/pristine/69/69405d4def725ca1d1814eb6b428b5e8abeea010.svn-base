package com.sgfm.datacenter.action.card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sgfm.base.action.BaseAction;
import com.sgfm.datacenter.SysConstant;
import com.sgfm.datacenter.entity.CgVariable;
import com.sgfm.datacenter.exception.AppException;
import com.sgfm.datacenter.memcached.CacheHashMap;
import com.sgfm.datacenter.memcached.MemcachedConstant;
import com.sgfm.datacenter.service.card.CardService;
import com.sgfm.datacenter.service.product.ProductService;
import com.sgfm.datacenter.util.JsonResponseResult;

@Controller
@Scope("prototype")
public class CardAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private CacheHashMap cacheMap;
	
	public CgVariable cgVariable = new CgVariable();
	
	
	public String ajaxLoadCardList(){
		JsonResponseResult result = null;
		try {
			String city = super.getRequest().getSession().getAttribute(SysConstant.SYS_CITYID).toString();
			String hosptType=super.getRequest().getParameter("hosptType");
			String prodType=super.getRequest().getParameter("prodType");
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("pageNo", super.getRequest().getParameter("pageNo"));
			map.put("hosptType", hosptType);
			map.put("prodType", prodType);
			map.put("city", city);
			Map<String, Object> infoMap = cardService.findCardList(map);
			
			result = JsonResponseResult.createSuccess();
			result.addData(infoMap.get("pages"));
		} catch (final AppException app){
			logger.error(app);
			final String msg = getText(app.getMessage());
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final Exception e){
			e.printStackTrace();
			logger.error(e);
            final String msg = getText("操作失败！");
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		}
		super.jsonResult = JSONObject.fromObject(result).toString();
		return BaseAction.JSON;
	}
	
	public String getProductType(){
		JsonResponseResult result = null;
		try {
			//String city = super.getRequest().getSession().getAttribute(SysConstant.SYS_CITYID).toString();
			//String condition=super.getRequest().getParameter("condition");
			HashMap<String, Object> map=new HashMap<String, Object>();
			List<HashMap<String, Object>> productType = cardService.getProductTypeList();
			
			result = JsonResponseResult.createSuccess();
			result.addData(productType);
		} catch (final AppException app){
			logger.error(app);
			final String msg = getText(app.getMessage());
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final Exception e){
			e.printStackTrace();
			logger.error(e);
            final String msg = getText("操作失败！");
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		}
		super.jsonResult = JSONObject.fromObject(result).toString();
		return BaseAction.JSON;
	}
}

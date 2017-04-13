package com.sgfm.datacenter.action.cache;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sgfm.base.action.BaseAction;
import com.sgfm.datacenter.exception.AppException;
import com.sgfm.datacenter.memcached.BranchCache;
import com.sgfm.datacenter.memcached.CityCache;
import com.sgfm.datacenter.memcached.ProductCache;
import com.sgfm.datacenter.memcached.TypeCache;
import com.sgfm.datacenter.util.JsonResponseResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@SuppressWarnings("serial")
public class CacheAction extends BaseAction {
	
	private static Logger logger = Logger.getLogger(CacheAction.class.getName());
	
	@Autowired
	private TypeCache typeCache;
	
	@Autowired
	private CityCache cityCache;
	
	@Autowired
	private ProductCache productCache;
	
	@Autowired
	private BranchCache branchCache;
	
	/**
	 * 类型缓存更新
	 * @return
	 * @author kangliangyu
	 */
	public String createCacheTypeInfo() {
		JsonResponseResult responseResult = null;
		boolean addFlag = false;
		try {
			typeCache.typeCacheInitialization();
			addFlag = true;
			responseResult = JsonResponseResult.createSuccess();
		} catch (final AppException app) {
			logger.error(app);
			final String msg = getText(app.getMessage());
			responseResult = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(responseResult).toString();
		} catch (final Exception e) {
			e.printStackTrace();
			logger.error(e);
			final String msg = getText("操作失败,请联系管理员！");
			responseResult = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(responseResult).toString();
		}

		responseResult.addData(addFlag);
		super.jsonResult = JSONArray.fromObject(addFlag).toString();
		return BaseAction.JSON;
	}
	
	/**
	 * 城市缓存更新
	 * @return
	 * @author kangliangyu
	 */
	public String createCacheCityInfo() {
		JsonResponseResult responseResult = null;
		boolean addFlag = false;
		try {
			cityCache.cityCacheInitialization();
			addFlag = true;
			responseResult = JsonResponseResult.createSuccess();
		} catch (final AppException app) {
			logger.error(app);
			final String msg = getText(app.getMessage());
			responseResult = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(responseResult).toString();
		} catch (final Exception e) {
			e.printStackTrace();
			logger.error(e);
			final String msg = getText("操作失败,请联系管理员！");
			responseResult = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(responseResult).toString();
		}

		responseResult.addData(addFlag);
		super.jsonResult = JSONArray.fromObject(addFlag).toString();
		return BaseAction.JSON;
	}
	
	/**
	 * 商品缓存更新
	 * @return
	 * @author kangliangyu
	 */
	public String createCacheProductInfo() {
		JsonResponseResult responseResult = null;
		boolean addFlag = false;
		try {
			productCache.productCacheInitialization();
			addFlag = true;
			responseResult = JsonResponseResult.createSuccess();
		} catch (final AppException app) {
			logger.error(app);
			final String msg = getText(app.getMessage());
			responseResult = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(responseResult).toString();
		} catch (final Exception e) {
			e.printStackTrace();
			logger.error(e);
			final String msg = getText("操作失败,请联系管理员！");
			responseResult = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(responseResult).toString();
		}

		responseResult.addData(addFlag);
		super.jsonResult = JSONArray.fromObject(addFlag).toString();
		return BaseAction.JSON;
	}
	
	/**
	 * 分院缓存更新
	 * @return
	 * @author kangliangyu
	 */
	public String createCacheBranchInfo() {
		JsonResponseResult responseResult = null;
		boolean addFlag = false;
		try {
			branchCache.branchCacheInitialization();
			addFlag = true;
			responseResult = JsonResponseResult.createSuccess();
		} catch (final AppException app) {
			logger.error(app);
			final String msg = getText(app.getMessage());
			responseResult = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(responseResult).toString();
		} catch (final Exception e) {
			e.printStackTrace();
			logger.error(e);
			final String msg = getText("操作失败,请联系管理员！");
			responseResult = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(responseResult).toString();
		}

		responseResult.addData(addFlag);
		super.jsonResult = JSONArray.fromObject(addFlag).toString();
		return BaseAction.JSON;
	}
}

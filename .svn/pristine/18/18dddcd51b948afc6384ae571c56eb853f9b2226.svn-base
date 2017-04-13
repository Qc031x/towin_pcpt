package com.sgfm.datacenter.memcached;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.danga.MemCached.MemCachedClient;
import com.sgfm.datacenter.service.cache.CacheService;

/**
 * 商品类型和机构类型缓存
 * @author kangliangyu
 *
 */
@Component
public class TypeCache {
	
	private static Logger logger = Logger.getLogger(TypeCache.class.getName());
	
	@Autowired
	private MemCachedClient  mcc;
	
	@Autowired
	private CacheService cacheService;
	
	public void typeCacheInitialization(){
		logger.info("商品类型和机构类型缓存加载beg");
		
		//缓存商品类型(年龄和类型)
		this.cacheProductType("");
		
		//缓存分院类型
		this.cacheBranchType();
    	
		logger.info("商品类型和机构类型缓存加载end");
	}
	
	/**
	 * 缓存商品类型(年龄和类型)
	 */
	public List<HashMap<String,Object>> cacheProductType(String keyName){
		logger.info("商品类型(年龄和类型)缓存加载beg");
		
		List<HashMap<String,Object>> productAllTypeList = cacheService.findProductTypeList();
		
		if(productAllTypeList == null || productAllTypeList.isEmpty()){
			logger.info("商品类型(年龄和类型)沒有数据");
			return null;
		}
		
		//年龄类型集合
		List<HashMap<String,Object>> productAgeList = new ArrayList<>();
		//商品套餐类型集合
		List<HashMap<String,Object>> productTypeList = new ArrayList<>();
		
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		
		String category;
		int size = productAllTypeList.size();
		for(int i = 0; i < size; i++){
			category = memcachedUtil.getString(productAllTypeList.get(i).get("CATEGORY_ID"));
			
			if("".equals(category))continue;
			
			if("1".equals(category)){
				productAgeList.add(productAllTypeList.get(i));
				continue;
			}
			
			productTypeList.add(productAllTypeList.get(i));
			
		}
		
		mcc.set(MemcachedConstant.TW_PRODUCT_TYPE, productTypeList);
    	mcc.set(MemcachedConstant.TW_PRODUCT_AGE, productAgeList);
    	
    	logger.info("商品类型(年龄和类型)缓存加载end");
    	
    	/*System.err.println("productType:"+mcc.get(MemcachedConstant.TW_PRODUCT_TYPE));
    	System.err.println("productAge:"+mcc.get(MemcachedConstant.TW_PRODUCT_AGE));*/
    	
    	if(keyName.equals(MemcachedConstant.TW_PRODUCT_TYPE))return productTypeList;
    	
    	return productAgeList;
	}
	
	/**
	 * 缓存分院类型
	 */
	public List<HashMap<String,Object>> cacheBranchType(){
		
		logger.info("分院类型缓存加载beg");
		
		List<HashMap<String,Object>> branchTypeList = cacheService.findBranchTypeList();
		
		if(branchTypeList == null || branchTypeList.isEmpty()){
			logger.info("分院类型沒有数据");
			return null;
		}
		
		mcc.set(MemcachedConstant.TW_BRANCH_TYPE, branchTypeList);
		
		logger.info("分院类型缓存加载end");
		
		/*System.err.println("branchType:"+mcc.get(MemcachedConstant.TW_BRANCH_TYPE));*/
		
		return branchTypeList;
	}
}

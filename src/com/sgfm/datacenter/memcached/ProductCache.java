package com.sgfm.datacenter.memcached;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.danga.MemCached.MemCachedClient;
import com.sgfm.datacenter.service.cache.CacheService;
import com.sgfm.datacenter.util.CommUtil;

/**
 * 商品缓存
 * @author kangliangyu
 *
 */
@SuppressWarnings("unchecked")
@Component
public class ProductCache {
	
	private static Logger logger = Logger.getLogger(ProductCache.class.getName());
	
	@Autowired
	private MemCachedClient  mcc;
	
	@Autowired
	private CacheService cacheService;

	public void productCacheInitialization(){
		logger.info("商品缓存加载beg");
		
		//缓存商品基础信息
		this.cacheProductInfo();
		//缓存商品类型
		this.cacheProductType();
		//缓存商品的体检卡图片信息
		this.cacheCardImg();
		//缓存商品的体检项目
		this.cacheProductSetmeal();
		//缓存商品的所属分院
		this.cacheProductBranch();
		
		logger.info("商品缓存加载end");
	}
	
	/**
	 * 缓存商品基础信息
	 */
	public void cacheProductInfo(){
		logger.info("商品基础信息缓存加载beg");
		
		List<HashMap<String, Object>> productList = cacheService.findValidProduct();
		if(productList == null || productList.isEmpty()){
			logger.info("商品基础信息没有数据");
			return;
		}
		
		new CommUtil().setClobToString(productList, "PRODUCT_DESC");
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		String pid;
		int size = productList.size();
		for(int i = 0; i < size; i++){
			pid = memcachedUtil.getString(productList.get(i).get("PID"));
			
			if("".equals(pid))continue;
			
			mcc.set(MemcachedConstant.TW_PRODUCT+pid, productList.get(i));
		}
		
		logger.info("商品基础信息缓存加载end");
		
		/*for(int i = 0; i < productList.size(); i++){
			pid = memcachedUtil.getString(productList.get(i).get("PID"));
			
			if("".equals(pid))continue;
			
			System.err.println(pid+":"+mcc.get(MemcachedConstant.TW_PRODUCT+pid));
		}*/
		
	}
	
	/**
	 * 缓存商品类型
	 */
	public void cacheProductType(){
		logger.info("商品类型信息缓存加载beg");
		
		List<HashMap<String, Object>> productTypeList = cacheService.findTypeByProduct();
		if(productTypeList == null || productTypeList.isEmpty()){
			logger.info("商品类型信息没有数据");
			return;
		}
		
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		
		String pid,category;
		List<HashMap<String,Object>> typeList, ageList;
		HashMap<String,Object> typeMap = new HashMap<String, Object>();
		HashMap<String,Object> ageMap = new HashMap<String, Object>();
		int size = productTypeList.size();
		for(int i = 0; i < size; i++){
			pid = memcachedUtil.getString(productTypeList.get(i).get("PID"));
			category = memcachedUtil.getString(productTypeList.get(i).get("CATEGORY_ID"));
			
			if("".equals(pid) || "".equals(category))continue;
			
			if("1".equals(category)){
				ageList = new ArrayList<>();
				
				//判断map是否已经存在这个pid的属性，如果存在则合并
				if(ageMap.containsKey(pid)){
					ageList = (List<HashMap<String, Object>>) ageMap.get(pid);
				}
				
				ageList.add(productTypeList.get(i));
				ageMap.put(pid,ageList);
				
				continue;
			}
			
			typeList = new ArrayList<>();
			
			//判断map是否已经存在这个pid的属性，如果存在则合并
			if(typeMap.containsKey(pid)){
				typeList = (List<HashMap<String, Object>>) typeMap.get(pid);
			}
			
			typeList.add(productTypeList.get(i));
			typeMap.put(pid,typeList);
			
		}
		
		for (Entry<String, Object> entry : typeMap.entrySet()) {       
		    mcc.set(MemcachedConstant.TW_TYPEBYPRODUCT+entry.getKey(), entry.getValue());
		}
		
		for (Entry<String, Object> entry : ageMap.entrySet()) {       
		    mcc.set(MemcachedConstant.TW_AGEBYPRODUCT+entry.getKey(), entry.getValue());
		}
		
		logger.info("商品类型信息缓存加载end");
		
		/*for (Entry<String, Object> entry : typeMap.entrySet()) {    
			System.err.println("type"+entry.getKey()+":"+mcc.get(MemcachedConstant.TW_TYPEBYPRODUCT+entry.getKey()));
		}
		
		for (Entry<String, Object> entry : ageMap.entrySet()) {       
		    System.err.println("age"+entry.getKey()+":"+mcc.get(MemcachedConstant.TW_AGEBYPRODUCT+entry.getKey()));
		}*/
	}
	
	/**
	 * 缓存商品的体检卡图片信息
	 */
	public void cacheCardImg(){
		logger.info("商品体检卡图片信息缓存加载beg");
		
		List<HashMap<String, Object>> productCardImgList = cacheService.findCardImgByProduct();
		if(productCardImgList == null || productCardImgList.isEmpty()){
			logger.info("商品体检卡图片信息没有数据");
			return;
		}
		
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		String pid;
		int size = productCardImgList.size();
		for(int i = 0; i < size; i++){
			pid = memcachedUtil.getString(productCardImgList.get(i).get("PID"));
			
			if("".equals(pid))continue;
			
			mcc.set(MemcachedConstant.TW_PRODUCT_CARDIMG+pid, productCardImgList.get(i));
		}
		
		logger.info("商品体检卡图片信息缓存加载end");
		
		/*for(int i = 0; i < productCardImgList.size(); i++){
			pid = memcachedUtil.getString(productCardImgList.get(i).get("PID"));
			
			if("".equals(pid))continue;
			
			System.err.println(pid+":"+mcc.get(MemcachedConstant.TW_PRODUCT_CARDIMG+pid));
		}*/
		
	}
	
	/**
	 * 缓存商品的体检项目信息
	 */
	public void cacheProductSetmeal(){
		logger.info("商品体检项目信息缓存加载beg");
		
		List<HashMap<String, Object>> productSetmealList = cacheService.findSetmealByProduct();
		if(productSetmealList == null || productSetmealList.isEmpty()){
			logger.info("商品体检项目信息没有数据");
			return;
		}
		
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		List<HashMap<String,Object>> list;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String pid;
		int size = productSetmealList.size();
		for(int i = 0; i < size; i++){
			pid = memcachedUtil.getString(productSetmealList.get(i).get("PID"));
			
			if("".equals(pid))continue;
			
			list = new ArrayList<>();
			
			//判断map是否已经存在这个pid的属性，如果存在则合并
			if(map.containsKey(pid)){
				list = (List<HashMap<String, Object>>) map.get(pid);
			}
			
			list.add(productSetmealList.get(i));
			map.put(pid,list);
		}
		
		for (Entry<String, Object> entry : map.entrySet()) {       
		    mcc.set(MemcachedConstant.TW_PRODUCT_SETMEAL+entry.getKey(), entry.getValue());
		} 
		
		logger.info("商品体检项目信息缓存加载end");
		
		/*for (Entry<String, Object> entry : map.entrySet()) {   
			System.err.println(entry.getKey()+":"+mcc.get(MemcachedConstant.TW_PRODUCT_SETMEAL+entry.getKey()));
		}*/
	}
	
	/**
	 * 缓存商品所属分院
	 */
	public void cacheProductBranch(){
		logger.info("商品所属分院信息缓存加载beg");
		
		List<HashMap<String, Object>> branchList = cacheService.findBranchByProduct();
		if(branchList == null || branchList.isEmpty()){
			logger.info("商品所属分院信息没有数据");
			return;
		}
		
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		List<String> list;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String pid;
		int size = branchList.size();
		for(int i = 0; i < size; i++){
			pid = memcachedUtil.getString(branchList.get(i).get("PID"));
			
			if("".equals(pid))continue;
			
			list = new ArrayList<>();
			
			//判断map是否已经存在这个pid的属性，如果存在则合并
			if(map.containsKey(pid)){
				list = (List<String>) map.get(pid);
			}
			
			list.add(memcachedUtil.getString(branchList.get(i).get("ESID")));
			map.put(pid,list);
		}
		
		for (Entry<String, Object> entry : map.entrySet()) {       
		    mcc.set(MemcachedConstant.TW_BRANCHBYPRODUCT+entry.getKey(), entry.getValue());
		}
		
		logger.info("商品所属分院信息缓存加载end");
		
		/*for (Entry<String, Object> entry : map.entrySet()) {   
			System.err.println(entry.getKey()+":"+mcc.get(MemcachedConstant.TW_BRANCHBYPRODUCT+entry.getKey()));
		}*/
	}
	
	/**
	 * 缓存商品基础信息 单个商品
	 * @param pid
	 */
	public HashMap<String, Object> cacheProductInfoByPid(String pid){
		logger.info("pid为"+pid+"的商品基础信息缓存加载beg");
		
		HashMap<String, Object> productMap = cacheService.findValidProductByPid(pid);
		
		if(productMap == null || productMap.isEmpty()){
			logger.info("pid为"+pid+"的商品基础信息没有数据");
			return null;
		}
		
		new CommUtil().setClobToStringMap(productMap, "PRODUCT_DESC");
		mcc.set(MemcachedConstant.TW_PRODUCT+pid, productMap);
		
		logger.info("pid为"+pid+"的商品基础信息缓存加载end");
		
		return productMap;
	}
	
	/**
	 * 缓存单个商品类型
	 * @param pid
	 */
	public List<HashMap<String,Object>> cacheProductTypeByPid(String pid, String keyName){
		logger.info("pid为"+pid+"的商品类型信息缓存加载beg");
		
		List<HashMap<String, Object>> productTypeList = cacheService.findTypeByPId(pid);
		if(productTypeList == null || productTypeList.isEmpty()){
			logger.info("pid为"+pid+"的商品类型信息没有数据");
			return null;
		}
		
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		
		String category;
		List<HashMap<String,Object>> typeList = new ArrayList<>();
		List<HashMap<String,Object>> ageList = new ArrayList<>();
		int size = productTypeList.size();
		for(int i = 0; i < size; i++){
			category = memcachedUtil.getString(productTypeList.get(i).get("CATEGORY_ID"));
			
			if("".equals(category))continue;
			
			if("1".equals(category)){
				ageList.add(productTypeList.get(i));
				continue;
			}
			
			typeList.add(productTypeList.get(i));
		}
		
		mcc.set(MemcachedConstant.TW_TYPEBYPRODUCT+pid, typeList);
		mcc.set(MemcachedConstant.TW_AGEBYPRODUCT+pid, ageList);
		
		logger.info("pid为"+pid+"的商品类型信息缓存加载end");
		
		if(keyName.indexOf(MemcachedConstant.TW_TYPEBYPRODUCT) != -1)return typeList;
		
		return ageList;
		
	}
	
	/**
	 * 缓存单个商品的体检卡图片信息 
	 * @param pid
	 */
	public HashMap<String, Object> cacheCardImgByPid(String pid){
		logger.info("pid为"+pid+"的商品体检卡图片信息缓存加载beg");
		
		HashMap<String, Object> productCardImgMap = cacheService.findCardImgByPId(pid);
		if(productCardImgMap == null || productCardImgMap.isEmpty()){
			logger.info("pid为"+pid+"的商品体检卡图片信息没有数据");
			return null;
		}
		
		mcc.set(MemcachedConstant.TW_PRODUCT_CARDIMG+pid, productCardImgMap);
		
		logger.info("pid为"+pid+"的商品体检卡图片信息缓存加载end");
		
		return productCardImgMap;
	}
	
	/**
	 * 缓存单个商品的体检项目信息
	 * @param pid
	 */
	public List<HashMap<String, Object>> cacheProductSetmealByPid(String pid){
		logger.info("pid为"+pid+"的商品体检项目信息缓存加载beg");
		
		List<HashMap<String, Object>> productSetmealList = cacheService.findSetmealByPId(pid);
		if(productSetmealList == null || productSetmealList.isEmpty()){
			logger.info("pid为"+pid+"的商品体检项目信息没有数据");
			return null;
		}
		
		mcc.set(MemcachedConstant.TW_PRODUCT_SETMEAL+pid, productSetmealList);
		
		logger.info("pid为"+pid+"的商品体检项目信息缓存加载end");
		
		return productSetmealList;
	}
	
	/**
	 * 缓存单个商品所属分院
	 * @param pid
	 */
	public List<String> cacheProductBranchByPid(String pid){
		logger.info("pid为"+pid+"的商品所属分院信息缓存加载beg");
		
		List<String> branchList = cacheService.findBranchByPid(pid);
		if(branchList == null || branchList.isEmpty()){
			logger.info("pid为"+pid+"的商品所属分院信息没有数据");
			return null;
		}
		
		mcc.set(MemcachedConstant.TW_BRANCHBYPRODUCT+pid, branchList);
		
		logger.info("pid为"+pid+"的商品所属分院信息缓存加载end");
		
		return branchList;
	}
	
}

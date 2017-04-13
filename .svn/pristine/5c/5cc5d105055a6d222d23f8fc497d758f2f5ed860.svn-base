package com.sgfm.datacenter.memcached;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.danga.MemCached.MemCachedClient;

/**
 * 获取缓存方法
 * @author kangliangyu
 *
 */
@SuppressWarnings("unchecked")
@Component
public class CacheHashMap {
	
	@Autowired
	private MemCachedClient  mcc;
	
	@Autowired
	private TypeCache typeCache;
	
	@Autowired
	private CityCache cityCache;
	
	@Autowired
	private ProductCache productCache;
	
	@Autowired
	private BranchCache branchCache;
	
	
	/**
	 * 获取体检套餐所有类型		TW_PRODUCT_TYPE
	 * 获取体检套餐所有年龄		TW_PRODUCT_AGE
	 * @param keyName
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> getProductTypeList(String keyName){
		List<HashMap<String, Object>> productTypeOrAgeList = (List<HashMap<String, Object>>) mcc.get(keyName);
		
		if(productTypeOrAgeList == null || productTypeOrAgeList.isEmpty()){
			//缓存商品类型(年龄和类型)
			productTypeOrAgeList = typeCache.cacheProductType(keyName);
			
			if(productTypeOrAgeList == null || productTypeOrAgeList.isEmpty()){
				return new ArrayList<>();
			}
		}
		
		return productTypeOrAgeList;
	}
	
	/**
	 * 获取分院所有类型
	 * @param keyName
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> getBranchTypeList(String keyName){
		List<HashMap<String, Object>> branchTypeList = (List<HashMap<String, Object>>) mcc.get(keyName);
		
		if(branchTypeList == null || branchTypeList.isEmpty()){
			//缓存分院类型
			branchTypeList = typeCache.cacheBranchType();
			
			if(branchTypeList == null || branchTypeList.isEmpty()){
				return new ArrayList<>();
			}
		}
		
		return branchTypeList;
	}
	
	/**
	 * 获取有分院的城市下的区域	TW_CITY_AREA + city
	 * @param keyName
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> getAreaByCity(String keyName){
		List<HashMap<String, Object>> areaList = (List<HashMap<String, Object>>) mcc.get(keyName);
		
		if(areaList == null || areaList.isEmpty()){
			String city = keyName.replaceAll(MemcachedConstant.TW_CITY_AREA,"").trim();
			
			//缓存这个城市下的区域
			areaList = cityCache.cacheAreaByCityId(city);
			
			if(areaList == null || areaList.isEmpty()){
				return new ArrayList<>();
			}
			
		}
		
		return areaList;
	}
	
	/**
	 * 获取有分院的城市		TW_VALIDCITY
	 * @param keyName
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> getValidCity(String keyName){
		List<HashMap<String, Object>> cityList = (List<HashMap<String, Object>>) mcc.get(keyName);
		
		if(cityList == null || cityList.isEmpty()){
			//缓存有分院的城市
			cityList = cityCache.cacheValidCity();
			
			if(cityList == null || cityList.isEmpty()){
				return new ArrayList<>();
			}
			
		}
		
		return cityList;
	}
	
	/**
	 * 获取城市下的品牌		TW_BRAND_CITY + city
	 * @param keyName
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> getBrandByCity(String keyName){
		List<HashMap<String, Object>> brandList = (List<HashMap<String, Object>>) mcc.get(keyName);
		
		if(brandList == null || brandList.isEmpty()){
			String city = keyName.replaceAll(MemcachedConstant.TW_BRAND_CITY,"").trim();
			//缓存这个城市下的品牌
			brandList = cityCache.cacheBrandByCityId(city);
			
			if(brandList == null || brandList.isEmpty()){
				return new ArrayList<>();
			}
			
		}
		
		return brandList;
	}
	
	/**
	 * 获取商品的基础信息	TW_PRODUCT + pid
	 * @param keyName
	 * @return
	 * @author kangliangyu
	 */
	public HashMap<String, Object> getProductByPid(String keyName){
		HashMap<String,Object> productMap = (HashMap<String, Object>) mcc.get(keyName);
		
		if(productMap == null || productMap.isEmpty()){
			String pid = keyName.replaceAll(MemcachedConstant.TW_PRODUCT,"").trim();
			//缓存这个商品
			productMap = productCache.cacheProductInfoByPid(pid);
			
			if(productMap == null || productMap.isEmpty()){
				return new HashMap<String, Object>();
			}
			
		}
		
		return productMap;
	}
	
	/**
	 * 获取商品的类型	TW_TYPEBYPRODUCT + pid
	 * 获取商品的年龄	TW_AGEBYPRODUCT	+ pid
	 * @param keyName
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> getProductTypeByPid(String keyName){
		List<HashMap<String, Object>> typeList = (List<HashMap<String, Object>>) mcc.get(keyName);
		
		if(typeList == null || typeList.isEmpty()){
			String pid = keyName.replaceAll(MemcachedConstant.TW_TYPEBYPRODUCT,"")
								.replaceAll(MemcachedConstant.TW_AGEBYPRODUCT,"").trim();
			//缓存这个商品类型
			typeList = productCache.cacheProductTypeByPid(pid, keyName);
			
			if(typeList == null || typeList.isEmpty()){
				return new ArrayList<>();
			}
			
		}
		
		return typeList;
	}
	
	/**
	 * 获取商品的体检卡图片信息	TW_PRODUCT_CARDIMG + pid
	 * @param keyName
	 * @return
	 * @author kangliangyu
	 */
	public HashMap<String, Object> getProductCardImgByPid(String keyName){
		HashMap<String,Object> cardImgMap = (HashMap<String, Object>) mcc.get(keyName);
		
		if(cardImgMap == null || cardImgMap.isEmpty()){
			String pid = keyName.replaceAll(MemcachedConstant.TW_PRODUCT_CARDIMG,"").trim();
			//缓存这个商品的体检卡信息
			cardImgMap = productCache.cacheCardImgByPid(pid);
			
			if(cardImgMap == null || cardImgMap.isEmpty()){
				return new HashMap<String, Object>();
			}
			
		}
		
		return cardImgMap;
	}
	
	/**
	 * 获取商品的体检单项信息		TW_PRODUCT_SETMEAL + pid
	 * @param keyName
	 * @return
	 */
	public List<HashMap<String, Object>> getProductSetmealByPid(String keyName){
		List<HashMap<String, Object>> setmealList = (List<HashMap<String, Object>>) mcc.get(keyName);
		
		if(setmealList == null || setmealList.isEmpty()){
			String pid = keyName.replaceAll(MemcachedConstant.TW_PRODUCT_SETMEAL,"").trim();
			//缓存这个商品的体检单项
			setmealList = productCache.cacheProductSetmealByPid(pid);
			
			if(setmealList == null || setmealList.isEmpty()){
				return new ArrayList<>();
			}
			
		}
		
		return setmealList;
	}
	
	/**
	 * 获取分院的基础信息	TW_BRANCH + esid
	 * @param keyName
	 * @return
	 */
	public HashMap<String, Object> getBranchByEsid(String keyName){
		HashMap<String,Object> branchMap = (HashMap<String, Object>) mcc.get(keyName);
		
		if(branchMap == null || branchMap.isEmpty()){
			String esid = keyName.replaceAll(MemcachedConstant.TW_BRANCH,"").trim();
			//缓存这个分院的基础信息
			branchMap = branchCache.cacheBranchInfoByEsid(esid);
			
			if(branchMap == null || branchMap.isEmpty()){
				return new HashMap<String, Object>();
			}
			
		}
		
		return branchMap;
	}
	
	/**
	 * 获取分院的环境图片	TW_ENVIRONMENTBYBRANCH + esid
	 * @param keyName
	 * @return
	 */
	public List<HashMap<String, Object>> getEnvironmentByEsid(String keyName){
		List<HashMap<String, Object>> environmentList = (List<HashMap<String, Object>>) mcc.get(keyName);
		
		if(environmentList == null || environmentList.isEmpty()){
			String esid = keyName.replaceAll(MemcachedConstant.TW_ENVIRONMENTBYBRANCH,"").trim();
			//缓存这个分院的环境图片
			environmentList = branchCache.cacheEnvironmentByEsid(esid);
			
			if(environmentList == null || environmentList.isEmpty()){
				return new ArrayList<>();
			}
			
		}
		
		return environmentList;
	}
	
	/**
	 * 获取商品所属分院   	TW_BRANCHBYPRODUCT + pid
	 * @param pid
	 * @return
	 */
	public List<String> getBranchByPid(String keyName){
		List<String> branchList = (List<String>) mcc.get(keyName);
		
		if(branchList == null || branchList.isEmpty()){
			String pid = keyName.replaceAll(MemcachedConstant.TW_BRANCHBYPRODUCT,"").trim();
			//缓存这个分院的环境图片
			branchList = productCache.cacheProductBranchByPid(pid);
			
			if(branchList == null || branchList.isEmpty()){
				return new ArrayList<>();
			}
			
		}
		
		return branchList;
		
	}
	
}

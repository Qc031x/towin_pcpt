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

/**
 * 分院缓存
 * @author kangliangyu
 *
 */
@SuppressWarnings("unchecked")
@Component
public class BranchCache {
	
	private static Logger logger = Logger.getLogger(BranchCache.class.getName());
	
	@Autowired
	private MemCachedClient  mcc;
	
	@Autowired
	private CacheService cacheService;

	public void branchCacheInitialization(){
		logger.info("分院缓存加载beg");
		
		//分院基础信息缓存
		this.cacheBranchInfo();
		//分院的环境图缓存
		this.cacheEnvironmentByBranch();
		
		logger.info("分院缓存加载end");
	}
	
	/**
	 * 分院基础信息缓存
	 */
	public void cacheBranchInfo(){
		logger.info("分院基础信息缓存加载beg");
		
		List<HashMap<String, Object>> branchList = cacheService.findValidBranch();
		if(branchList == null || branchList.isEmpty()){
			logger.info("分院基础信息没有数据");
			return;
		}
		
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		String esid;
		int size = branchList.size();
		for(int i = 0; i < size; i++){
			esid = memcachedUtil.getString(branchList.get(i).get("ESID"));
			
			if("".equals(esid))continue;
			
			mcc.set(MemcachedConstant.TW_BRANCH+esid, branchList.get(i));
		}
		
		logger.info("分院基础信息缓存加载end");
		
		/*for(int i = 0; i < size; i++){
			esid = memcachedUtil.getString(branchList.get(i).get("ESID"));
			
			if("".equals(esid))continue;
			
			System.err.println(esid+":"+mcc.get(MemcachedConstant.TW_BRANCH+esid));
		}*/
		
	}
	
	/**
	 * 分院的环境图片缓存
	 */
	public void cacheEnvironmentByBranch(){
		logger.info("分院环境图片信息缓存加载beg");
		
		List<HashMap<String, Object>> environmentImgList = cacheService.findEnvironmentImgByBranch();
		if(environmentImgList == null || environmentImgList.isEmpty()){
			logger.info("分院环境图片信息没有数据");
			return;
		}
		
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		List<HashMap<String,Object>> list;
		HashMap<String,Object> map = new HashMap<String, Object>();
		String esid;
		int size = environmentImgList.size();
		
		for(int i = 0; i < size; i++){
			esid = memcachedUtil.getString(environmentImgList.get(i).get("ESID"));
			
			if("".equals(esid))continue;
			
			list = new ArrayList<>();
			
			//判断map是否已经存在这个esid的属性，如果存在则合并
			if(map.containsKey(esid)){
				list = (List<HashMap<String, Object>>) map.get(esid);
			}
			
			list.add(environmentImgList.get(i));
			map.put(esid,list);
		}
		
		for (Entry<String, Object> entry : map.entrySet()) {       
		    mcc.set(MemcachedConstant.TW_ENVIRONMENTBYBRANCH+entry.getKey(), entry.getValue());
		} 
		
		logger.info("分院环境图片信息缓存加载end");
		
		/*for (Entry<String, Object> entry : map.entrySet()) {  
			System.err.println(entry.getKey()+":"+mcc.get(MemcachedConstant.TW_ENVIRONMENTBYBRANCH+entry.getKey()));
		}*/ 
		
	}
	
	/**
	 * 单个分院基础信息缓存
	 * @param esid
	 */
	public HashMap<String, Object> cacheBranchInfoByEsid(String esid){
		logger.info("esid为"+esid+"的分院基础信息缓存加载beg");
		
		HashMap<String, Object> branchMap = cacheService.findValidBranchByEsid(esid);
		if(branchMap == null || branchMap.isEmpty()){
			logger.info("esid为"+esid+"的分院基础信息没有数据");
			return null;
		}
		
		mcc.set(MemcachedConstant.TW_BRANCH+esid, branchMap);
		
		logger.info("esid为"+esid+"的分院基础信息缓存加载end");
		
		return branchMap;
	}
	
	/**
	 * 单个分院的环境图片缓存
	 * @param esid
	 */
	public List<HashMap<String, Object>> cacheEnvironmentByEsid(String esid){
		logger.info("esid为"+esid+"的分院环境图片信息缓存加载beg");
		
		List<HashMap<String, Object>> environmentImgList = cacheService.findEnvironmentImgByEsid(esid);
		if(environmentImgList == null || environmentImgList.isEmpty()){
			logger.info("esid为"+esid+"的分院环境图片信息没有数据");
			return null;
		}
		
		mcc.set(MemcachedConstant.TW_ENVIRONMENTBYBRANCH+esid, environmentImgList);
		
		logger.info("esid为"+esid+"的分院环境图片信息缓存加载end");
		
		return environmentImgList;
	}
	
}

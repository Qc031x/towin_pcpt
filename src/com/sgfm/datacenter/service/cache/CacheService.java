package com.sgfm.datacenter.service.cache;

import java.util.HashMap;
import java.util.List;

/**
 * 缓存Service
 * @author kangliangyu
 *
 */
public interface CacheService {
	
	/**
	 * 获取商品类型(年龄和类型)
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String,Object>> findProductTypeList();
	
	/**
	 * 获取分院类型
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findBranchTypeList();
	
	/**
	 * 获取有分院的城市下的区域
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findValidArea();
	
	/**
	 * 獲取有分院的城市
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findValidCity();
	
	/**
	 * 获取有分院的城市下的品牌
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findBrandByCity();
	
	/**
	 * 获取审核并上架的商品信息
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findValidProduct();
	
	/**
	 * 获取每个商品的类型
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findTypeByProduct();
	
	/**
	 * 获取每个商品的体检卡图片
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findCardImgByProduct();
	
	/**
	 * 获取每个商品体检项目
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findSetmealByProduct();
	
	/**
	 * 获取已审核并合作的分院信息
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findValidBranch();
	
	/**
	 * 获取分院的环境图片信息
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findEnvironmentImgByBranch();
	
	/**
	 * 获取有分院的城市下的区域  单个城市下的区域
	 * @param city
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findValidAreaByEsid(String city);
	
	/**
	 * 获取有分院的城市下的品牌  单个城市下的品牌
	 * @param city
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findBrandByCityId(String city);
	
	/**
	 * 获取已审核并上架的商品信息 单个商品信息
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	public HashMap<String, Object> findValidProductByPid(String pid);
	
	/**
	 * 获取单个商品的类型
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findTypeByPId(String pid);
	
	/**
	 * 获取单个商品的体检卡图片信息 
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	public HashMap<String, Object> findCardImgByPId(String pid);
	
	/**
	 * 获取单个商品的体检项目信息
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findSetmealByPId(String pid);
	
	/**
	 * 获取已审核并合作的分院信息 单个分院
	 * @param esid
	 * @return
	 * @author kangliangyu
	 */
	public HashMap<String, Object> findValidBranchByEsid(String esid);
	
	/**
	 * 获取单个分院的环境图片信息
	 * @param esid
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findEnvironmentImgByEsid(String esid);
	
	/**
	 * 获取商品的分院信息
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findBranchByProduct();
	
	/**
	 * 获取单个商品的分院信息
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	public List<String> findBranchByPid(String pid);
	
}

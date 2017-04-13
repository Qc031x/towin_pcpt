package com.sgfm.datacenter.dao.cache;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sgfm.base.dao.BaseDao;

@SuppressWarnings("unchecked")
@Repository
public class CacheDao extends BaseDao{
	
	/**
	 * 获取商品类型(年龄和类型)
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findProductTypeList() {
		return getSqlMapClientTemplate().queryForList("CacheMap.findProductTypeList");
	}
	
	/**
	 * 获取分院类型
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findBranchTypeList() {
		return getSqlMapClientTemplate().queryForList("CacheMap.findBranchTypeList");
	}
	
	/**
	 * 获取有分院的城市下的区域
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findValidArea() {
		return getSqlMapClientTemplate().queryForList("CacheMap.findValidArea");
	} 
	
	/**
	 * 获取有分院的城市
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findValidCity(){
		return getSqlMapClientTemplate().queryForList("CacheMap.findValidCity");
	}
	
	/**
	 * 获取有分院的城市下的品牌
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findBrandByCity(){
		return getSqlMapClientTemplate().queryForList("CacheMap.findBrandByCity");
	}

	/**
	 * 获取审核并上架的商品信息
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findValidProduct(){
		return getSqlMapClientTemplate().queryForList("CacheMap.findValidProduct");
	}
	
	/**
	 * 获取每个商品的类型
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findTypeByProduct(){
		return getSqlMapClientTemplate().queryForList("CacheMap.findTypeByProduct");
	}
	
	/**
	 * 获取每个商品的体检卡图片
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findCardImgByProduct(){
		return getSqlMapClientTemplate().queryForList("CacheMap.findCardImgByProduct");
	}
	
	/**
	 * 获取每个商品体检项目
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findSetmealByProduct(){
		return getSqlMapClientTemplate().queryForList("CacheMap.findSetmealByProduct");
	}
	
	/**
	 * 获取已审核并合作的分院信息
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findValidBranch(){
		return getSqlMapClientTemplate().queryForList("CacheMap.findValidBranch");
	}
	
	/**
	 * 获取分院的环境图片信息
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findEnvironmentImgByBranch(){
		return getSqlMapClientTemplate().queryForList("CacheMap.findEnvironmentImgByBranch");
	}
	
	/**
	 * 获取有分院的城市下的区域  单个城市下的区域
	 * @param city
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findValidAreaByEsid(String city){
		return getSqlMapClientTemplate().queryForList("CacheMap.findValidAreaByEsid", city);
	}
	
	/**
	 * 获取有分院的城市下的品牌  单个城市下的品牌
	 * @param city
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findBrandByCityId(String city){
		return getSqlMapClientTemplate().queryForList("CacheMap.findBrandByCityId", city);
	}
	
	/**
	 * 获取已审核并上架的商品信息 单个商品信息
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	public HashMap<String, Object> findValidProductByPid(String pid){
		return (HashMap<String, Object>) getSqlMapClientTemplate().queryForObject("CacheMap.findValidProductByPid", pid);
	}
	
	/**
	 * 获取单个商品的类型
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findTypeByPId(String pid){
		return  getSqlMapClientTemplate().queryForList("CacheMap.findTypeByPId", pid);
	}
	
	/**
	 * 获取单个商品的体检卡图片信息 
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	public HashMap<String, Object> findCardImgByPId(String pid){
		return (HashMap<String, Object>) getSqlMapClientTemplate().queryForObject("CacheMap.findCardImgByPId", pid);
	}
	
	/**
	 * 获取单个商品的体检项目信息
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findSetmealByPId(String pid){
		return  getSqlMapClientTemplate().queryForList("CacheMap.findSetmealByPId", pid);
	}
	
	/**
	 * 获取已审核并合作的分院信息 单个分院
	 * @param esid
	 * @return
	 * @author kangliangyu
	 */
	public HashMap<String, Object> findValidBranchByEsid(String esid){
		return (HashMap<String, Object>) getSqlMapClientTemplate().queryForObject("CacheMap.findValidBranchByEsid", esid);
	}
	
	/**
	 * 获取单个分院的环境图片信息
	 * @param esid
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findEnvironmentImgByEsid(String esid){
		return  getSqlMapClientTemplate().queryForList("CacheMap.findEnvironmentImgByEsid", esid);
	}
	
	/**
	 * 获取商品的分院信息
	 * @return
	 * @author kangliangyu
	 */
	public List<HashMap<String, Object>> findBranchByProduct(){
		return  getSqlMapClientTemplate().queryForList("CacheMap.findBranchByProduct");
	}
	
	/**
	 * 获取单个商品的分院信息
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	public List<String> findBranchByPid(String pid){
		return  getSqlMapClientTemplate().queryForList("CacheMap.findBranchByPid", pid);
	}
	
}

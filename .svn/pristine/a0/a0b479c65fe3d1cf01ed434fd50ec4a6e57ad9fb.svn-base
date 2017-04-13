package com.sgfm.datacenter.service.cache.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgfm.datacenter.dao.cache.CacheDao;
import com.sgfm.datacenter.service.cache.CacheService;

/**
 * 缓存ServiceImpl
 * @author kangliangyu
 *
 */
@Service
public class CacheServiceImpl implements CacheService {
	
	@Autowired
	private CacheDao cacheDao;
	

	/**
	 * 获取商品类型(年龄和类型)
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findProductTypeList() {
		return cacheDao.findProductTypeList();
	}

	/**
	 * 获取分院类型
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findBranchTypeList() {
		return cacheDao.findBranchTypeList();
	}

	/**
	 * 获取有分院的城市下的区域
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findValidArea() {
		return cacheDao.findValidArea();
	}

	/**
	 * 獲取有分院的城市
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findValidCity() {
		return cacheDao.findValidCity();
	}

	/**
	 * 获取有分院的城市下的品牌
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findBrandByCity() {
		return cacheDao.findBrandByCity();
	}

	/**
	 * 获取审核并上架的商品信息
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findValidProduct() {
		return cacheDao.findValidProduct();
	}

	/**
	 * 获取每个商品的类型
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findTypeByProduct() {
		return cacheDao.findTypeByProduct();
	}

	/**
	 * 获取每个商品的体检卡图片
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findCardImgByProduct() {
		return cacheDao.findCardImgByProduct();
	}

	/**
	 * 获取每个商品体检项目
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findSetmealByProduct(){
		return cacheDao.findSetmealByProduct();
	}
	
	/**
	 * 获取已审核并合作的分院信息
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findValidBranch(){
		return cacheDao.findValidBranch();
	}
	
	/**
	 * 获取分院的环境图片信息
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findEnvironmentImgByBranch(){
		return cacheDao.findEnvironmentImgByBranch();
	}
	
	/**
	 * 获取有分院的城市下的区域  单个城市下的区域
	 * @param city
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findValidAreaByEsid(String city){
		return cacheDao.findValidAreaByEsid(city);
	}
	
	/**
	 * 获取有分院的城市下的品牌  单个城市下的品牌
	 * @param city
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findBrandByCityId(String city){
		return cacheDao.findBrandByCityId(city);
	}
	
	/**
	 * 获取已审核并上架的商品信息 单个商品信息
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public HashMap<String, Object> findValidProductByPid(String pid){
		return cacheDao.findValidProductByPid(pid);
	}
	
	/**
	 * 获取单个商品的类型
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findTypeByPId(String pid){
		return cacheDao.findTypeByPId(pid);
	}
	
	/**
	 * 获取单个商品的体检卡图片信息 
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public HashMap<String, Object> findCardImgByPId(String pid){
		return cacheDao.findCardImgByPId(pid);
	}
	
	/**
	 * 获取单个商品的体检项目信息
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findSetmealByPId(String pid){
		return cacheDao.findSetmealByPId(pid);
	}
	
	/**
	 * 获取已审核并合作的分院信息 单个分院
	 * @param esid
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public HashMap<String, Object> findValidBranchByEsid(String esid){
		return cacheDao.findValidBranchByEsid(esid);
	}
	
	/**
	 * 获取单个分院的环境图片信息
	 * @param esid
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findEnvironmentImgByEsid(String esid){
		return cacheDao.findEnvironmentImgByEsid(esid);
	}
	
	/**
	 * 获取商品的分院信息
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<HashMap<String, Object>> findBranchByProduct(){
		return cacheDao.findBranchByProduct();
	}
	
	/**
	 * 获取单个商品的分院信息
	 * @param pid
	 * @return
	 * @author kangliangyu
	 */
	@Override
	public List<String> findBranchByPid(String pid){
		return cacheDao.findBranchByPid(pid);
	}
}

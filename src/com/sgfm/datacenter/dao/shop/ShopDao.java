package com.sgfm.datacenter.dao.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sgfm.base.dao.BaseDao;


@Repository
@SuppressWarnings("unchecked")
public class ShopDao extends BaseDao{
    
	public List<HashMap<String,Object>> findAllCountryByCity(String city){
		return super.getSqlMapClientTemplate().queryForList("ShopMap.findAllCountryByCity",city);
	}
	
	public List<HashMap<String,Object>> findAllHospitalType(){
		return super.getSqlMapClientTemplate().queryForList("ShopMap.findAllHospitalType");
	}
	
	public List<String> findAllEmpPoint(String cityId) {
        return super.getSqlMapClientTemplate().queryForList("ShopMap.findAllEmpPoint", cityId);
    }
	
	
	public int countEntityShopList(HashMap<String, Object> map) {
        return (Integer) super.getSqlMapClientTemplate().queryForObject("ShopMap.countEntityShopList", map);
    }
	
	
	public List<HashMap<String,Object>> findEntityShopList(HashMap<String, Object> map){
		return super.getSqlMapClientTemplate().queryForList("ShopMap.findEntityShopList",map);
	}
	
	public HashMap<String,Object> findShopInfoByEsid(String esid){
		return (HashMap<String,Object>)super.getSqlMapClientTemplate().queryForObject("ShopMap.findShopInfoByEsid",esid);
	}
	
	public int updShopPoint(Map<String, String> map) {
	    return super.getSqlMapClientTemplate().update("ShopMap.updShopPoint", map);
	}
	
	public List<HashMap<String,Object>> findHospitalTypeByEsid(String esid){
		return super.getSqlMapClientTemplate().queryForList("ShopMap.findHospitalTypeByEsid",esid);
	}
	
	public List<HashMap<String,Object>> findProductTypeByEsid(String esid){
		return super.getSqlMapClientTemplate().queryForList("ShopMap.findProductTypeByEsid",esid);
	}
	
	public int countMECProductByCondition(HashMap<String, Object> condition){
		return (int) super.getSqlMapClientTemplate().queryForObject("ShopMap.countMECProductByCondition", condition);
	}
	
	public List<HashMap<String,Object>> findMECProductListByCondition(HashMap<String, Object> condition){
		return super.getSqlMapClientTemplate().queryForList("ShopMap.findMECProductListByCondition",condition);
	}
}

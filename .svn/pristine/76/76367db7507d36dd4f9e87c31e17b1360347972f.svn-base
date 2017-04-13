package com.sgfm.datacenter.service.card.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgfm.base.util.Pager;
import com.sgfm.base.util.StringUtil;
import com.sgfm.datacenter.dao.card.CardDao;
import com.sgfm.datacenter.dao.product.ProductDao;
import com.sgfm.datacenter.entity.CgVariable;
import com.sgfm.datacenter.memcached.CacheHashMap;
import com.sgfm.datacenter.memcached.MemcachedConstant;
import com.sgfm.datacenter.service.card.CardService;
import com.sgfm.datacenter.service.shop.ShopService;
import com.sgfm.datacenter.util.CommUtil;
import com.sgfm.datacenter.util.LngAndLatUtil;

@Service
public class CardServiceImpl implements CardService{
	@Autowired
	private CardDao cardtDao;
	@Autowired
	private ShopService shopService;
	@Autowired
	private CacheHashMap cacheMap;
	/**
     * 分页方法整合
     * 
     * @param count
     *            数量
     * @param pageNo
     *            第几页
     * @param map
     *            查询条件集合
     * @param pagesize
     *            单页的数量
     * @return
     */
    public Pager setPager(int count, String pageNo, HashMap<String, Object> map, int pagesize) {
        Pager pager=new Pager();
        if(pageNo==null)pageNo="0";
        pager.setPageNo(pageNo);
        pager.setPageSize(pagesize);
        pager.setTotalRows(map, count);
        return pager;
    }
	
	public HashMap<String, Object> findCardList(HashMap<String, Object> map) {
		HashMap<String, Object> infoMap = new HashMap<String, Object>();
		int count= this.findCardListCount(map);
		String pageNo = map.get("pageNo") == null ? null : map.get("pageNo").toString();
		Pager pager = setPager(count, pageNo, map, 20);
		if(count>0){
	       	List<HashMap<String,Object>> productList = this.findCardListByCondition(map);
	       	for(int j = 0,size = productList.size();j<size;j++){
	       		String pid = productList.get(j).get("PID").toString();
	       		productList.get(j).putAll(cacheMap.getProductByPid(MemcachedConstant.TW_PRODUCT+pid));
	       		productList.get(j).putAll(cacheMap.getProductCardImgByPid(MemcachedConstant.TW_PRODUCT_CARDIMG+pid));
	       	}
	       	pager.setList(productList);
	        //infoMap.put("pages", pager);
	       	//infoMap.put("productList", productList);
     	}
		 // 套餐性别和价格
		//infoMap.put("productTypeList", cacheMap.getProductTypeList(MemcachedConstant.TW_PRODUCT_TYPE));
		//infoMap.put("productAgeList", cacheMap.getProductTypeList(MemcachedConstant.TW_PRODUCT_AGE));
		//infoMap.put("brandList", cacheMap.getBrandByCity(MemcachedConstant.TW_BRAND_CITY+map.get("city").toString()));
		//infoMap.put("goodProduct", CommHashMap.getHashMapGoodProductList(MemcachedConstant.YY_GOODPRODUCT+map.get("city").toString()));
		infoMap.put("pages", pager);
		return infoMap;
	}
	
	public List<HashMap<String,Object>> findCardListByCondition(HashMap<String,Object> map){
    	return cardtDao.findCardListByCondition(map);
    }
	
	public int findCardListCount(HashMap<String,Object> map){
    	if(map.get("brand_Id")==null || map.get("brand_Id").equals("0")){
			map.put("brand_Id", null);
		}
		
		if(map.get("categoryidOne")==null || map.get("categoryidOne").equals("0")){
			map.put("categoryidOne", null);
		}
		if(map.get("categoryidTwo")==null || map.get("categoryidTwo").equals("0")){
			map.put("categoryidTwo", null);
		}
		if(map.get("price")==null || map.get("price").equals("0")){
			map.put("price", null);
		}
		
		
		if(map.get("startPrice") != null && !map.get("startPrice").equals("") && map.get("endPrice") != null && !map.get("endPrice").equals("")){
			if(Integer.parseInt(map.get("startPrice").toString())>=0 && Integer.parseInt(map.get("endPrice").toString())>0){
				String sql = " and (nvl(price.ct4,0) >= "+map.get("startPrice")+" and nvl(price.ct4,0) <= "+map.get("endPrice")+")";
                map.put("priceCustom", sql);
			}
		}else{
			if(map.get("startPrice") != null && !map.get("startPrice").equals("")){

				String sql = " and (nvl(price.ct4,0) >= " +map.get("startPrice")+")";
                map.put("priceCustom", sql);
			}
			if(map.get("endPrice") != null && !map.get("endPrice").equals("")){
                String sql = " and (nvl(price.ct4,0) <= " +map.get("endPrice")+")";
                map.put("priceCustom", sql);
			}			
		}				
		return cardtDao.findCardListCount(map);
    }
	@Override
	public List<HashMap<String, Object>> getProductTypeList() {
		return cardtDao.getProductTypeList();
	}
	
}

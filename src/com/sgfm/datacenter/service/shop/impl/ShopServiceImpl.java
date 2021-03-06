package com.sgfm.datacenter.service.shop.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.sgfm.base.util.Pager;
import com.sgfm.base.util.StringUtil;
import com.sgfm.datacenter.dao.shop.ShopDao;
import com.sgfm.datacenter.entity.CgVariable;
import com.sgfm.datacenter.memcached.CacheHashMap;
import com.sgfm.datacenter.memcached.MemcachedConstant;
import com.sgfm.datacenter.service.shop.ShopService;
import com.sgfm.datacenter.util.AppContext;
import com.sgfm.datacenter.util.LngAndLatUtil;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDao shopDao;

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
	
	@Override
	public HashMap<String, Object> toShopCenter(HashMap<String, Object> param) {
	
		HashMap<String, Object> infoMap = new HashMap<String, Object>();

        infoMap.put("countryList", cacheMap.getAreaByCity(MemcachedConstant.TW_CITY_AREA+param.get("city").toString()) );
        infoMap.put("branchTypeList",cacheMap.getBranchTypeList(MemcachedConstant.TW_BRANCH_TYPE));

        return infoMap;
	}

	public List<HashMap<String,Object>> findEntityShopList(HashMap<String,Object> map){
		return shopDao.findEntityShopList(map);
	}
	
	@Override
	public HashMap<String, Object> getEntityShopList(HashMap<String, Object> param) {
		HashMap<String, Object> infoMap = new HashMap<String, Object>();
		int count = this.countEntityShopList(param);
		String pageNo = param.get("pageNo") == null ? null : param.get("pageNo").toString();
		  if (count > 0) {
	            findAllEmpPoint(param.get("city").toString());
	            Pager pager = setPager(count, pageNo, param, 16);
	            List<HashMap<String, Object>> branchList = this.findEntityShopList(param);
	            for (int j = 0; j < branchList.size(); j++) {
	                String esid = branchList.get(j).get("ESID").toString();
	                String counts = branchList.get(j).get("COUNTS").toString();
	                HashMap<String,Object> shopInfo = cacheMap.getBranchByEsid(MemcachedConstant.TW_BRANCH+esid);
	                branchList.get(j).putAll(shopInfo);
	                branchList.get(j).put("COUNTS", counts);
	            }
	            infoMap.put("list", branchList);
	            infoMap.put("pages", pager);
		  }
		return infoMap;
	}
	
	
	 public int countEntityShopList(HashMap<String, Object> map){

	        if (map.get("categoryid") == null || map.get("categoryid").equals("0")) {
	            map.put("categoryid", null);
	        }

	        if (map.get("county") == null || map.get("county").equals("0")) {
	            map.put("county", null);
	        }
	        if (map.get("brandId") == null || map.get("brandId").equals("0")) {
	            map.put("brandId", null);
	        }
	        return shopDao.countEntityShopList(map);
	    }
	
	 public List<Map<String, String>> findAllEmpPoint(String cityId) {
	        List<String> esids = shopDao.findAllEmpPoint(cityId);
	        return updShopPoint(esids);
	 }
	
	 
	 public List<Map<String, String>> updShopPoint(List<String> esids) {
	        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	        for (String esid : esids) {
	            // 获取机构基础信息
	            Map<String, String> ponitMap = new HashMap<String, String>();
	            HashMap<String, Object> map = shopDao.findShopInfoByEsid(esid);
	            if (StringUtil.isBlank(map.get("LGT"))) {  // 缓存机构信息中没有坐标信息
	                String address = map.get("CITYNAME").toString() +(StringUtil.isBlank(map.get("COUNTYNAME"))?"":map.get("COUNTYNAME").toString())+(StringUtil.isBlank(map.get("ADDRESS"))?"":map.get("ADDRESS").toString());
	                // 根据详细地址获取对应坐标地址
	                ponitMap = LngAndLatUtil.getLngAndLat(address);
	                ponitMap.put("ESID", esid);
	                // 更新数据库
	                shopDao.updShopPoint(ponitMap);
	                // 更新缓存
	                map.put("LAG", ponitMap.get("LAG"));
	                map.put("LAT", ponitMap.get("LAT"));
	                //memcachedManager.IncrementalEntityShop(map);
	            } else {
	                ponitMap.put("ESID", esid);
	                ponitMap.put("LAG", map.get("LGT").toString());
	                ponitMap.put("LAT", map.get("LAT").toString());
	            }
	            list.add(ponitMap);
	        }
	        return list;
	    }
	
	 public HashMap<String, Object> findMECdetail(HashMap<String, Object> map) {
	        HashMap<String, Object> info = new HashMap<String, Object>();
	        String esid = map.get("esid").toString();
	        Map<String, Object> medicalinStitution = new HashMap<String, Object>(); // 存放体检中心的相关信息
	        medicalinStitution.putAll(cacheMap.getBranchByEsid(MemcachedConstant.TW_BRANCH + esid));
	        
	        Double lat1 = 0d, lng1 = 0d, lat2 = 0d, lng2 = 0d;

	        if (StringUtil.isBlank(medicalinStitution.get("LAG"))) {  // 缓存机构信息中没有坐标信息
	            List<String> esids = Arrays.asList(esid);;
	            List<Map<String, String>> list = updShopPoint(esids);   // 获取坐标信息并更新
	            lat1 = Double.parseDouble(list.get(0).get("LAT"));
	            lng1 = Double.parseDouble(list.get(0).get("LAG"));
	        } else {
	            lat1 = Double.parseDouble(medicalinStitution.get("LAT").toString());
	            lng1 = Double.parseDouble(medicalinStitution.get("LAG").toString());
	        }
	        lat2 = Double.parseDouble(map.get("LOCATION_POINT_LAT").toString());
	        lng2 = Double.parseDouble(map.get("LOCATION_POINT_LNG").toString());
	        // 计算距离
	        medicalinStitution.put("DISTANCE", LngAndLatUtil.GetDistance(lat1, lng1, lat2, lng2));
	        medicalinStitution.put("LOCATION_POINT_LAT", lat2);
	        medicalinStitution.put("LOCATION_POINT_LNG", lng2);

	        info.put("mecinfo", medicalinStitution);
	        info.put("productTypeList", shopDao.findProductTypeByEsid(esid));

	        return info;
	    }
	 
	 public HashMap<String, Object> findMECtaocan(HashMap<String, Object> map){
	        HashMap<String, Object> info =new HashMap<String, Object>();
	        CgVariable cgVariable=(CgVariable) map.get("cgVariable");
	        // 体检套餐
	        HashMap<String, Object> condition = new HashMap<String, Object>(); // 存放体检套餐筛选条件
	        condition.put("esid", cgVariable.getEsid());
	        condition.put("key", (cgVariable.getCategoryidOne()==null||cgVariable.getCategoryidOne().equals("0"))? null:cgVariable.getCategoryidOne());
	        int count= shopDao.countMECProductByCondition(condition);
	        
	        Pager pager = this.setPager(count, (map.get("page") == null) ? "0" : map.get("page").toString(), condition, 16);
	        
	        if(count == 0){
	        	pager.setList(new ArrayList<>());
		        info.put("pages", pager);
		        return info;
	        }
	        
	        List<HashMap<String, Object>> productList = shopDao.findMECProductListByCondition(condition);
	        String pid;
	        for(int j = 0,size=productList.size();j<size;j++){
	            pid = productList.get(j).get("PID").toString();      
	            
	            if("".equals(pid))continue;
	            
	            productList.get(j).putAll(cacheMap.getProductByPid(MemcachedConstant.TW_PRODUCT+pid));
	         }
	        pager.setList(productList);
	        info.put("pages", pager);
	        return info;
	        
	    }
}

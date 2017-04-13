package com.sgfm.datacenter.service.product.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgfm.base.util.Pager;
import com.sgfm.base.util.StringUtil;
import com.sgfm.datacenter.dao.cache.CacheDao;
import com.sgfm.datacenter.dao.product.ProductDao;
import com.sgfm.datacenter.dao.user.UserDao;
import com.sgfm.datacenter.entity.CgVariable;
import com.sgfm.datacenter.entity.TArea;
import com.sgfm.datacenter.memcached.CacheHashMap;
import com.sgfm.datacenter.memcached.MemcachedConstant;
import com.sgfm.datacenter.service.product.ProductService;
import com.sgfm.datacenter.service.shop.ShopService;
import com.sgfm.datacenter.util.CommUtil;
import com.sgfm.datacenter.util.LngAndLatUtil;
import com.sgfm.datacenter.util.SysUtils;

import net.sf.json.JSONArray;

@Service
public class ProductServiceImpl implements ProductService{
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ShopService shopService;
	@Autowired
	private CacheHashMap cacheMap;
	@Autowired
	private CacheDao cacheDao;
	@Autowired
	private UserDao userDao;
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
	public List<HashMap<String, Object>> findAllCity() {
		return productDao.findAllCity();
	}

	@Override
	public HashMap<String, Object> findProductList(HashMap<String, Object> map) {
		HashMap<String, Object> infoMap = new HashMap<String, Object>();
		int count= this.countProductByCondition(map);
		logger.info("套餐列表页数据数量:------------" + count);
		String pageNo = map.get("pageNo") == null ? null : map.get("pageNo").toString();
		Pager pager = setPager(count, pageNo, map, 16);
		if(count>0){
	       	List<HashMap<String,Object>> productList = this.findProductListByCondition(map);
	       	logger.info("套餐列表页数据1:------------" + productList);
	       	for(int j = 0,size = productList.size();j<size;j++){
	       		String pid = productList.get(j).get("PID").toString();
	       		productList.get(j).putAll(cacheMap.getProductByPid(MemcachedConstant.TW_PRODUCT+pid));
            }
	       	logger.info("套餐列表页数据2:------------" + productList);
	       	pager.setList(productList);
	        //infoMap.put("pages", pager);
	       	infoMap.put("productList", productList);
     	}
		 // 套餐性别和价格
		//infoMap.put("productTypeList", cacheMap.getProductTypeList(MemcachedConstant.TW_PRODUCT_TYPE));
		//infoMap.put("productAgeList", cacheMap.getProductTypeList(MemcachedConstant.TW_PRODUCT_AGE));
		//infoMap.put("brandList", cacheMap.getBrandByCity(MemcachedConstant.TW_BRAND_CITY+map.get("city").toString()));
		//infoMap.put("goodProduct", CommHashMap.getHashMapGoodProductList(MemcachedConstant.YY_GOODPRODUCT+map.get("city").toString()));
		infoMap.put("totalCount", count);
    	infoMap.put("totalPage", pager.getTotalPages());
    	infoMap.put("currentPage", pager.getPageNo());
		return infoMap;
	}
	
	public List<HashMap<String,Object>> findProductListByCondition(HashMap<String,Object> map){
    	return productDao.findProductListByCondition(map);
    }
	
	public int countProductByCondition(HashMap<String,Object> map){
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
		return productDao.countProductByCondition(map);
    }
	
	public List<HashMap<String, Object>> findProductByCityAndPid(CgVariable cgVariable) {
		return productDao.findProductByCityAndPid(cgVariable);
	}
	
	public boolean findPageExist(CgVariable cg) {
		if (!StringUtil.isBlank(cg.getEsid())){
			return productDao.findEsidExist(cg) > 0;
		} else if (!StringUtil.isBlank(cg.getPid())){
			return productDao.findPidExist(cg.getPid()) > 0;
		} 
		return false;
	}
	
	public HashMap<String, Object> toProductDetail(HashMap<String,Object> map){
		String pid = map.get("pid").toString();
		HashMap<String, Object> infoMap = new HashMap<String, Object>();
		//套餐体检卡信息           秦光耀
		HashMap<String,Object> cardInfo=cacheMap.getProductCardImgByPid(MemcachedConstant.TW_PRODUCT_CARDIMG+pid);
        // 套餐基础数据和套餐类型
		HashMap<String,Object> baseInfoMap = cacheMap.getProductByPid(MemcachedConstant.TW_PRODUCT+pid);
		//销量
		int count = productDao.countSalesByPid(pid);
		baseInfoMap.put("COUNT",count);
		
        // 体检单项
		List<HashMap<String,Object>> singleProjectMap = cacheMap.getProductSetmealByPid(MemcachedConstant.TW_PRODUCT_SETMEAL+pid);
        // 套餐类型
		List<HashMap<String,Object>> productTypeList = cacheMap.getProductTypeByPid(MemcachedConstant.TW_TYPEBYPRODUCT+pid);
		//年龄类型
		List<HashMap<String,Object>> productAgeList = cacheMap.getProductTypeByPid(MemcachedConstant.TW_AGEBYPRODUCT+pid);
        // 促销信息
		//List<HashMap<String, Object>> promotions = CommHashMap.getHashMapPromotionList(MemcachedConstant.YY_PROMOTION + pid);
		//立减信息
		int coupon = 0;
//		String payMent = baseInfoMap.get("PAYMENT").toString();
//		if(!payMent.equals("1")){
//			HashMap<String,Object> couponMap = productDao.getCouponByPid(pid);
//			if(couponMap!=null && couponMap.size()>0){
//				baseInfoMap.putAll(couponMap);
//				coupon = 1;
//			}
//		}
		baseInfoMap.put("coupon", coupon);
		List<String>  esids = cacheMap.getBranchByPid(MemcachedConstant.TW_BRANCHBYPRODUCT+pid);
		List<HashMap<String,Object>> shopList = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < esids.size(); i++) {
			String esid =  esids.get(i);	
			HashMap<String, Object> branchInfo = cacheMap.getBranchByEsid(MemcachedConstant.TW_BRANCH+esid);
			shopList.add(branchInfo);
		}
		
		
		List<Map<String,Object>> rList= new  ArrayList<Map<String,Object>>();
        int attrFlag = 0; //如果套餐性别为男则讲只适合女性的单项去除 
		if(baseInfoMap.get("SEX").toString().equals("1")){
			attrFlag = 1;
			for (Map project : singleProjectMap) {					
				if("3".equals(project.get("SEX").toString()))
				{
					rList.add(project);
				}
			}
		}
		//如果套餐性别为已婚女或未婚女则讲只适合男性的单项去除 
		if(baseInfoMap.get("SEX").toString().equals("2")||baseInfoMap.get("SEX").toString().equals("4")){
			attrFlag = 2;
			for (Map project : singleProjectMap) {
				if("2".equals(project.get("SEX").toString()))
				{
					rList.add(project);
				}
			}
		}
		singleProjectMap.removeAll(rList);
		
		List<String> singleType = new ArrayList<String>();
		for (HashMap<String,Object> maps : singleProjectMap) {
			singleType.add(maps.get("TYPE_NAME").toString());
		}
		Collection<String> physicalType = new LinkedHashSet<String>(singleType);
		
		
		infoMap.put("baseInfoMap", baseInfoMap);
		infoMap.put("singleProjectMap", singleProjectMap);
		//infoMap.put("promotionMap", promotionMap);
		infoMap.put("productTypeList", productTypeList);
		infoMap.put("productAgeList", productAgeList);
		infoMap.put("singleType", physicalType);
		infoMap.put("attrFlag", attrFlag);
//		infoMap.put("shopMap", shopMap);
//		infoMap.put("promotions", promotions);
		infoMap.put("shopList", shopList);
//		infoMap.put("shopImgList", shopImgList);
		infoMap.put("cardInfo", cardInfo);
		return infoMap;
	}
	
	
	public List<HashMap<String,Object>> findAllMedicalByPid(Map<String, String> map){
		String pid = map.get("pid");
		String cityId = map.get("cityId");
		String cityName = map.get("cityName");
		map.clear();
		map.put("CITY", cityName);
		map.put("ID", cityId);
		List<String>  esids = cacheMap.getBranchByPid(MemcachedConstant.TW_BRANCHBYPRODUCT+pid);
		List<HashMap<String,Object>> shopList = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < esids.size(); i++) {
			String esid =  esids.get(i);	
			HashMap<String, Object> branchInfo = cacheMap.getBranchByEsid(MemcachedConstant.TW_BRANCH+esid);
			if(!SysUtils.isEmpty(branchInfo.get("CITYNAME"))){
			shopList.add(branchInfo);
			}
		}
		List<HashMap<String,Object>> medicals = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < shopList.size(); i++) {
			HashMap<String,Object> tempMap = new HashMap<String,Object>();
			tempMap.put("CITY", shopList.get(i).get("CITYNAME").toString());
			tempMap.put("ID", shopList.get(i).get("CITY").toString());
			tempMap.put("AREA_CODE",CommUtil.converterToFirstSpell(shopList.get(i).get("CITYNAME").toString()));
			if(shopList.get(i).get("CITYNAME").toString().equals(cityName)){
				medicals.add(0, tempMap);
			}else{
				medicals.add(tempMap);
			}
		}
		Collection<HashMap<String,Object>> collection = new LinkedHashSet<HashMap<String,Object>>(medicals);
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>(collection);
		return list;
	}
	
	
	/**
     * 找到单个城市商品挂靠所有门店
     */
    public List<Map> findMedicalList(Map<String, Object> map) {
        CgVariable cgVariable = (CgVariable) map.get("cgVariable");
        List<String>  esids = cacheMap.getBranchByPid(MemcachedConstant.TW_BRANCHBYPRODUCT+cgVariable.getPid());
		List<HashMap<String,Object>> shopList = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < esids.size(); i++) {
			String esid =  esids.get(i);	
			HashMap<String, Object> branchInfo = cacheMap.getBranchByEsid(MemcachedConstant.TW_BRANCH+esid);
			shopList.add(branchInfo);
		}
		List<Map> medicals = new ArrayList<Map>();
        boolean flag = false;
		for (int i = 0; i < shopList.size(); i++) {
			if(shopList.get(i).get("CITY") !=null && shopList.get(i).get("CITY").toString().equals(cgVariable.getId())){
                Double lat1 = 0d, lng1 = 0d, lat2 = 0d, lng2 = 0d;
                if (StringUtil.isBlank(shopList.get(i).get("LGT"))) {  // 列表缓存信息中没有坐标信息
                    List<Map<String, String>> list = shopService.updShopPoint(esids);   // 获取坐标信息
                    lat1 = Double.parseDouble(list.get(0).get("LAT"));
                    lng1 = Double.parseDouble(list.get(0).get("LGT"));
                    // 将坐标信息放入缓存查出的列表 后续更新列表缓存
                    shopList.get(i).put("LAT", lat1);
                    shopList.get(i).put("LGT", lng1);
                    flag = true;
                } else {
                    lat1 = Double.parseDouble(shopList.get(i).get("LAT").toString());
                    lng1 = Double.parseDouble(shopList.get(i).get("LGT").toString());
                }
                Double distance = null;
                if (null != map.get("LOCATION_POINT_LAT")) {
                lat2 = Double.parseDouble(map.get("LOCATION_POINT_LAT").toString());
                lng2 = Double.parseDouble(map.get("LOCATION_POINT_LNG").toString());
                    distance = LngAndLatUtil.GetDistance(lat1, lng1, lat2, lng2);
                }
                // 计算距离
                shopList.get(i).put("DISTANCE", distance);

				medicals.add(shopList.get(i));
			}
		}
//        if (flag)
//            memcachedManager.updHashMapBranchByPidList(cgVariable.getPid(), tempList);
		return medicals;
	}
    
    public HashMap<String, Object> findShopsByPidAndCity(HashMap<String, Object> map){
		String pid = map.get("pid").toString();
		String city = map.get("city").toString();
		List<String> list = cacheMap.getBranchByPid(MemcachedConstant.TW_BRANCHBYPRODUCT + pid);
		HashMap<String, Object> shop = new HashMap<String, Object>();
		HashMap<String, Object> address = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> shopDetail = cacheMap.getBranchByEsid(MemcachedConstant.TW_BRANCH + list.get(i));
			if(shopDetail.get("CITY").toString().equals(city)){
                shop.put(shopDetail.get("ESID").toString(), "【" + shopDetail.get("BNAME").toString() + "】" + shopDetail.get("ENAME").toString());
                address.put(shopDetail.get("ESID").toString(), shopDetail.get("PROVINCENAME").toString() + shopDetail.get("CITYNAME").toString() + "市" + shopDetail.get("COUNTYNAME").toString() + shopDetail.get("ADDRESS").toString());
			}
		}
		map.clear();
		map.put("shop", JSONArray.fromObject(shop).toString());
		map.put("address", JSONArray.fromObject(address).toString());
		return map;
	}
	
    
    public TArea findAreaById(int id){
    	return userDao.findAreaById(id);
	}
}

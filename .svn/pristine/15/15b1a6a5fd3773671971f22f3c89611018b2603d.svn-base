
package com.sgfm.datacenter.service.reservation.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgfm.base.util.StringUtil;
import com.sgfm.datacenter.dao.order.OrderDao;
import com.sgfm.datacenter.dao.product.RlDao;
import com.sgfm.datacenter.dao.reservation.ReservationDao;
import com.sgfm.datacenter.entity.SOrderLine;
import com.sgfm.datacenter.entity.SReservation;
import com.sgfm.datacenter.memcached.CacheHashMap;
import com.sgfm.datacenter.memcached.MemcachedConstant;
import com.sgfm.datacenter.service.reservation.ReservationService;
import com.sgfm.datacenter.util.SysUtils;

/** 
 * @author cxj
 * @date 2016-11-3 上午10:21:44
 */
@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDao reservationDao;
	
	@Autowired
	private RlDao rlDao;
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CacheHashMap cacheHashMap;
	
	@Override
	public HashMap<String, Object> findReservationInfoByMid(HashMap<String, Object> param) {
		HashMap<String,Object> info = new HashMap<String,Object>();
		List<HashMap<String,Object>> list = reservationDao.findReservationByMid(param);
		for(int i=0,size=list.size(); i < size; i++){
			HashMap<String, Object> baseInfo = cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT+list.get(i).get("PID").toString());
			if ("0".equals(baseInfo.get("PRODUCT_TYPE").toString())){
				baseInfo.put("IMG", cacheHashMap.getProductCardImgByPid(MemcachedConstant.TW_PRODUCT_CARDIMG + list.get(i).get("PID").toString()));
			}
			list.get(i).put("base", baseInfo);
		}
		info.put("list",list);
		return info;
	}
//
//
	@Override
	public HashMap<String, Object> findReservationInfo(Map<String, Object> param) {
		HashMap<String,Object> info = reservationDao.findReservationInfo(param);
		HashMap<String, Object> baseInfo = cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT+info.get("PID").toString());
		info.put("base", baseInfo);
		return info;
	}


//	@Override
//	public HashMap<String, Object> findOnlinePayReservationInfo(Map<String, Object> param) {
//		return reservationDao.findReservationInfo(param);
//	}
//
//
//	@Override
//	public HashMap<String, Object> findGroupbuyReservationInfo(Map<String, Object> param) {
//		return reservationDao.findGroupbuyReservationInfo(param);
//	}
//
//
//	@Override
//	public HashMap<String, Object> findShopPayReservationInfo(Map<String, Object> param) {
//		return reservationDao.findShopPayReservationInfo(param);
//	}
//
	@Override
	public Map<String, Object> getReservationInfoForUpdate(Map<String,Object> param) {
		Map<String,Object> info = new HashMap<String,Object>();
		List<String>  esids = cacheHashMap.getBranchByPid(MemcachedConstant.TW_BRANCHBYPRODUCT+param.get("pid"));
		List<HashMap<String,Object>> shopList = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < esids.size(); i++) {
			String esid =  esids.get(i);	
			HashMap<String, Object> branchInfo = cacheHashMap.getBranchByEsid(MemcachedConstant.TW_BRANCH+esid);
			shopList.add(branchInfo);
		}
		
		HashMap<String, Object> infoMap = linkAgeControl(shopList);

		return infoMap;
	}

	@Override
	public int updateCardReservation(SReservation sReservation) {
		int result = reservationDao.updateTreservation(sReservation);
		reservationDao.updateOrderItemStatus(sReservation);
//		if(("X").equals(sReservation.getCard().substring(0,1))){
//			reservationDao.updatePorderLineIsValid(sReservation);
//		}else{
//			reservationDao.updateGiftbookIsValid(sReservation);
//		}
		return result;
	}

//	@Override
//	public HashMap<String, Object> findSReservationByRid(String rid) {
//		return reservationDao.findSReservationByRid(rid);
//	}
//
//	
//	/**
//	 */
//	public int updateSreservation(SReservation sreservation){
//		return reservationDao.updateSreservation(sreservation);
//		
//	}
//
//	@Override
//	public int updateGroupbuyReservation(SGroupbuyReservation gReservation) {
//		return reservationDao.updateGroupbuyReservation(gReservation);
//	}
//	
//	
	public String checkCard(SReservation reservation){
		String result = "-999";
		
		HashMap<String, Object> infoMap = reservationDao.findVValidInfoByCardInfo(reservation);
		
		
		if(infoMap != null && infoMap.size() > 0){
			String active = infoMap.get("JH") == null ? "0" : infoMap.get("JH").toString();
			String valid = infoMap.get("IS_VALID") == null ? "0" : infoMap.get("IS_VALID").toString();
			String status = infoMap.get("STATUS") == null ? "0" : infoMap.get("STATUS").toString();
			String proType = infoMap.get("PRODUCT_TYPE") == null ? "1" : infoMap.get("PRODUCT_TYPE").toString().trim();
			String is_send = infoMap.get("IS_SEND") == null ? "1" : infoMap.get("IS_SEND").toString().trim();
			String count = infoMap.get("COUNT") == null ? "0" : infoMap.get("COUNT").toString();
			if(active.equals("1") && !is_send.equals("0")){
				if(valid.equals("0")){
					if((status.equals("2") && "0".equals(proType)) || proType.equals("1")){
						if(!count.equals("0")){
							result = "0" + infoMap.get("PID").toString();
						}else{
							result = "5";//未找到匹配门店
						}
					}else{
						result = "4";//商品下架
					}
				}else{
					result = "3";//已使用
				}
			}else{
				result = "2";//未激活
			}
		}else{
			result = "1";//卡号卡密错误
		}
		
		return result;
	}
	
	public HashMap<String, Object> toReFromUsercenter(HashMap<String, Object> map){
		SReservation reservation = (SReservation) map.get("reservation");
		String card = reservation.getCard();
		//String password = reservation.getPassword();
		String pid = String.valueOf(reservation.getPid());
		
		HashMap<String, Object> product = cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT+pid);
		List<String>  esids = cacheHashMap.getBranchByPid(MemcachedConstant.TW_BRANCHBYPRODUCT+pid);
		List<HashMap<String,Object>> shopList = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < esids.size(); i++) {
			String esid =  esids.get(i);	
			HashMap<String, Object> branchInfo = cacheHashMap.getBranchByEsid(MemcachedConstant.TW_BRANCH+esid);
			shopList.add(branchInfo);
		}
		
		HashMap<String, Object> infoMap = linkAgeControl(shopList);
		
		infoMap.put("type", card.length() < 14 ? "0" : "1");
		infoMap.put("product", product);
		return infoMap;
	}
	
	public HashMap<String, Object> linkAgeControl(List<HashMap<String, Object>> list){
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//城市map
		Map<String, String> areaMap = new LinkedHashMap<String, String>();
		//门店ID 城市ID map
		Map<String, String> medicalMap = new LinkedHashMap<String, String>();
		//门店ID 门店信息map
		Map<String, String> tempMap = new LinkedHashMap<String, String>();
		//门店ID 门店地址map
		Map<String, String> addressMap = new LinkedHashMap<String, String>();
		
		for (int i = 0; i < list.size(); i++) {
			String address = list.get(i).get("PROVINCENAME").toString() 
					+ list.get(i).get("CITYNAME").toString() 
					+ list.get(i).get("COUNTYNAME").toString() 
					+ (StringUtil.isBlank(list.get(i).get("ADDRESS"))?"":list.get(i).get("ADDRESS").toString());
			areaMap.put(list.get(i).get("CITY").toString(), list.get(i).get("CITYNAME").toString());
			tempMap.put(list.get(i).get("ESID").toString(), list.get(i).get("CITY").toString());
			medicalMap.put(list.get(i).get("ESID").toString(), list.get(i).get("ENAME").toString());
			addressMap.put(list.get(i).get("ESID").toString(), address);
		}
		
		map.put("areaMap", areaMap);
		map.put("medicals", JSONArray.fromObject(medicalMap).toString());
		map.put("temp", JSONArray.fromObject(tempMap).toString());
		map.put("address", JSONArray.fromObject(addressMap).toString());
		return map;
	}
	
	public boolean doCreateNewReservation(SReservation tr){
		HashMap<String, Object> calMap = new HashMap<String, Object>();
		calMap.put("day", tr.getCreaterTime());
		calMap.put("esid", tr.getEsid());
//		calMap.put("count", 1);
		String personCount = rlDao.findSingleDateCount(calMap);//可约人数
		if(!StringUtil.isBlank(personCount))if(Integer.parseInt(personCount) < 1) return false;
		if(tr.getCard().length() < 14){
			HashMap<String, Object> ridX = reservationDao.checkMallCardIsReve(tr.getCard());
			if (!SysUtils.isEmpty(ridX)){
				tr.setRid(Integer.parseInt(ridX.get("RID").toString()));
				//查看是否是被已驳回状态，如果是被驳回而重新预约则修改订单项和预约表
				reservationDao.updateTreservation(tr);	
			} else {
				tr.setStatus(0);
				reservationDao.addTreservation(tr);
			}
			reservationDao.updateOrderItemStatus(tr);
//			if(tr.getCard().indexOf("X") > -1){
//				reservationDao.updateOrderItemStatus(tr);
//			}else{
//				reservationDao.updateTGiftBookCardStatus(tr);
//			}
		}//else{
//			HashMap<String, Object> isRevYK = reservationDao.checkSaleCardIsReve(tr.getCard());
//			if (!SysUtils.isEmpty(isRevYK)){
//				tr.setRid(Integer.parseInt(isRevYK.get("RID").toString()));
//				reservationDao.updateSGroupbuyReservation(tr);
//			} else {
//				reservationDao.addSGroupbuyReservation(tr);
//			}
//			if(tr.getCard().indexOf("YK") > -1){
//				reservationDao.updateYKMedicalCardStatus(tr);
//			}else{
//				reservationDao.updateMedicalCardStatus(tr);
//			}
//		}
		if(!StringUtil.isBlank(personCount)) rlDao.updateSingleDateCount(calMap);
		return true;
	}
	@Override
	public String getAddressByEsidNoProvince(String esid) {
		return orderDao.getAddressByEsidNoProvince(esid);
	}
	@Override
	public SOrderLine findCardInfoByCard(SReservation sReservation) {
		return reservationDao.findCardInfoByCard(sReservation);
	}
	

}

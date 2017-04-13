/**
 * 
 */
package com.sgfm.datacenter.service.reservation;

import java.util.HashMap;
import java.util.Map;

import com.sgfm.datacenter.entity.SOrderLine;
import com.sgfm.datacenter.entity.SReservation;


/** 
 * @author cxj
 * @date 2016-11-3 上午10:21:14
 */
public interface ReservationService {

//	/**
//	 * @param param
//	 * @return
//	 */
	public HashMap<String, Object> findReservationInfoByMid(HashMap<String, Object> param);
//
//	/**
//	 * @param param
//	 * @return
//	 */
	public HashMap<String, Object> findReservationInfo(Map<String, Object> param);
//
//	/**
//	 * 查询体检卡预约单信息
//	 * @param param
//	 * @return
//	 */
//	public HashMap<String, Object> findOnlinePayReservationInfo(Map<String, Object> param);
//
//	/**
//	 * 查询渠道预约单信息
//	 * @param param
//	 * @return
//	 */
//	public HashMap<String, Object> findGroupbuyReservationInfo(Map<String, Object> param);
//
//	/**
//	 * 查询到店付款预约单信息
//	 * @param param
//	 * @return
//	 */
//	public HashMap<String, Object> findShopPayReservationInfo(Map<String, Object> param);
//
//	/**
//	 * 获取修改预约单页面所需数据
//	 * @param tProductAttr
//	 * @return
//	 */
	public Map<String, Object> getReservationInfoForUpdate(Map<String, Object> param);
//
	/**
	 * @param tReservation
	 * @return
	 */
	public int updateCardReservation(SReservation sReservation);
//
//	/**
//	 * @param rid
//	 * @return
//	 */
//	public HashMap<String, Object> findSReservationByRid(String rid);
//
//	/**
//	 * @param sReservation
//	 * @return
//	 */
//	public int updateSreservation(SReservation sReservation);
//
//	/**
//	 * @param gReservation
//	 * @return
//	 */
//	public int updateGroupbuyReservation(SGroupbuyReservation gReservation);
//	
	public String checkCard(SReservation reservation);
//	
	public HashMap<String, Object> toReFromUsercenter(HashMap<String, Object> map);
//	
	public boolean doCreateNewReservation(SReservation sReservation);
	
	public String getAddressByEsidNoProvince(String esid);

	public SOrderLine findCardInfoByCard(SReservation sReservation);
	
}

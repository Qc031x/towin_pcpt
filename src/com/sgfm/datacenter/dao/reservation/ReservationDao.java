/**
 * 
 */
package com.sgfm.datacenter.dao.reservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sgfm.base.dao.BaseDao;
import com.sgfm.datacenter.entity.SOrderLine;
import com.sgfm.datacenter.entity.SReservation;

/** 
 * @author cxj
 * @date 2016-11-3 上午10:24:32
 */
@SuppressWarnings("unchecked")
@Repository
public class ReservationDao extends BaseDao {
//
//	/**
//	 * @param param
//	 * @return
//	 */
	public List<HashMap<String, Object>> findReservationByMid(Map<String, Object> param) {
		return super.getSqlMapClientTemplate().queryForList("ReservationMap.findReservationByMid",param);
	}
//
//	/**
//	 * @param param
//	 * @return
//	 */
	public HashMap<String, Object> findReservationInfo(Map<String, Object> param) {
		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("ReservationMap.findReservationInfo",param);
	}

//	/**
//	 * @param param
//	 * @return
//	 */
//	public HashMap<String, Object> findOnlinePayReservationInfo(Map<String, Object> param) {
//		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("ReservationMap.findOnlinePayReservationInfo",param);
//	}
//
//	/**
//	 * @param param
//	 * @return
//	 */
//	public HashMap<String, Object> findGroupbuyReservationInfo(Map<String, Object> param) {
//		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("ReservationMap.findGroupbuyReservationInfo",param);
//	}
//
//	/**
//	 * @param param
//	 * @return
//	 */
//	public HashMap<String, Object> findShopPayReservationInfo(Map<String, Object> param) {
//		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("ReservationMap.findShopPayReservationInfo",param);
//	}
//
//	/**
//	 * @param 
//	 * @return
//	 */
//	public List<HashMap<String, Object>> findReservationRelativeInfo(
//			TProductAttr tProductAttr) {
//		return super.getSqlMapClientTemplate().queryForList("ReservationMap.findReservationRelativeInfo",tProductAttr);	
//	}
//
//	/**
//	 * @param rid
//	 * @return
//	 */
//	public HashMap<String, Object> findSReservationByRid(String rid) {
//		return (HashMap<String,Object>) super.getSqlMapClientTemplate().queryForObject("ReservationMap.findSReservationByRid",rid);
//	}
//
//	/**
//	 * @param sreservation
//	 * @return
//	 */
//	public int updateSreservation(SReservation sReservation) {
//		return super.getSqlMapClientTemplate().update("ReservationMap.updateSreservation",sReservation);
//	}
//
//	/**
//	 * @param tReservation
//	 */
	public int updateTreservation(SReservation sReservation) {
		return super.getSqlMapClientTemplate().update("ReservationMap.updateTReservation",sReservation);
	}

	/**
	 * @param tReservation
	 * @return
	 */
	public int updatePorderLineIsValid(SReservation sReservation) {
		return super.getSqlMapClientTemplate().update("ReservationMap.updatePorderLineIsValid",sReservation);
	}
//
//	/**
//	 * @param tReservation
//	 * @return
//	 */
//	public int updateGiftbookIsValid(TReservation tReservation) {
//		return super.getSqlMapClientTemplate().update("ReservationMap.updateGiftbookIsValid",tReservation);
//	}
//
//	/**
//	 * @param gReservation
//	 * @return
//	 */
//	public int updateGroupbuyReservation(SGroupbuyReservation gReservation) {
//		return super.getSqlMapClientTemplate().update("ReservationMap.updateGroupbuyReservation",gReservation);
//	}
//	
	public HashMap<String, Object> findVValidInfoByCardInfo(SReservation reservation){
		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("ReservationMap.findVValidInfoByCardInfo", reservation);
	}
//	
//	public HashMap<String, Object> findEValidInfoByCardInfo(TReservation reservation){
//		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("ReservationMap.findEValidInfoByCardInfo", reservation);
//	}
//	
//	public HashMap<String, Object> findSELPTYKValidInfoByCardInfo(TReservation reservation){
//		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("ReservationMap.findSELPTYKValidInfoByCardInfo", reservation);
//	}
//	
//	public HashMap<String, Object> findSELPTValidInfoByCardInfo(TReservation reservation){
//		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("ReservationMap.findSELPTValidInfoByCardInfo", reservation);
//	}
//	
	public HashMap<String, Object> checkMallCardIsReve(String card) {
		return (HashMap<String, Object>)super.getSqlMapClientTemplate().queryForObject("ReservationMap.checkMallCardIsReve", card);
	}
//	
//	public HashMap<String, Object> checkSaleCardIsReve(String card) {
//		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("ReservationMap.checkSaleCardIsReve",card);
//	}
//	
	public void addTreservation(SReservation sReservation){
		super.getSqlMapClientTemplate().insert("ReservationMap.insertTreservation",sReservation);
	}
//	
	public int updateOrderItemStatus(SReservation sReservation) {
		return super.getSqlMapClientTemplate().update("ReservationMap.updateOrderItemStatus",sReservation);
	}
//	
//	public int updateTGiftBookCardStatus(SReservation sReservation) {
//		return super.getSqlMapClientTemplate().update("ReservationMap.updateTGiftBookCardStatus", sReservation);
//	}
//	
//	public void updateSGroupbuyReservation(TReservation tReservation) {
//		super.getSqlMapClientTemplate().update("ReservationMap.updateSGroupbuyReservation",tReservation);
//	}
//	
//	public void addSGroupbuyReservation(TReservation tReservation) {
//		super.getSqlMapClientTemplate().insert("ReservationMap.addSGroupbuyReservation",tReservation);
//	}
//	
//	public void updateYKMedicalCardStatus(TReservation tReservation) {
//		super.getSqlMapClientTemplate().update("ReservationMap.updateYKMedicalCardStatus",tReservation);
//	}
//	
//	public void updateMedicalCardStatus(TReservation tReservation) {
//		super.getSqlMapClientTemplate().update("ReservationMap.updateMedicalCardStatus",tReservation);
//	}
	
	public SOrderLine findCardInfoByCard(SReservation sReservation){
		return (SOrderLine)super.getSqlMapClientTemplate().queryForObject("ReservationMap.findCardInfoByCard",sReservation);
	}
}

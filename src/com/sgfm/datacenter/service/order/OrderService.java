
package com.sgfm.datacenter.service.order;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import com.sgfm.datacenter.entity.SConsigneeAddress;
import com.sgfm.datacenter.entity.SOrder;

import java.util.Map;

/** 
 * @author cxj
 * @date 2016-11-1 上午8:55:33
 */
public interface OrderService {

	/**
	 * 根据OID查询订单
	 */
	public SOrder findOrderByOid(String oid);
//	
//	/**
//	 * 修改订单
//	 * @param valideData
//	 * @param oid
//	 * @param callBackType
//	 */
//	public void updateOrder(Map <String ,String>valideData,String oid,String callBackType);
//	public void updateOrder(Map <String ,String>valideData);
//	
//	public HashMap<String, Object> finishOrderToPay(RPorder order);
//	
//	public void SendOrderMsg(String oid);
//
//
//	public Map<String,Object> findProductByCardPwd(Map<String,Object> map);
//	
//	public int updatePorderLineSendCount(Map<String, Object> map);
//
//	public Map<String, Object> finishOrderPay(RPorder order);
//
//	public Map<String, Object> findPlatOrderAndPayInfo(String oid);
//	public TMember findMemberByMid(String mid);
//	
//	public boolean doCheckOrderPayed(String oid);
//
//	public boolean checkProductIsEmpty(String oid);
//	
//	public boolean doCompareOrderDate(String oid);
	/**
	 * 查看会员订单信息
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> findUserOrder(HashMap<String,Object> map);
//
//	/**
//	 * @param map
//	 * @return
//	 */
	public HashMap<String, Object> findUserCardByMid(HashMap<String, Object> map);
//
//	/**
//	 * 查看体检卡详情
//	 * @param param
//	 * @return
//	 */
	public HashMap<String, Object> findCardInfo(Map<String, Object> param);
//	
//	public HashMap<String, Object> findRpOrderLineByOid(HashMap<String, Object> map);
//	
//	public HashMap<String, Object> checkCount(String oid);
//	
//	public TPorder findMallOrderByOid(String oid);
//	
//	public HashMap<String, Object> checkMallCount(String oid);
//	
//	public void updateMallOrder(Map <String ,String>valideData,String oid,String callBackType);
//	public void updateMallOrder(Map <String ,String>valideData);
//	
	public HashMap<String, Object> doHandleRsCookie(HashMap<String, Object> map);
//	
	public HashMap<String, Object> doHandleCardCookie(HashMap<String, Object> map);
//	
	public String doCreateReservation(HashMap<String, Object> map) throws IllegalAccessException, InvocationTargetException;
//	
	public int addMemberAddress(SConsigneeAddress add);
//	
	public void updateMemberAddress(SConsigneeAddress add);
//	
	public String doCreateOrder(HashMap<String, Object> map);
//
	public HashMap<String, Object> getOrderInfoByOid(String oid);
//	
//	public Map orderOverdueSimulation(String oid);
//
	public List<HashMap<String, Object>> findPidsByOid(String oid);

	
	/**
	 * 修改订单状态
	 * @param orderMap
	 * @author 康良玉
	 */
	public void updateOrder(SOrder sorder);
	
	/**
	 * 通过oid获取支付成功页面显示的订单信息
	 * @param oid
	 * @return
	 * @author 康良玉
	 */
	public HashMap<String,Object> findPaySuccessInfoByOid(String oid);
	/**
	 * 到店付款预约 发送 短信和微信 通知信息
	 * @return
	 * @author 秦光耀
	 */
	public void sendMessage(HashMap<String, Object> map);
}

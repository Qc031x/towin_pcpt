package com.sgfm.datacenter.dao.order;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.sgfm.base.dao.BaseDao;
import com.sgfm.datacenter.entity.SConsigneeAddress;
import com.sgfm.datacenter.entity.SOrder;
import com.sgfm.datacenter.entity.SOrderLine;
import com.sgfm.datacenter.entity.SReservation;

/**
 * 订单数据库持久层
 * 
 * @author 曹雄
 */
@SuppressWarnings("unchecked")
@Repository
public class OrderDao extends BaseDao {

	/*
	 * public List<Map> findEntityshopInfoByPid(String pid){ return
	 * super.getSqlMapClientTemplate
	 * ().queryForList("OrderMap.findEntityshopInfoByPid", pid); } public
	 * List<Map> findEntityshopInfoByPidAndCity(CgVariable cgVariable){ return
	 * super.getSqlMapClientTemplate().queryForList(
	 * "OrderMap.findEntityshopInfoByPidAndCity", cgVariable); } public
	 * List<Map> findPromotionByPid(String pid) {
	 * 
	 * return
	 * super.getSqlMapClientTemplate().queryForList("ProductMap.findPromotionByPid"
	 * , pid); }
	 */

	/**
	 * 查询订单
	 * 
	 * @param oid
	 * @return
	 */
	public SOrder findOrderByOid(String oid) {
		return (SOrder) super.getSqlMapClientTemplate().queryForObject("OrderMap.findOrderByOid", oid);
	}
//
//	/**
//	 * 修改订单
//	 * 
//	 * @param order
//	 * @return
//	 */
//	public int updateOrder(RPorder order) {
//		return (int) super.getSqlMapClientTemplate().update("OrderMap.updateOrder", order);
//	}
//
//	public void updateOrderPayedStatus(RPorder order) {
//		super.getSqlMapClientTemplate().update("OrderMap.updateOrderPayedStatus", order);
//	}
//
//	/**
//	 * 修改订单项
//	 */
//	public int updPayLineByOid(TPayLine payLine) {
//		return super.getSqlMapClientTemplate().update("OrderMap.updPayLineByOid", payLine);
//	}
//
//	public int getPayId() {
//		super.getSqlMapClientTemplate().update("OrderMap.updatePayId");
//		return (Integer) super.getSqlMapClientTemplate().queryForObject("OrderMap.getPayId");
//	}
//
	public int getOid() {
		return (Integer) super.getSqlMapClientTemplate().queryForObject("OrderMap.getOid");
	}

	public void addOrder(SOrder order) {
		super.getSqlMapClientTemplate().insert("OrderMap.insertOrder", order);
	}

	public void addOrderLine(final List<SOrderLine> list) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				for (int i = 0; i < list.size(); i++) {
					executor.insert("OrderMap.insertOrderLine", list.get(i));
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
	public void batchAddReservation(final List<SReservation> list){
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				for (int i = 0; i < list.size(); i++) {
					executor.insert("OrderMap.addReservation", list.get(i));
				}
				return executor.executeBatch();
			}
		});
	}
//
//	/**
//	 * @param oid
//	 * @return
//	 */
//	public List<Map<String, Object>> findPlatOrderLineByOid(String oid) {
//		return super.getSqlMapClientTemplate().queryForList("OrderMap.findPlatOrderLineByOid", oid);
//	}
//
//	public List<HashMap<String, Object>> findPromotionByPid(String pid) {
//		return super.getSqlMapClientTemplate().queryForList("OrderMap.findPromotionByPid", pid);
//	}
//
//	public TMember findMemberByOid(String oid) {
//		return (TMember) super.getSqlMapClientTemplate().queryForObject("OrderMap.findMemberByOid", oid);
//	}
//
//	public TMember findMallMemberByOid(String oid) {
//		return (TMember) super.getSqlMapClientTemplate().queryForObject("OrderMap.findMallMemberByOid", oid);
//	}
//
//	public TMember findMemberByMid(String mid) {
//		return (TMember) super.getSqlMapClientTemplate().queryForObject("OrderMap.findMemberByMid", mid);
//	}
//
//	public List<Map> findProductSalesTop(String city) {
//		return super.getSqlMapClientTemplate().queryForList("OrderMap.findProductSalesTop", city);
//	}
//
//	public List<Map> findDistributionType(Map map) {
//		return super.getSqlMapClientTemplate().queryForList("OrderMap.findSameTypeProduct", map);
//	}
//
//	/**
//	 * @param map
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public Map<String, Object> findProductByCardPwd(Map<String, Object> map) {
//		return (Map<String, Object>) super.getSqlMapClientTemplate().queryForObject("OrderMap.findProductByCardPwd", map);
//	}
//
//	/**
//	 * @param map
//	 * @return
//	 */
//	public int updatePorderLineSendCount(Map<String, Object> map) {
//		return super.getSqlMapClientTemplate().update("OrderMap.updatePorderLineSendCount", map);
//	}
//
//	/**
//	 * 根据oid查找平台预约单
//	 * 
//	 * @param oid
//	 * @return
//	 */
//	public Map<String, Object> findPlatOrderByOid(String oid) {
//		return (Map<String, Object>) super.getSqlMapClientTemplate().queryForObject("OrderMap.findPlatOrderByOid", oid);
//	}
//
	public List<HashMap<String, Object>> findReservationByOid(String oid) {
		return super.getSqlMapClientTemplate().queryForList("OrderMap.findReservationByOid", oid);
	}
//
//	/**
//	 * @param caid
//	 * @return
//	 */
//	public TConsigneeAddress findAddressByCaid(String caid) {
//		return (TConsigneeAddress) super.getSqlMapClientTemplate().queryForObject("OrderMap.findAddressByCaid", caid);
//	}
//
//	/**
//	 * @param mid
//	 * @param caid
//	 */
//	public int updDefaultConsigneeAddress(String mid, String caid) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("mid", mid);
//		map.put("caid", caid);
//		return super.getSqlMapClientTemplate().update("OrderMap.updateDefaultAdd", map);
//	}
//
//	/**
//	 * @param creditLog
//	 */
//	public void addCreditLog(TCreditLog creditLog) {
//		super.getSqlMapClientTemplate().insert("OrderMap.addCreditLog", creditLog);
//	}
//
//	public HashMap<String, Object> findSELPTYKCardByCardinfo(HashMap<String, Object> map) {
//		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("OrderMap.findSELPTYKCardByCardinfo", map);
//	}
//
//	public HashMap<String, Object> findSELPTCardByCardinfo(HashMap<String, Object> map) {
//		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("OrderMap.findSELPTCardByCardinfo", map);
//	}
//
//	public List<HashMap<String, Object>> findProductByOidIsNotEmpty(String oid) {
//		return super.getSqlMapClientTemplate().queryForList("OrderMap.findProductByOidIsNotEmpty", oid);
//	}
//
	public List<Map<String, Object>> findUserOrder(HashMap<String, Object> map) {
		return super.getSqlMapClientTemplate().queryForList("OrderMap.findUserOrder", map);
	}
//
//	/**
//	 * @param oid
//	 * @return
//	 */
//	public List<Map> findROrderLineAndPriceByOid(String oid) {
//		return super.getSqlMapClientTemplate().queryForList("OrderMap.findROrderLineAndPriceByOid", oid);
//	}
//
//	public List<Map> findOrderLineAndPriceByOid(String oid) {
//		return super.getSqlMapClientTemplate().queryForList("OrderMap.findOrderLineAndPriceByOid", oid);
//	}
//
//	/**
//	 * @param param
//	 * @return
//	 */
//	public HashMap<String, Integer> findUserOrderNumbers(HashMap<String, Object> param) {
//		return (HashMap<String, Integer>) super.getSqlMapClientTemplate().queryForObject("OrderMap.findUserOrderNumbers", param);
//	}
//
//	/**
//	 * @param map
//	 * @return
//	 */
	public List<HashMap<String, Object>> findUserCardByMid(HashMap<String, Object> map) {
		return super.getSqlMapClientTemplate().queryForList("OrderMap.findUserCardByMid", map);
	}
//
//	/**
//	 * @param param
//	 * @return
//	 */
	public HashMap<String, Object> findCardInfo(Map<String, Object> param) {
		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("OrderMap.findUserCardByMid", param);
	}
//
//	public List<TPorderLine> findOrderLineByOid(String oid) {
//		return super.getSqlMapClientTemplate().queryForList("OrderMap.findOrderLineByOid", oid);
//	}
//
//	/**
//	 * @param param
//	 * @return
//	 */
//	public List<Map<String, Object>> findUserAllOrderItems(Map<String, Object> param) {
//		return super.getSqlMapClientTemplate().queryForList("OrderMap.findUserAllOrderItems", param);
//	}
//
//	/**
//	 * @param map
//	 * @return
//	 */
//	public HashMap<String, Object> findRpOrderLineByOid(HashMap<String, Object> map) {
//		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("OrderMap.findRpOrderLineByOid", map);
//	}
//	
//	public List<RPorderLine> findROrderLineByOid(String oid){
//		return super.getSqlMapClientTemplate().queryForList("OrderMap.findROrderLineByOid", oid);
//	}
//
//	public TPorder findMallOrderByOid(String oid) {
//		TPorder order = (TPorder) super.getSqlMapClientTemplate().queryForObject("OrderMap.findMallOrderByOid", oid);
//		return order;
//	}
//
//	public List<TPorderLine> findMallOrderLineByOid(String oid) {
//		return super.getSqlMapClientTemplate().queryForList("OrderMap.findMallOrderLineByOid", oid);
//	}
//
//	public int updateMallOrder(TPorder order) {
//		return (int) super.getSqlMapClientTemplate().update("OrderMap.updateMallOrder", order);
//	}
//
	public List<HashMap<String, Object>> findPidsByOid(String oid) {
		return super.getSqlMapClientTemplate().queryForList("OrderMap.findPidsByOid", oid);
	}
	public List<HashMap<String, Object>> findProductByOid(String oid) {
		return super.getSqlMapClientTemplate().queryForList("OrderMap.findProductByOid", oid);
	}
//
//	public TMemberCard findMemberCardByMid(String mid) {
//		return (TMemberCard) super.getSqlMapClientTemplate().queryForObject("OrderMap.findMemberCardByMid", mid);
//	}
//
	public List<HashMap<String, Object>> findMembersAddressByMid(int mid) {
		return super.getSqlMapClientTemplate().queryForList("OrderMap.findMembersAddressByMid", mid);
	}

	public void updMembersDefaultAdd(int mid) {
		super.getSqlMapClientTemplate().update("OrderMap.updateDefaultAdd", mid);
	}

	public void updMembersDefaultAddByCaid(int caid) {
		super.getSqlMapClientTemplate().update("OrderMap.updMembersDefaultAddByCaid", caid);
	}

	public int addAddress(SConsigneeAddress add) {
		return (int) super.getSqlMapClientTemplate().insert("OrderMap.addAddress", add);
	}
	
	public void updateAddressByCaid(SConsigneeAddress add){
		super.getSqlMapClientTemplate().update("OrderMap.updateAddressByCaid", add);
	}

//	public TSequence getMallOid() {
//		super.getSqlMapClientTemplate().update("OrderMap.updateMallOid");
//		return (TSequence) super.getSqlMapClientTemplate().queryForObject("OrderMap.getMallOid");
//	}
//
//	public int updateMemberCardBalanceAndCoupon(TMemberCard memberCard) {
//		return super.getSqlMapClientTemplate().update("OrderMap.updateMemberCardBalanceAndCoupon", memberCard);
//	}
//
//	public int updateMemberCardCoupon(TMemberCard memberCard) {
//		return super.getSqlMapClientTemplate().update("OrderMap.updateMemberCardCoupon", memberCard);
//	}
//
//	public TSequence getCLId() {
//		TSequence tSequence = new TSequence();
//		super.getSqlMapClientTemplate().update("OrderMap.updateCLId");
//		tSequence = (TSequence) super.getSqlMapClientTemplate().queryForObject("OrderMap.getCLId");
//		return tSequence;
//	}
//	
//	public void updateOrderSumpriceByOid(Map map){
//		super.getSqlMapClientTemplate().update("OrderMap.updateOrderSumpriceByOid", map);
//	}
//	
//	public void addMallOrder(TPorder order){
//		super.getSqlMapClientTemplate().insert("OrderMap.addMallOrder", order);
//	}
//	
//
	public HashMap<String, Object> getOrderDetailByOid(HashMap<String, Object> paramMap) {
		return (HashMap<String, Object>)super.getSqlMapClientTemplate().queryForObject("OrderMap.getOrderDetailByOid",paramMap);
	}
//	
//	public List<Map> findOrderInfoByOid(String oid){
//		return super.getSqlMapClientTemplate().queryForList("OrderMap.findOrderInfoByOid", oid);
//	}
//	
//	public TConsigneeAddress findDefaultAddressByMid(String mid){ 
//		return (TConsigneeAddress) super.getSqlMapClientTemplate().queryForObject("OrderMap.findDefaultAddressByMid", mid);
//	}
//
//	public TCreditLog findCreditLogByOid(String oid) {
//		return (TCreditLog) super.getSqlMapClientTemplate().queryForObject("OrderMap.findCreditLogByOid",oid);
//	}

	/**
	 * 修改订单状态
	 * @param temployeeOrder
	 * @author 康良玉
	 */
	public void updateOrder(SOrder sorder){
		super.getSqlMapClientTemplate().update("OrderMap.updateOrder",sorder);
	}
	
	public List<HashMap<String, Object>> findProductInfoByOid(String oid) {
		return super.getSqlMapClientTemplate().queryForList("OrderMap.findProductInfoByOid", oid);
	}
	public String getAddressByEsidNoProvince(String esid) {
		return (String) super.getSqlMapClientTemplate().queryForObject("OrderMap.getAddressByEsidNoProvince",esid);
	}
	public void addMsgrecord(HashMap<String, Object> addmsgMap) {
		super.getSqlMapClientTemplate().insert("OrderMap.addMsgrecord",addmsgMap);
	}

}

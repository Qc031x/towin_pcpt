package com.sgfm.datacenter.service.order.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgfm.base.util.DateUtil;
import com.sgfm.base.util.StringUtil;
import com.sgfm.datacenter.MsgConstant;
import com.sgfm.datacenter.dao.common.CommonDao;
import com.sgfm.datacenter.dao.order.OrderDao;
import com.sgfm.datacenter.dao.product.ProductDao;
import com.sgfm.datacenter.dao.product.RlDao;
import com.sgfm.datacenter.entity.SConsigneeAddress;
import com.sgfm.datacenter.entity.SOrder;
import com.sgfm.datacenter.entity.SOrderLine;
import com.sgfm.datacenter.entity.SReservation;
import com.sgfm.datacenter.entity.TMember;
import com.sgfm.datacenter.memcached.CacheHashMap;
import com.sgfm.datacenter.memcached.MemcachedConstant;
import com.sgfm.datacenter.service.common.CommonService;
import com.sgfm.datacenter.service.order.OrderService;
import com.sgfm.datacenter.util.CommUtil;
import com.sgfm.datacenter.util.SysUtils;
import com.taobao.api.ApiException;

import net.sf.json.JSONArray;

/** 
 * @author cxj
 * @date 2016-11-1 上午8:56:36
 */
@Service
public class OrderServiceImpl implements OrderService {

	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CacheHashMap cacheHashMap;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private RlDao rlDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CommonDao commonDao;

	@Autowired
	private CommonService commonService;

	/**
	 * 根据OID查询订单
	 * 
	 * @return TPorder
	 */
	public SOrder findOrderByOid(String oid) {
		return orderDao.findOrderByOid(oid);
	}
//
//	// 0微信回调 1支付宝回调 2银联支付回调
//	public void updateOrder(Map<String, String> valideData, String oid, String callBackType) {
//		logger.info(callBackType + "进入了支付回调beg-updateOrder :" + (new Date()).toLocaleString() + "  ");
//		RPorder orderTemp = this.findOrderByOid(oid);
//		int pst = orderTemp.getPayStatus();// ==null?-1:orderTemp.getPayStatus();
//		String pay_status = String.valueOf(pst);
//
//		// TMember tMember = this.findMemberByOid(oid);
//
//		// 如果未支付
//		if (!"2".equals(pay_status)) {
//
//			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//			orderTemp.setActualPay(Double.parseDouble(valideData.get("total_fee")));
//			orderTemp.setPayStatus(2);
//			orderTemp.setPayedTime(simpledateformat.format(new Date()));
//			orderTemp.setPayType(new Integer(valideData.get("pc_pay_type")));
//			orderDao.updateOrder(orderTemp);
//
//			TPayLine payLine = new TPayLine();
//			payLine.setSurplus(Double.parseDouble(valideData.get("total_fee")));
//			payLine.setPayType(new Integer(valideData.get("pc_pay_type")));
//			payLine.setHandleStatus(2);
//			payLine.setPayStatus(2);
//			payLine.setPaySource(valideData.get("orderId").replace("YY", ""));
//
//			orderDao.updPayLineByOid(payLine);
//			this.SendOrderMsg(oid);
//		} else {
//			logger.info(callBackType + "-(该订单状态是已经支付了):" + (new Date()).toLocaleString() + "  ");
//		}
//		logger.info(callBackType + "进入了支付回调end修改订单状态ok-updateOrder :" + (new Date()).toLocaleString() + "  ");
//		// TPorder orderTemp=orderDao.findOrderByOid(valideData.get("orderId"));
//
//		// if(!SysUtils.isEmpty(tMember)){
//		// logger.info("下单赠送积分开始"+(new Date()).toLocaleString()+"  ");
//		// Map map = new HashMap();
//		// map.put("tMember", tMember);
//		// map.put("type", "4");
//		// map.put("ids", oid);
//		// map.put("shop_price", orderTemp.getSumPrice());
//		// commonService.changeMemberCc(map);
//		// logger.info("下单赠送积分结束"+(new Date()).toLocaleString()+"  ");
//		//
//		// }else{
//		// logger.info("没有从订单中查找到会员信息"+(new Date()).toLocaleString()+"  ");
//		// return;
//		// }
//
//	}
//	
//	public void updateOrder(Map <String ,String>valideData){
//		logger.info("支付信息："+valideData);
//		RPorder orderTemp=orderDao.findOrderByOid(valideData.get("orderId"));
//		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		int pay_status = orderTemp.getPayStatus();
//		
//		if(pay_status!=2){
//			orderTemp.setActualPay(Double.parseDouble(valideData.get("total_fee").toString()));
//			orderTemp.setPayStatus(2);
//			orderTemp.setPayedTime(simpledateformat.format(new Date()));
//			orderTemp.setPayType(new Integer(valideData.get("wap_pay_type")));
//			orderDao.updateOrder(orderTemp);
//			 
//			TPayLine payLine=new TPayLine();
//			payLine.setSurplus(Double.parseDouble(valideData.get("total_fee").toString()));
//			payLine.setPayType(new Integer(valideData.get("wap_pay_type")));
//			payLine.setHandleStatus(2);
//			payLine.setPayStatus(2);
//			payLine.setPaySource(valideData.get("orderId").replace("Y", ""));
//			orderDao.updPayLineByOid(payLine);
//			
//			SendOrderMsg(valideData.get("orderId"));
//			List<RPorderLine> orderLines = orderDao.findROrderLineByOid(valideData.get("orderId"));
//			HashMap<String, Object> product = CommHashMap.getProductHashMaps(MemcachedConstant.YY_PRODUCT + orderLines.get(0).getPid());
//			if(!product.get("PAYMENT").toString().equals("1")){
//				TMember tMember = orderDao.findMemberByMid(String.valueOf(orderTemp.getMid()));
//				if(!SysUtils.isEmpty(tMember)){
//					logger.info("下单赠送积分开始"+(new Date()).toLocaleString()+"  ");
//					Map map = new HashMap();
//					map.put("tMember", tMember);
//					map.put("type", "4");
//					map.put("ids", valideData.get("orderId"));
//					map.put("shop_price", orderTemp.getSumPrice());
//					commonService.changeMemberCc(map);
//					logger.info("下单赠送积分结束"+(new Date()).toLocaleString()+"  ");
//				}else{
//					logger.info("没有从订单中查找到会员信息"+(new Date()).toLocaleString()+"  ");
//					return;
//				}
//			}
//		}
//			
//	}
//	
//	
//	public HashMap<String, Object> finishOrderToPay(RPorder order) {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		RPorder orderInfo = orderDao.findOrderByOid(String.valueOf(order.getOid()));
//		List<Map<String, Object>> orderLines = orderDao.findPlatOrderLineByOid(order.getOid());
//		ArrayList<HashMap<String, Object>> orderHandle = new ArrayList<HashMap<String,Object>>();
//		
//		HashMap<String, Object> temp = new HashMap<String, Object>();
//		for (int i = 0; i < orderLines.size(); i++) {
//			HashMap<String, Object> pro = CommHashMap.getProductHashMaps(MemcachedConstant.YY_PRODUCT + orderLines.get(i).get("PID").toString());
//			if(pro.get("ALIVE").toString().equals("0")) return new HashMap<String, Object>();
//			orderLines.get(i).put("IMG_S", pro.get("IMG_S"));
//			orderLines.get(i).put("CSID", pro.get("CSID"));
//			String str = orderLines.get(i).get("PID").toString() + orderLines.get(i).get("PRODUCT_ATTR").toString();
//			if(!temp.containsKey(str)){
//				//不存在 第一次添加
//				temp.put(str, orderLines.get(i));
//			}else{
//				//存在, 修改数量
//				int quantity = Integer.parseInt(orderLines.get(i).get("QUANTITY").toString());
//				HashMap<String, Object> temp2 = (HashMap<String, Object>) temp.get(str);
//				temp2.put("QUANTITY", Integer.parseInt(temp2.get("QUANTITY").toString()) + quantity);
//				temp.put(str, temp2);
//			}
//		}
//		
//		for (Map.Entry<String, Object> entry : temp.entrySet()) {
//			HashMap<String, Object> tempMap = (HashMap<String, Object>) entry.getValue();
//			List<HashMap<String, Object>> promotion = CommHashMap.getHashMapPromotionList(MemcachedConstant.YY_PROMOTION + tempMap.get("PID"));
//			tempMap.put("promotion", promotion);
//			orderHandle.add((HashMap<String, Object>) entry.getValue());
//		}
//		
//		map.put("order", orderInfo);
//		map.put("orderLine", orderHandle);
//		return map;
//	}
//
//	public void SendOrderMsg(String oid){
//		List<HashMap<String,Object>> list = orderDao.findReservationByOid(oid);
//		if(list!=null && list.size()>0){
//			
//			for(int i = 0;i<list.size();i++){
//				HashMap<String,Object> map = new HashMap<String, Object>();
//				map.put("type", "platformPaySuccess");
//				map.put("name", list.get(i).get("RE_NAME").toString());
//				map.put("mobile", list.get(i).get("RE_TEL").toString());
//				commonService.checkTodayMsgCount(map);
//			}
//			
//		}
//	}
//
//	@Override
//	public Map<String, Object> findProductByCardPwd(Map<String, Object> map) {
//		return orderDao.findProductByCardPwd(map);
//	}
//
//	@Override
//	public int updatePorderLineSendCount(Map<String, Object> map) {
//		return orderDao.updatePorderLineSendCount(map);
//	}
//	
//	@Override
//	public Map<String, Object> findPlatOrderAndPayInfo(String oid) {
//		//多个套餐性别是否相同的标识,false代表有同款套餐多个性别
//		Map<String,Object> returnMap = new HashMap<String,Object>();
//		
//		Map<String,Object> order = orderDao.findPlatOrderByOid(oid);
//		List<Map<String,Object>> orderLines = orderDao.findPlatOrderLineByOid(oid);
//		ArrayList<HashMap<String, Object>> orderHandle = new ArrayList<HashMap<String,Object>>();
//		double price = 0;
//		
//		HashMap<String, Object> temp = new HashMap<String, Object>();
//		for (int i = 0; i < orderLines.size(); i++) {
//			HashMap<String, Object> pro = CommHashMap.getProductHashMaps(MemcachedConstant.YY_PRODUCT + orderLines.get(i).get("PID").toString());
//			orderLines.get(i).put("IMG_S", pro.get("IMG_S"));
//			orderLines.get(i).put("CSID", pro.get("CSID"));
//			String str = orderLines.get(i).get("PID").toString() + orderLines.get(i).get("PRODUCT_ATTR").toString();
//			if(!temp.containsKey(str)){
//				//不存在 第一次添加
//				temp.put(str, orderLines.get(i));
//			}else{
//				//存在, 修改数量
//				int quantity = Integer.parseInt(orderLines.get(i).get("QUANTITY").toString());
//				HashMap<String, Object> temp2 = (HashMap<String, Object>) temp.get(str);
//				temp2.put("QUANTITY", Integer.parseInt(temp2.get("QUANTITY").toString()) + quantity);
//				temp.put(str, temp2);
//			}
//		}
//		
//		for (Map.Entry<String, Object> entry : temp.entrySet()) {
//			HashMap<String, Object> tempMap = (HashMap<String, Object>) entry.getValue();
//			List<HashMap<String, Object>> promotion = CommHashMap.getHashMapPromotionList(MemcachedConstant.YY_PROMOTION + tempMap.get("PID"));
//			tempMap.put("promotion", promotion);
//			price += Integer.parseInt(tempMap.get("QUANTITY").toString()) * Double.parseDouble(tempMap.get("SHOP_PRICE").toString());
//			orderHandle.add((HashMap<String, Object>) entry.getValue());
//		}
//		
//		List<TAppraiseTag> tagList = productDao.findAllProductTag();
//		
//		returnMap.put("price", price);
//		returnMap.put("tagList", tagList);
//		returnMap.put("order", order);
//		returnMap.put("orderLines", orderHandle);
//		return returnMap;
//	}
//	
//	@Override
//	public Map<String,Object> finishOrderPay(RPorder order) {
//		Map<String,Object> rOrder = orderDao.findPlatOrderByOid(order.getOid());
//		//String type = "";
//		//CommUtil commUtil = new CommUtil();
//		Map map = new HashMap();
//		
//		List<Map<String,Object>> orderLines = orderDao.findPlatOrderLineByOid(order.getOid());
//		/*促销信息
//		List<Map> promotionList = productDao.findPromotionByPids(pids);
//		commUtil.setClobToString(promotionList, "DETAIL");
//		 */
//		/*实体卡地址
//		TConsigneeAddress address = orderDao.findDefaultAddByMid(String.valueOf(rOrder.get("MID")));
//		if(type.indexOf("1") > -1 && !SysUtils.isEmpty(address)){
//			Map addMap = cartDao.findExactlyArea(String.valueOf(address.getProvince()), String.valueOf(address.getCity()), String.valueOf(address.getCounty()));
//			address.setPName(addMap.get("PROVINCE").toString());
//			address.setCiName(addMap.get("CITY").toString());
//			address.setCoName(addMap.get("COUNTY").toString());
//			map.put("address", address);
//		}
//		*/
//		map.put("order", rOrder);
//		map.put("orderLines", orderLines);
//		//map.put("promotionList", promotionList);
//		return map;
//	}
//
//	@Override
//	public TMember findMemberByMid(String mid) {
//		return  orderDao.findMemberByMid(mid);
//	}
//	
//	
//
	@Override
	public Map<String, Object> findUserOrder(HashMap<String,Object> map) {
		Map<String,Object> info = new HashMap<String,Object>();
		List<Map<String,Object>> list = orderDao.findUserOrder(map);	
		for (Map<String,Object> m : list){
			List<HashMap<String, Object>> productList=orderDao.findProductByOid(m.get("OID").toString());	
			for(int p=0;p<productList.size();p++){
				HashMap<String, Object> pro = cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT + productList.get(p).get("PID").toString());
				HashMap<String, Object> medicalCardImg =  cacheHashMap.getProductCardImgByPid(MemcachedConstant.TW_PRODUCT_CARDIMG + productList.get(p).get("PID").toString());//获取商品模板信息
				productList.get(p).putAll(pro);
				productList.get(p).putAll(medicalCardImg);
			}
			m.put("product", productList);
		}
		info.put("list", list);
		return info;
		
	}
//
//	/* (non-Javadoc)
//	 * @see com.sgfm.datacenter.service.order.OrderService#doCheckOrderPayed(java.lang.String)
//	 */
//	@Override
//	public boolean doCheckOrderPayed(String oid) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.sgfm.datacenter.service.order.OrderService#checkProductIsEmpty(java.lang.String)
//	 */
//	@Override
//	public boolean checkProductIsEmpty(String oid) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean doCompareOrderDate(String oid) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
	@Override
	public HashMap<String,Object> findUserCardByMid(HashMap<String, Object> param) {
		HashMap<String,Object> info = new HashMap<String,Object>();
		List<HashMap<String,Object>> list = orderDao.findUserCardByMid(param);
		String today = CommUtil.formatDate(new Date(), "yyyy-MM-dd");
		//已经使用过的卡数目
		//int used = 0;
		for(HashMap<String,Object> map : list){
			HashMap<String, Object> pro = cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT + map.get("PID").toString());
			HashMap<String,Object> cardImg = cacheHashMap.getProductCardImgByPid(MemcachedConstant.TW_PRODUCT_CARDIMG + map.get("PID").toString());
			map.put("proInfo", pro);
			map.put("cardImg", cardImg);
			//如果当前卡已经失效，is_valid 置为 -1
			if (today.compareTo(map.get("LAST_DATE").toString()) > 0 && !"2".equals(map.get("IS_VALID"))){
				map.put("IS_VALID", -1);
			}
		}
		info.put("list", list);
		return info;
	}
	@Override
	public HashMap<String, Object> findCardInfo(Map<String, Object> param) {
		HashMap<String,Object> map = orderDao.findCardInfo(param);
		String today = CommUtil.formatDate(new Date(),"yyyy-MM-dd");
		if (("0".equals(map.get("IS_VALID").toString())||"1".equals(map.get("IS_VALID").toString()))
				&& today.compareTo(map.get("LAST_DATE").toString()) > 0){
			map.put("IS_VALID", -1);
		}
		map.put("cardImg", cacheHashMap.getProductCardImgByPid(MemcachedConstant.TW_PRODUCT_CARDIMG + map.get("PID").toString()));
		map.putAll(cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT + map.get("PID").toString()));
		return map;
	}
//
//	/* (non-Javadoc)
//	 * @see com.sgfm.datacenter.service.order.OrderService#findRpOrderLineByOid(java.util.HashMap)
//	 */
//
//	
//	public HashMap<String, Object> checkCount(String oid){
//		List<TPorderLine> orderLine = orderDao.findOrderLineByOid(oid);
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("orderLine", orderLine);
//		return map;
//	}
//	
//	public TPorder findMallOrderByOid(String oid){
//		return orderDao.findMallOrderByOid(oid);
//	}
//	
//	public HashMap<String, Object> checkMallCount(String oid){
//		List<TPorderLine> orderLine = orderDao.findMallOrderLineByOid(oid);
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		int virtualCount = 0;
//		int entityCount = 0;
//		for (int i = 0; i < orderLine.size(); i++) {
//			if(orderLine.get(i).getCardType() == 0){
//				virtualCount++;
//			}else{
//				entityCount++;
//			}
//		}
//		map.put("virtualCount", virtualCount);
//		map.put("entityCount", entityCount);
//		map.put("orderLine", orderLine);
//		return map;
//	}
//	
//	public void updateMallOrder(Map <String ,String>valideData){
//		TPorder orderTemp=orderDao.findMallOrderByOid(valideData.get("orderId"));
//		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		
//		int pay_status = orderTemp.getPayStatus();
//		if(pay_status!=2){
//			orderTemp.setActualPay(Double.parseDouble(valideData.get("total_fee").toString()));
//			orderTemp.setPayStatus(2);
//			orderTemp.setPayedTime(simpledateformat.format(new Date()));
//			orderTemp.setPayType(new Integer(valideData.get("wap_pay_type")));
//			orderDao.updateMallOrder(orderTemp);
//			 
//			TPayLine payLine=new TPayLine();
//			payLine.setSurplus(Double.parseDouble(valideData.get("total_fee").toString()));
//			payLine.setPayType(new Integer(valideData.get("wap_pay_type")));
//			payLine.setHandleStatus(2);
//			payLine.setPayStatus(2);
//			payLine.setPaySource(valideData.get("orderId"));
//			
//			orderDao.updPayLineByOid(payLine);
//			
//			SendMallOrderMsg(valideData.get("orderId"));
//			
//			TMember tMember = orderDao.findMemberByMid(String.valueOf(orderTemp.getMid()));
//			if(!SysUtils.isEmpty(tMember)){
//				logger.info("下单赠送积分开始"+(new Date()).toLocaleString()+"  ");
//				Map map = new HashMap();
//				map.put("tMember", tMember);
//				map.put("type", "4");
//				map.put("ids", valideData.get("orderId"));
//				map.put("shop_price", orderTemp.getSumPrice());
//				commonService.changeMemberCc(map);
//				logger.info("下单赠送积分结束"+(new Date()).toLocaleString()+"  ");
//			}else{
//				logger.info("没有从订单中查找到会员信息"+(new Date()).toLocaleString()+"  ");
//				return;
//			}
//		}
//	}
//	
//	//0微信回调    1支付宝回调 2银联支付回调
//		public void updateMallOrder(Map <String ,String>valideData,String oid,String callBackType){
//			logger.info(callBackType+"进入了支付回调beg-updateOrder :"+(new Date()).toLocaleString()+"  ");
//				TPorder orderTemp=this.findMallOrderByOid(oid);
//			    int pst=orderTemp.getPayStatus();//==null?-1:orderTemp.getPayStatus();
//				String pay_status =String.valueOf(pst);
//				
//				//TMember tMember = this.findMemberByOid(oid);
//				
//				//如果未支付
//				if (!"2".equals(pay_status)) {
//					
//					SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					
//					orderTemp.setActualPay(Double.parseDouble(valideData.get("total_fee").toString()));
//					orderTemp.setPayStatus(2);
//					orderTemp.setPayedTime(simpledateformat.format(new Date()));
//					orderTemp.setPayType(new Integer(valideData.get("pc_pay_type")));
//					orderDao.updateMallOrder(orderTemp);
//					 
//					TPayLine payLine=new TPayLine();
//					payLine.setSurplus(Double.parseDouble(valideData.get("total_fee").toString()));
//					payLine.setPayType(new Integer(valideData.get("pc_pay_type")));
//					payLine.setHandleStatus(2);
//					payLine.setPayStatus(2);
//					payLine.setPaySource(valideData.get("orderId"));
//					
//					orderDao.updPayLineByOid(payLine);
//					/*this.SendOrderMsg(oid);*/
//					
//					SendMallOrderMsg(oid);
//				}
//				else{
//					logger.info(callBackType+"-(该订单状态是已经支付了):"+(new Date()).toLocaleString()+"  ");
//				}
//				logger.info(callBackType+"进入了支付回调end修改订单状态ok-updateOrder :"+(new Date()).toLocaleString()+"  ");
//		     
//			
//		}
//		
//	
	public HashMap<String, Object> doHandleRsCookie(HashMap<String, Object> map){
		String values[] = map.get("cookie").toString().split(",");
		String city = map.get("city").toString();
		
		return getCacheProInfo(values[0], values[1], city);
	}
	
	public HashMap<String, Object> doHandleCardCookie(HashMap<String, Object> map){
		HashMap<String, Object> reMap = new HashMap<String, Object>();
		String values[] = map.get("cookie").toString().split(",");
		TMember tMember = (TMember) map.get("tMember");
		HashMap<String, Object> product = cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT + values[0]);
		//List<HashMap<String, Object>> attr = CommHashMap.getHashMapAttrList(MemcachedConstant.YY_ATTR + values[0]);
		HashMap<String, Object> cardImg = cacheHashMap.getProductCardImgByPid(MemcachedConstant.TW_PRODUCT_CARDIMG + values[0]);
		
		reMap.put("product", product);
		//reMap.put("attr", attr);
		reMap.put("cardImg", cardImg);
		reMap.put("pid", values[0]);
		reMap.put("cardType", values[1]);
		reMap.put("quantity", values[2]);
		
//		TMemberCard memberCard = orderDao.findMemberCardByMid(String.valueOf(tMember.getMid()));
//		reMap.put("memberCard", memberCard);
		
		if(values[1].equals("1")){
			List<HashMap<String, Object>> address = orderDao.findMembersAddressByMid(tMember.getMid());
			reMap.put("address", address);
		}
		
//		List<Map> ratios = commonDao.getAllProductRatios();
//		for (int i = 0; i < ratios.size(); i++) {
//			if(ratios.get(i).get("PRODUCT_ID").toString().equals(values[0])){
//				reMap.put("ratios", ratios.get(i).get("DISTRIBUTIONNAME").toString());
//				break;
//			}
//		}
		
		return reMap;
	}
	
	public HashMap<String, Object> getCacheProInfo(String pid, String quantity, String city){
		HashMap<String, Object> productMap = new HashMap<String, Object>();
		HashMap<String, Object> product = cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT + pid);
		//List<HashMap<String, Object>> attr = cacheHashMap.getHashMapAttrList(MemcachedConstant.YY_ATTR + pid);
		List<String> medical = cacheHashMap.getBranchByPid(MemcachedConstant.TW_BRANCHBYPRODUCT + pid);
		
		product.put("SEX", product.get("SEX"));
		product.put("SHOP_PRICE", product.get("SHOP_PRICE"));
		product.put("MARKET_PRICE", product.get("MARKET_PRICE"));
		
		HashMap<String, Object> medicals = this.findAllShopFromPDetail(medical);
		productMap.put("areas", medicals.get("areas"));
		productMap.putAll(product);
		
		int coupon = 0;
		String payMent = productMap.get("PAY_TYPE").toString();
		if(!payMent.equals("1")){
			HashMap<String,Object> couponMap = productDao.getCouponByPid(pid);
			if(couponMap!=null && couponMap.size()>0){
				productMap.putAll(couponMap);
				coupon = 1;
			}
		}
		productMap.put("coupon", coupon);
		productMap.put("QUANTITY", quantity);
		return productMap;
	}
	
	public HashMap<String, Object> findAllShopFromPDetail(List<String> medical){
		
		HashMap<String, Object> map = new HashMap();
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
    	
    	//城市map
		Map<String, String> areaMap = new LinkedHashMap<String, String>();
		//门店ID 城市ID map
		Map<String, String> medicalMap = new LinkedHashMap<String, String>();
		//门店ID 门店信息map
		Map<String, String> tempMap = new LinkedHashMap<String, String>();
		//门店ID 门店地址map
		Map<String, String> addressMap = new LinkedHashMap<String, String>();
    	
    	for (int i = 0; i < medical.size(); i++) {
    		HashMap<String, Object> shopDetail = cacheHashMap.getBranchByEsid(MemcachedConstant.TW_BRANCH + medical.get(i));
    		list.add(shopDetail);
			String address = shopDetail.get("PROVINCENAME").toString() + shopDetail.get("CITYNAME").toString() + "市" + shopDetail.get("COUNTYNAME").toString() + shopDetail.get("ADDRESS").toString();
			areaMap.put(shopDetail.get("CITY").toString(), shopDetail.get("CITYNAME").toString());
			tempMap.put(shopDetail.get("ESID").toString(), shopDetail.get("CITY").toString());
			medicalMap.put(shopDetail.get("ESID").toString(), "【"+shopDetail.get("BNAME").toString()+"】" + shopDetail.get("ENAME").toString());
			addressMap.put(shopDetail.get("ESID").toString(), address);
		}
    	String medicals = JSONArray.fromObject(medicalMap).toString();
		String temp = JSONArray.fromObject(tempMap).toString();
		String address = JSONArray.fromObject(addressMap).toString();
    	map.put("areas", areaMap);
    	map.put("medicals", medicals);
    	map.put("address", address);
    	map.put("temp", temp);
    	map.put("shops", list);
    	return map;
    }
	
	public String doCreateReservation(HashMap<String, Object> map) throws IllegalAccessException, InvocationTargetException{
		List<String> name =  (List<String>) map.get("name");
		List<String> sex =  (List<String>) map.get("sex");
		List<String> idcard =  (List<String>) map.get("id");
		List<String> age =  (List<String>) map.get("age");
		List<String> mar =  (List<String>) map.get("mar");
		List<String> mobile =  (List<String>) map.get("mobile");
		
		SReservation sr = (SReservation) map.get("rs");
		String quantity = map.get("quantity").toString();
		String coupon = map.get("coupon").toString();
		
//		ArrayList<TReservation> list = new ArrayList<TReservation>();
		ArrayList<SReservation> list = new ArrayList<SReservation>();
		ArrayList<SOrderLine> orderLines = new ArrayList<SOrderLine>();
		
		Map<String, Object> calMap = new HashMap<String, Object>();
		calMap.put("day", sr.getCreaterTime());
		calMap.put("esid", sr.getEsid());
		calMap.put("count", quantity);
		String personCount = rlDao.findSingleDateCount(calMap);//可约人数
		if(!StringUtil.isBlank(personCount))if(Integer.parseInt(personCount) < Integer.parseInt(quantity)) return "分院可约人数超过上限,请选择其他分院";
		
		int id = orderDao.getOid();
		String oid = coupon.equals("1") ? "YY" + DateUtil.format(new Date(), "yyMMdd") + StringUtil.addLeftZero("" + id, 6) : "";
		
		HashMap<String, Object> product = cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT + sr.getPid());
		sr.setPName(product.get("PNAME").toString());
		sr.setMid((Integer)map.get("mid"));
		sr.setPostDate(SysUtils.getTime());
		sr.setStatus(0);
		for (int i = 0; i < Integer.parseInt(quantity); i++) {
			SReservation sr2 = new SReservation();
			BeanUtils.copyProperties(sr2, sr);
			sr2.setReCid(idcard.get(i));
			sr2.setReMarriage(Integer.parseInt(mar.get(i)));
			sr2.setReName(name.get(i));
			sr2.setReSex(Integer.parseInt(sex.get(i)));
			sr2.setReTel(mobile.get(i));
			sr2.setReYear(age.get(i));
			if(coupon.equals("1")){
				//在线支付预约
				SOrderLine orderLine = doCustractionLine(oid, String.valueOf(sr.getPid()), product);
				int olid = orderDao.getOid();
				orderLine.setOlid(String.valueOf(olid));
				orderLines.add(orderLine);
				
				sr2.setOlid(String.valueOf(olid));
				sr2.setRType(2);
			}else{
				//到店付款预约
				sr2.setRType(3);
			}
			list.add(sr2);
		}
		
		if(!StringUtil.isBlank(personCount)) rlDao.updateSingleDateCount(calMap);
		
		if(coupon.equals("1")) {
			SOrder order = new SOrder();
			//生成订单
			order.setOid(oid);
			order.setMid((Integer)map.get("mid"));
			order.setSumPrice(CommUtil.toFixNumber(Double.parseDouble(map.get("sumPrice").toString())));
			order.setIp((String)map.get("ip"));
			order.setPostDate(SysUtils.getTime());
			order.setFWhere("2");
			order.setOrderType(2);
			
			orderDao.addOrder(order);
			orderDao.addOrderLine(orderLines);
		}
		
		orderDao.batchAddReservation(list);
		
		return oid;
	}
	
	public SOrderLine doCustractionLine(String oid, String pid, HashMap<String, Object> product){
		String card = "Y" + DateUtil.format(new Date(), "yyMMdd") + RandomStringUtils.randomNumeric(3);
		String password = RandomStringUtils.randomNumeric(6);
		//生成订单项
		SOrderLine orderLine = new SOrderLine();
		orderLine.setOid(oid);
		orderLine.setPid(product.get("PID").toString());
		orderLine.setShopPrice(Double.parseDouble(product.get("SHOP_PRICE").toString()));
		orderLine.setName(product.get("PNAME").toString());
		orderLine.setPimg(product.get("IMG").toString());
		orderLine.setCardNumber(card);
		orderLine.setPassword(password);
		return orderLine;
	}
	
	public String doCreateOrder(HashMap<String, Object> map){
		String values[] = map.get("cookie").toString().split(",");
		TMember tMember = (TMember) map.get("tMember");
		String oid = "MC" + DateUtil.format(new Date(), "yyMMdd") + StringUtil.addLeftZero("" + orderDao.getOid(), 6);
		SOrder order = new SOrder();
//		TMemberCard memberCard = orderDao.findMemberCardByMid(String.valueOf(tMember.getMid()));
//		if(SysUtils.isEmpty(memberCard)){
//			doAddMemberCard(tMember);
//		}
//		map.put("oid", oid);
//		map = commonService.CreditOrCouponChangeInfo(map);
		
		List<SOrderLine> orderLines = new ArrayList<SOrderLine>();
		HashMap<String, Object> product = cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT + values[0]);
		//List<HashMap<String, Object>> attr = CommHashMap.getHashMapAttrList(MemcachedConstant.YY_ATTR + values[0]);
		for (int i = 0; i < Integer.parseInt(values[2]); i++) {
			SOrderLine orderLine = new SOrderLine();
			orderLine.setOlid(String.valueOf(orderDao.getOid()));
			orderLine.setOid(oid);
			orderLine.setPid(values[0]); // pid
			orderLine.setShopPrice(Double.valueOf(product.get("SHOP_PRICE").toString())); // shopPrice
			orderLine.setName(product.get("PNAME").toString());
			orderLine.setPimg(product.get("IMG").toString());
			if(values[1].equals("0")){
				order.setOrderType(1);
				orderLine.setCardNumber("X" + DateUtil.format(new Date(), "yyMMdd") + RandomStringUtils.randomNumeric(3));
				orderLine.setPassword(RandomStringUtils.randomNumeric(6));
				orderLines.add(orderLine);
			}else{
				order.setOrderType(3);
				orderLines.add(orderLine);
			}
		}
		
		orderDao.addOrderLine(orderLines);
		
		if(!map.get("caid").toString().equals("0")){
			orderDao.updMembersDefaultAdd(tMember.getMid());
			orderDao.updMembersDefaultAddByCaid(Integer.parseInt(map.get("caid").toString()));
			order.setCaid(Integer.parseInt(map.get("caid").toString()));
		}
		
		order.setOid(oid);
		order.setMid(tMember.getMid());
		order.setSumPrice(CommUtil.toFixNumber(Double.parseDouble(map.get("sumPrice").toString())));
		order.setIp((String)map.get("ip"));
		order.setPostDate(SysUtils.getTime());
		order.setFWhere("2");
		orderDao.addOrder(order);
		
		return String.valueOf(oid);
	}
//	
//	public void doAddMemberCard(TMember tMember){
//		try {
//			TMemberCard tMemberCard = new TMemberCard();
//			int MCId = productDao.getMCId().getSeq();
//
//			Date date = new Date();
//
//			String MD5Date = null;
//
//			MD5Date = SysUtils.retMd5Pwd(SysUtils.format(date, "yyyyMMddHHmmss"));
//
//			tMemberCard.setMcid(MCId);
//			tMemberCard.setMcno(tMember.getAccount() + "-" + MD5Date.substring(0, 15));
//			tMemberCard.setMcStatus("2");
//			tMemberCard.setMlid(1);
//			tMemberCard.setMid(tMember.getMid());
//			tMemberCard.setBalance(0);
//			tMemberCard.setCurrentCredit(0);
//			tMemberCard.setActiveDate(SysUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
//			tMemberCard.setPostDate(SysUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
//			tMemberCard.setCreateType("1");
//
//			registerDao.addMemberCard(tMemberCard);
//
//			Map maps = new HashMap();
//			maps.put("tMember", tMember);
//			maps.put("type", 1);
//			commonService.changeMemberCc(maps);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	public int addMemberAddress(SConsigneeAddress add){
		List<HashMap<String, Object>> address = orderDao.findMembersAddressByMid(add.getMid());
		String temp = add.getName() + add.getProvince() + add.getCity() + add.getCounty() + add.getAddress();
		for (HashMap<String, Object> hashMap : address) {
			String match = hashMap.get("NAME").toString() + hashMap.get("PROVINCE").toString() + hashMap.get("CITY").toString() + hashMap.get("COUNTY").toString() + hashMap.get("ADDRESS").toString();
			if(temp.equals(match)){
				return 0;
			}
		}
		if(add.getIsDefault() == 1) orderDao.updMembersDefaultAdd(add.getMid());
		int caid = orderDao.addAddress(add);
		return caid;
	}
	
	public void updateMemberAddress(SConsigneeAddress add){
		if(add.getIsDefault().equals("1")) orderDao.updMembersDefaultAdd(add.getMid());
		orderDao.updateAddressByCaid(add);
	}

//	@Override
//	public HashMap<String, Object> findRpOrderLineByOid(
//			HashMap<String, Object> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
	@Override
	public HashMap<String, Object> getOrderInfoByOid(String oid) {
		HashMap<String, Object> infoMap=new HashMap<String, Object>();

		List<HashMap<String, Object>> productList=null;	
		
		//若是平台订单，则获取预约信息
		if(oid.indexOf("YY")>=0){
			productList=orderDao.findReservationByOid(oid);
		}else{
			productList=orderDao.findProductByOid(oid);	
		}
		if(productList.size()<=0){
			//此判断 为避免预约单不存在导致获取不到订单商品信息
			productList=orderDao.findProductByOid(oid);
		}
		double sumCpPrice=0;
		double sumOrderPrice=0;
		for(int p=0;p<productList.size();p++){
			HashMap<String, Object> pro =cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT + productList.get(p).get("PID").toString());
			HashMap<String, Object> medicalCardImg =  cacheHashMap.getProductCardImgByPid(MemcachedConstant.TW_PRODUCT_CARDIMG + productList.get(p).get("PID").toString());//获取商品模板信息
			productList.get(p).put("PID",pro.get("PID"));
			productList.get(p).put("CSID",pro.get("CSID"));
			productList.get(p).put("PNAME",pro.get("PNAME"));
			productList.get(p).put("BNAME",pro.get("BNAME"));
			productList.get(p).put("IMG",pro.get("IMG"));
			productList.get(p).put("SHOP_PRICE",pro.get("SHOP_PRICE"));
			productList.get(p).put("MARKET_PRICE",pro.get("MARKET_PRICE"));
			productList.get(p).put("SEX",pro.get("SEX"));
			productList.get(p).putAll(medicalCardImg);
			sumOrderPrice+=Double.parseDouble(pro.get("SHOP_PRICE").toString());
			
			// 在线支付立减金额
			int coupon = 0;
			String payMent = pro.get("PAY_TYPE").toString();
			if(!payMent.equals("1")){
				HashMap<String,Object> couponMap = productDao.getCouponByPid(productList.get(p).get("PID").toString());
				if(couponMap!=null && couponMap.size()>0){
					//productList.get(p).putAll(couponMap);
					coupon = 1;
					sumCpPrice+=Double.parseDouble(couponMap.get("PRICE").toString());
				}
			}
			productList.get(p).put("coupon", coupon);
			// 促销信息
//			List<HashMap<String, Object>> promotions = CommHashMap.getHashMapPromotionList(MemcachedConstant.YY_PROMOTION + productList.get(p).get("PID").toString());
//			productList.get(p).put("promotions", promotions);
		};
		
		//订单基础信息
		HashMap<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("oid", oid);
		HashMap<String, Object> orderDetail=orderDao.getOrderDetailByOid(paramMap);
		
		
		
		//体检卡实体卡订单
//		if("3".equals(orderDetail.get("ORDER_TYPE"))){
//			//获取实体卡发货地址
//			HashMap<String,Object> consigneeAddress = 
//		}
		
		
		//订单 积分&优惠券 抵扣
//		if(oid.indexOf("YY")<0){
//		TCreditLog creditLog = orderDao.findCreditLogByOid(oid);
//		if(!SysUtils.isEmpty(creditLog)){
//			int credit = String.valueOf(creditLog.getOpCredit()) == null ? 0 : creditLog.getOpCredit();
//			double coupon = String.valueOf(creditLog.getOpCoupon()) == null ? 0 : creditLog.getOpCoupon();
//			creditLog.setOpCredit(credit);
//			creditLog.setOpCoupon(coupon);
//		}
//		orderDetail.put("creditLog", creditLog);
//		}
		orderDetail.put("sumCpPrice", Double.toString(sumCpPrice));
		orderDetail.put("sumOrderPrice", Double.toString(sumOrderPrice));
		infoMap.put("orderDetail", orderDetail);
		infoMap.put("product", productList);
		return infoMap;
	}
//	
//	
//	
//	public Map orderOverdueSimulation(String oid) {
//		Map returnMap = new HashMap();
//		if(oid.indexOf("YY") > -1){
//			RPorder order = orderDao.findOrderByOid(oid);
//			TMember tMember = orderDao.findMemberByOid(oid);
//			if (!SysUtils.isEmpty(order)) {
//				Map map = new HashMap();
//				map.put("orderId", String.valueOf(order.getOid()));
//				map.put("total_fee", String.valueOf(order.getSumPrice()));
//				map.put("wap_pay_type", "10001");
//				updateOrder(map);
//
//				//SendOrderMsg(String.valueOf(order.getOid()));
//
//				returnMap.put("flag", "true");
//				returnMap.put("order", order);
//			} else {
//				returnMap.put("flag", "false");
//			}
//		}else{
//			TPorder order = orderDao.findMallOrderByOid(oid);
//			TMember tMember = orderDao.findMallMemberByOid(oid);
//			if (!SysUtils.isEmpty(order)) {
//				Map map = new HashMap();
//				map.put("orderId", String.valueOf(order.getOid()));
//				map.put("total_fee", String.valueOf(order.getSumPrice()));
//				map.put("wap_pay_type", "10001");
//				updateMallOrder(map);
//
//				//SendOrderMsg(String.valueOf(order.getOid()));
//
//				returnMap.put("flag", "true");
//				returnMap.put("order", order);
//			} else {
//				returnMap.put("flag", "false");
//			}
//		}
//		return returnMap;
//	}
//
//	public String SendMallOrderMsg(String oid){
//		try {
//			List<Map> orderList = orderDao.findOrderInfoByOid(oid);
//			Map map = new HashMap();
//			if(orderList.size() > 0){
//				int virtualCount = 0;
//				int entityCount = 0;
//				for (int i = 0; i < orderList.size(); i++) {
//					if("0".equals(orderList.get(i).get("CARD_TYPE").toString())){
//						virtualCount++;
//					}else{
//						entityCount++;
//					}
//				}
//				if(virtualCount > 0){
//					String name = orderList.get(0).get("VNAME").toString();
//					String phone = orderList.get(0).get("VMOBILE").toString();
//					Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
//					Matcher m = p.matcher(name);
//					if(m.matches()){
//						StringBuffer nameBuffer = new StringBuffer();
//						name = nameBuffer.append(name.substring(0, 3)).append("****").append(name.substring(7, 11)).toString();
//					}
//					map.put("mobile", phone);
//					map.put("oid", oid);
//					map.put("account", name);
//					map.put("type", "paySuccess");
//					commonService.checkTodayMsgCount(map);
//					map.clear();
//					
//					int k = 0;
//					for (int i = 0; i < virtualCount % 10 + 1; i++) {
//						if(k == 0){
//							StringBuffer buffer = new StringBuffer();
//							for (int j = i * 10; j < 10 * (i + 1); j++) {
//								if(j < virtualCount && orderList.get(j).get("CARD").toString() != null && orderList.get(j).get("PASSWORD").toString() != null){
//									String card = orderList.get(j).get("CARD").toString();
//									String password = orderList.get(j).get("PASSWORD").toString();
//									buffer.append("卡号："+card+"\\密码："+password+"，");
//								}else{
//									k = 999;
//									break;
//								}
//							}
//							map.put("mobile", phone);
//							map.put("account", name);
//							map.put("oid", oid);
//							map.put("count", String.valueOf(virtualCount));
//							map.put("content", buffer.substring(0, buffer.length()-1).toString());
//							map.put("type", "vitualCardInfo");
//							commonService.checkTodayMsgCount(map);
//							map.clear();
//						}
//					}
//				}
//				if(entityCount > 0){
//					TConsigneeAddress address = orderDao.findDefaultAddressByMid(orderList.get(0).get("MID").toString());
//					String name = address.getName();
//					String phone = address.getMobile() == null ? address.getTel() : address.getMobile();
//					map.put("mobile", phone);
//					map.put("account", name);
//					map.put("oid", oid);
//					map.put("type", "entityCardInfo");
//					commonService.checkTodayMsgCount(map);
//				}
//				return "短信发送成功";
//			}else{
//				return "获取订单信息失败";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.info("发送短信出错");
//			logger.info("发送短信出错日志："+e.toString());
//		}
//		return "";
//	}
//
	@Override
	public List<HashMap<String, Object>> findPidsByOid(String oid) {
		List<HashMap<String, Object>> productList=orderDao.findPidsByOid(oid);
		return productList;
	}
	
	/**
	 * 修改订单状态
	 * @param orderMap
	 * @author 康良玉
	 */
	
	public void updateOrder(SOrder sorder) {
		logger.info("修改订单状态 beg：");
		String oid = sorder.getOid();
		
		SOrder sorder2 = orderDao.findOrderByOid(oid);
		logger.info("订单信息："+sorder2.toString());
		
		if(sorder2.getPayStatus()!=2){
			sorder2.setPayStatus(2);
			logger.info("修改信息："+sorder.toString());
			orderDao.updateOrder(sorder);
			logger.info("修改订单状态 end：");
		}
		
	}

	/**
	 * 通过oid获取支付成功页面显示的订单信息
	 * @param oid
	 * @return
	 * @author 康良玉
	 */

	public HashMap<String, Object> findPaySuccessInfoByOid(String oid) {
		HashMap<String, Object> info = new HashMap<>();
		SOrder sorder = orderDao.findOrderByOid(oid);
		List<HashMap<String, Object>> productList = orderDao.findProductInfoByOid(oid);
		
		info.put("order", sorder);
		info.put("productList", productList);
		
		return info;
	}
	/**
	 * 到店付款预约 发送 短信和微信 通知信息
	 * @return
	 * @author 秦光耀
	 */
	@Override
	public void sendMessage(HashMap<String, Object> map) {
		Map<String, String> param=new HashMap<String, String>();
		//param.put("name", map.get("name").toString());
		//param.put("date", param.get("orderTime").toString());
		//String address=orderDao.getAddressByEsid(param.get("esid").toString());
		//param.put("address", address);
		try {
			if(map.get("type").equals("1")){
				//在线预约 到店付款
				List<String> name =  (List<String>) map.get("name");
				List<String> sex =  (List<String>) map.get("sex");
				List<String> idcard =  (List<String>) map.get("id");
				List<String> age =  (List<String>) map.get("age");
				List<String> mar =  (List<String>) map.get("mar");
				List<String> mobile =  (List<String>) map.get("mobile");
				String quantity = map.get("quantity").toString();
				SReservation sr = (SReservation) map.get("rs");
				String wxName="";
				String address=orderDao.getAddressByEsidNoProvince(sr.getEsid().toString());
				for (int i = 0; i < Integer.parseInt(quantity); i++) {
					String se="先生";
					if(sex.get(i).equals("1")){
						se="女士";
					}
							
					param.put("name", name.get(i)+se);
					param.put("date", sr.getCreaterTime());
					param.put("address", address);
					commonService.sendMessage(param, MsgConstant.TMPL_RESERVATION_NOTIFY, mobile.get(i));
					wxName+=name.get(i)+se+",";
					HashMap<String, Object> addmsgMap=new HashMap<String, Object>();
					addmsgMap.put("type", "7");
					addmsgMap.put("mobile", mobile.get(i));
					addmsgMap.put("user", map.get("mid"));
					orderDao.addMsgrecord(addmsgMap);
				}
				//微信推送消息
				Map<String, String> wxMap=new HashMap<String, String>();
				if(wxName.length()>0){
					wxName= wxName.substring(0,wxName.length()-1);
				}
				wxMap.put("uname", wxName);
				wxMap.put("pname", sr.getPName());
				wxMap.put("address", address);
				wxMap.put("orderTime", sr.getCreaterTime());
				wxMap.put("openId", map.get("openId").toString());
				commonService.sendWechatTemplate(wxMap); 
				
			}else{
				//体检卡卡密预约
				//短信
				SReservation sReservation=(SReservation) map.get("sReservation");
				String se="先生";
				String sex=sReservation.getReSex().toString();
				if(sex.equals("1")){
					se="女士";
				}
				param.put("name", sReservation.getReName()+se);
				param.put("date", sReservation.getCreaterTime());
				param.put("address", map.get("address").toString());
				commonService.sendMessage(param, MsgConstant.TMPL_RESERVATION_NOTIFY, sReservation.getReTel());
				HashMap<String, Object> addmsgMap=new HashMap<String, Object>();
				addmsgMap.put("type", "8");
				addmsgMap.put("mobile", sReservation.getReTel());
				addmsgMap.put("user", map.get("mid"));
				orderDao.addMsgrecord(addmsgMap);
				//微信推送消息
				Map<String, String> wxMap=new HashMap<String, String>();
				wxMap.put("uname", sReservation.getReName());
				wxMap.put("pname", sReservation.getPName());
				wxMap.put("orderTime", sReservation.getCreaterTime());
				wxMap.put("address", map.get("address").toString());
				wxMap.put("openId", map.get("openId").toString());
				commonService.sendWechatTemplate(wxMap); 
				
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

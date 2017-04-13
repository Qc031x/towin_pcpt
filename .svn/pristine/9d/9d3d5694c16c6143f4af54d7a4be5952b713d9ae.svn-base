package com.sgfm.datacenter.service.user.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgfm.datacenter.dao.user.UserDao;
import com.sgfm.datacenter.entity.TMember;
import com.sgfm.datacenter.entity.TUserMsgrecord;
import com.sgfm.datacenter.service.user.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	public TMember findMemByOpenidOrMobile(TMember tMember){
		return userDao.findMemByOpenidOrMobile(tMember);
	}
	
	public int doRegisterNewMember(TMember tMember){
		return userDao.doRegisterNewMember(tMember);
	}
	
	public void updMemberByOpenid(TMember tMember){
		userDao.updMemberInfoByOpenid(tMember);
	}
    
	public HashMap<String,Object> getPerCenterInfo(TMember tMember){
		HashMap<String,Object> infoMap = new HashMap<String,Object>();
		infoMap.put("orderCount", userDao.countNoPayedOrder(tMember));//我的订单(未付款数量)
		infoMap.put("reservationCount", userDao.countNoCheckedReservation(tMember));//我的预约(未审核数量)
		infoMap.put("cardCount", userDao.countNoReveredCard(tMember));//我的卡密(未预约数量)
		return infoMap;
	}

	@Override
	public void changeOrderIsValid(String oid) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("oid", oid);
		userDao.changeOrderIsValid(map);	
	}
	
	public int addSecuritycode(TUserMsgrecord msgrecord){
		return userDao.addSecuritycode(msgrecord);
	}
	
	public HashMap<String, Object> findMsgCodeByIdAndMobile(HashMap<String, Object> param){
		return userDao.findMsgCodeByIdAndMobile(param);
	}

}

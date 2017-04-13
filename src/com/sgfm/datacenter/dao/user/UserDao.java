package com.sgfm.datacenter.dao.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sgfm.base.dao.BaseDao;
import com.sgfm.datacenter.entity.TArea;
import com.sgfm.datacenter.entity.TMember;
import com.sgfm.datacenter.entity.TUserMsgrecord;

@Repository
public class UserDao extends BaseDao{
      
	public TArea findAreaById(int id){
		return (TArea)super.getSqlMapClientTemplate().queryForObject("UserMap.findAreaById", id);
	}
//      
//	public TMember findMemberByAccountOrMobile(TMember tMember){
//		return (TMember) super.getSqlMapClientTemplate().queryForObject("UserMap.findMemberByAccountOrMobile", tMember);
//	}
	
	public TMember findMemByOpenidOrMobile(TMember tMember){
		return (TMember) super.getSqlMapClientTemplate().queryForObject("UserMap.findMemByOpenidOrMobile", tMember);
	}
	
	public void updMemberInfoByOpenid(TMember tMember){
		super.getSqlMapClientTemplate().update("UserMap.updMemberInfoByOpenid", tMember);
	}
	
	public int doRegisterNewMember(TMember tMember){
		return (int) super.getSqlMapClientTemplate().insert("UserMap.doRegisterNewMember", tMember);
	}
	
	public Integer countNoPayedOrder(TMember tMember){
		return (Integer)super.getSqlMapClientTemplate().queryForObject("UserMap.countNoPayedOrder",tMember);
	}
	
	public Integer countNoCheckedReservation(TMember tMember){
		return (Integer)super.getSqlMapClientTemplate().queryForObject("UserMap.countNoCheckedReservation",tMember);
	}
	
	public Integer countNoReveredCard(TMember tMember){
		return (Integer)super.getSqlMapClientTemplate().queryForObject("UserMap.countNoReveredCard",tMember);
	}
	
	public void changeOrderIsValid(HashMap<String, Object> map) {
		super.getSqlMapClientTemplate().update("UserMap.changeOrderIsValid",map);
	}
	
	public int addSecuritycode(TUserMsgrecord msgrecord){
		return (int) super.getSqlMapClientTemplate().insert("UserMap.addSecuritycode", msgrecord);
	}
	
	public HashMap<String, Object> findMsgCodeByIdAndMobile(HashMap<String, Object> param){
		return (HashMap<String, Object>) super.getSqlMapClientTemplate().queryForObject("UserMap.findMsgCodeByIdAndMobile", param);
	}
}

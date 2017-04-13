package com.sgfm.datacenter.service.user;

import java.util.HashMap;

import com.sgfm.datacenter.entity.TMember;
import com.sgfm.datacenter.entity.TUserMsgrecord;


public interface UserService {
	
	public TMember findMemByOpenidOrMobile(TMember tMember);
	
	public int doRegisterNewMember(TMember tMember);
	
	public void updMemberByOpenid(TMember tMember);
	
	public HashMap<String,Object> getPerCenterInfo(TMember tMember);

	public void changeOrderIsValid(String oid);
	
	public int addSecuritycode(TUserMsgrecord msgrecord);
	
	public HashMap<String, Object> findMsgCodeByIdAndMobile(HashMap<String, Object> param);
}

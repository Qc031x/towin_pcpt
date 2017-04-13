package com.sgfm.datacenter.dao.card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sgfm.base.dao.BaseDao;
import com.sgfm.datacenter.entity.CgVariable;

@Repository
@SuppressWarnings("unchecked")
public class CardDao extends BaseDao{
	
	public List<HashMap<String,Object>> findCardListByCondition(HashMap<String,Object> map){
    	return super.getSqlMapClientTemplate().queryForList("CardMap.findCardListByCondition",map);
    }
	
	public int findCardListCount(HashMap<String,Object> map){
    	return (Integer) super.getSqlMapClientTemplate().queryForObject("CardMap.findCardListCount", map);
    }

	public List<HashMap<String, Object>> getProductTypeList() {
		return super.getSqlMapClientTemplate().queryForList("CardMap.getProductTypeList");
	}

}

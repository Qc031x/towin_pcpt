package com.sgfm.datacenter.dao.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sgfm.base.dao.BaseDao;
import com.sgfm.datacenter.entity.TAutoStrategy;
import com.sgfm.datacenter.entity.THolidayStrategy;
import com.sgfm.datacenter.entity.TPlaydayStrategy;
import com.sgfm.datacenter.entity.TSigndayStrategy;

/**
 * 
 * 
 * @author cliu
 * 
 */
@Repository
public class RlDao extends BaseDao {
	/**
	 * 查询门店的默认策略。<br>
	 */
	public TAutoStrategy getAutoStgByEsidAndDate(String esid) {
		return (TAutoStrategy) super.getSqlMapClientTemplate().queryForObject("StrategyMap.getAutoStgByEsidAndDate", esid);
	}

	public List<Map<String, Object>> getReservationByEcd2(Map<String, Object> map) {
		return (List<Map<String, Object>>) super.getSqlMapClientTemplate().queryForList("StrategyMap.getReservationByEcd", map);
	}

	public List<Map<String,Object>> getReservationByEcd(Map<String,Object> map)
    {
        return (List<Map<String,Object>>)super.getSqlMapClientTemplate().queryForList("StrategyMap.getReservationByEcd",map);
    }

	public TPlaydayStrategy getPlayStgByEsid(String esid) {
		return (TPlaydayStrategy) super.getSqlMapClientTemplate().queryForObject("StrategyMap.getPlayStgByEsid", esid);
	}

	public List<THolidayStrategy> getHolidStgByEsid(Map<String, Object> map) {
		return (List<THolidayStrategy>) super.getSqlMapClientTemplate().queryForList("StrategyMap.getHolidStgByEsid", map);
	}

	public List<TSigndayStrategy> getSignStgByEsid(Map<String, Object> map) {
		return (List<TSigndayStrategy>) super.getSqlMapClientTemplate().queryForList("StrategyMap.getSignStgByEsid", map);
	}

	public Integer getRevCountByEsid(Map<String, Object> map) {
		return (Integer) super.getSqlMapClientTemplate().queryForObject("StrategyMap.getRevCountByEsid", map);
	}
	
	public void updateSingleDateCount(Map<String, Object> map){
		super.getSqlMapClientTemplate().update("StrategyMap.updateSingleDateCount", map);
	}
	
	public String findSingleDateCount(Map<String, Object> map){
		return (String) super.getSqlMapClientTemplate().queryForObject("StrategyMap.findSingleDateCount", map);
	}

}

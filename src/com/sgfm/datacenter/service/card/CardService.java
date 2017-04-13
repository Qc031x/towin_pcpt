package com.sgfm.datacenter.service.card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sgfm.datacenter.entity.CgVariable;

public interface CardService {

	public HashMap<String,Object> findCardList(HashMap<String,Object> map);
	
	public List<HashMap<String, Object>> getProductTypeList();
}

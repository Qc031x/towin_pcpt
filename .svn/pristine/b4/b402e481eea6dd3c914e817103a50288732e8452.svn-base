package com.sgfm.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @author LiuPing
 *数据存取的基类,后续开发的DAO必须继承此类
 */
public class BaseDao extends  SqlMapClientDaoSupport{

	/*
	 * 向父类属性 sqlMapClient注入SqlMapClient对象
	 * */
	@Autowired
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
}

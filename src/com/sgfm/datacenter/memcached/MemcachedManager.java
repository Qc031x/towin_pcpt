package com.sgfm.datacenter.memcached;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.danga.MemCached.MemCachedClient;
import com.sgfm.base.util.PropsLoader;
import com.sgfm.datacenter.util.AppContext;

/**
 * 初始化缓存
 * @author kangliangyu
 *
 */
@Component
public class MemcachedManager {
	
	@Autowired
	private MemCachedClient  mcc;
	
	@Autowired
	private TypeCache typeCache;
	
	@Autowired
	private CityCache cityCache;
	
	@Autowired
	private ProductCache productCache;
	
	@Autowired
	private BranchCache branchCache;
	
	public void cacheInitialization() {
		
		if(mcc == null){
			ApplicationContext app = AppContext.getAppContext();
			mcc=(MemCachedClient)app.getBean("memcachedClient");
			typeCache = (TypeCache)app.getBean("typeCache");
		}
		
		mcc.flushAll();
		
		typeCache.typeCacheInitialization();		//商品类型和机构类型缓存
		cityCache.cityCacheInitialization();    	//城市的区域和品牌缓存
		productCache.productCacheInitialization();	//商品缓存
		branchCache.branchCacheInitialization();	//分院缓存
		
	}
	
	
}

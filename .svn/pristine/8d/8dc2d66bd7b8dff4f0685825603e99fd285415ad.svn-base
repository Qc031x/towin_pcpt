package com.sgfm.datacenter.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.sgfm.datacenter.memcached.CityCache;
import com.sgfm.datacenter.memcached.TypeCache;
import com.sgfm.datacenter.util.AppContext;

/**
 * 定时更新部分缓存
 * @author kangliangyu
 *
 */
public class DimingUpdateSectionCache extends QuartzJobBean{
	
	public DimingUpdateSectionCache() {
		super();
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		ApplicationContext app = AppContext.getAppContext();
		TypeCache typeCache = (TypeCache) app.getBean("typeCache");
		CityCache cityCache = (CityCache) app.getBean("cityCache");
		
		typeCache.typeCacheInitialization();		//商品类型和机构类型缓存
		cityCache.cityCacheInitialization();    	//城市的区域和品牌缓存
	}

}
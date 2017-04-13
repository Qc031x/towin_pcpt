package com.sgfm.datacenter.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.sgfm.datacenter.memcached.MemcachedManager;
import com.sgfm.datacenter.util.AppContext;

/**
 * 定时更新所有缓存
 * @author kangliangyu
 *
 */
public class DimingUpdateAllCache extends QuartzJobBean{
	
	public DimingUpdateAllCache() {
		super();
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		ApplicationContext app = AppContext.getAppContext();
		MemcachedManager memcachedManager = (MemcachedManager) app.getBean("memcachedManager");
		
		memcachedManager.cacheInitialization();
	}
	
}

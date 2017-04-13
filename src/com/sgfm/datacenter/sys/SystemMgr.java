package com.sgfm.datacenter.sys;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sgfm.base.util.PropsLoader;
import com.sgfm.datacenter.memcached.MemcachedManager;

/**
 * 初始化
 * @author kangliangyu
 *	因为tomcat初始化的时候会通过web.xml的加载spring_*.xml配置文件，再还没有加载完的时候会进入首页拦截器，首页拦截器里面又需要通过Spring容器获取bean对象，
 *	这个时候获取不到bean对象，所以调用AppContext又加载了一次spring_*.xml配置文件,然后这个初始化方法会运行两次，所以通过i来控制它只运行一次
 */
@Component
public class SystemMgr {
	private static Logger logger = Logger.getLogger(SystemMgr.class.getName());
	
	private static int i = 0;

	@Autowired
	private MemcachedManager mc;
	
	@Autowired
	private PropsLoader propsLoader;

	@PostConstruct
	public void init() {
		
		if(i == 0){
			new Thread() {
				@Override
				public void run() {
					try{
						i++;
						Thread.sleep(6*1000);
		            		
					}catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
					
					String cacheLoaded=propsLoader.props.getProperty("cacheLoaded");
					
					if("true".equals(cacheLoaded)){
						logger.info("缓存初始化开始！");
						mc.cacheInitialization();
						logger.info("缓存初始化结束！");
					}
					
				}
			}.start();
		}
		
	}

}

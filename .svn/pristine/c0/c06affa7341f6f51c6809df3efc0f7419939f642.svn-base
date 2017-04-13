package com.sgfm.datacenter.memcached;

/**
 * IoDH单例模式
 * 单例模式的对象线程安全了，如果方法里面存在全局变量需要加锁
 *
 */
public class MemcachedUtil {
	
	/**
	 * 静态内部类
	 *
	 */
	private static class HolderClass {  
        private final static MemcachedUtil memcachedUtil = new MemcachedUtil();  
	} 
	
	/**
	 * 获取单例对象
	 * @return
	 */
	public static MemcachedUtil getInstance() {  
        return HolderClass.memcachedUtil;  
    }
	
	/**
	 * 过滤null
	 * @param object
	 * @return
	 * @author kangliangyu
	 */
	public String getString(Object object){
		if(object == null) return "";
		return object.toString();
	}

}

package com.sgfm.datacenter.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 有生存期限的安全的MAP。
 * @author cliu
 *
 * @param <K>
 * @param <V>
 */
public class MyConcurrentHashMapNew<K, V> extends ConcurrentHashMap<K, V>{
	private static final long serialVersionUID = 1L;
	Map<K, Long> time = new ConcurrentHashMap<K, Long>(); 
	
	
	/**
	 * 
	 * @param time 生存时间，单位：秒
	 */
	public MyConcurrentHashMapNew() {
		new Thread(){

			@Override
			public void run() {
				while(true){
					try{
						long curTime = System.currentTimeMillis();
						for(Map.Entry<K, Long> item : time.entrySet()){
							if(curTime>=item.getValue()){
								MyConcurrentHashMapNew.this.remove(item.getKey());
							}
						}
						Thread.sleep(5*1000);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}.start();
	}

	public V put(K key, V value, long time) {
		this.time.put(key, System.currentTimeMillis()+time*1000);
		return super.put(key, value);
	}
	
}
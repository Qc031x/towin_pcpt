package com.sgfm.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class PropsLoader {

	
	private List<String> locations;
	public Properties props;
	
	public PropsLoader(List<String> locations) {
		super();
		this.locations = locations;
		props = new Properties();
		if(locations != null && locations.size() > 0){
			for (String path : locations) {
				try {
					copyProp(props, readProp(path));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void copyProp(Properties rootProp,Properties subProp){
		Iterator<Object> iter = subProp.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			rootProp.setProperty(key, subProp.getProperty(key));
		}
	}
	
	private Properties readProp(String classPathLocation) throws Exception{
		Properties prop = new Properties();
		InputStream in = PropsLoader.class.getResourceAsStream("/"+classPathLocation);
		prop.load(in);
		in.close();
		return prop;
	}
	
	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}
	
}

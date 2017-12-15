package com.mx.sab.properties;

import java.util.Properties;
import java.util.Set;

public class PropertiesManager {
	
	
	public static PropertiesManager instance;
	
	private static Properties properties;
	
	
	private PropertiesManager(){
		properties = new Properties();
	}
	
	
	public static PropertiesManager getInstance(){
		if (instance==null){
			instance = new PropertiesManager();
		}
		return instance;
	}
	
	public synchronized String getValue(PropertiesKeys propertiesKeys){
		return properties.getProperty(propertiesKeys.getKey());
	}
	
	protected static void addProperties(Properties properties){
		getInstance();
		Set<?>keys = properties.keySet();
		for (Object key : keys){
			Object value = properties.get(key);
			PropertiesManager.properties.put(key, value);
		}
	}

}

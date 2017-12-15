package com.mx.sab.properties;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class CustomPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {


	@Override
	protected void convertProperties(Properties properties) {
		PropertiesManager.addProperties(properties);
		super.convertProperties(properties);
	}

}

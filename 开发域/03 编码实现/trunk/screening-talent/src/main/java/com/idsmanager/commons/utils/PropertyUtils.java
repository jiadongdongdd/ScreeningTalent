package com.idsmanager.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

	/**
	 * 采用静态方法
	 */
	public static Properties props = new Properties();

	public static String getProp(String prop) {
		InputStream in = PropertyUtils.class
				.getResourceAsStream("/TScAcntProp.properties");
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = (String) p.get(prop);

		return value;
	}

	public static String getInitProp(String prop) {
		InputStream in = PropertyUtils.class
				.getResourceAsStream("/initSystem.properties");
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = (String) p.get(prop);

		return value;
	}

	public static String getMessageProp(String key, String propPath) {

		InputStream inputStream = PropertyUtils.class
				.getResourceAsStream(propPath);
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = (String) p.get(key);
		return value;

	}

}

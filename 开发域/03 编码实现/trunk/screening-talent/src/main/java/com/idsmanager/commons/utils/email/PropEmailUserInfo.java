package com.idsmanager.commons.utils.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropEmailUserInfo {
	
	public static String getUserInfo(String name){
		InputStream in = PropEmailUserInfo.class.getResourceAsStream("/xsifter.properties");
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
		String value =  (String) p.get("mail."+name);
		
		return value;
	}
}

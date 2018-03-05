package com.idsmanager.xsifter.web.controller.company.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.idsmanager.xsifter.domain.security.SecurityUtils;

public class SessionListener implements HttpSessionListener {
	
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
			String username = SecurityUtils.username();
			IpFilter.map.remove(username);
			
	}
	
}

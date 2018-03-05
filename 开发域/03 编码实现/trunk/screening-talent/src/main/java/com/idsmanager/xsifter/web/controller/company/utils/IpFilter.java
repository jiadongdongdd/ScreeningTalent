package com.idsmanager.xsifter.web.controller.company.utils;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.idsmanager.commons.web.WebUtils;
import com.idsmanager.xsifter.domain.security.SecurityUtils;


public class IpFilter implements Filter {
	
	public static ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();

	@Override
	public void destroy() {
		String username = SecurityUtils.username();
		map.remove(username);
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		String ip = WebUtils.getIp();
		String username = SecurityUtils.username();
		IpUtils ips = new IpUtils(username,ip);
		IpUtils when = (IpUtils) map.get(username);
		
		if(null==when){
			map.put(username, ips);
		}
		
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}


}

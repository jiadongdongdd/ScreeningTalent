package com.idsmanager.xsifter.web.controller.company.utils;

import java.util.Date;

import com.idsmanager.commons.utils.DateUtils;

public class IpUtils {
	
	private String username;
	
	private String ip;
	
	private Date date = DateUtils.now();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public IpUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IpUtils(String username, String ip) {
		super();
		this.username = username;
		this.ip = ip;
	}
	
	
}

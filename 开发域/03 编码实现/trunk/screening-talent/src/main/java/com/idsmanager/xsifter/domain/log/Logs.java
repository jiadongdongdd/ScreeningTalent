package com.idsmanager.xsifter.domain.log;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.AbstractDomain;

@Document(collection = "Logs")
public class Logs extends AbstractDomain {

	private static final long serialVersionUID = 7822007829415380172L;

	private String username;// 用户名

	private String ip;// 登录ip

	private Date date = DateUtils.now();// 登录时间

	public Logs() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public Logs setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public Logs setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public Date getDate() {
		return date;
	}

	public Logs setDate(Date date) {
		this.date = date;
		return this;
	}

	@Override
	public String toString() {
		return "Logs [username=" + username + ", ip=" + ip + ", date=" + date
				+ "]";
	}

}

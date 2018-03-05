package com.idsmanager.xsifter.domain.login;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.AbstractDomain;

@Document(collection = "LoginRecord")
public class LoginRecord extends AbstractDomain {

	private static final long serialVersionUID = 240073294009361460L;

	private String userUUID;

	private String username;

	private Date lastLoginTime = DateUtils.now();

	private int loginCount;

	private int todayLoginCount;// 今日登录次数

	public LoginRecord() {
		super();
	}

	public String getUserUUID() {
		return userUUID;
	}

	public LoginRecord setUserUUID(String userUUID) {
		this.userUUID = userUUID;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public LoginRecord setUsername(String username) {
		this.username = username;
		return this;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public LoginRecord setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
		return this;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public LoginRecord setLoginCount(int loginCount) {
		this.loginCount = loginCount;
		return this;
	}

	public int getTodayLoginCount() {
		return todayLoginCount;
	}

	public LoginRecord setTodayLoginCount(int todayLoginCount) {
		this.todayLoginCount = todayLoginCount;
		return this;
	}

}

package com.idsmanager.xsifter.service.dto.site;

import java.util.Date;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class SiteInfoDto extends AbstractDto {

	private static final long serialVersionUID = -2653056397348533406L;

	private String userRegistration;// 用户注册量
	private String activeUsers;// 活跃用户量
	private String siteVisits;// 网站访问量
	private String lastVisitUsername;// 最后一次访问用户
	private Date lastVisitTime;// 最后一次访问时间
	private String lastVisitIp;// 最后一次访问IP

	public SiteInfoDto() {
		super();
	}

	public SiteInfoDto(String lastVisitUsername, Date lastVisitTime,
			String lastVisitIp) {
		super();
		this.lastVisitUsername = lastVisitUsername;
		this.lastVisitTime = lastVisitTime;
		this.lastVisitIp = lastVisitIp;
	}

	public String getUserRegistration() {
		return userRegistration;
	}

	public void setUserRegistration(String userRegistration) {
		this.userRegistration = userRegistration;
	}

	public String getActiveUsers() {
		return activeUsers;
	}

	public void setActiveUsers(String activeUsers) {
		this.activeUsers = activeUsers;
	}

	public String getSiteVisits() {
		return siteVisits;
	}

	public void setSiteVisits(String siteVisits) {
		this.siteVisits = siteVisits;
	}

	public String getLastVisitUsername() {
		return lastVisitUsername;
	}

	public void setLastVisitUsername(String lastVisitUsername) {
		this.lastVisitUsername = lastVisitUsername;
	}

	public Date getLastVisitTime() {
		return lastVisitTime;
	}

	public void setLastVisitTime(Date lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}

	public String getLastVisitIp() {
		return lastVisitIp;
	}

	public void setLastVisitIp(String lastVisitIp) {
		this.lastVisitIp = lastVisitIp;
	}

}

package com.idsmanager.xsifter.service.dto.invite;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.UUIDGenerator;

public class InviteCompanyFormDto {
	@Id
	private String guid = UUIDGenerator.generate();
	@CreatedDate
	private Date createTime = DateUtils.now();

	private String username;//企业收件人
	
	private String companyName;// 企业名称

	private String companyEmail;// 企业邮箱
	
	private Boolean inviteState = Boolean.FALSE;//邀请状态,false:未接受,true:已接受

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public Boolean getInviteState() {
		return inviteState;
	}

	public void setInviteState(Boolean inviteState) {
		this.inviteState = inviteState;
	}

	public InviteCompanyFormDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InviteCompanyFormDto(String guid, Date createTime, String username,
			String companyName, String companyEmail, Boolean inviteState) {
		super();
		this.guid = guid;
		this.createTime = createTime;
		this.username = username;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.inviteState = inviteState;
	}

	
}

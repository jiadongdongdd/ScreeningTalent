package com.idsmanager.xsifter.domain.inviteCompany;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.UUIDGenerator;
import com.idsmanager.xsifter.domain.user.Privilege;

/**
 * 企业邀请 类名称 创建人 dushaofei 创建时间：2016-1-28 下午4:05:28 类描述：
 * 
 * @version
 */
@Document(collection = "inviteCompany_")
public class InviteCompany implements Serializable {

	private static final long serialVersionUID = 5793058486673945606L;
	@Id
	private String guid = UUIDGenerator.generate();
	@CreatedDate
	private Date createTime = DateUtils.now();

	private String username;// 企业收件人

	private String companyName;// 企业名称

	private String companyEmail;// 企业邮箱

	private Boolean inviteState = Boolean.FALSE;// 邀请状态,false:未接受,true:已接受

	private String inviteUsername;// 企业邀请人 主动邀请方

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

	public InviteCompany() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InviteCompany(String guid, Date createTime, String username,
			String companyName, String companyEmail, Boolean inviteState,
			String inviteUsername) {
		super();
		this.guid = guid;
		this.createTime = createTime;
		this.username = username;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.inviteState = inviteState;
		this.inviteUsername = inviteUsername;
	}

	public String getInviteUsername() {
		return inviteUsername;
	}

	public void setInviteUsername(String inviteUsername) {
		this.inviteUsername = inviteUsername;
	}

}

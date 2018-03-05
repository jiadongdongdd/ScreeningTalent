package com.idsmanager.xsifter.domain.company;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.UUIDGenerator;

/**
 * 
 * 类名称 企业 创建人 dushaofei 创建时间：2016-1-25 上午11:12:54 类描述：
 * 
 * @version
 */

@Document(collection = "company_")
public class Company implements Serializable {
	private static final long serialVersionUID = 2825537534246252743L;
	@Id
	private String guid = UUIDGenerator.generate();
	@CreatedDate
	private Date createTime = DateUtils.now();

	private String username;

	private String password;

	private String companyName;// 企业名称

	private String companyEmail;// 企业邮箱

	private String contacts;// 联系人

	private String contactsPhone;// 联系人电话

	private String businessPath;// 营业执照路径

	private String authorityPath;// 机构证书路径

	private Boolean verifyState = Boolean.FALSE;// 审核状态,false:未审核,true:已审核

	private Boolean verifyPass = Boolean.FALSE;// 审核是否通过,false:未通过,true:通过

	private Integer creditNumber = 0;

	private String companyNo;// 企业编号

	private String createUserUuid;// 创建者uuid

	// 不通过原因
	private VerifyReason[] verifyReason;
	// 行业
	// private Industry industry;
	private String industry;
	// 区域
	private String prov;

	private String city;

	private String dist;

	// 会员级别
	private String level;

	public Company() {
	}

	public Company(String username, String password, String companyName,
			String companyEmail, String contacts, String contactsPhone) {
		super();
		this.username = username;
		this.password = password;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.contacts = contacts;
		this.contactsPhone = contactsPhone;
	}

	public Company(Boolean verifyState, Boolean verifyPass) {
		super();
		this.verifyState = verifyState;
		this.verifyPass = verifyPass;
	}

	public String getGuid() {
		return guid;
	}

	public Company setGuid(String guid) {
		this.guid = guid;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Company setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public Company setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Company setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Company setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public String getContacts() {
		return contacts;
	}

	public Company setContacts(String contacts) {
		this.contacts = contacts;
		return this;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public Company setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
		return this;
	}

	public String getBusinessPath() {
		return businessPath;
	}

	public Company setBusinessPath(String businessPath) {
		this.businessPath = businessPath;
		return this;
	}

	public String getAuthorityPath() {
		return authorityPath;
	}

	public Company setAuthorityPath(String authorityPath) {
		this.authorityPath = authorityPath;
		return this;
	}

	public String guid() {
		return this.guid;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public Company setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
		return this;
	}

	public Boolean getVerifyState() {
		return verifyState;
	}

	public Company setVerifyState(Boolean verifyState) {
		this.verifyState = verifyState;
		return this;
	}

	public Boolean getVerifyPass() {
		return verifyPass;
	}

	public Company setVerifyPass(Boolean verifyPass) {
		this.verifyPass = verifyPass;
		return this;
	}

	public Integer getCreditNumber() {
		return creditNumber;
	}

	public Company setCreditNumber(Integer creditNumber) {
		this.creditNumber = creditNumber;
		return this;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public Company setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
		return this;
	}

	public VerifyReason[] getVerifyReason() {
		return verifyReason;
	}

	public Company setVerifyReason(VerifyReason[] verifyReason) {
		this.verifyReason = verifyReason;
		return this;
	}

	public String getProv() {
		return prov;
	}

	public Company setProv(String prov) {
		this.prov = prov;
		return this;
	}

	public String getCity() {
		return city;
	}

	public Company setCity(String city) {
		this.city = city;
		return this;
	}

	public String getDist() {
		return dist;
	}

	public Company setDist(String dist) {
		this.dist = dist;
		return this;
	}

	public String getIndustry() {
		return industry;
	}

	public Company setIndustry(String industry) {
		this.industry = industry;
		return this;
	}

	public String getCreateUserUuid() {
		return createUserUuid;
	}

	public Company setCreateUserUuid(String createUserUuid) {
		this.createUserUuid = createUserUuid;
		return this;
	}

	public String getLevel() {
		return level;
	}

	public Company setLevel(String level) {
		this.level = level;
		return this;
	}

}

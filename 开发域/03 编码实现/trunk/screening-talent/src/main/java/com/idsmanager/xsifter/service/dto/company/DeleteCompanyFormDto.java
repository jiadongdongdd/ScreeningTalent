package com.idsmanager.xsifter.service.dto.company;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.UUIDGenerator;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.Industry;
import com.idsmanager.xsifter.domain.company.VerifyReason;

import org.springframework.web.multipart.MultipartFile;


public class DeleteCompanyFormDto implements Serializable {
	private static final long serialVersionUID = 2825537534246252743L;

	private String guid = UUIDGenerator.generate();
	
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
  	
	public DeleteCompanyFormDto() {
	}

	public DeleteCompanyFormDto(String guid,String username, String password, String companyName,
			String companyEmail, String contacts, String contactsPhone,
			String businessPath, String authorityPath) {
		super();
		this.guid = guid;
		this.username = username;
		this.password = password;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.contacts = contacts;
		this.contactsPhone = contactsPhone;
		this.businessPath = businessPath;
		this.authorityPath = authorityPath;
	}

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getBusinessPath() {
		return businessPath;
	}

	public void setBusinessPath(String businessPath) {
		this.businessPath = businessPath;
	}

	public String getAuthorityPath() {
		return authorityPath;
	}

	public void setAuthorityPath(String authorityPath) {
		this.authorityPath = authorityPath;
	}


	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	
	public Industry[] getIndustries(){
		return Industry.values();
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Boolean getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(Boolean verifyState) {
		this.verifyState = verifyState;
	}

	public Boolean getVerifyPass() {
		return verifyPass;
	}

	public void setVerifyPass(Boolean verifyPass) {
		this.verifyPass = verifyPass;
	}

	public Integer getCreditNumber() {
		return creditNumber;
	}

	public void setCreditNumber(Integer creditNumber) {
		this.creditNumber = creditNumber;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCreateUserUuid() {
		return createUserUuid;
	}

	public void setCreateUserUuid(String createUserUuid) {
		this.createUserUuid = createUserUuid;
	}

	public VerifyReason[] getVerifyReason() {
		return verifyReason;
	}

	public void setVerifyReason(VerifyReason[] verifyReason) {
		this.verifyReason = verifyReason;
	}
}

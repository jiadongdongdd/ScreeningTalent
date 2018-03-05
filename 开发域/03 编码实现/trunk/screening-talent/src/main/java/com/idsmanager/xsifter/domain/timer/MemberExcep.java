package com.idsmanager.xsifter.domain.timer;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.xsifter.domain.AbstractDomain;
/**
 * 数据相同的职工
 */
@Document(collection = "MemberExcep_")
public class MemberExcep extends AbstractDomain{
	private static final long serialVersionUID = 3495973346178116362L;
	
	private String companyGuid;
	/*
	 * 基本信息
	 */
	private String chName;
	private String email;
	private String mobile;
	private String idNumber;
	//入职时间
    private Date entryDate;
    //离职时间
    private Date turnoverDate;
    
	public String getCompanyGuid() {
		return companyGuid;
	}
	public MemberExcep setCompanyGuid(String companyGuid) {
		this.companyGuid = companyGuid;
		return this;
	}
	public String getChName() {
		return chName;
	}
	public MemberExcep setChName(String chName) {
		this.chName = chName;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public MemberExcep setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getMobile() {
		return mobile;
	}
	public MemberExcep setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public MemberExcep setIdNumber(String idNumber) {
		this.idNumber = idNumber;
		return this;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public MemberExcep setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
		return this;
	}
	public Date getTurnoverDate() {
		return turnoverDate;
	}
	public MemberExcep setTurnoverDate(Date turnoverDate) {
		this.turnoverDate = turnoverDate;
		return this;
	}
	public MemberExcep() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}

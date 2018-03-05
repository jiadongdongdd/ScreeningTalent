package com.idsmanager.xsifter.service.dto.memberexcep;

import java.io.Serializable;
import java.util.Date;

import com.idsmanager.xsifter.domain.timer.MemberExcep;

public class MemberExcepFormDto implements Serializable {
	private static final long serialVersionUID = 8320955063474805458L;
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
    private MemberExcep memberExcep;
	public String getCompanyGuid() {
		return companyGuid;
	}
	public void setCompanyGuid(String companyGuid) {
		this.companyGuid = companyGuid;
	}
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getTurnoverDate() {
		return turnoverDate;
	}
	public void setTurnoverDate(Date turnoverDate) {
		this.turnoverDate = turnoverDate;
	}
	public MemberExcepFormDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberExcepFormDto(MemberExcep memberExcep) {
		super();
		this.memberExcep = memberExcep;
	}
	public MemberExcep getMemberExcep() {
		return memberExcep;
	}
	public void setMemberExcep(MemberExcep memberExcep) {
		this.memberExcep = memberExcep;
	}
	
}

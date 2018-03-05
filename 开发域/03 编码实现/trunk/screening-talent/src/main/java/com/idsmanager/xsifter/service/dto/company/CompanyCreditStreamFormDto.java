package com.idsmanager.xsifter.service.dto.company;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class CompanyCreditStreamFormDto extends AbstractDto {

	private static final long serialVersionUID = 5181344926287040788L;

	// 用户
	private String companyUsername;
	// 公司ID
	private String companyUuid;
	// 公司名称
	private String companyName;

	private String creditDo;

	private String remarks;

	private String createTime;

	// 操作人
	private String operator;

	public String getCompanyUsername() {
		return companyUsername;
	}

	public void setCompanyUsername(String companyUsername) {
		this.companyUsername = companyUsername;
	}

	public String getCompanyUuid() {
		return companyUuid;
	}

	public void setCompanyUuid(String companyUuid) {
		this.companyUuid = companyUuid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCreditDo() {
		return creditDo;
	}

	public void setCreditDo(String creditDo) {
		this.creditDo = creditDo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}

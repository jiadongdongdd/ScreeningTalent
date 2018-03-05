package com.idsmanager.xsifter.service.dto.admin;

import com.idsmanager.xsifter.domain.admin.CreditStream;
import com.idsmanager.xsifter.service.dto.AbstractDto;

public class CreditStreamDto extends AbstractDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3598187671917668786L;
	
		
	private String uuid;
	
	private String companyUuid;
	
	private String companyName;
	
	private String companyUsername;
	
	private String createTime;
	
	private String creditDo;
	
	private String remarks;
	
	private String operator;
	
	
	public CreditStreamDto(CreditStream creditStream){
		this.uuid = creditStream.getUuid();
		this.companyUuid = creditStream.getCompanyUuid();
		
		this.companyName = creditStream.getCompanyName();
		this.companyUsername = creditStream.getCompanyUsername();
		
		this.createTime = creditStream.getCreateTime();
		this.creditDo = creditStream.getCreditDo();
		
		this.remarks = creditStream.getRemarks();
		this.operator = creditStream.getOperator();
		
	}
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public String getCompanyUsername() {
		return companyUsername;
	}

	public void setCompanyUsername(String companyUsername) {
		this.companyUsername = companyUsername;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	

}

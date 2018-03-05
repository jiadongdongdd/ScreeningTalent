package com.idsmanager.xsifter.service.dto.admin;

import java.util.List;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class CreditStreamFormDto extends AbstractDto{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2609832877796160013L;

	private String companyUuid;
	
	private String creditDo;
	
	private String remarks;
	
	private List<String> companyUuids;
	
	
	
	public CreditStreamFormDto(String companyUuid){
		this.companyUuid = companyUuid;
	}
	
	public CreditStreamFormDto(){
		
	}
	public CreditStreamFormDto(List<String> companyUuids){
		this.companyUuids = companyUuids;
	}

	
	
	public List<String> getCompanyUuids() {
		return companyUuids;
	}

	public void setCompanyUuids(List<String> companyUuids) {
		this.companyUuids = companyUuids;
	}

	public String getCompanyUuid() {
		return companyUuid;
	}


	public void setCompanyUuid(String companyUuid) {
		this.companyUuid = companyUuid;
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
	
	
}

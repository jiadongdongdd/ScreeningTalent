package com.idsmanager.xsifter.service.dto.company;

import java.io.Serializable;
import java.util.List;

import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.VerifyReason;

public class CompanyVerifyReasonFormDto implements Serializable{
	
	private static final long serialVersionUID = -6452082287674898742L;

	private Company company;
	
	private String companyId;
	//validator info.
	private String info;
	
	//不通过原因
  	public VerifyReason[] verifyReason;
  	//validate info.
  	public String formReason;
  	
  	
	public VerifyReason[] getVerifyReasons(){
  		return VerifyReason.values();
  	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CompanyVerifyReasonFormDto(Company company) {
		super();
		this.company = company;
	}

	public CompanyVerifyReasonFormDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VerifyReason[] getVerifyReason() {
		return verifyReason;
	}

	public void setVerifyReason(VerifyReason[] verifyReason) {
		this.verifyReason = verifyReason;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getFormReason() {
		return formReason;
	}

	public void setFormReason(String formReason) {
		this.formReason = formReason;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	
}

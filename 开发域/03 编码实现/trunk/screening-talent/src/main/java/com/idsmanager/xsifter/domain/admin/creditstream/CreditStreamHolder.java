package com.idsmanager.xsifter.domain.admin.creditstream;

import com.idsmanager.xsifter.domain.admin.CreditAction;

public class CreditStreamHolder {
	
	//公司UUID
	private String companyUuid;
	
	//操作属性
	private CreditAction action;
	
	//备注｛add user xxx/download user xxx???｝
	private String remarks;
	
	//admin 操作积分专用数据
	private String adminCreditDo;
	
	//正常情况下调用的构造器
	public CreditStreamHolder(String companyUuid,String remarks,CreditAction action){
		this.companyUuid = companyUuid;
		this.remarks = remarks;
		this.action = action;
	}

	//admin调用的构造器
	public CreditStreamHolder(String companyUuid,String remarks,String adminCreditDo){
		this.companyUuid = companyUuid;
		this.remarks = remarks;
		this.action = CreditAction.SYSTEM;
		this.adminCreditDo = adminCreditDo;
	}

	public String getCompanyUuid() {
		return companyUuid;
	}

	public void setCompanyUuid(String companyUuid) {
		this.companyUuid = companyUuid;
	}

	public CreditAction getAction() {
		return action;
	}

	public void setAction(CreditAction action) {
		this.action = action;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAdminCreditDo() {
		return adminCreditDo;
	}

	public void setAdminCreditDo(String adminCreditDo) {
		this.adminCreditDo = adminCreditDo;
	}
	
	
}

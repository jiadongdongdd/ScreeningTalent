package com.idsmanager.xsifter.service.dto.admin;

import java.util.List;

import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.CreditRule;
import com.idsmanager.xsifter.service.dto.AbstractDto;

public class CreditRuleFormDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 387385328518775633L;

	private String uuid;
	
	private String ruleName;
	
	private String creditNumber;
	
	private String remarks;
	
	private String action;
	
	public CreditRuleFormDto(){
		
	}

	
	public CreditRuleFormDto(CreditRule creditRule){
		this.uuid= creditRule.getUuid();
		this.ruleName = creditRule.getRuleName();
		this.creditNumber = String.valueOf(creditRule.getCreditNumber());
		this.remarks = creditRule.getRemarks();
		this.action = creditRule.getAction();
	}

	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getRuleName() {
		return ruleName;
	}


	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getCreditNumber() {
		return creditNumber;
	}


	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getAction() {
		return action;
	}

	public List<String> getAllActions(){
		return CreditAction.allAction();
	}
	

	public void setAction(String action) {
		this.action = action;
	}
	
	
	
}

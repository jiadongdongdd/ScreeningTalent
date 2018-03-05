package com.idsmanager.xsifter.domain.admin;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.UUIDGenerator;

@Document(collection="credit_rule")
public class CreditRule implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8850855170715553060L;
	
	@Id
	private String uuid = UUIDGenerator.generate();
	@CreatedDate
	private Date createTime = DateUtils.now();
	
	private String ruleName;
	
	private String action;
	
	private Integer creditNumber;
	
	private String remarks;

	public String getUuid() {
		return uuid;
	}

	public CreditRule setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public Date createTime() {
		return createTime;
	}
	
	public String getCreateTime(){
		return DateUtils.toDateTime(createTime);
	}
	
	public CreditRule setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getRuleName() {
		return ruleName;
	}

	public CreditRule setRuleName(String ruleName) {
		this.ruleName = ruleName;
		return this;
	}

	public Integer getCreditNumber() {
		return creditNumber;
	}

	public CreditRule setCreditNumber(Integer creditNumber) {
		this.creditNumber = creditNumber;
		return this;
	}

	public String getRemarks() {
		return remarks;
	}

	public CreditRule setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	public String getAction() {
		return action;
	}

	public CreditRule setAction(String action) {
		this.action = action;
		return this;
	}
	
	
	
}

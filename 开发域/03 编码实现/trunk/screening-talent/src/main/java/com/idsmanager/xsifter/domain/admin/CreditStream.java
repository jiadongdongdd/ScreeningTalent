package com.idsmanager.xsifter.domain.admin;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.UUIDGenerator;

@Document(collection="credit_stream")
public class CreditStream implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8578846900714889329L;
	@Id
	private String uuid =UUIDGenerator.generate();
	@CreatedDate
	private Date createTime = DateUtils.now();	
	//用户
	private String companyUsername;	
	//公司ID
	private String companyUuid;
	//公司名称
	private String companyName;
	//积分
	private Integer creditDo;	
	
	private Integer beforeCredit;
	
	private Integer nowCredit;
	
	//备注
	private String remarks;	
	//操作人
	private String operator;
	
	private String operatorDetailsName;
	
	private String creditAction;
	
	
	
	public String getUuid() {
		return uuid;
	}
	public Date createTime() {
		return createTime;
	}
	
	public String getCreateTime(){
		return DateUtils.toDateTime(createTime);
	}
	
	public String getCompanyUsername() {
		return companyUsername;
	}
	public String getCompanyUuid() {
		return companyUuid;
	}
	public String getCompanyName() {
		return companyName;
	}
	public Integer creditDo() {
		return creditDo;
	}
	
	public String getCreditDo(){
		return String.valueOf(creditDo);
	}
	
	public String getRemarks() {
		return remarks;
	}
	public String getOperator() {
		return operator;
	}
	
	
	public CreditStream setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}
	public CreditStream setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	public CreditStream setCompanyUsername(String companyUsername) {
		this.companyUsername = companyUsername;
		return this;
	}
	public CreditStream setCompanyUuid(String companyUuid) {
		this.companyUuid = companyUuid;
		return this;
	}
	public CreditStream setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}
	public CreditStream setCreditDo(Integer creditDo) {
		this.creditDo = creditDo;
		return this;
	}
	public CreditStream setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}
	public CreditStream setOperator(String operator) {
		this.operator = operator;
		return this;
	}
	public String getOperatorDetailsName() {
		return operatorDetailsName;
	}
	public CreditStream setOperatorDetailsName(String operatorDetailsName) {
		this.operatorDetailsName = operatorDetailsName;
		return this;
	}
	
	
	public Integer getBeforeCredit() {
		return beforeCredit;
	}
	public CreditStream setBeforeCredit(Integer beforeCredit) {
		this.beforeCredit = beforeCredit;
		return this;
	}
	public Integer getNowCredit() {
		return nowCredit;
	}
	public CreditStream setNowCredit(Integer nowCredit) {
		this.nowCredit = nowCredit;
		return this;
	}
	public String getCreditAction() {
		return creditAction;
	}
	public CreditStream setCreditAction(String creditAction) {
		this.creditAction = creditAction;
		return this;
	}
	@Override
	public String toString() {
		return "CreditStream [createTime=" + createTime + ", campanyUsername="
				+ companyUsername + ", campanyUuid=" + companyUuid
				+ ", campanyName=" + companyName + ", creditDo=" + creditDo
				+ ", remarks=" + remarks + ", operator=" + operator
				+ ", operatorDetailsName=" + operatorDetailsName + "]";
	}
	
	

}

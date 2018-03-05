/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.idsmanager.xsifter.domain.member;

import java.util.Date;

import com.idsmanager.xsifter.domain.AbstractDomain;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 2016/1/28
 * <p/>
 * 工作环节
 * 
 * @author Shengzhao Li
 */
@Document(collection = "Worked")
public class Worked extends AbstractDomain {
	private static final long serialVersionUID = 1658678690132033485L;

	// 公司
	private String companyGuid;
	private String companyName;

	/*
	 * 基本信息
	 */
	private String chNameBasic;
	private String mobileBasic;
	private String idNumberBasic;
	private String emailBasic;

	/*
	 * 嘉奖
	 */
	// 嘉奖
	private WorkedAwards awards;
	// 嘉奖备注
	private String awardsRemark;

	/*
	 * 处分
	 */
	// 处分
	private WorkPunished punished;
	// 处分备注
	private String punishedRemark;

	// 工作期间是否有职位变化
	private boolean workChange;

	// 变化时间
	private Date changeDateOne;
	private Date changeDateTwo;
	private Date changeDateThree;

	// 变化职位
	private String changeJobOne;
	private String changeJobTwo;
	private String changeJobThree;

	public Worked() {
	}

	public Worked(String memberUuid) {
		this.uuid = memberUuid;
	}

	public Worked(Basic basic) {
		this.companyGuid = basic.getCompanyGuid();
		this.companyName = basic.getCompanyName();
		this.uuid = basic.getUuid();
		this.chNameBasic = basic.getChName();
		this.idNumberBasic = basic.getIdNumber();
		this.mobileBasic = basic.getMobile();
		this.emailBasic = basic.getEmail();
	}

	public String getMemberUuid() {
		return uuid;
	}

	public Worked setMemberUuid(String memberUuid) {
		this.uuid = memberUuid;
		return this;
	}

	public String getCompanyGuid() {
		return companyGuid;
	}

	public Worked setCompanyGuid(String companyGuid) {
		this.companyGuid = companyGuid;
		return this;
	}

	public WorkedAwards getAwards() {
		return awards;
	}

	public Worked setAwards(WorkedAwards awards) {
		this.awards = awards;
		return this;
	}

	public WorkPunished getPunished() {
		return punished;
	}

	public Worked setPunished(WorkPunished punished) {
		this.punished = punished;
		return this;
	}

	public String getAwardsRemark() {
		return awardsRemark;
	}

	public Worked setAwardsRemark(String awardsRemark) {
		this.awardsRemark = awardsRemark;
		return this;
	}

	public String getPunishedRemark() {
		return punishedRemark;
	}

	public Worked setPunishedRemark(String punishedRemark) {
		this.punishedRemark = punishedRemark;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Worked setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public boolean isWorkChange() {
		return workChange;
	}

	public Worked setWorkChange(boolean workChange) {
		this.workChange = workChange;
		return this;
	}

	public Date getChangeDateOne() {
		return changeDateOne;
	}

	public Worked setChangeDateOne(Date changeDateOne) {
		this.changeDateOne = changeDateOne;
		return this;
	}

	public Date getChangeDateTwo() {
		return changeDateTwo;
	}

	public Worked setChangeDateTwo(Date changeDateTwo) {
		this.changeDateTwo = changeDateTwo;
		return this;
	}

	public Date getChangeDateThree() {
		return changeDateThree;
	}

	public Worked setChangeDateThree(Date changeDateThree) {
		this.changeDateThree = changeDateThree;
		return this;
	}

	public String getChangeJobOne() {
		return changeJobOne;
	}

	public Worked setChangeJobOne(String changeJobOne) {
		this.changeJobOne = changeJobOne;
		return this;
	}

	public String getChangeJobTwo() {
		return changeJobTwo;
	}

	public Worked setChangeJobTwo(String changeJobTwo) {
		this.changeJobTwo = changeJobTwo;
		return this;
	}

	public String getChangeJobThree() {
		return changeJobThree;
	}

	public Worked setChangeJobThree(String changeJobThree) {
		this.changeJobThree = changeJobThree;
		return this;
	}

	public String getChNameBasic() {
		return chNameBasic;
	}

	public Worked setChNameBasic(String chNameBasic) {
		this.chNameBasic = chNameBasic;
		return this;
	}

	public String getMobileBasic() {
		return mobileBasic;
	}

	public Worked setMobileBasic(String mobileBasic) {
		this.mobileBasic = mobileBasic;
		return this;
	}

	public String getIdNumberBasic() {
		return idNumberBasic;
	}

	public Worked setIdNumberBasic(String idNumberBasic) {
		this.idNumberBasic = idNumberBasic;
		return this;
	}

	public String getEmailBasic() {
		return emailBasic;
	}

	public Worked setEmailBasic(String emailBasic) {
		this.emailBasic = emailBasic;
		return this;
	}

}

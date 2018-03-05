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

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.AbstractDomain;
import com.idsmanager.xsifter.domain.IntentionStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 2016/1/28
 * <p/>
 * 招聘环节
 * 
 * @author Shengzhao Li
 */
@Document(collection = "Recruitment")
public class Recruitment extends AbstractDomain {
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

	// 电话邀请时间
	private Date telInvitationTime;
	// 电话面试职位
	// private TelIntentionPosition telIntentionPosition;
	private String telIntentionPosition;

	// 是否达成面试意向
	private IntentionStatus telIntention;

	// 不参加面试的原因;
	private NotJoinIntentionReason notJoinIntentionReason;
	// 其他不参加的原因, 企业自行填写原因
	private String notJoinIntentionReasonRemark;

	// 面试时间
	private Date invitationTime;

	// 是否参加面试 true参加&false未参加
	private boolean joinInterview;

	// 面试分数
	private String intentionScore;

	// 是否同意入职
	private IntentionStatus agreeEntry;

	// 不同意入职的原因是个人/公司
	private Boolean personalOrCompany;

	// 不同意入职的原因 - 个人
	private NotEntryPersonalReason notEntryPersonalReason;

	// 不同意入职的原因详细 - 个人
	private List<NotEntryPersonalReasonItem> notEntryPersonReasonDetails = new ArrayList<>();

	// 不同意入职的原因 - 公司;
	private NotEntryCompanyReason notEntryCompanyReason;
	// 不同意入职的原因 - 公司; 备注或其他的说明
	private String notEntryCompanyReasonRemark;

	// 是否入职成功
	private IntentionStatus entrySuccess;
	// 入职时间
	private Date entryDate;

	public Recruitment() {
	}

	public Recruitment(Basic basic) {
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

	public Recruitment setMemberUuid(String memberUuid) {
		this.uuid = memberUuid;
		return this;
	}

	public String getCompanyGuid() {
		return companyGuid;
	}

	public Recruitment setCompanyGuid(String companyGuid) {
		this.companyGuid = companyGuid;
		return this;
	}

	public NotJoinIntentionReason getNotJoinIntentionReason() {
		return notJoinIntentionReason;
	}

	public Recruitment setNotJoinIntentionReason(
			NotJoinIntentionReason notJoinIntentionReason) {
		this.notJoinIntentionReason = notJoinIntentionReason;
		return this;
	}

	public String getNotJoinIntentionReasonRemark() {
		return notJoinIntentionReasonRemark;
	}

	public Recruitment setNotJoinIntentionReasonRemark(
			String notJoinIntentionReasonRemark) {
		this.notJoinIntentionReasonRemark = notJoinIntentionReasonRemark;
		return this;
	}

	

	public String getIntentionScore() {
		return intentionScore;
	}

	public Recruitment setIntentionScore(String intentionScore) {
		this.intentionScore = intentionScore;
		return this;
	}

	public Boolean getPersonalOrCompany() {
		return personalOrCompany;
	}

	public Recruitment setPersonalOrCompany(Boolean personalOrCompany) {
		this.personalOrCompany = personalOrCompany;
		return this;
	}

	public NotEntryPersonalReason getNotEntryPersonalReason() {
		return notEntryPersonalReason;
	}

	public Recruitment setNotEntryPersonalReason(
			NotEntryPersonalReason notEntryPersonalReason) {
		this.notEntryPersonalReason = notEntryPersonalReason;
		return this;
	}

	public List<NotEntryPersonalReasonItem> getNotEntryPersonReasonDetails() {
		return notEntryPersonReasonDetails;
	}

	public Recruitment setNotEntryPersonReasonDetails(
			List<NotEntryPersonalReasonItem> notEntryPersonReasonDetails) {
		this.notEntryPersonReasonDetails = notEntryPersonReasonDetails;
		return this;
	}

	public NotEntryCompanyReason getNotEntryCompanyReason() {
		return notEntryCompanyReason;
	}

	public Recruitment setNotEntryCompanyReason(
			NotEntryCompanyReason notEntryCompanyReason) {
		this.notEntryCompanyReason = notEntryCompanyReason;
		return this;
	}

	public String getNotEntryCompanyReasonRemark() {
		return notEntryCompanyReasonRemark;
	}

	public Recruitment setNotEntryCompanyReasonRemark(
			String notEntryCompanyReasonRemark) {
		this.notEntryCompanyReasonRemark = notEntryCompanyReasonRemark;
		return this;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public Recruitment setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
		return this;
	}

	public Date getTelInvitationTime() {
		return telInvitationTime;
	}

	public String getTelInvitationTimeText() {
		return DateUtils.toDateText(telInvitationTime, "yyyy-MM-dd");
	}

	public Recruitment setTelInvitationTime(Date telInvitationTime) {
		this.telInvitationTime = telInvitationTime;
		return this;
	}

	/*
	 * public TelIntentionPosition getTelIntentionPosition() { return
	 * telIntentionPosition; }
	 * 
	 * public Recruitment setTelIntentionPosition( TelIntentionPosition
	 * telIntentionPosition) { this.telIntentionPosition = telIntentionPosition;
	 * return this; }
	 */

	public IntentionStatus getTelIntention() {
		return telIntention;
	}

	public String getTelIntentionPosition() {
		return telIntentionPosition;
	}

	public Recruitment setTelIntentionPosition(String telIntentionPosition) {
		this.telIntentionPosition = telIntentionPosition;
		return this;
	}

	public Recruitment setTelIntention(IntentionStatus telIntention) {
		this.telIntention = telIntention;
		return this;
	}

	public Date getInvitationTime() {
		return invitationTime;
	}

	public String getInvitationTimeText() {
		return DateUtils.toDateText(invitationTime, "yyyy-MM-dd");
	}

	public Recruitment setInvitationTime(Date invitationTime) {
		this.invitationTime = invitationTime;
		return this;
	}

	public IntentionStatus getAgreeEntry() {
		return agreeEntry;
	}

	public Recruitment setAgreeEntry(IntentionStatus agreeEntry) {
		this.agreeEntry = agreeEntry;
		return this;
	}

	public IntentionStatus getEntrySuccess() {
		return entrySuccess;
	}

	public Recruitment setEntrySuccess(IntentionStatus entrySuccess) {
		this.entrySuccess = entrySuccess;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Recruitment setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public String getChNameBasic() {
		return chNameBasic;
	}

	public Recruitment setChNameBasic(String chNameBasic) {
		this.chNameBasic = chNameBasic;
		return this;
	}

	public String getMobileBasic() {
		return mobileBasic;
	}

	public Recruitment setMobileBasic(String mobileBasic) {
		this.mobileBasic = mobileBasic;
		return this;
	}

	public String getIdNumberBasic() {
		return idNumberBasic;
	}

	public Recruitment setIdNumberBasic(String idNumberBasic) {
		this.idNumberBasic = idNumberBasic;
		return this;
	}

	public String getEmailBasic() {
		return emailBasic;
	}

	public Recruitment setEmailBasic(String emailBasic) {
		this.emailBasic = emailBasic;
		return this;
	}

	public boolean isJoinInterview() {
		return joinInterview;
	}

	public Recruitment setJoinInterview(boolean joinInterview) {
		this.joinInterview = joinInterview;
		return this;
	}

	public String getEntryDateTime() {
		return DateUtils.toDateText(entryDate, "yyyy-MM-dd");
	}

}

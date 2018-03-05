package com.idsmanager.xsifter.service.dto.menber;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.IntentionStatus;
import com.idsmanager.xsifter.domain.member.*;
import com.idsmanager.xsifter.domain.position.MemberPosition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecruitmentFormDto implements Serializable {

	private static final long serialVersionUID = 3367628098361817363L;

	private String memberUuid;

	private String telInvitationTime;
	// 电话面试职位
	private String telIntentionPosition;

	private List<MemberPosition> positionList;

	// 是否达成面试意向
	private IntentionStatus telIntention = IntentionStatus.DISABLED;

	// 不参加面试的原因;
	private NotJoinIntentionReason notJoinIntentionReason;
	// 其他不参加的原因, 企业自行填写原因
	private String notJoinIntentionReasonRemark;

	// 面试时间
	private String invitationTime;

	private boolean joinInterview;

	// 面试分数
	private String intentionScore;

	// 是否同意入职
	private IntentionStatus agreeEntry;

	// 不同意入职的原因是个人/公司, true 个人, false 公司
	private Boolean personalOrCompany = true;

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
	private String entryDate;

	private boolean next;

	public RecruitmentFormDto() {
		super();
	}

	public RecruitmentFormDto(Recruitment recruitment) {
		this.memberUuid = recruitment.getMemberUuid();
		this.telInvitationTime = DateUtils.toDate(recruitment
				.getTelInvitationTime());
		this.telIntentionPosition = recruitment.getTelIntentionPosition();

		this.telIntention = recruitment.getTelIntention();
		this.notJoinIntentionReason = recruitment.getNotJoinIntentionReason();
		this.notJoinIntentionReasonRemark = recruitment
				.getNotJoinIntentionReasonRemark();

		this.invitationTime = DateUtils.toDate(recruitment.getInvitationTime());
		this.joinInterview = recruitment.isJoinInterview();
		this.intentionScore = recruitment.getIntentionScore();
		this.agreeEntry = recruitment.getAgreeEntry();

		this.personalOrCompany = recruitment.getPersonalOrCompany();
		this.notEntryPersonalReason = recruitment.getNotEntryPersonalReason();
		this.notEntryPersonReasonDetails = recruitment
				.getNotEntryPersonReasonDetails();

		this.notEntryCompanyReason = recruitment.getNotEntryCompanyReason();
		this.notEntryCompanyReasonRemark = recruitment
				.getNotEntryCompanyReasonRemark();
		this.entrySuccess = recruitment.getEntrySuccess();

		this.entryDate = DateUtils.toDate(recruitment.getEntryDate());
	}

	public RecruitmentFormDto(String memberGuid) {
		this.memberUuid = memberGuid;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public NotEntryCompanyReason[] getNotEntryCompanyReasons() {
		return NotEntryCompanyReason.values();
	}

	public NotEntryPersonalReason[] getNotEntryPersonalReasons() {
		return NotEntryPersonalReason.values();
	}

	public IntentionStatus[] getIntentionStatuses() {
		return IntentionStatus.values();
	}

	public NotJoinIntentionReason[] getNotJoinIntentionReasons() {
		return NotJoinIntentionReason.values();
	}

	public TelIntentionPosition[] getTelIntentionPositions() {
		return TelIntentionPosition.values();
	}

	public String getMemberUuid() {
		return memberUuid;
	}

	public void setMemberUuid(String memberUuid) {
		this.memberUuid = memberUuid;
	}

	public String getTelInvitationTime() {
		return telInvitationTime;
	}

	public void setTelInvitationTime(String telInvitationTime) {
		this.telInvitationTime = telInvitationTime;
	}

	public String getTelIntentionPosition() {
		return telIntentionPosition;
	}

	public void setTelIntentionPosition(String telIntentionPosition) {
		this.telIntentionPosition = telIntentionPosition;
	}

	public List<MemberPosition> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<MemberPosition> positionList) {
		this.positionList = positionList;
	}

	public IntentionStatus getTelIntention() {
		return telIntention;
	}

	public void setTelIntention(IntentionStatus telIntention) {
		this.telIntention = telIntention;
	}

	public NotJoinIntentionReason getNotJoinIntentionReason() {
		return notJoinIntentionReason;
	}

	public void setNotJoinIntentionReason(
			NotJoinIntentionReason notJoinIntentionReason) {
		this.notJoinIntentionReason = notJoinIntentionReason;
	}

	public String getNotJoinIntentionReasonRemark() {
		return notJoinIntentionReasonRemark;
	}

	public void setNotJoinIntentionReasonRemark(
			String notJoinIntentionReasonRemark) {
		this.notJoinIntentionReasonRemark = notJoinIntentionReasonRemark;
	}

	public String getInvitationTime() {
		return invitationTime;
	}

	public void setInvitationTime(String invitationTime) {
		this.invitationTime = invitationTime;
	}

	

	public String getIntentionScore() {
		return intentionScore;
	}

	public void setIntentionScore(String intentionScore) {
		this.intentionScore = intentionScore;
	}

	public IntentionStatus getAgreeEntry() {
		return agreeEntry;
	}

	public void setAgreeEntry(IntentionStatus agreeEntry) {
		this.agreeEntry = agreeEntry;
	}

	public Boolean getPersonalOrCompany() {
		return personalOrCompany;
	}

	public void setPersonalOrCompany(Boolean personalOrCompany) {
		this.personalOrCompany = personalOrCompany;
	}

	public NotEntryPersonalReason getNotEntryPersonalReason() {
		return notEntryPersonalReason;
	}

	public void setNotEntryPersonalReason(
			NotEntryPersonalReason notEntryPersonalReason) {
		this.notEntryPersonalReason = notEntryPersonalReason;
	}

	public List<NotEntryPersonalReasonItem> getNotEntryPersonReasonDetails() {
		return notEntryPersonReasonDetails;
	}

	public void setNotEntryPersonReasonDetails(
			List<NotEntryPersonalReasonItem> notEntryPersonReasonDetails) {
		this.notEntryPersonReasonDetails = notEntryPersonReasonDetails;
	}

	public NotEntryCompanyReason getNotEntryCompanyReason() {
		return notEntryCompanyReason;
	}

	public void setNotEntryCompanyReason(
			NotEntryCompanyReason notEntryCompanyReason) {
		this.notEntryCompanyReason = notEntryCompanyReason;
	}

	public String getNotEntryCompanyReasonRemark() {
		return notEntryCompanyReasonRemark;
	}

	public void setNotEntryCompanyReasonRemark(
			String notEntryCompanyReasonRemark) {
		this.notEntryCompanyReasonRemark = notEntryCompanyReasonRemark;
	}

	public IntentionStatus getEntrySuccess() {
		return entrySuccess;
	}

	public void setEntrySuccess(IntentionStatus entrySuccess) {
		this.entrySuccess = entrySuccess;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public boolean isJoinInterview() {
		return joinInterview;
	}

	public void setJoinInterview(boolean joinInterview) {
		this.joinInterview = joinInterview;
	}

	public Recruitment updateFields(Recruitment recruitment) {
		return recruitment
				.setTelInvitationTime(DateUtils.getDate(this.telInvitationTime))
				.setTelIntentionPosition(this.telIntentionPosition)
				.setTelIntention(this.telIntention)
				.setNotJoinIntentionReason(this.notJoinIntentionReason)
				.setNotJoinIntentionReasonRemark(
						this.notJoinIntentionReasonRemark)
				.setInvitationTime(DateUtils.getDate(this.invitationTime))

				.setIntentionScore(this.intentionScore)
				.setAgreeEntry(this.agreeEntry)
				.setPersonalOrCompany(this.personalOrCompany)
				.setNotEntryPersonalReason(this.notEntryPersonalReason)

				.setNotEntryPersonReasonDetails(
						this.notEntryPersonReasonDetails)
				.setNotEntryCompanyReason(this.notEntryCompanyReason)
				.setNotEntryCompanyReasonRemark(
						this.notEntryCompanyReasonRemark)

				.setEntrySuccess(this.entrySuccess)
				.setEntryDate(DateUtils.getDate(this.entryDate))
				.setJoinInterview(joinInterview);
	}

}

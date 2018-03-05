package com.idsmanager.xsifter.service.dto.menber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.GenderStatus;
import com.idsmanager.xsifter.domain.IntentionStatus;
import com.idsmanager.xsifter.domain.member.Degree;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.NotEntryCompanyReason;
import com.idsmanager.xsifter.domain.member.NotEntryPersonalReason;
import com.idsmanager.xsifter.domain.member.NotEntryPersonalReasonItem;
import com.idsmanager.xsifter.domain.member.NotJoinIntentionReason;
import com.idsmanager.xsifter.domain.member.PoliticalStatus;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.TurnoverIncome;
import com.idsmanager.xsifter.domain.member.TurnoverProcess;
import com.idsmanager.xsifter.domain.member.TurnoverProcessItem;
import com.idsmanager.xsifter.domain.member.TurnoverReason;
import com.idsmanager.xsifter.domain.member.TurnoverReasonItem;
import com.idsmanager.xsifter.domain.member.WorkPunished;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.domain.member.WorkedAwards;
import com.idsmanager.xsifter.domain.nation.Nation;
import com.idsmanager.xsifter.domain.origin.Origin;

/**
 * 
 * @author andy
 * 
 */
public class MemberFormDto implements Serializable {

	private static final long serialVersionUID = 4955153789613119666L;

	private String uuid;
	private String companyGuid;

	/*
	 * private String inviteTime; private String interviewTime; private String
	 * entryTime; private String turnoverTime;
	 */

	/*
	 * 基本信息
	 */

	private String chName;
	private String enName;
	private String mobile;
	private String idNumber;
	private String email;

	/*
	 * 教育背景
	 */
	private GenderStatus gender;
	private Integer age;
	// 毕业院校
	private String school;
	// 专业
	private String major;
	// 学历
	private Degree degree;
	// 民族
	private Nation nation;
	// 政治面貌
	private PoliticalStatus politicalStatus;
	// 籍贯
	private Origin origin;

	/*
	 * 招聘环节
	 */

	// 电话邀请时间
	private Date telInvitationTime;
	// 电话面试职位
	private String telIntentionPosition;

	// 是否达成面试意向
	private IntentionStatus telIntention;

	// 不参加面试的原因;
	private NotJoinIntentionReason notJoinIntentionReason;
	// 其他不参加的原因, 企业自行填写原因
	private String notJoinIntentionReasonRemark;

	// 面试时间
	private Date invitationTime;

	// 面试分数
	private String intentionScore;

	// 是否同意入职
	private IntentionStatus agreeEntry;

	// 不同意入职的原因是个人/公司
	private Boolean personalOrCompany;

	// 不同意入职的原因 - 个人
	private NotEntryPersonalReason notEntryPersonalReason;

	// 不同意入职的原因详细 - 个人
	private List<NotEntryPersonalReasonItem> notEntryPersonReasonDetails;

	// 不同意入职的原因 - 公司;
	private NotEntryCompanyReason notEntryCompanyReason;
	// 不同意入职的原因 - 公司; 备注或其他的说明
	private String notEntryCompanyReasonRemark;

	// 是否入职成功
	private IntentionStatus entrySuccess;

	// 入职时间
	private Date entryDate;

	public void createMemberRecruitment(Recruitment recruitment) {
		this.telInvitationTime = recruitment.getTelInvitationTime();
		this.telIntentionPosition = recruitment.getTelIntentionPosition();
		this.telIntention = recruitment.getTelIntention();
		this.notJoinIntentionReason = recruitment.getNotJoinIntentionReason();
		this.notJoinIntentionReasonRemark = recruitment
				.getNotJoinIntentionReasonRemark();
		this.invitationTime = recruitment.getInvitationTime();
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
		this.entryDate = recruitment.getEntryDate();

	}

	/*
	 * 离职环节
	 */

	/*
	 * 离职原因
	 */
	// 离职原因
	private TurnoverReason turnoverReason;

	// 离职原因中 劳动纠纷的 附件
	private String laborDisputeFileGuid;

	// 开除原因为OTHER的备注 信息
	private String turnoverReasonRemark;

	// 开除 的原因, 可以多个
	private List<TurnoverReasonItem> goOutReasonItems = new ArrayList<>();

	/*
	 * 离职过程
	 */
	// 离职过程
	private TurnoverProcess turnoverProcess;

	// 离职过程中 OTHER 的注明信息
	private String otherProcessRemark;

	// '带走公司财产'的选项
	private List<TurnoverProcessItem> takeCompanyRsItems = new ArrayList<>();

	// 离职时间
	private Date turnoverDate;

	// 离职时职位
	private String turnoverPosition;

	// 离职时收入
	private TurnoverIncome turnoverIncome;

	/*
	 * 离职后状态
	 */

	// 是否违反培训协议
	private boolean breachTrainingAgreement;
	// 若违反培训协议的 附件证据
	private String breachTrainingAgreementFileGuid;

	// 是否在避业禁止期内(是/否)
	private boolean outStopPeriod;
	// 避业禁止期内 附件证据
	private String outStopPeriodFileGuid;

	// 离职后用非法手段恶意毁坏公司形象
	private boolean illegalDestroyCompanyFace;
	// 非法手段恶意毁坏公司形象 附件证据
	private String illegalDestroyCompanyFaceFileGuid;

	// 离职后和原单位发生劳动诉讼或法律纠纷
	private boolean legalDisputes;
	// 离职后和原单位发生劳动诉讼或法律纠纷 附件证据 上传判决文件
	private String legalDisputesFileGuid;

	// 离职后 其他信息注明
	private String outOtherRemark;
	// 离职后 其他信息注明 附件
	private String outOtherFileGuid;

	public void createMemberTurnover(Turnover turnover) {
		this.turnoverReason = turnover.getTurnoverReason();
		this.laborDisputeFileGuid = turnover.getLaborDisputeFileGuid();
		this.turnoverReasonRemark = turnover.getTurnoverReasonRemark();
		this.goOutReasonItems = turnover.getGoOutReasonItems();
		this.turnoverProcess = turnover.getTurnoverProcess();
		this.otherProcessRemark = turnover.getOtherProcessRemark();
		this.takeCompanyRsItems = turnover.getTakeCompanyRsItems();
		this.turnoverDate = turnover.getTurnoverDate();
		this.breachTrainingAgreement = turnover.isBreachTrainingAgreement();
		this.breachTrainingAgreementFileGuid = turnover
				.getBreachTrainingAgreementFileGuid();
		this.outStopPeriod = turnover.isOutStopPeriod();
		this.outStopPeriodFileGuid = turnover.getOutStopPeriodFileGuid();
		this.illegalDestroyCompanyFace = turnover.isIllegalDestroyCompanyFace();
		this.illegalDestroyCompanyFaceFileGuid = turnover
				.getIllegalDestroyCompanyFaceFileGuid();
		this.legalDisputes = turnover.isLegalDisputes();
		this.legalDisputesFileGuid = turnover.getLegalDisputesFileGuid();
		this.outOtherRemark = turnover.getOutOtherRemark();
		this.outOtherFileGuid = turnover.getOutOtherFileGuid();
		this.turnoverPosition = turnover.getTurnoverPosition();
		this.turnoverIncome = turnover.getTurnoverIncome();

	}

	/*
	 * 工作环节
	 */

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

	public void createMemberWorked(Worked worked) {
		this.awards = worked.getAwards();
		this.awardsRemark = worked.getAwardsRemark();
		this.punished = worked.getPunished();
		this.punishedRemark = worked.getPunishedRemark();

		this.workChange = worked.isWorkChange();
		this.changeDateOne = worked.getChangeDateOne();
		this.changeDateTwo = worked.getChangeDateTwo();
		this.changeDateThree = worked.getChangeDateThree();
		this.changeJobOne = worked.getChangeJobOne();
		this.changeJobTwo = worked.getChangeJobTwo();
		this.changeJobThree = worked.getChangeJobThree();

	}

	public MemberFormDto() {
	}

	public MemberFormDto(String uuid) {
		this.uuid = uuid;
	}

	public MemberFormDto(Member member) {
		this.uuid = member.getUuid();
		this.chName = member.getChName();
		this.enName = member.getEnName();
		this.mobile = member.getMobile();
		this.idNumber = member.getIdNumber();
		this.email = member.getEmail();
		this.gender = member.getGender();
		this.age = member.getAge();
		this.school = member.getSchool();
		this.major = member.getMajor();
		this.degree = member.getDegree();
		this.nation = member.getNation();
		this.politicalStatus = member.getPoliticalStatus();
		this.origin = member.getOrigin();
	}

	/*
	 * 基本信息getter&setter
	 */

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCompanyGuid() {
		return companyGuid;
	}

	public void setCompanyGuid(String companyGuid) {
		this.companyGuid = companyGuid;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * 教育背景
	 */

	public GenderStatus getGender() {
		return gender;
	}

	public void setGender(GenderStatus gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	/*
	 * 招聘环节
	 */

	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public Date getTelInvitationTime() {
		return telInvitationTime;
	}

	public void setTelInvitationTime(Date telInvitationTime) {
		this.telInvitationTime = telInvitationTime;
	}

	/*
	 * public TelIntentionPosition getTelIntentionPosition() { return
	 * telIntentionPosition; }
	 * 
	 * public void setTelIntentionPosition( TelIntentionPosition
	 * telIntentionPosition) { this.telIntentionPosition = telIntentionPosition;
	 * }
	 */

	public IntentionStatus getTelIntention() {
		return telIntention;
	}

	public String getTelIntentionPosition() {
		return telIntentionPosition;
	}

	public void setTelIntentionPosition(String telIntentionPosition) {
		this.telIntentionPosition = telIntentionPosition;
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

	public Date getInvitationTime() {
		return invitationTime;
	}

	public void setInvitationTime(Date invitationTime) {
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

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/*
	 * 离职环节
	 */

	public TurnoverReason getTurnoverReason() {
		return turnoverReason;
	}

	public void setTurnoverReason(TurnoverReason turnoverReason) {
		this.turnoverReason = turnoverReason;
	}

	public String getLaborDisputeFileGuid() {
		return laborDisputeFileGuid;
	}

	public void setLaborDisputeFileGuid(String laborDisputeFileGuid) {
		this.laborDisputeFileGuid = laborDisputeFileGuid;
	}

	public String getTurnoverReasonRemark() {
		return turnoverReasonRemark;
	}

	public void setTurnoverReasonRemark(String turnoverReasonRemark) {
		this.turnoverReasonRemark = turnoverReasonRemark;
	}

	public List<TurnoverReasonItem> getGoOutReasonItems() {
		return goOutReasonItems;
	}

	public void setGoOutReasonItems(List<TurnoverReasonItem> goOutReasonItems) {
		this.goOutReasonItems = goOutReasonItems;
	}

	public TurnoverProcess getTurnoverProcess() {
		return turnoverProcess;
	}

	public void setTurnoverProcess(TurnoverProcess turnoverProcess) {
		this.turnoverProcess = turnoverProcess;
	}

	public String getOtherProcessRemark() {
		return otherProcessRemark;
	}

	public void setOtherProcessRemark(String otherProcessRemark) {
		this.otherProcessRemark = otherProcessRemark;
	}

	public List<TurnoverProcessItem> getTakeCompanyRsItems() {
		return takeCompanyRsItems;
	}

	public void setTakeCompanyRsItems(
			List<TurnoverProcessItem> takeCompanyRsItems) {
		this.takeCompanyRsItems = takeCompanyRsItems;
	}

	public Date getTurnoverDate() {
		return turnoverDate;
	}

	public void setTurnoverDate(Date turnoverDate) {
		this.turnoverDate = turnoverDate;
	}

	public boolean isBreachTrainingAgreement() {
		return breachTrainingAgreement;
	}

	public void setBreachTrainingAgreement(boolean breachTrainingAgreement) {
		this.breachTrainingAgreement = breachTrainingAgreement;
	}

	public String getBreachTrainingAgreementFileGuid() {
		return breachTrainingAgreementFileGuid;
	}

	public void setBreachTrainingAgreementFileGuid(
			String breachTrainingAgreementFileGuid) {
		this.breachTrainingAgreementFileGuid = breachTrainingAgreementFileGuid;
	}

	public boolean isOutStopPeriod() {
		return outStopPeriod;
	}

	public void setOutStopPeriod(boolean outStopPeriod) {
		this.outStopPeriod = outStopPeriod;
	}

	public String getOutStopPeriodFileGuid() {
		return outStopPeriodFileGuid;
	}

	public void setOutStopPeriodFileGuid(String outStopPeriodFileGuid) {
		this.outStopPeriodFileGuid = outStopPeriodFileGuid;
	}

	public boolean isIllegalDestroyCompanyFace() {
		return illegalDestroyCompanyFace;
	}

	public void setIllegalDestroyCompanyFace(boolean illegalDestroyCompanyFace) {
		this.illegalDestroyCompanyFace = illegalDestroyCompanyFace;
	}

	public String getIllegalDestroyCompanyFaceFileGuid() {
		return illegalDestroyCompanyFaceFileGuid;
	}

	public void setIllegalDestroyCompanyFaceFileGuid(
			String illegalDestroyCompanyFaceFileGuid) {
		this.illegalDestroyCompanyFaceFileGuid = illegalDestroyCompanyFaceFileGuid;
	}

	public boolean isLegalDisputes() {
		return legalDisputes;
	}

	public void setLegalDisputes(boolean legalDisputes) {
		this.legalDisputes = legalDisputes;
	}

	public String getLegalDisputesFileGuid() {
		return legalDisputesFileGuid;
	}

	public void setLegalDisputesFileGuid(String legalDisputesFileGuid) {
		this.legalDisputesFileGuid = legalDisputesFileGuid;
	}

	public String getOutOtherRemark() {
		return outOtherRemark;
	}

	public void setOutOtherRemark(String outOtherRemark) {
		this.outOtherRemark = outOtherRemark;
	}

	public String getOutOtherFileGuid() {
		return outOtherFileGuid;
	}

	public void setOutOtherFileGuid(String outOtherFileGuid) {
		this.outOtherFileGuid = outOtherFileGuid;
	}

	/*
	 * 工作环节
	 */

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public PoliticalStatus getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(PoliticalStatus politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public WorkedAwards getAwards() {
		return awards;
	}

	public void setAwards(WorkedAwards awards) {
		this.awards = awards;
	}

	public String getAwardsRemark() {
		return awardsRemark;
	}

	public void setAwardsRemark(String awardsRemark) {
		this.awardsRemark = awardsRemark;
	}

	public WorkPunished getPunished() {
		return punished;
	}

	public void setPunished(WorkPunished punished) {
		this.punished = punished;
	}

	public String getPunishedRemark() {
		return punishedRemark;
	}

	public void setPunishedRemark(String punishedRemark) {
		this.punishedRemark = punishedRemark;
	}

	/*
	 * 表单显示属性
	 */

	public TurnoverIncome getTurnoverIncome() {
		return turnoverIncome;
	}

	public String getTurnoverPosition() {
		return turnoverPosition;
	}

	public void setTurnoverPosition(String turnoverPosition) {
		this.turnoverPosition = turnoverPosition;
	}

	public void setTurnoverIncome(TurnoverIncome turnoverIncome) {
		this.turnoverIncome = turnoverIncome;
	}

	public String getInviteTime() {
		return DateUtils.toDateTime(telInvitationTime);
	}

	public String getInterviewTime() {
		return DateUtils.toDateTime(invitationTime);
	}

	public String getEntryTime() {
		return DateUtils.toDateTime(entryDate);
	}

	public String getTurnoverTime() {
		return DateUtils.toDateTime(turnoverDate);
	}

	public boolean isWorkChange() {
		return workChange;
	}

	public void setWorkChange(boolean workChange) {
		this.workChange = workChange;
	}

	public Date getChangeDateOne() {
		return changeDateOne;
	}

	public void setChangeDateOne(Date changeDateOne) {
		this.changeDateOne = changeDateOne;
	}

	public Date getChangeDateTwo() {
		return changeDateTwo;
	}

	public void setChangeDateTwo(Date changeDateTwo) {
		this.changeDateTwo = changeDateTwo;
	}

	public Date getChangeDateThree() {
		return changeDateThree;
	}

	public void setChangeDateThree(Date changeDateThree) {
		this.changeDateThree = changeDateThree;
	}

	public String getChangeJobOne() {
		return changeJobOne;
	}

	public void setChangeJobOne(String changeJobOne) {
		this.changeJobOne = changeJobOne;
	}

	public String getChangeJobTwo() {
		return changeJobTwo;
	}

	public void setChangeJobTwo(String changeJobTwo) {
		this.changeJobTwo = changeJobTwo;
	}

	public String getChangeJobThree() {
		return changeJobThree;
	}

	public void setChangeJobThree(String changeJobThree) {
		this.changeJobThree = changeJobThree;
	}

}

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
 * 离职环节
 * 
 * @author Shengzhao Li
 */
@Document(collection = "Turnover")
public class Turnover extends AbstractDomain {
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

	// 离职原因是否正常
	private boolean reasonStatus;

	// 离职原因
	private TurnoverReason turnoverReason;

	// 离职原因中 劳动纠纷的 附件
	private String laborDisputeFileGuid;

	// 开除原因为OTHER的备注 信息
	private String turnoverReasonRemark;

	// 开除 的原因, 可以多个
	private List<TurnoverReasonItem> goOutReasonItems = new ArrayList<>();

	// 开除 - 泄露公司机密附件
	private String revealSecretsFileGuid;

	// 贪污受贿挪用公款附件
	private String briberyFileGuid;

	// 言行粗鲁附件
	private String rudeFileGuid;

	// 破坏公司重要资产附件
	private String destroyImportantAssetsFileGuid;

	// 偷盗公司财物
	private String stealingFileGuid;

	// 诋毁公司名誉附件
	private String defamationFileGuid;

	// 滥用公司资源附件
	private String misuseResourcesFileGuid;

	/*
	 * 离职过程
	 */
	// 离职过程是否正常
	private boolean processStatus;
	// 离职过程
	private TurnoverProcess turnoverProcess;

	// 离职过程中 OTHER 的注明信息
	private String otherProcessRemark;

	// '带走公司财产'的选项
	private List<TurnoverProcessItem> takeCompanyRsItems = new ArrayList<>();

	// '带走公司财产'附件
	private String takeCompanyRsFileGuid;

	// 离职时间
	private Date turnoverDate;

	// 离职时职位
	private String turnoverPosition;

	// 离职时收入
	private TurnoverIncome turnoverIncome;

	/*
	 * 离职后状态
	 */
	// 离职后状态是否正常
	private boolean processAfterStatus;
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
	
	//是否再次雇佣
	private boolean hireAgain;

	public Turnover() {
	}

	public Turnover(Basic basic) {
		this.companyGuid = basic.getCompanyGuid();
		this.companyName = basic.getCompanyName();
		this.uuid = basic.getUuid();
		this.chNameBasic = basic.getChName();
		this.idNumberBasic = basic.getIdNumber();
		this.mobileBasic = basic.getMobile();
		this.emailBasic = basic.getEmail();
	}

	public TurnoverProcess getTurnoverProcess() {
		return turnoverProcess;
	}

	public Turnover setTurnoverProcess(TurnoverProcess turnoverProcess) {
		this.turnoverProcess = turnoverProcess;
		return this;
	}

	public String getMemberUuid() {
		return uuid;
	}

	public Turnover setMemberUuid(String memberUuid) {
		this.uuid = memberUuid;
		return this;
	}

	public String getCompanyGuid() {
		return companyGuid;
	}

	public Turnover setCompanyGuid(String companyGuid) {
		this.companyGuid = companyGuid;
		return this;
	}

	public TurnoverReason getTurnoverReason() {
		return turnoverReason;
	}

	public Turnover setTurnoverReason(TurnoverReason turnoverReason) {
		this.turnoverReason = turnoverReason;
		return this;
	}

	public String getLaborDisputeFileGuid() {
		return laborDisputeFileGuid;
	}

	public Turnover setLaborDisputeFileGuid(String laborDisputeFileGuid) {
		this.laborDisputeFileGuid = laborDisputeFileGuid;
		return this;
	}

	public String getTurnoverReasonRemark() {
		return turnoverReasonRemark;
	}

	public Turnover setTurnoverReasonRemark(String turnoverReasonRemark) {
		this.turnoverReasonRemark = turnoverReasonRemark;
		return this;
	}

	public String getOtherProcessRemark() {
		return otherProcessRemark;
	}

	public Turnover setOtherProcessRemark(String otherProcessRemark) {
		this.otherProcessRemark = otherProcessRemark;
		return this;
	}

	public Date getTurnoverDate() {
		return turnoverDate;
	}

	public Turnover setTurnoverDate(Date turnoverDate) {
		this.turnoverDate = turnoverDate;
		return this;
	}

	public String getTurnoverDateTime() {
		return DateUtils.toDateText(turnoverDate, "yyyy-MM-dd");
	}

	public boolean isBreachTrainingAgreement() {
		return breachTrainingAgreement;
	}

	public Turnover setBreachTrainingAgreement(boolean breachTrainingAgreement) {
		this.breachTrainingAgreement = breachTrainingAgreement;
		return this;
	}

	public String getBreachTrainingAgreementFileGuid() {
		return breachTrainingAgreementFileGuid;
	}

	public Turnover setBreachTrainingAgreementFileGuid(
			String breachTrainingAgreementFileGuid) {
		this.breachTrainingAgreementFileGuid = breachTrainingAgreementFileGuid;
		return this;
	}

	public boolean isOutStopPeriod() {
		return outStopPeriod;
	}

	public Turnover setOutStopPeriod(boolean outStopPeriod) {
		this.outStopPeriod = outStopPeriod;
		return this;
	}

	public String getOutStopPeriodFileGuid() {
		return outStopPeriodFileGuid;
	}

	public Turnover setOutStopPeriodFileGuid(String outStopPeriodFileGuid) {
		this.outStopPeriodFileGuid = outStopPeriodFileGuid;
		return this;
	}

	public boolean isIllegalDestroyCompanyFace() {
		return illegalDestroyCompanyFace;
	}

	public Turnover setIllegalDestroyCompanyFace(
			boolean illegalDestroyCompanyFace) {
		this.illegalDestroyCompanyFace = illegalDestroyCompanyFace;
		return this;
	}

	public String getIllegalDestroyCompanyFaceFileGuid() {
		return illegalDestroyCompanyFaceFileGuid;
	}

	public Turnover setIllegalDestroyCompanyFaceFileGuid(
			String illegalDestroyCompanyFaceFileGuid) {
		this.illegalDestroyCompanyFaceFileGuid = illegalDestroyCompanyFaceFileGuid;
		return this;
	}

	public boolean isLegalDisputes() {
		return legalDisputes;
	}

	public Turnover setLegalDisputes(boolean legalDisputes) {
		this.legalDisputes = legalDisputes;
		return this;
	}

	public String getLegalDisputesFileGuid() {
		return legalDisputesFileGuid;
	}

	public Turnover setLegalDisputesFileGuid(String legalDisputesFileGuid) {
		this.legalDisputesFileGuid = legalDisputesFileGuid;
		return this;
	}

	public String getOutOtherRemark() {
		return outOtherRemark;
	}

	public Turnover setOutOtherRemark(String outOtherRemark) {
		this.outOtherRemark = outOtherRemark;
		return this;
	}

	public String getOutOtherFileGuid() {
		return outOtherFileGuid;
	}

	public Turnover setOutOtherFileGuid(String outOtherFileGuid) {
		this.outOtherFileGuid = outOtherFileGuid;
		return this;
	}

	public List<TurnoverReasonItem> getGoOutReasonItems() {
		return goOutReasonItems;
	}

	public Turnover setGoOutReasonItems(
			List<TurnoverReasonItem> goOutReasonItems) {
		this.goOutReasonItems = goOutReasonItems;
		return this;
	}

	public List<TurnoverProcessItem> getTakeCompanyRsItems() {
		return takeCompanyRsItems;
	}

	public Turnover setTakeCompanyRsItems(
			List<TurnoverProcessItem> takeCompanyRsItems) {
		this.takeCompanyRsItems = takeCompanyRsItems;
		return this;
	}

	public TurnoverIncome getTurnoverIncome() {
		return turnoverIncome;
	}

	public Turnover setTurnoverIncome(TurnoverIncome turnoverIncome) {
		this.turnoverIncome = turnoverIncome;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Turnover setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public boolean isReasonStatus() {
		return reasonStatus;
	}

	public Turnover setReasonStatus(boolean reasonStatus) {
		this.reasonStatus = reasonStatus;
		return this;
	}

	public boolean isProcessStatus() {
		return processStatus;
	}

	public Turnover setProcessStatus(boolean processStatus) {
		this.processStatus = processStatus;
		return this;
	}

	public boolean isProcessAfterStatus() {
		return processAfterStatus;
	}

	public Turnover setProcessAfterStatus(boolean processAfterStatus) {
		this.processAfterStatus = processAfterStatus;
		return this;
	}

	public String getTurnoverPosition() {
		return turnoverPosition;
	}

	public Turnover setTurnoverPosition(String turnoverPosition) {
		this.turnoverPosition = turnoverPosition;
		return this;
	}

	public String getChNameBasic() {
		return chNameBasic;
	}

	public Turnover setChNameBasic(String chNameBasic) {
		this.chNameBasic = chNameBasic;
		return this;
	}

	public String getMobileBasic() {
		return mobileBasic;
	}

	public Turnover setMobileBasic(String mobileBasic) {
		this.mobileBasic = mobileBasic;
		return this;
	}

	public String getIdNumberBasic() {
		return idNumberBasic;
	}

	public Turnover setIdNumberBasic(String idNumberBasic) {
		this.idNumberBasic = idNumberBasic;
		return this;
	}

	public String getEmailBasic() {
		return emailBasic;
	}

	public Turnover setEmailBasic(String emailBasic) {
		this.emailBasic = emailBasic;
		return this;
	}

	public String getTakeCompanyRsFileGuid() {
		return takeCompanyRsFileGuid;
	}

	public Turnover setTakeCompanyRsFileGuid(String takeCompanyRsFileGuid) {
		this.takeCompanyRsFileGuid = takeCompanyRsFileGuid;
		return this;
	}

	public String getRevealSecretsFileGuid() {
		return revealSecretsFileGuid;
	}

	public Turnover setRevealSecretsFileGuid(String revealSecretsFileGuid) {
		this.revealSecretsFileGuid = revealSecretsFileGuid;
		return this;
	}

	public String getBriberyFileGuid() {
		return briberyFileGuid;
	}

	public Turnover setBriberyFileGuid(String briberyFileGuid) {
		this.briberyFileGuid = briberyFileGuid;
		return this;
	}

	public String getRudeFileGuid() {
		return rudeFileGuid;
	}

	public Turnover setRudeFileGuid(String rudeFileGuid) {
		this.rudeFileGuid = rudeFileGuid;
		return this;
	}

	public String getDestroyImportantAssetsFileGuid() {
		return destroyImportantAssetsFileGuid;
	}

	public Turnover setDestroyImportantAssetsFileGuid(
			String destroyImportantAssetsFileGuid) {
		this.destroyImportantAssetsFileGuid = destroyImportantAssetsFileGuid;
		return this;
	}

	public String getStealingFileGuid() {
		return stealingFileGuid;
	}

	public Turnover setStealingFileGuid(String stealingFileGuid) {
		this.stealingFileGuid = stealingFileGuid;
		return this;
	}

	public String getDefamationFileGuid() {
		return defamationFileGuid;
	}

	public Turnover setDefamationFileGuid(String defamationFileGuid) {
		this.defamationFileGuid = defamationFileGuid;
		return this;
	}

	public String getMisuseResourcesFileGuid() {
		return misuseResourcesFileGuid;
	}

	public Turnover setMisuseResourcesFileGuid(String misuseResourcesFileGuid) {
		this.misuseResourcesFileGuid = misuseResourcesFileGuid;
		return this;
	}

	public boolean isHireAgain() {
		return hireAgain;
	}

	public Turnover setHireAgain(boolean hireAgain) {
		this.hireAgain = hireAgain;
		return this;
	}

	/*
	 * public TurnoverPosition getTurnoverPosition() { return turnoverPosition;
	 * }
	 * 
	 * public Turnover setTurnoverPosition(TurnoverPosition turnoverPosition) {
	 * this.turnoverPosition = turnoverPosition; return this; }
	 */

}

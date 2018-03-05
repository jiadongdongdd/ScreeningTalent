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
package com.idsmanager.xsifter.service.dto.menber;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.IntentionStatus;
import com.idsmanager.xsifter.domain.member.*;
import com.idsmanager.xsifter.domain.position.MemberPosition;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 2016/1/29
 * 
 * @author Shengzhao Li
 */
public class TurnoverFormDto implements Serializable {
	private static final long serialVersionUID = 6390077635261728046L;

	private String memberUuid;
	// 离职原因是否正常
	private boolean reasonStatus = true;
	// 离职原因
	private TurnoverReason turnoverReason;

	private MultipartFile laborFile;
	// 离职原因中 劳动纠纷的 附件
	private String laborDisputeFileGuid;

	// 开除原因为OTHER的备注 信息
	private String turnoverReasonRemark;

	// 开除 的原因, 可以多个
	private List<TurnoverReasonItem> goOutReasonItems = new ArrayList<>();

	private MultipartFile revealSecretsFile;
	// 开除 - 泄露公司机密附件
	private String revealSecretsFileGuid;

	private MultipartFile briberyFile;
	// 贪污受贿挪用公款附件
	private String briberyFileGuid;

	private MultipartFile rudeFile;
	// 言行粗鲁附件
	private String rudeFileGuid;

	private MultipartFile destroyImportantAssetsFile;
	// 破坏公司重要资产附件
	private String destroyImportantAssetsFileGuid;

	private MultipartFile stealingFile;
	// 偷盗公司财物
	private String stealingFileGuid;

	private MultipartFile defamationFile;
	// 诋毁公司名誉附件
	private String defamationFileGuid;

	private MultipartFile misuseResourcesFile;
	// 滥用公司资源附件
	private String misuseResourcesFileGuid;

	/*
	 * 离职过程
	 */
	// 离职过程是否正常
	private boolean processStatus = true;
	// 离职过程
	private TurnoverProcess turnoverProcess;

	// 离职过程中 OTHER 的注明信息
	private String otherProcessRemark;

	// '带走公司财产'的选项
	private List<TurnoverProcessItem> takeCompanyRsItems = new ArrayList<>();

	// '带走公司财产'附件guid
	private String takeCompanyRsFileGuid;
	// '带走公司财产'附件
	private MultipartFile takeCompanyRsFile;

	// 离职时间
	private String turnoverDate;

	// 离职时职位
	private String turnoverPosition;

	private List<MemberPosition> positionList;

	// 离职时收入
	private TurnoverIncome turnoverIncome;

	/*
	 * 离职后状态
	 */
	// 离职后状态是否正常
	private boolean processAfterStatus = true;
	// 是否违反培训协议
	private boolean breachTrainingAgreement;
	// 若违反培训协议的 附件证据
	private String breachTrainingAgreementFileGuid;

	private MultipartFile brTrainFile;

	// 是否在避业禁止期内(是/否)
	private boolean outStopPeriod;
	// 避业禁止期内 附件证据
	private String outStopPeriodFileGuid;

	private MultipartFile stopPeriFile;

	// 离职后用非法手段恶意毁坏公司形象
	private boolean illegalDestroyCompanyFace;
	// 非法手段恶意毁坏公司形象 附件证据
	private String illegalDestroyCompanyFaceFileGuid;

	private MultipartFile illegalDesCFFile;

	// 离职后和原单位发生劳动诉讼或法律纠纷
	private boolean legalDisputes;
	// 离职后和原单位发生劳动诉讼或法律纠纷 附件证据 上传判决文件
	private String legalDisputesFileGuid;

	private MultipartFile legalDisFile;

	// 离职后 其他信息注明
	private String outOtherRemark;
	// 离职后 其他信息注明 附件
	private String outOtherFileGuid;

	private MultipartFile outOtherFile;

	public boolean next;

	// 是否再次雇佣
	private boolean hireAgain;
	
	//临时属性
	private boolean add = false;
	

	public TurnoverFormDto() {
	}

	public TurnoverFormDto(Turnover over) {
		this.memberUuid = over.getMemberUuid();
		this.turnoverDate = DateUtils.toDate(over.getTurnoverDate());
		this.turnoverReason = over.getTurnoverReason();

		this.revealSecretsFileGuid = over.getRevealSecretsFileGuid();
		this.briberyFileGuid = over.getBriberyFileGuid();
		this.rudeFileGuid = over.getRudeFileGuid();
		this.destroyImportantAssetsFileGuid = over
				.getDestroyImportantAssetsFileGuid();
		this.stealingFileGuid = over.getStealingFileGuid();
		this.defamationFileGuid = over.getDefamationFileGuid();
		this.misuseResourcesFileGuid = over.getMisuseResourcesFileGuid();

		this.turnoverIncome = over.getTurnoverIncome();
		this.turnoverPosition = over.getTurnoverPosition();

		this.laborDisputeFileGuid = over.getLaborDisputeFileGuid();
		this.turnoverReasonRemark = over.getTurnoverReasonRemark();
		this.turnoverProcess = over.getTurnoverProcess();

		this.otherProcessRemark = over.getOtherProcessRemark();
		this.breachTrainingAgreement = over.isBreachTrainingAgreement();
		this.breachTrainingAgreementFileGuid = over
				.getBreachTrainingAgreementFileGuid();

		this.outStopPeriod = over.isOutStopPeriod();
		this.outStopPeriodFileGuid = over.getOutStopPeriodFileGuid();
		this.illegalDestroyCompanyFace = over.isIllegalDestroyCompanyFace();

		this.illegalDestroyCompanyFaceFileGuid = over
				.getIllegalDestroyCompanyFaceFileGuid();
		this.legalDisputes = over.isLegalDisputes();
		this.legalDisputesFileGuid = over.getLegalDisputesFileGuid();

		this.outOtherRemark = over.getOutOtherRemark();
		this.outOtherFileGuid = over.getOutOtherFileGuid();
		this.goOutReasonItems = over.getGoOutReasonItems();

		this.takeCompanyRsItems = over.getTakeCompanyRsItems();
		this.takeCompanyRsFileGuid = over.getTakeCompanyRsFileGuid();

		this.reasonStatus = over.isReasonStatus();
		this.processStatus = over.isProcessStatus();
		this.processAfterStatus = over.isProcessAfterStatus();
		this.hireAgain = over.isHireAgain();
	}

	public TurnoverFormDto(String memberUuid) {
		this.memberUuid = memberUuid;
	}

	public List<TurnoverProcessItem> getTakeRsProcessItems() {
		return TurnoverProcessItem.getItems(TurnoverProcess.TAKE_RESOURCES);
	}

	public List<TurnoverReasonItem> getOutReasonItems() {
		return TurnoverReasonItem.getItems(TurnoverReason.GO_OUT);
	}

	public TurnoverReason[] getTurnoverReasons() {
		return TurnoverReason.values();
	}

	public TurnoverProcess[] getTurnoverProcesses() {
		return TurnoverProcess.values();
	}

	public TurnoverIncome[] getTurnoverIncomes() {
		return TurnoverIncome.values();
	}

	public TurnoverPosition[] getTurnoverPositions() {
		return TurnoverPosition.values();
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public String getMemberUuid() {
		return memberUuid;
	}

	public void setMemberUuid(String memberUuid) {
		this.memberUuid = memberUuid;
	}

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

	public String getTurnoverDate() {
		return turnoverDate;
	}

	public void setTurnoverDate(String turnoverDate) {
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

	public List<TurnoverReasonItem> getGoOutReasonItems() {
		return goOutReasonItems;
	}

	public void setGoOutReasonItems(List<TurnoverReasonItem> goOutReasonItems) {
		this.goOutReasonItems = goOutReasonItems;
	}

	public MultipartFile getLaborFile() {
		return laborFile;
	}

	public void setLaborFile(MultipartFile laborFile) {
		this.laborFile = laborFile;
	}

	public List<TurnoverProcessItem> getTakeCompanyRsItems() {
		return takeCompanyRsItems;
	}

	public void setTakeCompanyRsItems(
			List<TurnoverProcessItem> takeCompanyRsItems) {
		this.takeCompanyRsItems = takeCompanyRsItems;
	}

	public MultipartFile getBrTrainFile() {
		return brTrainFile;
	}

	public void setBrTrainFile(MultipartFile brTrainFile) {
		this.brTrainFile = brTrainFile;
	}

	public MultipartFile getIllegalDesCFFile() {
		return illegalDesCFFile;
	}

	public void setIllegalDesCFFile(MultipartFile illegalDesCFFile) {
		this.illegalDesCFFile = illegalDesCFFile;
	}

	public MultipartFile getStopPeriFile() {
		return stopPeriFile;
	}

	public void setStopPeriFile(MultipartFile stopPeriFile) {
		this.stopPeriFile = stopPeriFile;
	}

	public MultipartFile getLegalDisFile() {
		return legalDisFile;
	}

	public void setLegalDisFile(MultipartFile legalDisFile) {
		this.legalDisFile = legalDisFile;
	}

	public MultipartFile getOutOtherFile() {
		return outOtherFile;
	}

	public void setOutOtherFile(MultipartFile outOtherFile) {
		this.outOtherFile = outOtherFile;
	}

	public String getTurnoverPosition() {
		return turnoverPosition;
	}

	public void setTurnoverPosition(String turnoverPosition) {
		this.turnoverPosition = turnoverPosition;
	}

	public List<MemberPosition> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<MemberPosition> positionList) {
		this.positionList = positionList;
	}

	public TurnoverIncome getTurnoverIncome() {
		return turnoverIncome;
	}

	public void setTurnoverIncome(TurnoverIncome turnoverIncome) {
		this.turnoverIncome = turnoverIncome;
	}

	public Turnover updateFields(Turnover turnover) {
		return turnover.setTurnoverReason(this.turnoverReason)
				.setTurnoverReasonRemark(this.turnoverReasonRemark)
				.setGoOutReasonItems(this.goOutReasonItems)

				.setTurnoverProcess(this.turnoverProcess)
				.setOtherProcessRemark(this.otherProcessRemark)
				.setTurnoverDate(DateUtils.getDate(this.turnoverDate))
				.setTurnoverIncome(turnoverIncome)
				.setTurnoverPosition(turnoverPosition)

				.setBreachTrainingAgreement(this.breachTrainingAgreement)
				.setOutStopPeriod(this.outStopPeriod)
				.setIllegalDestroyCompanyFace(this.illegalDestroyCompanyFace)

				.setLegalDisputes(this.legalDisputes)
				.setTakeCompanyRsItems(this.takeCompanyRsItems)
				.setOutOtherRemark(this.outOtherRemark)
				.setReasonStatus(this.reasonStatus)
				.setProcessStatus(processStatus)
				.setProcessAfterStatus(processAfterStatus)
				.setHireAgain(hireAgain);
	}

	public boolean isReasonStatus() {
		return reasonStatus;
	}

	public void setReasonStatus(boolean reasonStatus) {
		this.reasonStatus = reasonStatus;
	}

	public boolean isProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(boolean processStatus) {
		this.processStatus = processStatus;
	}

	public boolean isProcessAfterStatus() {
		return processAfterStatus;
	}

	public void setProcessAfterStatus(boolean processAfterStatus) {
		this.processAfterStatus = processAfterStatus;
	}

	public String getTakeCompanyRsFileGuid() {
		return takeCompanyRsFileGuid;
	}

	public void setTakeCompanyRsFileGuid(String takeCompanyRsFileGuid) {
		this.takeCompanyRsFileGuid = takeCompanyRsFileGuid;
	}

	public MultipartFile getTakeCompanyRsFile() {
		return takeCompanyRsFile;
	}

	public void setTakeCompanyRsFile(MultipartFile takeCompanyRsFile) {
		this.takeCompanyRsFile = takeCompanyRsFile;
	}

	public MultipartFile getRevealSecretsFile() {
		return revealSecretsFile;
	}

	public void setRevealSecretsFile(MultipartFile revealSecretsFile) {
		this.revealSecretsFile = revealSecretsFile;
	}

	public String getRevealSecretsFileGuid() {
		return revealSecretsFileGuid;
	}

	public void setRevealSecretsFileGuid(String revealSecretsFileGuid) {
		this.revealSecretsFileGuid = revealSecretsFileGuid;
	}

	public MultipartFile getBriberyFile() {
		return briberyFile;
	}

	public void setBriberyFile(MultipartFile briberyFile) {
		this.briberyFile = briberyFile;
	}

	public String getBriberyFileGuid() {
		return briberyFileGuid;
	}

	public void setBriberyFileGuid(String briberyFileGuid) {
		this.briberyFileGuid = briberyFileGuid;
	}

	public MultipartFile getRudeFile() {
		return rudeFile;
	}

	public void setRudeFile(MultipartFile rudeFile) {
		this.rudeFile = rudeFile;
	}

	public String getRudeFileGuid() {
		return rudeFileGuid;
	}

	public void setRudeFileGuid(String rudeFileGuid) {
		this.rudeFileGuid = rudeFileGuid;
	}

	public MultipartFile getDestroyImportantAssetsFile() {
		return destroyImportantAssetsFile;
	}

	public void setDestroyImportantAssetsFile(
			MultipartFile destroyImportantAssetsFile) {
		this.destroyImportantAssetsFile = destroyImportantAssetsFile;
	}

	public String getDestroyImportantAssetsFileGuid() {
		return destroyImportantAssetsFileGuid;
	}

	public void setDestroyImportantAssetsFileGuid(
			String destroyImportantAssetsFileGuid) {
		this.destroyImportantAssetsFileGuid = destroyImportantAssetsFileGuid;
	}

	public MultipartFile getStealingFile() {
		return stealingFile;
	}

	public void setStealingFile(MultipartFile stealingFile) {
		this.stealingFile = stealingFile;
	}

	public String getStealingFileGuid() {
		return stealingFileGuid;
	}

	public void setStealingFileGuid(String stealingFileGuid) {
		this.stealingFileGuid = stealingFileGuid;
	}

	public MultipartFile getDefamationFile() {
		return defamationFile;
	}

	public void setDefamationFile(MultipartFile defamationFile) {
		this.defamationFile = defamationFile;
	}

	public String getDefamationFileGuid() {
		return defamationFileGuid;
	}

	public void setDefamationFileGuid(String defamationFileGuid) {
		this.defamationFileGuid = defamationFileGuid;
	}

	public MultipartFile getMisuseResourcesFile() {
		return misuseResourcesFile;
	}

	public void setMisuseResourcesFile(MultipartFile misuseResourcesFile) {
		this.misuseResourcesFile = misuseResourcesFile;
	}

	public String getMisuseResourcesFileGuid() {
		return misuseResourcesFileGuid;
	}

	public void setMisuseResourcesFileGuid(String misuseResourcesFileGuid) {
		this.misuseResourcesFileGuid = misuseResourcesFileGuid;
	}

	public boolean isHireAgain() {
		return hireAgain;
	}

	public void setHireAgain(boolean hireAgain) {
		this.hireAgain = hireAgain;
	}

	public boolean isAdd() {
		return add;
	}

	public void setAdd(boolean add) {
		this.add = add;
	}

	 

}

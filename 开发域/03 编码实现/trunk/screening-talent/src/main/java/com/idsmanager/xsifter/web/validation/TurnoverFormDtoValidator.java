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
package com.idsmanager.xsifter.web.validation;

import java.util.List;

import com.idsmanager.xsifter.domain.member.TurnoverProcess;
import com.idsmanager.xsifter.domain.member.TurnoverReason;
import com.idsmanager.xsifter.domain.member.TurnoverReasonItem;
import com.idsmanager.xsifter.service.dto.menber.TurnoverFormDto;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

/**
 * 2016/1/29
 * 
 * @author Shengzhao Li
 */
@Component
public class TurnoverFormDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TurnoverFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TurnoverFormDto formDto = (TurnoverFormDto) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memberUuid", null,
				"数据有误");
		// 验证离职原因
		validateTurnoverReason(formDto, errors);
		// 验证离职过程
		validateTurnoverProcess(formDto, errors);
		// 验证离职后状态
		validateTurnoverStatus(formDto, errors);
		validateTurnoverOtherFile(formDto, errors);
	}

	private void validateTurnoverStatus(TurnoverFormDto formDto, Errors errors) {
		final boolean b1 = formDto.isLegalDisputes();
		boolean processAfterStatus = formDto.isProcessAfterStatus();
		if (!processAfterStatus) {
			if (b1) {
				String guid = formDto.getLegalDisputesFileGuid();
				MultipartFile file = formDto.getLegalDisFile();
				if (StringUtils.isEmpty(guid)) {
					if (file != null && file.isEmpty()) {
						errors.rejectValue("legalDisputesFileGuid", null,
								"劳动纠纷附件不能为空");
						return;
					}

				}
				if (file != null && !file.isEmpty()) {
					if (file.getSize() > 20 * 1048576) {
						errors.rejectValue("legalDisputesFileGuid", null,
								"请检查您上传的附件大小！");
					}
				}
			}
			final boolean b2 = formDto.isBreachTrainingAgreement();
			if (b2) {
				String guid = formDto.getBreachTrainingAgreementFileGuid();
				MultipartFile file = formDto.getBrTrainFile();
				if (StringUtils.isEmpty(guid)) {
					if (file != null && file.isEmpty()) {
						errors.rejectValue("breachTrainingAgreementFileGuid",
								null, "违反培训协议附件不能为空");
						return;
					}
				}
				if (file != null && !file.isEmpty()) {
					if (file.getSize() > 20 * 1048576) {
						errors.rejectValue("breachTrainingAgreementFileGuid",
								null, "请检查您上传的附件大小！");
					}
				}
			}

			final boolean b3 = formDto.isOutStopPeriod();
			if (b3) {
				String guid = formDto.getOutStopPeriodFileGuid();
				MultipartFile file = formDto.getStopPeriFile();
				if (StringUtils.isEmpty(guid)) {
					if (file != null && file.isEmpty()) {
						errors.rejectValue("outStopPeriodFileGuid", null,
								"竟业期内 附件证据不能为空");
						return;
					}
				}

				if (file != null && !file.isEmpty()) {
					if (file.getSize() > 20 * 1048576) {
						errors.rejectValue("outStopPeriodFileGuid", null,
								"请检查您上传的附件大小！");
					}
				}
			}
			final boolean b4 = formDto.isIllegalDestroyCompanyFace();
			if (b4) {
				String guid = formDto.getIllegalDestroyCompanyFaceFileGuid();
				MultipartFile file = formDto.getIllegalDesCFFile();
				if (StringUtils.isEmpty(guid)) {
					if (file != null && file.isEmpty()) {
						errors.rejectValue("illegalDestroyCompanyFaceFileGuid",
								null, "非法手段恶意毁坏公司形象 附件证据不能为空");
						return;
					}
				}

				if (file != null && !file.isEmpty()) {
					if (file.getSize() > 20 * 1048576) {
						errors.rejectValue("illegalDestroyCompanyFaceFileGuid",
								null, "请检查您上传的附件大小！");
					}
				}
			}
			final String outOtherRemark = formDto.getOutOtherRemark();
			if (StringUtils.isEmpty(outOtherRemark)) {
				errors.rejectValue("outOtherRemark", null, "离职后其他信息必须注明");
			}
		}

	}

	private void validateTurnoverProcess(TurnoverFormDto formDto, Errors errors) {
		final TurnoverProcess process = formDto.getTurnoverProcess();
		boolean processStatus = formDto.isProcessStatus();
		// boolean b = formDto.isProcessAfterStatus();
		if (!processStatus) {
			if (process != null && process.equals(TurnoverProcess.OTHER)) {
				String remark = formDto.getOtherProcessRemark();
				if (StringUtils.isEmpty(remark)) {
					errors.rejectValue("otherProcessRemark", null,
							"'其他' 离职过程,必须注明原因");
					return;
				}
			}

			if (process != null
					&& process.equals(TurnoverProcess.TAKE_RESOURCES)
					&& formDto.getTakeCompanyRsItems() != null
					&& formDto.getTakeCompanyRsItems().size() > 0) {
				final String guid = formDto.getTakeCompanyRsFileGuid();

				MultipartFile file = formDto.getTakeCompanyRsFile();
				if (StringUtils.isEmpty(guid)) {
					if (file != null && file.isEmpty()) {
						errors.rejectValue("takeCompanyRsFileGuid", null,
								"带走公司财产附件不能为空");
						return;
					}
				}
				if (file != null && !file.isEmpty()) {
					if (file.getSize() > 20 * 1048576) {
						errors.rejectValue("takeCompanyRsFileGuid", null,
								"请检查您上传的附件大小！");
					}
				}
			}

		}

	}

	private void validateTurnoverReason(TurnoverFormDto formDto, Errors errors) {
		final boolean reasonStatus = formDto.isReasonStatus();
		if (!reasonStatus) {

			TurnoverReason reason = formDto.getTurnoverReason();
			if (reason != null && reason.equals(TurnoverReason.LABOR_DISPUTE)) {
				String guid = formDto.getLaborDisputeFileGuid();
				MultipartFile file = formDto.getLaborFile();
				if (StringUtils.isEmpty(guid)) {
					if (file != null && file.isEmpty()) {
						errors.rejectValue("laborDisputeFileGuid", null,
								"劳动纠纷附件不能为空");
						return;
					}
				}

				if (file != null && !file.isEmpty()) {
					if (file.getSize() > 20 * 1048576) {
						errors.rejectValue("laborDisputeFileGuid", null,
								"请检查您上传的附件大小！");
					}
				}
			}

			if (reason != null && reason.equals(TurnoverReason.GO_OUT)) {
				List<TurnoverReasonItem> items = formDto.getGoOutReasonItems();
				if (items.indexOf(TurnoverReasonItem.OTHER) >= 0) {
					String remark = formDto.getTurnoverReasonRemark();
					if (StringUtils.isEmpty(remark)) {
						errors.rejectValue("turnoverReasonRemark", null,
								"开除原因选择'其他' ,必须注明原因");
						return;
					}
				}
				// 泄露
				validateRevealSecretsFile(formDto, errors, items);
				validateBriberyFile(formDto, errors, items);
				validateRudeFile(formDto, errors, items);
				validateDestroyImportantAssetsFile(formDto, errors, items);
				validateStealingFile(formDto, errors, items);
				validateDefamationFile(formDto, errors, items);
				validateMisuseResourcesFile(formDto, errors, items);

			}

		}

	}

	private void validateMisuseResourcesFile(TurnoverFormDto formDto,
			Errors errors, List<TurnoverReasonItem> items) {

		if (items.indexOf(TurnoverReasonItem.MISUSE_RESOURCES) >= 0) {
			String misuseResourcesFileGuid = formDto
					.getMisuseResourcesFileGuid();
			MultipartFile misuseResourcesFile = formDto
					.getMisuseResourcesFile();

			if (StringUtils.isEmpty(misuseResourcesFileGuid)) {
				if (misuseResourcesFile != null
						&& misuseResourcesFile.isEmpty()) {
					errors.rejectValue("misuseResourcesFileGuid", null,
							"滥用公司资源附件不能为空");
					return;
				}
			}

			if (misuseResourcesFile != null && !misuseResourcesFile.isEmpty()) {
				if (misuseResourcesFile.getSize() > 20 * 1048576) {
					errors.rejectValue("misuseResourcesFileGuid", null,
							"请检查您上传的附件大小！");
				}
			}

		}

	}

	private void validateDefamationFile(TurnoverFormDto formDto, Errors errors,
			List<TurnoverReasonItem> items) {
		if (items.indexOf(TurnoverReasonItem.DEFAMATION) >= 0) {
			String defamationFileGuid = formDto.getDefamationFileGuid();
			MultipartFile defamationFile = formDto.getDefamationFile();
			if (StringUtils.isEmpty(defamationFileGuid)) {
				if (defamationFile != null && defamationFile.isEmpty()) {
					errors.rejectValue("defamationFileGuid", null,
							"诋毁公司名誉附件不能为空");
					return;
				}
			}

			if (defamationFile != null && !defamationFile.isEmpty()) {
				if (defamationFile.getSize() > 20 * 1048576) {
					errors.rejectValue("defamationFileGuid", null,
							"请检查您上传的附件大小！");
				}
			}
		}
	}

	private void validateStealingFile(TurnoverFormDto formDto, Errors errors,
			List<TurnoverReasonItem> items) {
		if (items.indexOf(TurnoverReasonItem.STEALING) >= 0) {
			String stealingFileGuid = formDto.getStealingFileGuid();
			MultipartFile stealingFile = formDto.getStealingFile();
			if (StringUtils.isEmpty(stealingFileGuid)) {
				if (stealingFile != null && stealingFile.isEmpty()) {
					errors.rejectValue("stealingFileGuid", null, "偷盗公司财物附件不能为空");
					return;
				}
			}

			if (stealingFile != null && !stealingFile.isEmpty()) {
				if (stealingFile.getSize() > 20 * 1048576) {
					errors.rejectValue("stealingFileGuid", null,
							"请检查您上传的附件大小！");
				}
			}

		}

	}

	private void validateDestroyImportantAssetsFile(TurnoverFormDto formDto,
			Errors errors, List<TurnoverReasonItem> items) {

		if (items.indexOf(TurnoverReasonItem.DESTROY_IMPORTANT_ASSETS) >= 0) {
			String destroyImportantAssetsFileGuid = formDto
					.getDestroyImportantAssetsFileGuid();
			MultipartFile destroyImportantAssetsFile = formDto
					.getDestroyImportantAssetsFile();
			if (StringUtils.isEmpty(destroyImportantAssetsFileGuid)) {
				if (destroyImportantAssetsFile != null
						&& destroyImportantAssetsFile.isEmpty()) {
					errors.rejectValue("destroyImportantAssetsFileGuid", null,
							"破坏公司重要资产附件不能为空");
					return;
				}
			}

			if (destroyImportantAssetsFile != null
					&& !destroyImportantAssetsFile.isEmpty()) {
				if (destroyImportantAssetsFile.getSize() > 20 * 1048576) {
					errors.rejectValue("destroyImportantAssetsFileGuid", null,
							"请检查您上传的附件大小！");
				}
			}
		}

	}

	private void validateRudeFile(TurnoverFormDto formDto, Errors errors,
			List<TurnoverReasonItem> items) {

		if (items.indexOf(TurnoverReasonItem.RUDE) >= 0) {
			String rudeFileGuid = formDto.getRudeFileGuid();
			MultipartFile rudeFile = formDto.getRudeFile();
			if (StringUtils.isEmpty(rudeFileGuid)) {
				if (rudeFile != null && rudeFile.isEmpty()) {
					errors.rejectValue("rudeFileGuid", null, "言行粗鲁附件不能为空");
					return;
				}
			}

			if (rudeFile != null && !rudeFile.isEmpty()) {
				if (rudeFile.getSize() > 20 * 1048576) {
					errors.rejectValue("rudeFileGuid", null, "请检查您上传的附件大小！");
				}
			}
		}

	}

	private void validateBriberyFile(TurnoverFormDto formDto, Errors errors,
			List<TurnoverReasonItem> items) {
		if (items.indexOf(TurnoverReasonItem.BRIBERY) >= 0) {
			String briberyFileGuid = formDto.getBriberyFileGuid();
			MultipartFile briberyFile = formDto.getBriberyFile();
			if (StringUtils.isEmpty(briberyFileGuid)) {
				if (briberyFile != null && briberyFile.isEmpty()) {
					errors.rejectValue("briberyFileGuid", null,
							"贪污受贿挪用公款附件不能为空");
					return;
				}
			}

			if (briberyFile != null && !briberyFile.isEmpty()) {
				if (briberyFile.getSize() > 20 * 1048576) {
					errors.rejectValue("briberyFileGuid", null,
							"请检查您上传的附件大小！");
				}
			}
		}

	}

	private void validateRevealSecretsFile(TurnoverFormDto formDto,
			Errors errors, List<TurnoverReasonItem> items) {
		if (items.indexOf(TurnoverReasonItem.REVEAL_COMPANY_SECRETS) >= 0) {
			String revealSecretsFileGuid = formDto.getRevealSecretsFileGuid();
			MultipartFile revealSecretsFile = formDto.getRevealSecretsFile();
			if (StringUtils.isEmpty(revealSecretsFileGuid)) {
				if (revealSecretsFile != null && revealSecretsFile.isEmpty()) {
					errors.rejectValue("revealSecretsFileGuid", null,
							"泄露公司机密附件不能为空");
					return;
				}
			}

			if (revealSecretsFile != null && !revealSecretsFile.isEmpty()) {
				if (revealSecretsFile.getSize() > 20 * 1048576) {
					errors.rejectValue("revealSecretsFileGuid", null,
							"请检查您上传的附件大小！");
				}
			}
		}

	}

	private void validateTurnoverOtherFile(TurnoverFormDto formDto,
			Errors errors) {
		boolean b = formDto.isProcessAfterStatus();
		final String outOtherRemark = formDto.getOutOtherRemark();
		if (!b && !"无".equals(outOtherRemark)) {
			final String guid = formDto.getOutOtherFileGuid();
			MultipartFile file = formDto.getOutOtherFile();
			if (StringUtils.isEmpty(guid)) {
				if (file != null && file.isEmpty()) {
					errors.rejectValue("outOtherFileGuid", null,
							"离职后 其他信息注明 附件不能为空");
				}
			}

			if (file != null && !file.isEmpty()) {
				if (file.getSize() > 20 * 1048576) {
					errors.rejectValue("outOtherFileGuid", null,
							"请检查您上传的附件大小！");
				}
			}
		}
	}
}

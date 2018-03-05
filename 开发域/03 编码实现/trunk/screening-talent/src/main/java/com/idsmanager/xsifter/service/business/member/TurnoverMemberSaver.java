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
package com.idsmanager.xsifter.service.business.member;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.domain.commons.IdsFile;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.log.Log;
import com.idsmanager.xsifter.domain.log.LogRepository;
import com.idsmanager.xsifter.domain.log.OperateType;
import com.idsmanager.xsifter.domain.member.Basic;
import com.idsmanager.xsifter.domain.member.Education;
import com.idsmanager.xsifter.domain.member.Entry;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.business.admin.CreditStreamCreater;
import com.idsmanager.xsifter.service.dto.menber.TurnoverFormDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 2016/1/29
 * 
 * @author Shengzhao Li
 */
public class TurnoverMemberSaver {

	private static final Logger LOG = LoggerFactory
			.getLogger(TurnoverMemberSaver.class);

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private transient UserRepository userRepository = BeanProvider
			.getBean(UserRepository.class);
	private transient LogRepository logRepository = BeanProvider
			.getBean(LogRepository.class);

	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private TurnoverFormDto formDto;

	public TurnoverMemberSaver(TurnoverFormDto formDto) {
		this.formDto = formDto;
	}

	public String save() {

		Turnover turnover = memberRepository.findTurnoverByUuid(formDto
				.getMemberUuid());
		Basic basic = this.memberRepository.findBasicByUuid(formDto
				.getMemberUuid());
		Education education = this.memberRepository.findEducationByUuid(formDto
				.getMemberUuid());
		Member member = this.memberRepository.findMemberByUuid(formDto
				.getMemberUuid());

		Entry entry = this.memberRepository.findEntryByUuid(formDto
				.getMemberUuid());

		Recruitment recruitment = this.memberRepository
				.findRecruitmentByUuid(formDto.getMemberUuid());

		if (turnover == null) {
			turnover = createTurnover(basic);
			createOrUpdateMember(basic, education, member, turnover);
			this.logRepository
					.saveLog(createMemberTurnoverLog(turnover, basic));
		} else {
			turnover = updateTurnover(turnover, basic);
			createOrUpdateMember(basic, education, member, turnover);
			this.logRepository
					.saveLog(updateMemberTurnoverLog(turnover, basic));
		}

		saveEntry(entry, turnover, recruitment);

		return turnover.getMemberUuid();
	}

	private void updateLevel() {
		int num = (int) this.memberRepository
				.getMemberCountByCompanyUuid(SecurityUtils.currentUserGuid());
		Company company = this.companyRepository.findByGuid(SecurityUtils
				.currentUserGuid());
		if (company != null) {
			String level = company.getLevel();
			if (1 <= num && num <= 10) {
				if (!"A".equals(level)) {
					company.setLevel("A");
					this.companyRepository.saveCompany(company);
				}
			} else if (10 < num && num <= 30) {
				if (!"B".equals(level)) {
					company.setLevel("B");
					this.companyRepository.saveCompany(company);
				}
			} else if (30 < num && num <= 100) {
				if (!"C".equals(level)) {
					company.setLevel("C");
					this.companyRepository.saveCompany(company);
				}
			} else if (100 < num && num <= 300) {
				if (!"D".equals(level)) {
					company.setLevel("D");
					this.companyRepository.saveCompany(company);
				}
			} else if (300 < num && num < 2000) {
				if (!"E".equals(level)) {
					company.setLevel("E");
					this.companyRepository.saveCompany(company);
				}
			}
		}

	}

	private void createOrUpdateMember(Basic basic, Education education,
			Member member, Turnover turnover) {

		if (member == null) {
			member = new Member(basic, education);
			member.setWorkPosition(turnover.getTurnoverPosition());
			this.memberRepository.saveMember(member);
			updateLevel();
			LOG.debug("{}|Create  Member: {}", SecurityUtils.username(), member);
			this.logRepository.saveLog(createMemberLog(member));
			creditHandle(member);
		} else {
			member.setWorkPosition(turnover.getTurnoverPosition());
			this.memberRepository.saveMember(member);
			LOG.debug("{}|Update Member: {}", SecurityUtils.username(), member);
		}

	}

	private void creditHandle(Member member) {
		User user = this.userRepository.findByGuid(SecurityUtils
				.currentUserGuid());

		if (user == null) {
			CreditStreamCreater streamCreater = new CreditStreamCreater(
					new CreditStreamHolder(member.getCompanyGuid(), "添加职工 _"
							+ member.getChName(), CreditAction.ADD));
			try {
				streamCreater.create();
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}

	}

	private void saveEntry(Entry entry, Turnover turnover,
			Recruitment recruitment) {

		if (entry == null) {
			entry = new Entry(turnover);
		} else {
			entry.setTurnover(turnover);
		}

		if (recruitment != null) {
			entry.setEntryDate(recruitment.getEntryDate());
		}

		this.memberRepository.saveEntry(entry);

	}

	private Turnover updateTurnover(Turnover turnover, Basic basic) {
		formDto.updateFields(turnover);
		updateFiles(turnover);

		turnover.setChNameBasic(basic.getChName())
				.setIdNumberBasic(basic.getIdNumber())
				.setMobileBasic(basic.getMobile())
				.setEmailBasic(basic.getEmail());

		memberRepository.saveTurnover(turnover);
		LOG.debug("{}|Update Turnover: {}", SecurityUtils.username(), turnover);

		return turnover;
	}

	private Turnover createTurnover(Basic basic) {

		Turnover turnover = formDto.updateFields(new Turnover());
		turnover.setMemberUuid(formDto.getMemberUuid()).setCompanyGuid(
				SecurityUtils.currentUserGuid());

		updateFiles(turnover);

		turnover.setChNameBasic(basic.getChName())
				.setIdNumberBasic(basic.getIdNumber())
				.setMobileBasic(basic.getMobile())
				.setEmailBasic(basic.getEmail())
				.setCompanyName(basic.getCompanyName());

		memberRepository.saveTurnover(turnover);
		LOG.debug("{}|Create Turnover: {}", SecurityUtils.username(), turnover);

		return turnover;
	}

	private void updateFiles(Turnover turnover) {

		final String revealSecretsFileGuid = createAndSaveFile(formDto
				.getRevealSecretsFile());
		if (revealSecretsFileGuid != null) {
			turnover.setRevealSecretsFileGuid(revealSecretsFileGuid);
		}

		final String briberyFileGuid = createAndSaveFile(formDto
				.getBriberyFile());
		if (briberyFileGuid != null) {
			turnover.setBriberyFileGuid(briberyFileGuid);
		}

		final String rudeFileGuid = createAndSaveFile(formDto.getRudeFile());
		if (rudeFileGuid != null) {
			turnover.setRudeFileGuid(rudeFileGuid);
		}

		final String destroyImportantAssetsFileGuid = createAndSaveFile(formDto
				.getDestroyImportantAssetsFile());
		if (destroyImportantAssetsFileGuid != null) {
			turnover.setDestroyImportantAssetsFileGuid(destroyImportantAssetsFileGuid);
		}

		final String stealingFileGuid = createAndSaveFile(formDto
				.getStealingFile());
		if (stealingFileGuid != null) {
			turnover.setStealingFileGuid(stealingFileGuid);
		}

		final String defamationFileGuid = createAndSaveFile(formDto
				.getDefamationFile());
		if (defamationFileGuid != null) {
			turnover.setDefamationFileGuid(defamationFileGuid);
		}

		final String misuseResourcesFileGuid = createAndSaveFile(formDto
				.getMisuseResourcesFile());
		if (misuseResourcesFileGuid != null) {
			turnover.setMisuseResourcesFileGuid(misuseResourcesFileGuid);
		}

		final String laborFileGuid = createAndSaveFile(formDto.getLaborFile());
		if (laborFileGuid != null) {
			turnover.setLaborDisputeFileGuid(laborFileGuid);
		}

		final String brTrainFileGuid = createAndSaveFile(formDto
				.getBrTrainFile());
		if (brTrainFileGuid != null) {
			turnover.setBreachTrainingAgreementFileGuid(brTrainFileGuid);
		}

		final String stopPerFileGuid = createAndSaveFile(formDto
				.getStopPeriFile());
		if (stopPerFileGuid != null) {
			turnover.setOutStopPeriodFileGuid(stopPerFileGuid);
		}

		final String illegalDescFileGuid = createAndSaveFile(formDto
				.getIllegalDesCFFile());
		if (illegalDescFileGuid != null) {
			turnover.setIllegalDestroyCompanyFaceFileGuid(illegalDescFileGuid);
		}

		final String legalDisFileGuid = createAndSaveFile(formDto
				.getLegalDisFile());
		if (legalDisFileGuid != null) {
			turnover.setLegalDisputesFileGuid(legalDisFileGuid);
		}

		final String otherFileGuid = createAndSaveFile(formDto
				.getOutOtherFile());
		if (otherFileGuid != null) {
			turnover.setOutOtherFileGuid(otherFileGuid);
		}

		final String takeCompanyRsFileGuid = createAndSaveFile(formDto
				.getTakeCompanyRsFile());

		if (takeCompanyRsFileGuid != null) {
			turnover.setTakeCompanyRsFileGuid(takeCompanyRsFileGuid);
		}

	}

	private String createAndSaveFile(MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			try {
				IdsFile idsFile = new IdsFile(file.getOriginalFilename(),
						file.getBytes());
				idsFile.save();
				return idsFile.getGuid();
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
		return null;
	}

	private Log createMemberLog(Member member) {
		return new Log().setEntity(member)
				.setEntityClass(Member.class.toString())
				.setEntityUuid(member.getUuid())
				.setOperateDetail(OperateType.CREATE_MEMBER_BASIC.getLabel())
				.setOperateType(OperateType.CREATE_MEMBER_BASIC)
				.setOperator(SecurityUtils.username())
				.setOperatorId(SecurityUtils.currentUserGuid())
				.setEntityName(member.getChName());
	}

	private Log createMemberTurnoverLog(Turnover turnover, Basic basic) {
		return new Log()
				.setEntity(turnover)
				.setEntityClass(Turnover.class.toString())
				.setEntityUuid(turnover.getUuid())
				.setOperateDetail(OperateType.CREATE_MEMBER_TURNOVER.getLabel())
				.setOperateType(OperateType.CREATE_MEMBER_TURNOVER)
				.setOperator(SecurityUtils.username())
				.setOperatorId(SecurityUtils.currentUserGuid())
				.setEntityName(basic.getChName());
	}

	private Log updateMemberTurnoverLog(Turnover turnover, Basic basic) {
		return new Log().setEntity(turnover)
				.setEntityClass(Turnover.class.toString())
				.setEntityUuid(turnover.getUuid())
				.setOperateDetail(OperateType.EDIT_MEMBER_TURNOVER.getLabel())
				.setOperateType(OperateType.EDIT_MEMBER_TURNOVER)
				.setOperator(SecurityUtils.username())
				.setOperatorId(SecurityUtils.currentUserGuid())
				.setEntityName(basic.getChName());
	}

}

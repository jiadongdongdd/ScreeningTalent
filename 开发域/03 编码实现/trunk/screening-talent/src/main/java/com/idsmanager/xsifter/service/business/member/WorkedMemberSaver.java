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
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.business.admin.CreditStreamCreater;
import com.idsmanager.xsifter.service.dto.menber.WorkedFormDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2016/1/30
 * 
 * @author Shengzhao Li
 */
public class WorkedMemberSaver {

	private static final Logger LOG = LoggerFactory
			.getLogger(WorkedMemberSaver.class);

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);
	private transient UserRepository userRepository = BeanProvider
			.getBean(UserRepository.class);
	private transient LogRepository logRepository = BeanProvider
			.getBean(LogRepository.class);

	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private WorkedFormDto formDto;

	public WorkedMemberSaver(WorkedFormDto formDto) {
		this.formDto = formDto;
	}

	public String save() {

		Worked worked = memberRepository.findWorkedByUuid(formDto
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

		if (worked == null) {
			createMember(basic, education, member);
			worked = createWorked(basic);
		} else {
			createMember(basic, education, member);
			worked = updateWorked(worked, basic);
		}

		saveEntry(entry, worked, recruitment);

		//createMember(basic, education, member);

		return worked.getMemberUuid();
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

	private void createMember(Basic basic, Education education, Member member) {

		if (member == null) {
			member = new Member(basic, education);
			this.memberRepository.saveMember(member);
			updateLevel();
			LOG.debug("{}|Create  Member: {}", SecurityUtils.username(), member);
			this.logRepository.saveLog(createMemberLog(member));

			creditHandle(member);
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

	private void saveEntry(Entry entry, Worked worked, Recruitment recruitment) {

		if (entry == null) {
			entry = new Entry(worked);
		} else {
			entry.setWorked(worked);
		}

		if (recruitment != null) {
			entry.setEntryDate(recruitment.getEntryDate());
		}

		this.memberRepository.saveEntry(entry);

	}

	private Worked updateWorked(Worked worked, Basic basic) {
		worked = formDto.updateFields(worked);
		worked.setChNameBasic(basic.getChName())
				.setIdNumberBasic(basic.getIdNumber())
				.setMobileBasic(basic.getMobile())
				.setEmailBasic(basic.getEmail());
		// memberRepository.updateWorked(worked);
		memberRepository.saveWorked(worked);
		LOG.debug("{}|Update Worked: {}", SecurityUtils.username(), worked);
		this.logRepository.saveLog(updateMemberWorkedLog(worked, basic));
		return worked;
	}

	private Worked createWorked(Basic basic) {

		Worked worked = formDto.updateFields(new Worked());
		worked.setMemberUuid(formDto.getMemberUuid()).setCompanyGuid(
				SecurityUtils.currentUserGuid());

		worked.setChNameBasic(basic.getChName())
				.setIdNumberBasic(basic.getIdNumber())
				.setMobileBasic(basic.getMobile())
				.setEmailBasic(basic.getEmail())
				.setCompanyName(basic.getCompanyName());

		memberRepository.saveWorked(worked);
		LOG.debug("{}|Save Worked: {}", SecurityUtils.username(), worked);
		this.logRepository.saveLog(createMemberWorkedLog(worked, basic));
		return worked;
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

	private Log createMemberWorkedLog(Worked worked, Basic basic) {
		return new Log().setEntity(worked)
				.setEntityClass(Worked.class.toString())
				.setEntityUuid(worked.getUuid())
				.setOperateDetail(OperateType.CREATE_MEMBER_WORKED.getLabel())
				.setOperateType(OperateType.CREATE_MEMBER_WORKED)
				.setOperator(SecurityUtils.username())
				.setOperatorId(SecurityUtils.currentUserGuid())
				.setEntityName(basic.getChName());
	}

	private Log updateMemberWorkedLog(Worked worked, Basic basic) {
		return new Log().setEntity(worked)
				.setEntityClass(Worked.class.toString())
				.setEntityUuid(worked.getUuid())
				.setOperateDetail(OperateType.EDIT_MEMBER_WORKED.getLabel())
				.setOperateType(OperateType.EDIT_MEMBER_WORKED)
				.setOperator(SecurityUtils.username())
				.setOperatorId(SecurityUtils.currentUserGuid())
				.setEntityName(basic.getChName());
	}
}

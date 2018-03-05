package com.idsmanager.xsifter.service.business.member;

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
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.service.business.admin.CreditStreamCreater;
import com.idsmanager.xsifter.service.dto.menber.RecruitmentFormDto;

public class IntentionMemberSaver {

	private static final Logger LOG = LoggerFactory
			.getLogger(IntentionMemberSaver.class);

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);
	private transient UserRepository userRepository = BeanProvider
			.getBean(UserRepository.class);
	private transient LogRepository logRepository = BeanProvider
			.getBean(LogRepository.class);
	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);
	private RecruitmentFormDto formDto;

	public IntentionMemberSaver(RecruitmentFormDto formDto) {
		super();
		this.formDto = formDto;
	}

	public String save() {

		Recruitment recruitment = memberRepository
				.findRecruitmentByUuid(formDto.getMemberUuid());
		Basic basic = this.memberRepository.findBasicByUuid(formDto
				.getMemberUuid());
		Education education = this.memberRepository.findEducationByUuid(formDto
				.getMemberUuid());
		Member member = this.memberRepository.findMemberByUuid(formDto
				.getMemberUuid());
		Entry entry = this.memberRepository.findEntryByUuid(formDto
				.getMemberUuid());

		if (recruitment == null) {
			recruitment = createRecruitment(basic);
			// 创建职工
			createOrUpdateMember(basic, education, recruitment, member);
			this.logRepository.saveLog(createMemberRecruitmentLog(recruitment,
					basic));
			if (entry != null) {
				entry.setEntryDate(recruitment.getEntryDate());
				this.memberRepository.saveEntry(entry);
			}

		} else {
			recruitment = updateRecruitment(recruitment, basic);
			// 创建职工
			createOrUpdateMember(basic, education, recruitment, member);
			this.logRepository.saveLog(updateMemberRecruitmentLog(recruitment,
					basic));
			if (entry != null) {
				entry.setEntryDate(recruitment.getEntryDate());
				this.memberRepository.saveEntry(entry);
			}
		}

		// 创建职工
		// createOrUpdateMember(basic, education, recruitment, member);

		return recruitment.getMemberUuid();
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
			Recruitment recruitment, Member dataMember) {
		if (dataMember == null) {
			Member member = new Member(basic, education);

			this.memberRepository.saveMember(member);

			updateLevel();

			LOG.debug("{}|Create  Member: {}", SecurityUtils.username(), member);

			this.logRepository.saveLog(createMemberLog(member));
			// 积分调整
			creditHandle(member);
		} else {
			dataMember = dataMember.setWorkPosition(recruitment
					.getTelIntentionPosition());
			this.memberRepository.saveMember(dataMember);
			LOG.debug("{}|Update Member: {}", SecurityUtils.username(),
					dataMember);
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

		Company company = companyRepository.findByGuid(SecurityUtils
				.currentUserGuid());
		if (null != company) {

		}

	}

	private Recruitment updateRecruitment(Recruitment recruitment, Basic basic) {
		recruitment = formDto.updateFields(recruitment);

		recruitment.setChNameBasic(basic.getChName())
				.setIdNumberBasic(basic.getIdNumber())
				.setMobileBasic(basic.getMobile())
				.setEmailBasic(basic.getEmail());

		memberRepository.updateRecruitment(recruitment);
		LOG.debug("{}|Update Recruitment: {}, memberGuid: {}",
				SecurityUtils.username(), recruitment,
				recruitment.getMemberUuid());
		/*
		 * this.logRepository.saveLog(updateMemberRecruitmentLog(recruitment,
		 * basic));
		 */
		return recruitment;
	}

	private Recruitment createRecruitment(Basic basic) {
		Recruitment recruitment = formDto.updateFields(new Recruitment());
		recruitment.setMemberUuid(formDto.getMemberUuid()).setCompanyGuid(
				SecurityUtils.currentUserGuid());

		recruitment.setChNameBasic(basic.getChName())
				.setIdNumberBasic(basic.getIdNumber())
				.setMobileBasic(basic.getMobile())
				.setEmailBasic(basic.getEmail())
				.setCompanyName(basic.getCompanyName());

		memberRepository.saveRecruitment(recruitment);
		LOG.debug("{}|Save Recruitment: {}, memberGuid: {}",
				SecurityUtils.username(), recruitment,
				recruitment.getMemberUuid());
		/*
		 * this.logRepository.saveLog(createMemberRecruitmentLog(recruitment,
		 * basic));
		 */
		return recruitment;
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

	private Log createMemberRecruitmentLog(Recruitment recruitment, Basic basic) {
		return new Log()
				.setEntity(recruitment)
				.setEntityClass(Recruitment.class.toString())
				.setEntityUuid(recruitment.getUuid())
				.setOperateDetail(
						OperateType.CREATE_MEMBER_RECRUITMENT.getLabel())
				.setOperateType(OperateType.CREATE_MEMBER_RECRUITMENT)
				.setOperator(SecurityUtils.username())
				.setOperatorId(SecurityUtils.currentUserGuid())
				.setEntityName(basic.getChName());
	}

	private Log updateMemberRecruitmentLog(Recruitment recruitment, Basic basic) {
		return new Log()
				.setEntity(recruitment)
				.setEntityClass(Recruitment.class.toString())
				.setEntityUuid(recruitment.getUuid())
				.setOperateDetail(
						OperateType.EDIT_MEMBER_RECRUITMENT.getLabel())
				.setOperateType(OperateType.EDIT_MEMBER_RECRUITMENT)
				.setOperator(SecurityUtils.username())
				.setOperatorId(SecurityUtils.currentUserGuid())
				.setEntityName(basic.getChName());
	}

}

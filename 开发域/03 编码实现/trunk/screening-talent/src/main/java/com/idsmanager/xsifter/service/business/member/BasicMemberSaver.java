package com.idsmanager.xsifter.service.business.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
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
import com.idsmanager.xsifter.service.dto.menber.MemberFormOneDto;

/**
 * @author andy
 */
public class BasicMemberSaver {
	private static final Logger LOG = LoggerFactory
			.getLogger(BasicMemberSaver.class);
	private transient MemberRepository repository = BeanProvider
			.getBean(MemberRepository.class);
	private transient LogRepository logRepository = BeanProvider
			.getBean(LogRepository.class);

	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private transient UserRepository userRepository = BeanProvider
			.getBean(UserRepository.class);

	private MemberFormOneDto formDto;

	public BasicMemberSaver(MemberFormOneDto formDto) {
		this.formDto = formDto;
	}

	public String save() {

		Basic basic = this.repository.findBasicByUuid(formDto.getUuid());
		Education education = this.repository.findEducationByUuid(formDto
				.getUuid());
		Member member = this.repository.findMemberByUuid(formDto.getUuid());
		Recruitment recruitment = this.repository.findRecruitmentByUuid(formDto
				.getUuid());
		Turnover turnover = this.repository.findTurnoverByUuid(formDto
				.getUuid());
		Worked worked = this.repository.findWorkedByUuid(formDto.getUuid());
		Entry entry = this.repository.findEntryByUuid(formDto.getUuid());
		Company company = this.companyRepository.findByGuid(SecurityUtils
				.currentUserGuid());
		User user = this.userRepository.findByGuid(SecurityUtils
				.currentUserGuid());
		if (basic == null) {
			basic = createBasic(company, user);
			createOrUpdateEducation(basic, education);
		} else {
			basic = updateBasic(basic);
			createOrUpdateEducation(basic, education);
			updateOthers(basic, recruitment, turnover, worked, entry);
			if (member != null) {
				member = updateMember(basic, member);
				this.repository.saveMember(member);
				LOG.debug("{}|Update Member[Basic]: {}",
						SecurityUtils.username(), basic);
				Log log2 = updateMemberBasicLog(basic);
				this.logRepository.saveLog(log2);
			}

		}
		return basic.getUuid();

	}

	private void createOrUpdateEducation(Basic basic, Education education) {
		if (education == null) {
			education = new Education(basic);
			this.repository.saveEducation(education);
		} else {
			education.setGender(basic.getGender()).setAge(basic.getAge());
			this.repository.saveEducation(education);
		}

	}

	private void updateOthers(Basic basic, Recruitment recruitment,
			Turnover turnover, Worked worked, Entry entry) {

		if (recruitment != null) {
			recruitment.setChNameBasic(basic.getChName())
					.setMobileBasic(basic.getMobile())
					.setEmailBasic(basic.getEmail())
					.setIdNumberBasic(basic.getIdNumber());
			this.repository.saveRecruitment(recruitment);
		}
		if (turnover != null) {
			turnover.setChNameBasic(basic.getChName())
					.setMobileBasic(basic.getMobile())
					.setEmailBasic(basic.getEmail())
					.setIdNumberBasic(basic.getIdNumber());
			this.repository.saveTurnover(turnover);
		}
		if (worked != null) {
			worked.setChNameBasic(basic.getChName())
					.setMobileBasic(basic.getMobile())
					.setEmailBasic(basic.getEmail())
					.setIdNumberBasic(basic.getIdNumber());
			this.repository.saveWorked(worked);
		}
		if (entry != null) {
			entry.setChNameBasic(basic.getChName())
					.setMobileBasic(basic.getMobile())
					.setEmailBasic(basic.getEmail())
					.setIdNumberBasic(basic.getIdNumber());
			this.repository.saveEntry(entry);
		}

	}

	private Member updateMember(Basic basic, Member member) {
		return member.setChName(basic.getChName()).setEnName(basic.getEnName())
				.setEmail(basic.getEmail()).setGender(basic.getGender())
				.setIdNumber(basic.getIdNumber()).setMobile(basic.getMobile())
				.setPinyin(basic.getPinyin()).setAge(basic.getAge())
				.setBirthday(basic.getBirthday());
	}

	private Basic updateBasic(Basic basic) {
		basic = formDto.updateBasic(basic);
		this.repository.saveBasic(basic);
		LOG.debug("{}|Update Member Basic: {}", SecurityUtils.username(), basic);
		return basic;
	}

	private Basic createBasic(Company company, User user) {
		Basic basic;
		basic = formDto.createBasic();
		if (company != null) {
			basic.setCompanyName(company.getCompanyName());
		}
		if (user != null) {
			basic.setCompanyName(user.getUsername());
		}
		this.repository.saveBasic(basic);
		LOG.debug("{}|Create Member Basic: {}", SecurityUtils.username(), basic);
		return basic;
	}

	private Log updateMemberBasicLog(Basic basic) {
		return new Log().setEntity(basic)
				.setEntityClass(Basic.class.toString())
				.setEntityUuid(basic.getUuid())
				.setOperateDetail(OperateType.EDIT_MEMBER_BASIC.getLabel())
				.setOperateType(OperateType.EDIT_MEMBER_BASIC)
				.setOperator(SecurityUtils.username())
				.setOperatorId(SecurityUtils.currentUserGuid())
				.setEntityName(basic.getChName());
	}

}

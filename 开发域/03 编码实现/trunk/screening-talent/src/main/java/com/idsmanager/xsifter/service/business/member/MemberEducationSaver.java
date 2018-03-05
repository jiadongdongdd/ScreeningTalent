package com.idsmanager.xsifter.service.business.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.domain.log.Log;
import com.idsmanager.xsifter.domain.log.LogRepository;
import com.idsmanager.xsifter.domain.log.OperateType;
import com.idsmanager.xsifter.domain.member.Basic;
import com.idsmanager.xsifter.domain.member.Education;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.business.admin.CreditStreamCreater;
import com.idsmanager.xsifter.service.dto.menber.MemberFormTwoDto;

public class MemberEducationSaver {
	private static final Logger LOG = LoggerFactory
			.getLogger(MemberEducationSaver.class);

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private transient LogRepository logRepository = BeanProvider
			.getBean(LogRepository.class);

	private MemberFormTwoDto formDto;

	public MemberEducationSaver(MemberFormTwoDto formDto) {
		super();
		this.formDto = formDto;
	}

	public void save() {
		final String uuid = formDto.getUuid();
		Education education = this.memberRepository.findEducationByUuid(uuid);
		Member dataMember = this.memberRepository.findMemberByUuid(uuid);
		Basic basic = this.memberRepository.findBasicByUuid(uuid);

		if (education == null) {
			education = formDto.createEducation();
			this.memberRepository.saveEducation(education);
			basic.setAge(education.getAge()).setGender(education.getGender());
			this.memberRepository.saveBasic(basic);
			LOG.debug("{}|Create  Member[Education]: {}",
					SecurityUtils.username(), education);

		} else {
			education = formDto.updateEducation(education);
			this.memberRepository.saveEducation(education);
			basic.setAge(education.getAge()).setGender(education.getGender());
			this.memberRepository.saveBasic(basic);
			LOG.debug("{}|Update  Member[Education]: {}",
					SecurityUtils.username(), education);
		}

		// 更新member教育信息
		updateMember(dataMember, education);

	}

	private void updateMember(Member member, Education education) {
		if (member != null) {
			member = updateMemberEducation(member, education);
			this.memberRepository.saveMember(member);
			LOG.debug("{}|Update Member: {}", SecurityUtils.currentUserGuid(),
					member);
			// this.logRepository.saveLog(updateMemberLog(member));
		}

	}

	private Member updateMemberEducation(Member dataMember, Education education) {
		return dataMember.setAge(education.getAge())
				.setDegree(education.getDegree())
				.setMajor(education.getMajor())
				.setGender(education.getGender())
				.setNation(education.getNation())
				.setOrigin(education.getOrigin())
				.setPoliticalStatus(education.getPoliticalStatus())
				.setSchool(education.getSchool())
				.setNationOther(education.getNationOther())
				.setForeignOrigin(education.getForeignOrigin());
	}

	private Log updateMemberLog(Member member) {

		return new Log().setEntity(member)
				.setEntityClass(Member.class.toString())
				.setEntityUuid(member.getUuid())
				.setOperateDetail(OperateType.EDIT_MEMBER_EDU.getLabel())
				.setOperateType(OperateType.EDIT_MEMBER_EDU)
				.setOperator(SecurityUtils.username())
				.setOperatorId(SecurityUtils.currentUserGuid())
				.setEntityName(member.getChName());

	}

}

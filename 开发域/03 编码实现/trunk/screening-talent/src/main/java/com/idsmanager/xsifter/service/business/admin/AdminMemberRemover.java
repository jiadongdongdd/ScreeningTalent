package com.idsmanager.xsifter.service.business.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.member.Basic;
import com.idsmanager.xsifter.domain.member.Education;
import com.idsmanager.xsifter.domain.member.Entry;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

public class AdminMemberRemover {

	private static final Logger LOG = LoggerFactory
			.getLogger(AdminMemberRemover.class);

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private String uuid;

	public AdminMemberRemover(String uuid) {
		super();
		this.uuid = uuid;
	}

	public void remove() {

		Member member = this.memberRepository.findMemberByUuid(uuid);

		if (member != null) {
			// this.memberRepository.deleteMember(member);
			deleteAllEntities(member);
			LOG.debug("{}|Remove Member: {}", SecurityUtils.username(), member);
		}

	}

	private void deleteAllEntities(Member member) {
		this.memberRepository.deleteMember(member);
		Basic basic = this.memberRepository.findBasicByUuid(uuid);
		if (basic != null) {
			this.memberRepository.deleteBasic(basic);
		}
		Education education = this.memberRepository.findEducationByUuid(uuid);
		if (education != null) {
			this.memberRepository.deleteEducation(education);
		}
		Recruitment recruitment = this.memberRepository
				.findRecruitmentByUuid(uuid);
		if (recruitment != null) {
			this.memberRepository.deleteRecruitment(recruitment);
		}
		Turnover turnover = this.memberRepository.findTurnoverByUuid(uuid);
		if (turnover != null) {
			this.memberRepository.deleteTurnover(turnover);
		}
		Worked worked = this.memberRepository.findWorkedByUuid(uuid);
		if (worked != null) {
			this.memberRepository.deleteWorked(worked);
		}
		Entry entry = this.memberRepository.findEntryByUuid(uuid);
		if (entry != null) {
			this.memberRepository.deleteEntry(entry);
		}

	}

}

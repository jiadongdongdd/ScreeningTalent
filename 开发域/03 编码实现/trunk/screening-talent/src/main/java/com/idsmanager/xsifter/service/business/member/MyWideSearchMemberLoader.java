package com.idsmanager.xsifter.service.business.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.UUIDGenerator;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.business.admin.CreditStreamCreater;
import com.idsmanager.xsifter.service.dto.menber.MemberFormDto;

public class MyWideSearchMemberLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyWideSearchMemberLoader.class);

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private String uuid;

	public MyWideSearchMemberLoader(String uuid) {
		super();
		this.uuid = uuid;
	}

	public MemberFormDto load() {
		MemberFormDto formDto;
		Member member = this.memberRepository.findMemberByUuid(uuid);
		
		Recruitment recruitment = this.memberRepository.findRecruitmentByUuid(uuid);

		Turnover turnover = this.memberRepository.findTurnoverByUuid(uuid);
		
		Worked worked = this.memberRepository.findWorkedByUuid(uuid);

		if (member == null) {
			formDto = new MemberFormDto(UUIDGenerator.generate());
			return formDto;
		}

		formDto = new MemberFormDto(member);
		
		if (recruitment != null) {

			formDto.createMemberRecruitment(recruitment);

		}

		if (turnover != null) {
			formDto.createMemberTurnover(turnover);
		}
		
		if(worked != null) {
			formDto.createMemberWorked(worked);
		}

		creditHandle(member);

		return formDto;

	}

	public void creditHandle(Member member) {
		CreditStreamCreater streamCreater = new CreditStreamCreater(
				new CreditStreamHolder(SecurityUtils.currentUserGuid(),
						"全站查找职工_" + member.getChName(),
						CreditAction.SEARCH));
		try {
			streamCreater.create();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}

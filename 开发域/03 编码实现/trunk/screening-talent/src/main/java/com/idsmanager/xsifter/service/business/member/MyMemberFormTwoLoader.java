package com.idsmanager.xsifter.service.business.member;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.member.Basic;
import com.idsmanager.xsifter.domain.member.Education;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.service.dto.menber.MemberFormTwoDto;

public class MyMemberFormTwoLoader {

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private String uuid;

	public MyMemberFormTwoLoader(String uuid) {
		this.uuid = uuid;
	}

	public MemberFormTwoDto load() {
		Basic basic = this.memberRepository.findBasicByUuid(uuid);
		// Member member = this.memberRepository.findMemberByUuid(uuid);
		if (basic == null) {
			throw new IllegalStateException("Not Found member basic");
		}
		Education education = this.memberRepository.findEducationByUuid(uuid);

		if (education == null) {

			education = new Education(basic);
		}

		education = education.setAge(basic.getAge()).setGender(
				basic.getGender());

		// MemberFormTwoDto formDto = new MemberFormTwoDto(member);
		MemberFormTwoDto formDto = new MemberFormTwoDto(education);

		return formDto;
	}

}

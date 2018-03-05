package com.idsmanager.xsifter.service.business.member;

import java.util.List;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;
import com.idsmanager.xsifter.service.dto.menber.RecruitmentFormDto;

public class RecruitmentFormDtoLoader {

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private transient MemberPositionRepository memberPositionRepository = BeanProvider
			.getBean(MemberPositionRepository.class);

	private String uuid;

	public RecruitmentFormDtoLoader(String uuid) {
		super();
		this.uuid = uuid;
	}

	public RecruitmentFormDto load() {

		Recruitment recruitment = memberRepository.findRecruitmentByUuid(uuid);

		List<MemberPosition> pList = this.memberPositionRepository
				.findPositionList();

		if (recruitment == null) {
			RecruitmentFormDto formDto = new RecruitmentFormDto(uuid);
			formDto.setPositionList(pList);
			return formDto;
		}

		RecruitmentFormDto formDto = new RecruitmentFormDto(recruitment);

		formDto.setPositionList(pList);

		return formDto;
	}

}

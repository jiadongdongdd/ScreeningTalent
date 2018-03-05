package com.idsmanager.xsifter.service.business.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.member.Entry;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.service.dto.menber.BasicFormDto;
import com.idsmanager.xsifter.service.dto.menber.DetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.EntryFormDto;
import com.idsmanager.xsifter.service.dto.menber.RecruitmentFormDto;

public class PartMemberInfoLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(PartMemberInfoLoader.class);

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private String partName;

	private String uuid;

	public PartMemberInfoLoader(String partName, String uuid) {
		super();
		this.partName = partName;
		this.uuid = uuid;
	}

	public DetailFormDto load() {

		Member member = this.memberRepository.findMemberByUuid(uuid);

		Recruitment recruitment = this.memberRepository
				.findRecruitmentByUuid(uuid);
		Turnover turnover = this.memberRepository.findTurnoverByUuid(uuid);
		Worked worked = this.memberRepository.findWorkedByUuid(uuid);
		Entry entry = this.memberRepository.findEntryByUuid(uuid);

		DetailFormDto formDto = new DetailFormDto();

		if (member == null) {
			return formDto;
		}

		BasicFormDto basicFormDto = new BasicFormDto(member);

		formDto.setBasicFormDto(basicFormDto);

		if ("tel".equals(partName) || "interview".equals(partName)) {
			formDto.setPartName(partName);
			if (recruitment != null) {
				RecruitmentFormDto recruitmentFormDto = new RecruitmentFormDto(
						recruitment);
				formDto.setRecruitmentFormDto(recruitmentFormDto);
				return formDto;

			} else {
				formDto.setRecruitmentFormDto(new RecruitmentFormDto());
				return formDto;
			}
		}

		if ("entry".equals(partName)) {
			formDto.setPartName(partName);
			if (entry != null) {
				EntryFormDto entryFormDto = new EntryFormDto(entry);
				formDto.setEntryFormDto(entryFormDto);
				return formDto;
			} else {
				formDto.setEntryFormDto(new EntryFormDto());
				return formDto;
			}

		}

		return new DetailFormDto();

	}
}

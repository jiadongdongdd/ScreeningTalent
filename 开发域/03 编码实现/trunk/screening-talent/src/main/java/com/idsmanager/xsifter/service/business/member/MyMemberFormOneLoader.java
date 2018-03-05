package com.idsmanager.xsifter.service.business.member;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.service.dto.menber.MemberFormOneDto;

public class MyMemberFormOneLoader {

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private String uuid;

	public MyMemberFormOneLoader(String uuid) {
		super();
		this.uuid = uuid;
	}
	
	public MemberFormOneDto load() {
		Member member = this.memberRepository.findMemberByUuid(uuid);
		if(member == null) {
			throw new IllegalStateException("Not Found member");
		}
		
		MemberFormOneDto formDto = new MemberFormOneDto(member);
		
		return formDto;
	}

}

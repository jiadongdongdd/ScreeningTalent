package com.idsmanager.xsifter.service.business.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.UUIDGenerator;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.dto.menber.MemberDetailFormDto;

public class MyMemberDetailLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyMemberDetailLoader.class);

	private transient MemberRepository repository = BeanProvider
			.getBean(MemberRepository.class);

	private transient UserRepository userRepository = BeanProvider
			.getBean(UserRepository.class);

	private String uuid;

	public MyMemberDetailLoader(String uuid) {
		super();
		this.uuid = uuid;
	}

	public MemberDetailFormDto load() {
		MemberDetailFormDto detailFormDto;
		Member member = this.repository.findMemberByUUIDAndCompanyGuid(uuid,
				SecurityUtils.currentUserGuid());

		Recruitment recruitment = this.repository.findRecruitmentByUuid(uuid);

		Turnover turnover = this.repository.findTurnoverByUuid(uuid);

		Worked worked = this.repository.findWorkedByUuid(uuid);

		User user = userRepository.findByGuid(SecurityUtils.currentUserGuid());

		if (user != null && user.getPrivileges().contains(Privilege.ADMIN)) {
			member = this.repository.findMemberByUuid(uuid);
		}
		
		if(member != null) {
			detailFormDto = new MemberDetailFormDto(member);
			if(recruitment != null) {
				detailFormDto.createRecruitmentFormDto(recruitment);
			}
			
			if(turnover != null) {
				detailFormDto.createTurnoverFormDto(turnover);
			}
			
			if(worked != null) {
				detailFormDto.createWorkedFormDto(worked);
			}
			return detailFormDto;
		}
		
		detailFormDto = new MemberDetailFormDto(UUIDGenerator.generate());

		return detailFormDto;
	}

}

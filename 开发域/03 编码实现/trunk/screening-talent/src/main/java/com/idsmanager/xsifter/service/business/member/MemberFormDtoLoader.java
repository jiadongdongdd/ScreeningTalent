package com.idsmanager.xsifter.service.business.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.UUIDGenerator;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.dto.menber.MemberFormDto;

/**
 * 
 * @author andy
 * 
 */
public class MemberFormDtoLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(MemberFormDtoLoader.class);

	private transient MemberRepository repository = BeanProvider
			.getBean(MemberRepository.class);

	private transient UserRepository userRepository = BeanProvider
			.getBean(UserRepository.class);

	private String uuid;

	public MemberFormDtoLoader(String uuid) {
		this.uuid = uuid;
	}

	public MemberFormDto load() {
		MemberFormDto formDto;

		Member member = this.repository.findMemberByUUIDAndCompanyGuid(uuid,
				SecurityUtils.currentUserGuid());

		Recruitment recruitment = this.repository.findRecruitmentByUuid(uuid);

		Turnover turnover = this.repository.findTurnoverByUuid(uuid);
		
		Worked worked = this.repository.findWorkedByUuid(uuid);

		User user = userRepository.findByGuid(SecurityUtils.currentUserGuid());

		if (user != null && user.getPrivileges().contains(Privilege.ADMIN)) {
			member = this.repository.findMemberByUuid(uuid);
		}

		if (member != null) {
			formDto = new MemberFormDto(member);
			LOG.debug("{}|View member{}", SecurityUtils.username(), member);
			if (recruitment != null) {

				formDto.createMemberRecruitment(recruitment);

			}

			if (turnover != null) {
				formDto.createMemberTurnover(turnover);
			}
			
			if(worked != null) {
				formDto.createMemberWorked(worked);
			}
			
			return formDto;
		}

		formDto = new MemberFormDto(UUIDGenerator.generate());

		return formDto;
	}

}

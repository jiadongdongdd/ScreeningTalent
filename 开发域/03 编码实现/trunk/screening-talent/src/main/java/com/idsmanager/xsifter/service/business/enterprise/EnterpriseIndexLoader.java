package com.idsmanager.xsifter.service.business.enterprise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompany;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompanyRepository;
import com.idsmanager.xsifter.domain.log.LogRepository;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.enterprise.EnterpriseIndexDto;

public class EnterpriseIndexLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(EnterpriseIndexLoader.class);

	private transient InviteCompanyRepository inviteCompanyRepository = BeanProvider
			.getBean(InviteCompanyRepository.class);

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private transient LogRepository logRepository = BeanProvider
			.getBean(LogRepository.class);

	public EnterpriseIndexDto load() {
		String companyGuid = SecurityUtils.currentUserGuid();
		String username = SecurityUtils.username();

		InviteCompany company = this.inviteCompanyRepository
				.findByUsername(username);

		int inviteCompany = (int) this.inviteCompanyRepository
				.totalCompanyInviteList(username);// 邀请企业数量

		int inviteSuccessCompany = (int) this.inviteCompanyRepository
				.totalCompanyInviteSuccessList(username);// 邀请成功企业数量

		int totalMember = (int) this.memberRepository
				.companyTotalMembers(companyGuid);// 录入员工数
		
		int totalStationQueryAmount = (int)this.logRepository.findMyTotalStationQuery();

		return new EnterpriseIndexDto(inviteCompany, inviteSuccessCompany,
				totalMember, company == null ? null
						: company.getInviteUsername(),totalStationQueryAmount);
	}
}

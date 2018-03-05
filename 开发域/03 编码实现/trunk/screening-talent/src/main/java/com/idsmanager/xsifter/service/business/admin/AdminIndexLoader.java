package com.idsmanager.xsifter.service.business.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.login.LoginRepository;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.service.MemberService;
import com.idsmanager.xsifter.service.dto.admin.AdminIndexDto;

public class AdminIndexLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(AdminIndexLoader.class);

	private MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private LoginRepository loginRepository = BeanProvider
			.getBean(LoginRepository.class);

	public AdminIndexLoader() {
		super();
	}

	public AdminIndexDto load() {
		int totalActiveUsers = (int) this.loginRepository.totalActiveUsers();// 活跃用户
		int totalCompany = (int) this.companyRepository.totalCompany();// 企业注册总数
		int totalMember = (int) this.memberRepository.totalMembers();// 职工总数
		int totalStationQueryAmount = (int) this.loginRepository
				.findTotalStationQueryTimes();// 全站职工查询数

		return new AdminIndexDto(totalCompany, totalMember, totalActiveUsers,
				totalStationQueryAmount);
	}

}

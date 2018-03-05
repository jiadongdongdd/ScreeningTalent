package com.idsmanager.xsifter.service.business.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyDeleteState;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.company.DeleteCompany;
import com.idsmanager.xsifter.domain.company.DeleteCompanyRepository;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompany;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompanyRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.company.DeleteCompanyFormDto;

public class AdminPassedCompanyRemover {

	private static final Logger LOG = LoggerFactory
			.getLogger(AdminPassedCompanyRemover.class);

	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private transient DeleteCompanyRepository deleteCompanyRepository = BeanProvider
			.getBean(DeleteCompanyRepository.class);

	private transient InviteCompanyRepository inviteCompanyRepository = BeanProvider
			.getBean(InviteCompanyRepository.class);

	private String uuid;

	public AdminPassedCompanyRemover(String uuid) {
		super();
		this.uuid = uuid;
	}

	public void remove() {
		Company company = this.companyRepository.findByGuid(uuid);
		if (company != null) {
			// 物理删除企业
			 this.companyRepository.removeCompany(company);
			// 删除后添加删除记录
			addDeleteCompany(company);
			LOG.debug("{}|Remove a Passed company: {}",
					SecurityUtils.username(), company);
			/*
			 * InviteCompany inviteCompany = this.inviteCompanyRepository
			 * .findBycompanyName(company.getCompanyName()); if (inviteCompany
			 * != null) {
			 * this.inviteCompanyRepository.removeInviteCompany(inviteCompany);
			 * LOG.debug("{}|Remove inviteCompany: {}",
			 * SecurityUtils.username(), inviteCompany); }
			 */
		}
	}
	
	private void addDeleteCompany(Company com) {
		DeleteCompany company = new DeleteCompany().setGuid(com.getGuid())
				.setAuthorityPath(com.getAuthorityPath())
				.setBusinessPath(com.getBusinessPath()).setCity(com.getCity())
				.setCompanyEmail(com.getCompanyEmail())
				.setCompanyName(com.getCompanyName())
				.setCompanyNo(com.getCompanyNo())
				.setContacts(com.getContacts())
				.setContactsPhone(com.getContactsPhone())
				.setCreateTime(com.getCreateTime())
				.setCreateUserUuid(com.getCreateUserUuid())
				.setCreditNumber(com.getCreditNumber()).setDist(com.getDist())
				.setIndustry(com.getIndustry()).setPassword(com.getPassword())
				.setProv(com.getProv()).setUsername(com.getUsername())
				.setVerifyPass(com.getVerifyPass())
				.setVerifyReason(com.getVerifyReason())
				.setVerifyState(com.getVerifyState())
				.setCompanyDeleteState(CompanyDeleteState.DELETE);//默认删除
		DeleteCreateCompanyHandler handler = new DeleteCreateCompanyHandler(
				company);
		handler.handleEntity();
	}

}

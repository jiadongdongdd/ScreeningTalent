package com.idsmanager.xsifter.service.business.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.PasswordHandler;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.password.ModifyPasswordFormDto;

public class MyCompanyPasswordModifier {
	private static final Logger LOG = LoggerFactory
			.getLogger(MyCompanyPasswordModifier.class);

	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private ModifyPasswordFormDto formDto;

	public MyCompanyPasswordModifier(ModifyPasswordFormDto formDto) {
		super();
		this.formDto = formDto;
	}

	public boolean modify() {

		final String uuid = formDto.getUuid();

		Company company = companyRepository.findByGuid(uuid);

		if (company == null) {
			throw new IllegalStateException("The company is not exist");
		}

		String password = PasswordHandler.encryptPassword(
				formDto.getNewPassword(), company.getUsername());

		company.setPassword(password);

		this.companyRepository.saveCompany(company);

		LOG.debug("{}|Modify company password", SecurityUtils.username());

		return true;

	}
}

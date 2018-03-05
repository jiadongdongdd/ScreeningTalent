package com.idsmanager.xsifter.service.business.password;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.PasswordHandler;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.service.dto.password.PasswordResetFormDto;

public class PasswordReseter {

	private static final Logger LOG = LoggerFactory
			.getLogger(PasswordReseter.class);

	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private PasswordResetFormDto formDto;

	public PasswordReseter(PasswordResetFormDto formDto) {
		this.formDto = formDto;
	}

	public boolean reset() {
		final Company company = this.companyRepository.findByGuid(formDto
				.getUuid());

		if (company != null) {

			String password = PasswordHandler.encryptPassword(
					formDto.getPassword(), company.getUsername());

			company.setPassword(password);

			this.companyRepository.saveCompany(company);

			LOG.debug("{}|Reset password", company.getUsername());

			return true;
		}

		return false;

	}

}

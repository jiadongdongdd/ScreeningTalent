package com.idsmanager.xsifter.service.business.password;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.password.PasswordResetCode;
import com.idsmanager.xsifter.domain.password.PasswordResetCodeRepository;
import com.idsmanager.xsifter.infrastructure.XsifterHolder;
import com.idsmanager.xsifter.service.business.company.SenderInfoUtils;
import com.idsmanager.xsifter.service.business.company.SenderMailUtils;
import com.idsmanager.xsifter.service.dto.password.PasswordSendMailFormDto;

public class PasswordEmailSender {

	private static final Logger LOG = LoggerFactory
			.getLogger(PasswordEmailSender.class);

	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private transient PasswordResetCodeRepository passwordResetCodeRepository = BeanProvider
			.getBean(PasswordResetCodeRepository.class);

	private PasswordSendMailFormDto formDto;

	public PasswordEmailSender(PasswordSendMailFormDto formDto) {
		this.formDto = formDto;
	}

	public boolean send() {

		Company company = this.companyRepository.findByCompanyEmail(formDto
				.getEmail());

		if (company != null) {
			PasswordResetCode passwordResetCode = this.passwordResetCodeRepository
					.findCodeByUserUUID(company.getGuid());

			if (passwordResetCode != null) {
				this.passwordResetCodeRepository.deleteCode(passwordResetCode);
			}

			PasswordResetCode code = new PasswordResetCode()
					.setUserUUID(company.getGuid())
					.setUsername(company.getUsername()).setAction("密码重置");

			this.passwordResetCodeRepository.saveCode(code);

			SenderMailUtils.send(new SenderInfoUtils("筛子网密码重置", "筛子网密码重置链接："
					+ XsifterHolder.getHost() + "public/password/reset/"
					+ code.getUuid(), company.getCompanyName(), formDto
					.getEmail()));
			LOG.debug("{}|Send a eamil to {} for password reset",
					company.getUsername(), formDto.getEmail());
			return true;
		}

		return false;

	}

}

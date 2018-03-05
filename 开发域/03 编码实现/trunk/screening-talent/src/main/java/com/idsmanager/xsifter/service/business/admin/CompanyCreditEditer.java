package com.idsmanager.xsifter.service.business.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

public class CompanyCreditEditer {
	private static final Logger LOG = LoggerFactory
			.getLogger(CompanyCreditEditer.class);

	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private Company company;

	private Integer creditNumber;

	public CompanyCreditEditer(Company company, Integer creditNumber) {
		this.company = company;
		this.creditNumber = creditNumber;
	}

	public void edit(CreditStreamHolder holder) throws CreditNotEnoughException {

		LOG.debug("{} has make a {} credit for company {}",
				SecurityUtils.username(), creditNumber,
				company.getCompanyName());
		Integer result;
		if (holder.getAction().equals(CreditAction.DELETE)) {

			if (company.getCreditNumber() < Math.abs(creditNumber))
				throw new CreditNotEnoughException("积分不足");

			result = company.getCreditNumber() + creditNumber;

		} else {
			result = company.getCreditNumber() + creditNumber;
		}

		if (result < 0) {
			throw new CreditNotEnoughException("积分不足");
		} else {
			company.setCreditNumber(result);
			companyRepository.updateCompanyCredit(company);
			// companyRepository.saveCompany(company);
		}

	}
}

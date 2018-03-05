package com.idsmanager.xsifter.service.business.invite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompany;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompanyRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

public class InviteCompanyRemover {

	private static final Logger LOG = LoggerFactory
			.getLogger(InviteCompanyRemover.class);

	private transient InviteCompanyRepository inviteCompanyRepository = BeanProvider
			.getBean(InviteCompanyRepository.class);

	private String uuid;

	public InviteCompanyRemover(String uuid) {
		super();
		this.uuid = uuid;
	}

	public void remove() {

		InviteCompany inviteCompany = this.inviteCompanyRepository
				.findByGuid(uuid);
		if (inviteCompany != null) {
			this.inviteCompanyRepository.removeInviteCompany(inviteCompany);
			LOG.debug("{}|Remove inviteCompany: {}", SecurityUtils.username(),
					inviteCompany);
		}

	}

}

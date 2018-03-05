package com.idsmanager.xsifter.service.business.password;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.password.PasswordResetCode;
import com.idsmanager.xsifter.domain.password.PasswordResetCodeRepository;

public class MyPasswordResetCodeRemover {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyPasswordResetCodeRemover.class);

	private transient PasswordResetCodeRepository passwordResetCodeRepository = BeanProvider
			.getBean(PasswordResetCodeRepository.class);

	private PasswordResetCode passwordResetCode;

	public MyPasswordResetCodeRemover(PasswordResetCode passwordResetCode) {
		super();
		this.passwordResetCode = passwordResetCode;
	}
	
	public void remove() {
		this.passwordResetCodeRepository.deleteCode(passwordResetCode);
		LOG.debug("{}|Delete code",passwordResetCode.getUsername());
	}

}

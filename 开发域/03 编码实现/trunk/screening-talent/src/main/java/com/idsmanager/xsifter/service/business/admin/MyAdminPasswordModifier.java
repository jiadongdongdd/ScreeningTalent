package com.idsmanager.xsifter.service.business.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.PasswordHandler;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.dto.password.ModifyAdminPasswordFormDto;

public class MyAdminPasswordModifier {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyAdminPasswordModifier.class);

	private transient UserRepository userRepository = BeanProvider
			.getBean(UserRepository.class);

	private ModifyAdminPasswordFormDto formDto;

	public MyAdminPasswordModifier(ModifyAdminPasswordFormDto formDto) {
		super();
		this.formDto = formDto;
	}

	public void modify() {
		final String uuid = formDto.getUuid();

		User user = userRepository.findByGuid(uuid);

		if (user == null) {
			throw new IllegalStateException("The user is not exist");
		}

		String password = formDto.getNewPassword();

		user.setPassword(password);

		this.userRepository.saveUser(user);

		LOG.debug("{}|Modify user password", SecurityUtils.username());
	}
	

}

package com.idsmanager.xsifter.service.business.user;

import com.idsmanager.commons.utils.PasswordHandler;
import com.idsmanager.commons.web.context.BeanProvider;

import com.idsmanager.xsifter.domain.log.Log;
import com.idsmanager.xsifter.domain.log.LogRepository;
import com.idsmanager.xsifter.domain.log.OperateType;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.dto.user.UserFormDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2015/12/19
 * 
 * @author Shengzhao Li
 */
public class CreateUserHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(CreateUserHandler.class);

	private transient UserRepository userDao = BeanProvider
			.getBean(UserRepository.class);

	private transient LogRepository logRepository = BeanProvider
			.getBean(LogRepository.class);

	private UserFormDto formDto;

	public CreateUserHandler(UserFormDto formDto) {
		this.formDto = formDto;
	}

	public String handle() {

		final String username = formDto.getUsername();
		final String pass = PasswordHandler.encryptPassword(
				formDto.getPassword(), username);
		User user = new User(username, pass);
		user.getPrivileges().addAll(formDto.getPrivileges());

		userDao.saveUser(user);
		LOG.debug("{}|Create User: {}", SecurityUtils.username(), user);
		createUserLog(user);
		return user.guid();
	}

	private void createUserLog(User user) {
		if(user.getPrivileges().contains(Privilege.ENTERPRISEADMIN)) {
			Log log = new Log()
			.setEntity(user)
			.setEntityClass(user.getClass().toString())
			.setEntityName(user.getUsername())
			.setEntityUuid(user.getGuid())
			.setOperateDetail(OperateType.CREATE_ENTERPRISEADMIN.getLabel())
			.setOperateType(OperateType.CREATE_ENTERPRISEADMIN)
			.setOperator(SecurityUtils.username())
			.setOperatorId(SecurityUtils.currentUserGuid());
			this.logRepository.saveLog(log);
		}
	}
}

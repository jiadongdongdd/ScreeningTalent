package com.idsmanager.xsifter.web.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.idsmanager.xsifter.service.UserService;

/**
 * validate user 类名称 创建人 dushaofei 创建时间：2016-1-28 下午3:37:29 类描述：
 * 
 * @version
 */
public abstract class AbstractUserValidator implements Validator {

	protected static final int MIN_PASSWORD_LENGTH = 3;
	protected static final int MIN_USERNAME_LENGTH = 2;
	protected static final int MAX_USERNAME_LENGTH = 16;

	@Autowired
	protected UserService userService;

	protected void validatePassword(final String password,
			final String rePassword, Errors errors) {

		if (StringUtils.isEmpty(password)) {
			errors.rejectValue("password", null, "Password is required");
			return;
		}

		if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_USERNAME_LENGTH) {
			errors.rejectValue("password", null, "密码长度不合法");
			return;
		}

		if (!password.equals(rePassword)) {
			errors.rejectValue("rePassword", null,
					"两次密码必须一致");
		}

	}

	protected void validateUsername(final String username, Errors errors) {
		validateUsername(username, errors, true);
	}

	protected void validateUsername(final String username, Errors errors,
			boolean validateExisted) {

		if (StringUtils.isEmpty(username)) {
			errors.rejectValue("username", null, "Username is required");
			return;
		}

		if (username.trim().length() < MIN_USERNAME_LENGTH || username.trim().length() >MAX_USERNAME_LENGTH) {
			errors.rejectValue("username", null, "用户名长度不合法");
			return;
		}

		if (validateExisted) {
			boolean existed = existedUsername(username);
			if (existed) {
				errors.rejectValue("username", null, "Username(" + username
						+ ") already existed");
			}
		}
	}

	protected boolean existedUsername(String username) {
		return userService.isExistedUsername(username);
	}
}

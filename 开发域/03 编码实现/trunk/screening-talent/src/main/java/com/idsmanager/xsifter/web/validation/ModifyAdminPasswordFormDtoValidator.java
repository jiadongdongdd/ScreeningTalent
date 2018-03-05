package com.idsmanager.xsifter.web.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.idsmanager.commons.utils.PasswordHandler;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.service.UserService;
import com.idsmanager.xsifter.service.dto.password.ModifyAdminPasswordFormDto;

@Component
public class ModifyAdminPasswordFormDtoValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return ModifyAdminPasswordFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ModifyAdminPasswordFormDto formDto = (ModifyAdminPasswordFormDto) target;

		validateOriginalPassword(formDto, errors);

		validateNewPassword(formDto, errors);

		validateReNewPassword(formDto, errors);

	}

	public void validateOriginalPassword(ModifyAdminPasswordFormDto formDto,
			Errors errors) {

		final String uuid = SecurityUtils.currentUserGuid();
		final String orpassword = formDto.getOriginalPassword();

		if (StringUtils.isEmpty(orpassword)) {
			errors.rejectValue("originalPassword", null, "原密码不能为空");
			return;
		}

		User user = userService.getUserByGuid(uuid);
		if (user == null) {
			throw new IllegalStateException("The user is not exist");
		}

		String companyPassword = user.getPassword();

		String password = PasswordHandler.encryptPassword(orpassword,
				user.getUsername());

		if (!password.equals(companyPassword)) {
			errors.rejectValue("originalPassword", null, "原密码不正确");
		}

	}

	public void validateNewPassword(ModifyAdminPasswordFormDto formDto,
			Errors errors) {

		final String newPassword = formDto.getNewPassword();

		if (StringUtils.isEmpty(newPassword)) {
			errors.rejectValue("newPassword", null, "新密码不能为空");
			return;
		}

		if (newPassword.length() < 2 || newPassword.length() > 16) {
			errors.rejectValue("newPassword", null, "新密码长度2-16位");
		}

	}

	public void validateReNewPassword(ModifyAdminPasswordFormDto formDto,
			Errors errors) {
		final String newPassword = formDto.getNewPassword();
		final String reNewPassword = formDto.getReNewPassword();

		if (StringUtils.isEmpty(reNewPassword)) {
			errors.rejectValue("reNewPassword", null, "确认密码不能为空");
			return;
		}

		if (!reNewPassword.equals(newPassword)) {
			errors.rejectValue("reNewPassword", null, "两次密码不一致");
		}

	}

}

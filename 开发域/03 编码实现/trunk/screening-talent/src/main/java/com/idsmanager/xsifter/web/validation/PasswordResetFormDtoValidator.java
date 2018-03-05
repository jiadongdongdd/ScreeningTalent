package com.idsmanager.xsifter.web.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.idsmanager.xsifter.service.dto.password.PasswordResetFormDto;

@Component
public class PasswordResetFormDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PasswordResetFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		PasswordResetFormDto formDto = (PasswordResetFormDto) target;

		validatePassword(formDto, errors);

		validateRePassword(formDto, errors);

	}

	public void validatePassword(PasswordResetFormDto formDto, Errors errors) {

		final String password = formDto.getPassword();

		if (StringUtils.isEmpty(password)) {
			errors.rejectValue("password", null, "新密码不能为空");
			return;
		}

		if (password.length() < 2 || password.length() > 16) {
			errors.rejectValue("password", null, "新密码长度2-16位");
			return;
		}
		
		if(!isAvailablePassword(password)) {
			errors.rejectValue("password", null, "新密码中应至少包含一个字母");
		}

	}

	// 检查是否至少包含一个字母
	private boolean isAvailablePassword(String password) {
		String regex = "^.*[a-zA-Z]+.*$";
		return password.matches(regex);
	}

	public void validateRePassword(PasswordResetFormDto formDto, Errors errors) {
		final String password = formDto.getPassword();
		final String rePassword = formDto.getRepassword();

		if (StringUtils.isEmpty(rePassword)) {
			errors.rejectValue("repassword", null, "确认密码不能为空");
			return;
		}

		if (!rePassword.equals(password)) {
			errors.rejectValue("repassword", null, "两次密码不一致");
		}

	}

}

package com.idsmanager.xsifter.web.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.idsmanager.commons.utils.PasswordHandler;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.dto.password.ModifyPasswordFormDto;

@Component
public class ModifyPasswordFormDtoValidator implements Validator {

	@Autowired
	private CompanyService companyService;

	@Override
	public boolean supports(Class<?> clazz) {
		return ModifyPasswordFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ModifyPasswordFormDto formDto = (ModifyPasswordFormDto) target;

		validateOriginalPassword(formDto, errors);

		validateNewPassword(formDto, errors);

		validateReNewPassword(formDto, errors);

	}

	public void validateOriginalPassword(ModifyPasswordFormDto formDto,
			Errors errors) {

		final String uuid = SecurityUtils.currentUserGuid();
		final String orpassword = formDto.getOriginalPassword();

		if (StringUtils.isEmpty(orpassword)) {
			errors.rejectValue("originalPassword", null, "原密码不能为空");
			return;
		}

		Company company = companyService.findByGuid(uuid);
		if (company == null) {
			throw new IllegalStateException("The company is not exist");
		}

		String companyPassword = company.getPassword();

		String password = PasswordHandler.encryptPassword(orpassword,
				company.getUsername());

		if (!password.equals(companyPassword)) {
			errors.rejectValue("originalPassword", null, "原密码不正确");
		}

	}

	public void validateNewPassword(ModifyPasswordFormDto formDto, Errors errors) {

		final String newPassword = formDto.getNewPassword();

		if (StringUtils.isEmpty(newPassword)) {
			errors.rejectValue("newPassword", null, "新密码不能为空");
			return;
		}

		if (newPassword.length() < 2 || newPassword.length() > 16) {
			errors.rejectValue("newPassword", null, "新密码长度2-16位");
		}

	}

	public void validateReNewPassword(ModifyPasswordFormDto formDto,
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

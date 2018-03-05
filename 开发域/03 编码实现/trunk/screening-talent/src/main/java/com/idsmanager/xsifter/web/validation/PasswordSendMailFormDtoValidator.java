package com.idsmanager.xsifter.web.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.idsmanager.commons.utils.MatchUtils;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.dto.password.PasswordSendMailFormDto;

@Component
public class PasswordSendMailFormDtoValidator implements Validator {

	@Autowired
	private CompanyService companyService;

	@Override
	public boolean supports(Class<?> clazz) {
		return PasswordSendMailFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		PasswordSendMailFormDto formDto = (PasswordSendMailFormDto) target;

		validateEmail(formDto, errors);

	}

	public void validateEmail(PasswordSendMailFormDto formDto, Errors errors) {

		final String email = formDto.getEmail();

		if (StringUtils.isEmpty(email)) {
			errors.rejectValue("email", null, "邮箱不能为空");
			return;
		}

		if (!MatchUtils.isEmail(email)) {
			errors.rejectValue("email", null, "邮箱格式不正确");
		} else {
			final Company company = companyService.findByCompanyNEmail(email);
			if (company == null) {
				errors.rejectValue("email", null, "该邮箱不存在");
			}
		}

	}

}

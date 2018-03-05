package com.idsmanager.xsifter.web.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.idsmanager.xsifter.domain.company.VerifyReason;
import com.idsmanager.xsifter.service.dto.company.CompanyVerifyReasonFormDto;
@Component
public class CompanyVerifyReasonFormDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CompanyVerifyReasonFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CompanyVerifyReasonFormDto formDto = (CompanyVerifyReasonFormDto) target;
		 validateReason(formDto,errors);
	}

	private void validateReason(CompanyVerifyReasonFormDto formDto,
			Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "verifyReason", null, "请选择原因");
	}

}

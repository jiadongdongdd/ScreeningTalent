package com.idsmanager.xsifter.web.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;
import com.idsmanager.xsifter.service.MemberPositionService;
import com.idsmanager.xsifter.service.dto.position.PositionFormDto;

@Component
public class PositionFormDtoValidator implements Validator {

	@Autowired
	private MemberPositionService memberPositionService;

	@Override
	public boolean supports(Class<?> clazz) {
		return PositionFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PositionFormDto formDto = (PositionFormDto) target;
		validateName(formDto, errors);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "industry", null,
				"行业不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "level", null,
				"职位级别不能为空");
	}

	private void validateName(PositionFormDto formDto, Errors errors) {
		final String positionName = formDto.getPositionName();
		if (StringUtils.isEmpty(positionName)) {
			errors.rejectValue("positionName", null, "职位名称不能为空");
			return;
		}

		if (StringUtils.isNotEmpty(positionName)) {
			MemberPosition position = memberPositionService
					.findPositionByAllOption(formDto);
			if (null != position) {
				errors.rejectValue("positionName", null, "职位名称已经存在");
			}
		}

	}

}

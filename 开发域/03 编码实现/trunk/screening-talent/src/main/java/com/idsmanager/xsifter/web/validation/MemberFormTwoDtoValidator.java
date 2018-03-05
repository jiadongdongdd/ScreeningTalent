package com.idsmanager.xsifter.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.idsmanager.xsifter.domain.nation.Nation;
import com.idsmanager.xsifter.domain.origin.Origin;
import com.idsmanager.xsifter.service.dto.menber.MemberFormTwoDto;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MemberFormTwoDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberFormTwoDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uuid", null,
				"uuid is required");
		MemberFormTwoDto formDto = (MemberFormTwoDto) target;

		validateNationAndOrigin(formDto, errors);
		validateAge(formDto, errors);

	}

	private void validateAge(MemberFormTwoDto formDto, Errors errors) {
		final String age = formDto.getAge();
		if (StringUtils.isNotEmpty(age)) {
			try {
				Integer.valueOf(age);
			} catch (NumberFormatException e) {
				errors.rejectValue("age", null, "年龄格式不正确");
			}
		}
	}

	private void validateNationAndOrigin(MemberFormTwoDto formDto, Errors errors) {
		final Origin origin = formDto.getOrigin();
		if (origin != null && Origin.WAIJI.equals(origin)) {
			String foreignOrigin = formDto.getForeignOrigin();
			if (StringUtils.isEmpty(foreignOrigin)) {
				errors.rejectValue("foreignOrigin", null, "外籍备注不能为空");
			}
			if (StringUtils.isNotEmpty(foreignOrigin)) {
				String regex = "^[\u4e00-\u9fa5_a-zA-Z]+$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(foreignOrigin);
				if (!matcher.matches()) {
					errors.rejectValue("foreignOrigin", null, "外籍备注格式为中文、英文字母");
				}
			}
		}

		final Nation nation = formDto.getNation();
		if (nation != null && Nation.OTHER.equals(nation)) {
			String nationOther = formDto.getNationOther();
			if (StringUtils.isEmpty(nationOther)) {
				errors.rejectValue("nationOther", null, "其他民族备注不能为空");
			}

			if (StringUtils.isNotEmpty(nationOther)) {
				String regex = "^[\u4e00-\u9fa5_a-zA-Z]+$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(nationOther);
				if (!matcher.matches()) {
					errors.rejectValue("nationOther", null, "其他民族备注格式为中文、英文字母");
				}
			}
		}

	}

}

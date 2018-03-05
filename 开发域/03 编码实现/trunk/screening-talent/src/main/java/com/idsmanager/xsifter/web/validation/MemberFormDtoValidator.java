package com.idsmanager.xsifter.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.service.MemberService;
import com.idsmanager.xsifter.service.dto.menber.MemberFormDto;

@Component
public class MemberFormDtoValidator implements Validator {

	@Autowired
	private MemberService memberService;

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberFormDto formDto = (MemberFormDto) target;

		validateChName(formDto, errors);
		validateMobile(formDto, errors);
		validateIDNumber(formDto, errors);

	}

	public void validateChName(MemberFormDto formDto, Errors errors) {
		final String chName = formDto.getChName();
		if (StringUtils.isEmpty(chName)) {
			errors.rejectValue("chName", null, "中文名称不能为空");
			return;
		}
	}

	public void validateMobile(MemberFormDto formDto, Errors errors) {

		final String mobile = formDto.getMobile();
		if (StringUtils.isEmpty(mobile)) {
			errors.rejectValue("mobile", null, "手机不能为空");
			return;
		}

		boolean b = isMobile(mobile);

		if (!b) {
			errors.rejectValue("mobile", null, "手机(" + mobile + ")格式不正确");
			return;
		}

		final Member member = memberService.findMemberByMobile(mobile);
		if (member != null) {
			errors.rejectValue("mobile", null, "手机(" + mobile + ") 已经存在");
		}
	}

	public void validateIDNumber(MemberFormDto formDto, Errors errors) {
		final String idNumber = formDto.getIdNumber();
		if (idNumber != null) {
			boolean b = isIDNumber(idNumber);
			if (!b) {
				errors.rejectValue("idNumber", null, "身份证号码(" + idNumber
						+ ")格式不正确");
				return;
			}
			final Member member = memberService.findMemberByIDNumber(idNumber);
			if (member != null) {
				errors.rejectValue("idNumber", null, "身份证号(" + idNumber
						+ ") 已经存在");
			}
		}
	}

	public boolean isMobile(String mobile) {
		final String reg = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}|[0]{1}[0-9]{2,3}-[0-9]{7,8}$";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(mobile);
		return m.matches();

	}

	public boolean isIDNumber(String idNumber) {
		final String reg1 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
		final String reg2 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
		Pattern p1 = Pattern.compile(reg1);
		Pattern p2 = Pattern.compile(reg2);
		Matcher m1 = p1.matcher(idNumber);
		Matcher m2 = p2.matcher(idNumber);
		return m1.matches()|| m2.matches();

	}

}

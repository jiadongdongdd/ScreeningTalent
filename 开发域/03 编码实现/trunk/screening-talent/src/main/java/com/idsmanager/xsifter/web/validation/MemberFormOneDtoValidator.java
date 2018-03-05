package com.idsmanager.xsifter.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.MatchUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.MemberService;
import com.idsmanager.xsifter.service.dto.menber.MemberFormOneDto;

@Component
public class MemberFormOneDtoValidator implements Validator {

	@Autowired
	private MemberService memberService;

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberFormOneDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberFormOneDto formDto = (MemberFormOneDto) target;

		validateChName(formDto, errors);
		validateMobile(formDto, errors);
		validateIDNumber(formDto, errors);
		validateEmail(formDto, errors);

	}

	public void validateChName(MemberFormOneDto formDto, Errors errors) {
		final String chName = formDto.getChName();
		if (StringUtils.isEmpty(chName)) {
			errors.rejectValue("chName", null, "中文名称不能为空");
			return;
		}

		boolean b = isChName(chName);

		if (!b) {
			errors.rejectValue("chName", null, "中文名称(" + chName + ")格式不正确");
		}

	}

	public boolean isChName(String chName) {

		final String reg = "^[\u4E00-\u9FA5]{2,5}$";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(chName);
		return m.matches();
	}

	public void validateMobile(MemberFormOneDto formDto, Errors errors) {

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
		final Member member = memberService.findMemberByMobileAndCompanyUuid(
				mobile, SecurityUtils.currentUserGuid());

		if (member != null) {
			if (!member.getUuid().equals(formDto.getUuid())) {
				errors.rejectValue("mobile", null, "手机(" + mobile + ") 已经存在");
			}
		}
	}

	public void validateIDNumber(MemberFormOneDto formDto, Errors errors) {
		final String idNumber = formDto.getIdNumber();
		if (!StringUtils.isEmpty(idNumber)) {
			boolean b = isIDNumber(idNumber);
			if (!b) {
				errors.rejectValue("idNumber", null, "身份证号码(" + idNumber
						+ ")格式不正确");
				return;
			}

			boolean valid = isVaildIDNumber(idNumber);

			if (!valid) {
				errors.rejectValue("idNumber", null, "身份证号码(" + idNumber
						+ ")内容不正确");
				return;
			}

			final Member member = memberService
					.findMemberByIDNumberAndCompanyGuid(idNumber,
							SecurityUtils.currentUserGuid());
			if (member != null) {
				if (!member.getUuid().equals(formDto.getUuid())) {
					errors.rejectValue("idNumber", null, "身份证号(" + idNumber
							+ ") 已经存在");
				}
			}
		}
	}

	public boolean isVaildIDNumber(String idNumber) {

		String now = DateUtils.toDateText(DateUtils.now(), "yyyyMMdd");

		String nowYear = now.substring(0, 4);
		String nowMonth = now.substring(4, 6);
		String nowDay = now.substring(6, 8);

		if (idNumber.length() == 15) {
			return getIdNum15Result(nowYear, nowMonth, nowDay, idNumber);
		}

		if (idNumber.length() == 18) {
			return getIdNum18Result(nowYear, nowMonth, nowDay, idNumber);
		}

		return true;

	}

	public boolean getIdNum15Result(String nowYear, String nowMonth,
			String nowDay, String idNumber) {
		String year = idNumber.substring(6, 8);
		String month = idNumber.substring(8, 10);
		String day = idNumber.substring(10, 12);

		if (nowYear.compareTo("19" + year) == -1) {
			return false;
		}

		if (nowYear.compareTo("19" + year) == 0) {

			if (nowMonth.compareTo(month) == -1) {
				return false;
			}

			if (nowMonth.compareTo(month) == 0) {
				if (nowDay.compareTo(day) == -1) {
					return false;
				}

			}

		}
		return true;
	}

	public boolean getIdNum18Result(String nowYear, String nowMonth,
			String nowDay, String idNumber) {
		String year = idNumber.substring(6, 10);
		String month = idNumber.substring(10, 12);
		String day = idNumber.substring(12, 14);
		if (nowYear.compareTo(year) == -1) {
			return false;
		}

		if (nowYear.compareTo(year) == 0) {

			if (nowMonth.compareTo(month) == -1) {
				return false;
			}

			if (nowMonth.compareTo(month) == 0) {
				if (nowDay.compareTo(day) == -1) {
					return false;
				}

			}

		}
		return true;
	}

	public boolean isMobile(String mobile) {
		final String reg = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9]))\\d{8}|[0]{1}[0-9]{2,3}-[0-9]{7,8}$";
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
		return m1.matches() || m2.matches();

	}

	public void validateEmail(MemberFormOneDto formDto, Errors errors) {
		final String email = formDto.getEmail();
		if (StringUtils.isNotEmpty(email)) {
			if (!MatchUtils.isEmail(email)) {
				errors.rejectValue("email", null, "邮箱(" + email + ")格式不正确");
				return;
			}

			Member member = this.memberService.findMemberByEmailAndCompanGuid(
					email, SecurityUtils.currentUserGuid());

			if (member != null) {
				if (!member.getUuid().equals(formDto.getUuid())) {
					errors.rejectValue("email", null, "邮箱(" + email + ") 已经存在");
					return;
				}
			}
		}
	}

	// public boolean isEmail(String email) {
	// final String reg =
	// "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	// Pattern p = Pattern.compile(reg);
	// Matcher m = p.matcher(email);
	// return m.matches();
	//
	// }

}

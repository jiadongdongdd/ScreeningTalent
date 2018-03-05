package com.idsmanager.xsifter.web.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.business.admin.CreditStreamCreater;
import com.idsmanager.xsifter.service.business.admin.credit.CreditRuleNotFoundException;
import com.idsmanager.xsifter.service.dto.menber.MemberPaginated;

@Component
public class MemberPaginatedValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberPaginated.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		// MemberPaginated paginated = (MemberPaginated) target;

		// validateConditions(paginated, errors);

	}

	/*
	 * private void validateConditions(MemberPaginated paginated, Errors errors)
	 * { final String chName = paginated.getChName1(); if
	 * (StringUtils.isEmpty(chName)) { errors.rejectValue("errors", null,
	 * "姓名为必填项"); return; }
	 * 
	 * final String email = paginated.getEmail1(); final String idNumber =
	 * paginated.getIdNumber1(); final String mobile = paginated.getMobile1();
	 * 
	 * if (StringUtils.isEmpty(mobile) && StringUtils.isEmpty(idNumber) &&
	 * StringUtils.isEmpty(email)) { errors.rejectValue("errors", null,
	 * "身份证号、邮箱、手机三项中至少填写一项"); return; } }
	 */

}

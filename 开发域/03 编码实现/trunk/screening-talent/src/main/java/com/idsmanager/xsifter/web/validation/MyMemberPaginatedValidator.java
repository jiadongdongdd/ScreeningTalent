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
import com.idsmanager.xsifter.service.dto.menber.MyMemberPaginated;

@Component
public class MyMemberPaginatedValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MyMemberPaginated.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MyMemberPaginated paginated = (MyMemberPaginated) target;

		try {
			validateCredit(paginated, errors);
		} catch (CreditRuleNotFoundException e) {
			e.printStackTrace();
		}

		validateConditions(paginated, errors);
	}

	private void validateConditions(MyMemberPaginated paginated, Errors errors) {

		final boolean first = paginated.isFirst();

		if (!first) {
			final String chName = paginated.getChNameQuery();
			if (StringUtils.isEmpty(chName)) {
				errors.rejectValue("errors", "chName", "姓名为必填项");
				return;
			}

			final String email = paginated.getEmailQuery();
			final String idNumber = paginated.getIdNumberQuery();
			final String mobile = paginated.getMobileQuery();

			if (StringUtils.isEmpty(mobile) && StringUtils.isEmpty(idNumber)
					&& StringUtils.isEmpty(email)) {
				errors.rejectValue("errors", "option", "身份证号、邮箱、手机三项中至少填写一项");
				return;
			}
		}

	}

	public void validateCredit(MyMemberPaginated paginated, Errors errors)
			throws CreditRuleNotFoundException {

		CreditStreamCreater streamCreater = new CreditStreamCreater(
				new CreditStreamHolder(SecurityUtils.currentUserGuid(),
						"search member ", CreditAction.SEARCH));

		boolean b = streamCreater.checkCreditEnough();

		if (!b) {
			paginated.setErrors("积分不足");
			errors.rejectValue("errors", null, "积分不足");
			return;
		}

	}

}

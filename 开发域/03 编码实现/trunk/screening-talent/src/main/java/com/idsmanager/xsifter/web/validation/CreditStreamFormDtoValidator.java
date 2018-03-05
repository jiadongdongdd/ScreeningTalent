package com.idsmanager.xsifter.web.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.idsmanager.commons.utils.MatchUtils;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.dto.admin.CreditStreamFormDto;

@Component
public class CreditStreamFormDtoValidator implements Validator {
	@Autowired
	private CompanyService companyService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return CreditStreamFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditDo", null, "积分是必填的");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "remarks", null, "请填写备注");
		CreditStreamFormDto formDto = (CreditStreamFormDto)target;
		
		//validateCreditDo(formDto, errors);
	}
	
	public void validateCreditDo(CreditStreamFormDto formDto,Errors errors){
		String creditDo = formDto.getCreditDo();
		if(!MatchUtils.isNotZERO(creditDo)){
			errors.rejectValue("creditDo", null, "请输入一个有效的积分值");
			return;
			}
		Company company = companyService.findByGuid(formDto.getCompanyUuid());
		Integer formNum = Integer.valueOf(creditDo);
		Integer dataNum = company.getCreditNumber();
		if((formNum+dataNum)<0){
			errors.rejectValue("creditDo", null, "企业积分不足，请检查");
			return;
		}
	}

}

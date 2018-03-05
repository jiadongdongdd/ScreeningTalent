package com.idsmanager.xsifter.web.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.idsmanager.commons.utils.MatchUtils;
import com.idsmanager.xsifter.service.SystemAdminService;
import com.idsmanager.xsifter.service.dto.admin.CreditRuleFormDto;


@Component
public class CreditRuleFormDtoValidation implements Validator {

	@Autowired
	private SystemAdminService systemAdminService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return CreditRuleFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ruleName", null, "ruleName is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "action", null, "action is required");
		
		
		CreditRuleFormDto formDto = (CreditRuleFormDto)target;
		validateRoleName(formDto, errors);
		//validateCreditNumber(formDto, errors);
		validateAction(formDto, errors);
	}

	public void validateRoleName(CreditRuleFormDto formDto,Errors errors){
		String ruleName = formDto.getRuleName();
		if(null!=ruleName){
			CreditRuleFormDto dto = systemAdminService.getCreditRuleFormDtoByRuleName(ruleName);
			if(null!=dto&&!dto.getUuid().equals(formDto.getUuid())){
				if(!dto.getUuid().equals(formDto.getUuid())){
					errors.rejectValue("ruleName",null, "规则项 {"+ruleName+"}已经存在 ");
					return;
				}
			}
		}
	}
	
	public void validateCreditNumber(CreditRuleFormDto formDto,Errors errors){
		String creditNumber = formDto.getCreditNumber();
		if(StringUtils.isEmpty(creditNumber)){
			errors.rejectValue("creditNumber", null, "请输入有效的数字");
			return;
		}
		if(!MatchUtils.isNotZERO(creditNumber)){
			errors.rejectValue("creditNumber", null, "请输入有效的数字");
		}
	}
	
	public void validateAction(CreditRuleFormDto formDto,Errors errors){
		String action = formDto.getAction();
		if(null!=action){
			CreditRuleFormDto dataDto = systemAdminService.getCreditStreamFromDtoByAction(action);
			if(null!=dataDto&&!dataDto.getUuid().equals(formDto.getUuid())){
				errors.rejectValue("action", null, "该选项｛"+action+"｝已存在，请选择其他项");
			}
		}
	}
}

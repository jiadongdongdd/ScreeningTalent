package com.idsmanager.xsifter.web.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.idsmanager.commons.utils.MatchUtils;
import com.idsmanager.xsifter.service.SystemAdminService;
import com.idsmanager.xsifter.service.dto.admin.MemberLeveLFormDto;
import com.idsmanager.xsifter.service.dto.admin.MemberLevelDto;

@Component
public class MemberLevelFormDtoValidator implements Validator {

	@Autowired
	private SystemAdminService systemAdminService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MemberLeveLFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "level", null, "AppName is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rewardCoefficient", null, "rewardCoefficient is required");
		MemberLeveLFormDto formDto = (MemberLeveLFormDto)target;
		validateLevel(formDto, errors);
		validateMixNumberAndMaxNumber(formDto, errors);
		validateRewardCoefficient(formDto,errors);
	}
	
	public void validateLevel(MemberLeveLFormDto formDto,Errors errors){
		String level = formDto.getLevel();
		String uuid = formDto.getUuid();
		if(null!=level){
			MemberLevelDto dto =systemAdminService.getMemberLevelDtoByLevel(level);
			if(null!=dto){
				if(!dto.getUuid().equals(uuid)){
					errors.rejectValue("level", null, "会员级别 { "+level+" } 已经存在!!");
				}
			}
		}
	}
	
	
	public void validateMixNumberAndMaxNumber(MemberLeveLFormDto formDto,Errors errors){
		String strMix = formDto.getMixNumber();
		String uuid = formDto.getUuid();
		if(!MatchUtils.isPositiveNumber(strMix)){
			errors.rejectValue("mixNumber", null, "请输入有效数字");
			return;
		}
		Integer mix = Integer.valueOf(strMix);
		if(systemAdminService.checkRegionForNumber(mix,uuid)){
			errors.rejectValue("mixNumber", null, "该区间有重复！！");
		}
		String strMax = formDto.getMaxNumber();
		if(!MatchUtils.isPositiveNumber(strMax)){
			errors.rejectValue("maxNumber", null, "请输入有效数字");
			return;
		}
		Integer max = Integer.valueOf(strMax);
		if(systemAdminService.checkRegionForNumber(max,uuid)){
			errors.rejectValue("maxNumber", null, "该区间有重复！！");
		}
		if(max<mix){
			errors.rejectValue("maxNumber", null,"请输入一个较大的数字");
			return;
		}
		
		
	}
	
	public void validateRewardCoefficient(MemberLeveLFormDto formDto,Errors errors){
		String rewardCoefficient = formDto.getRewardCoefficient();
		if(null==rewardCoefficient){
			errors.rejectValue("rewardCoefficient", null, "请输入该系数");
		}
		if(!MatchUtils.isBigDecimal(rewardCoefficient)){
			errors.rejectValue("rewardCoefficient", null, "请输入有效的数字");
			return;
		}
		if(Float.parseFloat(rewardCoefficient)<=0){
			errors.rejectValue("rewardCoefficient", null, "请输入一个大于0的数字");
		}
	}

}

/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.idsmanager.xsifter.web.validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompany;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.InviteCompanyService;
import com.idsmanager.xsifter.service.dto.invite.InviteCompanyFormDto;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 
 * 类名称 创建人 dushaofei 创建时间：2016-1-28 下午6:38:03 类描述：
 * 
 * @version
 */
@Component
public class InviteCompanyFormDtoValidator implements Validator {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private InviteCompanyService inviteCompanyService;

	@Override
	public boolean supports(Class<?> clazz) {
		return InviteCompanyFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		InviteCompanyFormDto formDto = (InviteCompanyFormDto) target;

		validateName(formDto, errors);

		validateUsername(formDto, errors);

		validateEmail(formDto, errors);

	}

	private void validateEmail(InviteCompanyFormDto formDto, Errors errors) {
		final String email = formDto.getCompanyEmail();
		if (StringUtils.isEmpty(email)) {
			if (StringUtils.isEmpty(email)) {
				errors.rejectValue("companyEmail", null, "公司邮箱不能为空");
				return;
			}
		}

		final Company company = companyService.findByCompanyNEmail(email);
		if (company != null) {
			errors.rejectValue("companyEmail", null, "公司邮箱(" + email + ") 已经存在");
			return;
		}

		final InviteCompany inviteCompany = inviteCompanyService
				.findBycompanyEmail(email);
		if (inviteCompany != null) {
			errors.rejectValue("companyEmail", null, "公司邮箱(" + email + ") 已经存在");
			return;
		}
		
		final Boolean check = checkEmail(email);
		if(!check){
			errors.rejectValue("companyEmail", null, "公司邮箱(" + email + ") 不合法");
			return;
		}
	}

	private void validateName(InviteCompanyFormDto formDto, Errors errors) {
		final String companyName = formDto.getCompanyName();
		if (StringUtils.isEmpty(companyName)) {
			errors.rejectValue("companyName", null, "公司名称不能为空");
			return;
		}

		final Company company = companyService.findByCompanyName(companyName);
		if (company != null) {
			errors.rejectValue("companyName", null, "公司名称(" + companyName
					+ ") 已经存在");
			return;
		}

		final InviteCompany inviteCompany = inviteCompanyService
				.findBycompanyName(companyName);
		if (inviteCompany != null) {
			errors.rejectValue("companyName", null, "公司名称(" + companyName
					+ ") 已经存在");
		}
	}

	private void validateUsername(InviteCompanyFormDto formDto, Errors errors) {
		final String username = formDto.getUsername();
		if (StringUtils.isEmpty(username)) {
			errors.rejectValue("username", null, "用户名不能为空");
			return;
		}

		final Company company = companyService.findByUsername(username);
		if (company != null) {
			errors.rejectValue("username", null, "用户名(" + username + ") 已经存在");
			return;
		}

		final InviteCompany inviteCompany = inviteCompanyService
				.findByUsername(username);
		if (inviteCompany != null) {
			errors.rejectValue("username", null, "用户名(" + username + ") 已经存在");
		}
	}
	
	private Boolean checkEmail(String email){
		//电子邮件
		 Pattern p = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		 //Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");
		 //Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
//	    Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
	}
}

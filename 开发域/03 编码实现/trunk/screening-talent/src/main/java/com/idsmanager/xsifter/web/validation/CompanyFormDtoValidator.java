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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.idsmanager.commons.file.ImageUtils;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.dto.company.CompanyFormDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

/**
 * 2016/1/25
 * 
 * @author Shengzhao Li
 */
@Component
public class CompanyFormDtoValidator implements Validator {

	@Autowired
	private CompanyService companyService;

	@Override
	public boolean supports(Class<?> clazz) {
		return CompanyFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		CompanyFormDto formDto = (CompanyFormDto) target;

		/*
		 * errors.rejectValue("fall_password", null, formDto.getPassword());
		 * 
		 * errors.rejectValue("re_fall_password", null,
		 * formDto.getRePassword());
		 * 
		 * errors.rejectValue("fall_img1", null,
		 * formDto.getUrlauth().getOriginalFilename());
		 * 
		 * errors.rejectValue("fall_img2", null,
		 * formDto.getUrlbusi().getOriginalFilename());
		 */

		validateName(formDto, errors);

		validateUsername(formDto, errors);

		validateEmail(formDto, errors);

		validateBusiImg(formDto, errors);

		validateAuthImg(formDto, errors);

		validatePassword(formDto, errors);

		validatePhone(formDto, errors);
	}

	private void validateAuthImg(CompanyFormDto formDto, Errors errors) {
		final MultipartFile auth = formDto.getUrlauth();

		if (!ImageUtils.isImageFilename(auth.getOriginalFilename())) {
			errors.rejectValue("urlauthImg", null, "必须上传JPG图片.");
			return;
		}

		if (ImageUtils.isAvailableUploadImageSize(1024 * 1024 * 2)) {
			errors.rejectValue("urlauthImg", null, "图片必须小于2M.");
			return;
		}

	}

	private void validateBusiImg(CompanyFormDto formDto, Errors errors) {
		final MultipartFile busi = formDto.getUrlbusi();

		if (!ImageUtils.isImageFilename(busi.getOriginalFilename())) {
			errors.rejectValue("urlbusiImg", null, "必须上传JPG图片.");
			return;
		}

		if (ImageUtils.isAvailableUploadImageSize(1024 * 1024 * 2)) {
			errors.rejectValue("urlbusiImg", null, "图片必须小于2M.");
			return;
		}

	}

	private void validateEmail(CompanyFormDto formDto, Errors errors) {
		final String email = formDto.getCompanyEmail();
		if (StringUtils.isEmpty(email)) {
			if (StringUtils.isEmpty(email)) {
				errors.rejectValue("companyEmail", null, "公司邮箱不能为空");
				return;
			}
		}

		final Company company = companyService.findByCompanyNEmail(email);
		if (company != null
				&& (company.getVerifyPass() == null || company.getVerifyPass())) {// 并且是审核通过的
			errors.rejectValue("companyEmail", null, "公司邮箱(" + email + ") 已经存在");
		}

		final Boolean check = checkEmail(email);
		if (!check) {
			errors.rejectValue("companyEmail", null, "公司邮箱(" + email + ") 不合法");
			return;
		}

	}

	private void validateName(CompanyFormDto formDto, Errors errors) {
		final String companyName = formDto.getCompanyName();
		if (StringUtils.isEmpty(companyName)) {
			errors.rejectValue("companyName", null, "公司名称不能为空");
			return;
		}

		final Company company = companyService.findByCompanyName(companyName);
		if (company != null) {// 并且审核通过
			if (company.getVerifyState() && company.getVerifyPass() != null
					&& !company.getVerifyPass()) {
				errors.rejectValue("companyName", null, "公司名称(" + companyName
						+ ") 已经存在");
			}
		}
	}

	private void validateUsername(CompanyFormDto formDto, Errors errors) {
		final String username = formDto.getUsername();
		if (StringUtils.isEmpty(username)) {
			errors.rejectValue("username", null, "用户名不能为空");
			return;
		}
		if (username.length() < 2 || username.length() > 16) {
			errors.rejectValue("username", null, "用户名长度不合法");
			return;
		}

		Company company = companyService.findByUsername(username);
		if (null != company) {
			errors.rejectValue("username", null, "用户名已存在");
			return;
		}
	}

	private void validatePassword(CompanyFormDto formDto, Errors errors) {
		final String password = formDto.getPassword();
		if (StringUtils.isEmpty(password)) {
			errors.rejectValue("password", null, "密码不能为空");
			return;
		}

		final String repassword = formDto.getRePassword();
		if (StringUtils.isEmpty(repassword)) {
			errors.rejectValue("rePassword", null, "密码不能为空");
			return;
		}

		if (password.length() > 16) {
			errors.rejectValue("password", null, "密码长度过长");
			return;
		}

		if (repassword.length() > 16) {
			errors.rejectValue("rePassword", null, "密码长度过长");
			return;
		}

		if (!password.equalsIgnoreCase(repassword)) {
			errors.rejectValue("rePassword", null, "两次密码不一致");
			return;
		}

		/*if (password.matches("^\\d+$")) {
			errors.rejectValue("password", null, "输入至少一个字母");
		}*/
	}

	private void validatePhone(CompanyFormDto formDto, Errors errors) {

		final String phone = formDto.getContactsPhone();
		if (StringUtils.isEmpty(phone)) {
			errors.rejectValue("contactsPhone", null, "手机号不能为空");
			return;
		}

		/*
		 * if (isZuoJ(phone) || isPhones(phone)) { return; }
		 * 
		 * errors.rejectValue("contactsPhone", null, "请输入正确的手机号或座机号");
		 */

	}

	public static boolean isPhones(String phone) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

		Matcher m = p.matcher(phone);
		return m.matches();

	}

	public static boolean isZuoJ(String str) {
		String reg = "(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|"
				+ "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
		return Pattern.matches(reg, str);
	}

	public static Boolean checkEmail(String email) {
		// 电子邮件
		Pattern p = Pattern
				.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		// Pattern p =
		// Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
		Matcher m = p.matcher(email);
		return m.matches();
	}
}

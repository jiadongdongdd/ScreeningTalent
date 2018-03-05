package com.idsmanager.xsifter.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.password.PasswordResetCode;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.PasswordResetCodeService;
import com.idsmanager.xsifter.service.PasswordService;
import com.idsmanager.xsifter.service.dto.password.PasswordResetFormDto;
import com.idsmanager.xsifter.service.dto.password.PasswordSendMailFormDto;

@Controller
@RequestMapping("/public/password/")
public class PasswordController {

	@Autowired
	private PasswordService passwordService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private PasswordResetCodeService passwordResetCodeService;

	@RequestMapping(value = "send_eamil", method = RequestMethod.GET)
	public String sendForm(Model model) {
		PasswordSendMailFormDto formDto = new PasswordSendMailFormDto();
		model.addAttribute("formDto", formDto);
		return "password/sendEmail_form";
	}

	@RequestMapping(value = "send_eamil", method = RequestMethod.POST)
	public String sendMail(
			@ModelAttribute("formDto") @Valid PasswordSendMailFormDto formDto,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "password/sendEmail_form";
		}

		boolean b = passwordService.sendEmailForPasswordReset(formDto);
		if (b) {
			model.addAttribute("alert", "sendOk");
		} else {
			model.addAttribute("alert", "fail");
		}

		return "password/sendEmail_form";

	}

	@RequestMapping(value = "reset/{uuid}", method = RequestMethod.GET)
	public String resetForm(@PathVariable("uuid") String uuid, Model model) {

		PasswordResetCode code = passwordResetCodeService.findCodeByUUID(uuid);

		if (code == null) {
			return "password/error_link";
		}

		String userUUID = code.getUserUUID();

		Company company = companyService.findByGuid(userUUID);

		PasswordResetFormDto formDto = new PasswordResetFormDto();
		formDto.setUuid(userUUID);
		formDto.setUsername(company.getUsername());
		model.addAttribute("formDto", formDto);

		passwordResetCodeService.deleteMyCode(code);

		return "password/passwordResetForm";

	}

	@RequestMapping(value = "reset/{uuid}", method = RequestMethod.POST)
	public String reset(
			@ModelAttribute("formDto") @Valid PasswordResetFormDto formDto,
			BindingResult result, Model model) {

		model.addAttribute("formDto", formDto);
		if (result.hasErrors()) {
			return "password/passwordResetForm";

		}

		passwordService.resetPassword(formDto);
		model.addAttribute("alert", "resetOk");

		return "password/passwordResetForm";

	}
}

package com.idsmanager.xsifter.web.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.UserService;
import com.idsmanager.xsifter.service.dto.password.ModifyAdminPasswordFormDto;

@RequestMapping("/admin/password/")
@Controller
public class AdminPasswordController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public String showForm(Model model) {
		ModifyAdminPasswordFormDto formDto = new ModifyAdminPasswordFormDto();
		model.addAttribute("formDto", formDto);
		return "sysAdmin/modify_password";
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String submit(
			@ModelAttribute("formDto") @Valid ModifyAdminPasswordFormDto formDto,
			BindingResult result, Model model) {

		formDto.setUuid(SecurityUtils.currentUserGuid());

		if (result.hasErrors()) {
			return "sysAdmin/modify_password";
		}
		
		userService.modifyPassword(formDto);
		

		return "redirect:../../login";
	}

}

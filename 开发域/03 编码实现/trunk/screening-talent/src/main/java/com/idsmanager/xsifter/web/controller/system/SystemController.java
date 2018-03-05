package com.idsmanager.xsifter.web.controller.system;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.service.UserService;
import com.idsmanager.xsifter.service.dto.company.CompanyPaginated;
import com.idsmanager.xsifter.service.dto.user.MySettingDto;
import com.idsmanager.xsifter.service.dto.user.UserFormDto;
import com.idsmanager.xsifter.service.dto.user.UserPaginated;

/**
 * 系统设置 类名称 创建人 dushaofei 创建时间：2016-1-28 下午2:35:41 类描述：
 * 
 * @version
 */
@Controller
public class SystemController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/admin/add")
	public String addAdmin(Model model) {
		UserFormDto formDto = new UserFormDto();
		List<Privilege> privilegeList = formDto.getAdminPrivileges();
		model.addAttribute("formDto", formDto);
		model.addAttribute("privilegeList", privilegeList);
		return "system/admin_form";
	}

	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	public String submitAdmin(
			@ModelAttribute("formDto") @Valid UserFormDto formDto,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("password", formDto.getPassword());
			model.addAttribute("repassword", formDto.getRePassword());
			model.addAttribute("privilegeList", formDto.getAdminPrivileges());
			return "system/admin_form";
		}
		userService.createUser(formDto);
		return "redirect:/admin/list";
	}

	@RequestMapping(value = "/admin/list")
	public String companyTureList(UserPaginated paginated, Model model) {
		paginated.setNotUsername(SecurityUtils.username());
		paginated = userService.loadUserPaginated(paginated);
		model.addAttribute("paginated", paginated);
		return "system/admin_list";
	}

	@RequestMapping(value = "/admin/logOff/{username}")
	public String adminLogOff(@PathVariable String username, Model model) {
		if (null == username || "".equalsIgnoreCase(username))
			return "redirect:/admin/list";

		if (username.equalsIgnoreCase("admin"))
			return "redirect:/admin/list";

		Boolean result = userService.updateUserState(username);
		if (result)
			model.addAttribute("alert", "saveOk");

		else
			model.addAttribute("alert", "excep");

		return "redirect:/admin/list";
	}

	@RequestMapping(value = "/admin/logOpen/{username}")
	public String adminLogOpen(@PathVariable String username, Model model) {
		if (null == username || "".equalsIgnoreCase(username))
			return "redirect:/admin/list";

		userService.updateUserStateOpen(username);

		return "redirect:/admin/list";
	}

}

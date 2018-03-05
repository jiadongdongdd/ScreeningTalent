package com.idsmanager.xsifter.web.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idsmanager.commons.web.WebUtils;
import com.idsmanager.xsifter.domain.news.Platform;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.security.IdsUserDetails;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.security.SpringSecurityHolder;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.service.AdminIndexService;
import com.idsmanager.xsifter.service.EnterpriseIndexService;
import com.idsmanager.xsifter.service.LoginService;
import com.idsmanager.xsifter.service.MemberPositionService;
import com.idsmanager.xsifter.service.MerchantService;
import com.idsmanager.xsifter.service.NewsService;
import com.idsmanager.xsifter.service.SystemAdminService;
import com.idsmanager.xsifter.service.UserService;
import com.idsmanager.xsifter.service.dto.admin.AdminIndexDto;
import com.idsmanager.xsifter.service.dto.enterprise.EnterpriseIndexDto;
import com.idsmanager.xsifter.service.dto.news.NewsPaginated;
import com.idsmanager.xsifter.service.dto.user.UserFormDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 2016/1/19
 * 
 * @author Shengzhao Li
 */
@Controller
public class StartupController {

	private static final Logger LOG = LoggerFactory
			.getLogger(StartupController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private NewsService newsService;

	@Autowired
	private AdminIndexService adminIndexService;

	@Autowired
	private EnterpriseIndexService enterpriseIndexService;

	@Autowired
	private MemberPositionService memberPositionService;

	@RequestMapping(value = "/")
	public String index(Model model, NewsPaginated paginated,
			Authentication authentication, HttpServletRequest request,HttpServletResponse response) {
		if(authentication != null) {
			try {
				loginService.loginSuccessHandle(request, authentication,response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		paginated.setIspush(true);

		paginated.setPlatform(Platform.PLATNEWS);
		paginated = newsService.loadNewsPaginated(paginated);
		model.addAttribute("news", paginated);

		NewsPaginated notice = new NewsPaginated();
		notice.setIspush(true);
		notice.setPlatform(Platform.PLATNOTICE);
		notice = newsService.loadNewsPaginated(notice);
		model.addAttribute("notice", notice);

		return "home";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/admin/index")
	public String adminIndex(Model model) {
		AdminIndexDto indexDto = adminIndexService.loadAdminIndex();
		model.addAttribute("dto", indexDto);
		return "sysAdmin/index";
	}

	@RequestMapping("/enterpriseAdmin/index")
	public String enterpriseAdmin(Model model) {
		return "enterpriseAdmin/enterAdmin_index";
	}

	@RequestMapping("/enterprise/index")
	public String enterpriseIndex(Model model) {
		EnterpriseIndexDto indexDto = enterpriseIndexService
				.loadEnterpriseIndex();
		model.addAttribute("dto", indexDto);
		return "enterprise/e_index";
	}

	/*
	 * When the firstly initial system, create a super-admin account
	 */
	@RequestMapping(value = "/public/initial_user", method = RequestMethod.GET)
	@ResponseBody
	public String initialUser() {
		LOG.info("Call 'initialUser' from IP: {}", WebUtils.getIp());
		try {
			memberPositionService.initPosition();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userService.initialDefaultUser();
	}
	
	@RequestMapping(value = "/contact_us", method = RequestMethod.GET)
	public String contactUs() {
		return "contact";

	}
}

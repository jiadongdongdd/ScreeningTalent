package com.idsmanager.xsifter.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.security.IdsUserDetails;
import com.idsmanager.xsifter.domain.user.User;

@Controller
public class TargetUrlController {

	@RequestMapping("/targetUrl")
	public void targetUrl(Authentication authentication,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();

		IdsUserDetails userDetails = (IdsUserDetails) authentication
				.getPrincipal();
		final String contextPath = request.getContextPath();
		User user = userDetails.user();
		Company company = userDetails.company();
		try {
			if (user != null && user.isAdmin()) {
				session.setAttribute("isSwitch", "");
				session.setAttribute("companyName", "");
				response.sendRedirect(contextPath + "/admin/index");
			}

			if (user != null && user.isEnterpriseAdmin()) {
				session.setAttribute("isSwitch", "");
				session.setAttribute("companyName", "");
				response.sendRedirect(contextPath + "/enterpriseAdmin/index");
			}

			if (company != null) {
				session.setAttribute("isSwitch", true);
				session.setAttribute("companyName", company.getCompanyName());
				response.sendRedirect(contextPath + "/enterprise/index");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

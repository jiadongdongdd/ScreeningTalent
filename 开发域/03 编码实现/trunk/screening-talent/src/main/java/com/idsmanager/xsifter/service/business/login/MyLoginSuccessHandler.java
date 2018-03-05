package com.idsmanager.xsifter.service.business.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import com.idsmanager.xsifter.domain.security.IdsUserDetails;
import com.idsmanager.xsifter.domain.user.User;

public class MyLoginSuccessHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyLoginSuccessHandler.class);

	public void handle(HttpServletRequest request,Authentication authentication, HttpServletResponse response) throws IOException {
		
		IdsUserDetails userDetails = (IdsUserDetails) authentication
				.getPrincipal();
		User user = userDetails.user();
		final String contextPath = request.getContextPath();
		String redirectUri;
		if (user != null && user.isAdmin()) {
			// Admin登录成功后跳转的地址
			redirectUri = contextPath + "/admin/index";
		} else if (userDetails.company() != null) {
			// enterpriseAdmin 登录成功后跳转的地址
			redirectUri = contextPath + "/enterprise/index";
		} else if (user != null && user.isEnterpriseAdmin()) {
			// 普通Admin登录成功后跳转的地址
			redirectUri = contextPath + "/enterpriseAdmin/index";
		} else {
			redirectUri = contextPath + "/home";
		}
		response.sendRedirect(redirectUri);

	}

}

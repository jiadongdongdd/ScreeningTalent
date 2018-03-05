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
package com.idsmanager.xsifter.web.context;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.idsmanager.commons.web.WebUtils;
import com.idsmanager.xsifter.domain.security.IdsUserDetails;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.service.LoginService;
import com.idsmanager.xsifter.service.LogsService;

/**
 * 2016/1/25
 * 
 * @author Shengzhao Li
 */
public class XSISuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(XSISuccessHandler.class);

	@Autowired
	private LogsService logsService;

	@Autowired
	private LoginService loginService;

	public XSISuccessHandler() {
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		IdsUserDetails userDetails = (IdsUserDetails) authentication
				.getPrincipal();
		User user = userDetails.user();
		final String contextPath = request.getContextPath();
		String redirectUri;
		if (user != null && user.isAdmin()) {
			// Admin登录成功后跳转的地址
			redirectUri = contextPath + "/admin/index";
			//session.setAttribute("privileges", user.getPrivileges());
		} else if (userDetails.company() != null) {
			// enterpriseAdmin 登录成功后跳转的地址
			redirectUri = contextPath + "/enterprise/index";
			session.setAttribute("companyName", userDetails.company()
					.getCompanyName());
		} else if (user != null && user.isEnterpriseAdmin()) {
			// 普通Admin登录成功后跳转的地址
			redirectUri = contextPath + "/enterpriseAdmin/index";
			//session.setAttribute("privileges", user.getPrivileges());
		} else {
			throw new IllegalStateException("Unsupport User redirect, user = "
					+ user);
		}

		// 改变网站访问量

		// 添加登录记录&积分处理
		saveLoginRecord(userDetails.currentGuid());

		// save log
		saveLog(request, userDetails.getUsername());
		LOG.debug("Login Successful,Username: {}, redirect to: {}",
				userDetails.getUsername(), redirectUri);
		response.sendRedirect(redirectUri);
	}

	public void saveLog(HttpServletRequest request, String username) {
		String ip = WebUtils.getIp();

		logsService.logsHandle(username, ip);

	}

	public void saveLoginRecord(String userUUID) {

		loginService.saveLoginRecord(userUUID);

	}

}

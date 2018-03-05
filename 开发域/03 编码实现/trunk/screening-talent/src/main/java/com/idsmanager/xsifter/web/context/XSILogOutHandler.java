package com.idsmanager.xsifter.web.context;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.idsmanager.commons.web.WebUtils;
import com.idsmanager.xsifter.service.LogsService;

public class XSILogOutHandler implements LogoutSuccessHandler {
	@Autowired
	private LogsService logsService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String name = authentication.getName();
		final String contextPath = request.getContextPath();
		String ip = WebUtils.getIp();
		logsService.logsHandleUpdate(name, ip);
		response.sendRedirect(contextPath + "/");

	}
}

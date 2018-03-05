package com.idsmanager.xsifter.web.context;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class XsifterAuthencationFailureHandler implements
		AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		final String contextPath = request.getContextPath();
		if (exception.getClass().equals(
				InternalAuthenticationServiceException.class)) {
			response.sendRedirect(contextPath + "/login?authentication_error=3");
		} else {
			response.sendRedirect(contextPath + "/login?authentication_error=1");
		}
	}

}

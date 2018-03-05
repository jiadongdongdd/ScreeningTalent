package com.idsmanager.commons.web.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.idsmanager.commons.web.context.CommonUserService;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

public class PrivilegesFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletResponse res = (HttpServletResponse)response;
		 if(null ==CommonUserService.getUserService().getUserByUsername(SecurityUtils.username())){
			 res.sendRedirect("../login");
			 return;
		 }else{
			 chain.doFilter(request, response);
		 }
	}

	@Override
	public void destroy() {
		 
	}

}

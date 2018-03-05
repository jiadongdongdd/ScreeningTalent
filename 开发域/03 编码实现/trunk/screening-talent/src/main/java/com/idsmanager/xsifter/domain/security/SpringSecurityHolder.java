package com.idsmanager.xsifter.domain.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import antlr.collections.List;

import com.idsmanager.xsifter.domain.user.Privilege;

public class SpringSecurityHolder implements SecurityHolder {

	@Override
	public UserDetails userDetails() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			return (UserDetails) principal;
		}
		return null;
	}

	@Override
	public String username() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		if (principal instanceof String) {
			return (String) principal;
		}
		return null;
	}

	@Override
	public java.util.List<Privilege> privileges() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getAuthorities();
		if (principal instanceof java.util.List<?>) {
			return (java.util.List<Privilege>) principal;
		}
		return null;
	}

}

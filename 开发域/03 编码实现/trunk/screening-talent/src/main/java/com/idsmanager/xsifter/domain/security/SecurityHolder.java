package com.idsmanager.xsifter.domain.security;

import org.springframework.security.core.userdetails.UserDetails;

import com.idsmanager.xsifter.domain.user.Privilege;

public interface SecurityHolder {
	UserDetails userDetails();
	
	String username();
	
	java.util.List<Privilege> privileges();
}

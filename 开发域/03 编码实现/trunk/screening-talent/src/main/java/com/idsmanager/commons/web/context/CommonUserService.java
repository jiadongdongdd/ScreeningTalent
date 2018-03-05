package com.idsmanager.commons.web.context;

import com.idsmanager.xsifter.service.UserService;

public class CommonUserService {
	
	public static UserService userService;

	public static UserService getUserService() {
		return userService;
	}

	public static void setUserService(UserService userService) {
		CommonUserService.userService = userService;
	}
	
}

package com.idsmanager.xsifter.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import com.idsmanager.xsifter.domain.login.LoginRecord;

public interface LoginService {

	void saveLoginRecord(String userUUID);

	LoginRecord findLoginRecordByUserUUID(String uuid);

	List<LoginRecord> findActiveUser();

	long totalActiveUsers();

	void loginSuccessHandle(HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) throws IOException;

}

package com.idsmanager.xsifter.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.idsmanager.xsifter.domain.login.LoginRecord;
import com.idsmanager.xsifter.domain.login.LoginRepository;
import com.idsmanager.xsifter.service.LoginService;
import com.idsmanager.xsifter.service.business.login.MyLoginRecordSaver;
import com.idsmanager.xsifter.service.business.login.MyLoginSuccessHandler;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginRepository loginRepository;

	@Override
	public void saveLoginRecord(String userUUID) {

		MyLoginRecordSaver saver = new MyLoginRecordSaver(userUUID);
		saver.save();

	}

	@Override
	public LoginRecord findLoginRecordByUserUUID(String uuid) {
		return loginRepository.findLoginRecordByUserUUID(uuid);
	}

	@Override
	public List<LoginRecord> findActiveUser() {
		return loginRepository.findActiveUsers();
	}

	@Override
	public long totalActiveUsers() {
		return loginRepository.totalActiveUsers();
	}

	@Override
	public void loginSuccessHandle(HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) throws IOException {

		MyLoginSuccessHandler handler = new MyLoginSuccessHandler();
		handler.handle(request, authentication,response);

	}

}

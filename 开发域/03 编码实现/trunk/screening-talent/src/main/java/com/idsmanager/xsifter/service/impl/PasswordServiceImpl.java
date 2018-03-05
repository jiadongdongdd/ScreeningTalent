package com.idsmanager.xsifter.service.impl;

import org.springframework.stereotype.Service;

import com.idsmanager.xsifter.service.PasswordService;
import com.idsmanager.xsifter.service.business.password.PasswordEmailSender;
import com.idsmanager.xsifter.service.business.password.PasswordReseter;
import com.idsmanager.xsifter.service.dto.password.PasswordResetFormDto;
import com.idsmanager.xsifter.service.dto.password.PasswordSendMailFormDto;

@Service("passwordService")
public class PasswordServiceImpl implements PasswordService {

	@Override
	public boolean sendEmailForPasswordReset(PasswordSendMailFormDto formDto) {
		PasswordEmailSender sender = new PasswordEmailSender(formDto);
		return sender.send();
	}

	@Override
	public boolean resetPassword(PasswordResetFormDto formDto) {
		PasswordReseter reseter = new PasswordReseter(formDto);
		return reseter.reset();
	}
	
	

}

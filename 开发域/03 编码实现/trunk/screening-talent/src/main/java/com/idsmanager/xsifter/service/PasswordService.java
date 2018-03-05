package com.idsmanager.xsifter.service;

import com.idsmanager.xsifter.service.dto.password.PasswordResetFormDto;
import com.idsmanager.xsifter.service.dto.password.PasswordSendMailFormDto;

public interface PasswordService {

	boolean sendEmailForPasswordReset(PasswordSendMailFormDto formDto);

	boolean resetPassword(PasswordResetFormDto formDto);

}

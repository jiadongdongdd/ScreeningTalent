package com.idsmanager.xsifter.service;

import com.idsmanager.xsifter.domain.password.PasswordResetCode;

public interface PasswordResetCodeService {

	PasswordResetCode findCodeByUUID(String uuid);

	void deleteMyCode(PasswordResetCode code);

}

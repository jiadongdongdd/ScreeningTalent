package com.idsmanager.xsifter.domain.password;

public interface PasswordResetCodeRepository {

	void saveCode(PasswordResetCode code);

	PasswordResetCode findCodeByUUID(String uuid);

	void deleteCode(PasswordResetCode passwordResetCode);

	PasswordResetCode findCodeByUserUUID(String userUUID);

}

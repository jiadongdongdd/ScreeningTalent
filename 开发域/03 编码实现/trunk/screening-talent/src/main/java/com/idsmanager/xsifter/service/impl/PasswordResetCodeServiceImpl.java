package com.idsmanager.xsifter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idsmanager.xsifter.domain.password.PasswordResetCode;
import com.idsmanager.xsifter.domain.password.PasswordResetCodeRepository;
import com.idsmanager.xsifter.service.PasswordResetCodeService;
import com.idsmanager.xsifter.service.business.password.MyPasswordResetCodeRemover;

@Service("passwordResetCodeService")
public class PasswordResetCodeServiceImpl implements PasswordResetCodeService {
	@Autowired
	private PasswordResetCodeRepository passwordResetCodeRepository;

	@Override
	public PasswordResetCode findCodeByUUID(String uuid) {
		return passwordResetCodeRepository.findCodeByUUID(uuid);
	}

	@Override
	public void deleteMyCode(PasswordResetCode code) {
		MyPasswordResetCodeRemover remover = new MyPasswordResetCodeRemover(code);
		remover.remove();
	}
}

package com.idsmanager.xsifter.service.dto.password;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class PasswordSendMailFormDto extends AbstractDto {

	private static final long serialVersionUID = -7602529495400709159L;

	private String username;
	private String email;

	private boolean result;

	public PasswordSendMailFormDto() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}

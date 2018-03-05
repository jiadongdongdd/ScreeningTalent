package com.idsmanager.xsifter.service.dto.password;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class PasswordResetFormDto extends AbstractDto {

	private static final long serialVersionUID = -6092444826613461716L;

	private String uuid;
	private String username;
	private String password;
	private String repassword;

	public PasswordResetFormDto() {
		super();
	}

	public PasswordResetFormDto(String uuid, String username) {
		this.uuid = uuid;
		this.username = username;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

}

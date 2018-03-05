package com.idsmanager.xsifter.domain.password;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.xsifter.domain.AbstractDomain;

@Document(collection = "PasswordResetCode")
public class PasswordResetCode extends AbstractDomain {

	private static final long serialVersionUID = 7128207578407020498L;

	private String userUUID;
	private String username;
	private String action;

	public PasswordResetCode() {
		super();
	}

	public String getUserUUID() {
		return userUUID;
	}

	public PasswordResetCode setUserUUID(String userUUID) {
		this.userUUID = userUUID;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public PasswordResetCode setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getAction() {
		return action;
	}

	public PasswordResetCode setAction(String action) {
		this.action = action;
		return this;
	}

}

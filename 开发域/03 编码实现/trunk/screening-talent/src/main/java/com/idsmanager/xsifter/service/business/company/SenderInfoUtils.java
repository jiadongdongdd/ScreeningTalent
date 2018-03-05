package com.idsmanager.xsifter.service.business.company;

public class SenderInfoUtils {
	private String subject;
	
	private String context;
	
	private String username;
	
	private String email;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
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

	public SenderInfoUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SenderInfoUtils(String subject, String context, String username,
			String email) {
		super();
		this.subject = subject;
		this.context = context;
		this.username = username;
		this.email = email;
	}
	
	
}

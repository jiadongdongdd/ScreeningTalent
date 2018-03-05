package com.idsmanager.xsifter.service.dto.password;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class ModifyPasswordFormDto extends AbstractDto {

	private static final long serialVersionUID = -1375028405799461690L;

	private String uuid;
	private String originalPassword;
	private String newPassword;
	private String reNewPassword;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOriginalPassword() {
		return originalPassword;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReNewPassword() {
		return reNewPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}

}

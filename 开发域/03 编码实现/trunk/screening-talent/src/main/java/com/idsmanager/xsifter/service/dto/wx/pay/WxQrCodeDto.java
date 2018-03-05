package com.idsmanager.xsifter.service.dto.wx.pay;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class WxQrCodeDto extends AbstractDto {

	private static final long serialVersionUID = 8977741127852078824L;

	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}

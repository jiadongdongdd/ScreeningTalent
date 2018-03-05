package com.idsmanager.xsifter.service.dto.wx.pay;

import com.idsmanager.xsifter.domain.wx.pay.Merchant;
import com.idsmanager.xsifter.service.dto.AbstractDto;

public class MerchantFormDto extends AbstractDto {

	private static final long serialVersionUID = -5160000784071241459L;

	private String uuid;
	private String appId;// 公众账号ID
	private String mchId;// 商户号
	private String mchKey;// 商户API密匙
	private String certLocalPath;// 证书位置
	private String certPassword;// 证书密码（默认为商户号）

	private String ip;// 机器IP
	private String port;// 端口

	public MerchantFormDto() {
		super();
	}

	public MerchantFormDto(Merchant merchant) {
		this.uuid = merchant.getUuid();
		this.appId = merchant.getAppId();
		this.mchId = merchant.getMchId();
		this.mchKey = merchant.getMchKey();
		this.ip = merchant.getIp();
		this.port = merchant.getPort();
		this.certLocalPath = merchant.getCertLocalPath();
		this.certPassword = merchant.getCertPassword();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchKey() {
		return mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	public String getCertLocalPath() {
		return certLocalPath;
	}

	public void setCertLocalPath(String certLocalPath) {
		this.certLocalPath = certLocalPath;
	}

	public String getCertPassword() {
		return certPassword;
	}

	public void setCertPassword(String certPassword) {
		this.certPassword = certPassword;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Merchant create() {
		return new Merchant().setAppId(appId).setMchId(mchId).setMchKey(mchKey)
				.setIp(ip).setCertLocalPath(certLocalPath)
				.setCertPassword(certPassword).setPort(port);
	}

	public Merchant update(Merchant merchant) {
		return merchant.setAppId(appId).setMchId(mchId).setMchKey(mchKey)
				.setIp(ip).setPort(port);
	}

}

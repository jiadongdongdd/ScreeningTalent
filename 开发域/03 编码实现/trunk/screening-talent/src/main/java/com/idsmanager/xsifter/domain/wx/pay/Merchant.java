package com.idsmanager.xsifter.domain.wx.pay;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.xsifter.domain.AbstractDomain;

//商户配置
@Document(collection = "Merchant")
public class Merchant extends AbstractDomain {

	private static final long serialVersionUID = -7308038035751891273L;

	private String appId;// 公众账号ID
	private String mchId;// 商户号
	private String mchKey;// 商户API密匙
	private String certLocalPath;// 证书位置
	private String certPassword;// 证书密码（默认为商户号）

	private String ip;// 机器IP
	private String port;// 端口

	public Merchant() {
		super();
	}

	public String getAppId() {
		return appId;
	}

	public Merchant setAppId(String appId) {
		this.appId = appId;
		return this;
	}

	public String getMchId() {
		return mchId;
	}

	public Merchant setMchId(String mchId) {
		this.mchId = mchId;
		return this;
	}

	public String getMchKey() {
		return mchKey;
	}

	public Merchant setMchKey(String mchKey) {
		this.mchKey = mchKey;
		return this;
	}

	public String getCertLocalPath() {
		return certLocalPath;
	}

	public Merchant setCertLocalPath(String certLocalPath) {
		this.certLocalPath = certLocalPath;
		return this;
	}

	public String getCertPassword() {
		return certPassword;
	}

	public Merchant setCertPassword(String certPassword) {
		this.certPassword = certPassword;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public Merchant setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public String getPort() {
		return port;
	}

	public Merchant setPort(String port) {
		this.port = port;
		return this;
	}

	@Override
	public String toString() {
		return "Merchant [appId=" + appId + ", mchId=" + mchId + ", mchKey="
				+ mchKey + ", certLocalPath=" + certLocalPath
				+ ", certPassword=" + certPassword + ", ip=" + ip + ", port="
				+ port + "]";
	}

}

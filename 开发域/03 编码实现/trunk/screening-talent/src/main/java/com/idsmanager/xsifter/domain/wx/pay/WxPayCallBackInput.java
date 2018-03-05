package com.idsmanager.xsifter.domain.wx.pay;

import java.io.Serializable;

//微信支付回调输入参数
public class WxPayCallBackInput implements Serializable {

	private static final long serialVersionUID = -2629740955368078260L;

	private String appid;

	private String openid;

	private String mch_id;

	private String is_subscribe;

	private String nonce_str;

	private String product_id;

	private String sign;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "WxPayCallBackInput [appid=" + appid + ", openid=" + openid
				+ ", mch_id=" + mch_id + ", is_subscribe=" + is_subscribe
				+ ", nonce_str=" + nonce_str + ", product_id=" + product_id
				+ ", sign=" + sign + "]";
	}

}

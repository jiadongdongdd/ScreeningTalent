package com.idsmanager.xsifter.domain.wx.pay;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.idsmanager.commons.utils.UUIDGenerator;
import com.idsmanager.commons.utils.wx.pay.WxSignatureUtil;

public class WxPayQrCode implements Serializable {

	private static final long serialVersionUID = -4713358387352101469L;

	private String appid = "";
	private String mch_id = "";
	private String time_stamp = "";
	private String nonce_str = "";
	private String product_id = "";
	private String sign = "";

	@SuppressWarnings("unchecked")
	public WxPayQrCode(String product_id, Merchant merchant)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NoSuchAlgorithmException,
			UnsupportedEncodingException {
		this.product_id = product_id;
		setAppid(merchant.getAppId());
		setMch_id(merchant.getMchId());
		setTime_stamp(System.currentTimeMillis() / 1000 + "");
		setNonce_str(UUIDGenerator.generate());

		Map<String, String> map = BeanUtils.describe(this);
		map.remove("class");

		String sign = WxSignatureUtil.getSign(map, merchant);
		setSign(sign);

	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
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

}

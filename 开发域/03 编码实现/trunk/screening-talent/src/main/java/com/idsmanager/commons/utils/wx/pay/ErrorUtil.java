package com.idsmanager.commons.utils.wx.pay;

public class ErrorUtil {

	/**
	 * 签名验证失败
	 * 
	 * @return
	 */
	public static String validateSignFail() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<xml>")
				.append("<return_code><![CDATA[SUCCESS]]></return_code>")
				.append("<return_msg><![CDATA[签名失败]]></return_msg>")
				.append("<result_code><![CDATA[FAIL]]></result_code>")
				.append("<err_code_des><![CDATA[交易失败]]></err_code_des>")
				.append("</xml>");
		return stringBuffer.toString();
	}
	
	public static String getPrePayIdFail() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<xml>")
				.append("<return_code><![CDATA[SUCCESS]]></return_code>")
				.append("<return_msg><![CDATA[获取prepay_id失败]]></return_msg>")
				.append("<result_code><![CDATA[FAIL]]></result_code>")
				.append("<err_code_des><![CDATA[交易失败]]></err_code_des>")
				.append("</xml>");
		return stringBuffer.toString();
	}

}

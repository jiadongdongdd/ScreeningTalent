package com.idsmanager.commons.utils.message;

import com.idsmanager.commons.utils.PropertyUtils;

public class SMSUtils {

	public static void sendSMS(String mobileNumber) {

		String path = "/sendMsg.properties";
		String url = PropertyUtils.getMessageProp("url", path);
		String account = PropertyUtils.getMessageProp("account", path);
		String pswd = PropertyUtils.getMessageProp("pswd", path);
		boolean needstatus = Boolean.valueOf(PropertyUtils.getMessageProp(
				"needstatus", path));
		String product = PropertyUtils.getMessageProp("product", path);
		String extno = PropertyUtils.getMessageProp("extno", path);
		String otp = createRandomVcode();
		String content = "您好，您的验证码是" + otp;// 短信内容

		try {
			String result = HttpSender.batchSend(url, account, pswd, mobileNumber, content,
					needstatus, product, extno);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	/**
	 * 生成6位验证码
	 * 
	 * @return
	 */
	public static String createRandomVcode() {
		// 验证码
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + (int) (Math.random() * 9);
		}
		return vcode;
	}

}

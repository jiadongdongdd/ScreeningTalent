package com.idsmanager.commons.utils.wx.pay;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.idsmanager.xsifter.domain.wx.pay.Merchant;
import com.idsmanager.xsifter.domain.wx.pay.WxPayCallBackInput;

public class WxSignatureUtil {

	public static String getSign(Map<String, String> map, Merchant merchant)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		// map ordered by key
		Map<String, String> orderMap = WxMapUtil.orderMap(map);
		// url参数串联
		String result = WxMapUtil.mapJoin(orderMap, true, false);
		// url增加商户KEY参数
		result += "&key=" + merchant.getMchKey();
		System.out.println("扫码支付模式一签名URL=" + result);

		String sign = WxMD5Util.MD5Encode(result, "UTF-8").toUpperCase();

		System.out.println("扫码支付模式一签名sign=" + sign);

		return sign;

	}

	@SuppressWarnings("unchecked")
	public static boolean validateSignature(WxPayCallBackInput input,
			Merchant merchant) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NoSuchAlgorithmException, UnsupportedEncodingException {
		Map<String, String> map = BeanUtils.describe(input);
		map.remove("class");
		map.put("sign", "");
		String sign = getSign(map, merchant);
		return input.getSign().equals(sign) ? true : false;

	}

}

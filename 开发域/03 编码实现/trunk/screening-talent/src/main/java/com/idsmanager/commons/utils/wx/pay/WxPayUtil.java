package com.idsmanager.commons.utils.wx.pay;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.idsmanager.commons.utils.UUIDGenerator;
import com.idsmanager.xsifter.domain.wx.pay.Merchant;
import com.idsmanager.xsifter.domain.wx.pay.OrderQueryRequestParams;
import com.idsmanager.xsifter.domain.wx.pay.OrderQueryResponseParams;
import com.idsmanager.xsifter.domain.wx.pay.WxPayCallBackInput;
import com.idsmanager.xsifter.domain.wx.pay.WxPayCallBackOutput;
import com.idsmanager.xsifter.domain.wx.pay.WxPayCallbackNotify;
import com.idsmanager.xsifter.domain.wx.pay.WxPayPackage;
import com.idsmanager.xsifter.domain.wx.pay.WxPayQrCode;
import com.idsmanager.xsifter.domain.wx.product.Product;

public class WxPayUtil {

	/**
	 * 获取模式一URL
	 * 
	 * @param productId
	 * @param merchant
	 * @return
	 * @throws IllegalAccessException
	 *             (模式一参数错误)
	 * @throws InvocationTargetException
	 *             (模式一参数错误)
	 * @throws NoSuchMethodException
	 *             (模式一参数错误)
	 * @throws NoSuchAlgorithmException
	 *             (MD5错误)
	 * @throws UnsupportedEncodingException
	 *             (MD5错误)
	 */
	public static String getNativePayURL(String productId, Merchant merchant)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NoSuchAlgorithmException,
			UnsupportedEncodingException {

		WxPayQrCode code = new WxPayQrCode(productId, merchant);
		Map<String, String> map = new HashMap<String, String>();
		map.put("sign", code.getSign());
		map.put("appid", code.getAppid());
		map.put("mch_id", code.getMch_id());
		map.put("product_id", code.getProduct_id());
		map.put("time_stamp", code.getTime_stamp());
		map.put("nonce_str", code.getNonce_str());
		String url = "weixin://wxpay/bizpayurl?"
				+ WxMapUtil.mapJoin(map, false, false);
		System.out.println("模式一生成二维码URL=" + url);
		return url;

	}

	/**
	 * 获取微信支付回调请求参数
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static WxPayCallBackInput convertRequest(
			ServletInputStream inputStream) throws IOException {
		String content = IOUtils.toString(inputStream);
		XmlMapper mapper = new XmlMapper();
		WxPayCallBackInput input = mapper.readValue(content,
				WxPayCallBackInput.class);
		return input;
	}

	/**
	 * 封装统一下单package
	 * 
	 * @param input
	 * @param order
	 * @param merchant
	 * @return
	 */
	public static WxPayPackage getPayPackage(WxPayCallBackInput input,
			Product product, Merchant merchant) {
		Map<String, String> map = TimeUtil.getTimeMap(20);
		WxPayPackage payPackage = new WxPayPackage();
		payPackage.setAppid(input.getAppid());
		payPackage.setAttach("");
		payPackage.setDevice_info("WEB");
		payPackage.setBody(product.getProductBody());
		payPackage.setDetail(product.getProductDetail());
		payPackage.setNotify_url("http://" + merchant.getIp() + ":"
				+ merchant.getPort() + "/xsifter/public/wx/callback_notify");
		payPackage.setNonce_str(input.getNonce_str());
		payPackage.setMch_id(input.getMch_id());
		payPackage.setOpenid(input.getOpenid());
		payPackage.setOut_trade_no(input.getProduct_id());
		payPackage.setProduct_id(input.getProduct_id());
		payPackage.setSpbill_create_ip(merchant.getIp());
		payPackage.setTotal_fee(getMoney(product.getProductPrice()));
		payPackage.setFee_type("CNY");
		payPackage.setTrade_type("NATIVE");
		payPackage.setTime_start(map.get("start"));
		payPackage.setTime_expire(map.get("expire"));
		return payPackage;
	}

	/**
	 * 获取预支付ID（prepay_id）
	 * 
	 * @param payPackage
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws JsonProcessingException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public static String getPrePayId(WxPayPackage payPackage, Merchant merchant)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, JsonProcessingException,
			NoSuchAlgorithmException, UnsupportedEncodingException {

		Map<String, String> map = BeanUtils.describe(payPackage);
		map.remove("class");

		String sign = WxSignatureUtil.getSign(map, merchant);
		payPackage.setSign(sign);

		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.setSerializationInclusion(Include.NON_EMPTY);

		String xmlContent = xmlMapper.writeValueAsString(payPackage);

		String prepay_id = WxPrePay
				.getPayNo(WxAPIURL.UNIFY_PAY_API, xmlContent);

		return prepay_id;

	}

	public static String getSuccessXml(WxPayCallBackInput input,
			String prepay_id, Merchant merchant) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NoSuchAlgorithmException, UnsupportedEncodingException {
		WxPayCallBackOutput output = new WxPayCallBackOutput();
		output.setAppid(input.getAppid());
		output.setMch_id(input.getMch_id());
		output.setNonce_str(input.getNonce_str());
		output.setPrepay_id(prepay_id);
		output.setReturn_code("SUCCESS");
		output.setResult_code("SUCCESS");

		Map<String, String> map = BeanUtils.describe(output);
		map.remove("class");

		String sign = WxSignatureUtil.getSign(map, merchant);

		String xmlContent = "<xml>" + "<appid><![CDATA[" + input.getAppid()
				+ "]]></appid>" + "<mch_id><![CDATA[" + input.getMch_id()
				+ "]]></mch_id>" + "<nonce_str><![CDATA["
				+ input.getNonce_str() + "]]></nonce_str>"
				+ "<prepay_id><![CDATA[" + prepay_id + "]]></prepay_id>"
				+ "<return_code><![CDATA[SUCCESS]]></return_code>"
				+ "<result_code><![CDATA[SUCCESS]]></result_code>"
				+ "<return_msg></return_msg>" + "<err_code_des></err_code_des>"
				+ "<sign><![CDATA[" + sign + "]]></sign>" + "</xml>";

		return xmlContent;

	}

	/**
	 * 异步获取支付结果
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static WxPayCallbackNotify getPayCallbackNotify(InputStream input)
			throws IOException {
		String content = IOUtils.toString(input);
		XmlMapper xmlMapper = new XmlMapper();
		WxPayCallbackNotify payCallbackNotify = xmlMapper.readValue(content,
				WxPayCallbackNotify.class);
		if (payCallbackNotify.getResult_code().equals("SUCCESS")
				&& payCallbackNotify.getReturn_code().equals("SUCCESS")) {
			payCallbackNotify.setPaySuccess(true);
		}
		return payCallbackNotify;
	}

	public static String getPaySuccessReplyXML() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<xml>")
				.append("<return_code><![CDATA[SUCCESS]]></return_code>")
				.append("<return_msg><![CDATA[OK]]></return_msg>")
				.append("</xml>");
		return stringBuffer.toString();
	}

	/**
	 * 元转换成分
	 * 
	 * @param money
	 * @return
	 */
	public static String getMoney(String amount) {
		if (amount == null) {
			return "";
		}
		// 金额转化为分为单位
		String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥
																// 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if (index == -1) {
			amLong = Long.valueOf(currency + "00");
		} else if (length - index >= 3) {
			amLong = Long.valueOf((currency.substring(0, index + 3)).replace(
					".", ""));
		} else if (length - index == 2) {
			amLong = Long.valueOf((currency.substring(0, index + 2)).replace(
					".", "") + 0);
		} else {
			amLong = Long.valueOf((currency.substring(0, index + 1)).replace(
					".", "") + "00");
		}
		return amLong.toString();
	}

	/**
	 * 获取微信查询订单xml
	 * 
	 * @param requestParams
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws JsonProcessingException
	 */
	public static String getOrderQueryXml(
			OrderQueryRequestParams requestParams, Merchant merchant)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NoSuchAlgorithmException,
			UnsupportedEncodingException, JsonProcessingException {
		/*
		 * Map<String, String> map = BeanUtils.describe(requestParams);
		 * map.remove("class");
		 * 
		 * String sign = WxSignatureUtil.getSign(map, merchant);
		 * requestParams.setSign(sign);
		 */
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.setSerializationInclusion(Include.NON_EMPTY);

		String xmlContent = xmlMapper.writeValueAsString(requestParams);
		String result = WxPrePay.orderQuery(WxAPIURL.PAY_QUERY_API, xmlContent);
		return result;
	}

	/**
	 * 
	 * @param xml
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static OrderQueryResponseParams getOrderQueryResponse(String xml)
			throws JsonParseException, JsonMappingException, IOException {

		if ("".equals(xml)) {
			OrderQueryResponseParams responseParams = new OrderQueryResponseParams();
			responseParams.setReturn_msg("获取订单信息失败");
			return responseParams;
		}

		XmlMapper mapper = new XmlMapper();
		OrderQueryResponseParams responseParams = mapper.readValue(xml,
				OrderQueryResponseParams.class);

		return responseParams;
	}

	/**
	 * 封装订单查询请求参数
	 * 
	 * @param requestParams
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public static OrderQueryRequestParams getRequestParams(
			OrderQueryRequestParams requestParams, Merchant merchant)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NoSuchAlgorithmException,
			UnsupportedEncodingException {
		requestParams.setNonce_str(UUIDGenerator.generate());

		Map<String, String> map = BeanUtils.describe(requestParams);
		map.remove("class");

		String sign = WxSignatureUtil.getSign(map, merchant);
		requestParams.setSign(sign);

		return requestParams;
	}
}

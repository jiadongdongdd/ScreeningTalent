package com.idsmanager.xsifter.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.zxing.WriterException;
import com.idsmanager.xsifter.domain.wx.pay.OrderQueryRequestParams;
import com.idsmanager.xsifter.domain.wx.pay.OrderQueryResponseParams;
import com.idsmanager.xsifter.service.WxPayService;
import com.idsmanager.xsifter.service.business.wx.MyWxCallBackHandler;
import com.idsmanager.xsifter.service.business.wx.MyWxCallBackNotifyHandler;
import com.idsmanager.xsifter.service.business.wx.MyWxNativePayQrCodeLoader;
import com.idsmanager.xsifter.service.business.wx.MyWxOrderLoader;

@Service("wxPayService")
public class WxPayServiceImpl implements WxPayService {

	@Override
	public void getQrCode(HttpServletResponse response, String productId)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NoSuchAlgorithmException, WriterException,
			IOException {
		MyWxNativePayQrCodeLoader loader = new MyWxNativePayQrCodeLoader(
				response, productId);
		loader.load();
	}

	@Override
	public String nativePayReplyXML(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NoSuchAlgorithmException, IOException {
		MyWxCallBackHandler handler = new MyWxCallBackHandler(request);
		return handler.handle();
	}

	@Override
	public String callBackNotifyReplyXML(HttpServletRequest request) throws IOException {
		MyWxCallBackNotifyHandler handler = new MyWxCallBackNotifyHandler(request);
		return handler.handle();
	}

	@Override
	public OrderQueryResponseParams orderQuery(
			OrderQueryRequestParams requestParams) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchAlgorithmException, IOException {
		MyWxOrderLoader loader = new MyWxOrderLoader(requestParams);
		return loader.load();
	}


}

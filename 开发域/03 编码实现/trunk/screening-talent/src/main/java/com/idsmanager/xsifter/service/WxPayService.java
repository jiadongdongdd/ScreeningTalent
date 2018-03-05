package com.idsmanager.xsifter.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.WriterException;
import com.idsmanager.xsifter.domain.wx.pay.OrderQueryRequestParams;
import com.idsmanager.xsifter.domain.wx.pay.OrderQueryResponseParams;

public interface WxPayService {

	public void getQrCode(HttpServletResponse response, String productId)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NoSuchAlgorithmException, WriterException,
			IOException;

	public String nativePayReplyXML(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NoSuchAlgorithmException, IOException;

	public String callBackNotifyReplyXML(HttpServletRequest request)
			throws IOException;

	public OrderQueryResponseParams orderQuery(
			OrderQueryRequestParams requestParams)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NoSuchAlgorithmException, IOException;

}

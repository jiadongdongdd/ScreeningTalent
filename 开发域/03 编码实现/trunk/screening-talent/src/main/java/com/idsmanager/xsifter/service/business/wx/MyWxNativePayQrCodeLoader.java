package com.idsmanager.xsifter.service.business.wx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.WriterException;
import com.idsmanager.commons.utils.wx.pay.WxPayUtil;
import com.idsmanager.commons.utils.wx.pay.WxQrCodeUtil;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.wx.pay.Merchant;
import com.idsmanager.xsifter.domain.wx.pay.MerchantRepository;
import com.idsmanager.xsifter.domain.wx.pay.WxPayQrCode;

public class MyWxNativePayQrCodeLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyWxNativePayQrCodeLoader.class);

	private MerchantRepository merchantRepository = BeanProvider
			.getBean(MerchantRepository.class);

	private HttpServletResponse response;

	private String productId;

	public MyWxNativePayQrCodeLoader(HttpServletResponse response,
			String productId) {
		super();
		this.response = response;
		this.productId = productId;
	}

	public void load() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NoSuchAlgorithmException, WriterException, IOException {

		List<Merchant> list = this.merchantRepository.findMerchantList();

		if (list == null || list.size() == 0) {
			LOG.debug("系统未设置商户数据");
			throw new IllegalStateException("系统未设置商户数据");
		}

		String url = WxPayUtil.getNativePayURL(productId, list.get(0));
		LOG.debug("{}|Get model1 URL{}", SecurityUtils.username(), url);

		WxQrCodeUtil.encodeQRCode(url, response);

	}

}

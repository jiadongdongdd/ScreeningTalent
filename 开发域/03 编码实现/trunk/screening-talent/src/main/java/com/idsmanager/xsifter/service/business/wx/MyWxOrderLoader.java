package com.idsmanager.xsifter.service.business.wx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idsmanager.commons.utils.wx.pay.WxPayUtil;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.wx.pay.Merchant;
import com.idsmanager.xsifter.domain.wx.pay.MerchantRepository;
import com.idsmanager.xsifter.domain.wx.pay.OrderQueryRequestParams;
import com.idsmanager.xsifter.domain.wx.pay.OrderQueryResponseParams;

public class MyWxOrderLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyWxOrderLoader.class);

	private transient MerchantRepository merchantRepository = BeanProvider
			.getBean(MerchantRepository.class);

	private OrderQueryRequestParams requestParams;

	public MyWxOrderLoader(OrderQueryRequestParams requestParams) {
		super();
		this.requestParams = requestParams;
	}

	public OrderQueryResponseParams load() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NoSuchAlgorithmException, IOException {

		List<Merchant> list = this.merchantRepository.findMerchantList();

		if (list == null || list.size() == 0) {
			System.out.println("没有发现商户信息");
			LOG.debug("Not found merchant info");
		}

		Merchant merchant = list.get(0);

		requestParams = WxPayUtil.getRequestParams(requestParams, merchant);

		// 获取订单结果XML
		String xml = WxPayUtil.getOrderQueryXml(requestParams, merchant);
		System.out.println("查询订单结果：" + xml);
		LOG.debug("Query Order Result:{}", xml);

		// 封装返回结果
		OrderQueryResponseParams responseParams = WxPayUtil
				.getOrderQueryResponse(xml);

		return responseParams;

	}

}

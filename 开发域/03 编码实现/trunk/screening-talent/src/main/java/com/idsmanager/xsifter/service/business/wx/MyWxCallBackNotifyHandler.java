package com.idsmanager.xsifter.service.business.wx;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.wx.pay.WxPayUtil;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.wx.order.WxOrder;
import com.idsmanager.xsifter.domain.wx.order.WxOrderRepository;
import com.idsmanager.xsifter.domain.wx.pay.WxPayCallbackNotify;
import com.idsmanager.xsifter.domain.wx.product.Product;
import com.idsmanager.xsifter.domain.wx.product.ProductRepository;
import com.idsmanager.xsifter.service.CompanyService;

public class MyWxCallBackNotifyHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyWxCallBackNotifyHandler.class);

	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private transient WxOrderRepository wxOrderRepository = BeanProvider
			.getBean(WxOrderRepository.class);

	private transient ProductRepository productRepository = BeanProvider
			.getBean(ProductRepository.class);

	private HttpServletRequest request;

	public MyWxCallBackNotifyHandler(HttpServletRequest request) {
		super();
		this.request = request;
	}

	public String handle() throws IOException {
		WxPayCallbackNotify notify = WxPayUtil.getPayCallbackNotify(request
				.getInputStream());
		System.out.println("异步通知结果：" + notify);
		LOG.debug("Notify Callback Result:" + notify);
		if (notify.isPaySuccess()) {
			// 增加积分
			String orderNo = notify.getOut_trade_no();// 订单号
			System.out.println("订单号：" + orderNo);
			WxOrder order = null;
			if (orderNo != null) {
				order = wxOrderRepository.findOrderByOrderNo(orderNo);
			}

			if (order != null) {
				Company company = companyRepository.findByGuid(order
						.getCustomerId());
				Product product = productRepository.findProductByUUID(order
						.getProductId());
				if (company != null && product != null) {
					
					order.setPaySuccess(true)
							.setBeforeCredit(company.getCreditNumber())
							.setPrice(product.getProductPrice())
							.setDetail("积分充值");
					
					String credit = product.getCredit();
					if (StringUtils.isNotEmpty(credit)) {
						company.setCreditNumber(company.getCreditNumber()
								+ Integer.valueOf(product.getCredit()));
						this.companyRepository.saveCompany(company);
						LOG.debug("{}|Add {} credit By Charge",
								company.getCompanyName(), credit);
						order.setAfterCredit(company.getCreditNumber());
						this.wxOrderRepository.saveWxOrder(order);
					}
					
				}
			}

			String xml = WxPayUtil.getPaySuccessReplyXML();
			System.out.println("返回微信支付信息：" + xml);
			LOG.debug("Return notify callback info:" + xml);
			return xml;
		}

		return null;
	}
}

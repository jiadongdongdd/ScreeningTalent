package com.idsmanager.xsifter.service.business.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.UUIDGenerator;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.wx.order.WxOrder;
import com.idsmanager.xsifter.domain.wx.order.WxOrderRepository;

public class MyWxOrderCreater {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyWxOrderCreater.class);

	private transient WxOrderRepository orderRepository = BeanProvider
			.getBean(WxOrderRepository.class);
	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private String uuid;

	public MyWxOrderCreater(String uuid) {
		super();
		this.uuid = uuid;
	}

	public String create() {
		Company company = this.companyRepository.findByGuid(SecurityUtils
				.currentUserGuid());
		WxOrder order = createOrder(company);
		this.orderRepository.saveWxOrder(order);
		LOG.debug("{}|Create an wxorder {}", SecurityUtils.username(), order);
		return order.getOrderNo();
	}

	private WxOrder createOrder(Company company) {
		if (company != null) {
			return new WxOrder().setCustomerId(SecurityUtils.currentUserGuid())
					.setCustomerName(company.getCompanyName())
					.setOrderDate(DateUtils.now())
					.setOrderNo(UUIDGenerator.generate()).setProductId(uuid);
		}
		return new WxOrder().setCustomerId(SecurityUtils.currentUserGuid())
				.setCustomerName(SecurityUtils.username())
				.setOrderDate(DateUtils.now())
				.setOrderNo(UUIDGenerator.generate()).setProductId(uuid);
	}

}

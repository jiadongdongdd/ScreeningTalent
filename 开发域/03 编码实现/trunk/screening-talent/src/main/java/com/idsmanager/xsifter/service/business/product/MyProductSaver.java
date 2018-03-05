package com.idsmanager.xsifter.service.business.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.wx.product.Product;
import com.idsmanager.xsifter.domain.wx.product.ProductRepository;
import com.idsmanager.xsifter.service.dto.wx.pay.ProductFormDto;

public class MyProductSaver {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyProductSaver.class);
	private transient ProductRepository productRepository = BeanProvider
			.getBean(ProductRepository.class);
	private ProductFormDto formDto;

	public MyProductSaver(ProductFormDto formDto) {
		super();
		this.formDto = formDto;
	}

	public void save() {

		final String uuid = formDto.getUuid();
		if (uuid == null) {
			Product product = formDto.create();
			this.productRepository.saveProduct(product);
			LOG.debug("{}|Create Product{}", SecurityUtils.username(), product);
		} else {
			Product dataProduct = this.productRepository
					.findProductByUUID(uuid);
			Product product = formDto.update(dataProduct);
			this.productRepository.saveProduct(product);
			LOG.debug("{}|Update Product{}", SecurityUtils.username(), product);
		}

	}

}

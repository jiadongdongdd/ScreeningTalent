package com.idsmanager.xsifter.service.business.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.wx.product.Product;
import com.idsmanager.xsifter.domain.wx.product.ProductRepository;

public class MyProductRemover {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyProductRemover.class);

	private transient ProductRepository productRepository = BeanProvider
			.getBean(ProductRepository.class);

	private String uuid;

	public MyProductRemover(String uuid) {
		super();
		this.uuid = uuid;
	}

	public void remove() {
		Product product = this.productRepository.findProductByUUID(uuid);
		if(product != null) {
			this.productRepository.deleteProduct(product);
		}
	}

}

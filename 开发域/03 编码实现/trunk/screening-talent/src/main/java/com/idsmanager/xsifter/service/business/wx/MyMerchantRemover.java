package com.idsmanager.xsifter.service.business.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.wx.pay.Merchant;
import com.idsmanager.xsifter.domain.wx.pay.MerchantRepository;

public class MyMerchantRemover {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyMerchantRemover.class);

	private transient MerchantRepository merchantRepository = BeanProvider
			.getBean(MerchantRepository.class);

	private String uuid;

	public MyMerchantRemover(String uuid) {
		super();
		this.uuid = uuid;
	}

	public void remove() {

		Merchant merchant = this.merchantRepository.findMerchantByUUID(uuid);

		if (merchant == null) {

		}

		this.merchantRepository.deleteMerchant(merchant);
		LOG.debug("{}|Delete Merchant {}", SecurityUtils.username(), merchant);

	}

}

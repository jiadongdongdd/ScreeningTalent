package com.idsmanager.xsifter.service.business.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.wx.pay.Merchant;
import com.idsmanager.xsifter.domain.wx.pay.MerchantRepository;
import com.idsmanager.xsifter.service.dto.wx.pay.MerchantFormDto;

public class MyMerchantLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyMerchantLoader.class);

	private transient MerchantRepository merchantRepository = BeanProvider
			.getBean(MerchantRepository.class);

	private String uuid;

	public MyMerchantLoader(String uuid) {
		super();
		this.uuid = uuid;
	}

	public MerchantFormDto load() {
		Merchant merchant = this.merchantRepository.findMerchantByUUID(uuid);
		if (merchant == null) {

		}
		MerchantFormDto formDto = new MerchantFormDto(merchant);
		LOG.debug("{}|Load Merchant{}", SecurityUtils.username(), merchant);

		return formDto;
	}

}

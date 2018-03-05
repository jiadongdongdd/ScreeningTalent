package com.idsmanager.xsifter.service.business.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.wx.pay.Merchant;
import com.idsmanager.xsifter.domain.wx.pay.MerchantRepository;
import com.idsmanager.xsifter.service.dto.wx.pay.MerchantFormDto;

public class MyMerchantSaver {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyMerchantSaver.class);

	private transient MerchantRepository merchantRepository = BeanProvider
			.getBean(MerchantRepository.class);

	private MerchantFormDto formDto;

	public MyMerchantSaver(MerchantFormDto formDto) {
		this.formDto = formDto;
	}

	public void save() {
		final String uuid = formDto.getUuid();
		if (uuid == null) {
			Merchant merchant = formDto.create();
			this.merchantRepository.saveMerchant(merchant);
			LOG.debug("{}|Create a Merchant{}", SecurityUtils.username(),
					merchant);
		} else {
			Merchant merchant = this.merchantRepository
					.findMerchantByUUID(uuid);

			if (merchant == null) {
				throw new IllegalStateException("Not Found Merchant");
			}

			Merchant merchantUpdate = formDto.update(merchant);
			this.merchantRepository.saveMerchant(merchantUpdate);
			LOG.debug("{}|Update Merchant{}",SecurityUtils.username(),merchant);

		}

	}
}

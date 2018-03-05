package com.idsmanager.xsifter.service;

import com.idsmanager.xsifter.service.dto.wx.pay.MerchantFormDto;
import com.idsmanager.xsifter.service.dto.wx.pay.MerchantPaginated;

public interface MerchantService {

	MerchantPaginated loadMyMerchant(MerchantPaginated paginated);

	void createMerchant(MerchantFormDto formDto);

	MerchantFormDto loadMyMerchantByUUID(String uuid);

	void updateMerchant(MerchantFormDto formDto);

	void deleteMerchant(String uuid);

	void init();

}

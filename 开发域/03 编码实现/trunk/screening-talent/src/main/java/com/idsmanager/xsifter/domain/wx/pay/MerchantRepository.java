package com.idsmanager.xsifter.domain.wx.pay;

import java.util.List;
import java.util.Map;

import com.idsmanager.xsifter.domain.Repository;

public interface MerchantRepository extends Repository{

	long totalMerchantList(Map<String, Object> map);

	List<Merchant> findMerchantList(Map<String, Object> map);

	void saveMerchant(Merchant merchant);

	Merchant findMerchantByUUID(String uuid);

	void deleteMerchant(Merchant merchant);

	List<Merchant> findMerchantList();

}

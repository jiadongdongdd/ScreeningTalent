package com.idsmanager.xsifter.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.xsifter.domain.wx.pay.Merchant;
import com.idsmanager.xsifter.domain.wx.pay.MerchantRepository;
import com.idsmanager.xsifter.service.MerchantService;
import com.idsmanager.xsifter.service.business.wx.MyMerchantLoader;
import com.idsmanager.xsifter.service.business.wx.MyMerchantRemover;
import com.idsmanager.xsifter.service.business.wx.MyMerchantSaver;
import com.idsmanager.xsifter.service.dto.wx.pay.MerchantFormDto;
import com.idsmanager.xsifter.service.dto.wx.pay.MerchantPaginated;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {

	private static final Logger LOG = LoggerFactory
			.getLogger(MerchantServiceImpl.class);

	@Autowired
	private MerchantRepository merchantRepository;

	@Override
	public MerchantPaginated loadMyMerchant(MerchantPaginated paginated) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<Merchant>() {

			@Override
			public long loadTotalSize() {
				return merchantRepository.totalMerchantList(map);
			}

			@Override
			public List<Merchant> loadList() {
				return merchantRepository.findMerchantList(map);
			}
		});
	}

	@Override
	public void createMerchant(MerchantFormDto formDto) {
		MyMerchantSaver saver = new MyMerchantSaver(formDto);
		saver.save();
	}

	@Override
	public MerchantFormDto loadMyMerchantByUUID(String uuid) {
		MyMerchantLoader loader = new MyMerchantLoader(uuid);
		return loader.load();
	}

	@Override
	public void updateMerchant(MerchantFormDto formDto) {
		MyMerchantSaver saver = new MyMerchantSaver(formDto);
		saver.save();
	}

	@Override
	public void deleteMerchant(String uuid) {
		MyMerchantRemover remover = new MyMerchantRemover(uuid);
		remover.remove();
	}

	@Override
	public void init() {

		List<Merchant> list = merchantRepository.findMerchantList();
		if (list == null || list.size() == 0) {
			Merchant merchant = new Merchant().setAppId("wxffa009d7df70383c")
					.setIp("123.59.78.13").setMchId("1313578201")
					.setMchKey("e84786ce109849f6b1fe1c6a0e97ec4f");
			this.merchantRepository.saveMerchant(merchant);
		}

	}

}

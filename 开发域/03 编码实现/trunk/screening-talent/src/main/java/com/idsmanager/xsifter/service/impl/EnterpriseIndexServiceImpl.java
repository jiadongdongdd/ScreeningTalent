package com.idsmanager.xsifter.service.impl;

import org.springframework.stereotype.Service;

import com.idsmanager.xsifter.service.EnterpriseIndexService;
import com.idsmanager.xsifter.service.business.enterprise.EnterpriseIndexLoader;
import com.idsmanager.xsifter.service.dto.enterprise.EnterpriseIndexDto;

@Service("enterpriseIndexService")
public class EnterpriseIndexServiceImpl implements EnterpriseIndexService {

	@Override
	public EnterpriseIndexDto loadEnterpriseIndex() {
		EnterpriseIndexLoader loader = new EnterpriseIndexLoader();
		return loader.load();
	}

}

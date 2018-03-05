package com.idsmanager.xsifter.service.impl;

import org.springframework.stereotype.Service;

import com.idsmanager.xsifter.service.AdminIndexService;
import com.idsmanager.xsifter.service.business.admin.AdminIndexLoader;
import com.idsmanager.xsifter.service.dto.admin.AdminIndexDto;

@Service("adminIndexService")
public class AdminIndexServiceImpl implements AdminIndexService {

	@Override
	public AdminIndexDto loadAdminIndex() {
		AdminIndexLoader loader = new AdminIndexLoader();
		return loader.load();
	}

}

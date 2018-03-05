package com.idsmanager.xsifter.service.impl;

import org.springframework.stereotype.Service;

import com.idsmanager.xsifter.service.TScAcntPropService;
import com.idsmanager.xsifter.service.business.member.TScAcntPropLoad;
@Service("tScAcntPropService")
public class TScAcntPropServiceImpl implements TScAcntPropService {

	@Override
	public Integer loadTScAcntNumber(String uuid) {
		TScAcntPropLoad loader = new TScAcntPropLoad(uuid);
		return loader.load();
	}

}

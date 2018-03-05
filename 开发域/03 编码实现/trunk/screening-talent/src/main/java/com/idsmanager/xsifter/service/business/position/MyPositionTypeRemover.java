package com.idsmanager.xsifter.service.business.position;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.position.PositionType;
import com.idsmanager.xsifter.domain.position.PositionTypeRepository;

public class MyPositionTypeRemover {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyPositionTypeRemover.class);

	private PositionTypeRepository positionTypeRepository = BeanProvider
			.getBean(PositionTypeRepository.class);

	private String uuid;

	public MyPositionTypeRemover(String uuid) {
		super();
		this.uuid = uuid;
	}

	public void remove() {
		
		PositionType type = this.positionTypeRepository.findPositionTypeByUuid(uuid);
		
		if(type != null) {
			this.positionTypeRepository.deleteType(type);
		}

	}

}

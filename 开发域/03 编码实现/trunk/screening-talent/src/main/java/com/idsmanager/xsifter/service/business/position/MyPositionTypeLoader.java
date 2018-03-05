package com.idsmanager.xsifter.service.business.position;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.position.PositionType;
import com.idsmanager.xsifter.domain.position.PositionTypeRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.position.PositionTypeFormDto;

public class MyPositionTypeLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyPositionTypeLoader.class);

	private PositionTypeRepository positionTypeRepository = BeanProvider
			.getBean(PositionTypeRepository.class);

	private String uuid;

	public MyPositionTypeLoader(String uuid) {
		super();
		this.uuid = uuid;
	}

	public PositionTypeFormDto load() {

		PositionType positionType = this.positionTypeRepository
				.findPositionTypeByUuid(uuid);

		LOG.debug("{}|Load positionType: {}", SecurityUtils.username(),
				positionType);

		return positionType == null ? new PositionTypeFormDto()
				: new PositionTypeFormDto(positionType);

	}

}

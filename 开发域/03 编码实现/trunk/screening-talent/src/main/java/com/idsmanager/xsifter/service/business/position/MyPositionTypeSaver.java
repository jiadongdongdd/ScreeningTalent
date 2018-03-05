package com.idsmanager.xsifter.service.business.position;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.position.PositionType;
import com.idsmanager.xsifter.domain.position.PositionTypeRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.position.PositionTypeFormDto;

public class MyPositionTypeSaver {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyPositionTypeSaver.class);

	private PositionTypeRepository positionTypeRepository = BeanProvider
			.getBean(PositionTypeRepository.class);

	private PositionTypeFormDto formDto;

	public MyPositionTypeSaver(PositionTypeFormDto formDto) {
		super();
		this.formDto = formDto;
	}

	public void save() {

		PositionType positionType = this.positionTypeRepository
				.findPositionTypeByUuid(formDto.getUuid());

		if (positionType == null) {
			positionType = createPositionType();
		} else {
			positionType = updatePositionType(positionType);
		}

	}

	private PositionType updatePositionType(PositionType positionType) {
		PositionType type = formDto.update(positionType);
		this.positionTypeRepository.saveType(type);
		LOG.debug("{}|Update positionType: {}", SecurityUtils.username(), type);
		return type;
	}

	private PositionType createPositionType() {
		PositionType type = formDto.create();
		this.positionTypeRepository.saveType(type);
		LOG.debug("{}|Create positionType: {}", SecurityUtils.username(), type);
		return type;
	}

}

package com.idsmanager.xsifter.service.business.position;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.position.PositionFormDto;

public class MyPositionSaver {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyPositionSaver.class);

	private MemberPositionRepository memberPositionRepository = BeanProvider
			.getBean(MemberPositionRepository.class);

	private PositionFormDto formDto;

	public MyPositionSaver(PositionFormDto formDto) {
		super();
		this.formDto = formDto;
	}

	public void save() {
		MemberPosition position = this.memberPositionRepository
				.findPostionByUuid(formDto.getUuid());

		if (position == null) {
			position = createPosition();
		} else {
			position = updatePosition(position);
		}
	}

	private MemberPosition updatePosition(MemberPosition position) {
		position = formDto.update(position);
		this.memberPositionRepository.savePosition(position);
		LOG.debug("{}|Update MemberPosition: {}", SecurityUtils.username(),
				position);
		return position;
	}

	private MemberPosition createPosition() {
		MemberPosition position = formDto.create();
		this.memberPositionRepository.savePosition(position);
		LOG.debug("{}|Create MemberPosition: {}", SecurityUtils.username(),
				position);
		return position;
	}

}

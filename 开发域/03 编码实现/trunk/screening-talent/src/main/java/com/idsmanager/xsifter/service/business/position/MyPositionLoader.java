package com.idsmanager.xsifter.service.business.position;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.position.PositionFormDto;

public class MyPositionLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyPositionLoader.class);

	private MemberPositionRepository memberPositionRepository = BeanProvider
			.getBean(MemberPositionRepository.class);

	private String uuid;

	public MyPositionLoader(String uuid) {
		super();
		this.uuid = uuid;
	}

	public PositionFormDto load() {

		MemberPosition position = this.memberPositionRepository
				.findPostionByUuid(uuid);

		LOG.debug("{}|Load MemberPosition: {}", SecurityUtils.username(),
				position);

		return position == null ? new PositionFormDto() : new PositionFormDto(
				position);

	}

}

package com.idsmanager.xsifter.service.business.position;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;

public class MyPositionRemover {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyPositionRemover.class);

	private MemberPositionRepository memberPositionRepository = BeanProvider
			.getBean(MemberPositionRepository.class);

	private String uuid;

	public MyPositionRemover(String uuid) {
		super();
		this.uuid = uuid;
	}

	public void remove() {

		MemberPosition position = this.memberPositionRepository
				.findPostionByUuid(uuid);

		if (position != null) {
			this.memberPositionRepository.deletePosition(position);
		}

	}

}

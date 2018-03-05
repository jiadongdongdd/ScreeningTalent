package com.idsmanager.xsifter.service.business.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.news.News;
import com.idsmanager.xsifter.domain.news.NewsRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.news.NewsFormDto;

public class MyNewsLoader {
	private static final Logger LOG = LoggerFactory
			.getLogger(MyNewsLoader.class);
	private transient NewsRepository newsRepository = BeanProvider
			.getBean(NewsRepository.class);
	private String uuid;

	public MyNewsLoader(String uuid) {
		super();
		this.uuid = uuid;
	}

	public NewsFormDto load() {

		NewsFormDto formDto;

		final News news = this.newsRepository.findNewsByUuid(uuid);

		if (news == null) {
			formDto = new NewsFormDto();
		}

		formDto = new NewsFormDto(news);
		LOG.debug("{}|Load news{}", SecurityUtils.currentUserGuid(), news);
		return formDto;
	}

}

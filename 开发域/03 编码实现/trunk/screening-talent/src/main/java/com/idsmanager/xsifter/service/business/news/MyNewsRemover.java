package com.idsmanager.xsifter.service.business.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.news.News;
import com.idsmanager.xsifter.domain.news.NewsRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

public class MyNewsRemover {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyNewsRemover.class);
	private transient NewsRepository newsRepository = BeanProvider
			.getBean(NewsRepository.class);

	private String uuid;

	public MyNewsRemover(String uuid) {
		super();
		this.uuid = uuid;
	}

	public void remove() {
		News news = this.newsRepository.findNewsByUuid(uuid);
		if (news != null) {
			this.newsRepository.deleteNews(news);
			LOG.debug("{}|Delete news{}", SecurityUtils.currentUserGuid(), news);
		}
	}

}

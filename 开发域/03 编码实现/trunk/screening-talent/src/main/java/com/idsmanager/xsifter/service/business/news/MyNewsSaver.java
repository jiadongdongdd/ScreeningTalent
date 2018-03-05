package com.idsmanager.xsifter.service.business.news;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.commons.IdsFile;
import com.idsmanager.xsifter.domain.news.News;
import com.idsmanager.xsifter.domain.news.NewsRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.news.NewsFormDto;

public class MyNewsSaver {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyNewsSaver.class);

	private transient NewsRepository newsRepository = BeanProvider
			.getBean(NewsRepository.class);

	private NewsFormDto formDto;

	public MyNewsSaver(NewsFormDto formDto) {
		super();
		this.formDto = formDto;
	}

	public void save() {

		News news;

		final String uuid = formDto.getUuid();

		if (uuid == null) {
			news = formDto.create();
			updateFiles(news);
			this.newsRepository.saveNews(news);
			LOG.debug("{}|Create News{}", SecurityUtils.username(), news);

		} else {
			News newsUpdate = this.newsRepository.findNewsByUuid(uuid);
			if(newsUpdate !=null) {
				news = formDto.update(newsUpdate);
				updateFiles(news);
				this.newsRepository.saveNews(news);
				LOG.debug("{}|Update News{}", SecurityUtils.username(), news);
			}
		}

	}

	private void updateFiles(News news) {
		final String pictureGuid = createAndSaveFile(formDto.getPictureFile());
		if (pictureGuid != null) {
			news.setPicture(pictureGuid);
		}

	}

	private String createAndSaveFile(MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			try {
				IdsFile idsFile = new IdsFile(file.getOriginalFilename(),
						file.getBytes());
				idsFile.save();
				return idsFile.getGuid();
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
		return null;
	}

}

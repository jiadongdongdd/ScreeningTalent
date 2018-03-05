package com.idsmanager.xsifter.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.xsifter.domain.news.News;
import com.idsmanager.xsifter.domain.news.NewsRepository;
import com.idsmanager.xsifter.service.NewsService;
import com.idsmanager.xsifter.service.business.news.MyNewsLoader;
import com.idsmanager.xsifter.service.business.news.MyNewsRemover;
import com.idsmanager.xsifter.service.business.news.MyNewsSaver;
import com.idsmanager.xsifter.service.dto.news.NewsFormDto;
import com.idsmanager.xsifter.service.dto.news.NewsPaginated;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsRepository newsRepository;

	@Override
	public NewsPaginated loadNewsPaginated(NewsPaginated paginated) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<News>() {

			@Override
			public long loadTotalSize() {
				return newsRepository.totalNewsList(map);
			}

			@Override
			public List<News> loadList() {
				return newsRepository.findNewsList(map);
			}
		});
	}

	@Override
	public void saveNews(NewsFormDto formDto) {
		MyNewsSaver saver = new MyNewsSaver(formDto);
		saver.save();
	}

	@Override
	public NewsFormDto loadMyNewsByUUID(String uuid) {
		MyNewsLoader loader = new MyNewsLoader(uuid);
		return loader.load();
	}

	@Override
	public void deleteNews(String uuid) {
		MyNewsRemover remover = new MyNewsRemover(uuid);
		remover.remove();
	}

}

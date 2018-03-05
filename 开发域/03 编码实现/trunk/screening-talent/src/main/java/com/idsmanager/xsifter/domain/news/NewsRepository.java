package com.idsmanager.xsifter.domain.news;

import java.util.List;
import java.util.Map;

import com.idsmanager.xsifter.domain.Repository;

public interface NewsRepository extends Repository{

	long totalNewsList(Map<String, Object> map);

	List<News> findNewsList(Map<String, Object> map);

	void saveNews(News news);

	News findNewsByUuid(String uuid);

	void deleteNews(News news);

}

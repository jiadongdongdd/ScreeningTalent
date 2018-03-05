package com.idsmanager.xsifter.service;

import com.idsmanager.xsifter.service.dto.news.NewsFormDto;
import com.idsmanager.xsifter.service.dto.news.NewsPaginated;

public interface NewsService {

	NewsPaginated loadNewsPaginated(NewsPaginated paginated);

	void saveNews(NewsFormDto formDto);

	NewsFormDto loadMyNewsByUUID(String uuid);

	void deleteNews(String uuid);

}

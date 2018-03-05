package com.idsmanager.xsifter.infrastructure.mongo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.news.News;
import com.idsmanager.xsifter.domain.news.NewsRepository;
import com.idsmanager.xsifter.domain.news.Platform;

@Repository("newsRepositoryMongo")
public class NewsRepositoryMongo extends AbstractMongoSupport implements
		NewsRepository {

	@Override
	public long totalNewsList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, News.class);
	}

	@Override
	public List<News> findNewsList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().find(query, News.class);
	}

	private void addQueryConditions(Map<String, Object> map, Query query) {
		final String title = (String) map.get("title");
		if (StringUtils.isNotEmpty(title)) {
			query.addCriteria(Criteria.where("title")
					.regex("/*" + title + "/*"));
		}
		final Boolean ispush = (Boolean) map.get("ispush");
		if(null != ispush){
			query.addCriteria(Criteria.where("ispush").is(ispush));
		}
		
		final Platform platform = (Platform) map.get("platform");
		if(null!=platform){
			query.addCriteria(Criteria.where("platform").is(platform));
		}

	}

	@Override
	public void saveNews(News news) {
		this.mongoTemplate().save(news);

	}

	@Override
	public News findNewsByUuid(String uuid) {
		Query query = new Query(Criteria.where("uuid").is(uuid));
		return this.mongoTemplate().findOne(query, News.class);
	}

	@Override
	public void deleteNews(News news) {
		this.mongoTemplate().remove(news);
	}

}

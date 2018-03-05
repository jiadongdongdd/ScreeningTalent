package com.idsmanager.xsifter.infrastructure.mongo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.log.Logs;
import com.idsmanager.xsifter.domain.log.LogsRepository;

@Repository("logsRepositoryMongo")
public class LogsRepositoryMongo extends AbstractMongoSupport implements
		LogsRepository {

	@Override
	public Logs findLogsByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		return this.mongoTemplate().findOne(query, Logs.class);
	}

	@Override
	public void saveLogs(Logs logs) {
		this.mongoTemplate().save(logs);
	}

}

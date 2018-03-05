package com.idsmanager.xsifter.infrastructure.mongo;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.log.Log;
import com.idsmanager.xsifter.domain.log.OperateType;
import com.idsmanager.xsifter.domain.login.LoginRecord;
import com.idsmanager.xsifter.domain.login.LoginRepository;

@Repository("loginRepository")
public class LoginRepositoryMongo extends AbstractMongoSupport implements
		LoginRepository {

	@Override
	public LoginRecord findLoginRecordByUserUUID(String userUUID) {
		Query query = new Query(Criteria.where("userUUID").is(userUUID));
		return this.mongoTemplate().findOne(query, LoginRecord.class);
	}

	@Override
	public void saveLoginRecord(LoginRecord loginRecord) {
		this.mongoTemplate().save(loginRecord);
	}

	@Override
	public List<LoginRecord> findActiveUsers() {
		Query query = new Query(Criteria.where("todayLoginCount").gte(5));
		return this.mongoTemplate().find(query, LoginRecord.class);
	}

	@Override
	public long totalActiveUsers() {
		Query query = new Query(Criteria.where("todayLoginCount").gte(5));
		return this.mongoTemplate().count(query, LoginRecord.class);
	}

	@Override
	public long findTotalStationQueryTimes() {
		Query query = new Query(Criteria.where("operateType").is(
				OperateType.TOTAL_STATION_QUERY_MEMBER));
		return this.mongoTemplate().count(query, Log.class);
	}

}

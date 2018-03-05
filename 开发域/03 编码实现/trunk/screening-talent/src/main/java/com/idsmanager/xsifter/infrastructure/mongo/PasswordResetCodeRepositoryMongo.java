package com.idsmanager.xsifter.infrastructure.mongo;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.password.PasswordResetCode;
import com.idsmanager.xsifter.domain.password.PasswordResetCodeRepository;

@Repository("passwordResetCodeRepository")
public class PasswordResetCodeRepositoryMongo extends AbstractMongoSupport
		implements PasswordResetCodeRepository {

	@Override
	public void saveCode(PasswordResetCode code) {
		this.mongoTemplate().save(code);
	}

	@Override
	public PasswordResetCode findCodeByUUID(String uuid) {
		Query query = new Query(Criteria.where("uuid").is(uuid));
		return this.mongoTemplate().findOne(query, PasswordResetCode.class);
	}

	@Override
	public void deleteCode(PasswordResetCode passwordResetCode) {
		this.mongoTemplate().remove(passwordResetCode);
	}

	@Override
	public PasswordResetCode findCodeByUserUUID(String userUUID) {
		Query query = new Query(Criteria.where("userUUID").is(userUUID));
		return this.mongoTemplate().findOne(query, PasswordResetCode.class);
	}

}

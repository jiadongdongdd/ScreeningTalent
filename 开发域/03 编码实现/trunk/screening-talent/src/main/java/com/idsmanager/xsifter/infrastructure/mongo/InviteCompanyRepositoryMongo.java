package com.idsmanager.xsifter.infrastructure.mongo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompany;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompanyRepository;

@Repository("inviteCompanyRepositoryMongo")
public class InviteCompanyRepositoryMongo extends AbstractMongoSupport
		implements InviteCompanyRepository {
	@Autowired
	private MongoOperations mongoTemplate;

	@Override
	public List<InviteCompany> findCompanyList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return mongoTemplate.find(query, InviteCompany.class);
	}

	private void addQueryConditions(Map<String, Object> map, Query query) {
		final String companyName = (String) map.get("companyName");
		final Boolean verifyState = (Boolean) map.get("inviteState");
		final String inviteUsername = (String) map.get("inviteUsername");
		if (StringUtils.isNotEmpty(companyName)) {
			query.addCriteria(Criteria.where("companyName").regex(
					"/*" + companyName + "/*"));
		}
		if (null != verifyState) {
			query.addCriteria(Criteria.where("inviteState").is(verifyState));
		}

		if (null != inviteUsername) {
			query.addCriteria(Criteria.where("inviteUsername").is(
					inviteUsername));
		}

	}

	@Override
	public long totalInviteCompanyList(Map<String, Object> map) {
		Query query = new Query();
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, InviteCompany.class);
	}

	@Override
	public void saveInviteCompany(InviteCompany company) {
		this.mongoTemplate().save(company);

	}

	@Override
	public void updateInviteCompany(InviteCompany company) {
		Update update = new Update();
		update.set("username", company.getUsername());
		update.set("companyName", company.getCompanyName());
		update.set("companyEmail", company.getCompanyEmail());
		update.set("inviteState", company.getInviteState());

		Query query = createIDQuery(company.getGuid());
		this.mongoTemplate().updateFirst(query, update, InviteCompany.class);

	}

	@Override
	public InviteCompany findByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		return this.mongoTemplate().findOne(query, InviteCompany.class);
	}

	@Override
	public InviteCompany findByGuid(String guid) {
		Query query = new Query(Criteria.where("guid").is(guid));
		return this.mongoTemplate().findOne(query, InviteCompany.class);
	}

	@Override
	public InviteCompany findBycompanyEmail(String email) {
		Query query = new Query(Criteria.where("companyEmail").is(email));
		return this.mongoTemplate().findOne(query, InviteCompany.class);
	}

	@Override
	public InviteCompany findBycompanyName(String companyName) {
		Query query = new Query(Criteria.where("companyName").is(companyName));
		return this.mongoTemplate().findOne(query, InviteCompany.class);
	}

	@Override
	public long totalCompanyInviteList(String inviteUsername) {
		Query query = new Query(Criteria.where("inviteUsername").is(
				inviteUsername));
		return this.mongoTemplate().count(query, InviteCompany.class);
	}

	@Override
	public long totalCompanyInviteSuccessList(String inviteUsername) {
		Query query = new Query(Criteria.where("inviteUsername")
				.is(inviteUsername).and("inviteState").is(true));
		return this.mongoTemplate().count(query, InviteCompany.class);
	}

	@Override
	public void removeInviteCompany(InviteCompany inviteCompany) {
		this.mongoTemplate().remove(inviteCompany);
	}

}

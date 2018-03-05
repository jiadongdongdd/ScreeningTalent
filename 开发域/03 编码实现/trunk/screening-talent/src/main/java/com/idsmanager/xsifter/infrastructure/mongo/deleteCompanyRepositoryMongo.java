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
import com.idsmanager.xsifter.domain.company.DeleteCompany;
import com.idsmanager.xsifter.domain.company.DeleteCompanyRepository;

@Repository("deleteCompanyRepositoryMongo")
public class deleteCompanyRepositoryMongo extends AbstractMongoSupport implements
		 DeleteCompanyRepository {

	@Autowired
	private MongoOperations mongoTemplate;

	@Override
	public void savedeleteCompany(DeleteCompany company) {
		mongoTemplate.save(company);

	}

	@Override
	public void updatedeleteCompany(DeleteCompany company) {
		Update update = new Update();
		update.set("username", company.getUsername());
		update.set("password", company.getPassword());
		update.set("companyName", company.getCompanyName());
		update.set("companyEmail", company.getCompanyEmail());
		update.set("contacts", company.getContacts());
		update.set("contactsPhone", company.getContactsPhone());
		update.set("verifyState", company.getVerifyState());
		update.set("verifyPass", company.getVerifyPass());

		Query query = createIDQuery(company.guid());
		this.mongoTemplate().updateFirst(query, update, DeleteCompany.class);

	}

	@Override
	public DeleteCompany findByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		return this.mongoTemplate().findOne(query, DeleteCompany.class);
	}

	@Override
	public List<DeleteCompany> finddeleteCompanyList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return mongoTemplate.find(query, DeleteCompany.class);
	}

	@Override
	public long totaldeleteCompanyList(Map<String, Object> map) {
		Query query = new Query();
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, DeleteCompany.class);
	}

	private void addQueryConditions(Map<String, Object> map, Query query) {
		final String companyName = (String) map.get("companyName");
		final Boolean verifyState = (Boolean) map.get("verifyState");
		final Boolean verifyPass = (Boolean) map.get("verifyPass");
		if (StringUtils.isNotEmpty(companyName)) {
			query.addCriteria(Criteria.where("companyName").regex(
					"/*" + companyName + "/*"));
		}
		if (null != verifyState) {
			query.addCriteria(Criteria.where("verifyState").is(verifyState));
		}
		if (null != verifyPass) {
			query.addCriteria(Criteria.where("verifyPass").is(verifyPass));
		}

	}

	@Override
	public void removedeleteCompany(DeleteCompany company) {
		mongoTemplate.remove(company);
	}

	@Override
	public DeleteCompany findByGuid(String guid) {
		Query query = new Query(Criteria.where("guid").is(guid));
		return mongoTemplate.findOne(query, DeleteCompany.class);
	}

	@Override
	public DeleteCompany findBydeleteCompanyName(String companyName) {
		Query query = new Query(Criteria.where("companyName").is(companyName));
		return mongoTemplate.findOne(query, DeleteCompany.class);
	}

	@Override
	public DeleteCompany findBydeleteCompanyEmail(String email) {
		Query query = new Query(Criteria.where("companyEmail").is(email));
		return this.mongoTemplate().findOne(query, DeleteCompany.class);
	}

	@Override
	public void updatedeleteCompanyByState(DeleteCompany com) {
		Update update = new Update();
		update.set("verifyState", com.getVerifyState());
		update.set("verifyPass", com.getVerifyPass());
		Query query = createIDQuery(com.guid());
		this.mongoTemplate().updateFirst(query, update, DeleteCompany.class);

	}

}

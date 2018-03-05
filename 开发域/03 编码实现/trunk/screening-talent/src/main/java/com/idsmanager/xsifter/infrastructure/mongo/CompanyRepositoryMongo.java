package com.idsmanager.xsifter.infrastructure.mongo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.admin.CreditStream;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.company.VerifyReason;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.service.dto.company.CompanyVerifyReasonFormDto;

@Repository("companyRepositoryMongo")
public class CompanyRepositoryMongo extends AbstractMongoSupport implements
		CompanyRepository {

	@Autowired
	private MongoOperations mongoTemplate;

	@Override
	public void saveCompany(Company company) {
		mongoTemplate.save(company);

	}

	@Override
	public void updateCompany(Company company) {
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
		this.mongoTemplate().updateFirst(query, update, Company.class);

	}

	@Override
	public Company findByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		return this.mongoTemplate().findOne(query, Company.class);
	}

	@Override
	public List<Company> findCompanyList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return mongoTemplate.find(query, Company.class);
	}

	@Override
	public long totalCompanyList(Map<String, Object> map) {
		Query query = new Query();
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, Company.class);
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
	public void removeCompany(Company company) {
		mongoTemplate.remove(company);
	}

	@Override
	public Company findByGuid(String guid) {
		Query query = new Query(Criteria.where("guid").is(guid));
		return mongoTemplate.findOne(query, Company.class);
	}

	@Override
	public Company findByCompanyName(String companyName) {
		Query query = new Query(Criteria.where("companyName").is(companyName));
		return mongoTemplate.findOne(query, Company.class);
	}

	@Override
	public Company findByCompanyEmail(String email) {
		Query query = new Query(Criteria.where("companyEmail").is(email));
		return this.mongoTemplate().findOne(query, Company.class);
	}

	@Override
	public void updateCompanyByState(Company com) {
		Update update = new Update();
		update.set("verifyState", com.getVerifyState());
		update.set("verifyPass", com.getVerifyPass());
		update.set("level", com.getLevel());
		Query query = createIDQuery(com.guid());
		this.mongoTemplate().updateFirst(query, update, Company.class);

	}

	@Override
	public long countCompany() {
		Query query = new Query(Criteria.where("guid").exists(true));
		return this.mongoTemplate().count(query, Company.class);
	}

	@Override
	public long totalCompanyCreditStreamByCompanyUUID(Map<String, Object> map,
			String companyUUID) {
		Query query = new Query(Criteria.where("companyUuid").is(companyUUID));
		addPagination(query, map);
		addCreditStreamQueryConditions(map, query);
		return this.mongoTemplate().count(query, CreditStream.class);
	}

	@Override
	public List<CreditStream> findCompanyCreditStreamListByCompanyUUID(
			Map<String, Object> map, String companyUUID) {
		Query query = new Query(Criteria.where("companyUuid").is(companyUUID));
		addPagination(query, map);
		addCreditStreamQueryConditions(map, query);
		return this.mongoTemplate().find(query, CreditStream.class);
	}

	public void addCreditStreamQueryConditions(Map<String, Object> map,
			Query query) {

		final String operator = (String) map.get("operator");

		if (StringUtils.isNotEmpty(operator)) {
			query.addCriteria(Criteria.where("operator").regex(
					"/*" + operator + "/*"));
		}

		final String start = (String) map.get("start");
		final String end = (String) map.get("end");
		Criteria criteria = new Criteria("createTime");

		if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end)) {
			Date startDate = DateUtils.getDate(start);
			String endTime = end + " 23:59:59";
			Date endDate = DateUtils.getDate(endTime, "yyyy-MM-dd HH:mm:ss");
			query.addCriteria(criteria.gte(startDate).lte(endDate));
		} else {
			if (StringUtils.isNotEmpty(start)) {
				Date startDate = DateUtils.getDate(start);
				query.addCriteria(criteria.gte(startDate));
			}

			if (StringUtils.isNotEmpty(end)) {
				String endTime = end + " 23:59:59";
				Date endDate = DateUtils
						.getDate(endTime, "yyyy-MM-dd HH:mm:ss");
				query.addCriteria(criteria.lte(endDate));
			}
		}

	}

	@Override
	public Company findLoginCompanyByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		return mongoTemplate.findOne(query, Company.class);
	}

	@Override
	public List<Member> findMemberListByGuid(String guid) {
		Query query = new Query(Criteria.where("companyGuid").is(guid));
		return mongoTemplate.find(query, Member.class);
	}

	@Override
	public void updateCompanyReason(Company dto) {
		Update update = new Update();
		update.set("verifyReason", dto.getVerifyReason());

		Query query = createIDQuery(dto.guid());
		this.mongoTemplate().updateFirst(query, update, Company.class);

	}

	@Override
	public long totalCompany() {
		Query query = new Query();
		return this.mongoTemplate().count(query, Company.class);
	}

	@Override
	public List<Company> findCompanyPassVerifyList(boolean b) {
		Query query = new Query(Criteria.where("verifyPass").is(b));
		return this.mongoTemplate().find(query, Company.class);
	}

	@Override
	public void updateCompanyCredit(Company company) {
		Update update = new Update();
		update.set("creditNumber", company.getCreditNumber());

		Query query = createIDQuery(company.guid());
		this.mongoTemplate().updateFirst(query, update, Company.class);
	}

}

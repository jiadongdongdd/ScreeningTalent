package com.idsmanager.xsifter.infrastructure.mongo.admin;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sun.swing.StringUIClientPropertyKey;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.admin.CreditRule;
import com.idsmanager.xsifter.domain.admin.CreditStream;
import com.idsmanager.xsifter.domain.admin.MemberLevel;
import com.idsmanager.xsifter.domain.admin.SystemAdminRepository;

@Repository("systemAdminRepository")
public class SystemAdminRepositoryMongo extends AbstractMongoSupport implements
		SystemAdminRepository {

	protected void addQueryConditions(Map<String, Object> map, Query query) {
		final String appName = (String) map.get("uuid");
		if (StringUtils.isNotEmpty(appName)) {
			query.addCriteria(Criteria.where("uuid").regex(
					"/*" + appName + "/*"));
		}

		final String operator = (String) map.get("operator");
		if (StringUtils.isNotEmpty(operator)) {
			query.addCriteria(Criteria.where("operator").regex(
					"/*" + operator + "/*"));
		}

		final String companyName = (String) map.get("companyName");
		if (StringUtils.isNotEmpty(companyName)) {
			query.addCriteria(Criteria.where("companyName").regex(
					"/*" + companyName + "/*"));
		}

	}

	protected Query addPagination(Query query, Map<String, Object> queryMap) {
		query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "createTime")));
		query.skip((Integer) queryMap.get("startIndex")).limit(
				(Integer) queryMap.get("perPageSize"));
		return query;
	}

	@Override
	public void saveMemberLevel(MemberLevel memberLevel) {
		this.mongoTemplate().save(memberLevel);
	}

	@Override
	public void deleteMemberLever(MemberLevel memberLevel) {
		this.mongoTemplate().remove(memberLevel);
	}

	@Override
	public MemberLevel findByUUID(String uuid) {
		// TODO Auto-generated method stub
		return findById(MemberLevel.class, uuid);
	}

	@Override
	public MemberLevel findMemberLevelByNumber(Integer number, String uuid) {
		Query query = new Query(Criteria.where("mixNumber").lte(number)
				.andOperator(Criteria.where("maxNumber").gte(number)));
		MemberLevel memberLevel = this.mongoTemplate().findOne(query,
				MemberLevel.class);
		if (null != memberLevel) {
			if (memberLevel.getUuid().equals(uuid)) {
				return null;
			} else {
				return memberLevel;
			}
		} else {
			return null;
		}
	}

	@Override
	public MemberLevel findByLevel(String level) {
		Query query = new Query(Criteria.where("level").is(level));
		return this.mongoTemplate().findOne(query, MemberLevel.class);
	}

	@Override
	public List<MemberLevel> findAllMemberLevelList() {
		// TODO Auto-generated method stub
		return this.mongoTemplate().findAll(MemberLevel.class);
	}

	@Override
	public void saveCreditRule(CreditRule creditRule) {
		this.mongoTemplate().save(creditRule);
	}

	@Override
	public void deleteCreditRule(CreditRule creditRule) {
		this.mongoTemplate().remove(creditRule);
	}

	@Override
	public CreditRule findCreditRuleByUUID(String uuid) {
		return this.mongoTemplate().findById(uuid, CreditRule.class);
	}

	@Override
	public CreditRule findCreditRuleByAction(String action) {
		Query query = new Query(Criteria.where("action").is(action));
		return this.mongoTemplate().findOne(query, CreditRule.class);
	}

	@Override
	public CreditRule findByRuleName(String ruleName) {
		Query query = new Query(Criteria.where("ruleName").is(ruleName));
		return this.mongoTemplate().findOne(query, CreditRule.class);
	}

	@Override
	public List<CreditRule> findAllCreditRulesList() {
		// TODO Auto-generated method stub
		return this.mongoTemplate().findAll(CreditRule.class);
	}

	@Override
	public void saveCreditStream(CreditStream creditStream) {
		this.mongoTemplate().save(creditStream);
	}

	@Override
	public List<CreditStream> findCreditStreamListByCompanyUUID(
			String companyUUID) {
		Query query = new Query(Criteria.where("campanyUuid").is(companyUUID));
		return this.mongoTemplate().find(query, CreditStream.class);
	}

	@Override
	public List<CreditStream> findCreditStreamListByCompanyUUID(
			Map<String, Object> map, String companyUUID) {
		Query query = new Query(Criteria.where("companyUuid").is(companyUUID));
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().find(query, CreditStream.class);
	}

	@Override
	public long totalCreditStreamByCompanyUUID(Map<String, Object> map,
			String companyUUID) {
		Query query = new Query(Criteria.where("companyUuid").is(companyUUID));
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, CreditStream.class);
	}

	@Override
	public List<CreditStream> findAllCreditStreamList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().find(query, CreditStream.class);
	}

	@Override
	public long countCreditStream(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, CreditStream.class);
	}

}

package com.idsmanager.xsifter.infrastructure.mongo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.log.Log;
import com.idsmanager.xsifter.domain.log.LogRepository;
import com.idsmanager.xsifter.domain.log.OperateType;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

@Repository("logRepositoryMongo")
public class LogRepositoryMongo extends AbstractMongoSupport implements
		LogRepository {

	@Override
	public void saveLog(Log log) {
		this.mongoTemplate().save(log);
	}

	@Override
	public long getVisitSumByMonthly(Date date) {
		Query query = new Query();
		addVisitQueryConditions(query, date);
		return this.mongoTemplate().count(query, Log.class);
	}

	private void addVisitQueryConditions(Query query, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date todayStart = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		Date endStart = calendar.getTime();
		Criteria criteria = Criteria.where("createTime").gte(todayStart)
				.lte(endStart);
		query.addCriteria(criteria);
		query.addCriteria(Criteria.where("operateType").is(
				OperateType.LOGIN_SUCCESS));

	}

	@Override
	public long getEnterpriseVisitSumByMonthly(String currentUserGuid, Date date) {
		Query query = new Query();
		addEnterpriseQueryConditions(query, currentUserGuid, date);
		return this.mongoTemplate().count(query, Log.class);
	}

	private void addEnterpriseQueryConditions(Query query,
			String currentUserGuid, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date todayStart = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		Date endStart = calendar.getTime();
		Criteria criteria = Criteria.where("createTime").gte(todayStart)
				.lte(endStart);
		query.addCriteria(criteria);
		query.addCriteria(Criteria.where("operatorId").is(currentUserGuid));
		query.addCriteria(Criteria.where("operateType").is(
				OperateType.LOGIN_SUCCESS));
	}

	@Override
	public List<Log> findMemberLogs(Map<String, Object> map, String uuid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("entityUuid").is(uuid)
				.and("operatorId").is(SecurityUtils.currentUserGuid()));
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().find(query, Log.class);
	}

	private Query addQueryConditions(Map<String, Object> map, Query query) {
		final String operateDetail = (String) map.get("operateDetail");
		if (StringUtils.isNotEmpty(operateDetail)) {
			query.addCriteria(Criteria.where("operateDetail").regex(
					"/*" + operateDetail + "/*"));
		}
		final String operator = (String) map.get("operator");
		if (StringUtils.isNotEmpty(operator)) {
			query.addCriteria(Criteria.where("operator").regex(
					"/*" + operator + "/*"));
		}

		query.addCriteria(Criteria.where("operateType").ne(
				OperateType.LOGIN_SUCCESS));

		return query;

	}

	@Override
	public long totalMemberLogs(Map<String, Object> map, String uuid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("entityUuid").is(uuid)
				.and("operatorId").is(SecurityUtils.currentUserGuid()));
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, Log.class);
	}

	@Override
	public List<Log> findAdminMemberLogs(Map<String, Object> map, String uuid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("entityUuid").is(uuid));
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().find(query, Log.class);
	}

	@Override
	public long totalAdminMemberLogs(Map<String, Object> map, String uuid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("entityUuid").is(uuid));
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, Log.class);
	}

	@Override
	public List<Log> findEnterpriseLogs(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addEnterAllLogsConditions(query, map);
		return this.mongoTemplate().find(query, Log.class);
	}

	private void addEnterAllLogsConditions(Query query, Map<String, Object> map) {
		final String operateDetail = (String) map.get("operateDetail");
		if (StringUtils.isNotEmpty(operateDetail)) {
			query.addCriteria(Criteria.where("operateDetail").regex(
					"/*" + operateDetail + "/*"));
		}

		final String operator = (String) map.get("operator");
		if (StringUtils.isNotEmpty(operator)) {
			query.addCriteria(Criteria.where("operator").regex(
					"/*" + operator + "/*"));
		}

		final String entityName = (String) map.get("chName");
		if (StringUtils.isNotEmpty(entityName)) {
			query.addCriteria(Criteria.where("entityName").regex(
					"/*" + entityName + "/*"));
		}

		query.addCriteria(Criteria.where("operatorId").is(
				SecurityUtils.currentUserGuid()));
	}

	@Override
	public long totalEnterpriseLogs(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addEnterAllLogsConditions(query, map);
		return this.mongoTemplate().count(query, Log.class);
	}

	@Override
	public List<Log> findAdminLogs(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addAdminAllLogsConditions(query, map);
		return this.mongoTemplate().find(query, Log.class);
	}

	private void addAdminAllLogsConditions(Query query, Map<String, Object> map) {
		final String operateDetail = (String) map.get("operateDetail");
		if (StringUtils.isNotEmpty(operateDetail)) {
			query.addCriteria(Criteria.where("operateDetail").regex(
					"/*" + operateDetail + "/*"));
		}

		final String operator = (String) map.get("operator");
		if (StringUtils.isNotEmpty(operator)) {
			query.addCriteria(Criteria.where("operator").regex(
					"/*" + operator + "/*"));
		}

		final String entityName = (String) map.get("chName");
		if (StringUtils.isNotEmpty(entityName)) {
			query.addCriteria(Criteria.where("entityName").regex(
					"/*" + entityName + "/*"));
		}
	}

	@Override
	public long totalAdminLogs(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addAdminAllLogsConditions(query, map);
		return this.mongoTemplate().count(query, Log.class);
	}

	@Override
	public long findMyTotalStationQuery() {
		Query query = new Query(Criteria.where("operateType")
				.is(OperateType.TOTAL_STATION_QUERY_MEMBER).and("operatorId")
				.is(SecurityUtils.currentUserGuid()));
		return this.mongoTemplate().count(query, Log.class);
	}

	@Override
	public List<Log> findQueryHistoryList(Map<String, Object> queryMap) {
		Query query = new Query();
		addPagination(query, queryMap);
		addQueryHistoryConditions(query, queryMap);
		return this.mongoTemplate().find(query, Log.class);
	}

	private void addQueryHistoryConditions(Query query,
			Map<String, Object> queryMap) {

		final String createTimeStr = (String) queryMap.get("createTime");
		if (StringUtils.isNotEmpty(createTimeStr)) {
			Date createDateStart = DateUtils.getDate(createTimeStr);
			Date createDateEnd = new Date(createDateStart.getTime() + 24 * 60
					* 60 * 1000 - 1);
			Criteria criteria = Criteria.where("createTime")
					.gte(createDateStart).lte(createDateEnd);
			query.addCriteria(criteria);

		}

		final String chName = (String) queryMap.get("chName");
		if (StringUtils.isNotEmpty(chName)) {
			query.addCriteria(Criteria.where("entityName").regex(
					"/*" + chName + "/*"));
		}

		final String companyName = (String) queryMap.get("companyName");
		if (StringUtils.isNotEmpty(companyName)) {
			query.addCriteria(Criteria.where("entity.companyName").regex(
					"/*" + companyName + "/*"));
		}

		query.addCriteria(Criteria.where("operatorId")
				.is(SecurityUtils.currentUserGuid()).and("operateType")
				.is(OperateType.TOTAL_STATION_QUERY_MEMBER));

	}

	@Override
	public long totalQueryHistoryList(Map<String, Object> queryMap) {
		Query query = new Query();
		addPagination(query, queryMap);
		addQueryHistoryConditions(query, queryMap);
		return this.mongoTemplate().count(query, Log.class);
	}

	@Override
	public List<Log> findExportLogList(Map<String, Object> map) {
		Query query = addExportQueryConditions(new Query(), map);
		return this.mongoTemplate().find(query, Log.class);
	}

	private Query addExportQueryConditions(Query query, Map<String, Object> map) {
		String uuid = (String) map.get("userUuid");
		if (StringUtils.isNotEmpty(uuid)) {
			query.addCriteria(Criteria.where("operatorId").is(uuid));
		}

		String operator = (String) map.get("operator");
		if (StringUtils.isNotEmpty(operator) && !"null".equals(operator)) {
			query.addCriteria(Criteria.where("operator").regex(
					"/*" + operator + "/*"));
		}

		String operateDetail = (String) map.get("operateDetail");
		if (StringUtils.isNotEmpty(operateDetail)
				&& !"null".equals(operateDetail)) {
			query.addCriteria(Criteria.where("operateDetail").regex(
					"/*" + operateDetail + "/*"));
		}
		return query;

	}

}

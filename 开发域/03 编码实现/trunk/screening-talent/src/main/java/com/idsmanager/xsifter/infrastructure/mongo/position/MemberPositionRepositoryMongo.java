package com.idsmanager.xsifter.infrastructure.mongo.position;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;
import com.idsmanager.xsifter.service.dto.position.PositionFormDto;

@Repository("memberPositionRepositoryMongo")
public class MemberPositionRepositoryMongo extends AbstractMongoSupport
		implements MemberPositionRepository {

	@Override
	public long totalPositionList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, MemberPosition.class);
	}

	private Query addQueryConditions(Map<String, Object> map, Query query) {
		final String positionName = (String) map.get("positionName");
		if (StringUtils.isNotEmpty(positionName)) {
			query.addCriteria(Criteria.where("positionName").regex(
					"/*" + positionName + "/*"));
		}

		final String industry = (String) map.get("industry");
		if (StringUtils.isNotEmpty(industry)) {
			query.addCriteria(Criteria.where("industry").regex(
					"/*" + industry + "/*"));
		}

		final String level = (String) map.get("level");
		if (StringUtils.isNotEmpty(level)) {
			query.addCriteria(Criteria.where("level")
					.regex("/*" + level + "/*"));
		}

		return query;

	}

	@Override
	public List<MemberPosition> findPositionList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().find(query, MemberPosition.class);
	}

	@Override
	public MemberPosition findPostionByUuid(String uuid) {
		Query query = new Query(Criteria.where("uuid").is(uuid));
		return this.mongoTemplate().findOne(query, MemberPosition.class);
	}

	@Override
	public void savePosition(MemberPosition position) {
		this.mongoTemplate().save(position);
	}

	@Override
	public void deletePosition(MemberPosition position) {

		this.mongoTemplate().remove(position);

	}

	@Override
	public List<MemberPosition> findPositionList() {
		return this.mongoTemplate().find(new Query(), MemberPosition.class);
	}

	@Override
	public long countPositions() {
		return this.mongoTemplate().count(new Query(), MemberPosition.class);
	}

	@Override
	public MemberPosition findPositionByAllOptions(PositionFormDto formDto) {
		Query query = new Query();
		addCondtions(query, formDto);
		return this.mongoTemplate().findOne(query, MemberPosition.class);
	}

	private Query addCondtions(Query query, PositionFormDto formDto) {
		final String industry = formDto.getIndustry();
		if (StringUtils.isNotEmpty(industry)) {
			query.addCriteria(Criteria.where("industry").is(industry));
		}

		final String level = formDto.getLevel();
		if (StringUtils.isNotEmpty(level)) {
			query.addCriteria(Criteria.where("level").is(level));
		}

		final String positionName = formDto.getPositionName();
		if (StringUtils.isNotEmpty(positionName)) {
			query.addCriteria(Criteria.where("positionName").is(positionName));
		}
		return query;
	}

}

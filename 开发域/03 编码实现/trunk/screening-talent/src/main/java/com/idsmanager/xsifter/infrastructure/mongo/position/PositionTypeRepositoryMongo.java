package com.idsmanager.xsifter.infrastructure.mongo.position;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.position.PositionType;
import com.idsmanager.xsifter.domain.position.PositionTypeRepository;

@Repository("positionTypeRepositoryMongo")
public class PositionTypeRepositoryMongo extends AbstractMongoSupport implements
		PositionTypeRepository {

	@Override
	public long totalPositionTypeList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, PositionType.class);
	}

	private Query addQueryConditions(Map<String, Object> map, Query query) {

		final String typeName = (String) map.get("typeName");

		if (StringUtils.isNotEmpty(typeName)) {
			query.addCriteria(Criteria.where("typeName").is(typeName));
		}
		return query;

	}

	@Override
	public List<PositionType> findPositionTypeList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().find(query, PositionType.class);
	}

	@Override
	public PositionType findPositionTypeByUuid(String uuid) {
		Query query = new Query(Criteria.where("uuid").is(uuid));
		return this.mongoTemplate().findOne(query, PositionType.class);
	}

	@Override
	public void saveType(PositionType type) {
		this.mongoTemplate().save(type);

	}

	@Override
	public void deleteType(PositionType type) {
		this.mongoTemplate().remove(type);

	}

}

package com.idsmanager.xsifter.infrastructure.mongo;
import java.util.List;
import java.util.Map;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.timer.MemberExcep;
import com.idsmanager.xsifter.domain.timer.MemberExcepRepository;

@Repository("memberExcepRepository")
public class MemberExcepRepositoryMongo extends AbstractMongoSupport implements MemberExcepRepository {

	@Override
	public long totalMemberExcepList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, MemberExcep.class);
	}
	
	private void addQueryConditions(Map<String, Object> map, Query query) {
		final String title = (String) map.get("");
	}

	@Override
	public List<MemberExcep> findMemberExcepList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().find(query, MemberExcep.class);
	}

	@Override
	public void saveMemberExcep(MemberExcep news) {
		 this.mongoTemplate().save(news);

	}

	@Override
	public MemberExcep findMemberExcepByUuid(String uuid) {
		 Query query = new Query(Criteria.where("uuid").is(uuid));
		return this.mongoTemplate().findOne(query, MemberExcep.class);
	}

	@Override
	public void deleteMemberExcep(MemberExcep news) {
		this.mongoTemplate().remove(news);

	}

	@Override
	public MemberExcep findMemberExcepByIDNember(String idNumber) {
		Query query = new Query(Criteria.where("idNumber").is(idNumber));
		return this.mongoTemplate().findOne(query, MemberExcep.class);
	}

}

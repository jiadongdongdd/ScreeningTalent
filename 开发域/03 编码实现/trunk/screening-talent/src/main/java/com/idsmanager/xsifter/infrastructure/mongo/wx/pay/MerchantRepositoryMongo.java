package com.idsmanager.xsifter.infrastructure.mongo.wx.pay;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.wx.pay.Merchant;
import com.idsmanager.xsifter.domain.wx.pay.MerchantRepository;

@Repository("merchantRepositoryMongo")
public class MerchantRepositoryMongo extends AbstractMongoSupport implements
		MerchantRepository {

	@Override
	public long totalMerchantList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, Merchant.class);
	}

	private void addQueryConditions(Map<String, Object> map, Query query) {
		final String appId = (String) map.get("appId");
		if (StringUtils.isNotEmpty(appId)) {
			query.addCriteria(Criteria.where("appId")
					.regex("/*" + appId + "/*"));
		}

		final String mchId = (String) map.get("mchId");
		if (StringUtils.isNotEmpty(mchId)) {
			query.addCriteria(Criteria.where("mchId")
					.regex("/*" + mchId + "/*"));
		}

	}

	@Override
	public List<Merchant> findMerchantList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().find(query, Merchant.class);
	}

	@Override
	public void saveMerchant(Merchant merchant) {
		this.mongoTemplate().save(merchant);
	}

	@Override
	public Merchant findMerchantByUUID(String uuid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("uuid").is(uuid));
		return this.mongoTemplate().findOne(query, Merchant.class);
	}

	@Override
	public void deleteMerchant(Merchant merchant) {
		this.mongoTemplate().remove(merchant);
	}

	@Override
	public List<Merchant> findMerchantList() {
		Query query = new Query();
		return this.mongoTemplate().find(query, Merchant.class);
	}

}

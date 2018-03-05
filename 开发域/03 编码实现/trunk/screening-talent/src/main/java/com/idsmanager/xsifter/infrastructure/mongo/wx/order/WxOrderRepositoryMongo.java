package com.idsmanager.xsifter.infrastructure.mongo.wx.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.domain.wx.order.WxOrder;
import com.idsmanager.xsifter.domain.wx.order.WxOrderRepository;

@Repository("wxOrderRepositoryMongo")
public class WxOrderRepositoryMongo extends AbstractMongoSupport implements
		WxOrderRepository {

	@Override
	public WxOrder findOrderByOrderNo(String orderNo) {
		Query query = new Query(Criteria.where("orderNo").is(orderNo));
		return this.mongoTemplate().findOne(query, WxOrder.class);
	}

	@Override
	public void saveWxOrder(WxOrder order) {
		this.mongoTemplate().save(order);

	}

	@Override
	public long totalWxOrderList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, WxOrder.class);
	}

	@Override
	public List<WxOrder> findOrderList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().find(query, WxOrder.class);
	}

	public void addQueryConditions(Map<String, Object> map, Query query) {

		final String customerName = (String) map.get("customerName");
		if (StringUtils.isNotEmpty(customerName)) {
			query.addCriteria(Criteria.where("customerName").regex(
					"/*" + customerName + "/*"));
		}

		final String start = (String) map.get("start");
		final String end = (String) map.get("end");
		Criteria criteria = new Criteria("orderDate");

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

		final Privilege role = (Privilege) map.get("role");

		if (!Privilege.ADMIN.equals(role)) {
			query.addCriteria(Criteria.where("customerId")
					.is(SecurityUtils.currentUserGuid()).and("paySuccess")
					.is(true));
		}

	}
}

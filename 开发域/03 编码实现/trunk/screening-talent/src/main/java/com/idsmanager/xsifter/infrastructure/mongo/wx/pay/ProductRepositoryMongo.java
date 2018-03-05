package com.idsmanager.xsifter.infrastructure.mongo.wx.pay;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.wx.product.Product;
import com.idsmanager.xsifter.domain.wx.product.ProductRepository;

@Repository("productRepositoryMongo")
public class ProductRepositoryMongo extends AbstractMongoSupport implements
		ProductRepository {

	@Override
	public long totalProductList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, Product.class);
	}

	private void addQueryConditions(Map<String, Object> map, Query query) {
		final String productName = (String) map.get("productName");
		if (StringUtils.isNotEmpty(productName)) {
			query.addCriteria(Criteria.where("productName").regex(
					"/*" + productName + "/*"));
		}

	}

	@Override
	public List<Product> findProductList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditions(map, query);
		return this.mongoTemplate().find(query, Product.class);
	}

	@Override
	public void saveProduct(Product product) {
		this.mongoTemplate().save(product);
	}

	@Override
	public Product findProductByUUID(String product_id) {
		Query query = new Query(Criteria.where("uuid").is(product_id));
		return this.mongoTemplate().findOne(query, Product.class);
	}

	@Override
	public void deleteProduct(Product product) {
		this.mongoTemplate().remove(product);
	}

}

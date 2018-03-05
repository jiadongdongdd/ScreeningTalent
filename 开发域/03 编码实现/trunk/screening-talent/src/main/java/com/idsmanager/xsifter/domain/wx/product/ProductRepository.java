package com.idsmanager.xsifter.domain.wx.product;

import java.util.List;
import java.util.Map;

import com.idsmanager.xsifter.domain.Repository;

public interface ProductRepository extends Repository{

	long totalProductList(Map<String, Object> map);

	List<Product> findProductList(Map<String, Object> map);

	void saveProduct(Product product);

	Product findProductByUUID(String product_id);

	void deleteProduct(Product product);

}

package com.idsmanager.xsifter.service.dto.wx.pay;

import java.util.Map;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.wx.product.Product;

public class ProductPaginated extends DefaultPaginated<Product> {

	private String productName;

	@Override
	public Map<String, Object> queryMap() {
		final Map<String, Object> map = super.queryMap();
		map.put("productName", productName);
		return map;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}

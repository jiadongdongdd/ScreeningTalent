package com.idsmanager.xsifter.service;

import com.idsmanager.xsifter.service.dto.wx.pay.ProductFormDto;
import com.idsmanager.xsifter.service.dto.wx.pay.ProductPaginated;

public interface ProductService {

	ProductPaginated loadMyProduct(ProductPaginated paginated);

	void createProduct(ProductFormDto formDto);

	ProductFormDto loadMyProductFormDto(String uuid);

	void updateProduct(ProductFormDto formDto);

	void deleteProduct(String uuid);

}

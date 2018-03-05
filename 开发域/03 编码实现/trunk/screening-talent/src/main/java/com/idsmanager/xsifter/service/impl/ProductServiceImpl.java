package com.idsmanager.xsifter.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.xsifter.domain.wx.product.Product;
import com.idsmanager.xsifter.domain.wx.product.ProductRepository;
import com.idsmanager.xsifter.service.ProductService;
import com.idsmanager.xsifter.service.business.product.MyProductRemover;
import com.idsmanager.xsifter.service.business.product.MyProductSaver;
import com.idsmanager.xsifter.service.dto.wx.pay.ProductFormDto;
import com.idsmanager.xsifter.service.dto.wx.pay.ProductPaginated;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductPaginated loadMyProduct(ProductPaginated paginated) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<Product>() {

			@Override
			public long loadTotalSize() {
				return productRepository.totalProductList(map);
			}

			@Override
			public List<Product> loadList() {
				return productRepository.findProductList(map);
			}
		});
	}

	@Override
	public void createProduct(ProductFormDto formDto) {
		MyProductSaver saver = new MyProductSaver(formDto);
		saver.save();
	}

	@Override
	public ProductFormDto loadMyProductFormDto(String uuid) {
		Product product = productRepository.findProductByUUID(uuid);
		return product == null ? new ProductFormDto() : new ProductFormDto(
				product);
	}

	@Override
	public void updateProduct(ProductFormDto formDto) {
		MyProductSaver saver = new MyProductSaver(formDto);
		saver.save();
	}

	@Override
	public void deleteProduct(String uuid) {
		MyProductRemover remover = new MyProductRemover(uuid);
		remover.remove();
	}

}

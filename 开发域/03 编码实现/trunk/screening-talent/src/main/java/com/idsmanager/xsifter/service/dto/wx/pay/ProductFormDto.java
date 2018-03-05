package com.idsmanager.xsifter.service.dto.wx.pay;

import java.io.Serializable;

import com.idsmanager.xsifter.domain.wx.product.Product;

public class ProductFormDto implements Serializable {

	private static final long serialVersionUID = 3892009810866893506L;

	private String uuid;
	private String productName;// 商品名
	private String productBody;// 商品简单描述
	private String productDetail;// 商品详细描述
	private String productPrice;// 商品价格
	private String credit;

	public ProductFormDto() {
		super();
	}

	public ProductFormDto(Product product) {
		this.uuid = product.getUuid();
		this.productName = product.getProductName();
		this.productBody = product.getProductBody();
		this.productDetail = product.getProductDetail();
		this.productPrice = product.getProductPrice();
		this.credit = product.getCredit();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBody() {
		return productBody;
	}

	public void setProductBody(String productBody) {
		this.productBody = productBody;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public Product create() {
		return new Product().setCredit(credit).setProductBody(productBody)
				.setProductDetail(productDetail).setProductName(productName)
				.setProductPrice(productPrice);
	}

	public Product update(Product product) {
		return product.setCredit(credit).setProductBody(productBody)
				.setProductDetail(productDetail).setProductName(productName)
				.setProductPrice(productPrice);
	}

}

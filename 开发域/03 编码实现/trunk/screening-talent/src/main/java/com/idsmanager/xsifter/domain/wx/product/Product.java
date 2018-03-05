package com.idsmanager.xsifter.domain.wx.product;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.xsifter.domain.AbstractDomain;

@Document(collection = "Product")
public class Product extends AbstractDomain {

	private static final long serialVersionUID = 609814306072189498L;

	private String productName;// 商品名
	private String productBody;// 商品简单描述
	private String productDetail;// 商品详细描述
	private String productPrice;// 商品价格
	private String credit;// 积分

	public Product() {
		super();
	}

	public String getProductName() {
		return productName;
	}

	public Product setProductName(String productName) {
		this.productName = productName;
		return this;
	}

	public String getProductBody() {
		return productBody;
	}

	public Product setProductBody(String productBody) {
		this.productBody = productBody;
		return this;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public Product setProductDetail(String productDetail) {
		this.productDetail = productDetail;
		return this;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public Product setProductPrice(String productPrice) {
		this.productPrice = productPrice;
		return this;
	}

	public String getCredit() {
		return credit;
	}

	public Product setCredit(String credit) {
		this.credit = credit;
		return this;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productBody="
				+ productBody + ", productDetail=" + productDetail
				+ ", productPrice=" + productPrice + ", credit=" + credit + "]";
	}

}

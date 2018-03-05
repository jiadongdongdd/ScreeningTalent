package com.idsmanager.xsifter.domain.wx.order;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.AbstractDomain;

@Document(collection = "WxOrder")
public class WxOrder extends AbstractDomain {

	private static final long serialVersionUID = -3724254316044639253L;

	private String orderNo;// 订单号
	private Date orderDate;// 订单日期
	private String productId;// 商品ID
	private String customerId;// 订单人ID
	private String customerName;// 订单人
	private boolean paySuccess;// 订单支付状态

	private int beforeCredit;// 充值前积分
	private int afterCredit;// 充值后积分
	private String price;// 金额
	private String detail;// 详情

	public WxOrder() {
		super();
	}

	public String getOrderNo() {
		return orderNo;
	}

	public WxOrder setOrderNo(String orderNo) {
		this.orderNo = orderNo;
		return this;
	}

	public String getOrderDate() {
		return DateUtils.toDateText(orderDate, "yyyy-MM-dd HH:mm:ss");
	}

	public WxOrder setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
		return this;
	}

	public String getProductId() {
		return productId;
	}

	public WxOrder setProductId(String productId) {
		this.productId = productId;
		return this;
	}

	public String getCustomerId() {
		return customerId;
	}

	public WxOrder setCustomerId(String customerId) {
		this.customerId = customerId;
		return this;
	}

	public String getCustomerName() {
		return customerName;
	}

	public WxOrder setCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}

	public boolean isPaySuccess() {
		return paySuccess;
	}

	public WxOrder setPaySuccess(boolean paySuccess) {
		this.paySuccess = paySuccess;
		return this;
	}

	public int getBeforeCredit() {
		return beforeCredit;
	}

	public WxOrder setBeforeCredit(int beforeCredit) {
		this.beforeCredit = beforeCredit;
		return this;
	}

	public int getAfterCredit() {
		return afterCredit;
	}

	public WxOrder setAfterCredit(int afterCredit) {
		this.afterCredit = afterCredit;
		return this;
	}

	public String getPrice() {
		return price;
	}

	public WxOrder setPrice(String price) {
		this.price = price;
		return this;
	}

	public String getDetail() {
		return detail;
	}

	public WxOrder setDetail(String detail) {
		this.detail = detail;
		return this;
	}

	@Override
	public String toString() {
		return "WxOrder [orderNo=" + orderNo + ", orderDate=" + orderDate
				+ ", productId=" + productId + ", customerId=" + customerId
				+ ", customerName=" + customerName + ", paySuccess="
				+ paySuccess + ", beforeCredit=" + beforeCredit
				+ ", afterCredit=" + afterCredit + ", price=" + price
				+ ", detail=" + detail + "]";
	}

}

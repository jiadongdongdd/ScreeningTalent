package com.idsmanager.xsifter.domain.wx.pay;

import java.io.Serializable;

public class WxPayCallbackNotify implements Serializable {

	private static final long serialVersionUID = 4576426194626765199L;

	private String return_code;// 返回状态码
	private String return_msg;// 返回信息

	// return_code 为 "SUCCESS"时返回
	private String appid;// 微信分配的公众账号ID
	private String mch_id;// 微信支付分配的商户号
	private String nonce_str;// 随机字符串
	private String sign;// 签名
	private String result_code;// 业务结果
	private String err_code;// 错误代码
	private String err_code_des;// 错误代码描述

	// 以下字段在return_code 和result_code都为SUCCESS的时候有返回
	private String device_info;// 设备号
	private String openid;// 用户标识
	private String is_subscribe;// 是否关注公众账号
	private String trade_type;// 交易类型
	private String trade_state;// 交易状态
	private String bank_type;// 付款银行
	private String total_fee;// 总金额
	private String fee_type;// 货币种类
	private String transaction_id;// 微信支付订单号
	private String out_trade_no;// 商户订单号
	private String attach;// 附加数据
	private String time_end;// 支付完成时间
	private String trade_state_desc;// 交易状态描述
	private String cash_fee;// 现金

	private boolean paySuccess;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public boolean isPaySuccess() {
		return paySuccess;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public void setPaySuccess(boolean paySuccess) {
		this.paySuccess = paySuccess;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public String getTrade_state_desc() {
		return trade_state_desc;
	}

	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
	}

	@Override
	public String toString() {
		return "WxPayCallbackNotify [appid=" + appid + ", attach=" + attach
				+ ", bank_type=" + bank_type + ", cash_fee=" + cash_fee
				+ ", is_subscribe=" + is_subscribe + ", mch_id=" + mch_id
				+ ", nonce_str=" + nonce_str + ", openid=" + openid
				+ ", out_trade_no=" + out_trade_no + ", result_code="
				+ result_code + ", return_code=" + return_code + ", sign="
				+ sign + ", time_end=" + time_end + ", total_fee=" + total_fee
				+ ", fee_type=" + fee_type + ", trade_type=" + trade_type
				+ ", transaction_id=" + transaction_id + ", paySuccess="
				+ paySuccess + "]";
	}

}

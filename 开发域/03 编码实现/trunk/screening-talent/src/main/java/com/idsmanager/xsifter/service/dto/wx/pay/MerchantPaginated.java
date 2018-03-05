package com.idsmanager.xsifter.service.dto.wx.pay;

import java.util.Map;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.wx.pay.Merchant;

public class MerchantPaginated extends DefaultPaginated<Merchant> {

	private String appId;// 公众账号ID
	private String mchId;// 商户号

	@Override
	public Map<String, Object> queryMap() {
		
		final Map<String, Object> map = super.queryMap();
		map.put("appId", appId);
		map.put("mchId", mchId);
		return map;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

}

package com.idsmanager.xsifter.service.dto.wx.order;

import java.util.Map;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.wx.order.WxOrder;

public class WxOrderPaginated extends DefaultPaginated<WxOrder> {

	private String companyName;// 公司名

	private String start;// 起始时间
	private String end;// 截止时间

	@Override
	public Map<String, Object> queryMap() {

		final Map<String, Object> map = super.queryMap();
		map.put("start", start);
		map.put("end", end);
		map.put("customerName", companyName);
		return map;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}

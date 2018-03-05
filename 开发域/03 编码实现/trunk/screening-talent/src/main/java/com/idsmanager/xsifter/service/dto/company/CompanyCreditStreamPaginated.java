package com.idsmanager.xsifter.service.dto.company;

import java.util.Map;

import com.idsmanager.commons.utils.MatchUtils;
import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.admin.CreditStream;

public class CompanyCreditStreamPaginated extends
		DefaultPaginated<CreditStream> {

	private String operator;

	private String start;

	private String end;

	public CompanyCreditStreamPaginated() {
	}

	@Override
	public Map<String, Object> queryMap() {
		final Map<String, Object> map = super.queryMap();
		if (MatchUtils.isDate(start)) {
			map.put("start", start);
		}

		if (MatchUtils.isDate(end)) {
			map.put("end", end);
		}
		map.put("operator", operator);
		return map;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

}

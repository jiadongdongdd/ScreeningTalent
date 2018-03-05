package com.idsmanager.xsifter.service.dto.admin;

import java.util.Map;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.admin.CreditStream;

public class CreditStreamPaginated extends DefaultPaginated<CreditStream> {

	private String operator;

	private String companyName;

	public CreditStreamPaginated() {

	}

	@Override
	public Map<String, Object> queryMap() {
		final Map<String, Object> map = super.queryMap();
		map.put("operator", operator);
		map.put("companyName", companyName);
		return map;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}

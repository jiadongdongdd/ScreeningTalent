package com.idsmanager.xsifter.service.dto.memberexcep;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.timer.MemberExcep;

import java.util.Map;

/**
 * 
 * @author andy
 * 
 */
public class MemberExcepPaginated extends DefaultPaginated<MemberExcep> {

	private String idNumber;


	public MemberExcepPaginated() {
	}

	@Override
	public Map<String, Object> queryMap() {
		final Map<String, Object> map = super.queryMap();
		map.put("idNumber", "idNumber");
		return map;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

}

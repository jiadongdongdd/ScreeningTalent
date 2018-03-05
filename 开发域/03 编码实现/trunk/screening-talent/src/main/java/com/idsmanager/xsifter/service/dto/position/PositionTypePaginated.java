package com.idsmanager.xsifter.service.dto.position;

import java.util.Map;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.position.PositionType;

public class PositionTypePaginated extends DefaultPaginated<PositionType> {

	private String typeName;

	@Override
	public Map<String, Object> queryMap() {
		final Map<String, Object> map = super.queryMap();
		map.put("typeName", typeName);
		return map;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}

package com.idsmanager.xsifter.service.dto.menber;

import java.util.Map;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.member.Worked;

public class WorkedQueryPaginated extends DefaultPaginated<Worked> {
	@Override
	public Map<String, Object> queryMap() {
		return super.queryMap();
	}

}

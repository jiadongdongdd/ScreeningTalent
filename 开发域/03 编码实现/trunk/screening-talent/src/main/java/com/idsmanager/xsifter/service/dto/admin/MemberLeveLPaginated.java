package com.idsmanager.xsifter.service.dto.admin;

import java.util.Map;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.admin.MemberLevel;

public class MemberLeveLPaginated extends DefaultPaginated<MemberLevel>{
	
	private String level;
	
	public MemberLeveLPaginated(){
		
	}
	
	@Override
	public Map<String, Object> queryMap() {
		final Map<String, Object> map = super.queryMap();
        map.put("level", level);
        return map;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
}

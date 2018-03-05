package com.idsmanager.xsifter.service.dto.position;

import java.util.Map;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.position.MemberPosition;

public class PositionPaginated extends DefaultPaginated<MemberPosition> {

	private String positionName;

	private String industry;

	private String level;

	@Override
	public Map<String, Object> queryMap() {
		final Map<String, Object> map = super.queryMap();
		map.put("positionName", positionName);
		map.put("industry", industry);
		map.put("level", level);
		return map;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}

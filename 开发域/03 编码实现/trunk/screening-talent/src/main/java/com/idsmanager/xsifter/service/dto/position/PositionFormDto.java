package com.idsmanager.xsifter.service.dto.position;

import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.PositionIndustry;
import com.idsmanager.xsifter.service.dto.AbstractDto;

public class PositionFormDto extends AbstractDto {

	private static final long serialVersionUID = 1229780768749616587L;

	private String uuid;

	private String positionName;

	private String industry;

	private String level;

	public PositionFormDto() {
		super();
	}

	public PositionFormDto(MemberPosition position) {
		this.uuid = position.getUuid();
		this.positionName = position.getPositionName();
		this.industry = position.getIndustry();
		this.level = position.getLevel();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public PositionIndustry[] getIndustries() {
		return PositionIndustry.values();
	}

	public MemberPosition create() {
		return new MemberPosition().setPositionName(positionName)
				.setIndustry(industry).setLevel(level);
	}

	public MemberPosition update(MemberPosition position) {
		return position.setPositionName(positionName).setIndustry(industry)
				.setLevel(level);
	}

}

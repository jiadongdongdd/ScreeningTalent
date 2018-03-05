package com.idsmanager.xsifter.domain.position;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.xsifter.domain.AbstractDomain;

//职位
@Document(collection = "MemberPosition")
public class MemberPosition extends AbstractDomain {

	private static final long serialVersionUID = 2308965976993685931L;

	private String positionName;// 职位名称
	private String positionTypeUuid;// 职位行业uuid
	private String industry;// 职位行业
	private String level;// 职位级别

	public MemberPosition() {
		super();
	}

	public String getPositionName() {
		return positionName;
	}

	public MemberPosition setPositionName(String positionName) {
		this.positionName = positionName;
		return this;
	}

	public String getPositionTypeUuid() {
		return positionTypeUuid;
	}

	public MemberPosition setPositionTypeUuid(String positionTypeUuid) {
		this.positionTypeUuid = positionTypeUuid;
		return this;
	}

	public String getIndustry() {
		return industry;
	}

	public MemberPosition setIndustry(String industry) {
		this.industry = industry;
		return this;
	}

	public String getLevel() {
		return level;
	}

	public MemberPosition setLevel(String level) {
		this.level = level;
		return this;
	}

}

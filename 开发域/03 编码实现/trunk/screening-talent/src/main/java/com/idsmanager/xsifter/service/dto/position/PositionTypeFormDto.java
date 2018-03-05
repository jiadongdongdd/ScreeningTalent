package com.idsmanager.xsifter.service.dto.position;

import java.util.List;

import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.PositionType;
import com.idsmanager.xsifter.service.dto.AbstractDto;

public class PositionTypeFormDto extends AbstractDto {

	private static final long serialVersionUID = 584651598172302779L;

	private String uuid;

	private String typeName;// 行业类别名称

	private List<MemberPosition> memberPositionList;// 类别下职位

	public PositionTypeFormDto() {
		super();
	}

	public PositionTypeFormDto(PositionType positionType) {

		this.uuid = positionType.getUuid();
		this.typeName = positionType.getTypeName();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<MemberPosition> getMemberPositionList() {
		return memberPositionList;
	}

	public void setMemberPositionList(List<MemberPosition> memberPositionList) {
		this.memberPositionList = memberPositionList;
	}

	public PositionType update(PositionType positionType) {
		return positionType.setTypeName(typeName);
	}

	public PositionType create() {
		return new PositionType().setTypeName(typeName);
	}

}

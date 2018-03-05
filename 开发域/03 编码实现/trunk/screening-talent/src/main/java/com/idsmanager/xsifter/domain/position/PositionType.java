package com.idsmanager.xsifter.domain.position;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.xsifter.domain.AbstractDomain;

//职位行业类别
@Document(collection = "PositionType")
public class PositionType extends AbstractDomain {

	private static final long serialVersionUID = 2155777238271957503L;

	private String typeName;// 行业类别名称

	public PositionType() {
		super();
	}

	public String getTypeName() {
		return typeName;
	}

	public PositionType setTypeName(String typeName) {
		this.typeName = typeName;
		return this;
	}

	@Override
	public String toString() {
		return "PositionType [typeName=" + typeName + "]";
	}

}

package com.idsmanager.xsifter.domain.company;

public enum CompanyDeleteState {
	RESET("还原"),
	DELETE("删除");
	private String label;

	CompanyDeleteState(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}

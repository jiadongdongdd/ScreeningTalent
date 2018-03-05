package com.idsmanager.xsifter.domain.member;

public enum TurnoverPosition {
	
	PROJECT_MANAGER("项目经理"),
	PRODUCT_MANAGER("产品经理");
	
	private String label;
	
	TurnoverPosition(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}

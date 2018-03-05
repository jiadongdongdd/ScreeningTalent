package com.idsmanager.xsifter.domain.news;

public enum Category {

	SPORTS("体育"),
	FINANCE("金融"),
	COMPANY("企业"),
	ENTERTAINMENT("娱乐");

	private String label;

	Category(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}

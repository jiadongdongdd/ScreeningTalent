package com.idsmanager.xsifter.domain.news;

public enum Platform {
	PLATNOTICE("平台公告"), PLATNEWS("平台新闻");

	private String label;

	private Platform(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isPlatNotice() {
		return PLATNOTICE.equals(this);
	}

	public boolean isPlatNews() {
		return PLATNEWS.equals(this);
	}

}

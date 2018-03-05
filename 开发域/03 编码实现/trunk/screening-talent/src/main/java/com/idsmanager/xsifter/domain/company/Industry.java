package com.idsmanager.xsifter.domain.company;

public enum Industry {
	
	INTERNET("计算机|互联网|通信|电子"),
	SALE("销售|客服|技术支持"),
	FINANCE("会计|金融|银行|保险"),
	LOGISTICS("生产|营运|采购|物流"),
	MEDICINE("生物|制药|医疗|护理"),
	MEDIA("广告|市场|媒体艺术"),
	BUILDING("建筑|房地产"),
	ADMINISTRATION("人事|行政|高级管理"),
	EDUCATION("咨询|法律|教育|科研"),
	SERVICE("服务业"),
	OFFIC("公务员|翻译|其他");

	private String label;

	Industry(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}

package com.idsmanager.xsifter.domain.position;

public enum PositionIndustry {
	
	COMPUTER_INTERNET_COMMUNICATION_ELECTRONIC("计算机/互联网/通信/电子"),
	SALES_CUSTOMER_SUPPORT("销售/客服/技术支持"),
	ACCOUNTING_FINANCIAL_BANKING_INSURANCE("会计/金融/银行/保险"),
	PRODUCTION_OPERATION("生产/营运/采购/物流"),
	BIOLOGICAL_PHARMACEUTICAL_MEDICAL_NURSING("生物/制药/医疗/护理"),
	ADVERTISING_MARKET_MEDIA("广告/市场/媒体艺术"),
	ARCHITECTURE_REALESTATE("建筑/房地产"),
	PERSONNELMATTERS_ADMINISTRATION_SENIORMANAGEMENT("人事/行政/高级管理"),
	CONSULTATION_LAW_EDUCATION_SCIENTIFICRESEARCH("咨询/法律/教育/科研"),
	SERVICEINDUSTRY("服务业"),
	CIVILSERVANT_TRANSLATER_OTHER("公务员/翻译/其他");
	
	private PositionIndustry(String label) {
		this.label = label;
	}
	
	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	
	
	

}

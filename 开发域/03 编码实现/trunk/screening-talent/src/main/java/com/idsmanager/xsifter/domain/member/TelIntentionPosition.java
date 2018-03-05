package com.idsmanager.xsifter.domain.member;

/**
 * 电话面试职位
 * @author thinkpad
 *
 */
public enum TelIntentionPosition {
	HARDWARE_ENGINEER("硬件工程师"),
	RESIDENT_HARDWARE_ENGINEER("驻场硬件工程师"),
	WORKSHEET_PROCESSING_ENGINEER("派单工程师"),
	SYSTEM_ENGINEER("系统工程师"),
	INSTALLATION_ENGINEER("安装工程师"),
	SENIOR_HARDWARE_ENGINEER("高级硬件工程师"),
	HARDWARE_TEST("硬件测试"),
	SOFTWARE_DEVELOPMENT("软件开发"),
	SOFTWARE_TEST("软件测试"),
	SOFTWARE_ENGINEER("软件工程师"),
	UI_SOFTWARE_DESIGNER_OR_ENGINEER("软件UI设计师/工程师"),
	ERP_TECHNOLOGY_DEVELOPMENT("ERP技术开发"),
	ERP_CONSULTANT("ERP实施顾问"),
	USE_ERP_TECHNOLOGY("ERP技术应用"),
	INTERNET_OR_ECOMMERCE("互联网/电子商务"), 
	INTERNET("互联网"), 
	NETWORK_AND_INFORMATION_SECURITY_ENGINEER("网络与信息安全工程师"), 
	NETWORK_ENGINEER("网络工程师"), 
	NETWORK_ADMINISTRATOR("网络管理员"), 
	UI("用户界面（UI)设计"),
	INTERNET_PRODUCT_MANAGER_OR_SUPERVISOR("互联网产品经理/主管"),
	INTERNET_PRODUCT_SPECIALIST_OR_ASSISTANT("互联网产品专员/助理"),
	INTERNET_SOFTWARE_DEVELOPMENT_ENGINEER("互联网软件开发工程师"),
	SITE_MANAGEMENT("网站运营管理"),
	REMOTE_SUPPORT("远程支持"),
	OPERATION_ENGINEER("运维工程师"),
	ELECTRONIC_COMMERCE("电子商务"),
	ECOMMERCE_SPECIALIST("电子商务专员"),
	NETWORK_PROMOTION("网络推广"),
	WEBSITE_EDITOR("网站编辑"),
	SITE_PLANNING("网站策划"),
	WEB_DESIGN_OR_PRODUCTION("网页设计/制作"),
	TELECOMMUNICATIONS_ENGINEER("通信工程师"),
	ELECTRONIC_ENGINEER("电子工程师"),
	ELECTRONIC_OR_ELECTRICAL_ENGINEER("电子/电器工程师"),
	ELECTRONIC_OR_ELECTRICAL_PROJECT_MANAGEMENT("电子/电器项目管理"),
	ELECTRICAL_ENGINEER("电气工程师"),
	ANALOG_CIRCUIT_DESIGN_OR_APPLICATION_ENGINEER("模拟电路设计/应用工程师"),
	AUTOMATION_ENGINEER("自动化工程师"),
	QUALITY_INSPECTION_SPECIALIST("质量检测技术专员"),
	SALES_MANAGEMENT("销售管理"),
	SALES_DIRECTOR("销售总监"),
	SALES_MANAGER_OR_SUPERVISOR("销售经理/主管"),
	SALES_REPRESENTATIVE("销售代表"),
	SALES_ASSISTANT("销售助理"),
	CHANNEL_OR_DISTRIBUTION_DIRECTOR("渠道/分销总监"),
	CHANNEL_OR_DISTRIBUTION_SPECIALIST("渠道/分销专员"),
	BUSINESS_MANAGER("业务经理"),
	MARKET_RESEARCH_AND_ANALYSIS("市场调研与分析"),
	MARKETING_DIRECTOR("市场总监"),
	MARKETING_SUPERVISOR("市场主管"),
	MARKETING_ASSISTANT("市场助理"),
	MARKET_DEVELOPMENT("市场拓展"),
	CUSTOMER_SERVICE_MANAGER("客服经理"),
	CUSTOMER_SERVICE_SPECIALIST("客服专员"),
	CUSTOMER_SERVICE_REPRESENTATIVE("客服代表"),
	TECHNICAL_SUPPORT("技术支持"),
	TECHNICAL_DIRECTOR_OR_MANAGER("技术总监/经理"),
	TECHNICAL_SUPPORT_OR_MAINTENANCE_MANAGER("技术支持/维护经理"),
	TECHNICAL_SUPPORT_OR_MAINTENANCE_ENGINEER("技术支持/维护工程师"),
	ACCOUNTING_FINANCE_BANKING_INSURANCE("财务/金融/银行/保险"),
	CHIEF_FINANCIAL_OFFICER("财务总监"),
	FINANCE_MANAGER_OR_SUPERVISOR("财务经理/主管"),
	FINANCIAL_COST_CONTROL_SUPERVISOR("财务成本控制主管"),
	BUDGET_DIRECTOR("预算主管"),
	TAX_SPECIALIST_OR_ASSISTANT("税务专员/助理"),
	ACCOUNTING_SUPERVISOR("会计主管"),
	ACCOUNTING("会计"),
	CASHIER("出纳"),
	AUDIT_SUPERVISOR("审计主管"),
	FINANCE_OR_BANKING("金融/银行"),
	FINANCE_DIRECTOR("融资总监"),
	SECURITIES_DEPARTMENT_MANAGER_OR_DIRECTOR("证券总监/部门经理"),
	SECURITIES_BROKER("证券/期货/外汇经纪人"),
	STOCK_OR_FUTURES_TRADER("股票/期货货操盘手"),
	RISK_MANAGEMENT_CONTROL_INSPECTION("风险管理/控制/稽查"),
	BANK_MANAGER_DIRECTOR("银行经理/主任"),
	BANK_TELLER_ACCOUNTING("银行会计/柜员"),
	INSURANCE("保险"),
	INSURANCE_SALESMAN("保险业务员"),
	CLAIMS_SPECIALIST_CONSULTANT("理赔专员/顾问"),
	PRODUCTION_OPERATION_PROCUREMENT_LOGISTICS_BUSINESS("生产/营运/采购/物流/商务"),
	PRODUCTION_PROCESSING_MANUFACTURING("生产/加工/制造"),
	DEPUTY_DIRECTOR_DIRECTOR("厂长/副厂长"),
	PRODUCTION_MANAGER_DIRECTOR("生产经理/车间主任"),
	PRODUCTION_SCHEDULER("生产调度员"),
	PRODUCT_DESIGN_ENGINEER("产品设计工程师"),
	QUALITY_MANAGER("质量管理员"),
	PRODUCT_DESIGN_DEPARTMENT_ASSISTANT("产品方案部助理");
	
	private String label;
	
	TelIntentionPosition(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	
}

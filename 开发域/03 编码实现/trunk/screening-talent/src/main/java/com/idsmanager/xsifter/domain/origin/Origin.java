package com.idsmanager.xsifter.domain.origin;


public enum Origin {
	
	BEIJING("北京"),
	TIANJIN("天津"),
	SHANGHAI("上海"),
	CHONGQING("重庆"),
	HEBEI("河北"),
	HENAN("河南"),
	SHANDONG("山东"),
	SHANXI("山西"),
	HUNAN("湖南"),
	HUBEI("湖北"),
	JIANGSU("江苏"),
	ZHEJIANG("浙江"),
	FUJIAN("福建"),
	TAIWAN("台湾"),
	GUANGDONG("广东"),
	GAUNGXI("广西"),
	YUNNAN("云南"),
	SICHUAN("四川"),
	XIZANG("西藏"),
	XINJIANG("新疆"),
	NEIMENGGU("内蒙古"),
	QINGHAI("青海"),
	GANSU("甘肃"),
	SHANXI2("陕西"),
	HEILONGJIANG("黑龙江"),
	LIAONING("辽宁"),
	JILIN("吉林"),
	ANHUI("安徽"),
	JIANGXI("江西"),
	XIANGANG("香港"),
	AOMEN("澳门"),
	NINGXIA("宁夏"),
	GUIZHOU("贵州"),
	HAINAN("海南"),
	WAIJI("外籍");
	
	private String label;
	
	Origin(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	public static Origin getValue(String label){
		for (Origin origin : values()) {
			if(origin.getLabel().equalsIgnoreCase(label)){
				return origin;
			}
		}
		return null;
	}

}

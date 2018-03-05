package com.idsmanager.xsifter.domain.nation;

public enum Nation {
	
	HANZU("汉族"),
	MENGGUZU("蒙古族"),
	ZHUANGZU("壮族"),
	WEIWUERZU("维吾尔族"),
	HUIZU("回族"),
	ZANGZU("藏族"),
	MANZU("满族"),
	MIAOZU("苗族"),
	YIZU("彝族"),
	TUJIAZU("土家族"),
	OTHER("其他");
	
	private String label;
	
	Nation(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	public static Nation getValue(String label){
		for (Nation nation : values()) {
			if(nation.getLabel().equalsIgnoreCase(label)){
				return nation;
			}
		}
		return Nation.OTHER;
	}

}

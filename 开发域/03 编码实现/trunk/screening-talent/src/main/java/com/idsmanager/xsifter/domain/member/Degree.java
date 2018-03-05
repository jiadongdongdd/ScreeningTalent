package com.idsmanager.xsifter.domain.member;

/**
 * 学历
 * @author thinkpad
 *
 */
public enum Degree {
	PRIMARY_SCHOOL("小学"),
	JUNIOR_MIDDLE_SCHOOL("初中"),
	HIGH_SCHOOL("高中"),
	JUNIOR_COLLEGE("大专"),
	UNDERGRADUATE("本科"),
	MASTER("硕士"),
	DOCTOR("博士");
	
	private String label;
	
	Degree(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
	
}

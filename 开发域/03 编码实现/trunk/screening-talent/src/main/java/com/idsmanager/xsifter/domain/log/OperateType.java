package com.idsmanager.xsifter.domain.log;

public enum OperateType {

	LOGIN_SUCCESS("登录成功"),
	QUERY_MEMBER("查询职工"),
	REMOVE_MEMBER("删除职工"),
	CREATE_MEMBER("录入职工"),
	EDIT_MEMBER("编辑职工"),
	CREATE_MEMBER_BASIC("录入职工基本信息"),
	EDIT_MEMBER_BASIC("编辑职工基本信息"),
	CREATE_MEMBER_EDU("录入职工教育背景"),
	EDIT_MEMBER_EDU("编辑职工教育背景"),
	CREATE_MEMBER_RECRUITMENT("录入职工招聘环节"),
	EDIT_MEMBER_RECRUITMENT("编辑职工招聘环节"),
	CREATE_MEMBER_TURNOVER("录入职工离职环节"),
	EDIT_MEMBER_TURNOVER("编辑职工离职环节"),
	CREATE_MEMBER_WORKED("录入职工工作环节"),
	EDIT_MEMBER_WORKED("编辑职工工作环节"),
	CREATE_MEMBER_BASIC_EDU("录入员工基本信息和教育背景"),
	EDIT_MEMBER_BASIC_EDU("编辑员工基本信息和教育背景"),
	TOTAL_STATION_QUERY_MEMBER("全站查找职工"),
	CREATE_ENTERPRISEADMIN("创建系统操作员"),
	CANCELLATION_ENTERPRISE("注销系统操作员"),
	REDUCTION_ENTERPRISE("还原系统操作员"),
	IMPORT_MEMBER_EXCEL("导入职工信息"),
	EXPORT_MEMBER_EXCEL("导出职工信息");
	
	private String label;
	
	OperateType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

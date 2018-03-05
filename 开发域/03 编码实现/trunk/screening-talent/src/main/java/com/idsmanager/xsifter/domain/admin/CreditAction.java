package com.idsmanager.xsifter.domain.admin;

import java.util.Arrays;
import java.util.List;

public enum CreditAction {
	
	SYSTEM("系统操作"),
	SEARCH("查询职工信息"),
	ADD("添加职工信息"),
	DELETE("删除职工信息"),
	SIGN("签到"),
	INVITE("邀请注册"),
	FEEDBACK("反馈或评价"),
	DOWNLOAD("下载职工信息"),
	SUGGESTION("用户给予建议");
	
	
	private String action;
	
	CreditAction(String action){
		this.action = action;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public static List<String> allAction() {
		// TODO Auto-generated method stub
		return Arrays.asList(
				SEARCH.getAction(),
				SUGGESTION.getAction(),
				ADD.getAction(),
				DELETE.getAction(),
				DOWNLOAD.getAction(),
				SIGN.getAction(),
				FEEDBACK.getAction(),
				INVITE.getAction());
	}
	
	
}

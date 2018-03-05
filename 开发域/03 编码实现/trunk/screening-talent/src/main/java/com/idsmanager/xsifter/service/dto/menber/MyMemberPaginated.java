package com.idsmanager.xsifter.service.dto.menber;

import java.util.Map;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

public class MyMemberPaginated extends DefaultPaginated<Member> {

	private String chNameQuery;// 姓名
	private String idNumberQuery;// 身份证号
	private String emailQuery;// 邮箱
	private String mobileQuery;// 手机号

	private String propertyName;
	private String direction;

	private String errors;

	private boolean first = true;

	// private MemberProcess memberProcess;

	@Override
	public Map<String, Object> queryMap() {
		final Map<String, Object> map = super.queryMap();
		map.put("chName", chNameQuery);
		map.put("idNumber", idNumberQuery);
		map.put("email", emailQuery);
		map.put("mobile", mobileQuery);

		map.put("companyGuid", SecurityUtils.currentUserGuid());

		map.put("propertyName", propertyName);
		map.put("direction", direction);

		map.put("first", first);

		// map.put("memberProcess", memberProcess);

		return map;
	}

	public String getChNameQuery() {
		return chNameQuery;
	}

	public void setChNameQuery(String chNameQuery) {
		this.chNameQuery = chNameQuery;
	}

	public String getIdNumberQuery() {
		return idNumberQuery;
	}

	public void setIdNumberQuery(String idNumberQuery) {
		this.idNumberQuery = idNumberQuery;
	}

	public String getEmailQuery() {
		return emailQuery;
	}

	public void setEmailQuery(String emailQuery) {
		this.emailQuery = emailQuery;
	}

	public String getMobileQuery() {
		return mobileQuery;
	}

	public void setMobileQuery(String mobileQuery) {
		this.mobileQuery = mobileQuery;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

}

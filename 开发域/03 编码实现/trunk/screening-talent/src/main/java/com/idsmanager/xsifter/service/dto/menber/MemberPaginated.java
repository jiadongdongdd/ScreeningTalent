package com.idsmanager.xsifter.service.dto.menber;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberProcess;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author andy
 * 
 */
public class MemberPaginated extends DefaultPaginated<Member> {

	private String propertyName;

	private String direction;

	private String chName1;// 职工姓名

	private String idNumber1;// 身份证号

	private String email1;// 邮箱

	private String mobile1;// 手机号

	private String companyName1;// 公司名称

	private MemberProcess memberProcess;

	public MemberPaginated() {
	}

	@Override
	public Map<String, Object> queryMap() {
		final Map<String, Object> map = super.queryMap();
		map.put("direction", direction);
		map.put("chName", chName1);
		map.put("idNumber", idNumber1);
		map.put("email", email1);
		map.put("mobile", mobile1);
		map.put("companyName", companyName1);
		map.put("propertyName", propertyName);
		map.put("memberProcess", memberProcess);
		return map;
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

	public MemberProcess getMemberProcess() {
		return memberProcess;
	}

	public void setMemberProcess(MemberProcess memberProcess) {
		this.memberProcess = memberProcess;
	}

	public String getChName1() {
		return chName1;
	}

	public void setChName1(String chName1) {
		this.chName1 = chName1;
	}

	public String getIdNumber1() {
		return idNumber1;
	}

	public void setIdNumber1(String idNumber1) {
		this.idNumber1 = idNumber1;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getCompanyName1() {
		return companyName1;
	}

	public void setCompanyName1(String companyName1) {
		this.companyName1 = companyName1;
	}

}

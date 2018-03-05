package com.idsmanager.xsifter.domain.member;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.xsifter.domain.AbstractDomain;
import com.idsmanager.xsifter.domain.GenderStatus;

@Document(collection = "Basic")
public class Basic extends AbstractDomain {

	private static final long serialVersionUID = 9213691083489470084L;

	private String companyGuid;
	private String companyUsername;
	private String companyName;

	/*
	 * 基本信息
	 */
	private String chName;
	private String enName;
	private String mobile;
	private String idNumber;
	private String email;
	private Date birthday;

	private String pinyin;

	private GenderStatus gender = GenderStatus.MALE;

	private Integer age;

	public Basic() {
	}

	public String getCompanyGuid() {
		return companyGuid;
	}

	public Basic setCompanyGuid(String companyGuid) {
		this.companyGuid = companyGuid;
		return this;
	}

	public String getChName() {
		return chName;
	}

	public Basic setChName(String chName) {
		this.chName = chName;
		return this;
	}

	public String getEnName() {
		return enName;
	}

	public Basic setEnName(String enName) {
		this.enName = enName;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public Basic setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public Basic setIdNumber(String idNumber) {
		this.idNumber = idNumber;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Basic setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPinyin() {
		return pinyin;
	}

	public Basic setPinyin(String pinyin) {
		this.pinyin = pinyin;
		return this;
	}

	public GenderStatus getGender() {
		return gender;
	}

	public Basic setGender(GenderStatus gender) {
		this.gender = gender;
		return this;
	}

	public String getCompanyUsername() {
		return companyUsername;
	}

	public Basic setCompanyUsername(String companyUsername) {
		this.companyUsername = companyUsername;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public Basic setAge(Integer age) {
		this.age = age;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Basic setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Basic setBirthday(Date birthday) {
		this.birthday = birthday;
		return this;
	}

}

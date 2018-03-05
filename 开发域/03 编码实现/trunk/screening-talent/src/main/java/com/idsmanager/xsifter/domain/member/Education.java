package com.idsmanager.xsifter.domain.member;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.xsifter.domain.AbstractDomain;
import com.idsmanager.xsifter.domain.GenderStatus;
import com.idsmanager.xsifter.domain.nation.Nation;
import com.idsmanager.xsifter.domain.origin.Origin;

@Document(collection = "Education")
public class Education extends AbstractDomain {

	private static final long serialVersionUID = -476723159752896805L;

	// 公司
	private String companyGuid;

	/*
	 * 教育背景
	 */
	private GenderStatus gender = GenderStatus.MALE;
	private Integer age;
	// 毕业院校
	private String school;
	// 专业
	private String major;
	// 学历
	private Degree degree;
	// 民族
	private Nation nation;

	// 民族为 '其他'选项时，必须填写
	private String nationOther;

	// 政治面貌
	private PoliticalStatus politicalStatus;
	// 籍贯
	private Origin origin;
	//外籍
	private String foreignOrigin;

	public Education() {
	}

	public Education(Basic basic) {
		this.gender = basic.getGender();
		this.age = basic.getAge();
		this.companyGuid = basic.getCompanyGuid();
		this.uuid = basic.getUuid();
	}

	public String getMemberUuid() {
		return uuid;
	}

	public Education setMemberUuid(String memberUuid) {
		this.uuid = memberUuid;
		return this;
	}

	public String getCompanyGuid() {
		return companyGuid;
	}

	public Education setCompanyGuid(String companyGuid) {
		this.companyGuid = companyGuid;
		return this;
	}

	public GenderStatus getGender() {
		return gender;
	}

	public Education setGender(GenderStatus gender) {
		this.gender = gender;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public Education setAge(Integer age) {
		this.age = age;
		return this;
	}

	public String getSchool() {
		return school;
	}

	public Education setSchool(String school) {
		this.school = school;
		return this;
	}

	public Nation getNation() {
		return nation;
	}

	public Education setNation(Nation nation) {
		this.nation = nation;
		return this;
	}

	public Origin getOrigin() {
		return origin;
	}

	public Education setOrigin(Origin origin) {
		this.origin = origin;
		return this;
	}

	public String getMajor() {
		return major;
	}

	public Education setMajor(String major) {
		this.major = major;
		return this;
	}

	public Degree getDegree() {
		return degree;
	}

	public Education setDegree(Degree degree) {
		this.degree = degree;
		return this;
	}

	public PoliticalStatus getPoliticalStatus() {
		return politicalStatus;
	}

	public Education setPoliticalStatus(PoliticalStatus politicalStatus) {
		this.politicalStatus = politicalStatus;
		return this;
	}

	public String getNationOther() {
		return nationOther;
	}

	public Education setNationOther(String nationOther) {
		this.nationOther = nationOther;
		return this;
	}

	public String getForeignOrigin() {
		return foreignOrigin;
	}

	public Education setForeignOrigin(String foreignOrigin) {
		this.foreignOrigin = foreignOrigin;
		return this;
	}

}

package com.idsmanager.xsifter.service.dto.menber;

import java.io.Serializable;

import com.idsmanager.xsifter.domain.GenderStatus;
import com.idsmanager.xsifter.domain.member.Degree;
import com.idsmanager.xsifter.domain.member.Education;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.PoliticalStatus;
import com.idsmanager.xsifter.domain.nation.Nation;
import com.idsmanager.xsifter.domain.origin.Origin;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

public class MemberFormTwoDto implements Serializable {

	private static final long serialVersionUID = -932150581757154239L;
	private String uuid;
	private GenderStatus gender;
	private String age;
	private String school;
	// private MultipartFile educationFile;
	// 专业
	private String major;
	private Degree degree;
	private Origin origin;
	// 外籍
	private String foreignOrigin;
	private Nation nation;
	private String nationOther;
	private PoliticalStatus politicalStatus;

	private boolean next;

	public MemberFormTwoDto() {
		super();
	}

	public MemberFormTwoDto(Member member) {
		this.uuid = member.getUuid();
		this.gender = member.getGender();
		this.age = String.valueOf(member.getAge());
		this.school = member.getSchool();
		this.degree = member.getDegree();
		this.origin = member.getOrigin();
		this.foreignOrigin = member.getForeignOrigin();
		this.nation = member.getNation();
		this.nationOther = member.getNationOther();
		this.politicalStatus = member.getPoliticalStatus();
		this.major = member.getMajor();
	}

	public MemberFormTwoDto(Education education2) {
		this.uuid = education2.getUuid();
		this.gender = education2.getGender();
		if (education2.getAge() != null) {
			this.age = String.valueOf(education2.getAge());
		}
		this.school = education2.getSchool();
		this.degree = education2.getDegree();
		this.major = education2.getMajor();

		this.origin = education2.getOrigin();
		this.foreignOrigin = education2.getForeignOrigin();
		this.nation = education2.getNation();
		this.nationOther = education2.getNationOther();
		this.politicalStatus = education2.getPoliticalStatus();
	}

	public Degree[] getDegrees() {
		return Degree.values();
	}

	public PoliticalStatus[] getPoliticalStatuses() {
		return PoliticalStatus.values();
	}

	public Nation[] getNations() {
		return Nation.values();
	}

	public Origin[] getOrigins() {
		return Origin.values();
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public GenderStatus getGender() {
		return gender;
	}

	public void setGender(GenderStatus gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public String getNationOther() {
		return nationOther;
	}

	public void setNationOther(String nationOther) {
		this.nationOther = nationOther;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public PoliticalStatus getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(PoliticalStatus politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getForeignOrigin() {
		return foreignOrigin;
	}

	public void setForeignOrigin(String foreignOrigin) {
		this.foreignOrigin = foreignOrigin;
	}

	public Education createEducation() {
		if ("".equals(age)) {
			return new Education().setAge(null)
					.setCompanyGuid(SecurityUtils.currentUserGuid())
					.setDegree(degree).setGender(gender).setMemberUuid(uuid)
					.setNation(nation).setOrigin(origin)
					.setPoliticalStatus(politicalStatus).setSchool(school)
					.setMajor(major).setNationOther(nationOther)
					.setForeignOrigin(foreignOrigin);
		}
		return new Education().setAge(Integer.valueOf(age))
				.setCompanyGuid(SecurityUtils.currentUserGuid())
				.setDegree(degree).setGender(gender).setMemberUuid(uuid)
				.setNation(nation).setOrigin(origin)
				.setPoliticalStatus(politicalStatus).setSchool(school)
				.setMajor(major).setNationOther(nationOther)
				.setForeignOrigin(foreignOrigin);
	}

	public Education updateEducation(Education education2) {
		if ("".equals(age)) {
			return education2.setAge(null).setGender(gender).setNation(nation)
					.setOrigin(origin).setPoliticalStatus(politicalStatus)
					.setSchool(school).setDegree(degree).setMajor(major)
					.setNationOther(nationOther)
					.setForeignOrigin(foreignOrigin);
		}
		return education2.setAge(Integer.valueOf(age)).setGender(gender)
				.setNation(nation).setOrigin(origin)
				.setPoliticalStatus(politicalStatus).setSchool(school)
				.setDegree(degree).setMajor(major).setNationOther(nationOther)
				.setForeignOrigin(foreignOrigin);
	}

}

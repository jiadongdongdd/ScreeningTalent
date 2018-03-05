package com.idsmanager.xsifter.domain.member;

import java.util.Date;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.AbstractDomain;
import com.idsmanager.xsifter.domain.GenderStatus;
import com.idsmanager.xsifter.domain.nation.Nation;
import com.idsmanager.xsifter.domain.origin.Origin;

/**
 * 职工
 */
@Document(collection = "Member")
public class Member extends AbstractDomain {

	/**
     *
     */
	private static final long serialVersionUID = -1605326511262680732L;

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

	private String workPosition;// 工作职位

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
	// 外籍
	private String foreignOrigin;

	// 数据状态,不作页面展示,作为查询条件 , 默认:正常状态
	private MemberProcess memberProcess;

	/*
	 * 招聘环节
	 * 
	 * 详见 Recruitment
	 */

	/*
	 * 离职环节 详见 Turnover
	 */

	/*
	 * 工作环节 详见 Worked
	 */

	@Transient
	private Integer integrity;

	public Member() {
	}

	public Member(String uuid) {
		this.uuid = uuid;
	}

	public Member(Basic basic, Education education2) {

		this.uuid = basic.getUuid();
		this.companyGuid = basic.getCompanyGuid();
		this.companyUsername = basic.getCompanyUsername();
		this.companyName = basic.getCompanyName();

		this.chName = basic.getChName();
		this.enName = basic.getEnName();
		this.mobile = basic.getMobile();
		this.idNumber = basic.getIdNumber();
		this.email = basic.getEmail();
		this.birthday = basic.getBirthday();
		this.pinyin = basic.getPinyin();

		this.age = education2.getAge();
		this.gender = education2.getGender();
		this.degree = education2.getDegree();
		this.nation = education2.getNation();
		this.nationOther = education2.getNationOther();
		this.origin = education2.getOrigin();
		this.foreignOrigin = education2.getForeignOrigin();
		this.politicalStatus = education2.getPoliticalStatus();
		this.school = education2.getSchool();
		this.major = education2.getMajor();
		this.memberProcess = MemberProcess.NORMAL;

	}

	public Member(Basic basic) {
		this.uuid = basic.getUuid();
		this.companyGuid = basic.getCompanyGuid();
		this.companyUsername = basic.getCompanyUsername();
		this.companyName = basic.getCompanyName();

		this.chName = basic.getChName();
		this.enName = basic.getEnName();
		this.mobile = basic.getMobile();
		this.idNumber = basic.getIdNumber();
		this.email = basic.getEmail();
		this.birthday = basic.getBirthday();
		this.pinyin = basic.getPinyin();
		this.gender = basic.getGender();
		this.memberProcess = MemberProcess.NORMAL;
	}

	public Member(Basic basic, Recruitment recruitment) {
		this.uuid = basic.getUuid();
		this.companyGuid = basic.getCompanyGuid();
		this.companyUsername = basic.getCompanyUsername();
		this.companyName = basic.getCompanyName();

		this.chName = basic.getChName();
		this.enName = basic.getEnName();
		this.mobile = basic.getMobile();
		this.idNumber = basic.getIdNumber();
		this.email = basic.getEmail();
		this.birthday = basic.getBirthday();
		this.pinyin = basic.getPinyin();
		this.gender = basic.getGender();
		this.workPosition = recruitment.getTelIntentionPosition();
		this.memberProcess = MemberProcess.NORMAL;
	}

	public Member(Basic basic, Turnover turnover) {
		this.uuid = basic.getUuid();
		this.companyGuid = basic.getCompanyGuid();
		this.companyUsername = basic.getCompanyUsername();
		this.companyName = basic.getCompanyName();

		this.chName = basic.getChName();
		this.enName = basic.getEnName();
		this.mobile = basic.getMobile();
		this.idNumber = basic.getIdNumber();
		this.email = basic.getEmail();
		this.birthday = basic.getBirthday();
		this.pinyin = basic.getPinyin();
		this.gender = basic.getGender();
		this.workPosition = turnover.getTurnoverPosition();
		this.memberProcess = MemberProcess.NORMAL;
	}

	public String getChName() {
		return chName;
	}

	public Member setChName(String chName) {
		this.chName = chName;
		return this;
	}

	public String getEnName() {
		return enName;
	}

	public Member setEnName(String enName) {
		this.enName = enName;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public Member setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public Member setIdNumber(String idNumber) {
		this.idNumber = idNumber;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Member setEmail(String email) {
		this.email = email;
		return this;
	}

	public GenderStatus getGender() {
		return gender;
	}

	public Member setGender(GenderStatus gender) {
		this.gender = gender;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public Member setAge(Integer age) {
		this.age = age;
		return this;
	}

	public String getSchool() {
		return school;
	}

	public Member setSchool(String school) {
		this.school = school;
		return this;
	}

	public Nation getNation() {
		return nation;
	}

	public Member setNation(Nation nation) {
		this.nation = nation;
		return this;
	}

	public Origin getOrigin() {
		return origin;
	}

	public Member setOrigin(Origin origin) {
		this.origin = origin;
		return this;
	}

	public String getCompanyGuid() {
		return companyGuid;
	}

	public Member setCompanyGuid(String companyGuid) {
		this.companyGuid = companyGuid;
		return this;
	}

	public String getPinyin() {
		return pinyin;
	}

	public Member setPinyin(String pinyin) {
		this.pinyin = pinyin;
		return this;
	}

	public MemberProcess getMemberProcess() {
		return memberProcess;
	}

	public Member setMemberProcess(MemberProcess memberProcess) {
		this.memberProcess = memberProcess;
		return this;
	}

	public String getCompanyUsername() {
		return companyUsername;
	}

	public Member setCompanyUsername(String companyUsername) {
		this.companyUsername = companyUsername;
		return this;
	}

	public String getMajor() {
		return major;
	}

	public Member setMajor(String major) {
		this.major = major;
		return this;
	}

	public Degree getDegree() {
		return degree;
	}

	public Member setDegree(Degree degree) {
		this.degree = degree;
		return this;
	}

	public PoliticalStatus getPoliticalStatus() {
		return politicalStatus;
	}

	public Member setPoliticalStatus(PoliticalStatus politicalStatus) {
		this.politicalStatus = politicalStatus;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Member setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public String getBirthday() {
		return DateUtils.toDateText(birthday, "yyyy-MM-dd");
	}

	public Member setBirthday(Date birthday) {
		this.birthday = birthday;
		return this;
	}

	public String getWorkPosition() {
		return workPosition;
	}

	public Member setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
		return this;
	}

	public Integer getIntegrity() {
		return integrity;
	}

	public void setIntegrity(Integer integrity) {
		this.integrity = integrity;
	}

	public String getNationOther() {
		return nationOther;
	}

	public Member setNationOther(String nationOther) {
		this.nationOther = nationOther;
		return this;
	}

	public String getForeignOrigin() {
		return foreignOrigin;
	}

	public Member setForeignOrigin(String foreignOrigin) {
		this.foreignOrigin = foreignOrigin;
		return this;
	}

}

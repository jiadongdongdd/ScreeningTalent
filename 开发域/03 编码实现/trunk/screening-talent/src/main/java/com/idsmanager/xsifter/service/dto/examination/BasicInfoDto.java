package com.idsmanager.xsifter.service.dto.examination;

import com.idsmanager.xsifter.domain.GenderStatus;
import com.idsmanager.xsifter.domain.member.Basic;
import com.idsmanager.xsifter.domain.member.Degree;
import com.idsmanager.xsifter.domain.member.Education;
import com.idsmanager.xsifter.domain.member.PoliticalStatus;
import com.idsmanager.xsifter.domain.nation.Nation;
import com.idsmanager.xsifter.domain.origin.Origin;
import com.idsmanager.xsifter.service.dto.AbstractDto;

/**
 * Created by LZW on 2016/9/30.
 */
public class BasicInfoDto extends AbstractDto {
    private static final long serialVersionUID = 2246422335452270959L;

    private String memberUuid;

    private String chName;
    private String enName;
    private String mobile;
    private String idNumber;
    private String email;
    private GenderStatus gender;
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

    public BasicInfoDto() {
    }

    public BasicInfoDto(Basic basic, Education education) {
        this.memberUuid = basic.getUuid();
        this.chName = basic.getChName();
        this.enName = basic.getEnName();
        this.mobile = basic.getMobile();
        this.idNumber = basic.getIdNumber();
        this.email = basic.getEmail();
        this.age = education.getAge();
        this.gender = education.getGender();
        this.school = education.getSchool();
        this.major = education.getMajor();
    }

    public String getMemberUuid() {
        return memberUuid;
    }

    public void setMemberUuid(String memberUuid) {
        this.memberUuid = memberUuid;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GenderStatus getGender() {
        return gender;
    }

    public void setGender(GenderStatus gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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

    public PoliticalStatus getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(PoliticalStatus politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getForeignOrigin() {
        return foreignOrigin;
    }

    public void setForeignOrigin(String foreignOrigin) {
        this.foreignOrigin = foreignOrigin;
    }
}

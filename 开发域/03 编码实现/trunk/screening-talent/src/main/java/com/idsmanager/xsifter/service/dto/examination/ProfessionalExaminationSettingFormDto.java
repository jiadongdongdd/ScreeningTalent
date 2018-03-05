package com.idsmanager.xsifter.service.dto.examination;

import com.idsmanager.xsifter.domain.exam.ProfessionalExaminationSetting;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.question.QuestionDegree;
import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.service.dto.question.TagDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZW on 2016/9/26.
 */
public class ProfessionalExaminationSettingFormDto implements Serializable {
    private static final long serialVersionUID = 2897266976500741263L;

    //关联的面试员工
    private String memberUuid;
    private String positionUuid;
    //所选职位
    private MemberPosition position;
    //选择的综合标签
    private List<TagDto> professionalTagDtos;
    private List<String> professionalTags;
    //已选择的试题类型
    private List<QuestionType> selectedTypes;
    //单选题数量
    private int singleNum;
    //多选题数量
    private int multipleNum;
    //简答题数量
    private int shortNum;
    //难度
    private QuestionDegree degree;
    //生成试卷 默认true 是：true 否：false
    private boolean create = true;
    //试题总数
    private long totalAmount;
    //单选题总数
    private long totalSingle;
    //多选题总数
    private long totalMultiple;
    //简答题数量
    private long totalShort;

    public ProfessionalExaminationSettingFormDto() {
    }

    public ProfessionalExaminationSettingFormDto(ProfessionalExaminationSetting setting) {
        this.memberUuid = setting.memberUuid();
        this.positionUuid = setting.positionUuid();
        this.professionalTags = setting.professionalTags();
        this.selectedTypes = setting.selectedTypes();
        this.singleNum = setting.singleNum();
        this.multipleNum = setting.multipleNum();
        this.shortNum = setting.shortNum();
        this.degree = setting.degree();
        this.create = setting.create();
    }

    public MemberPosition getPosition() {
        return position;
    }

    public void setPosition(MemberPosition position) {
        this.position = position;
    }

    public String getMemberUuid() {
        return memberUuid;
    }

    public void setMemberUuid(String memberUuid) {
        this.memberUuid = memberUuid;
    }

    public String getPositionUuid() {
        return positionUuid;
    }

    public void setPositionUuid(String positionUuid) {
        this.positionUuid = positionUuid;
    }

    public List<TagDto> getProfessionalTagDtos() {
        return professionalTagDtos;
    }

    public void setProfessionalTagDtos(List<TagDto> professionalTagDtos) {
        this.professionalTagDtos = professionalTagDtos;
    }

    public List<String> getProfessionalTags() {
        return professionalTags;
    }

    public void setProfessionalTags(List<String> professionalTags) {
        this.professionalTags = professionalTags;
    }

    public List<QuestionType> getSelectedTypes() {
        return selectedTypes;
    }

    public void setSelectedTypes(List<QuestionType> selectedTypes) {
        this.selectedTypes = selectedTypes;
    }

    public int getSingleNum() {
        return singleNum;
    }

    public void setSingleNum(int singleNum) {
        this.singleNum = singleNum;
    }

    public int getMultipleNum() {
        return multipleNum;
    }

    public void setMultipleNum(int multipleNum) {
        this.multipleNum = multipleNum;
    }

    public int getShortNum() {
        return shortNum;
    }

    public void setShortNum(int shortNum) {
        this.shortNum = shortNum;
    }

    public QuestionDegree getDegree() {
        return degree;
    }

    public void setDegree(QuestionDegree degree) {
        this.degree = degree;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getTotalSingle() {
        return totalSingle;
    }

    public void setTotalSingle(long totalSingle) {
        this.totalSingle = totalSingle;
    }

    public long getTotalMultiple() {
        return totalMultiple;
    }

    public void setTotalMultiple(long totalMultiple) {
        this.totalMultiple = totalMultiple;
    }

    public long getTotalShort() {
        return totalShort;
    }

    public void setTotalShort(long totalShort) {
        this.totalShort = totalShort;
    }

    public QuestionType[] getTypes() {
        return QuestionType.values();
    }

    public QuestionDegree[] getDegrees() {
        return QuestionDegree.values();
    }
}

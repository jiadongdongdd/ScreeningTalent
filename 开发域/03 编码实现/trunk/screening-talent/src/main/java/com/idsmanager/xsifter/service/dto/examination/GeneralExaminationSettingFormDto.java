package com.idsmanager.xsifter.service.dto.examination;

import com.idsmanager.xsifter.domain.exam.GeneralExaminationSetting;
import com.idsmanager.xsifter.domain.question.QuestionDegree;
import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.service.dto.question.TagDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZW on 2016/9/22.
 */
public class GeneralExaminationSettingFormDto implements Serializable {
    private static final long serialVersionUID = 1291935753050647007L;

    //关联的面试员工
    private String memberUuid;
    private String positionUuid;
    //生成的基础标签
    private List<TagDto> basicTags;
    //选择的基础标签
    private List<TagDto> selectedBasicTagDtos;
    private List<String> selectedBasicTags;
    //选择的综合标签
    private List<TagDto> generalTagDtos;
    private List<String> generalTags;
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
    //考试结果是否已经存在
    private boolean existed = false;

    public GeneralExaminationSettingFormDto() {
    }

    public GeneralExaminationSettingFormDto(GeneralExaminationSetting setting) {
        this.memberUuid = setting.memberUuid();
        this.selectedBasicTags = setting.selectedBasicTags();
        this.generalTags = setting.generalTags();
        this.selectedTypes = setting.selectedTypes();
        this.singleNum = setting.singleNum();
        this.multipleNum = setting.multipleNum();
        this.shortNum = setting.shortNum();
        this.degree = setting.degree();
        this.create = setting.create();
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

    public List<TagDto> getBasicTags() {
        return basicTags;
    }

    public void setBasicTags(List<TagDto> basicTags) {
        this.basicTags = basicTags;
    }

    public List<String> getSelectedBasicTags() {
        return selectedBasicTags;
    }

    public void setSelectedBasicTags(List<String> selectedBasicTags) {
        this.selectedBasicTags = selectedBasicTags;
    }

    public List<String> getGeneralTags() {
        return generalTags;
    }

    public void setGeneralTags(List<String> generalTags) {
        this.generalTags = generalTags;
    }

    public QuestionType[] getTypes() {
        return QuestionType.values();
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

    public List<QuestionType> getSelectedTypes() {
        return selectedTypes;
    }

    public void setSelectedTypes(List<QuestionType> selectedTypes) {
        this.selectedTypes = selectedTypes;
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

    public List<TagDto> getSelectedBasicTagDtos() {
        return selectedBasicTagDtos;
    }

    public void setSelectedBasicTagDtos(List<TagDto> selectedBasicTagDtos) {
        this.selectedBasicTagDtos = selectedBasicTagDtos;
    }

    public List<TagDto> getGeneralTagDtos() {
        return generalTagDtos;
    }

    public void setGeneralTagDtos(List<TagDto> generalTagDtos) {
        this.generalTagDtos = generalTagDtos;
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

    public QuestionDegree getDegree() {
        return degree;
    }

    public void setDegree(QuestionDegree degree) {
        this.degree = degree;
    }

    public QuestionDegree[] getDegrees() {
        return QuestionDegree.values();
    }

    public boolean isExisted() {
        return existed;
    }

    public void setExisted(boolean existed) {
        this.existed = existed;
    }
}

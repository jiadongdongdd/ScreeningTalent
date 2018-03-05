package com.idsmanager.xsifter.domain.exam;

import com.idsmanager.xsifter.domain.AbstractDomain;
import com.idsmanager.xsifter.domain.question.QuestionDegree;
import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.domain.question.Tag;
import com.idsmanager.xsifter.service.dto.question.TagDto;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2016/9/23.
 */
@Document(collection = "GeneralExaminationSetting")
public class GeneralExaminationSetting extends AbstractDomain {
    private static final long serialVersionUID = 6996782701769976683L;

    //关联的面试员工
    private String memberUuid;
    private String positionUuid;
    //已选择的基础标签
    private List<String> selectedBasicTags = new ArrayList<>();
    //选择的综合标签
    private List<String> generalTags = new ArrayList<>();
    //已选择的试题类型
    private List<QuestionType> selectedTypes = new ArrayList<>();
    //单选题数量
    private int singleNum;
    //多选题数量
    private int multipleNum;
    //简答题数量
    private int shortNum;
    //难度
    private QuestionDegree degree;
    //生成试卷 默认true 是：true 否：false
    private boolean create;

    private List<Tag> tags = new ArrayList<>();

    public GeneralExaminationSetting() {
    }

    public String memberUuid() {
        return memberUuid;
    }

    public GeneralExaminationSetting memberUuid(String memberUuid) {
        this.memberUuid = memberUuid;
        return this;
    }

    public String positionUuid() {
        return positionUuid;
    }

    public GeneralExaminationSetting positionUuid(String positionUuid) {
        this.positionUuid = positionUuid;
        return this;
    }

    public List<String> generalTags() {
        return generalTags;
    }

    public GeneralExaminationSetting generalTags(List<String> generalTags) {
        this.generalTags = generalTags;
        return this;
    }

    public List<QuestionType> selectedTypes() {
        return selectedTypes;
    }

    public GeneralExaminationSetting selectedTypes(List<QuestionType> selectedTypes) {
        this.selectedTypes = selectedTypes;
        return this;
    }

    public int singleNum() {
        return singleNum;
    }

    public GeneralExaminationSetting singleNum(int singleNum) {
        this.singleNum = singleNum;
        return this;
    }

    public int multipleNum() {
        return multipleNum;
    }

    public GeneralExaminationSetting multipleNum(int multipleNum) {
        this.multipleNum = multipleNum;
        return this;
    }

    public int shortNum() {
        return shortNum;
    }

    public GeneralExaminationSetting shortNum(int shortNum) {
        this.shortNum = shortNum;
        return this;
    }

    public QuestionDegree degree() {
        return degree;
    }

    public GeneralExaminationSetting degree(QuestionDegree degree) {
        this.degree = degree;
        return this;
    }

    public boolean create() {
        return create;
    }

    public GeneralExaminationSetting create(boolean create) {
        this.create = create;
        return this;
    }

    public List<String> selectedBasicTags() {
        return selectedBasicTags;
    }

    public GeneralExaminationSetting selectedBasicTags(List<String> selectedBasicTags) {
        this.selectedBasicTags = selectedBasicTags;
        return this;
    }

    public List<Tag> tags() {
        return tags;
    }

    public GeneralExaminationSetting tags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

}

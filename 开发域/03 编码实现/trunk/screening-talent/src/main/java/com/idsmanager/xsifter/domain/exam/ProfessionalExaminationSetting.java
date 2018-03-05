package com.idsmanager.xsifter.domain.exam;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.AbstractDomain;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;
import com.idsmanager.xsifter.domain.question.QuestionDegree;
import com.idsmanager.xsifter.domain.question.QuestionType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2016/9/26.
 */
@Document(collection = "ProfessionalExaminationSetting")
public class ProfessionalExaminationSetting extends AbstractDomain {
    private static final long serialVersionUID = 857358367435125959L;

    //关联的面试员工
    private String memberUuid;
    private String positionUuid;
    //选择的专业标签
    private List<String> professionalTags = new ArrayList<>();
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

    public ProfessionalExaminationSetting() {
    }

    public String memberUuid() {
        return memberUuid;
    }

    public ProfessionalExaminationSetting memberUuid(String memberUuid) {
        this.memberUuid = memberUuid;
        return this;
    }

    public String positionUuid() {
        return positionUuid;
    }

    public ProfessionalExaminationSetting positionUuid(String positionUuid) {
        this.positionUuid = positionUuid;
        return this;
    }

    public List<String> professionalTags() {
        return professionalTags;
    }

    public ProfessionalExaminationSetting professionalTags(List<String> professionalTags) {
        this.professionalTags = professionalTags;
        return this;
    }

    public List<QuestionType> selectedTypes() {
        return selectedTypes;
    }

    public ProfessionalExaminationSetting selectedTypes(List<QuestionType> selectedTypes) {
        this.selectedTypes = selectedTypes;
        return this;
    }

    public int singleNum() {
        return singleNum;
    }

    public ProfessionalExaminationSetting singleNum(int singleNum) {
        this.singleNum = singleNum;
        return this;
    }

    public int multipleNum() {
        return multipleNum;
    }

    public ProfessionalExaminationSetting multipleNum(int multipleNum) {
        this.multipleNum = multipleNum;
        return this;
    }

    public int shortNum() {
        return shortNum;
    }

    public ProfessionalExaminationSetting shortNum(int shortNum) {
        this.shortNum = shortNum;
        return this;
    }

    public QuestionDegree degree() {
        return degree;
    }

    public ProfessionalExaminationSetting degree(QuestionDegree degree) {
        this.degree = degree;
        return this;
    }

    public boolean create() {
        return create;
    }

    public ProfessionalExaminationSetting create(boolean create) {
        this.create = create;
        return this;
    }


}

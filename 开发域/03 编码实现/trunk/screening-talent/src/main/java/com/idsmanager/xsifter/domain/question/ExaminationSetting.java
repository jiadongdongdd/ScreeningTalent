package com.idsmanager.xsifter.domain.question;

import com.idsmanager.xsifter.domain.AbstractDomain;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by LZW on 2016/9/14.
 */
//试卷设置
@Document(collection = "ExaminationSetting")
public class ExaminationSetting extends AbstractDomain {
    private static final long serialVersionUID = 1189208610713603380L;
    //关联的面试员工
    private String memberUuid;
    //差异性标签
    private List<String> differenceTags;
    //选择的差异性标签
    private List<String> selectedDifferenceTags;
    //选择的综合标签
    private List<String> generalTags;
    //综合试卷题型
    private Set<QuestionType> generalTypes;
    //综合试卷各题型数量
    private Map<String, Object> generalQuestionNum;
    //是否综合试卷生成试卷 是：true 否：false
    private boolean generatedGeneralExam;
    //综合试卷难度
    private String generalDifficultyDegree;

    //专业标签
    private Set<String> professionalTags;
    //专业试卷各题型数量
    private Map<String, Object> professionalQuestionNum;
    //专业试卷生成试卷 是：true 否：false
    private boolean generatedProfessionalExam;
    //专业试卷难度
    private String professionalDifficultyDegree;

    public ExaminationSetting() {
    }

    public String memberUuid() {
        return memberUuid;
    }

    public ExaminationSetting memberUuid(String memberUuid) {
        this.memberUuid = memberUuid;
        return this;
    }

    public List<String> differenceTags() {
        return differenceTags;
    }

    public ExaminationSetting differenceTags(List<String> differenceTags) {
        this.differenceTags = differenceTags;
        return this;
    }

    public List<String> selectedDifferenceTags() {
        return selectedDifferenceTags;
    }

    public ExaminationSetting selectedDifferenceTags(List<String> selectedDifferenceTags) {
        this.selectedDifferenceTags = selectedDifferenceTags;
        return this;
    }

    public List<String> generalTags() {
        return generalTags;
    }

    public ExaminationSetting generalTags(List<String> generalTags) {
        this.generalTags = generalTags;
        return this;
    }

    public Set<QuestionType> generalTypes() {
        return generalTypes;
    }

    public ExaminationSetting generalTypes(Set<QuestionType> generalTypes) {
        this.generalTypes = generalTypes;
        return this;
    }

    public Map<String, Object> generalQuestionNum() {
        return generalQuestionNum;
    }

    public ExaminationSetting generalQuestionNum(Map<String, Object> generalQuestionNum) {
        this.generalQuestionNum = generalQuestionNum;
        return this;
    }

    public boolean generatedGeneralExam() {
        return generatedGeneralExam;
    }

    public ExaminationSetting generatedGeneralExam(boolean generatedGeneralExam) {
        this.generatedGeneralExam = generatedGeneralExam;
        return this;
    }

    public String generalDifficultyDegree() {
        return generalDifficultyDegree;
    }

    public ExaminationSetting generalDifficultyDegree(String generalDifficultyDegree) {
        this.generalDifficultyDegree = generalDifficultyDegree;
        return this;
    }

    public Set<String> professionalTags() {
        return professionalTags;
    }

    public ExaminationSetting professionalTags(Set<String> professionalTags) {
        this.professionalTags = professionalTags;
        return this;
    }

    public Map<String, Object> professionalQuestionNum() {
        return professionalQuestionNum;
    }

    public ExaminationSetting professionalQuestionNum(Map<String, Object> professionalQuestionNum) {
        this.professionalQuestionNum = professionalQuestionNum;
        return this;
    }

    public boolean generatedProfessionalExam() {
        return generatedProfessionalExam;
    }

    public ExaminationSetting generatedProfessionalExam(boolean generatedProfessionalExam) {
        this.generatedProfessionalExam = generatedProfessionalExam;
        return this;
    }

    public String professionalDifficultyDegree() {
        return professionalDifficultyDegree;
    }

    public ExaminationSetting professionalDifficultyDegree(String professionalDifficultyDegree) {
        this.professionalDifficultyDegree = professionalDifficultyDegree;
        return this;
    }


}

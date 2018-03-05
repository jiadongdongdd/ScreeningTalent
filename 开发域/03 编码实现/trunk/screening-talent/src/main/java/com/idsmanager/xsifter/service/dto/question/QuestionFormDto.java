package com.idsmanager.xsifter.service.dto.question;

import com.idsmanager.xsifter.domain.question.QuestionDegree;
import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.domain.question.SuitableType;
import com.idsmanager.xsifter.domain.question.Tag;
import com.idsmanager.xsifter.service.dto.position.PositionDto;
import com.idsmanager.xsifter.service.dto.position.PositionFormDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZW on 2016/9/20.
 */
public class QuestionFormDto implements Serializable {
    private static final long serialVersionUID = 918919323417628436L;

    //题目
    private String title;
    //试题类型
    private QuestionType type;
    //单选题或多选题选项
    private List<String> options;
    //试题答案
    private String answer;
    //试题标签
    private List<String> tags;
    //试题职位Uuid
    private String positionUuid;

    private List<PositionDto> positions;
    //应用场景
    private SuitableType suitableType;

    private QuestionDegree degree;


    public QuestionFormDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPositionUuid() {
        return positionUuid;
    }

    public void setPositionUuid(String positionUuid) {
        this.positionUuid = positionUuid;
    }

    public List<PositionDto> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionDto> positions) {
        this.positions = positions;
    }

    public QuestionType[] getTypes() {
        return QuestionType.values();
    }

    public SuitableType[] getSuitableTypes() {
        return SuitableType.values();
    }

    public QuestionDegree[] getDegrees() {
        return QuestionDegree.values();
    }

    public SuitableType getSuitableType() {
        return suitableType;
    }

    public void setSuitableType(SuitableType suitableType) {
        this.suitableType = suitableType;
    }

    public QuestionDegree getDegree() {
        return degree;
    }

    public void setDegree(QuestionDegree degree) {
        this.degree = degree;
    }
}

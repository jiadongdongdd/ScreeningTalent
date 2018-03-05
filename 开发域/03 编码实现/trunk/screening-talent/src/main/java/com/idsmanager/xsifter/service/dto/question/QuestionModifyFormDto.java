package com.idsmanager.xsifter.service.dto.question;

import com.idsmanager.xsifter.domain.question.Question;
import com.idsmanager.xsifter.domain.question.QuestionDegree;
import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.domain.question.SuitableType;
import com.idsmanager.xsifter.service.dto.position.PositionDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2016/9/21.
 */
public class QuestionModifyFormDto implements Serializable {
    private static final long serialVersionUID = -2484523479915560179L;

    private String uuid;

    private String number;
    //题目
    private String title;
    //试题类型
    private QuestionType type;
    //单选题或多选题选项
    private List<String> options = new ArrayList<>();
    //选项
    private List<OptionDto> optionDtos = new ArrayList<>();
    //试题答案
    private String answer;
    //试题标签
    private List<String> tags = new ArrayList<>();

    //已选择的标签
    private List<TagDto> selectedTags = new ArrayList<>();
    //试题难度
    private QuestionDegree degree;
    //适合场景
    private SuitableType suitableType;
    //试题职位Uuid
    private String positionUuid;

    private List<PositionDto> positions = new ArrayList<>();

    public QuestionModifyFormDto() {
    }

    public QuestionModifyFormDto(Question question) {
        this.uuid = question.getUuid();
        this.number = question.number();
        this.title = question.title();
        this.type = question.type();
        this.options = question.options();
        this.answer = question.answer();
        this.tags = question.tags();
        this.degree = question.degree();
        this.suitableType = question.suitableType();
        this.positionUuid = question.positionUuid();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public List<TagDto> getSelectedTags() {
        return selectedTags;
    }

    public void setSelectedTags(List<TagDto> selectedTags) {
        this.selectedTags = selectedTags;
    }

    public List<OptionDto> getOptionDtos() {
        return optionDtos;
    }

    public void setOptionDtos(List<OptionDto> optionDtos) {
        this.optionDtos = optionDtos;
    }

    public QuestionType[] getTypes() {
        return QuestionType.values();
    }

    public QuestionDegree getDegree() {
        return degree;
    }

    public void setDegree(QuestionDegree degree) {
        this.degree = degree;
    }

    public SuitableType getSuitableType() {
        return suitableType;
    }

    public void setSuitableType(SuitableType suitableType) {
        this.suitableType = suitableType;
    }

    public QuestionDegree[] getDegrees() {
        return QuestionDegree.values();
    }

    public SuitableType[] getSuitableTypes() {
        return SuitableType.values();
    }
}

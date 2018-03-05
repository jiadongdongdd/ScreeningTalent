package com.idsmanager.xsifter.service.dto.examination;

import com.idsmanager.xsifter.domain.question.Question;
import com.idsmanager.xsifter.service.dto.position.PositionDto;
import com.idsmanager.xsifter.service.dto.question.TagDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2016/9/30.
 */
public class GeneralExaminationResultDto implements Serializable {
    private static final long serialVersionUID = 4174816932121064654L;

    private String memberUuid;
    private String positionUuid;

    //单选题
    private List<Question> singleQuestions = new ArrayList<>();
    private List<String> singleAnswers = new ArrayList<>();

    //多选题
    private List<Question> multipleQuestions = new ArrayList<>();
    private List<String> multipleAnswers = new ArrayList<>();

    //简答题
    private List<Question> shortQuestions = new ArrayList<>();
    private List<String> shortAnswers = new ArrayList<>();

    //简答题评分
    private List<String> scores = new ArrayList<>();

    //单选题分数
    private float singleGrade;
    //多选题分数
    private float multipleGrade;
    //简答题分数
    private float shortGrade;

    //所选标签
    private List<TagDto> tags = new ArrayList<>();
    //基本信息
    private BasicInfoDto infoDto;
    //职位信息
    private PositionDto positionDto;

    public GeneralExaminationResultDto() {
    }

    public BasicInfoDto getInfoDto() {
        return infoDto;
    }

    public void setInfoDto(BasicInfoDto infoDto) {
        this.infoDto = infoDto;
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

    public List<Question> getSingleQuestions() {
        return singleQuestions;
    }

    public void setSingleQuestions(List<Question> singleQuestions) {
        this.singleQuestions = singleQuestions;
    }

    public List<String> getSingleAnswers() {
        return singleAnswers;
    }

    public void setSingleAnswers(List<String> singleAnswers) {
        this.singleAnswers = singleAnswers;
    }

    public List<Question> getMultipleQuestions() {
        return multipleQuestions;
    }

    public void setMultipleQuestions(List<Question> multipleQuestions) {
        this.multipleQuestions = multipleQuestions;
    }

    public List<String> getMultipleAnswers() {
        return multipleAnswers;
    }

    public void setMultipleAnswers(List<String> multipleAnswers) {
        this.multipleAnswers = multipleAnswers;
    }

    public List<Question> getShortQuestions() {
        return shortQuestions;
    }

    public void setShortQuestions(List<Question> shortQuestions) {
        this.shortQuestions = shortQuestions;
    }

    public List<String> getShortAnswers() {
        return shortAnswers;
    }

    public void setShortAnswers(List<String> shortAnswers) {
        this.shortAnswers = shortAnswers;
    }

    public List<String> getScores() {
        return scores;
    }

    public void setScores(List<String> scores) {
        this.scores = scores;
    }

    public float getSingleGrade() {
        return singleGrade;
    }

    public void setSingleGrade(float singleGrade) {
        this.singleGrade = singleGrade;
    }

    public float getMultipleGrade() {
        return multipleGrade;
    }

    public void setMultipleGrade(float multipleGrade) {
        this.multipleGrade = multipleGrade;
    }

    public float getShortGrade() {
        return shortGrade;
    }

    public void setShortGrade(float shortGrade) {
        this.shortGrade = shortGrade;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    public PositionDto getPositionDto() {
        return positionDto;
    }

    public void setPositionDto(PositionDto positionDto) {
        this.positionDto = positionDto;
    }
}

package com.idsmanager.xsifter.service.dto.examination;

import com.idsmanager.xsifter.domain.exam.GeneralExaminationResult;
import com.idsmanager.xsifter.domain.question.Question;
import com.idsmanager.xsifter.service.dto.question.QuestionDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2016/9/26.
 */
public class GeneralExaminationResultFormDto implements Serializable {
    private static final long serialVersionUID = -1675780453282550096L;

    private String memberUuid;
    private String positionUuid;

    //综合设置是否存在
    private boolean setting = true;

    //是否已经录入答案 false：否 true:是
    private boolean existed = false;

    private List<QuestionDto> questionDtos = new ArrayList<>();

    private List<String> questions = new ArrayList<>();
    //单选题
    private List<QuestionDto> singleQuestionDtos = new ArrayList<>();
    private List<String> singleQuestions = new ArrayList<>();
    private List<String> singleAnswers = new ArrayList<>();

    //多选题
    private List<QuestionDto> multipleQuestionDtos = new ArrayList<>();
    private List<String> multipleQuestions = new ArrayList<>();
    private List<String> multipleAnswers = new ArrayList<>();

    //简答题
    private List<QuestionDto> shortQuestionDtos = new ArrayList<>();
    private List<String> shortQuestions = new ArrayList<>();
    private List<String> shortAnswers = new ArrayList<>();

    //简答题评分
    private List<String> scores = new ArrayList<>();


    public GeneralExaminationResultFormDto() {
    }

    public GeneralExaminationResultFormDto(GeneralExaminationResult result) {
        this.memberUuid = result.memberUuid();
        this.positionUuid = result.positionUuid();
        this.singleQuestions = result.singleQuestionIds();
        this.multipleQuestions = result.multipleQuestionIds();
        this.shortQuestions = result.shortQuestionIds();
        this.singleAnswers = result.singleAnswers();
        this.multipleAnswers = result.multipleAnswers();
        this.shortAnswers = result.shortAnswers();
        this.scores = result.scores();
    }

    public GeneralExaminationResultFormDto(boolean setting) {
        this.setting = setting;
    }

    public boolean isExisted() {
        return existed;
    }

    public void setExisted(boolean existed) {
        this.existed = existed;
    }

    public boolean isSetting() {
        return setting;
    }

    public void setSetting(boolean setting) {
        this.setting = setting;
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

    public List<QuestionDto> getQuestionDtos() {
        return questionDtos;
    }

    public void setQuestionDtos(List<QuestionDto> questionDtos) {
        this.questionDtos = questionDtos;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<QuestionDto> getSingleQuestionDtos() {
        return singleQuestionDtos;
    }

    public void setSingleQuestionDtos(List<QuestionDto> singleQuestionDtos) {
        this.singleQuestionDtos = singleQuestionDtos;
    }

    public List<String> getSingleQuestions() {
        return singleQuestions;
    }

    public void setSingleQuestions(List<String> singleQuestions) {
        this.singleQuestions = singleQuestions;
    }

    public List<QuestionDto> getMultipleQuestionDtos() {
        return multipleQuestionDtos;
    }

    public void setMultipleQuestionDtos(List<QuestionDto> multipleQuestionDtos) {
        this.multipleQuestionDtos = multipleQuestionDtos;
    }

    public List<String> getMultipleQuestions() {
        return multipleQuestions;
    }

    public void setMultipleQuestions(List<String> multipleQuestions) {
        this.multipleQuestions = multipleQuestions;
    }

    public List<QuestionDto> getShortQuestionDtos() {
        return shortQuestionDtos;
    }

    public void setShortQuestionDtos(List<QuestionDto> shortQuestionDtos) {
        this.shortQuestionDtos = shortQuestionDtos;
    }

    public List<String> getShortQuestions() {
        return shortQuestions;
    }

    public void setShortQuestions(List<String> shortQuestions) {
        this.shortQuestions = shortQuestions;
    }

    public List<String> getSingleAnswers() {
        return singleAnswers;
    }

    public void setSingleAnswers(List<String> singleAnswers) {
        this.singleAnswers = singleAnswers;
    }

    public List<String> getMultipleAnswers() {
        return multipleAnswers;
    }

    public void setMultipleAnswers(List<String> multipleAnswers) {
        this.multipleAnswers = multipleAnswers;
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
}

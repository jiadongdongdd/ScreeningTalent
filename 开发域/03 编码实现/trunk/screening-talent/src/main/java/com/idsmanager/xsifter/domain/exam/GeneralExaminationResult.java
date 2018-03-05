package com.idsmanager.xsifter.domain.exam;

import com.idsmanager.xsifter.domain.AbstractDomain;
import com.idsmanager.xsifter.domain.question.Question;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2016/9/27.
 */
@Document(collection = "GeneralExaminationResult")
public class GeneralExaminationResult extends AbstractDomain {
    private static final long serialVersionUID = -3267836815930874816L;

    private String memberUuid;

    private String positionUuid;

    //单选题
    private List<Question> singleQuestions = new ArrayList<>();
    private List<String> singleAnswers = new ArrayList<>();
    private List<String> singleQuestionIds = new ArrayList<>();

    //多选题
    private List<Question> multipleQuestions = new ArrayList<>();
    private List<String> multipleAnswers = new ArrayList<>();
    private List<String> multipleQuestionIds = new ArrayList<>();

    //简答题
    private List<Question> shortQuestions = new ArrayList<>();
    private List<String> shortAnswers = new ArrayList<>();
    private List<String> shortQuestionIds = new ArrayList<>();

    //简答题评分
    private List<String> scores = new ArrayList<>();

    //单选题分数
    private float singleGrade;
    //多选题分数
    private float multipleGrade;
    //简答题分数
    private float shortGrade;

    public GeneralExaminationResult() {
    }

    public List<String> singleQuestionIds() {
        return singleQuestionIds;
    }

    public GeneralExaminationResult singleQuestionIds(List<String> singleQuestionIds) {
        this.singleQuestionIds = singleQuestionIds;
        return this;
    }

    public List<String> multipleQuestionIds() {
        return multipleQuestionIds;
    }

    public GeneralExaminationResult multipleQuestionIds(List<String> multipleQuestionIds) {
        this.multipleQuestionIds = multipleQuestionIds;
        return this;
    }

    public List<String> shortQuestionIds() {
        return shortQuestionIds;
    }

    public GeneralExaminationResult shortQuestionIds(List<String> shortQuestionIds) {
        this.shortQuestionIds = shortQuestionIds;
        return this;
    }

    public String memberUuid() {
        return memberUuid;
    }

    public GeneralExaminationResult memberUuid(String memberUuid) {
        this.memberUuid = memberUuid;
        return this;
    }

    public String positionUuid() {
        return positionUuid;
    }

    public GeneralExaminationResult positionUuid(String positionUuid) {
        this.positionUuid = positionUuid;
        return this;
    }

    public List<Question> singleQuestions() {
        return singleQuestions;
    }

    public GeneralExaminationResult singleQuestions(List<Question> singleQuestions) {
        this.singleQuestions = singleQuestions;
        return this;
    }

    public List<String> singleAnswers() {
        return singleAnswers;
    }

    public GeneralExaminationResult singleAnswers(List<String> singleAnswers) {
        this.singleAnswers = singleAnswers;
        return this;
    }

    public List<Question> multipleQuestions() {
        return multipleQuestions;
    }

    public GeneralExaminationResult multipleQuestions(List<Question> multipleQuestions) {
        this.multipleQuestions = multipleQuestions;
        return this;
    }

    public List<String> multipleAnswers() {
        return multipleAnswers;
    }

    public GeneralExaminationResult multipleAnswers(List<String> multipleAnswers) {
        this.multipleAnswers = multipleAnswers;
        return this;
    }

    public List<Question> shortQuestions() {
        return shortQuestions;
    }

    public GeneralExaminationResult shortQuestions(List<Question> shortQuestions) {
        this.shortQuestions = shortQuestions;
        return this;
    }

    public List<String> shortAnswers() {
        return shortAnswers;
    }

    public GeneralExaminationResult shortAnswers(List<String> shortAnswers) {
        this.shortAnswers = shortAnswers;
        return this;
    }

    public List<String> scores() {
        return scores;
    }

    public GeneralExaminationResult scores(List<String> scores) {
        this.scores = scores;
        return this;
    }

    public float singleGrade() {
        return singleGrade;
    }

    public GeneralExaminationResult singleGrade(float singleGrade) {
        this.singleGrade = singleGrade;
        return this;
    }

    public float multipleGrade() {
        return multipleGrade;
    }

    public GeneralExaminationResult multipleGrade(float multipleGrade) {
        this.multipleGrade = multipleGrade;
        return this;
    }

    public float shortGrade() {
        return shortGrade;
    }

    public GeneralExaminationResult shortGrade(float shortGrade) {
        this.shortGrade = shortGrade;
        return this;
    }


}

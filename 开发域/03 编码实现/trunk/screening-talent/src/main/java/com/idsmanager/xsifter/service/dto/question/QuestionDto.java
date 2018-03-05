package com.idsmanager.xsifter.service.dto.question;

import com.idsmanager.xsifter.domain.question.Question;
import com.idsmanager.xsifter.domain.question.QuestionDegree;
import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.domain.question.SuitableType;
import com.idsmanager.xsifter.service.dto.AbstractDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by LZW on 2016/9/20.
 */
public class QuestionDto extends AbstractDto {
    private static final long serialVersionUID = -2637090285214271435L;

    //试题编号
    private String number;
    //题目
    private String title;
    //试题类型
    private QuestionType type;
    //单选题或多选题选项
    private List<String> options;
    //选项
    private List<OptionDto> optionDtos;
    //试题答案
    private String answer;
    //试题标签
    private List<String> tags;
    //标签
    private List<TagDto> tagDtos;
    //试题难度
    private QuestionDegree degree;
    private SuitableType suitableType;
    //被使用次数
    private int useTimes;
    //正确次数
    private int correctTimes;
    //正确率
    private int correctRate;
    //满意度
    private int satisfaction;

    public QuestionDto() {
    }

    public QuestionDto(Question question) {
        super(question);
        this.number = question.number();
        this.title = question.title();
        this.type = question.type();
        this.options = question.options();
        this.answer = question.answer();
        this.tags = question.tags();
        this.tagDtos = question.tagDtos();
        this.degree = question.degree();
        this.useTimes = question.useTimes();
        this.correctTimes = question.correctTimes();
        this.correctRate = question.correctRate();
        this.satisfaction = question.satisfaction();
        this.suitableType = question.suitableType();
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

    public int getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(int useTimes) {
        this.useTimes = useTimes;
    }

    public int getCorrectTimes() {
        return correctTimes;
    }

    public void setCorrectTimes(int correctTimes) {
        this.correctTimes = correctTimes;
    }

    public int getCorrectRate() {
        return correctRate;
    }

    public void setCorrectRate(int correctRate) {
        this.correctRate = correctRate;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public List<TagDto> getTagDtos() {
        return tagDtos;
    }

    public void setTagDtos(List<TagDto> tagDtos) {
        this.tagDtos = tagDtos;
    }

    public List<OptionDto> getOptionDtos() {
        return optionDtos;
    }

    public void setOptionDtos(List<OptionDto> optionDtos) {
        this.optionDtos = optionDtos;
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

    public static List<QuestionDto> toDtos(List<Question> list) {
        List<QuestionDto> dtos = new ArrayList<>(list.size());
        dtos.addAll(list.stream().map(QuestionDto::new).collect(Collectors.toList()));
        return dtos;
    }
}

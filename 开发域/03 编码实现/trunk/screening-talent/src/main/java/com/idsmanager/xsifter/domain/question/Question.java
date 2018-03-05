package com.idsmanager.xsifter.domain.question;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.AbstractDomain;
import com.idsmanager.xsifter.service.dto.question.TagDto;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2016/9/14.
 */
//试题
@Document(collection = "Question")
public class Question extends AbstractDomain {
    private static final long serialVersionUID = 2101517425596191862L;

    @Transient
    private transient TagRepository tagRepository = BeanProvider.getBean(TagRepository.class);

    //试题编号
    private String number;
    //题目
    private String title;
    //试题类型
    private QuestionType type;
    //单选题或多选题选项
    private List<String> options = new ArrayList<>();
    //试题答案
    private String answer;
    //试题标签
    private List<String> tags = new ArrayList<>();
    //适合场景
    private SuitableType suitableType;
    //试题难度
    private QuestionDegree degree;
    //试题职位Uuid
    private String positionUuid;
    //被使用次数
    private int useTimes;
    //正确次数
    private int correctTimes;
    //正确率
    private int correctRate;
    //满意度
    private int satisfaction;

    public Question() {
    }

    public String number() {
        return number;
    }

    public Question number(String number) {
        this.number = number;
        return this;
    }

    public String title() {
        return title;
    }

    public Question title(String title) {
        this.title = title;
        return this;
    }

    public QuestionType type() {
        return type;
    }

    public Question type(QuestionType type) {
        this.type = type;
        return this;
    }

    public List<String> options() {
        return options;
    }

    public Question options(List<String> options) {
        this.options = options;
        return this;
    }

    public String answer() {
        return answer;
    }

    public Question answer(String answer) {
        this.answer = answer;
        return this;
    }

    public List<String> tags() {
        return tags;
    }

    public Question tags(List<String> tags) {
        this.tags = tags;
        return this;
    }


    public int useTimes() {
        return useTimes;
    }

    public Question useTimes(int useTimes) {
        this.useTimes = useTimes;
        return this;
    }

    public int correctTimes() {
        return correctTimes;
    }

    public Question correctTimes(int correctTimes) {
        this.correctTimes = correctTimes;
        return this;
    }

    public int correctRate() {
        return correctRate;
    }

    public Question correctRate(int correctRate) {
        this.correctRate = correctRate;
        return this;
    }

    public int satisfaction() {
        return satisfaction;
    }

    public Question satisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
        return this;
    }

    public String positionUuid() {
        return positionUuid;
    }

    public Question positionUuid(String positionUuid) {
        this.positionUuid = positionUuid;
        return this;
    }

    public SuitableType suitableType() {
        return suitableType;
    }

    public Question suitableType(SuitableType suitableType) {
        this.suitableType = suitableType;
        return this;
    }

    public QuestionDegree degree() {
        return degree;
    }

    public Question degree(QuestionDegree degree) {
        this.degree = degree;
        return this;
    }

    public List<TagDto> tagDtos() {
        List<TagDto> dtos = new ArrayList<>();
        if (tags != null && !tags.isEmpty()) {
            for (String id : tags) {
                Tag tag = tagRepository.findTagById(id);
                dtos.add(new TagDto(tag));
            }
        }
        return dtos;
    }
}

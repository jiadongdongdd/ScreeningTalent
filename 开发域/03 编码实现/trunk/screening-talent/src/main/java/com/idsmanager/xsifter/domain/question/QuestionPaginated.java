package com.idsmanager.xsifter.domain.question;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.service.dto.question.QuestionDto;

import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2016/9/20.
 */
public class QuestionPaginated extends DefaultPaginated<QuestionDto> {

    private String title;

    private QuestionType questionType;

    @Override
    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.queryMap();
        map.put("title", title);
        map.put("questionType", questionType);
        return map;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public List<QuestionType> getQuestionTypes(){
        return QuestionType.allQuestionTypes();
    }
}

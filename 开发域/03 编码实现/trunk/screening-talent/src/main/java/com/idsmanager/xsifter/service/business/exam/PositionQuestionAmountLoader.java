package com.idsmanager.xsifter.service.business.exam;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.question.QuestionDegree;
import com.idsmanager.xsifter.domain.question.QuestionRepository;
import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.service.dto.examination.QuestionQueryResult;
import com.idsmanager.xsifter.service.dto.examination.QuestionQueryTags;

import java.util.List;

/**
 * Created by LZW on 2016/9/29.
 */
public class PositionQuestionAmountLoader {

    private transient QuestionRepository questionRepository = BeanProvider.getBean(QuestionRepository.class);

    private QuestionQueryTags tags;

    public PositionQuestionAmountLoader(QuestionQueryTags tags) {
        this.tags = tags;
    }

    public QuestionQueryResult load() {
        QuestionQueryResult result = new QuestionQueryResult();
        QuestionDegree degree = tags.getDegree();
        String positionUuid = tags.getPositionUuid();
        return loadTotalAmountByPosition(positionUuid, result, degree);
    }

    private QuestionQueryResult loadTotalAmountByPosition(String positionUuid, QuestionQueryResult result, QuestionDegree degree) {
        //选择标签
        long amount = questionRepository.findQuestionAmountByDegreeAndPositionUuid(degree, positionUuid);
        result.setAmount(amount);
        long singleAmount = questionRepository.findQuestionAmountByTypeAndDegreeAndPositionUuid(QuestionType.SINGLE_CHOICE, degree, positionUuid);
        result.setSingleAmount(singleAmount);
        long multipleAmount = questionRepository.findQuestionAmountByTypeAndDegreeAndPositionUuid(QuestionType.MULTIPLE_CHOICE, degree, positionUuid);
        result.setMultipleAmount(multipleAmount);
        long shortAmount = questionRepository.findQuestionAmountByTypeAndDegreeAndPositionUuid(QuestionType.SHORT_ANSWER, degree, positionUuid);
        result.setShortAmount(shortAmount);
        return result;
    }
}

package com.idsmanager.xsifter.service.business.exam;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.question.QuestionDegree;
import com.idsmanager.xsifter.domain.question.QuestionRepository;
import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.service.dto.examination.QuestionQueryResult;
import com.idsmanager.xsifter.service.dto.examination.QuestionQueryTags;

import java.util.List;

/**
 * Created by LZW on 2016/9/23.
 */
public class QuestionAmountLoader {

    private transient QuestionRepository questionRepository = BeanProvider.getBean(QuestionRepository.class);

    private QuestionQueryTags tags;

    public QuestionAmountLoader(QuestionQueryTags tags) {
        this.tags = tags;
    }

    public QuestionQueryResult load() {
        QuestionQueryResult result = new QuestionQueryResult();
        List<String> tagsIds = tags.getTags();
        QuestionDegree degree = tags.getDegree();
        //未设置标签
        if (null == tagsIds || tagsIds.isEmpty()) {
            return loadTotalAmount(result, degree);
        }


        return loadTotalAmountByTags(tagsIds, result, degree);
    }

    private QuestionQueryResult loadTotalAmountByTags(List<String> tagsIds, QuestionQueryResult result, QuestionDegree degree) {
        //选择标签
        long amount = questionRepository.findQuestionAmountByTagsAndDegree(tagsIds, degree);
        result.setAmount(amount);
        long singleAmount = questionRepository.findQuestionAmountByTagsAndTypeAndDegree(tagsIds, QuestionType.SINGLE_CHOICE, degree);
        result.setSingleAmount(singleAmount);
        long multipleAmount = questionRepository.findQuestionAmountByTagsAndTypeAndDegree(tagsIds, QuestionType.MULTIPLE_CHOICE, degree);
        result.setMultipleAmount(multipleAmount);
        long shortAmount = questionRepository.findQuestionAmountByTagsAndTypeAndDegree(tagsIds, QuestionType.SHORT_ANSWER, degree);
        result.setShortAmount(shortAmount);
        return result;
    }

    private QuestionQueryResult loadTotalAmount(QuestionQueryResult result, QuestionDegree degree) {
        QuestionDegree mediumDifficulty = degree;
        long amount = questionRepository.findQuestionAmountByDegree(mediumDifficulty);
        long singleAmount = questionRepository.findQuestionAmountByTypeAndDegree(QuestionType.SINGLE_CHOICE, mediumDifficulty);
        long multipleAmount = questionRepository.findQuestionAmountByTypeAndDegree(QuestionType.MULTIPLE_CHOICE, mediumDifficulty);
        long shortAmount = questionRepository.findQuestionAmountByTypeAndDegree(QuestionType.SHORT_ANSWER, mediumDifficulty);
        result.setAmount(amount);
        result.setSingleAmount(singleAmount);
        result.setMultipleAmount(multipleAmount);
        result.setShortAmount(shortAmount);
        return result;
    }
}

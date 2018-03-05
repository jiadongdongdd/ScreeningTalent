package com.idsmanager.xsifter.domain.question;

import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2016/9/20.
 */
public interface QuestionRepository {
    List<Question> findQuestionPaginatedList(Map<String, Object> map);

    long totalQuestionPaginatedList(Map<String, Object> map);

    void saveQuestion(Question question);

    Question findQuestionByUuid(String uuid);

    void remove(Question question);

    long findQuestionAmountByDegree(QuestionDegree degree);

    Question findQuestionByNumber(String number);

    void updateQuestion(Question question);

    long findQuestionAmountByTagsAndDegree(List<String> tags, QuestionDegree degree);

    long findQuestionAmountByTypeAndDegree(QuestionType type, QuestionDegree degree);

    long findQuestionAmountByTagsAndTypeAndDegree(List<String> tags, QuestionType type, QuestionDegree degree);

    List<Question> findQuestionByTagsAndTypeAndDegree(List<String> tags, QuestionType type, QuestionDegree difficultyDegree);

    long findQuestionAmount();

    long findQuestionAmountByDegreeAndPositionUuid(QuestionDegree degree, String positionUuid);

    long findQuestionAmountByTypeAndDegreeAndPositionUuid(QuestionType type, QuestionDegree degree, String positionUuid);

    List<Question> findQuestionsByTagAndDegree(String tag, QuestionDegree degree);
}

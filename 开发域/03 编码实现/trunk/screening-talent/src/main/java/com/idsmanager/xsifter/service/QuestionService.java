package com.idsmanager.xsifter.service;

import com.idsmanager.xsifter.domain.question.QuestionPaginated;
import com.idsmanager.xsifter.service.dto.question.QuestionFormDto;
import com.idsmanager.xsifter.service.dto.question.QuestionModifyFormDto;
import com.idsmanager.xsifter.service.dto.question.QuestionSubmitDto;
import com.idsmanager.xsifter.service.dto.question.TagDataDto;

/**
 * Created by LZW on 2016/9/20.
 */
public interface QuestionService {
    QuestionPaginated loadQuestionPaginated(QuestionPaginated paginated);

    QuestionFormDto loadQuestionFormDto();

    TagDataDto loadFirstLevelTagDataDto();

    TagDataDto loadOtherTagDataDto(String pId);

    QuestionSubmitDto createQuestion(QuestionFormDto formDto);

    void removeQuestion(String uuid);

    QuestionModifyFormDto loadQuestionModifyFormDto(String uuid);

    QuestionSubmitDto editQuestion(QuestionModifyFormDto formDto);
}

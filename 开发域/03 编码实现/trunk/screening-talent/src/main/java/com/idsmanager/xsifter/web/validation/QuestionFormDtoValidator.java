package com.idsmanager.xsifter.web.validation;

import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.service.dto.question.QuestionFormDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by LZW on 2016/9/21.
 */
@Component
public class QuestionFormDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return QuestionFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        QuestionFormDto formDto = (QuestionFormDto) target;
        validateQuestionTitle(formDto, errors);
        validateQuestionType(formDto, errors);
        validateQuestionOptions(formDto, errors);
        validateQuestionAnswer(formDto, errors);
        validateQuestionTags(formDto, errors);
        validateQuestionDegree(formDto, errors);
    }

    private void validateQuestionDegree(QuestionFormDto formDto, Errors errors) {

    }



    private void validateQuestionTags(QuestionFormDto formDto, Errors errors) {
        final List<String> tags = formDto.getTags();
        if (null == tags || tags.isEmpty()) {
            errors.rejectValue("tags", null, "请选择标签");
        }
    }

    private void validateQuestionAnswer(QuestionFormDto formDto, Errors errors) {
        final String answer = formDto.getAnswer();
        if (StringUtils.isEmpty(answer)) {
            errors.rejectValue("answer", null, "答案不能为空");
        }
    }

    private void validateQuestionOptions(QuestionFormDto formDto, Errors errors) {
        final List<String> options = formDto.getOptions();
        final QuestionType type = formDto.getType();
        if (QuestionType.SINGLE_CHOICE.equals(type) || QuestionType.MULTIPLE_CHOICE.equals(type)) {
            if (null == options || options.isEmpty()) {
                errors.rejectValue("options", null, "选项不能为空");
            } else {
                for (String option : options) {
                    if (StringUtils.isEmpty(option)) {
                        errors.rejectValue("options", null, "选项不能为空");
                    }
                }
            }
        }
    }

    private void validateQuestionType(QuestionFormDto formDto, Errors errors) {
        final QuestionType type = formDto.getType();
        if (null == type) {
            errors.rejectValue("type", null, "题目类型不能为空");
        }
    }

    private void validateQuestionTitle(QuestionFormDto formDto, Errors errors) {
        final String title = formDto.getTitle();
        if (StringUtils.isEmpty(title)) {
            errors.rejectValue("title", null, "题目不能为空");
        }
    }
}

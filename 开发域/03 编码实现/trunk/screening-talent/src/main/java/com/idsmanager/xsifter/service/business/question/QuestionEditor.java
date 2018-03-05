package com.idsmanager.xsifter.service.business.question;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.question.*;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.question.QuestionFormDto;
import com.idsmanager.xsifter.service.dto.question.QuestionModifyFormDto;
import com.idsmanager.xsifter.service.dto.question.QuestionSubmitDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2016/9/22.
 */
public class QuestionEditor {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionEditor.class);

    private transient QuestionRepository questionRepository = BeanProvider.getBean(QuestionRepository.class);

    private QuestionModifyFormDto formDto;

    public QuestionEditor(QuestionModifyFormDto formDto) {
        this.formDto = formDto;
    }

    public QuestionSubmitDto edit() {

        Question question = questionRepository.findQuestionByUuid(formDto.getUuid());

        QuestionSubmitDto dto = new QuestionSubmitDto();
        //非标准题目
        QuestionType type = formDto.getType();
        if (QuestionType.SHORT_ANSWER.equals(type)) {
            dto.setStandard(false);
        }
        //验证输入参数
        boolean validate = validate(dto);
        if (validate) {
            question = updateQuestion(question);
            questionRepository.updateQuestion(question);
            LOG.debug("{}|Update Question:{}", SecurityUtils.username(), question);
        }
        return dto;
    }

    private Question updateQuestion(Question question) {
        return question.positionUuid(formDto.getPositionUuid())
                .title(formDto.getTitle())
                .tags(formDto.getTags())
                .options(formDto.getOptions())
                .answer(getAnswer())
                .degree(formDto.getDegree())
                .type(formDto.getType())
                .suitableType(formDto.getSuitableType());
    }

    private String getAnswer() {
        String answers = "";
        String answer = formDto.getAnswer();
        QuestionType type = formDto.getType();
        if (QuestionType.MULTIPLE_CHOICE.equals(type)) {
            String[] split = answer.split(",");
            for (String s : split) {
                answers += s;
            }
            return answers;
        }
        return answer;

    }

    private boolean validate(QuestionSubmitDto dto) {
        Map<String, Object> errors = dto.getErrors();
        validateQuestionTitle(formDto, errors);
        validateQuestionType(formDto, errors);
        validateQuestionOptions(formDto, errors);
        validateQuestionAnswer(formDto, errors);
        validateQuestionTags(formDto, errors);
        validateQuestionDegree(formDto, errors);
        validatePosition(formDto,errors);
        if (!errors.isEmpty()) {
            dto.setSuccess(false);
            return false;
        }
        return true;
    }

    private void validatePosition(QuestionModifyFormDto formDto, Map<String, Object> errors) {
        final SuitableType suitableType = formDto.getSuitableType();
        final String positionUuid = formDto.getPositionUuid();
        if (SuitableType.PROFESSIONAL.equals(suitableType)) {
            if (StringUtils.isEmpty(positionUuid)) {
                errors.put("positionUuid", "请选择适合职位");
            }
        }
    }

    private void validateQuestionDegree(QuestionModifyFormDto formDto, Map<String, Object> errors) {
        final QuestionDegree degree = formDto.getDegree();
        if (null == degree) {
            errors.put("degree", "请选择难度");
        }
    }

    private void validateQuestionTags(QuestionModifyFormDto formDto, Map<String, Object> errors) {
        final List<String> tags = formDto.getTags();
        final SuitableType suitableType = formDto.getSuitableType();
        if (SuitableType.GENERAL.equals(suitableType)) {
            if (null == tags || tags.isEmpty()) {
                errors.put("tags", "请选择标签");
            }
        }

    }

    private void validateQuestionAnswer(QuestionModifyFormDto formDto, Map<String, Object> errors) {
        final String answer = formDto.getAnswer();
        if (StringUtils.isEmpty(answer)) {
            errors.put("answer", "答案不能为空");
        }
    }

    private void validateQuestionOptions(QuestionModifyFormDto formDto, Map<String, Object> errors) {
        final List<String> options = formDto.getOptions();
        final QuestionType type = formDto.getType();
        if (QuestionType.SINGLE_CHOICE.equals(type) || QuestionType.MULTIPLE_CHOICE.equals(type)) {
            if (null == options || options.isEmpty()) {
                errors.put("options1", "选项不能为空");
            } else {
                for (String option : options) {
                    if (StringUtils.isEmpty(option)) {
                        errors.put("options1", "选项不能为空");
                    }
                }
            }
        }
    }

    private void validateQuestionType(QuestionModifyFormDto formDto, Map<String, Object> errors) {
        final QuestionType type = formDto.getType();
        if (null == type) {
            errors.put("type", "题目类型不能为空");
        }
    }

    private void validateQuestionTitle(QuestionModifyFormDto formDto, Map<String, Object> errors) {
        final String title = formDto.getTitle();
        if (StringUtils.isEmpty(title)) {
            errors.put("title", "题目不能为空");
        }
    }


}

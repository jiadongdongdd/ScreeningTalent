package com.idsmanager.xsifter.service.business.question;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.question.*;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.question.QuestionFormDto;
import com.idsmanager.xsifter.service.dto.question.QuestionSubmitDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2016/9/21.
 */
public class QuestionCreator {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionCreator.class);

    private transient QuestionRepository questionRepository = BeanProvider.getBean(QuestionRepository.class);

    private QuestionFormDto formDto;

    public QuestionCreator(QuestionFormDto formDto) {
        this.formDto = formDto;
    }

    public QuestionSubmitDto create() {
        QuestionSubmitDto dto = new QuestionSubmitDto();
        //非标准题目
        QuestionType type = formDto.getType();
        if (QuestionType.SHORT_ANSWER.equals(type)) {
            dto.setStandard(false);
        }
        //验证输入参数
        boolean validate = validate(dto);
        if (validate) {
            Question question = createQuestion();
            questionRepository.saveQuestion(question);
            LOG.debug("{}|Create Question:{}", SecurityUtils.username(), question);
        }
        return dto;
    }

    private boolean validate(QuestionSubmitDto dto) {
        Map<String, Object> errors = dto.getErrors();
        validateQuestionTitle(formDto, errors);
        validateQuestionType(formDto, errors);
        validateQuestionOptions(formDto, errors);
        validateQuestionAnswer(formDto, errors);
        validateQuestionTags(formDto, errors);
        validatePosition(formDto, errors);
        validateQuestionDegree(formDto, errors);
        if (!errors.isEmpty()) {
            dto.setSuccess(false);
            return false;
        }
        return true;
    }

    private void validatePosition(QuestionFormDto formDto, Map<String, Object> errors) {
        final SuitableType suitableType = formDto.getSuitableType();
        final String positionUuid = formDto.getPositionUuid();
        if (SuitableType.PROFESSIONAL.equals(suitableType)) {
            if (StringUtils.isEmpty(positionUuid)) {
                errors.put("positionUuid", "请选择适合职位");
            }
        }
    }

    private void validateQuestionDegree(QuestionFormDto formDto, Map<String, Object> errors) {
        final QuestionDegree degree = formDto.getDegree();
        if (null == degree) {
            errors.put("degree", "请选择难度");
        }
    }

    private void validateQuestionTags(QuestionFormDto formDto, Map<String, Object> errors) {
        final List<String> tags = formDto.getTags();
        final SuitableType suitableType = formDto.getSuitableType();
        if (SuitableType.GENERAL.equals(suitableType)) {
            if (null == tags || tags.isEmpty()) {
                errors.put("tags", "请选择标签");
            }
        }

    }

    private void validateQuestionAnswer(QuestionFormDto formDto, Map<String, Object> errors) {
        final String answer = formDto.getAnswer();
        if (StringUtils.isEmpty(answer)) {
            errors.put("answer", "答案不能为空");
        }
    }

    private void validateQuestionOptions(QuestionFormDto formDto, Map<String, Object> errors) {
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

    private void validateQuestionType(QuestionFormDto formDto, Map<String, Object> errors) {
        final QuestionType type = formDto.getType();
        if (null == type) {
            errors.put("type", "题目类型不能为空");
        }
    }

    private void validateQuestionTitle(QuestionFormDto formDto, Map<String, Object> errors) {
        final String title = formDto.getTitle();
        if (StringUtils.isEmpty(title)) {
            errors.put("title", "题目不能为空");
        }
    }

    private Question createQuestion() {

        return new Question().answer(getAnswer())
                .degree(formDto.getDegree())
                .options(formDto.getOptions())
                .tags(formDto.getTags())
                .title(formDto.getTitle())
                .type(formDto.getType())
                .number(getQuestionNumber())
                .positionUuid(formDto.getPositionUuid())
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

    private String getQuestionNumber() {
        String number = "";
        boolean flag = true;
        long total = questionRepository.findQuestionAmount();
        while (flag) {
            String tempNumber = String.valueOf(total);
            String zeroStr = "";
            if (tempNumber.length() < 7) {
                for (int i = 0; i < 7 - tempNumber.length(); i++) {
                    zeroStr += "0";
                }
            }
            tempNumber = zeroStr + tempNumber;
            Question question = questionRepository.findQuestionByNumber(tempNumber);
            if (null == question) {
                number = tempNumber;
                flag = false;
            } else {
                total += 1;
            }
        }
        return number;
    }

}

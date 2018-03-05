package com.idsmanager.xsifter.service.business.exam;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.exam.ExaminationRepository;
import com.idsmanager.xsifter.domain.exam.GeneralExaminationResult;
import com.idsmanager.xsifter.domain.exam.GeneralExaminationSetting;
import com.idsmanager.xsifter.domain.question.Question;
import com.idsmanager.xsifter.domain.question.QuestionRepository;
import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.examination.GeneralExaminationResultFormDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by LZW on 2016/9/27.
 */
public class GeneralExaminationResultCreator {

    private static final Logger LOG = LoggerFactory.getLogger(GeneralExaminationResultCreator.class);

    private transient ExaminationRepository examinationRepository = BeanProvider.getBean(ExaminationRepository.class);

    private transient QuestionRepository questionRepository = BeanProvider.getBean(QuestionRepository.class);

    private GeneralExaminationResultFormDto formDto;

    public GeneralExaminationResultCreator(GeneralExaminationResultFormDto formDto) {
        this.formDto = formDto;
    }

    public String create() {
        GeneralExaminationResult result = examinationRepository.findGeneralExaminationResultByMemberUuidAndPositionUuid(formDto.getMemberUuid(), formDto.getPositionUuid());
        if (result == null) {
            result = createGeneralExaminationResult();
            //保存试题
            putQuestions(result);
            //评分
            calculationResult(result);
            examinationRepository.saveGeneralExaminationResult(result);
            LOG.debug("{}|Create General Examination Result:{}", SecurityUtils.username(), result);
        } else {
            result = updateGeneralExaminationResult(result);
            //评分
            calculationResult(result);
            examinationRepository.updateGeneralExaminationResult(result);
            LOG.debug("{}|Update General Examination Result:{}", SecurityUtils.username(), result);
        }

        return result.getUuid();
    }

    private GeneralExaminationResult updateGeneralExaminationResult(GeneralExaminationResult result) {
        return result.multipleAnswers(formDto.getMultipleAnswers())
                .scores(formDto.getScores())
                .shortAnswers(formDto.getShortAnswers())
                .singleAnswers(formDto.getSingleAnswers());
    }

    private void calculationResult(GeneralExaminationResult result) {
        float average = getAverageGrade();
        //单选题
        List<Question> singleQuestions = result.singleQuestions();
        List<String> singleAnswers = result.singleAnswers();
        float singleGrade = getGrade(singleQuestions, singleAnswers, average);

        //多选题
        List<Question> multipleQuestions = result.multipleQuestions();
        List<String> multipleAnswers = result.multipleAnswers();
        float multipleGrade = getGrade(multipleQuestions, multipleAnswers, average);

        //简答题
        List<String> scores = result.scores();
        float shortGrade = 0;
        for (String score : scores) {
            if (StringUtils.isNotEmpty(score)) {
                float grade = 0;
                try {
                    grade = Float.valueOf(score);
                    shortGrade += (grade / 5) * average;
                } catch (NumberFormatException e) {
                    LOG.debug("Short Score Input Exception");
                }
            }
        }

        result.singleGrade(singleGrade);
        result.multipleGrade(multipleGrade);
        result.shortGrade(shortGrade);

    }

    private float getAverageGrade() {
        int total = 0;//试题总数
        int singleNum = 0;//单选题数量
        int multipleNum = 0;//多选题数量
        int shortNum = 0;//简答题数量
        GeneralExaminationSetting setting = examinationRepository.findGeneralExaminationSettingByMemberUuidAndPositionUuid(formDto.getMemberUuid(), formDto.getPositionUuid());
        List<QuestionType> types = setting.selectedTypes();
        for (QuestionType type : types) {
            if (QuestionType.SINGLE_CHOICE.equals(type)) {
                singleNum = setting.singleNum();
            } else if (QuestionType.MULTIPLE_CHOICE.equals(type)) {
                multipleNum = setting.multipleNum();
            } else if (QuestionType.SHORT_ANSWER.equals(type)) {
                shortNum = setting.shortNum();
            }
        }
        total = singleNum + multipleNum + shortNum;
        return (float) 100 / total;
    }

    private float getGrade(List<Question> questions, List<String> answers, float average) {
        int correct = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String answer;
            try {
                answer = answers.get(i);
                if (question.answer().equals(answer)) {
                    correct++;
                }
            }catch (Exception e){

            }
        }
        return average * correct;
    }

    private void putQuestions(GeneralExaminationResult result) {
        //单选
        List<Question> singleQuestionsList = result.singleQuestions();
        List<String> singleQuestions = formDto.getSingleQuestions();
        handlePut(singleQuestionsList, singleQuestions);

        //多选
        List<Question> multipleQuestionsList = result.multipleQuestions();
        List<String> multipleQuestions = formDto.getMultipleQuestions();
        handlePut(multipleQuestionsList, multipleQuestions);

        //简答
        List<Question> shortQuestionsList = result.shortQuestions();
        List<String> shortQuestions = formDto.getShortQuestions();
        handlePut(shortQuestionsList, shortQuestions);
    }

    private void handlePut(List<Question> questionList, List<String> questionIds) {

        for (String questionId : questionIds) {
            Question question = questionRepository.findQuestionByUuid(questionId);
            questionList.add(question);
        }
    }

    private GeneralExaminationResult createGeneralExaminationResult() {
        return new GeneralExaminationResult().memberUuid(formDto.getMemberUuid())
                .positionUuid(formDto.getPositionUuid())
                .multipleAnswers(formDto.getMultipleAnswers())
                .scores(formDto.getScores())
                .shortAnswers(formDto.getShortAnswers())
                .singleAnswers(formDto.getSingleAnswers())
                .singleQuestionIds(formDto.getSingleQuestions())
                .multipleQuestionIds(formDto.getMultipleQuestions())
                .shortQuestionIds(formDto.getShortQuestions());
    }
}

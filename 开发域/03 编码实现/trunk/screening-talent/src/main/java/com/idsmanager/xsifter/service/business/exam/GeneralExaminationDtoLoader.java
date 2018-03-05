package com.idsmanager.xsifter.service.business.exam;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.exam.ExaminationRepository;
import com.idsmanager.xsifter.domain.exam.GeneralExaminationResult;
import com.idsmanager.xsifter.domain.exam.GeneralExaminationSetting;
import com.idsmanager.xsifter.domain.question.Question;
import com.idsmanager.xsifter.domain.question.QuestionDegree;
import com.idsmanager.xsifter.domain.question.QuestionRepository;
import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.service.dto.examination.GeneralExaminationResultFormDto;
import com.idsmanager.xsifter.service.dto.question.OptionDto;
import com.idsmanager.xsifter.service.dto.question.QuestionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LZW on 2016/9/26.
 */
public class GeneralExaminationDtoLoader {

    private transient QuestionRepository questionRepository = BeanProvider.getBean(QuestionRepository.class);

    private transient ExaminationRepository examinationRepository = BeanProvider.getBean(ExaminationRepository.class);

    private static int SURPLUS_SINGLE_NUM = 0;//剩余单选题数
    private static int SURPLUS_MULTIPLE_NUM = 0;//剩余多选题数
    private static int SURPLUS_SHORT_NUM = 0;//剩余简答题数

    private String memberUuid;

    private String positionUuid;

    public GeneralExaminationDtoLoader(String positionUuid, String memberUuid) {
        this.memberUuid = memberUuid;
        this.positionUuid = positionUuid;
    }

    public GeneralExaminationResultFormDto load() {

        GeneralExaminationResult result = examinationRepository.findGeneralExaminationResultByMemberUuidAndPositionUuid(memberUuid, positionUuid);
        GeneralExaminationSetting setting = examinationRepository.findGeneralExaminationSettingByMemberUuidAndPositionUuid(memberUuid, positionUuid);

        if (setting == null) {
            GeneralExaminationResultFormDto formDto = new GeneralExaminationResultFormDto(false);
            formDto.setPositionUuid(positionUuid);
            formDto.setMemberUuid(memberUuid);
            return formDto;
        }

        if (result == null) {
            return loadNewFormDto();
        }

        return loadExistedFormDto(result);
    }

    private GeneralExaminationResultFormDto loadExistedFormDto(GeneralExaminationResult result) {
        GeneralExaminationResultFormDto dto = new GeneralExaminationResultFormDto(result);
        dto.setExisted(true);
        List<String> singleQuestionIds = result.singleQuestionIds();
        for (int i = 0; i < singleQuestionIds.size(); i++) {
            QuestionDto questionDto = new QuestionDto(questionRepository.findQuestionByUuid(singleQuestionIds.get(i)));
            //选项
            loadOptions(questionDto);
            dto.getSingleQuestionDtos().add(questionDto);
        }
        List<String> multipleQuestionIds = result.multipleQuestionIds();
        for (int i = 0; i < multipleQuestionIds.size(); i++) {
            QuestionDto questionDto = new QuestionDto(questionRepository.findQuestionByUuid(multipleQuestionIds.get(i)));
            loadOptions(questionDto);
            dto.getMultipleQuestionDtos().add(questionDto);
        }
        List<String> shortQuestionIds = result.shortQuestionIds();
        for (int i = 0; i < shortQuestionIds.size(); i++) {
            QuestionDto questionDto = new QuestionDto(questionRepository.findQuestionByUuid(shortQuestionIds.get(i)));
            loadOptions(questionDto);
            dto.getShortQuestionDtos().add(questionDto);
        }
        return dto;

    }

    private GeneralExaminationResultFormDto loadNewFormDto() {
        GeneralExaminationResultFormDto dto = new GeneralExaminationResultFormDto();
        dto.setMemberUuid(memberUuid);
        dto.setPositionUuid(positionUuid);
        GeneralExaminationSetting setting = examinationRepository.findGeneralExaminationSettingByMemberUuidAndPositionUuid(memberUuid, positionUuid);
        //已选问题
        List<String> existedQuestions = new ArrayList<>();
        //每个标签加载一道问题
        loadEveryOneTagQuestion(setting, dto, existedQuestions);

        //剩余题随机
        loadQuestions(setting, dto, QuestionType.SINGLE_CHOICE, SURPLUS_SINGLE_NUM, existedQuestions);
        loadQuestions(setting, dto, QuestionType.MULTIPLE_CHOICE, SURPLUS_MULTIPLE_NUM, existedQuestions);
        loadQuestions(setting, dto, QuestionType.SHORT_ANSWER, SURPLUS_SHORT_NUM, existedQuestions);
        return dto;
    }

    private void loadEveryOneTagQuestion(GeneralExaminationSetting setting, GeneralExaminationResultFormDto dto, List<String> existedQuestions) {
        List<QuestionType> selectedTypes = setting.selectedTypes();
        QuestionDegree degree = setting.degree();
        List<String> tags = setting.generalTags();
        SURPLUS_SINGLE_NUM = setting.singleNum();
        SURPLUS_MULTIPLE_NUM = setting.multipleNum();
        SURPLUS_SHORT_NUM = setting.shortNum();
        for (String tag : tags) {
            List<Question> questions = questionRepository.findQuestionsByTagAndDegree(tag, degree);
            randomQuestion(dto, questions, selectedTypes, existedQuestions);
        }
    }

    //剩余题目加载
    private void loadQuestions(GeneralExaminationSetting setting, GeneralExaminationResultFormDto dto, QuestionType type, int num, List<String> existedQuestions) {
        if (num > 0) {
            QuestionDegree degree = setting.degree();
            List<String> tags = setting.generalTags();
            List<Question> questions = questionRepository.findQuestionByTagsAndTypeAndDegree(tags, type, degree);
            if (num > questions.size()) {
                throw new IllegalStateException("您所选取的试题数目超出系统试题总量");
            }
            List<Question> questionsData = randomQuestions(dto, questions, num, type, existedQuestions);
            if (QuestionType.SINGLE_CHOICE.equals(type)) {
                List<QuestionDto> questionDtos = QuestionDto.toDtos(questionsData);
                List<QuestionDto> result = loadAllOptions(questionDtos);
                dto.getSingleQuestionDtos().addAll(result);
            } else if (QuestionType.MULTIPLE_CHOICE.equals(type)) {
                List<QuestionDto> questionDtos = QuestionDto.toDtos(questionsData);
                List<QuestionDto> result = loadAllOptions(questionDtos);
                dto.getMultipleQuestionDtos().addAll(result);
            } else if (QuestionType.SHORT_ANSWER.equals(type)) {
                dto.getShortQuestionDtos().addAll(QuestionDto.toDtos(questionsData));
            }

        }
    }

    //随机选取一道题
    public void randomQuestion(GeneralExaminationResultFormDto dto, List<Question> original, List<QuestionType> types, List<String> existedQuestions) {
        boolean flag = true;
        while (flag) {
            Random random = new Random();
            int randomNum = random.nextInt(original.size());
            Question question = original.get(randomNum);
            QuestionType type = question.type();
            if (types.contains(type)) {
                if (QuestionType.SINGLE_CHOICE.equals(type) && SURPLUS_SINGLE_NUM > 0) {
                    flag = false;
                    SURPLUS_SINGLE_NUM--;
                    dto.getSingleQuestionDtos().add(getQuestionDto(question, existedQuestions));
                    dto.getSingleQuestions().add(question.getUuid());
                } else if (QuestionType.MULTIPLE_CHOICE.equals(type) && SURPLUS_MULTIPLE_NUM > 0) {
                    flag = false;
                    SURPLUS_MULTIPLE_NUM--;
                    dto.getMultipleQuestionDtos().add(getQuestionDto(question, existedQuestions));
                    dto.getMultipleQuestions().add(question.getUuid());
                } else if (QuestionType.SHORT_ANSWER.equals(type) && SURPLUS_SHORT_NUM > 0) {
                    flag = false;
                    SURPLUS_SHORT_NUM--;
                    dto.getShortQuestionDtos().add(getQuestionDto(question, existedQuestions));
                    dto.getShortQuestions().add(question.getUuid());
                }
            }
        }

    }

    //获取Dto
    private QuestionDto getQuestionDto(Question question, List<String> existedQuestions) {
        QuestionDto questionDto = new QuestionDto(question);
        existedQuestions.add(question.getUuid());
        return loadOptions(questionDto);
    }

    //随机选取题目
    public List<Question> randomQuestions(GeneralExaminationResultFormDto dto, List<Question> original, int num, QuestionType type, List<String> existedQuestions) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            boolean existed = true;
            while (existed) {
                Random random = new Random();
                int randomNum = random.nextInt(original.size());
                if (!existedQuestions.contains(original.get(randomNum))) {
                    questions.add(original.get(randomNum));
                    putQuestionIds(dto, original.get(randomNum), type);
                    existed = false;
                    existedQuestions.add(original.get(randomNum).getUuid());
                }
                original.remove(randomNum);
            }
        }
        return questions;
    }

    //放置试题ID
    private void putQuestionIds(GeneralExaminationResultFormDto dto, Question question, QuestionType type) {
        if (QuestionType.SINGLE_CHOICE.equals(type)) {
            List<String> singleQuestions = dto.getSingleQuestions();
            singleQuestions.add(question.getUuid());
        } else if (QuestionType.MULTIPLE_CHOICE.equals(type)) {
            List<String> multipleQuestions = dto.getMultipleQuestions();
            multipleQuestions.add(question.getUuid());
        } else if (QuestionType.SHORT_ANSWER.equals(type)) {
            List<String> shortQuestions = dto.getShortQuestions();
            shortQuestions.add(question.getUuid());
        }

    }

    //加载选项
    private QuestionDto loadOptions(QuestionDto questionDto) {
        final List<String> options = questionDto.getOptions();
        int firstLetter = 65;
        List<OptionDto> optionDtos = new ArrayList<>(options.size());
        for (int i = 0; i < options.size(); i++) {
            int letterNum = firstLetter + i;
            String letter = String.valueOf((char) letterNum);
            OptionDto optionDto = new OptionDto(letter, options.get(i));
            optionDtos.add(optionDto);
        }
        questionDto.setOptionDtos(optionDtos);
        return questionDto;
    }

    private List<QuestionDto> loadAllOptions(List<QuestionDto> questionDtos) {
        List<QuestionDto> result = new ArrayList<>();
        for (QuestionDto questionDto : questionDtos) {
            loadOptions(questionDto);
            result.add(questionDto);
        }
        return result;
    }


}

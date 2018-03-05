package com.idsmanager.xsifter.service.business.exam;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.exam.ExaminationRepository;
import com.idsmanager.xsifter.domain.exam.GeneralExaminationResult;
import com.idsmanager.xsifter.domain.exam.GeneralExaminationSetting;
import com.idsmanager.xsifter.domain.question.QuestionDegree;
import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.domain.question.Tag;
import com.idsmanager.xsifter.domain.question.TagRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.examination.ExamSettingSubmitDto;
import com.idsmanager.xsifter.service.dto.examination.GeneralExaminationSettingFormDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2016/9/23.
 */
public class GeneralExaminationSettingCreator {

    private static final Logger LOG = LoggerFactory.getLogger(GeneralExaminationSettingCreator.class);

    private transient ExaminationRepository examinationRepository = BeanProvider.getBean(ExaminationRepository.class);

    private transient TagRepository tagRepository = BeanProvider.getBean(TagRepository.class);

    private GeneralExaminationSettingFormDto formDto;

    public GeneralExaminationSettingCreator(GeneralExaminationSettingFormDto formDto) {
        this.formDto = formDto;
    }

    public ExamSettingSubmitDto create() {

        ExamSettingSubmitDto dto = new ExamSettingSubmitDto();

        //验证输入参数
        boolean validate = validate(dto);

        if (validate) {
            GeneralExaminationSetting setting = examinationRepository.findGeneralExaminationSettingByMemberUuidAndPositionUuid(formDto.getMemberUuid(), formDto.getPositionUuid());
            if (null == setting) {
                setting = createSetting();
                addTags(setting);
                examinationRepository.saveGeneralSetting(setting);
                LOG.debug("{}|Create General Examination Setting:{}", SecurityUtils.username(), setting);
            } else {
                setting = updateSetting(setting);
                addTags(setting);
                examinationRepository.updateGeneralSetting(setting);
                LOG.debug("{}|Update General Examination Setting:{}", SecurityUtils.username(), setting);
            }
            //删除之前的考试成绩
            GeneralExaminationResult result = examinationRepository.findGeneralExaminationResultByMemberUuidAndPositionUuid(formDto.getMemberUuid(), formDto.getPositionUuid());
            examinationRepository.removeGeneralExaminationResult(result);
            LOG.debug("{}|删除考试成绩：{}", SecurityUtils.username(), result);
        }

        return dto;
    }

    private void addTags(GeneralExaminationSetting setting) {
        List<Tag> tags = new ArrayList<>();
        List<String> generalTags = setting.generalTags();
        for (String generalTag : generalTags) {
            Tag tag = tagRepository.findTagById(generalTag);
            tags.add(tag);
        }
        setting.tags(tags);

    }

    private GeneralExaminationSetting updateSetting(GeneralExaminationSetting setting) {
        return setting.degree(formDto.getDegree())
                .create(formDto.isCreate())
                .generalTags(formDto.getGeneralTags())
                .multipleNum(questionChecked(QuestionType.MULTIPLE_CHOICE) ? formDto.getMultipleNum() : 0)
                .shortNum(questionChecked(QuestionType.SHORT_ANSWER) ? formDto.getShortNum() : 0)
                .selectedTypes(formDto.getSelectedTypes())
                .singleNum(questionChecked(QuestionType.SINGLE_CHOICE) ? formDto.getSingleNum() : 0)
                .selectedBasicTags(formDto.getSelectedBasicTags());
    }

    private GeneralExaminationSetting createSetting() {
        return new GeneralExaminationSetting()
                .degree(formDto.getDegree())
                .create(formDto.isCreate())
                .generalTags(formDto.getGeneralTags())
                .memberUuid(formDto.getMemberUuid())
                .multipleNum(questionChecked(QuestionType.MULTIPLE_CHOICE) ? formDto.getMultipleNum() : 0)
                .shortNum(questionChecked(QuestionType.SHORT_ANSWER) ? formDto.getShortNum() : 0)
                .selectedTypes(formDto.getSelectedTypes())
                .singleNum(questionChecked(QuestionType.SINGLE_CHOICE) ? formDto.getSingleNum() : 0)
                .selectedBasicTags(formDto.getSelectedBasicTags())
                .positionUuid(formDto.getPositionUuid());
    }

    //试题类型是否勾选
    private boolean questionChecked(QuestionType type) {
        final List<QuestionType> selectedTypes = formDto.getSelectedTypes();
        if (selectedTypes.contains(type)) {
            return true;
        }
        return false;
    }

    private boolean validate(ExamSettingSubmitDto dto) {
        Map<String, Object> errors = dto.getErrors();
        validateSettingGeneralTags(formDto, errors);
        validateSelectedTypes(formDto, errors);
        validateDifficultyDegree(formDto, errors);
        if (!errors.isEmpty()) {
            dto.setSuccess(false);
            return false;
        }
        return true;
    }

    private void validateDifficultyDegree(GeneralExaminationSettingFormDto formDto, Map<String, Object> errors) {
        QuestionDegree degree = formDto.getDegree();
        if (degree == null) {
            errors.put("degree", "试卷难度不能为空");
        }
    }

    private void validateSelectedTypes(GeneralExaminationSettingFormDto formDto, Map<String, Object> errors) {
        final List<QuestionType> selectedTypes = formDto.getSelectedTypes();
        if (null == selectedTypes || selectedTypes.isEmpty()) {
            errors.put("selectedTypes", "请至少勾选一种试题类型");
        } else {
            int singleNum = formDto.getSingleNum();
            int multipleNum = formDto.getMultipleNum();
            int shortNum = formDto.getShortNum();
            if (singleNum == 0 && multipleNum == 0 && shortNum == 0) {
                errors.put("singleNum", "您还未设置各类型试题数目");
            } else {
                if (singleNum > formDto.getTotalSingle() || multipleNum > formDto.getTotalMultiple() || shortNum > formDto.getTotalShort()) {
                    errors.put("singleNum", "您输入的题目数量大于系统数量");
                    return;
                }

                int total = singleNum + multipleNum + shortNum;
                if (total < formDto.getGeneralTags().size()) {
                    errors.put("singleNum", "输入试题总数应大于标签数量");
                }


            }
        }


    }

    private void validateSettingGeneralTags(GeneralExaminationSettingFormDto formDto, Map<String, Object> errors) {
        final List<String> generalTags = formDto.getGeneralTags();
        if (null == generalTags || generalTags.isEmpty()) {
            errors.put("generalTags", "综合标签不能为空");
        }
    }
}

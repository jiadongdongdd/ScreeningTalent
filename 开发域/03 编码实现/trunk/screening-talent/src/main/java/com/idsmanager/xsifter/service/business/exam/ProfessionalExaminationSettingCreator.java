package com.idsmanager.xsifter.service.business.exam;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.exam.ExaminationRepository;
import com.idsmanager.xsifter.domain.exam.GeneralExaminationSetting;
import com.idsmanager.xsifter.domain.exam.ProfessionalExaminationSetting;
import com.idsmanager.xsifter.domain.question.QuestionDegree;
import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.examination.ExamSettingSubmitDto;
import com.idsmanager.xsifter.service.dto.examination.ProfessionalExaminationSettingFormDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2016/9/26.
 */
public class ProfessionalExaminationSettingCreator {

    private static final Logger LOG = LoggerFactory.getLogger(ProfessionalExaminationSettingCreator.class);

    private transient ExaminationRepository examinationRepository = BeanProvider.getBean(ExaminationRepository.class);

    private ProfessionalExaminationSettingFormDto formDto;

    public ProfessionalExaminationSettingCreator(ProfessionalExaminationSettingFormDto formDto) {
        this.formDto = formDto;
    }

    public ExamSettingSubmitDto create() {
        ExamSettingSubmitDto dto = new ExamSettingSubmitDto();

        //验证输入参数
        boolean validate = validate(dto);
        if (validate) {
            ProfessionalExaminationSetting setting = examinationRepository.findProfessionalExaminationSettingByMemberUuid(formDto.getMemberUuid());
            if (null == setting) {
                setting = createSetting();
                examinationRepository.saveProfessionalSetting(setting);
                LOG.debug("{}|Create Professional Examination Setting:{}", SecurityUtils.username(), setting);
            } else {
                setting = updateSetting(setting);
                examinationRepository.updateProfessionalSetting(setting);
                LOG.debug("{}|Update Professional Examination Setting:{}", SecurityUtils.username(), setting);
            }
        }
        return dto;
    }

    private ProfessionalExaminationSetting updateSetting(ProfessionalExaminationSetting setting) {
        return setting
                .professionalTags(formDto.getProfessionalTags())
                .degree(formDto.getDegree())
                .create(formDto.isCreate())
                .multipleNum(questionChecked(QuestionType.MULTIPLE_CHOICE) ? formDto.getMultipleNum() : 0)
                .selectedTypes(formDto.getSelectedTypes())
                .shortNum(questionChecked(QuestionType.SHORT_ANSWER) ? formDto.getShortNum() : 0)
                .singleNum(questionChecked(QuestionType.SINGLE_CHOICE) ? formDto.getSingleNum() : 0)
                .positionUuid(formDto.getPositionUuid());
    }

    private ProfessionalExaminationSetting createSetting() {
        return new ProfessionalExaminationSetting()
                .singleNum(questionChecked(QuestionType.SINGLE_CHOICE) ? formDto.getSingleNum() : 0)
                .selectedTypes(formDto.getSelectedTypes())
                .shortNum(questionChecked(QuestionType.SHORT_ANSWER) ? formDto.getShortNum() : 0)
                .multipleNum(questionChecked(QuestionType.MULTIPLE_CHOICE) ? formDto.getMultipleNum() : 0)
                .create(formDto.isCreate())
                .degree(formDto.getDegree())
                .memberUuid(formDto.getMemberUuid())
                .professionalTags(formDto.getProfessionalTags())
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
        //validateSettingProfessionalTags(formDto, errors);
        validateSelectedTypes(formDto, errors);
        validateDifficultyDegree(formDto, errors);
        if (!errors.isEmpty()) {
            dto.setSuccess(false);
            return false;
        }
        return true;
    }

    private void validateDifficultyDegree(ProfessionalExaminationSettingFormDto formDto, Map<String, Object> errors) {
        QuestionDegree degree = formDto.getDegree();
        if (null == degree) {
            errors.put("degree", "试卷难度不能为空");
        }
    }

    private void validateSelectedTypes(ProfessionalExaminationSettingFormDto formDto, Map<String, Object> errors) {
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
                }
            }
        }
    }

    /*private void validateSettingProfessionalTags(ProfessionalExaminationSettingFormDto formDto, Map<String, Object> errors) {
        final List<String> professionalTags = formDto.getProfessionalTags();
        if (null == professionalTags || professionalTags.isEmpty()) {
            errors.put("generalTags", "专业标签不能为空");
        } else if (professionalTags.size() > 5) {
            errors.put("generalTags", "最多能选择5个标签");
        }
    }*/
}

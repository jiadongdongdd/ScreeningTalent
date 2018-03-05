package com.idsmanager.xsifter.service.business.exam;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.exam.ExaminationRepository;
import com.idsmanager.xsifter.domain.exam.ProfessionalExaminationSetting;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;
import com.idsmanager.xsifter.domain.question.*;
import com.idsmanager.xsifter.service.dto.examination.ProfessionalExaminationSettingFormDto;
import com.idsmanager.xsifter.service.dto.question.TagDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2016/9/26.
 */
public class ProfessionalExaminationSettingFormDtoLoader {

    private transient TagRepository tagRepository = BeanProvider.getBean(TagRepository.class);

    private transient QuestionRepository questionRepository = BeanProvider.getBean(QuestionRepository.class);

    private transient ExaminationRepository examinationRepository = BeanProvider.getBean(ExaminationRepository.class);

    private transient MemberPositionRepository memberPositionRepository = BeanProvider.getBean(MemberPositionRepository.class);

    private String memberUuid;

    private String positionUuid;

    public ProfessionalExaminationSettingFormDtoLoader(String positionUuid, String memberUuid) {
        this.memberUuid = memberUuid;
        this.positionUuid = positionUuid;
    }

    public ProfessionalExaminationSettingFormDto load() {

        ProfessionalExaminationSetting setting = examinationRepository.findProfessionalExaminationSettingByMemberUuid(memberUuid);
        if (null == setting) {
            //加载新的formDto
            return loadNewFormDto();
        }

        return loadExistedFormDto(setting);
    }

    private ProfessionalExaminationSettingFormDto loadExistedFormDto(ProfessionalExaminationSetting setting) {
        ProfessionalExaminationSettingFormDto formDto = new ProfessionalExaminationSettingFormDto(setting);
        formDto.setPositionUuid(positionUuid);
        formDto.setPosition(memberPositionRepository.findPostionByUuid(positionUuid));

        //已选专业标签
        /*List<String> professionalTags = setting.professionalTags();
        List<TagDto> professionalTagDtos = getTagDtos(professionalTags);
        formDto.setProfessionalTags(professionalTags);
        formDto.setProfessionalTagDtos(professionalTagDtos);*/

        //试题数量
        /*List<String> tags = new ArrayList<>();
        tags.addAll(professionalTags);*/
        QuestionDegree degree = setting.degree();
        loadQuestionAmount(formDto, degree);
        return formDto;
    }


    /*private List<TagDto> getTagDtos(List<String> tags) {
        List<TagDto> dtos = new ArrayList<>();
        if (tags != null && !tags.isEmpty()) {
            for (String tag : tags) {
                dtos.add(new TagDto(tagRepository.findTagById(tag)));
            }
        }
        return dtos;
    }*/

    private ProfessionalExaminationSettingFormDto loadNewFormDto() {
        ProfessionalExaminationSettingFormDto formDto = new ProfessionalExaminationSettingFormDto();
        formDto.setMemberUuid(memberUuid);
        formDto.setPositionUuid(positionUuid);
        formDto.setPosition(memberPositionRepository.findPostionByUuid(positionUuid));
        //试题总数
        loadQuestionAmount(formDto, QuestionDegree.MIDDLE);

        return formDto;
    }

    private void loadQuestionAmount(ProfessionalExaminationSettingFormDto formDto, QuestionDegree degree) {

        //试题总数
        long questionAmount = questionRepository.findQuestionAmountByDegreeAndPositionUuid(degree, positionUuid);
        formDto.setTotalAmount(questionAmount);
        //单选题总数
        long signalAmount = questionRepository.findQuestionAmountByTypeAndDegreeAndPositionUuid(QuestionType.SINGLE_CHOICE, degree, positionUuid);
        formDto.setTotalSingle(signalAmount);
        //多选题总数
        long multipleAmount = questionRepository.findQuestionAmountByTypeAndDegreeAndPositionUuid(QuestionType.MULTIPLE_CHOICE, degree, positionUuid);
        formDto.setTotalMultiple(multipleAmount);
        //简答题总数
        long shortAmount = questionRepository.findQuestionAmountByTypeAndDegreeAndPositionUuid(QuestionType.SHORT_ANSWER, degree, positionUuid);
        formDto.setTotalShort(shortAmount);
    }
}

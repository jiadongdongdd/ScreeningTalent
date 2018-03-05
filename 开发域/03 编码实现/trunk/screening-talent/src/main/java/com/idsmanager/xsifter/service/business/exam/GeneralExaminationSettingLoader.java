package com.idsmanager.xsifter.service.business.exam;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.GenderStatus;
import com.idsmanager.xsifter.domain.exam.ExaminationRepository;
import com.idsmanager.xsifter.domain.exam.GeneralExaminationResult;
import com.idsmanager.xsifter.domain.exam.GeneralExaminationSetting;
import com.idsmanager.xsifter.domain.member.Degree;
import com.idsmanager.xsifter.domain.member.Education;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.question.*;
import com.idsmanager.xsifter.service.dto.examination.GeneralExaminationSettingFormDto;
import com.idsmanager.xsifter.service.dto.question.TagDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2016/9/22.
 */
public class GeneralExaminationSettingLoader {

    private transient MemberRepository memberRepository = BeanProvider.getBean(MemberRepository.class);

    private transient TagRepository tagRepository = BeanProvider.getBean(TagRepository.class);

    private transient QuestionRepository questionRepository = BeanProvider.getBean(QuestionRepository.class);

    private transient ExaminationRepository examinationRepository = BeanProvider.getBean(ExaminationRepository.class);

    private String memberUuid;

    private String positionUuid;

    public GeneralExaminationSettingLoader(String positionUuid, String memberUuid) {
        this.memberUuid = memberUuid;
        this.positionUuid = positionUuid;
    }

    public GeneralExaminationSettingFormDto load() {
        GeneralExaminationSetting setting = examinationRepository.findGeneralExaminationSettingByMemberUuidAndPositionUuid(memberUuid, positionUuid);
        if (null == setting) {
            return loadNewFormDto();
        }

        return loadExistedFormDto(setting);
    }

    //已存在数据时显示
    private GeneralExaminationSettingFormDto loadExistedFormDto(GeneralExaminationSetting setting) {
        GeneralExaminationSettingFormDto formDto = new GeneralExaminationSettingFormDto(setting);
        formDto.setPositionUuid(positionUuid);
        GeneralExaminationResult result = examinationRepository.findGeneralExaminationResultByMemberUuidAndPositionUuid(memberUuid, positionUuid);
        formDto.setExisted(result == null ? false : true);
        //基本标签
        //loadBasicTags(formDto);

        //已选基本标签
        List<String> selectedBasicTags = setting.selectedBasicTags();
        List<TagDto> selectedBasicTagDtos = getTagDtos(selectedBasicTags);
        formDto.setSelectedBasicTags(selectedBasicTags);
        formDto.setSelectedBasicTagDtos(selectedBasicTagDtos);

        //已选综合标签
        List<String> generalTags = setting.generalTags();
        List<TagDto> generalTagDtos = getTagDtos(generalTags);
        formDto.setGeneralTags(generalTags);
        formDto.setGeneralTagDtos(generalTagDtos);

        //试题数量
        QuestionDegree degree = setting.degree();
        List<String> tags = new ArrayList<>();
        if (selectedBasicTags != null) {
            tags.addAll(selectedBasicTags);
        }
        tags.addAll(generalTags);
        loadQuestionAmountByTags(formDto, tags, degree);

        return formDto;
    }

    private void loadQuestionAmountByTags(GeneralExaminationSettingFormDto formDto, List<String> tags, QuestionDegree degree) {
        //试题总数
        long questionAmountByTags = questionRepository.findQuestionAmountByTagsAndDegree(tags, degree);
        formDto.setTotalAmount(questionAmountByTags);
        //单选题数量
        long totalSingleAmount = questionRepository.findQuestionAmountByTagsAndTypeAndDegree(tags, QuestionType.SINGLE_CHOICE, degree);
        formDto.setTotalSingle(totalSingleAmount);
        //多选题数量
        long totalMultipleAmount = questionRepository.findQuestionAmountByTagsAndTypeAndDegree(tags, QuestionType.MULTIPLE_CHOICE, degree);
        formDto.setTotalMultiple(totalMultipleAmount);
        //简答题数量
        long totalShortAmount = questionRepository.findQuestionAmountByTagsAndTypeAndDegree(tags, QuestionType.SHORT_ANSWER, degree);
        formDto.setTotalShort(totalShortAmount);
    }

    private List<TagDto> getTagDtos(List<String> tags) {
        List<TagDto> dtos = new ArrayList<>();
        if (tags != null) {
            for (String tag : tags) {
                dtos.add(new TagDto(tagRepository.findTagById(tag)));
            }
        }
        return dtos;
    }

    //未设置数据时显示
    private GeneralExaminationSettingFormDto loadNewFormDto() {
        GeneralExaminationSettingFormDto formDto = new GeneralExaminationSettingFormDto();
        formDto.setMemberUuid(memberUuid);
        formDto.setPositionUuid(positionUuid);
        GeneralExaminationResult result = examinationRepository.findGeneralExaminationResultByMemberUuidAndPositionUuid(memberUuid, positionUuid);
        formDto.setExisted(result == null ? false : true);
        //基本标签
        //loadBasicTags(formDto);

        //中等难度试题总数
        loadQuestionAmount(formDto);

        return formDto;
    }

    private void loadQuestionAmount(GeneralExaminationSettingFormDto formDto) {
        //试题总数
        long questionAmount = questionRepository.findQuestionAmountByDegree(QuestionDegree.MIDDLE);
        formDto.setTotalAmount(questionAmount);
        //单选题总数
        long signalAmount = questionRepository.findQuestionAmountByTypeAndDegree(QuestionType.SINGLE_CHOICE, QuestionDegree.MIDDLE);
        formDto.setTotalSingle(signalAmount);
        //多选题总数
        long multipleAmount = questionRepository.findQuestionAmountByTypeAndDegree(QuestionType.MULTIPLE_CHOICE, QuestionDegree.MIDDLE);
        formDto.setTotalMultiple(multipleAmount);
        //简答题总数
        long shortAmount = questionRepository.findQuestionAmountByTypeAndDegree(QuestionType.SHORT_ANSWER, QuestionDegree.MIDDLE);
        formDto.setTotalShort(shortAmount);
    }

    private void loadBasicTags(GeneralExaminationSettingFormDto formDto) {
        List<TagDto> basicTags = new ArrayList<>();
        Education education = memberRepository.findEducationByUuid(memberUuid);
        //性别
        loadGenderTag(basicTags, education);
        //年龄
        loadAgeTag(basicTags, education);
        //学历
        loadDegreeTag(basicTags, education);

        formDto.setBasicTags(basicTags);

    }

    private void loadDegreeTag(List<TagDto> basicTags, Education education) {
        final Degree degree = education.getDegree();
        if (Degree.PRIMARY_SCHOOL.equals(degree)) {
            //小学
            basicTags.add(new TagDto(tagRepository.findTagById("276")));
        } else if (Degree.JUNIOR_MIDDLE_SCHOOL.equals(degree)) {
            //初中
            basicTags.add(new TagDto(tagRepository.findTagById("275")));
        } else if (Degree.HIGH_SCHOOL.equals(degree)) {
            //高中
            basicTags.add(new TagDto(tagRepository.findTagById("274")));
        } else if (Degree.JUNIOR_COLLEGE.equals(degree)) {
            //大专
            basicTags.add(new TagDto(tagRepository.findTagById("273")));
        } else if (Degree.UNDERGRADUATE.equals(degree)) {
            //本科
            basicTags.add(new TagDto(tagRepository.findTagById("272")));
        } else if (Degree.MASTER.equals(degree)) {
            //硕士
            basicTags.add(new TagDto(tagRepository.findTagById("271")));
        } else if (degree.DOCTOR.equals(degree)) {
            //博士
            basicTags.add(new TagDto(tagRepository.findTagById("270")));
        }
    }


    //年龄标签
    private void loadAgeTag(List<TagDto> basicTags, Education education) {
        final Integer age = education.getAge();
        if (20 <= age && age <= 25) {
            basicTags.add(new TagDto(tagRepository.findTagById("264")));
        } else if (26 <= age && age <= 30) {
            basicTags.add(new TagDto(tagRepository.findTagById("265")));
        } else if (31 <= age && age <= 35) {
            basicTags.add(new TagDto(tagRepository.findTagById("266")));
        } else if (36 <= age && age <= 40) {
            basicTags.add(new TagDto(tagRepository.findTagById("267")));
        }
    }

    //性别标签
    private void loadGenderTag(List<TagDto> basicTags, Education education) {
        final GenderStatus gender = education.getGender();
        basicTags.add((GenderStatus.MALE.equals(gender) ? new TagDto(tagRepository.findTagById("268")) : new TagDto(tagRepository.findTagById("269"))));
    }
}

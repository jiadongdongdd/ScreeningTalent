package com.idsmanager.xsifter.service.business.exam;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.exam.ExaminationRepository;
import com.idsmanager.xsifter.domain.exam.GeneralExaminationResult;
import com.idsmanager.xsifter.domain.exam.GeneralExaminationSetting;
import com.idsmanager.xsifter.domain.member.Basic;
import com.idsmanager.xsifter.domain.member.Education;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;
import com.idsmanager.xsifter.domain.question.Question;
import com.idsmanager.xsifter.domain.question.QuestionType;
import com.idsmanager.xsifter.domain.question.Tag;
import com.idsmanager.xsifter.service.dto.examination.BasicInfoDto;
import com.idsmanager.xsifter.service.dto.examination.GeneralExaminationResultDto;
import com.idsmanager.xsifter.service.dto.position.PositionDto;
import com.idsmanager.xsifter.service.dto.question.TagDto;

import java.util.List;

/**
 * Created by LZW on 2016/9/30.
 */
public class GeneralExaminationResultDtoLoader {

    private transient ExaminationRepository examinationRepository = BeanProvider.getBean(ExaminationRepository.class);
    private transient MemberPositionRepository memberPositionRepository = BeanProvider.getBean(MemberPositionRepository.class);
    private transient MemberRepository memberRepository = BeanProvider.getBean(MemberRepository.class);

    private String memberUuid;
    private String positionUuid;

    public GeneralExaminationResultDtoLoader(String memberUuid, String positionUuid) {
        this.memberUuid = memberUuid;
        this.positionUuid = positionUuid;
    }

    public GeneralExaminationResultDto load() {
        GeneralExaminationResultDto dto = new GeneralExaminationResultDto();
        dto.setMemberUuid(memberUuid);
        dto.setPositionUuid(positionUuid);

        //加载所选标签
        loadExaminationSetting(dto);
        //加载考试信息
        loadExaminationInfo(dto);
        //加载考试成绩
        loadExaminationGrade(dto);
        //加载职位信息
        loadPosition(dto);
        //加载基本信息
        loadBasicInfo(dto);
        return dto;
    }

    private void loadBasicInfo(GeneralExaminationResultDto dto) {
        //基本信息
        Basic basic = memberRepository.findBasicByUuid(memberUuid);
        Education education = memberRepository.findEducationByUuid(memberUuid);
        dto.setInfoDto(new BasicInfoDto(basic,education));

    }

    private void loadPosition(GeneralExaminationResultDto dto) {
        //职位信息
        MemberPosition position = memberPositionRepository.findPostionByUuid(positionUuid);
        dto.setPositionDto(new PositionDto(position));
    }

    private void loadExaminationGrade(GeneralExaminationResultDto dto) {
        //考试结果
        GeneralExaminationResult result = examinationRepository.findGeneralExaminationResultByMemberUuidAndPositionUuid(memberUuid, positionUuid);
        dto.setSingleGrade(result.singleGrade());
        dto.setMultipleGrade(result.multipleGrade());
        dto.setShortGrade(result.shortGrade());
    }

    private void loadExaminationInfo(GeneralExaminationResultDto dto) {
        //考试结果
        GeneralExaminationResult result = examinationRepository.findGeneralExaminationResultByMemberUuidAndPositionUuid(memberUuid, positionUuid);
        //加载问题
        loadQuestions(result, dto);
        //加载考生答案
        loadAnswers(result, dto);

    }

    private void loadAnswers(GeneralExaminationResult result, GeneralExaminationResultDto dto) {
        List<String> singleAnswers = result.singleAnswers();
        List<String> multipleAnswers = result.multipleAnswers();
        List<String> shortAnswers = result.shortAnswers();
        dto.setSingleAnswers(singleAnswers);
        dto.setMultipleAnswers(multipleAnswers);
        dto.setShortAnswers(shortAnswers);
    }

    private void loadQuestions(GeneralExaminationResult result, GeneralExaminationResultDto dto) {
        List<Question> singleQuestions = result.singleQuestions();
        List<Question> multipleQuestions = result.multipleQuestions();
        List<Question> shortQuestions = result.shortQuestions();
        dto.setSingleQuestions(singleQuestions);
        dto.setMultipleQuestions(multipleQuestions);
        dto.setShortQuestions(shortQuestions);
    }

    private void loadExaminationSetting(GeneralExaminationResultDto dto) {
        //设置
        GeneralExaminationSetting setting = examinationRepository.findGeneralExaminationSettingByMemberUuidAndPositionUuid(memberUuid, positionUuid);
        List<Tag> tags = setting.tags();
        dto.setTags(TagDto.toDtos(tags));
    }
}

package com.idsmanager.xsifter.service.impl;

import com.idsmanager.xsifter.domain.question.Tag;
import com.idsmanager.xsifter.domain.question.TagRepository;
import com.idsmanager.xsifter.service.EnterpriseExaminationService;
import com.idsmanager.xsifter.service.business.exam.*;
import com.idsmanager.xsifter.service.dto.examination.*;
import com.idsmanager.xsifter.service.dto.question.TagDataDto;
import com.idsmanager.xsifter.service.dto.question.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LZW on 2016/9/22.
 */
@Service("enterpriseExaminationService")
public class EnterpriseExaminationServiceImpl implements EnterpriseExaminationService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public GeneralExaminationSettingFormDto loadGeneralExaminationSetting(String positionUuid, String memberUuid) {
        GeneralExaminationSettingLoader loader = new GeneralExaminationSettingLoader(positionUuid, memberUuid);
        return loader.load();
    }

    @Override
    public QuestionQueryResult queryQuestionByTags(QuestionQueryTags tags) {
        QuestionAmountLoader loader = new QuestionAmountLoader(tags);
        return loader.load();
    }

    @Override
    public TagDataDto loadGeneralTag() {
        TagDataDto dto = new TagDataDto();
        List<Tag> tags = tagRepository.findSubTagsByPId("2");
        Tag tag = tagRepository.findTagById("2");
        dto.setpId("2");
        dto.setpName(tag.name());
        dto.setTags(TagDto.toDtos(tags));
        return dto;
    }

    @Override
    public TagDataDto loadGeneralOtherTagDataDto(String pId) {
        TagDataDto dto = new TagDataDto();
        Tag tag = tagRepository.findTagById(pId);
        List<Tag> tags = tagRepository.findSubTagsByPId(pId);
        dto.setpId(pId);
        dto.setpName(tag.name());
        dto.setTags(TagDto.toDtos(tags));
        return dto;
    }

    @Override
    public ExamSettingSubmitDto createGeneralSetting(GeneralExaminationSettingFormDto formDto) {
        GeneralExaminationSettingCreator creator = new GeneralExaminationSettingCreator(formDto);
        return creator.create();
    }

    @Override
    public ProfessionalExaminationSettingFormDto loadProfessionalExaminationSetting(String positionUuid, String memberUuid) {
        ProfessionalExaminationSettingFormDtoLoader loader = new ProfessionalExaminationSettingFormDtoLoader(positionUuid, memberUuid);
        return loader.load();
    }

    @Override
    public TagDataDto loadProfessionalTag() {
        TagDataDto dto = new TagDataDto();
        List<Tag> tags = tagRepository.findSubTagsByPId("3");
        Tag tag = tagRepository.findTagById("3");
        dto.setpId("3");
        dto.setpName(tag.name());
        dto.setTags(TagDto.toDtos(tags));
        return dto;
    }

    @Override
    public ExamSettingSubmitDto createProfessionalSetting(ProfessionalExaminationSettingFormDto formDto) {
        ProfessionalExaminationSettingCreator creator = new ProfessionalExaminationSettingCreator(formDto);
        return creator.create();
    }

    @Override
    public GeneralExaminationResultFormDto loadGeneralExaminationResultFormDto(String positionUuid, String memberUuid) {
        GeneralExaminationDtoLoader loader = new GeneralExaminationDtoLoader(positionUuid, memberUuid);
        return loader.load();
    }

    @Override
    public String createGeneralExaminationResult(GeneralExaminationResultFormDto formDto) {
        GeneralExaminationResultCreator creator = new GeneralExaminationResultCreator(formDto);
        return creator.create();
    }

    @Override
    public QuestionQueryResult queryQuestionByPosition(QuestionQueryTags tags) {
        PositionQuestionAmountLoader loader = new PositionQuestionAmountLoader(tags);
        return loader.load();
    }

    @Override
    public GeneralExaminationResultDto loadGeneralExaminationResultDto(String memberUuid, String positionUuid) {
        GeneralExaminationResultDtoLoader loader = new GeneralExaminationResultDtoLoader(memberUuid, positionUuid);
        return loader.load();
    }
}

package com.idsmanager.xsifter.service;

import com.idsmanager.xsifter.service.dto.examination.*;
import com.idsmanager.xsifter.service.dto.question.TagDataDto;

/**
 * Created by LZW on 2016/9/22.
 */
public interface EnterpriseExaminationService {
    GeneralExaminationSettingFormDto loadGeneralExaminationSetting(String positionUuid, String memberUuid);

    QuestionQueryResult queryQuestionByTags(QuestionQueryTags tags);

    TagDataDto loadGeneralTag();

    TagDataDto loadGeneralOtherTagDataDto(String pId);

    ExamSettingSubmitDto createGeneralSetting(GeneralExaminationSettingFormDto formDto);

    ProfessionalExaminationSettingFormDto loadProfessionalExaminationSetting(String positionUuid, String memberUuid);

    TagDataDto loadProfessionalTag();

    ExamSettingSubmitDto createProfessionalSetting(ProfessionalExaminationSettingFormDto formDto);

    GeneralExaminationResultFormDto loadGeneralExaminationResultFormDto(String positionUuid, String memberUuid);

    String createGeneralExaminationResult(GeneralExaminationResultFormDto formDto);

    QuestionQueryResult queryQuestionByPosition(QuestionQueryTags tags);

    GeneralExaminationResultDto loadGeneralExaminationResultDto(String memberUuid, String positionUuid);
}

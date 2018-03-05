package com.idsmanager.xsifter.domain.exam;

/**
 * Created by LZW on 2016/9/22.
 */
public interface ExaminationRepository {
    void saveGeneralSetting(GeneralExaminationSetting setting);

    GeneralExaminationSetting findGeneralExaminationSettingByMemberUuidAndPositionUuid(String memberUuid, String positionUuid);

    void updateGeneralSetting(GeneralExaminationSetting setting);

    ProfessionalExaminationSetting findProfessionalExaminationSettingByMemberUuid(String memberUuid);

    void saveProfessionalSetting(ProfessionalExaminationSetting setting);

    void updateProfessionalSetting(ProfessionalExaminationSetting setting);

    void saveGeneralExaminationResult(GeneralExaminationResult result);

    GeneralExaminationResult findGeneralExaminationResultByMemberUuidAndPositionUuid(String memberUuid, String positionUuid);

    void removeGeneralExaminationResult(GeneralExaminationResult result);

    void updateGeneralExaminationResult(GeneralExaminationResult result);
}

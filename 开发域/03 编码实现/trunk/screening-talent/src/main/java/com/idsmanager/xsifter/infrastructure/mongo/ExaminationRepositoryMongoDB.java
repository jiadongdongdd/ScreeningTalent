package com.idsmanager.xsifter.infrastructure.mongo;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.exam.ExaminationRepository;
import com.idsmanager.xsifter.domain.exam.GeneralExaminationResult;
import com.idsmanager.xsifter.domain.exam.GeneralExaminationSetting;
import com.idsmanager.xsifter.domain.exam.ProfessionalExaminationSetting;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by LZW on 2016/9/22.
 */
@Repository("examinationRepositoryMongoDB")
public class ExaminationRepositoryMongoDB extends AbstractMongoSupport implements ExaminationRepository {
    @Override
    public void saveGeneralSetting(GeneralExaminationSetting setting) {
        this.mongoTemplate().save(setting);
    }

    @Override
    public GeneralExaminationSetting findGeneralExaminationSettingByMemberUuidAndPositionUuid(String memberUuid, String positionUuid) {
        Query query = new Query(Criteria.where("memberUuid").is(memberUuid).and("positionUuid").is(positionUuid));
        return this.mongoTemplate().findOne(query, GeneralExaminationSetting.class);
    }

    @Override
    public void updateGeneralSetting(GeneralExaminationSetting setting) {
        Update update = new Update()
                .set("degree", setting.degree())
                .set("create", setting.create())
                .set("generalTags", setting.generalTags())
                .set("multipleNum", setting.multipleNum())
                .set("shortNum", setting.shortNum())
                .set("selectedTypes", setting.selectedTypes())
                .set("singleNum", setting.singleNum())
                .set("selectedBasicTags", setting.selectedBasicTags());
        Query query = createIDQuery(setting.getUuid());
        this.mongoTemplate().updateFirst(query, update, GeneralExaminationSetting.class);
    }

    @Override
    public ProfessionalExaminationSetting findProfessionalExaminationSettingByMemberUuid(String memberUuid) {
        Query query = new Query(Criteria.where("memberUuid").is(memberUuid));
        return this.mongoTemplate().findOne(query, ProfessionalExaminationSetting.class);
    }

    @Override
    public void saveProfessionalSetting(ProfessionalExaminationSetting setting) {
        this.mongoTemplate().save(setting);
    }

    @Override
    public void updateProfessionalSetting(ProfessionalExaminationSetting setting) {
        Update update = new Update()
                .set("degree", setting.degree())
                .set("create", setting.create())
                .set("professionalTags", setting.professionalTags())
                .set("multipleNum", setting.multipleNum())
                .set("shortNum", setting.shortNum())
                .set("selectedTypes", setting.selectedTypes())
                .set("singleNum", setting.singleNum())
                .set("positionUuid", setting.positionUuid());
        Query query = createIDQuery(setting.getUuid());
        this.mongoTemplate().updateFirst(query, update, ProfessionalExaminationSetting.class);
    }

    @Override
    public void saveGeneralExaminationResult(GeneralExaminationResult result) {
        this.mongoTemplate().save(result);
    }

    @Override
    public GeneralExaminationResult findGeneralExaminationResultByMemberUuidAndPositionUuid(String memberUuid, String positionUuid) {
        Query query = new Query(Criteria.where("memberUuid").is(memberUuid).and("positionUuid").is(positionUuid));
        return this.mongoTemplate().findOne(query, GeneralExaminationResult.class);
    }

    @Override
    public void removeGeneralExaminationResult(GeneralExaminationResult result) {
        this.mongoTemplate().remove(result);
    }

    @Override
    public void updateGeneralExaminationResult(GeneralExaminationResult result) {
        Update update = new Update()
                .set("singleAnswers", result.singleAnswers())
                .set("multipleAnswers", result.multipleAnswers())
                .set("shortAnswers", result.shortAnswers())
                .set("scores", result.scores())
                .set("singleGrade", result.singleGrade())
                .set("multipleGrade", result.multipleGrade())
                .set("shortGrade", result.shortGrade());
        Query query = createIDQuery(result.getUuid());
        this.mongoTemplate().updateFirst(query, update, GeneralExaminationResult.class);
    }

}

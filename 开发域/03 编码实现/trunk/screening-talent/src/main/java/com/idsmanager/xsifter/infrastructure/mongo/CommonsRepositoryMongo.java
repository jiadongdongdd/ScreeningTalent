package com.idsmanager.xsifter.infrastructure.mongo;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.commons.CommonsRepository;
import com.idsmanager.xsifter.domain.commons.IdsFile;
import org.springframework.stereotype.Repository;

/**
 * 2016/1/25
 *
 * @author Shengzhao Li
 */
@Repository("commonsRepositoryMongo")
public class CommonsRepositoryMongo extends AbstractMongoSupport implements CommonsRepository {

    @Override
    public void saveIdsFile(IdsFile idsFile) {
        this.mongoTemplate().save(idsFile);
    }

    @Override
    public void deleteIdsFile(IdsFile idsFile) {
        this.mongoTemplate().remove(idsFile);
    }

    @Override
    public IdsFile findIdsFileByGuid(String guid) {
        return findById(IdsFile.class, guid);
    }
}

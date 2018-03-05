package com.idsmanager.xsifter.infrastructure.mongo;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.question.Tag;
import com.idsmanager.xsifter.domain.question.TagRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LZW on 2016/9/18.
 */
@Repository("tagRepositoryMongoDB")
public class TagRepositoryMongoDB extends AbstractMongoSupport implements TagRepository {
    @Override
    public Tag findTagById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return this.mongoTemplate().findOne(query, Tag.class);
    }

    @Override
    public List<Tag> findSubTagsByPId(String pId) {
        Query query = new Query(Criteria.where("pId").is(pId));
        return this.mongoTemplate().find(query, Tag.class);
    }

    @Override
    public List<Tag> findAllTags() {
        return this.mongoTemplate().find(new Query(), Tag.class);
    }

    @Override
    public void saveTag(Tag tag) {
        this.mongoTemplate().save(tag);
    }
}

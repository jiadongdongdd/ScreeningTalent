
package com.idsmanager.commons.utils.mongo;


import com.idsmanager.commons.web.context.BeanProvider;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 2015/10/28
 * <p/>
 * Abstract MongoDB commons actions
 *
 * @author Shengzhao Li
 */
public abstract class AbstractMongoSupport {

    protected static final String ID = "_id";


    private MongoTemplate mongoTemplate;


    protected AbstractMongoSupport() {
    }

    protected MongoOperations mongoTemplate() {
        if (mongoTemplate == null) {
            mongoTemplate = BeanProvider.getBean(MongoTemplate.class);
        }
        return mongoTemplate;
    }

    protected <T extends Serializable> T findById(Class<T> clazz, Object id) {
        Query query = new Query(new Criteria(ID).is(id));
        return this.mongoTemplate().findOne(query, clazz);
    }


    protected Query createIDQuery(Object id) {
        final Criteria criteria = new Criteria(ID).is(id);
        return new Query(criteria);
    }

    protected Query addPagination(Query query, Map<String, Object> queryMap) {
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "createTime")));
        query.skip((Integer) queryMap.get("startIndex")).limit((Integer) queryMap.get("perPageSize"));
        return query;
    }

    protected Query addRegexCriteria(Query query, String field, String value) {
        if (StringUtils.isNotEmpty(value)) {
            Pattern pattern = Pattern.compile("/*" + value + "/*", 2);
            query.addCriteria(Criteria.where(field).regex(pattern));
        }

        return query;
    }


    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}

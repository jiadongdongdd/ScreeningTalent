package com.idsmanager.xsifter.infrastructure.mongo;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.question.Question;
import com.idsmanager.xsifter.domain.question.QuestionDegree;
import com.idsmanager.xsifter.domain.question.QuestionRepository;
import com.idsmanager.xsifter.domain.question.QuestionType;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2016/9/20.
 */
@Repository("questionRepositoryMongoDB")
public class QuestionRepositoryMongoDB extends AbstractMongoSupport implements QuestionRepository {


    @Override
    public List<Question> findQuestionPaginatedList(Map<String, Object> map) {
        Query query = addPagination(new Query(), map);
        addQueryConditions(query, map);
        return this.mongoTemplate().find(query, Question.class);
    }

    @Override
    public long totalQuestionPaginatedList(Map<String, Object> map) {
        Query query = addQueryConditions(new Query(), map);
        return this.mongoTemplate().count(query, Question.class);
    }

    @Override
    public void saveQuestion(Question question) {
        this.mongoTemplate().save(question);
    }

    @Override
    public Question findQuestionByUuid(String uuid) {
        Query query = new Query(Criteria.where("uuid").is(uuid));
        return this.mongoTemplate().findOne(query, Question.class);
    }

    @Override
    public void remove(Question question) {
        this.mongoTemplate().remove(question);
    }

    @Override
    public long findQuestionAmountByDegree(QuestionDegree degree) {
        Query query = new Query(Criteria.where("degree").is(degree));
        return this.mongoTemplate().count(query, Question.class);
    }

    @Override
    public Question findQuestionByNumber(String number) {
        Query query = new Query(Criteria.where("number").is(number));
        return this.mongoTemplate().findOne(query, Question.class);
    }

    @Override
    public void updateQuestion(Question question) {
        Update update = new Update()
                .set("title", question.title())
                .set("type", question.type())
                .set("options", question.options())
                .set("answer", question.answer())
                .set("positionUuid", question.positionUuid())
                .set("tags", question.tags())
                .set("degree", question.degree())
                .set("suitableType", question.suitableType());
        Query query = createIDQuery(question.getUuid());
        this.mongoTemplate().updateFirst(query, update, Question.class);
    }

    @Override
    public long findQuestionAmountByTagsAndDegree(List<String> tags, QuestionDegree degree) {
        Query query = new Query(Criteria.where("tags").in(tags).and("degree").is(degree));
        return this.mongoTemplate().count(query, Question.class);
    }

    @Override
    public long findQuestionAmountByTypeAndDegree(QuestionType type, QuestionDegree degree) {
        Query query = new Query(Criteria.where("type").is(type).and("degree").is(degree));
        return this.mongoTemplate().count(query, Question.class);
    }

    @Override
    public long findQuestionAmountByTagsAndTypeAndDegree(List<String> tags, QuestionType type, QuestionDegree degree) {
        Query query = new Query(Criteria.where("tags").in(tags).and("type").is(type).and("degree").is(degree));
        return this.mongoTemplate().count(query, Question.class);
    }

    @Override
    public List<Question> findQuestionByTagsAndTypeAndDegree(List<String> tags, QuestionType type, QuestionDegree degree) {
        Query query = new Query(Criteria.where("tags").in(tags).and("type").is(type).and("degree").is(degree));
        return this.mongoTemplate().find(query, Question.class);
    }

    @Override
    public long findQuestionAmount() {
        return this.mongoTemplate().count(new Query(), Question.class);
    }

    @Override
    public long findQuestionAmountByDegreeAndPositionUuid(QuestionDegree degree, String positionUuid) {
        Query query = new Query(Criteria.where("degree").is(degree).and("positionUuid").is(positionUuid));
        return this.mongoTemplate().count(query, Question.class);
    }

    @Override
    public long findQuestionAmountByTypeAndDegreeAndPositionUuid(QuestionType type, QuestionDegree degree, String positionUuid) {
        Query query = new Query(Criteria.where("degree").is(degree).and("type").is(type).and("positionUuid").is(positionUuid));
        return this.mongoTemplate().count(query, Question.class);
    }

    @Override
    public List<Question> findQuestionsByTagAndDegree(String tag, QuestionDegree degree) {
        List<String> list = new ArrayList<>();
        list.add(tag);
        Query query = new Query(Criteria.where("tags").in(list).and("degree").is(degree));
        return this.mongoTemplate().find(query, Question.class);
    }

    private Query addQueryConditions(Query query, Map<String, Object> map) {
        final String title = (String) map.get("title");
        addRegexCriteria(query, "title", title);
        QuestionType questionType = (QuestionType)map.get("questionType");
        if(null!=questionType){
            query.addCriteria(Criteria.where("type").is(questionType));
        }
        return query;
    }
}

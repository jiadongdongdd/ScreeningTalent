package com.idsmanager.xsifter.service.business.question;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.question.Question;
import com.idsmanager.xsifter.domain.question.QuestionRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by LZW on 2016/9/21.
 */
public class QuestionRemover {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionRemover.class);

    private transient QuestionRepository questionRepository = BeanProvider.getBean(QuestionRepository.class);

    private String uuid;

    public QuestionRemover(String uuid) {
        this.uuid = uuid;
    }


    public void remove() {
        Question question = questionRepository.findQuestionByUuid(uuid);
        if (null == question) {
            throw new IllegalStateException("错误参数：" + uuid);
        }

        questionRepository.remove(question);
        LOG.debug("{}|Remove Question:{}", SecurityUtils.username(), question);
    }
}

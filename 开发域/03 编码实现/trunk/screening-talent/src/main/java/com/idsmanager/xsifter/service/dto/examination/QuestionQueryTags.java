package com.idsmanager.xsifter.service.dto.examination;

import com.idsmanager.xsifter.domain.question.QuestionDegree;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZW on 2016/9/23.
 */
public class QuestionQueryTags implements Serializable {
    private static final long serialVersionUID = 627957693071478950L;

    private List<String> tags;

    private QuestionDegree degree;

    private String positionUuid;

    public QuestionQueryTags() {
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public QuestionDegree getDegree() {
        return degree;
    }

    public void setDegree(QuestionDegree degree) {
        this.degree = degree;
    }

    public String getPositionUuid() {
        return positionUuid;
    }

    public void setPositionUuid(String positionUuid) {
        this.positionUuid = positionUuid;
    }
}

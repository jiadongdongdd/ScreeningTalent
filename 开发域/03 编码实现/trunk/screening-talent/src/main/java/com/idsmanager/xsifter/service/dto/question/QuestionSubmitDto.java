package com.idsmanager.xsifter.service.dto.question;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LZW on 2016/9/21.
 */
public class QuestionSubmitDto implements Serializable {
    private static final long serialVersionUID = -7568141250294522694L;

    private String questionUuid;
    //是否成功
    private boolean success = true;
    //错误信息
    private Map<String, Object> errors = new HashMap<>();

    //有标准答案的试题
    private boolean standard = true;

    public QuestionSubmitDto() {
    }

    public String getQuestionUuid() {
        return questionUuid;
    }

    public void setQuestionUuid(String questionUuid) {
        this.questionUuid = questionUuid;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }

    public boolean isStandard() {
        return standard;
    }

    public void setStandard(boolean standard) {
        this.standard = standard;
    }
}

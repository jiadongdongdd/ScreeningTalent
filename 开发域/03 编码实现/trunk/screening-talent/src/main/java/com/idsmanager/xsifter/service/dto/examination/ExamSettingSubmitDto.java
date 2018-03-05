package com.idsmanager.xsifter.service.dto.examination;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LZW on 2016/9/23.
 */
public class ExamSettingSubmitDto implements Serializable {
    private static final long serialVersionUID = -6102540835117144181L;

    private String memberUuid;
    //是否成功
    private boolean success = true;
    //错误信息
    private Map<String, Object> errors = new HashMap<>();

    public ExamSettingSubmitDto() {
    }

    public String getMemberUuid() {
        return memberUuid;
    }

    public void setMemberUuid(String memberUuid) {
        this.memberUuid = memberUuid;
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
}

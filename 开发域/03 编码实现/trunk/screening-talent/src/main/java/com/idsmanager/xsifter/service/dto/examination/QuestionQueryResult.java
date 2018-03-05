package com.idsmanager.xsifter.service.dto.examination;

import java.io.Serializable;

/**
 * Created by LZW on 2016/9/23.
 */
public class QuestionQueryResult implements Serializable {
    private static final long serialVersionUID = -2036025541794122320L;

    //题目数量
    private long amount;

    //单选题数量
    private long singleAmount;
    //多选题数量
    private long multipleAmount;
    //简答题数量
    private long shortAmount;
    //提示信息
    private String message;

    public QuestionQueryResult() {
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getSingleAmount() {
        return singleAmount;
    }

    public void setSingleAmount(long singleAmount) {
        this.singleAmount = singleAmount;
    }

    public long getMultipleAmount() {
        return multipleAmount;
    }

    public void setMultipleAmount(long multipleAmount) {
        this.multipleAmount = multipleAmount;
    }

    public long getShortAmount() {
        return shortAmount;
    }

    public void setShortAmount(long shortAmount) {
        this.shortAmount = shortAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

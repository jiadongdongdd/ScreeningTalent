package com.idsmanager.xsifter.domain.question;

/**
 * Created by LZW on 2016/9/28.
 */
public enum SuitableType {

    PROFESSIONAL("专业试题"),
    GENERAL("综合试题");

    private String label;

    SuitableType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

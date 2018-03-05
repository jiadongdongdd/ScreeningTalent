package com.idsmanager.xsifter.domain.question;

/**
 * Created by LZW on 2016/9/28.
 */
public enum QuestionDegree {

    HARD("难"),
    MIDDLE("中"),
    EASY("易");

    private String label;

    QuestionDegree(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

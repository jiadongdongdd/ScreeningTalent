package com.idsmanager.xsifter.domain.question;

import java.util.Arrays;
import java.util.List;

/**
 * Created by LZW on 2016/9/14.
 */
public enum QuestionType {

    SINGLE_CHOICE("单选题"),
    MULTIPLE_CHOICE("多选题"),
    SHORT_ANSWER("简答题");

    public String label;

    QuestionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static List<QuestionType> allQuestionTypes(){
        return Arrays.asList(SHORT_ANSWER
        ,MULTIPLE_CHOICE,SINGLE_CHOICE);
    }
}

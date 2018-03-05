package com.idsmanager.xsifter.service.dto.question;

import java.io.Serializable;

/**
 * Created by LZW on 2016/9/22.
 */
public class OptionDto implements Serializable {
    private static final long serialVersionUID = -3957862208829405095L;

    //选项字母
    private String letter;
    //选项内容
    private String content;

    public OptionDto() {
    }

    public OptionDto(String letter, String content) {
        this.letter = letter;
        this.content = content;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

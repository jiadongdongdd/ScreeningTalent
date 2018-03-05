package com.idsmanager.xsifter.service.business.question;

import com.idsmanager.xsifter.service.dto.question.TagDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZW on 2016/9/18.
 */
public class TagTreeResult implements Serializable {
    private static final long serialVersionUID = 4919277176447409310L;

    private List<TagDto> data;

    private boolean empty;

    public TagTreeResult() {
    }

    public List<TagDto> getData() {
        return data;
    }

    public void setData(List<TagDto> data) {
        this.data = data;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}

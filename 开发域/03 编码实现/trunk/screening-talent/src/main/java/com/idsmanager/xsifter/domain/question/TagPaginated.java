package com.idsmanager.xsifter.domain.question;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.service.dto.question.TagDto;

import java.util.Map;

/**
 * Created by LZW on 2016/9/18.
 */
public class TagPaginated extends DefaultPaginated<TagDto> {

    private String id;

    private boolean empty;

    @Override
    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.queryMap();
        map.put("id", id);
        return map;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

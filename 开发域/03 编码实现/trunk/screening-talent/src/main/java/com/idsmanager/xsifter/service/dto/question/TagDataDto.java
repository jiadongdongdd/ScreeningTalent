package com.idsmanager.xsifter.service.dto.question;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZW on 2016/9/20.
 */
public class TagDataDto implements Serializable {
    private static final long serialVersionUID = -8570234061252136012L;
    //父id
    private String pId;
    //父名称
    private String pName;
    //子标签
    private List<TagDto> tags;

    public TagDataDto() {
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }
}

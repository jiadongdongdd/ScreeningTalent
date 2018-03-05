package com.idsmanager.xsifter.service.dto.question;

import com.idsmanager.commons.utils.UUIDGenerator;
import com.idsmanager.xsifter.domain.question.Tag;
import com.idsmanager.xsifter.service.dto.AbstractDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by LZW on 2016/9/18.
 */
public class TagDto extends AbstractDto {
    private static final long serialVersionUID = -8782575015481868950L;

    //标签节点id
    private String id;
    //标签父节点id
    private String pId;
    //标签名称
    private String name;
    //标签的子标签是否展开 是：true 否：false
    private boolean open;
    //初始化标识 是：true 否：false 默认 false
    private boolean noR;
    //是否为父标签
    private boolean parentTag;

    public TagDto() {
    }

    public TagDto(Tag tag) {
        super(tag);
        this.id = tag.id();
        this.pId = tag.pId();
        this.name = tag.name();
        this.open = tag.open();
        this.noR = tag.noR();
        this.parentTag = tag.parentTag();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isNoR() {
        return noR;
    }

    public void setNoR(boolean noR) {
        this.noR = noR;
    }

    public boolean isParentTag() {
        return parentTag;
    }

    public void setParentTag(boolean parentTag) {
        this.parentTag = parentTag;
    }

    public static List<TagDto> toDtos(List<Tag> tags) {
        List<TagDto> dtos = new ArrayList<>(tags.size());
        dtos.addAll(tags.stream().map(TagDto::new).collect(Collectors.toList()));
        return dtos;
    }
}

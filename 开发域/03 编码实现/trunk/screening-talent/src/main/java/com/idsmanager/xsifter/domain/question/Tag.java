package com.idsmanager.xsifter.domain.question;

import com.idsmanager.commons.utils.UUIDGenerator;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.AbstractDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by LZW on 2016/9/14.
 */
//标签
@Document(collection = "Tag")
public class Tag extends AbstractDomain {
    private static final long serialVersionUID = 2497216022942553484L;

    @Transient
    private transient TagRepository tagRepository = BeanProvider.getBean(TagRepository.class);

    //标签节点id
    private String id = UUIDGenerator.generate();
    //标签父节点id
    private String pId;
    //标签名称
    private String name;
    //标签的子标签是否展开 是：true 否：false
    private boolean open;
    //初始化标识 是：true 否：false 默认 false
    private boolean noR;


    public Tag() {
    }

    public String id() {
        return id;
    }

    public Tag id(String id) {
        this.id = id;
        return this;
    }

    public String pId() {
        return pId;
    }

    public Tag pId(String pId) {
        this.pId = pId;
        return this;
    }

    public String name() {
        return name;
    }

    public Tag name(String name) {
        this.name = name;
        return this;
    }

    public boolean open() {
        return open;
    }

    public Tag open(boolean open) {
        this.open = open;
        return this;
    }

    public boolean noR() {
        return noR;
    }

    public Tag noR(boolean noR) {
        this.noR = noR;
        return this;
    }

    public boolean parentTag() {
        List<Tag> tags = tagRepository.findSubTagsByPId(id);
        return tags != null && !tags.isEmpty();
    }
}

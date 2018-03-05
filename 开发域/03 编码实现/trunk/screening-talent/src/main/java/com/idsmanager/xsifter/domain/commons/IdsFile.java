package com.idsmanager.xsifter.domain.commons;


import com.idsmanager.commons.file.FileWarehouse;
import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.UUIDGenerator;
import com.idsmanager.commons.web.context.BeanProvider;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Date;

/**
 * 定义一个文件信息
 *
 * @author Shengzhao Li
 */
@Document(collection = "IdsFile")
public class IdsFile implements Serializable {

    private static final long serialVersionUID = -5200293878666693774L;

    @Transient
    protected transient FileWarehouse fileWarehouse = BeanProvider.getBean(FileWarehouse.class);
    @Transient
    protected transient CommonsRepository commonsRepository = BeanProvider.getBean(CommonsRepository.class);


    @Id
    protected String guid = UUIDGenerator.generate();
    @CreatedDate
    protected Date createTime = DateUtils.now();


    //Original file name
    protected String name;

    protected String path;
    //e.g. txt;   file suffix
    protected String suffix;

    //If temp file or not, default false
    protected boolean temp = false;
    //File original size, unit: byte
    protected long size;

    @Transient
    protected transient byte[] data;

    public IdsFile() {
    }

    public IdsFile(String name, byte[] data) {
        Assert.notNull(name, "name is null");
        this.name = name;
        this.suffix = FilenameUtils.getExtension(name);
        this.data = data;
        this.size = data.length;
    }

    public String name() {
        return name;
    }

    //Update suffix at the same time
    public IdsFile name(String name) {
        this.name = name;
        this.suffix = FilenameUtils.getExtension(name);
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public String createTime() {
        return DateUtils.toDateTime(createTime);
    }

    public String suffix() {
        return suffix;
    }

    public byte[] data() {
        if (data == null) {
            data = fileWarehouse.read(path);
        }
        return data;
    }

    public void save() {
        this.path = fileWarehouse.write(this.data);
        commonsRepository.saveIdsFile(this);
    }

    public String path() {
        return path;
    }

    public void delete() {
        fileWarehouse.delete(this.path);
        commonsRepository.deleteIdsFile(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{temp=").append(temp);
        sb.append(", guid='").append(guid).append('\'');
        sb.append(", suffix='").append(suffix).append('\'');
        sb.append(", path='").append(path).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public boolean temp() {
        return temp;
    }

    public IdsFile temp(boolean temp) {
        this.temp = temp;
        return this;
    }

    public long size() {
        return size;
    }

    public IdsFile size(long size) {
        this.size = size;
        return this;
    }


}
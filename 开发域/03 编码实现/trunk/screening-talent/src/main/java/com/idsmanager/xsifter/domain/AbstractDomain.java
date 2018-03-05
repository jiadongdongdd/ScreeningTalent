package com.idsmanager.xsifter.domain;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.UUIDGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.io.Serializable;
import java.util.Date;

/**
 * 2016/1/30
 *
 * @author Shengzhao Li
 */
public abstract class AbstractDomain implements Serializable {
    private static final long serialVersionUID = -4429635460660746630L;

    @Id
    protected String uuid = UUIDGenerator.generate();

    @CreatedDate
    protected Date createTime = DateUtils.now();

    @Version
    protected Long version;


    public AbstractDomain() {
    }


    public String getUuid() {
        return uuid;
    }

    public String getCreateTime() {
        return DateUtils.toDateTime(createTime);
    }

    public Long getVersion() {
        return version;
    }


    @Override
    public String toString() {
        return "{" +
                "uuid='" + uuid + '\'' +
                ", createTime=" + createTime +
                ", version=" + version +
                '}';
    }
}

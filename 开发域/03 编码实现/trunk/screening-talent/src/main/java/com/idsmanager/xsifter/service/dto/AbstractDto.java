/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.idsmanager.xsifter.service.dto;

import com.idsmanager.xsifter.domain.AbstractDomain;

import java.io.Serializable;

/**
 * 2016/1/23
 *
 * @author Shengzhao Li
 */
public abstract class AbstractDto implements Serializable {

    private static final long serialVersionUID = 2700974154809879256L;

    protected String uuid;
    protected String createTime;


    public AbstractDto() {
    }

    public AbstractDto(AbstractDomain domain) {
        this.uuid = domain.getUuid();
        this.createTime = domain.getCreateTime();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDto)) return false;

        AbstractDto that = (AbstractDto) o;

        if (!uuid.equals(that.uuid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


}

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
package com.idsmanager.xsifter.domain.member;

import java.util.List;

/**
 * 2016/1/28
 *
 * @author Shengzhao Li
 */

public enum NotEntryPersonalReason {


    NOT_OUT("未出现", false),
    POST_OBJECTION("职务异议", false),
    INFO_NOT_MATCH("信息不匹配", true),
    BAD_ETIQUETTE("面试礼仪差", false);


    private String label;
    //是否多可选
    private boolean multi;

    NotEntryPersonalReason(String label, boolean multi) {
        this.label = label;
        this.multi = multi;
    }

    public List<NotEntryPersonalReasonItem> getItems() {
        return NotEntryPersonalReasonItem.getItems(this);
    }

    public boolean isMulti() {
        return multi;
    }

    public String getLabel() {
        return label;
    }
}

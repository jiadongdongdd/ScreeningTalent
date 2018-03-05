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

/**
 * 2016/1/28
 * <p/>
 * 不同意入职的原因 -  公司
 *
 * @author Shengzhao Li
 */

public enum NotEntryCompanyReason {

    POSITION_CANCELED("职位取消"),
    CHOOSE_OTHER("另有人选"),
    JOB_WROING("与职位要求不符"),
    OTHER("其他");

    private String label;

    NotEntryCompanyReason(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

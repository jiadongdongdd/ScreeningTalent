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
 * 工作环节   处分
 *
 * @author Shengzhao Li
 */

public enum WorkPunished {

    WARNING("警告"),
    DEMERIT("记过"),
    DOWN_POSITION("降职"),

    DOWN_SALARY("降薪"),
    OTHER("其他");

    private String label;

    WorkPunished(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

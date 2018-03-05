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
 * <p/>
 * 嘉奖类型
 *
 * @author Shengzhao Li
 */

public enum WorkedAwards {

    UP_POSITION("升职"),
    UP_SALARY("加薪"),
    GOOD_EMPLOYEE("评为先进或优秀员工"),

    OTHER_AWARDS("其它奖励"),
    CUSTOMER_AWARDS("客户表彰");

    private String label;

    WorkedAwards(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

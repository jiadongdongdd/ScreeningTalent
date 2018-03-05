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
 * 月税前固定收入
 *
 * @author Shengzhao Li
 */

public enum MonthFixedIncome {

    TWO_THREE("2000-3000"),
    THREE_FOUR("3000-4000"),
    FOUR_FIVE("4000-5000"),
    FIVE_SIX("5000-6000"),
    SIX_EIGHT("6000-8000"),
    EIGHT_TEN("8000-10000");


    private String label;

    MonthFixedIncome(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }


}

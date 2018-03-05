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
 * 月税前浮动收入(奖金等)
 *
 * @author Shengzhao Li
 */
public enum MonthFloatIncome {

    TEN_TWELVE("10000-12000"),
    TWELVE_FIFTEEN("12000-15000"),
    FIFTEEN_EIGHTEEN("15000-18000"),
    EIGHTEEN_TWENTY_TWO("18000-22000"),
    LARGER_TWENTY_TWO(">22000");

    private String label;

    MonthFloatIncome(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

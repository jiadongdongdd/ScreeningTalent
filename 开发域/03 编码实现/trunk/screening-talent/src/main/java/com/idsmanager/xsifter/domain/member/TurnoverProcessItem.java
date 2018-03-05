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

import java.util.ArrayList;
import java.util.List;

/**
 * 2016/1/28
 *
 * @author Shengzhao Li
 */

public enum TurnoverProcessItem {

    RETRIEVE_COMPANY_PROPERTY(TurnoverProcess.TAKE_RESOURCES, "侵吞公司财产"),
    NOT_ARREARS(TurnoverProcess.TAKE_RESOURCES, "欠款不还"),
    GET_SECRETS(TurnoverProcess.TAKE_RESOURCES, "盗取保密信息");

    private TurnoverProcess process;
    private String label;


    TurnoverProcessItem(TurnoverProcess process, String label) {
        this.process = process;
        this.label = label;
    }

    public TurnoverProcess getProcess() {
        return process;
    }

    public String getLabel() {
        return label;
    }

    public static List<TurnoverProcessItem> getItems(TurnoverProcess process) {
        List<TurnoverProcessItem> items = new ArrayList<>();
        for (TurnoverProcessItem item : TurnoverProcessItem.values()) {
            if (item.getProcess().equals(process)) {
                items.add(item);
            }
        }
        return items;
    }
}

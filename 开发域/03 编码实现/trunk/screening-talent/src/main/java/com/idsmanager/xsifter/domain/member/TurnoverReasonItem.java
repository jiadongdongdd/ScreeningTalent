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

public enum TurnoverReasonItem {

    REVEAL_COMPANY_SECRETS(TurnoverReason.GO_OUT, "泄露公司机密"),
    BRIBERY(TurnoverReason.GO_OUT, "贪污受贿挪用公款"),
    RUDE(TurnoverReason.GO_OUT, "言行粗鲁"),
    DESTROY_IMPORTANT_ASSETS(TurnoverReason.GO_OUT, "破坏公司重要资产"),

    STEALING(TurnoverReason.GO_OUT, "偷盗公司财物"),
    DEFAMATION(TurnoverReason.GO_OUT, "诋毁公司名誉"),
    MISUSE_RESOURCES(TurnoverReason.GO_OUT, "滥用公司资源"),
    OTHER(TurnoverReason.GO_OUT, "其它"), //必须注明

    ;

    private TurnoverReason reason;

    private String label;

    TurnoverReasonItem(TurnoverReason reason, String label) {
        this.reason = reason;
        this.label = label;
    }

    public TurnoverReason getReason() {
        return reason;
    }

    public String getLabel() {
        return label;
    }

    public static List<TurnoverReasonItem> getItems(TurnoverReason reason) {
        List<TurnoverReasonItem> items = new ArrayList<>();
        for (TurnoverReasonItem item : TurnoverReasonItem.values()) {
            if (reason.equals(item.getReason())) {
                items.add(item);
            }
        }
        return items;
    }
}

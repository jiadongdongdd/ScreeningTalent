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
 * <p/>
 * <p/>
 * 不同意入职的原因项
 *
 * @author Shengzhao Li
 */

public enum NotEntryPersonalReasonItem {

    PRE_TELL_REASON(NotEntryPersonalReason.NOT_OUT, "预先说明原因"),
    PRE_NOT_TELL(NotEntryPersonalReason.NOT_OUT, "预先无任何说明（不来也不告知）"),

    SALARY_DIFF(NotEntryPersonalReason.POST_OBJECTION, "待遇异议"),
    POSITION_DIFF(NotEntryPersonalReason.POST_OBJECTION, "工作地点异议"),
    UNKNOWN_REASON(NotEntryPersonalReason.POST_OBJECTION, "未说明原因"),
    OTHER_REASON(NotEntryPersonalReason.POST_OBJECTION, "其他（测试不符合职位要求）"),

    IMG_NOT_MATCH(NotEntryPersonalReason.INFO_NOT_MATCH, "照片与本人不符"),
    EDU_NOT_MATCH(NotEntryPersonalReason.INFO_NOT_MATCH, "学历不符或造假"),
    HISTORY_NOT_MATCH(NotEntryPersonalReason.INFO_NOT_MATCH, "工作经历不符或造假"),
    OTHER_GOAL(NotEntryPersonalReason.INFO_NOT_MATCH, "别有商业目的（不是为入职而是为商业信息）"),
    OTHER_NOT_MATCH(NotEntryPersonalReason.INFO_NOT_MATCH, "其他（企业认为的其他原因）"),

    LANGUAGE_BAD(NotEntryPersonalReason.BAD_ETIQUETTE, "语言"),
    OPERATION_BAD(NotEntryPersonalReason.BAD_ETIQUETTE, "举止"),
    CLOTHES_BAD(NotEntryPersonalReason.BAD_ETIQUETTE, "服饰");

    private NotEntryPersonalReason reason;

    private String label;

    NotEntryPersonalReasonItem(NotEntryPersonalReason reason, String label) {
        this.reason = reason;
        this.label = label;
    }


    public static List<NotEntryPersonalReasonItem> getItems(NotEntryPersonalReason reason) {
        List<NotEntryPersonalReasonItem> items = new ArrayList<>();
        for (NotEntryPersonalReasonItem notEntryPersonalReasonItem : NotEntryPersonalReasonItem.values()) {
            if (notEntryPersonalReasonItem.getReason().equals(reason)) {
                items.add(notEntryPersonalReasonItem);
            }
        }
        return items;
    }

    public NotEntryPersonalReason getReason() {
        return reason;
    }

    public String getLabel() {
        return label;
    }
}

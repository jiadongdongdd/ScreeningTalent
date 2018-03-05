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

public enum TurnoverReason {

    LEAVE_WITHOUT_GOODBYE("不辞而别", false),
    PERSONAL_REASON("个人原因辞职", false),
    CONTRACT_FINISHED("合同到期自动终止", false),
    BUSINESS_REASON("业务原因遣散", false),

    PERSONAL_ABILITY("个人能力原因辞退", false),
    LABOR_DISPUTE("劳动纠纷", false),   //必须上传附件
    GO_OUT("开除", true);

    private String label;
    //是否可多选
    private boolean multi;

    TurnoverReason(String label, boolean multi) {
        this.label = label;
        this.multi = multi;
    }


    public List<TurnoverReasonItem> getItems() {
        return TurnoverReasonItem.getItems(this);
    }


    public boolean isGoOut() {
        return GO_OUT.equals(this);
    }

    public boolean isLaborDispute() {
        return LABOR_DISPUTE.equals(this);
    }


    public String getLabel() {
        return label;
    }

    public boolean isMulti() {
        return multi;
    }
}

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

public enum TurnoverProcess {

    //NORMAL("正常", false),
    NOT_CLEAN("交接不清", false),
    NOT_ON_TIME("未按时交接", false),

    TAKE_RESOURCES("带走公司财产信息等", true),
    LOST("失联", false),
    OTHER("其他", false),  //必须注明

    ;
    private String label;
    //是否可多选
    private boolean multi;

    TurnoverProcess(String label, boolean multi) {
        this.label = label;
        this.multi = multi;
    }


    public boolean isTakeResources() {
        return TAKE_RESOURCES.equals(this);
    }

    public List<TurnoverProcessItem> getItems() {
        return TurnoverProcessItem.getItems(this);
    }

    public String getLabel() {
        return label;
    }

    public boolean isMulti() {
        return multi;
    }
}

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
package com.idsmanager.xsifter.domain;

/**
 * 
 * @author andy
 *
 */
public enum IntentionStatus {

    ENABLED("是"),
    DISABLED("否");

    private String label;

    IntentionStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public boolean isEnabled() {
        return ENABLED.equals(this);
    }

    public boolean isDisabled() {
        return DISABLED.equals(this);
    }
}

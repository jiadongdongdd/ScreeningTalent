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
package com.idsmanager.xsifter.domain.admin.directory;

/**
 * 2016/1/28
 * <p/>
 * <p/>
 * 数据字典类型
 *
 * @author Shengzhao Li
 */

public enum DirectoryType {

    NOT_JOIN_INTENTION("不参加面试原因"),
    NOT_ENTRY_COMPANY_REASON("不同意入职的(公司)原因"),
    ;

    private String label;

    DirectoryType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

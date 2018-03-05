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
 *
 * @author Shengzhao Li
 */

public enum NotJoinIntentionReason {

    NOT_WANTED("无离职意愿"),
    ALREADY_WORKED("已入职其他公司"),
    NOT_CONNECTED("沟通不畅或无法联系"),
    LAKE_CIVILIZED("缺乏文明礼貌（无礼、挂电话、说话不文明）"),

    REJECT_INTENTION("拒绝面试"),
    OTHER("其他（测试不符合职位要求）");

    private String label;

    NotJoinIntentionReason(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

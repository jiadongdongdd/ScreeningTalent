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
package com.idsmanager.xsifter.infrastructure;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 2016/1/29
 *
 * @author Shengzhao Li
 */
public class XsifterHolder implements InitializingBean {


    private static String host;


    public XsifterHolder() {
    }


    public static String getHost() {
        return host;
    }

    public void setHost(String host) {
        if (!host.endsWith("/")) {
            host += "/";
        }
        XsifterHolder.host = host;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(host);
    }
}

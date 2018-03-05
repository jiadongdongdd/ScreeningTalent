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
package com.idsmanager.xsifter.web.context;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 2016/1/29
 *
 * @author Shengzhao Li
 */
public class IdsCsrfRequestMatcher implements RequestMatcher {

    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

    private List<String> ignoredUris = Arrays.asList("/admin/company/create");

    public IdsCsrfRequestMatcher() {
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        final String requestURI = request.getRequestURI();
        String uri = requestURI.replace(request.getContextPath(), "");
        if (ignoredUris.contains(uri)) {
            return false;
        }
        return !allowedMethods.matcher(request.getMethod()).matches();
    }
}

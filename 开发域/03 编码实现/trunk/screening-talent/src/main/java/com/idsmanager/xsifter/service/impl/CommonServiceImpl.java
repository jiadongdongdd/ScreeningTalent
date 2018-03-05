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
package com.idsmanager.xsifter.service.impl;

import com.idsmanager.xsifter.domain.commons.CommonsRepository;
import com.idsmanager.xsifter.domain.commons.IdsFile;
import com.idsmanager.xsifter.service.CommonService;
import com.idsmanager.xsifter.service.dto.IdsFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2016/1/25
 *
 * @author Shengzhao Li
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {


    @Autowired
    private CommonsRepository commonsRepository;


    @Override
    public IdsFileDto loadFileByGuid(String guid) {
        IdsFile file = commonsRepository.findIdsFileByGuid(guid);
        return new IdsFileDto(file, true);
    }
}

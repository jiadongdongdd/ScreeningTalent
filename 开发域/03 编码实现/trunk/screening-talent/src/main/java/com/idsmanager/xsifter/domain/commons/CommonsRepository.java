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
package com.idsmanager.xsifter.domain.commons;

import com.idsmanager.xsifter.domain.Repository;

/**
 * 2016/1/25
 *
 * @author Shengzhao Li
 */

public interface CommonsRepository extends Repository {

    void saveIdsFile(IdsFile idsFile);

    void deleteIdsFile(IdsFile idsFile);

    IdsFile findIdsFileByGuid(String guid);
}
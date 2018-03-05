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
package com.idsmanager.xsifter.domain.user;


import com.idsmanager.xsifter.domain.Repository;

import java.util.List;
import java.util.Map;

/**
 * 2016/1/23
 *
 * @author Shengzhao Li
 */

public interface UserRepository extends Repository {

    void saveUser(User user);

    User findByUsername(String username);

    void removeUser(User user);

    User findByGuid(String guid);

    User currentUser();

    List<User> findUserPaginated(Map<String, Object> map);

    long totalUserPaginated(Map<String, Object> map);

    void updateUserPassword(User user);

    long countUsers();

    void updateUser(User user);

	void updateUserState(User user);

	void updateUserStateOpen(User user);

	User findByUsernameNoState(String username);

}
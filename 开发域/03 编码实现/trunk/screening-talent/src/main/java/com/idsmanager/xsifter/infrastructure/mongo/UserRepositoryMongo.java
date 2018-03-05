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
package com.idsmanager.xsifter.infrastructure.mongo;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 2016/1/23
 * 
 * @author Shengzhao Li
 */
@Repository("userRepositoryMongo")
public class UserRepositoryMongo extends AbstractMongoSupport implements
		UserRepository {

	@Autowired
	private MongoOperations mongoTemplate;

	@Override
	protected MongoOperations mongoTemplate() {
		return this.mongoTemplate;
	}

	@Override
	public void saveUser(User user) {
		mongoTemplate().save(user);
	}

	@Override
	public User findByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username)
				.andOperator(Criteria.where("disabled").is(false)));
		return mongoTemplate().findOne(query, User.class);
	}

	@Override
	public void removeUser(User user) {
		mongoTemplate().remove(user);
	}

	@Override
	public User findByGuid(String guid) {
		Query query = createIDQuery(guid);
		return mongoTemplate().findOne(query, User.class);
	}

	@Override
	public User currentUser() {
		return findById(User.class, SecurityUtils.currentUserGuid());
	}

	@Override
	public List<User> findUserPaginated(Map<String, Object> map) {

		Query query = new Query();
		addPagination(query, map);

		addQueryConditions(map, query);

		return this.mongoTemplate().find(query, User.class);
	}

	private void addQueryConditions(Map<String, Object> map, Query query) {
		final String username = (String) map.get("username");
		if (StringUtils.isNotEmpty(username)) {
			query.addCriteria(Criteria.where("username")
					.regex("/*" + username + "/*").and("privileges")
					.is(Privilege.ENTERPRISEADMIN));
		}

		if (StringUtils.isEmpty(username)) {
			final String notUserName = (String) map.get("notUsername");
			if (StringUtils.isNotEmpty(notUserName)) {
				query.addCriteria(Criteria.where("username")
						.ne("/*" + notUserName + "/*").and("privileges")
						.ne(Privilege.ADMIN));
			}
		}
	}

	@Override
	public long totalUserPaginated(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);

		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, User.class);
	}

	@Override
	public void updateUserPassword(User user) {
		Update update = new Update();
		update.set("password", user.password());

		Query query = createIDQuery(user.guid());
		this.mongoTemplate().updateFirst(query, update, User.class);
	}

	@Override
	public long countUsers() {
		final Query query = new Query();
		return this.mongoTemplate().count(query, User.class);
	}

	@Override
	public void updateUser(User user) {
		Update update = new Update();
		update.set("password", user.password());
		update.set("username", user.username());
		update.set("privileges", user.getPrivileges());

		Query query = createIDQuery(user.guid());
		this.mongoTemplate().updateFirst(query, update, User.class);
	}

	@Override
	public void updateUserState(User user) {
		Update update = new Update();
		update.set("disabled", true);
		//去掉权限
		update.set("privileges", null);
		Query query = createIDQuery(user.guid());
		this.mongoTemplate().updateFirst(query, update, User.class);

	}

	@Override
	public void updateUserStateOpen(User user) {
		Update update = new Update();
		update.set("disabled", false);
		//开启权限
		Set<Privilege> privileges = new HashSet<>();
		privileges.add(Privilege.ENTERPRISEADMIN);
		update.set("privileges",privileges);
		Query query = createIDQuery(user.guid());
		this.mongoTemplate().updateFirst(query, update, User.class);
	}

	@Override
	public User findByUsernameNoState(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		return mongoTemplate().findOne(query, User.class);
	}

}

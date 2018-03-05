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
package com.idsmanager.xsifter.service.dto.company;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.company.DeleteCompany;

import java.util.Map;

/**
 * 2016/1/20
 * 
 * @author Shengzhao Li
 */
public class DeleteCompanyPaginated extends DefaultPaginated<DeleteCompany> {

	private String companyName;

	private String guid;

	private Boolean inviteState;

	private Boolean verifyPass;

	public DeleteCompanyPaginated() {
	}

	@Override
	public Map<String, Object> queryMap() {
		final Map<String, Object> map = super.queryMap();
		map.put("companyName", companyName);
		map.put("inviteState", inviteState);
		map.put("verifyPass", verifyPass);
		map.put("guid", guid);
		return map;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Boolean getInviteState() {
		return inviteState;
	}

	public void setInviteState(Boolean inviteState) {
		this.inviteState = inviteState;
	}

	public Boolean getVerifyPass() {
		return verifyPass;
	}

	public void setVerifyPass(Boolean verifyPass) {
		this.verifyPass = verifyPass;
	}

}

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
package com.idsmanager.xsifter.service.dto.invite;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompany;

import java.util.Map;

/**
 * 2016/1/20
 *
 * @author Shengzhao Li
 */
public class InviteCompanyPaginated extends DefaultPaginated<InviteCompany> {


    private String companyName;

    private String guid;
    
    private Boolean verifyState;
    
    private String inviteUsername;//邀请人
    public InviteCompanyPaginated() {
    }

    @Override
    public Map<String, Object> queryMap() {
        final Map<String, Object> map = super.queryMap();
        map.put("companyName", companyName);
        map.put("verifyState", verifyState);
        map.put("guid", guid);
        map.put("inviteUsername", inviteUsername);
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

	public void setVerifyState(Boolean verifyState) {
		this.verifyState = verifyState;
	}

	public Boolean getVerifyState() {
		return verifyState;
	}

	public String getInviteUsername() {
		return inviteUsername;
	}

	public void setInviteUsername(String inviteUsername) {
		this.inviteUsername = inviteUsername;
	}
    
}

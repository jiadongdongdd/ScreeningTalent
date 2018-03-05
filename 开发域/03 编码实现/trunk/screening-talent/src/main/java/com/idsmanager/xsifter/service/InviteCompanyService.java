package com.idsmanager.xsifter.service;

import java.util.List;
import java.util.Map;

import com.idsmanager.xsifter.domain.inviteCompany.InviteCompany;
import com.idsmanager.xsifter.service.dto.invite.InviteCompanyFormDto;
import com.idsmanager.xsifter.service.dto.invite.InviteCompanyPaginated;

public interface InviteCompanyService {

	InviteCompanyPaginated findCompanyList(InviteCompanyPaginated paginated);

	String saveInviteCompany(InviteCompanyFormDto company);

	void updateInviteCompany(InviteCompanyFormDto company);

	InviteCompany findByUsername(String username);

	InviteCompany findByGuid(String guid);

	InviteCompany findBycompanyEmail(String username);

	InviteCompany findBycompanyName(String companyName);

	long totalCompanyInviteList(String companyGuid);
	
	long totalCompanyInviteSuccessList(String companyGuid);

	void deleteInviteCompany(String uuid);
}

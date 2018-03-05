package com.idsmanager.xsifter.domain.inviteCompany;

import java.util.List;
import java.util.Map;


public interface InviteCompanyRepository {
	
	List<InviteCompany> findCompanyList(Map<String, Object> map);
	
	long totalInviteCompanyList(Map<String, Object> map);
	
	void saveInviteCompany(InviteCompany company);
	
	void updateInviteCompany(InviteCompany company);
	
	InviteCompany findByUsername(String username);
	
	InviteCompany findByGuid(String guid);

	InviteCompany findBycompanyEmail(String email);

	InviteCompany findBycompanyName(String companyName);

	long totalCompanyInviteList(String inviteUsername);

	long totalCompanyInviteSuccessList(String inviteUsername);

	void removeInviteCompany(InviteCompany inviteCompany);
}

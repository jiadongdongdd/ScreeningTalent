package com.idsmanager.xsifter.domain.admin;

import java.util.List;
import java.util.Map;

public interface SystemAdminRepository {
	
	void saveMemberLevel(MemberLevel memberLevel);
	
	void deleteMemberLever(MemberLevel memberLevel);
	
	MemberLevel findByUUID(String uuid);
	
	MemberLevel findByLevel(String level);

	List<MemberLevel> findAllMemberLevelList();
	
	MemberLevel findMemberLevelByNumber(Integer number,String uuid);
	
	
	
	
	void saveCreditRule(CreditRule creditRule);
	
	void deleteCreditRule(CreditRule creditRule);
	
	CreditRule findCreditRuleByUUID(String uuid);
	
	CreditRule findByRuleName(String ruleName);
	
	List<CreditRule> findAllCreditRulesList();

	
	
	void saveCreditStream(CreditStream creditStream);
	
	List<CreditStream> findCreditStreamListByCompanyUUID(String companyUUID);
	
	List<CreditStream> findCreditStreamListByCompanyUUID(Map<String,Object> map,String companyUUID);
	
	long totalCreditStreamByCompanyUUID(Map<String,Object> map,String companyUUID);

	CreditRule findCreditRuleByAction(String action);

	List<CreditStream> findAllCreditStreamList(Map<String,Object> map);

	long countCreditStream(Map<String,Object> map);
	
	
	
}

package com.idsmanager.xsifter.service;

import java.util.List;

import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.MemberLevel;
import com.idsmanager.xsifter.service.business.admin.CreditNotEnoughException;
import com.idsmanager.xsifter.service.dto.admin.CreditRuleFormDto;
import com.idsmanager.xsifter.service.dto.admin.CreditStreamDto;
import com.idsmanager.xsifter.service.dto.admin.CreditStreamFormDto;
import com.idsmanager.xsifter.service.dto.admin.CreditStreamPaginated;
import com.idsmanager.xsifter.service.dto.admin.MemberLeveLFormDto;
import com.idsmanager.xsifter.service.dto.admin.MemberLevelDto;

public interface SystemAdminService {
	
	
	String saveMemberLevel(MemberLeveLFormDto fromDto);
	
	String updateMemberLevel(MemberLevel memberLevel);
	
	void deleteMemberLevel(String uuid);
	
	boolean checkRegionForNumber(Integer nubmer,String uuid);
	
	MemberLevelDto getMemberLevelDtoByUUID(String uuid);
	
	MemberLevelDto getMemberLevelDtoByLevel(String level);
	
	List<MemberLevelDto> getAllMemberLevelDto();

	void deleteMemberLevelByUUIDList(List<String> asList);
	
	
	
	String saveCreditRule(CreditRuleFormDto formDto);
	
	void deleteCreditRuleByUUIDList(List<String> list);
	
	CreditRuleFormDto getCreditRuleFormDtoByUUID(String uuid);
	
	CreditRuleFormDto getCreditRuleFormDtoByRuleName(String ruleName);
	
	List<CreditRuleFormDto> getAllCreditRuleFormDtos();
	
	
	
	void addCreditStream(CreditStreamFormDto formDto) throws Exception;
	
	List<CreditStreamDto> findCreditStreamDtoListByCompanyUUID(String uuid);
	
	CreditStreamPaginated loadCreditStreamPaginated(CreditStreamPaginated paginated,String companyUUID);

	CreditRuleFormDto getCreditStreamFromDtoByAction(String action);

	void addBatchCreditStream(CreditStreamFormDto formDto) throws CreditNotEnoughException, Exception;

	CreditStreamPaginated loadAllCreditStreamPaginated(
			CreditStreamPaginated paginated);

	void initCreditRule();
}

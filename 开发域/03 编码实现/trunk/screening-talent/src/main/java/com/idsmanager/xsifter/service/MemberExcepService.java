package com.idsmanager.xsifter.service;

import com.idsmanager.xsifter.service.dto.memberexcep.MemberExcepFormDto;
import com.idsmanager.xsifter.service.dto.memberexcep.MemberExcepPaginated;

public interface MemberExcepService {
	
	MemberExcepPaginated loadMemberExcepPaginated(MemberExcepPaginated paginated);
	
	long countMemberExcep();
	
	void saveMemberExcep();
	
	void deleteMemberExcep(MemberExcepFormDto dto);

	MemberExcepFormDto findMemberExcepById(String uuid);
}	

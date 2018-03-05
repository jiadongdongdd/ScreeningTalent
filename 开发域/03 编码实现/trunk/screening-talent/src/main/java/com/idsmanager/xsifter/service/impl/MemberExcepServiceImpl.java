package com.idsmanager.xsifter.service.impl;

import org.springframework.stereotype.Service;

import com.idsmanager.xsifter.service.MemberExcepService;
import com.idsmanager.xsifter.service.business.memberexcep.LoadMemberExcepHandler;
import com.idsmanager.xsifter.service.dto.memberexcep.MemberExcepFormDto;
import com.idsmanager.xsifter.service.dto.memberexcep.MemberExcepPaginated;
@Service("memberExcepService")
public class MemberExcepServiceImpl implements MemberExcepService {

	@Override
	public MemberExcepPaginated loadMemberExcepPaginated(
			MemberExcepPaginated paginated) {
		LoadMemberExcepHandler handler = new LoadMemberExcepHandler(paginated);
		return handler.load();
	}

	@Override
	public long countMemberExcep() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveMemberExcep() {
		 

	}

	@Override
	public void deleteMemberExcep(MemberExcepFormDto dto) {
		

	}

	@Override
	public MemberExcepFormDto findMemberExcepById(String uuid) {
		LoadMemberExcepHandler load = new LoadMemberExcepHandler();
		load.setUuid(uuid);
		return load.loadOne();
	}

}

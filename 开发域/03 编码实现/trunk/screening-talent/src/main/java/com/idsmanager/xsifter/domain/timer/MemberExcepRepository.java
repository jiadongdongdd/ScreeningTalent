package com.idsmanager.xsifter.domain.timer;

import java.util.List;
import java.util.Map;

import com.idsmanager.xsifter.domain.Repository;
import com.idsmanager.xsifter.domain.news.News;

public interface MemberExcepRepository extends Repository {
	
	long totalMemberExcepList(Map<String, Object> map);

	List<MemberExcep> findMemberExcepList(Map<String, Object> map);

	void saveMemberExcep(MemberExcep news);

	MemberExcep findMemberExcepByUuid(String uuid);

	void deleteMemberExcep(MemberExcep news);

	MemberExcep findMemberExcepByIDNember(String idNumber);
}

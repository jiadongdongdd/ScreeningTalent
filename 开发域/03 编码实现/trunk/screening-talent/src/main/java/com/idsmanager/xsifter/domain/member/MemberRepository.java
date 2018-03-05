package com.idsmanager.xsifter.domain.member;

import java.util.List;
import java.util.Map;

import com.idsmanager.xsifter.domain.Repository;

public interface MemberRepository extends Repository {

	void saveMember(Member member);

	void updateBasicMember(Member member);

	Member findMemberByUuid(String uuid);

	List<Member> findMemberList(Map<String, Object> map);

	long totalMemberList(Map<String, Object> map);

	Member findMemberByMobile(String mobile);

	Member findMemberByIDNumber(String idNumber);

	List<Member> findMemberListByWide(Map<String, Object> map);

	long totalMemberListByWide(Map<String, Object> map);

	int findMembersCountByCompanyId(String uuid);

	Recruitment findRecruitmentByUuid(String uuid);

	int findMemberTotalCount();

	void saveRecruitment(Recruitment recruitment);

	void updateRecruitment(Recruitment recruitment);

	Member findMemberByUUIDAndCompanyGuid(String uuid, String guid);

	Turnover findTurnoverByUuid(String uuid);

	void saveTurnover(Turnover turnover);

	void updateTurnover(Turnover turnover);

	Worked findWorkedByUuid(String uuid);

	void saveWorked(Worked worked);

	void updateWorked(Worked worked);

	void deleteMember(Member member);

	List<Member> findAdminMemberList(Map<String, Object> map);

	long findAdminMemberTotalCount(Map<String, Object> map);

	List<Member> findHomeMemberList(Map<String, Object> map);

	long totalHomeMemberList(Map<String, Object> map);

	long totalMembers();

	List<Member> findMembersByCompanyGuid(String companyGuid);

	long companyTotalMembers(String companyGuid);

	Member findMemberByMobileAndCompanyUuid(String mobile,
			String currentUserGuid);

	Member findMemberByIDNumberAndCompanyGuid(String idNumber,
			String currentUserGuid);

	List<Member> findMemberList();

	void updateMemberDataState(Member member1, MemberProcess memberProcess);

	void saveBasic(Basic basic);

	Basic findBasicByUuid(String uuid);

	void saveEducation(Education education);

	Education findEducationByUuid(String uuid);

	Member findMemberByEmailAndCompanyGuid(String email, String currentUserGuid);

	List<Member> findMyMemberList(Map<String, Object> queryMap);

	long totalMyMemberList(Map<String, Object> queryMap);

	List<Member> findQueryMemberList(Map<String, Object> map);

	long totalQueryMemberList(Map<String, Object> map);

	long totalQueryRecruitmentList(Map<String, Object> map);

	List<Recruitment> findQueryRecruitmentList(Map<String, Object> map);

	List<Turnover> findQueryTurnoverList(Map<String, Object> map);

	long totalQueryTurnoverList(Map<String, Object> map);

	long totalQueryWorkedList(Map<String, Object> map);

	List<Worked> findQueryWorkedList(Map<String, Object> map);

	Entry findEntryByUuid(String memberUuid);

	void saveEntry(Entry entry);

	long totalEntryList(Map<String, Object> map3);

	List<Entry> findEntryList(Map<String, Object> map3);

	List<Recruitment> findRecruitmentList(Map<String, Object> map2);

	long totalRecruitmentList(Map<String, Object> map2);

	List<Recruitment> findRecruitmentTelList(Map<String, Object> map1);

	long totalRecruitmentTelList(Map<String, Object> map1);

	void deleteBasic(Basic basic);

	void deleteEducation(Education education);

	void deleteRecruitment(Recruitment recruitment);

	void deleteTurnover(Turnover turnover);

	void deleteWorked(Worked worked);

	void deleteEntry(Entry entry);

	Member findMemberByBasic(Basic basic);

	long getMemberCountByCompanyUuid(String currentUserGuid);

}

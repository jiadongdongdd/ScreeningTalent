package com.idsmanager.xsifter.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.service.dto.IdsFileDto;
import com.idsmanager.xsifter.service.dto.menber.DetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.EntryRecordPaginated;
import com.idsmanager.xsifter.service.dto.menber.ImportExcelFormDto;
import com.idsmanager.xsifter.service.dto.menber.ImportExcelResultDto;
import com.idsmanager.xsifter.service.dto.menber.InterviewRecordPaginated;
import com.idsmanager.xsifter.service.dto.menber.MemberDetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.MemberFormDto;
import com.idsmanager.xsifter.service.dto.menber.MemberFormOneDto;
import com.idsmanager.xsifter.service.dto.menber.MemberFormTwoDto;
import com.idsmanager.xsifter.service.dto.menber.MemberPaginated;
import com.idsmanager.xsifter.service.dto.menber.MyMemberPaginated;
import com.idsmanager.xsifter.service.dto.menber.QueryDetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.QueryMemberDetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.QueryResultDto;
import com.idsmanager.xsifter.service.dto.menber.RecruitmentFormDto;
import com.idsmanager.xsifter.service.dto.menber.RecruitmentQueryPaginated;
import com.idsmanager.xsifter.service.dto.menber.TelInvitationPaginated;
import com.idsmanager.xsifter.service.dto.menber.TurnoverFormDto;
import com.idsmanager.xsifter.service.dto.menber.WorkedFormDto;

public interface MemberService {

	MemberPaginated loadMemberPaginated(MemberPaginated paginated);

	MemberFormDto loadMemberFormDto(String uuid);

	String saveBasicMember(MemberFormOneDto formDto);

	Member findMemberByMobile(String mobile);

	Member findMemberByIDNumber(String idNumber);

	void saveMemberEducation(MemberFormTwoDto formDto);

	MemberFormOneDto loadMyFormOneDto(String uuid);

	MemberFormTwoDto loadMemberFormTwoDto(String uuid);

	RecruitmentFormDto loaRecruitmentFormDto(String uuid);

	void saveIntentionMember(RecruitmentFormDto formDto);

	int findMembersCountByCompanyId(String uuid);

	MemberFormOneDto loadMemberFormOneDto(String uuid);

	MemberFormDto loadWideMemberFormDto(String uuid);

	TurnoverFormDto loadTurnoverFormDto(String uuid);

	String saveTurnoverMember(TurnoverFormDto formDto);

	WorkedFormDto loadWorkedFormDto(String uuid);

	String saveWorkedMember(WorkedFormDto formDto);

	void deleteMyMember(String uuid);

	MemberPaginated loadAdminMemberPaginated(MemberPaginated paginated);

	MemberPaginated loadHomeMemberPaginated(MemberPaginated paginated);

	List<Member> findMembersByCompanyGuid(String companyGuid);

	long companyTotalMembers(String companyGuid);

	Member findMemberByMobileAndCompanyUuid(String mobile,
			String currentUserGuid);

	Member findMemberByIDNumberAndCompanyGuid(String idNumber,
			String currentUserGuid);

	Member findMemberByEmailAndCompanGuid(String email, String currentUserGuid);

	MemberDetailFormDto loadMemberDetialFormDto(String uuid);

	QueryDetailFormDto loadWideMemberDetailFormDto(String uuid,
			RecruitmentQueryPaginated paginated);

	MyMemberPaginated loadMyMemberPaginated(MyMemberPaginated paginated);

	MyMemberPaginated loadQueryMemberPaginated(MyMemberPaginated paginated,
			boolean isSwitch);

	boolean queryMemberHadle(MyMemberPaginated paginated, boolean isSwitch);

	QueryMemberDetailFormDto loadWideMemberDetailFormDto(String uuid,
			TelInvitationPaginated telPaginated,
			InterviewRecordPaginated interviewRecordPaginated,
			EntryRecordPaginated entryRecordPaginated);

	DetailFormDto loadPartInfo(String partName, String uuid);

	void deleteMember(String uuid);

	Recruitment findRecruitment(String uuid);

	Turnover findTurnoverByUuid(String uuid);

	ImportExcelResultDto importExcel(ImportExcelFormDto formDto);

	IdsFileDto exportModel();

	QueryResultDto validateFiled(MyMemberPaginated paginated);
}

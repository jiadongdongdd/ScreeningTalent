package com.idsmanager.xsifter.service;

import java.util.List;

import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberInfos;
import com.idsmanager.xsifter.service.business.admin.CreditNotEnoughException;
import com.idsmanager.xsifter.service.dto.company.CompanyCreditStreamPaginated;
import com.idsmanager.xsifter.service.dto.company.CompanyFormDto;
import com.idsmanager.xsifter.service.dto.company.CompanyPaginated;
import com.idsmanager.xsifter.service.dto.company.CompanyReSetFormDto;
import com.idsmanager.xsifter.service.dto.company.CompanyVerifyReasonFormDto;
import com.idsmanager.xsifter.service.dto.password.ModifyPasswordFormDto;

public interface CompanyService {

	CompanyPaginated findCompanyList(Boolean verifyState,
			CompanyPaginated paginated);

	Company findByCompanyName(String companyName);

	Company findByUsername(String username);

	Company findByCompanyNEmail(String email);
	
	CompanyVerifyReasonFormDto findCompanyById(String guid);

	long countCompany();

	void removeCompany(CompanyFormDto company);

	Company findByGuid(String guid);

	void saveCompany(CompanyFormDto company);

	void updateCompant(CompanyFormDto company);

	void updateCompantByState(Company com) throws CreditNotEnoughException, Exception;

	void updateCompantReason(CompanyVerifyReasonFormDto formDto);
	
	CompanyCreditStreamPaginated loadMyCreditStream(
			CompanyCreditStreamPaginated paginated, final String currentUserGuid);

	boolean modifyPassword(ModifyPasswordFormDto formDto);

	List<MemberInfos> findMemberListByGuid(String guid);

	void updateCompantVerifyState(Company com);

	void deletePassedCompany(String uuid);

	List<Company> findCompanyPassVerifyList(boolean b);

	void saveReSetCompany(CompanyReSetFormDto reCom);


}

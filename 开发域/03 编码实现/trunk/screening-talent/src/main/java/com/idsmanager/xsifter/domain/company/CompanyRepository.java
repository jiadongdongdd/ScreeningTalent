package com.idsmanager.xsifter.domain.company;

import java.util.List;
import java.util.Map;

import com.idsmanager.xsifter.domain.Repository;
import com.idsmanager.xsifter.domain.admin.CreditStream;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.service.dto.company.CompanyVerifyReasonFormDto;

public interface CompanyRepository extends Repository {
	
	List<Company> findCompanyList(Map<String, Object> map);
	
	long totalCompanyList(Map<String, Object> map);
	
	void removeCompany(Company company);

	Company findByGuid(String guid);
	
	Company findByCompanyName(String companyName);

	Company findByUsername(String username);

	Company findByCompanyEmail(String email);
	
	void saveCompany(Company company);
	
	void updateCompany(Company company);

	void updateCompanyByState(Company com);

	long countCompany();

	long totalCompanyCreditStreamByCompanyUUID(Map<String, Object> map,
			String companyUUID);

	List<CreditStream> findCompanyCreditStreamListByCompanyUUID(
			Map<String, Object> map, String companyUUID);
	
	Company findLoginCompanyByUsername(String username);//init company info check for state.

	List<Member> findMemberListByGuid(String guid);

	void updateCompanyReason(Company  company);

	long totalCompany();

	List<Company> findCompanyPassVerifyList(boolean b);

	void updateCompanyCredit(Company company);


	
}

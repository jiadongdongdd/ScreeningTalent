package com.idsmanager.xsifter.service;


import com.idsmanager.xsifter.domain.company.DeleteCompany;
import com.idsmanager.xsifter.service.business.admin.CreditNotEnoughException;
import com.idsmanager.xsifter.service.dto.company.DeleteCompanyFormDto;
import com.idsmanager.xsifter.service.dto.company.DeleteCompanyPaginated;

public interface DeleteCompanyService {

	DeleteCompanyPaginated findCompanyList(DeleteCompanyPaginated paginated);

	DeleteCompany findByCompanyName(String companyName);

	DeleteCompany findByUsername(String username);

	DeleteCompany findByCompanyNEmail(String email);
	
	DeleteCompanyFormDto findCompanyById(String guid);

	long countDeleteCompany();

	void removeDeleteCompany(DeleteCompanyFormDto company);

	DeleteCompany findByGuid(String guid);

	void saveDeleteCompany(DeleteCompanyFormDto company);

	void updateDeleteCompant(DeleteCompanyFormDto company);

	void updateDeleteCompantByState(DeleteCompany com) throws CreditNotEnoughException, Exception;

}

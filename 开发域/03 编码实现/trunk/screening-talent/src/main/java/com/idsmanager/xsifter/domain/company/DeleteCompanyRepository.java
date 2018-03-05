package com.idsmanager.xsifter.domain.company;

import java.util.List;
import java.util.Map;

import com.idsmanager.xsifter.domain.Repository;

public interface DeleteCompanyRepository extends Repository {
	
	List<DeleteCompany> finddeleteCompanyList(Map<String, Object> map);
	
	long totaldeleteCompanyList(Map<String, Object> map);
	
	void removedeleteCompany(DeleteCompany company);

	DeleteCompany findByGuid(String guid);
	
	DeleteCompany findBydeleteCompanyName(String companyName);

	DeleteCompany findByUsername(String username);

	DeleteCompany findBydeleteCompanyEmail(String email);
	
	void savedeleteCompany(DeleteCompany company);
	
	void updatedeleteCompany(DeleteCompany company);

	void updatedeleteCompanyByState(DeleteCompany com);

}

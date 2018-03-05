package com.idsmanager.xsifter.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idsmanager.xsifter.domain.company.DeleteCompany;
import com.idsmanager.xsifter.domain.company.DeleteCompanyRepository;
import com.idsmanager.xsifter.service.DeleteCompanyService;
import com.idsmanager.xsifter.service.business.admin.CreditNotEnoughException;
import com.idsmanager.xsifter.service.business.company.DeleteCreateCompanyHandler;
import com.idsmanager.xsifter.service.business.company.LoadDeleteCompanyHandler;
import com.idsmanager.xsifter.service.dto.company.DeleteCompanyFormDto;
import com.idsmanager.xsifter.service.dto.company.DeleteCompanyPaginated;
@Service("deleteCompanyService")
public class DeleteCompanyServiceImpl implements DeleteCompanyService {

	private static final Logger LOG = LoggerFactory.getLogger(DeleteCompanyServiceImpl.class);

    @Autowired
    private DeleteCompanyRepository companyDao;

	@Override
	public DeleteCompanyPaginated findCompanyList(
			DeleteCompanyPaginated paginated) {
		 LoadDeleteCompanyHandler handler = new LoadDeleteCompanyHandler(paginated);
		return handler.load();
	}

	@Override
	public DeleteCompany findByCompanyName(String companyName) {
		 
		return companyDao.findBydeleteCompanyName(companyName);
	}

	@Override
	public DeleteCompany findByUsername(String username) {
		return companyDao.findByUsername(username);
	}

	@Override
	public DeleteCompany findByCompanyNEmail(String email) {
		return companyDao.findBydeleteCompanyEmail(email);
	}

	@Override
	public DeleteCompanyFormDto findCompanyById(String guid) {
		return null;
	}

	@Override
	public long countDeleteCompany() {
	 
		return 0;
	}

	@Override
	public void removeDeleteCompany(DeleteCompanyFormDto company) {
		companyDao.removedeleteCompany(companyDao.findByGuid(company.getGuid()));
	}

	@Override
	public DeleteCompany findByGuid(String guid) {
		return companyDao.findByGuid(guid);
	}

	@Override
	public void saveDeleteCompany(DeleteCompanyFormDto company) {
		DeleteCreateCompanyHandler hanlder = new DeleteCreateCompanyHandler(company);
		hanlder.handle();
	}

	@Override
	public void updateDeleteCompant(DeleteCompanyFormDto company) {
	}

	@Override
	public void updateDeleteCompantByState(DeleteCompany com)
			throws CreditNotEnoughException, Exception {
		
	}


}

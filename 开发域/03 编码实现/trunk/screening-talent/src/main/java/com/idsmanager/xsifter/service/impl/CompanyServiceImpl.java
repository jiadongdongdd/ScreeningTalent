package com.idsmanager.xsifter.service.impl;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.xsifter.domain.admin.CreditStream;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.member.MemberInfos;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.business.admin.CreditNotEnoughException;
import com.idsmanager.xsifter.service.business.company.AdminPassedCompanyRemover;
import com.idsmanager.xsifter.service.business.company.CreateCompanyHandler;
import com.idsmanager.xsifter.service.business.company.LoadCompanyHandler;
import com.idsmanager.xsifter.service.business.company.MyCompanyPasswordModifier;
import com.idsmanager.xsifter.service.business.company.UpdateCompanyHandler;
import com.idsmanager.xsifter.service.dto.company.CompanyCreditStreamPaginated;
import com.idsmanager.xsifter.service.dto.company.CompanyFormDto;
import com.idsmanager.xsifter.service.dto.company.CompanyPaginated;
import com.idsmanager.xsifter.service.dto.company.CompanyReSetFormDto;
import com.idsmanager.xsifter.service.dto.company.CompanyVerifyReasonFormDto;
import com.idsmanager.xsifter.service.dto.password.ModifyPasswordFormDto;
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	private static final Logger LOG = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyRepository companyDao;

	@Override
	public CompanyPaginated findCompanyList(Boolean verifyState,CompanyPaginated paginated) {
		LoadCompanyHandler handler = new LoadCompanyHandler(paginated);
		return handler.load(verifyState);
	}

	@Override
	public void removeCompany(CompanyFormDto company) {
		Company com = companyDao.findByGuid(company.getGuid());
		companyDao.removeCompany(com);
	}

	@Override
	public Company findByGuid(String guid) {
		 
		return  companyDao.findByGuid(guid); 
	}

	@Override
	public void saveCompany(CompanyFormDto company) {
		CreateCompanyHandler handler = new CreateCompanyHandler(company);
		handler.handle();

	}

	@Override
	public void updateCompant(CompanyFormDto company) {
		UpdateCompanyHandler handler = new UpdateCompanyHandler(company);
		handler.update();
	}

    @Override
    public Company findByCompanyName(String companyName) {
        return companyDao.findByCompanyName(companyName);
    }

	@Override
	public Company findByUsername(String username) {
		return companyDao.findByUsername(username);
	}

	@Override
	public Company findByCompanyNEmail(String email) {
		return companyDao.findByCompanyEmail(email);
	}

	@Override
	public void updateCompantByState(Company com) throws CreditNotEnoughException, Exception {
		companyDao.updateCompanyByState(com);
		UpdateCompanyHandler handler = new UpdateCompanyHandler(com);
		handler.send();
	}

	@Override
	public long countCompany() {
		 
		return companyDao.countCompany();
	}

	@Override
	public CompanyCreditStreamPaginated loadMyCreditStream(CompanyCreditStreamPaginated paginated,
			final String currentUserGuid) {
		final Map<String, Object> map = paginated.queryMap();
		
		return paginated.load(new PaginatedLoader<CreditStream>() {
			
			@Override
			public long loadTotalSize() {
				return companyDao.totalCompanyCreditStreamByCompanyUUID(map,currentUserGuid);
			}
			
			@Override
			public List<CreditStream> loadList() {
				return companyDao.findCompanyCreditStreamListByCompanyUUID(map,currentUserGuid);
			}
		});
	}

	@Override
	public boolean modifyPassword(ModifyPasswordFormDto formDto) {
		MyCompanyPasswordModifier modifier = new MyCompanyPasswordModifier(formDto);
		return modifier.modify();
	}

	@Override
	public List<MemberInfos> findMemberListByGuid(String guid) {
		LoadCompanyHandler handler = new LoadCompanyHandler();
		return handler.loadMemberAll(guid);
	}

	@Override
	public CompanyVerifyReasonFormDto findCompanyById(String guid) {
		
		return new  CompanyVerifyReasonFormDto(companyDao.findByGuid(guid));
	}

	@Override
	public void updateCompantReason(CompanyVerifyReasonFormDto formDto) {
		UpdateCompanyHandler handler = new UpdateCompanyHandler(formDto);
		handler.updateVerifyReason();
	}

	@Override
	public void updateCompantVerifyState(Company com) {
		companyDao.updateCompanyByState(com);
		
	}

	@Override
	public void deletePassedCompany(String uuid) {
		
		AdminPassedCompanyRemover remover = new AdminPassedCompanyRemover(uuid);
		remover.remove();
		
	}

	@Override
	public List<Company> findCompanyPassVerifyList(boolean b) {
		return companyDao.findCompanyPassVerifyList(b);
		
	}

	@Override
	public void saveReSetCompany(CompanyReSetFormDto reCom) {
		CreateCompanyHandler handler = new CreateCompanyHandler(reCom);
		handler.handleReSet();
	}

}

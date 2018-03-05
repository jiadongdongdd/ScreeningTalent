package com.idsmanager.xsifter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idsmanager.xsifter.domain.inviteCompany.InviteCompany;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompanyRepository;
import com.idsmanager.xsifter.service.InviteCompanyService;
import com.idsmanager.xsifter.service.business.invite.CreateInviteCompanyHandler;
import com.idsmanager.xsifter.service.business.invite.InviteCompanyRemover;
import com.idsmanager.xsifter.service.business.invite.LoadInviteCompanyHandler;
import com.idsmanager.xsifter.service.dto.invite.InviteCompanyFormDto;
import com.idsmanager.xsifter.service.dto.invite.InviteCompanyPaginated;
@Service("inviteCompanyService")
public class InviteCompanyServiceImpl implements InviteCompanyService{
	@Autowired
	private InviteCompanyRepository companyRepository;
	@Override
	public InviteCompanyPaginated findCompanyList(InviteCompanyPaginated paginated) {
		 LoadInviteCompanyHandler handler = new LoadInviteCompanyHandler(paginated);
		return handler.load();
	}

	@Override
	public String saveInviteCompany(InviteCompanyFormDto formDto) {
		CreateInviteCompanyHandler handler = new CreateInviteCompanyHandler(formDto);
		return handler.handle();
	}

	@Override
	public void updateInviteCompany(InviteCompanyFormDto company) {
		
	}

	@Override
	public InviteCompany findByUsername(String username) {
		 
		return companyRepository.findByUsername(username);
	}

	@Override
	public InviteCompany findByGuid(String guid) {
		 
		return companyRepository.findByGuid(guid);
	}

	@Override
	public InviteCompany findBycompanyEmail(String email) {
		return companyRepository.findBycompanyEmail(email);
	}

	@Override
	public InviteCompany findBycompanyName(String companyName) {
		return companyRepository.findBycompanyName(companyName);
	}

	@Override
	public long totalCompanyInviteList(String companyGuid) {
		return this.companyRepository.totalCompanyInviteList(companyGuid);
	}

	@Override
	public long totalCompanyInviteSuccessList(String companyGuid) {
		return this.companyRepository.totalCompanyInviteSuccessList(companyGuid);
	}

	@Override
	public void deleteInviteCompany(String uuid) {
		
		InviteCompanyRemover remover = new InviteCompanyRemover(uuid);
		remover.remove();
		
	}

}

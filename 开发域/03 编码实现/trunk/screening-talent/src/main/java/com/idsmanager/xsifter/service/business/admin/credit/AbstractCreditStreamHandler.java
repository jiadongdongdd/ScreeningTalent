package com.idsmanager.xsifter.service.business.admin.credit;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.admin.CreditStream;
import com.idsmanager.xsifter.domain.admin.SystemAdminRepository;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.business.admin.AdminUtils;
import com.idsmanager.xsifter.service.business.admin.CompanyCreditEditer;

public abstract class AbstractCreditStreamHandler implements
		CreditStreamHandler {
	
	protected transient SystemAdminRepository systemAdminRepository = BeanProvider.getBean(SystemAdminRepository.class);
	
	
	protected transient CompanyRepository companyRepository = BeanProvider.getBean(CompanyRepository.class);
	
	protected transient UserRepository userRepositoryMongo = BeanProvider.getBean(UserRepository.class);
	
	protected CreditStreamHolder holder;
	
	@Override
	public final void handle(CreditStreamHolder holder) throws Exception {
		this.holder = holder;
		Company company = companyRepository.findByGuid(holder.getCompanyUuid());
		Integer creditNumber = this.getCreditNumber();
		CreditStream creditStream = newCreditStream(creditNumber,company.getCreditNumber());
		//公司积分更改
		CompanyCreditEditer companyCreditEditer = new CompanyCreditEditer(company, creditStream.creditDo());
		companyCreditEditer.edit(holder);
		//积分流水记录
		this.handle(creditStream);
	}
	
	protected void handle(CreditStream creditStream) throws Exception{
		systemAdminRepository.saveCreditStream(creditStream);
	}
	
	protected CreditStream newCreditStream(Integer creditDo,Integer beforeCredit){
		Company company = companyRepository.findByGuid(holder.getCompanyUuid());
		String operator ;
		if(SecurityUtils.userDetails().getAuthorities().contains(Privilege.ADMIN)){
			operator = AdminUtils.ADMIN;
		}else{
			operator = SecurityUtils.username();
		}
		if(null==company){
			throw new IllegalArgumentException("company can not find by {"+holder.getCompanyUuid()+" }");
		}
		return new CreditStream().setCompanyUuid(holder.getCompanyUuid())
				.setCompanyName(company.getCompanyName())
				.setCompanyUsername(company.getUsername())
				.setCreditDo(creditDo)
				.setRemarks(holder.getRemarks())
				.setOperator(operator)
				.setOperatorDetailsName(SecurityUtils.username())
				.setCreditAction(holder.getAction().getAction())
				.setBeforeCredit(beforeCredit)
				.setNowCredit(beforeCredit+creditDo);
	}
	
	protected abstract Integer getCreditNumber() throws CreditRuleNotFoundException; 
	
	@Override
	public boolean checkCreditEnough(CreditStreamHolder holder) throws CreditRuleNotFoundException {
		this.holder = holder;
		Integer doNumber = getCreditNumber();
		Integer leftCredit = 0;
		try {
			leftCredit = companyRepository.findByGuid(holder.getCompanyUuid()).getCreditNumber();
		} catch (Exception e) {
			String username = SecurityUtils.username();
			User user = userRepositoryMongo.findByUsername(username);
			if(user.isAdmin() || user.isEnterpriseAdmin()){
				return true;
			}else{
				return false;
			}
		}
		return (doNumber+leftCredit)>=0;
	}
}

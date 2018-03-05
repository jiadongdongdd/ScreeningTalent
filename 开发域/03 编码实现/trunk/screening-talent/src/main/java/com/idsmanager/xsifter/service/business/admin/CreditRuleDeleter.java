package com.idsmanager.xsifter.service.business.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.admin.CreditRule;
import com.idsmanager.xsifter.domain.admin.SystemAdminRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

public class CreditRuleDeleter {
	
	private static final Logger LOG = LoggerFactory.getLogger(CreditRuleDeleter.class);
	
	private transient SystemAdminRepository systemAdminRepository = BeanProvider.getBean(SystemAdminRepository.class);
	
	private List<String> uuids;
	
	public CreditRuleDeleter(List<String> uuids){
		this.uuids = uuids;
	}
	
	public void delete(){
		for(String single:uuids){
			CreditRule creditRule = systemAdminRepository.findCreditRuleByUUID(single);
			if(null!=creditRule){
				LOG.debug("{} has deleted a CreditRule {}",SecurityUtils.username(),creditRule.getRuleName());
				systemAdminRepository.deleteCreditRule(creditRule);
			}
		}
	}
}

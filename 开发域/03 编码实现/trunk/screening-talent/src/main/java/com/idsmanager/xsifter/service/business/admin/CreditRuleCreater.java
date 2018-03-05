package com.idsmanager.xsifter.service.business.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.admin.CreditRule;
import com.idsmanager.xsifter.domain.admin.SystemAdminRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.admin.CreditRuleFormDto;

public class CreditRuleCreater {
	private static final Logger LOG = LoggerFactory.getLogger(CreditRuleCreater.class);
	
	private transient SystemAdminRepository systemAdminRepository = BeanProvider.getBean(SystemAdminRepository.class);
	
	
	private CreditRuleFormDto formDto;
	
	public CreditRuleCreater(CreditRuleFormDto formDto){
		this.formDto = formDto;
	}
	
	public String create(){
		CreditRule creditRule ;
		if(StringUtils.isEmpty(formDto.getUuid())){
			
			creditRule = newCreditRule();
		}else{
			creditRule = newCreditRule().setUuid(formDto.getUuid());
		}
		
		LOG.debug("{} has create a CreditRule {}",SecurityUtils.username(),formDto.getRuleName());
		
		systemAdminRepository.saveCreditRule(creditRule);
		return creditRule.getUuid();
	}
	
	public CreditRule newCreditRule(){
		return new CreditRule()
				.setRuleName(formDto.getRuleName())
				.setRemarks(formDto.getRemarks())
				.setCreditNumber(Integer.valueOf(formDto.getCreditNumber()))
				.setAction(formDto.getAction());
	}
	
}

package com.idsmanager.xsifter.service.business.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.admin.MemberLevel;
import com.idsmanager.xsifter.domain.admin.SystemAdminRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.admin.MemberLeveLFormDto;

public class MemberLevelCreater {
	
	private static final Logger LOG = LoggerFactory.getLogger(MemberLevelCreater.class);
	
	private transient SystemAdminRepository systemAdminRepository = BeanProvider.getBean(SystemAdminRepository.class);
	
	private MemberLeveLFormDto formDto;
	
	public MemberLevelCreater(MemberLeveLFormDto formDto){
		this.formDto = formDto;
	}
	
	public String create(){
		MemberLevel level ;
		if(StringUtils.isEmpty(formDto.getUuid())){
			level= newMemberLevel();
		}else{
			level = newMemberLevel().setUuid(formDto.getUuid());
		}
		
		systemAdminRepository.saveMemberLevel(level);
		
		LOG.debug("{} has create a memberlevel {}",SecurityUtils.username(),level.getLevel());
		return level.getUuid();
	}
	
	public MemberLevel newMemberLevel(){
		return new MemberLevel()
				.setLevel(formDto.getLevel())
				.setMaxNumber(Integer.valueOf(formDto.getMaxNumber()))
				.setMixNumber(Integer.valueOf(formDto.getMixNumber()))
				.setRemarks(formDto.getRemarks())
				.setRewardCoefficient(formDto.getRewardCoefficient());
	}
}

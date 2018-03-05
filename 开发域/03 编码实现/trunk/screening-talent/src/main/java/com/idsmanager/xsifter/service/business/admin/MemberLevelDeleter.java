package com.idsmanager.xsifter.service.business.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.admin.MemberLevel;
import com.idsmanager.xsifter.domain.admin.SystemAdminRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

public class MemberLevelDeleter {
	
	private static final Logger LOG = LoggerFactory.getLogger(MemberLevelDeleter.class);
	
	private transient SystemAdminRepository systemAdminRepository = BeanProvider.getBean(SystemAdminRepository.class);
	
	private List<String> list;
	
	public MemberLevelDeleter(String uuid){
		this.list.add(uuid);
	}
	
	public MemberLevelDeleter(List<String> uuids){
		this.list = uuids;
	}
	
	public MemberLevelDeleter(String[] deleteIds){
		for(String single:deleteIds){
			list.add(single);
		}
	}
	
	public void delete(){
		for(String single:list){
			MemberLevel level = systemAdminRepository.findByUUID(single);
			if (null != level) {
				LOG.debug("{} has deleted a MemberLevel {}",SecurityUtils.username(),level.getLevel());
				systemAdminRepository.deleteMemberLever(level);
				
			}
		}
	}
	
	
}

package com.idsmanager.xsifter.service.business.invite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompany;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompanyRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.infrastructure.XsifterHolder;
import com.idsmanager.xsifter.service.business.company.SenderInfoUtils;
import com.idsmanager.xsifter.service.business.company.SenderMailUtils;
import com.idsmanager.xsifter.service.dto.invite.InviteCompanyFormDto;

/**
 * 
 * 类名称 工具类 创建人 dushaofei 创建时间：2016-1-28 下午4:17:09 类描述：
 * 
 * @version
 */
public class CreateInviteCompanyHandler {
	private static final Logger LOG = LoggerFactory
			.getLogger(CreateInviteCompanyHandler.class);

	private transient InviteCompanyRepository repository = BeanProvider
			.getBean(InviteCompanyRepository.class);

	private InviteCompanyFormDto formDto;
	
	public CreateInviteCompanyHandler(InviteCompanyFormDto formDto) {
		super();
		this.formDto = formDto;
	}



	public String handle() {
		//邀请方
		String inviteUsername = SecurityUtils.username();
		 
		final InviteCompany company = new InviteCompany(formDto.getGuid(), formDto.getCreateTime(), formDto.getUsername(), formDto.getCompanyName(), formDto.getCompanyEmail(), formDto.getInviteState(),inviteUsername);
		repository.saveInviteCompany(company);
		
		//发送邮件
		SenderMailUtils.send(new SenderInfoUtils("筛子网邀请注册邮件", " ,"+inviteUsername+" 邀请您注册筛子网企业用户,注册链接: "+XsifterHolder.getHost()+"public/company/create", formDto.getUsername(), formDto.getCompanyEmail()));
		LOG.debug("{}|Create Company: {}", SecurityUtils.username(), company);

		return company.getGuid();
	}
}

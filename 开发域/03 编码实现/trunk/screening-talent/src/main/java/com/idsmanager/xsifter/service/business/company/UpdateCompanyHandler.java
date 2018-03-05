package com.idsmanager.xsifter.service.business.company;

import java.util.Collections;
import java.util.List;

import com.idsmanager.commons.utils.email.AbstractMailSender;
import com.idsmanager.commons.utils.email.MailGunSender;
import com.idsmanager.commons.utils.email.mail.Email;
import com.idsmanager.commons.utils.email.mail.Mailer;
import com.idsmanager.commons.web.context.BeanProvider;

import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.company.VerifyReason;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompany;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompanyRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.infrastructure.XsifterHolder;
import com.idsmanager.xsifter.service.business.admin.CreditNotEnoughException;
import com.idsmanager.xsifter.service.business.admin.CreditStreamCreater;
import com.idsmanager.xsifter.service.dto.company.CompanyFormDto;
import com.idsmanager.xsifter.service.dto.company.CompanyVerifyReasonFormDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * 类名称 创建人 dushaofei 创建时间：2016-1-25 上午11:38:23 类描述：
 * 
 * @version
 */
public class UpdateCompanyHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(UpdateCompanyHandler.class);

	private transient CompanyRepository comPanyDao = BeanProvider
			.getBean(CompanyRepository.class);

	private transient UserRepository userRepository = BeanProvider
			.getBean(UserRepository.class);

	private CompanyFormDto formDto;

	private CompanyVerifyReasonFormDto companyVerifyReasonFormDto;

	private transient InviteCompanyRepository inviteDao = BeanProvider
			.getBean(InviteCompanyRepository.class);

	private Company company;

	@Autowired
	private MailGunSender mailSender = new MailGunSender();

	public UpdateCompanyHandler(CompanyFormDto formDto) {
		this.formDto = formDto;
	}

	public String update() {

		final Company company = new Company(formDto.getUsername(),
				formDto.getPassword(), formDto.getCompanyName(),
				formDto.getCompanyEmail(), formDto.getContacts(),
				formDto.getContactsPhone());
		company.setGuid(formDto.getGuid());
		comPanyDao.updateCompany(company);
		LOG.debug("{}|Create Company: {}", SecurityUtils.username(), company);

		return company.guid();
	}

	public UpdateCompanyHandler(Company company) {
		super();
		this.company = company;
	}

	public UpdateCompanyHandler(CompanyVerifyReasonFormDto dto) {
		this.companyVerifyReasonFormDto = dto;
	}

	public void send() throws CreditNotEnoughException, Exception {

		if (company.getVerifyState()) {
			// 邀请人加积分
			InviteCompany com = inviteDao.findBycompanyName(company
					.getCompanyName());
			Company inviteCompany = null;
			if (null != com) {
				inviteCompany = comPanyDao.findByUsername(com
						.getInviteUsername());
			}
			if (null != inviteCompany) {
				inviterCredit(inviteCompany.getGuid(), com.getCompanyName());

				LOG.debug("{}|Invite Company add Credit: {}",
						com.getInviteUsername(), com.getCompanyName());
			}
			if (null != com) {
				invitedCredit(company.getGuid());
				LOG.debug("add  invite Credit: {}", com.getCompanyName());
			}
			User user = userRepository.findByGuid(company.getCreateUserUuid());

			if (user == null) {
				SenderMailUtils.send(new SenderInfoUtils("筛子网注册企业邮件",
						"你在筛子网注册的企业账号审核已通过,请访问连接激活登录:" + "<a href=\""
								+ XsifterHolder.getHost() + "public/active/"
								+ company.getGuid() + "\">点击激活</a>", company
								.getUsername(), company.getCompanyEmail()));
				LOG.debug("sender email to: {} ,Email:{}",
						company.getUsername(), company.getCompanyEmail());
			} else {
				company.setVerifyPass(true);
				this.comPanyDao.saveCompany(company);
			}

		}
	}

	private void inviterCredit(String inviterCompanyGuid,
			String invitedCompanyName) throws CreditNotEnoughException,
			Exception {
		CreditStreamCreater creater = new CreditStreamCreater(
				new CreditStreamHolder(inviterCompanyGuid, "邀请"
						+ invitedCompanyName + "企业注册", CreditAction.INVITE));
		creater.create();
	}

	private void invitedCredit(String invitedCompanyGuid)
			throws CreditNotEnoughException, Exception {
		CreditStreamCreater creater = new CreditStreamCreater(
				new CreditStreamHolder(invitedCompanyGuid, "公司注册成功", "20"));
		creater.create();
	}

	public void updateVerifyReason() {
		if (null != companyVerifyReasonFormDto) {
			Company company = comPanyDao.findByGuid(companyVerifyReasonFormDto
					.getCompanyId());
			if (null != company)
				updateCompanyReason(company, companyVerifyReasonFormDto);
		}

	}

	private void updateCompanyReason(Company com, CompanyVerifyReasonFormDto dto) {
		com.setVerifyReason(dto.getVerifyReason());
		com.setVerifyPass(false);
		com.setVerifyState(true);
		comPanyDao.updateCompanyByState(com);
		comPanyDao.updateCompanyReason(com);
		sendfaild(com, dto.getVerifyReason());
		// 物理删除
//		comPanyDao.removeCompany(com);
	}

	public void sendfaild(Company com, VerifyReason[] reasons) {
		String reason = "";
		for (int i = 0; i < reasons.length; i++) {
			VerifyReason verify = reasons[i];
			reason += "," + verify.getLabel();
		}
		String reasonStr = reason.replaceFirst(",", "");
		SenderMailUtils.send(new SenderInfoUtils("筛子网注册企业邮件",
				"你在筛子网注册的企业账号审核未通过,请重新注册:" + "<a href=\""
						+ XsifterHolder.getHost() + "public/company/resetCreate/"+com.getGuid()
						 + "\">重新注册</a> " + ", 失败原因:"
						+ reasonStr + "。", com.getUsername(), com
						.getCompanyEmail()));
	}
}

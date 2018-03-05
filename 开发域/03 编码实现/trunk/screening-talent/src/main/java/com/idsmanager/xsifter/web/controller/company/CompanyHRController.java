package com.idsmanager.xsifter.web.controller.company;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idsmanager.commons.utils.excel.ExcelUtils;
import com.idsmanager.commons.web.WebUtils;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompany;
import com.idsmanager.xsifter.domain.log.Log;
import com.idsmanager.xsifter.domain.log.Logs;
import com.idsmanager.xsifter.domain.log.OperateType;
import com.idsmanager.xsifter.domain.member.MemberInfos;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.InviteCompanyService;
import com.idsmanager.xsifter.service.LogService;
import com.idsmanager.xsifter.service.LogsService;
import com.idsmanager.xsifter.service.MemberService;
import com.idsmanager.xsifter.service.UserService;
import com.idsmanager.xsifter.service.dto.invite.InviteCompanyFormDto;
import com.idsmanager.xsifter.service.dto.invite.InviteCompanyPaginated;

/**
 * 
 * 企业邀请 详情 类名称 创建人 dushaofei 创建时间：2016-1-25 上午11:59:14 类描述：
 * 
 * @version
 */
@Controller
public class CompanyHRController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private LogsService logsService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private InviteCompanyService inviteCompanyService;
	@Autowired
	private UserService userService;
	@Autowired
	private LogService logService;

	/**
	 * 企业查看自己信息 详情 thinkpad dushaofei
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/enterprise/company/detail")
	public String companyVerify(Model model, HttpServletRequest request) {
		String username = SecurityUtils.username();
		Company com = companyService.findByUsername(username);
		if (null != com) {
			Logs logs = logsService.findLogsByUsername(username);
			String ip = WebUtils.getIp();
			// 成员
			int mumberNumber = memberService.findMembersCountByCompanyId(com
					.getGuid());
			model.addAttribute("mumberNumber", mumberNumber);
			model.addAttribute("log", logs);
			model.addAttribute("ip", ip);
			model.addAttribute("formDto", com);
		}
		return "enterprise/enterpriseInfo";
	}

	/**
	 * admin 企业 和企业admin 邀请企业注册 thinkpad dushaofei
	 * 
	 * @param formDto
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/enterprise/inviteRegister",
			"/admin/enterprise/inviteRegister",
			"/enterpriseAdmin/enterprise/inviteRegister" })
	public String inviteRegiter(Model model) {
		InviteCompanyFormDto formDto = new InviteCompanyFormDto();
		model.addAttribute("formDto", formDto);
		return "enterprise/invite_form";
	}

	/**
	 * admin 企业 和企业admin 邀请企业注册 thinkpad dushaofei
	 * 
	 * @param formDto
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/enterprise/inviteRegister",
			"/admin/enterprise/inviteRegister",
			"/enterpriseAdmin/enterprise/inviteRegister" }, method = RequestMethod.POST)
	public String submitAdmin(
			@ModelAttribute("formDto") @Valid InviteCompanyFormDto formDto,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "enterprise/invite_form";
		}
		inviteCompanyService.saveInviteCompany(formDto);
		model.addAttribute("alert", "saveOK");
		return "enterprise/invite_form";
	}

	/**
	 * admin 和企业管理员 查看邀请记录 thinkpad dushaofei
	 * 
	 * @param paginated
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/enterprise/list", "/admin/enterprise/list",
			"/enterpriseAdmin/enterprise/list" })
	public String inviteList(InviteCompanyPaginated paginated, Model model) {
		paginated = inviteCompanyService.findCompanyList(paginated);
		String userGuid = SecurityUtils.currentUserGuid();
		User user = userService.getUserByGuid(userGuid);
		model.addAttribute("user", user);
		model.addAttribute("paginated", paginated);
		return "enterprise/invite_list";
	}

	@RequestMapping(value = "/admin/enterprise/invite/delete_{uuid}", method = RequestMethod.GET)
	public String deleteInviteCompany(@PathVariable("uuid") String uuid,
			Model model) {

		inviteCompanyService.deleteInviteCompany(uuid);
		return "redirect:../list";

	}

	/**
	 * 企业查看被自己邀请的企业 集合 thinkpad dushaofei
	 * 
	 * @param paginated
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/enterprise/invite/List")
	public String companyinviteList(InviteCompanyPaginated paginated,
			Model model) {
		paginated.setInviteUsername(SecurityUtils.username());
		paginated = inviteCompanyService.findCompanyList(paginated);
		model.addAttribute("paginated", paginated);
		return "enterprise/companyInvite_list";
	}

	/**
	 * 企业导出excel thinkpad dushaofei
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "/enterprise/excel", "/admin/excel" })
	public String excel(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String guid = SecurityUtils.currentUserGuid();
		List<MemberInfos> listMember = companyService
				.findMemberListByGuid(guid);
		try {
			ExcelUtils._importExcel(request, response, listMember);
			Log log = new Log()
					.setOperator(SecurityUtils.username())
					.setOperatorId(SecurityUtils.currentUserGuid())
					.setOperateType(OperateType.EXPORT_MEMBER_EXCEL)
					.setOperateDetail(
							OperateType.EXPORT_MEMBER_EXCEL.getLabel());
			this.logService.saveLog(log);
		} catch (Exception e) {
			throw new IllegalStateException("Write file to disk wrong"
					+ e.getMessage());
		}

		return null;
	}

	/**
	 * 企业查看自己邀请记录 明细 thinkpad dushaofei
	 * 
	 * @param model
	 * @param guid
	 * @return
	 */
	@RequestMapping(value = { "/enterpriseAdmin/enterprise/detail/{guid}",
			"/admin/enterprise/detail/{guid}",
			"/enterprise/companyInvite/detail/{guid}" })
	public String companyinviteDetail(Model model, @PathVariable String guid) {
		InviteCompany inviteCompany = inviteCompanyService.findByGuid(guid);
		String userGuid = SecurityUtils.currentUserGuid();
		User user = userService.getUserByGuid(userGuid);
		Company company = companyService.findByGuid(userGuid);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("invite", inviteCompany);
		return "enterprise/companyInvite_detail";
	}

}

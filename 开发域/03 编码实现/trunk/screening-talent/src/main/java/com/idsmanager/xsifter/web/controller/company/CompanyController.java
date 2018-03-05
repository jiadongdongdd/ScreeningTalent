package com.idsmanager.xsifter.web.controller.company;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idsmanager.commons.utils.excel.ExcelUtils;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.Industry;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.UserService;
import com.idsmanager.xsifter.service.business.admin.CreditNotEnoughException;
import com.idsmanager.xsifter.service.dto.company.CompanyFormDto;
import com.idsmanager.xsifter.service.dto.company.CompanyPaginated;

/**
 * 
 * 类名称 创建人 dushaofei 创建时间：2016-1-25 上午11:59:14 类描述：
 * 
 * @version
 */
@Controller
public class CompanyController {

	private static final Logger LOG = LoggerFactory
			.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;

	/**
	 * 对外企业注册 thinkpad dushaofei
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/public/company/create")
	public String companyCreate(Model model) {
		CompanyFormDto formDto = new CompanyFormDto();
		Industry[] indus = formDto.getIndustries();
		model.addAttribute("indus", indus);
		model.addAttribute("formDto", formDto);
		return "company/company_form";
	}

	/**
	 * 对外企业注册 thinkpad dushaofei
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/public/company/create", method = RequestMethod.POST)
	public String submitOut(
			@ModelAttribute("formDto") @Valid CompanyFormDto formDto,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("hasErrors", "Y");
			return "company/company_form";
		}
		companyService.saveCompany(formDto);
		model.addAttribute("alert", "saveOK");
		model.addAttribute("result", "companySuccess");
		return "company/success";
	}

	/**
	 * 企业协议 thinkpad dushaofei
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/public/company/agree")
	public String companyAgree(Model model) {
		return "company/company_agree";
	}

	/**
	 * 企业协议 thinkpad dushaofei
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/public/company/agreeAlert")
	public String companyAgreeAlert(Model model) {
		return "company/company_agreeAlert";
	}

	/**
	 * admin 和企业admin 管理后台企业列表 未审核 thinkpad dushaofei
	 * 
	 * @param paginated
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/company/NoVerify/{state}",
			"/enterpriseAdmin/company/NoVerify/{state}" })
	public String companyFalseList(@PathVariable String state,
			CompanyPaginated paginated, Model model) {
		paginated = companyService.findCompanyList(Boolean.parseBoolean(state),
				paginated);
		String userGuid = SecurityUtils.currentUserGuid();
		User user = userService.getUserByGuid(userGuid);
		model.addAttribute("user", user);
		model.addAttribute("paginated", paginated);
		return "company/company_NoVerifylist";
	}

	/**
	 * admin 和企业admin 管理后台企业 未审核noverify thinkpad dushaofei
	 * 
	 * @param paginated
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/company/NoVerify/verify/{guid}",
			"/enterpriseAdmin/company/NoVerify/verify/{guid}" })
	public String companyVerify(@PathVariable String guid, Model model) {
		Company com = companyService.findByGuid(guid);
		model.addAttribute("formDto", com);
		return "company/company_verify";
	}

	/**
	 * admin 和企业admin 管理后台企业 审核pass or no thinkpad dushaofei
	 * 
	 * @param paginated
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/company/NoVerify/pass/{guid}/{state}",
			"/enterpriseAdmin/company/NoVerify/pass/{guid}/{state}" })
	public String companyVerifyPass(@PathVariable @Valid String guid,
			@PathVariable @Valid String state, Model model) {
		Company com = companyService.findByGuid(guid);
		com.setVerifyState(true);
		com.setVerifyPass(null);
		com.setLevel("A");
		try {
			companyService.updateCompantByState(com);
		} catch (CreditNotEnoughException e) {
			model.addAttribute("alert", "faildCredit");
			e.printStackTrace();
			return "company/company_verify";
		} catch (Exception e) {
			model.addAttribute("alert", "faildMail");
			e.printStackTrace();
			return "company/company_verify";
		}
		model.addAttribute("formDto", com);
		User user = this.userService.getUserByGuid(com.getCreateUserUuid());
		if (user == null) {
			model.addAttribute("alert", "upOk");
		} else {
			model.addAttribute("successAlert", "Ok");
		}

		return "company/company_verify";
	}

	/**
	 * admin 和企业admin 管理后台企业列表 已审核 thinkpad dushaofei
	 * 
	 * @param paginated
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/company/passVerify/{state}",
			"/enterpriseAdmin/company/passVerify/{state}" })
	public String companyTureList(@PathVariable String state,
			CompanyPaginated paginated, Model model) {
		paginated = companyService.findCompanyList(Boolean.parseBoolean(state),
				paginated);
		String userGuid = SecurityUtils.currentUserGuid();
		User user = userService.getUserByGuid(userGuid);
		model.addAttribute("user", user);
		model.addAttribute("paginated", paginated);
		return "company/company_Verifylist";
	}

	/**
	 * admin 和企业admin 管理后台企业 已审核Passdetail thinkpad dushaofei
	 * 
	 * @param paginated
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/company/passVerify/detail/{guid}",
			"/enterpriseAdmin/company/passVerify/detail/{guid}" })
	public String companyDetail(@PathVariable String guid, Model model) {
		Company com = companyService.findByGuid(guid);
		model.addAttribute("formDto", com);
		return "company/company_detail";
	}

	/**
	 * admin 新增 企业管理员thinkpad dushaofei
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/company/create/{choose}",
			"/enterpriseAdmin/company/create/{choose}" })
	public String companyAdminCreate(Model model) {
		CompanyFormDto formDto = new CompanyFormDto();
		Industry[] indus = formDto.getIndustries();
		model.addAttribute("indus", indus);
		model.addAttribute("formDto", formDto);
		return "company/company_mainform";
	}

	/**
	 * admin 新增企业管理员 thinkpad dushaofei
	 * 
	 * @param formDto
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/company/create/{choose}",
			"/enterpriseAdmin/company/create/{choose}" }, method = RequestMethod.POST)
	public String submitOutAdminAdd(
			@ModelAttribute("formDto") @Valid CompanyFormDto formDto,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "company/company_mainform";
		}

		User user = userService.getUserByGuid(SecurityUtils.currentUserGuid());

		companyService.saveCompany(formDto);
		model.addAttribute("alert", "saveOK");

		if (user.isEnterpriseAdmin()) {
			return "redirect:/enterpriseAdmin/company/passVerify/true";
		}
		return "redirect:/admin/company/passVerify/true";

	}

	/**
	 * 删除已审核企业(包含邀请记录中已接受的企业)
	 * 
	 * @author lzw
	 * @param uuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/company/passVerify/delete/{uuid}", method = RequestMethod.GET)
	public String deletePassedCompany(@PathVariable("uuid") String uuid,
			Model model) {
		companyService.deletePassedCompany(uuid);
		return "redirect:/admin/company/passVerify/true";
	}

	@RequestMapping(value = "/public/is_exist_username", method = RequestMethod.GET)
	@ResponseBody
	public boolean isUsernameExist(CompanyFormDto formDto) {
		if (formDto != null) {
			Company company = companyService.findByUsername(formDto
					.getUsername());
			User user = userService.getUserByUsername(formDto.getUsername());
			if (user != null || company != null) {
				return true;
			}
		}

		return false;

	}

	@RequestMapping(value = "/public/is_exist_companyName", method = RequestMethod.GET)
	@ResponseBody
	public boolean isCompanyUsernameExist(CompanyFormDto formDto) {
		if (formDto != null) {
			Company company = companyService.findByCompanyName(formDto
					.getCompanyName());
			if (company != null) {// 并且审核通过
				if (company.getVerifyState() && company.getVerifyPass() != null
						&& !company.getVerifyPass()) {
					return false;
				}
				return true;
			}
		}
		return false;

	}

	@RequestMapping(value = "/public/is_exist_companyEmail", method = RequestMethod.GET)
	@ResponseBody
	public boolean isCompanyEmailExist(CompanyFormDto formDto) {
		if (formDto != null) {
			Company company = companyService.findByCompanyNEmail(formDto
					.getCompanyEmail());
			if (company != null) {// 并且审核通过
				if (company.getVerifyState() && company.getVerifyPass() != null
						&& !company.getVerifyPass()) {
					return false;
				}
				return true;
			}
		}
		return false;

	}

	@RequestMapping(value = { "/admin/is_exist_username",
			"/admin/company/is_exist_username" }, method = RequestMethod.GET)
	@ResponseBody
	public boolean isAdminUsernameExist(CompanyFormDto formDto) {
		if (formDto != null) {
			Company company = companyService.findByUsername(formDto
					.getUsername());
			User user = userService.getUserByUsername(formDto.getUsername());
			if (user != null || company != null) {
				return true;
			}
		}

		return false;

	}

	@RequestMapping(value = { "/admin/is_exist_companyName",
			"/admin/company/is_exist_companyName" }, method = RequestMethod.GET)
	@ResponseBody
	public boolean isAdminCompanyUsernameExist(CompanyFormDto formDto) {
		if (formDto != null) {
			Company company = companyService.findByCompanyName(formDto
					.getCompanyName());
			if (company != null) {
				if (company.getVerifyState() && company.getVerifyPass() != null
						&& !company.getVerifyPass()) {
					return false;
				}
				return true;
			}
		}
		return false;

	}

	@RequestMapping(value = { "/admin/is_exist_companyEmail",
			"/admin/company/is_exist_companyEmail" }, method = RequestMethod.GET)
	@ResponseBody
	public boolean isAdminCompanyEmailExist(CompanyFormDto formDto) {
		if (formDto != null) {
			Company company = companyService.findByCompanyNEmail(formDto
					.getCompanyEmail());
			if (company != null) {
				if (company.getVerifyState() && company.getVerifyPass() != null
						&& !company.getVerifyPass()) {
					return false;
				}
				return true;
			}
		}
		return false;

	}

	@RequestMapping(value = { "/enterpriseAdmin/is_exist_username",
			"/enterpriseAdmin/company/is_exist_username" }, method = RequestMethod.GET)
	@ResponseBody
	public boolean isEnterpriseAdminUsernameExist(CompanyFormDto formDto) {
		if (formDto != null) {
			Company company = companyService.findByUsername(formDto
					.getUsername());
			User user = userService.getUserByUsername(formDto.getUsername());
			if (user != null || company != null) {
				return true;
			}
		}

		return false;

	}

	@RequestMapping(value = { "/enterpriseAdmin/is_exist_companyName",
			"/enterpriseAdmin/company/is_exist_companyName" }, method = RequestMethod.GET)
	@ResponseBody
	public boolean isEnterpriseAdminCompanyUsernameExist(CompanyFormDto formDto) {
		if (formDto != null) {
			Company company = companyService.findByCompanyName(formDto
					.getCompanyName());
			if (company != null) {
				if (company.getVerifyState() && company.getVerifyPass() != null
						&& !company.getVerifyPass()) {
					return false;
				}
				return true;
			}
		}
		return false;

	}

	@RequestMapping(value = { "/enterpriseAdmin/is_exist_companyEmail",
			"/enterpriseAdmin/company/is_exist_companyEmail" }, method = RequestMethod.GET)
	@ResponseBody
	public boolean isEnterpriseAdminCompanyEmailExist(CompanyFormDto formDto) {
		if (formDto != null) {
			Company company = companyService.findByCompanyNEmail(formDto
					.getCompanyEmail());
			if (company != null) {

				if (company.getVerifyState() && company.getVerifyPass() != null
						&& !company.getVerifyPass()) {
					return false;
				}

				return true;
			}
		}
		return false;

	}

	/**
	 * 导出企业excel thinkpad dushaofei
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "/enterpriseAdmin/company/excel", "/admin/company/excel" })
	public String excelCompany(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		List<Company> companies = companyService
				.findCompanyPassVerifyList(true);
		try {
			ExcelUtils._importCompanyExcel(request, response, companies);
		} catch (Exception e) {
			throw new IllegalStateException("Write file to disk wrong"
					+ e.getMessage());
		}

		return null;
	}
}

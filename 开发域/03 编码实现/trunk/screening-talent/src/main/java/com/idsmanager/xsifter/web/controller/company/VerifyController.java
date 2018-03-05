package com.idsmanager.xsifter.web.controller.company;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idsmanager.commons.utils.PasswordHandler;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.VerifyReason;
import com.idsmanager.xsifter.domain.password.PasswordResetCode;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.business.admin.CreditNotEnoughException;
import com.idsmanager.xsifter.service.dto.company.CompanyFormDto;
import com.idsmanager.xsifter.service.dto.company.CompanyReSetFormDto;
import com.idsmanager.xsifter.service.dto.company.CompanyVerifyReasonFormDto;

/**
 * 
 * 类名称 企业审核不同过 创建人 dushaofei 创建时间：2016-2-23 上午10:11:57 类描述：
 * 
 * @version
 */
@Controller
public class VerifyController {

	@Autowired
	private CompanyService companyService;

	/**
	 * admin 和企业admin 管理后台企业 未审核Passdetail thinkpad dushaofei
	 * 
	 * @param paginated
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/company/NoVerify/reason/{guid}",
			"/enterpriseAdmin/company/NoVerify/reason/{guid}" }, method = RequestMethod.GET)
	public String companyReason(@PathVariable String guid, Model model) {
		CompanyVerifyReasonFormDto dto = companyService.findCompanyById(guid);
		model.addAttribute("re", dto.getVerifyReasons());
		model.addAttribute("formDto", dto);
		return "company/company_Noverifydetail";
	}

	/**
	 * 审核未通过 原因 thinkpad dushaofei
	 * 
	 * @param formDto
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/company/NoVerify/reason/{guid}",
			"/enterpriseAdmin/company/NoVerify/reason/{guid}" }, method = RequestMethod.POST)
	public String companyReasonSubmit(
			@ModelAttribute("formDto") @Valid CompanyVerifyReasonFormDto formDto,
			BindingResult result, @PathVariable String guid, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("re", VerifyReason.values());
			return "company/company_Noverifydetail";
		}
		formDto.setCompanyId(guid);
		companyService.updateCompantReason(formDto);
		return "redirect:../false";
	}

	/**
	 * 用户激活账号 thinkpad dushaofei
	 * 
	 * @param guid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/public/active/{guid}")
	public String companyVerifyPass(@PathVariable @Valid String guid,
			Model model) {
		if (StringUtils.isEmpty(guid)) {
			model.addAttribute("result", "fail");
			return "company/success";
		}
		Company com = companyService.findByGuid(guid);
		if (null != com) {
			if (com.getVerifyPass() != null && com.getVerifyPass()) {
				model.addAttribute("result", "exist");
				return "company/success";
			}
			com.setVerifyPass(true);
			companyService.updateCompantVerifyState(com);
			model.addAttribute("result", "activeSuccess");
			return "company/success";
		} else {
			model.addAttribute("result", "fail");
			return "company/success";
		}

	}

	/**
	 * 用户从新注册企业 thinkpad dushaofei
	 * 
	 * @param guid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/public/company/resetCreate/{guid}")
	public String companyResetCreate(@PathVariable @Valid String guid,
			Model model) {
		if (StringUtils.isEmpty(guid)) {
			model.addAttribute("result", "ReCreateComfail");
			return "company/success";
		}
		Company com = companyService.findByGuid(guid);
		if (null != com) {
			if (com.getVerifyPass() != null && com.getVerifyPass()) {
				model.addAttribute("result", "reCreateComPass");
				return "company/success";
			}
			CompanyReSetFormDto dto = new CompanyReSetFormDto(
					com.getUsername(), com.getPassword(), com.getCompanyName(),
					com.getCompanyEmail(), com.getContacts(),
					com.getContactsPhone(), com.getBusinessPath(),
					com.getAuthorityPath());
			dto.setCity(com.getCity());
			dto.setDist(com.getDist());
			dto.setGuid(com.getGuid());
			dto.setIndustry(com.getIndustry());
			dto.setOperator("I");
			dto.setProv(com.getProv());
			model.addAttribute("reCom", dto);
			return "company/company_reform";
		} else {
			model.addAttribute("result", "ReCreateComfail");
			return "company/success";
		}

	}

	/**
	 * 企业重新注册 thinkpad dushaofei
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/public/company/resetCreate/{guid}", method = RequestMethod.POST)
	public String submitOut(
			@ModelAttribute("reCom") @Valid CompanyReSetFormDto reCom,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("hasErrors", "Y");
			return "company/company_reform";
		}
		companyService.saveReSetCompany(reCom);
		model.addAttribute("alert", "saveOK");
		model.addAttribute("result", "reCreateComSuccess");
		return "company/success";
	}
}

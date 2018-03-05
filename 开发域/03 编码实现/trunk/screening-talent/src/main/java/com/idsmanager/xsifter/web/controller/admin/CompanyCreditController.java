package com.idsmanager.xsifter.web.controller.admin;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.SystemAdminService;
import com.idsmanager.xsifter.service.business.admin.CreditNotEnoughException;
import com.idsmanager.xsifter.service.dto.admin.CreditStreamFormDto;
import com.idsmanager.xsifter.service.dto.admin.CreditStreamPaginated;
import com.idsmanager.xsifter.service.dto.company.CompanyPaginated;

@Controller
@RequestMapping("/admin/credit/handwork/")
public class CompanyCreditController {

	@Autowired
	private SystemAdminService adminService;
	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "list")
	public String pageList(Model model, CompanyPaginated paginated) {
		paginated.setVerifyPass(true);
		paginated = companyService.findCompanyList(Boolean.TRUE, paginated);
		model.addAttribute("paginated", paginated);
		return "sysAdmin/companyCredit_list";
	}

	@RequestMapping(value = "credit/batch/create", method = {
			RequestMethod.POST, RequestMethod.GET })
	public String batchCreditCreate(HttpServletRequest request, Model model) {
		String[] ids = request.getParameterValues("companyCheckIds");
		if (ArrayUtils.isEmpty(ids)) {
			return "redirect:../../list";
		}
		model.addAttribute("formDto",
				new CreditStreamFormDto(Arrays.asList(ids)));
		return "sysAdmin/creditStream_batch_form";
	}

	@RequestMapping(value = "credit/batch/create/form", method = RequestMethod.POST)
	public String batchCreditCreate(
			@ModelAttribute("formDto") @Valid CreditStreamFormDto formDto,
			BindingResult result, Model model, HttpServletRequest request)
			throws CreditNotEnoughException, Exception {
		if (result.hasErrors()) {
			return "sysAdmin/creditStream_batch_form";
		}
		if (null != formDto.getCompanyUuids()
				&& formDto.getCompanyUuids().size() > 0) {
			adminService.addBatchCreditStream(formDto);
		}
		return "redirect:../../../list";
	}

	@RequestMapping(value = "credit/create/{companyUuid}", method = RequestMethod.GET)
	public String showEditForm(@PathVariable String companyUuid, Model model) {
		model.addAttribute("formDto", new CreditStreamFormDto(companyUuid));
		return "sysAdmin/creditStream_form";
	}

	@RequestMapping(value = "credit/create/{companyUuid}", method = RequestMethod.POST)
	public String creditCreate(
			@ModelAttribute("formDto") @Valid CreditStreamFormDto formDto,
			BindingResult result, Model model, @PathVariable String companyUuid)
			throws Exception {
		if (result.hasErrors()) {
			return "sysAdmin/creditStream_form";
		}
		adminService.addCreditStream(formDto);
		model.addAttribute("alert", "createOK");
		return "redirect:../../list";
	}

	@RequestMapping(value = "company/stream/{companyUUID}")
	public String companyCreditList(@PathVariable String companyUUID,
			CreditStreamPaginated paginated, Model model) {
		paginated = adminService.loadCreditStreamPaginated(paginated,
				companyUUID);
		model.addAttribute("paginated", paginated);
		return "sysAdmin/company_creditStream_list";
	}
}

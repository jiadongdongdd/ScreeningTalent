package com.idsmanager.xsifter.web.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.MemberExcepService;
import com.idsmanager.xsifter.service.dto.memberexcep.MemberExcepFormDto;
import com.idsmanager.xsifter.service.dto.memberexcep.MemberExcepPaginated;

@Controller
public class MemberExcepController {
	@Autowired
	private MemberExcepService memberExcepService;
	@Autowired
	private CompanyService companyService;

	@RequestMapping(value="/admin/memberExcep/list")
	public String list(
			@ModelAttribute("paginated") @Valid MemberExcepPaginated paginated,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "member/member_list";
		}
		paginated = memberExcepService.loadMemberExcepPaginated(paginated);
		model.addAttribute("paginated", paginated);
		return "enterpriseAdmin/member_list";
	}
	
	@RequestMapping(value="/admin/memberExcep/detail/{uuid}",method=RequestMethod.GET)
	public String detail(@PathVariable @Valid String uuid,Model model){
		MemberExcepFormDto dto = memberExcepService.findMemberExcepById(uuid);
		String comId = dto.getMemberExcep().getCompanyGuid();
		 Company company = companyService.findByGuid(comId);
		model.addAttribute("formDto", dto.getMemberExcep());
		model.addAttribute("company", company);
		return "enterpriseAdmin/member_detail";
	}
}

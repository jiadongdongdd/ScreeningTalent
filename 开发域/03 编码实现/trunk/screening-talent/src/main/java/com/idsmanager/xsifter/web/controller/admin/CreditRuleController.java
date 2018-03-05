package com.idsmanager.xsifter.web.controller.admin;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idsmanager.xsifter.service.SystemAdminService;
import com.idsmanager.xsifter.service.dto.admin.CreditRuleFormDto;




@Controller("creditRuleController")
@RequestMapping("/admin/credit/creditrule/")
public class CreditRuleController {
	@Autowired
	private SystemAdminService systemAdminService;
	
	@RequestMapping(value="list")
	public String listPage(Model model){
		List<CreditRuleFormDto> dtoList = systemAdminService.getAllCreditRuleFormDtos();
		model.addAttribute("list", dtoList);
		return "sysAdmin/creditRule_list";
	}
	
	@RequestMapping(value="form/create" ,method=RequestMethod.GET)
	public String showForm(Model model){
		model.addAttribute("formDto", new CreditRuleFormDto());
		return "sysAdmin/creditRule_form";
	}
	
	@RequestMapping(value="form/create",method=RequestMethod.POST)
	public String create(@ModelAttribute("formDto") @Valid CreditRuleFormDto formDto,BindingResult result,Model model){
		if(result.hasErrors()){
			return "sysAdmin/creditRule_form";
		}
		systemAdminService.saveCreditRule(formDto);
		return "redirect:../list";
	}
	
	@RequestMapping(value="form/edit/{uuid}",method=RequestMethod.GET)
	public String editPage(@PathVariable String uuid,Model model){
		model.addAttribute("formDto", systemAdminService.getCreditRuleFormDtoByUUID(uuid));
		return "sysAdmin/creditRule_form";
	}
	
	@RequestMapping(value="form/edit/{uuid}",method=RequestMethod.POST)
	public String edit(@ModelAttribute("formDto") @Valid CreditRuleFormDto formDto,BindingResult result,Model model){
		if(result.hasErrors()){
			return "sysAdmin/creditRule_form";
		}
		systemAdminService.saveCreditRule(formDto);
		return "redirect:../../list";
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(HttpServletRequest request){
		String[] ids = request.getParameterValues("deleteIds");
		if(null!=ids&&ids.length>0){
			systemAdminService.deleteCreditRuleByUUIDList(Arrays.asList(ids));
		}
		return "redirect:list";
	}
}

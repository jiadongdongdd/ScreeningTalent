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
import com.idsmanager.xsifter.service.dto.admin.MemberLeveLFormDto;
import com.idsmanager.xsifter.service.dto.admin.MemberLevelDto;


@Controller("memberLevelController")
@RequestMapping("/admin/credit/memberlevel/")
public class MemberLevelController {
	@Autowired
	private SystemAdminService systemAdminService;
	
	@RequestMapping("list")
	public String listPage(Model model){
		List<MemberLevelDto> dtoList = systemAdminService.getAllMemberLevelDto();
		model.addAttribute("list",dtoList );
		
		return "sysAdmin/memberLevel_list";
	}
	
	@RequestMapping(value="form/create",method=RequestMethod.GET)
	public String shorForm(Model model){
		model.addAttribute("formDto",new MemberLeveLFormDto());
		return "sysAdmin/memberLevel_form";
	}
	
	@RequestMapping(value="form/create",method=RequestMethod.POST)
	public String createMemberLevel(@ModelAttribute("formDto") @Valid MemberLeveLFormDto formDto,BindingResult result,Model model){
		if(result.hasErrors()){
			return "sysAdmin/memberLevel_form";
		}
		systemAdminService.saveMemberLevel(formDto);
		model.addAttribute("alert", "createOK");
		return "redirect:../list";
	}
	
	@RequestMapping(value="form/edit/{uuid}",method=RequestMethod.GET)
	public String showUpdateForm(@PathVariable String uuid,Model model){
		model.addAttribute("formDto", systemAdminService.getMemberLevelDtoByUUID(uuid));
		return "sysAdmin/memberLevel_form";
	}
	
	@RequestMapping(value="form/edit/{uuid}",method=RequestMethod.POST)
	public String editMemberLevel(@PathVariable String uuid,@ModelAttribute("formDto") @Valid MemberLeveLFormDto formDto,BindingResult result,Model model){
		if(result.hasErrors()){
			return "sysAdmin/memberLevel_form";
		}
		formDto.setUuid(uuid);
		systemAdminService.saveMemberLevel(formDto);
		model.addAttribute("alert", "createOK");
		return "redirect:../../list";
	}
	
	public String update(){
		return null;
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String deleteMemberLevels(HttpServletRequest request){
		String[] ids =request.getParameterValues("deleteIds");
		if(null!=ids&&ids.length>0){
			systemAdminService.deleteMemberLevelByUUIDList(Arrays.asList(ids));
		}
		return "redirect:list";
	}
}

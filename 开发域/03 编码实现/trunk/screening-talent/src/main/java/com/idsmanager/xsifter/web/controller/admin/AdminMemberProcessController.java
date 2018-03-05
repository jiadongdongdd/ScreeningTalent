package com.idsmanager.xsifter.web.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idsmanager.xsifter.domain.member.MemberProcess;
import com.idsmanager.xsifter.service.MemberService;
import com.idsmanager.xsifter.service.dto.menber.MemberPaginated;

@Controller
public class AdminMemberProcessController {
	@Autowired
	private MemberService memberService;

	@RequestMapping(value="enterpriseAdmin/member/list")
	public String list(
			@ModelAttribute("paginated") @Valid MemberPaginated paginated,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "member/member_list";
		}
		paginated.setMemberProcess(MemberProcess.DATA_REPEAT);
		paginated = memberService.loadMemberPaginated(paginated);
		model.addAttribute("paginated", paginated);
		return "enterpriseAdmin/member_list";
	}
}

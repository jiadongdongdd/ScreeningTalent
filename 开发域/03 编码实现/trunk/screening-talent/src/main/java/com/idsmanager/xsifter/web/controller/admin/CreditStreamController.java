package com.idsmanager.xsifter.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idsmanager.xsifter.service.SystemAdminService;
import com.idsmanager.xsifter.service.dto.admin.CreditStreamPaginated;

@Controller
@RequestMapping("/admin/credit/stream/")
public class CreditStreamController {
	@Autowired
	private SystemAdminService adminService;
	
	@RequestMapping(value="list")
	public String allCreditStreamList(Model model,CreditStreamPaginated paginated){
		paginated = adminService.loadAllCreditStreamPaginated(paginated);
		model.addAttribute("paginated", paginated);
		return "sysAdmin/all_creditStream_list";
	}
}

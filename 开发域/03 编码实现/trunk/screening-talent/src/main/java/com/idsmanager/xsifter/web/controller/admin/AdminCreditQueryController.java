package com.idsmanager.xsifter.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idsmanager.xsifter.service.WxOrderService;
import com.idsmanager.xsifter.service.dto.wx.order.WxOrderPaginated;

@Controller
@RequestMapping("/admin/credit/record/")
public class AdminCreditQueryController {

	@Autowired
	private WxOrderService wxOrderService;

	@RequestMapping(value = "query", method = RequestMethod.GET)
	public String query(WxOrderPaginated paginated, Model model) {
		paginated = wxOrderService.loadMyWxOrderList(paginated);
		model.addAttribute("paginated", paginated);
		return "sysAdmin/company_credit_query";
	}
}

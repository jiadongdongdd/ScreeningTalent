package com.idsmanager.xsifter.web.controller.enterprise;

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
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.CompanyService;
import com.idsmanager.xsifter.service.ProductService;
import com.idsmanager.xsifter.service.WxOrderService;
import com.idsmanager.xsifter.service.dto.company.CompanyCreditStreamPaginated;
import com.idsmanager.xsifter.service.dto.password.ModifyPasswordFormDto;
import com.idsmanager.xsifter.service.dto.wx.order.WxOrderPaginated;
import com.idsmanager.xsifter.service.dto.wx.pay.ProductPaginated;
import com.idsmanager.xsifter.service.dto.wx.pay.WxQrCodeDto;

@Controller
@RequestMapping("/enterprise/company/")
public class EnterpriseCompanyController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ProductService productService;

	@Autowired
	private WxOrderService wxOrderService;

	@RequestMapping("stream")
	public String companyStream(CompanyCreditStreamPaginated paginated,
			Model model) {

		paginated = companyService.loadMyCreditStream(paginated,
				SecurityUtils.currentUserGuid());

		model.addAttribute("paginated", paginated);

		return "enterprise/company_creditStream_list";

	}

	@RequestMapping(value = "modify_password", method = RequestMethod.GET)
	public String showForm(Model model) {
		ModifyPasswordFormDto formDto = new ModifyPasswordFormDto();
		model.addAttribute("formDto", formDto);
		return "password/modify_password";
	}

	@RequestMapping(value = "modify_password", method = RequestMethod.POST)
	public String submit(
			@ModelAttribute("formDto") @Valid ModifyPasswordFormDto formDto,
			BindingResult result, Model model) {

		formDto.setUuid(SecurityUtils.currentUserGuid());

		if (result.hasErrors()) {
			return "password/modify_password";
		}

		companyService.modifyPassword(formDto);

		return "redirect:../../login";

	}

	@RequestMapping(value = "creditbuy", method = RequestMethod.GET)
	public String buyCredit(ProductPaginated paginated, Model model) {
		paginated = productService.loadMyProduct(paginated);
		model.addAttribute("paginated", paginated);
		return "enterprise/product_list";

	}

	@RequestMapping(value = "buy/{uuid}", method = RequestMethod.GET)
	public String buy(@PathVariable("uuid") String uuid, Model model) {
		WxQrCodeDto dto = new WxQrCodeDto();
		model.addAttribute("formDto", dto);
		return "enterprise/product_qrcode";
	}

	@RequestMapping(value = "credit/query", method = RequestMethod.GET)
	public String creditQueryList(WxOrderPaginated paginated, Model model) {
		paginated = wxOrderService.loadMyWxOrderList(paginated);
		model.addAttribute("paginated", paginated);
		return "enterprise/company_credit_query";
	}
}

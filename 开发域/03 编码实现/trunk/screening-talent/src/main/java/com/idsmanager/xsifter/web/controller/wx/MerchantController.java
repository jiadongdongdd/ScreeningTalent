package com.idsmanager.xsifter.web.controller.wx;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idsmanager.xsifter.service.MerchantService;
import com.idsmanager.xsifter.service.dto.wx.pay.MerchantFormDto;
import com.idsmanager.xsifter.service.dto.wx.pay.MerchantPaginated;

//微信商户配置
@Controller
@RequestMapping("/admin/wx/merchant")
public class MerchantController {

	@Autowired
	private MerchantService merchantService;

	@RequestMapping("list")
	public String list(MerchantPaginated paginated, Model model) {
		paginated = merchantService.loadMyMerchant(paginated);
		model.addAttribute("paginated", paginated);
		return "wx/merchant_list";
	}

	@RequestMapping(value = "form/create", method = RequestMethod.GET)
	public String showCreateForm(Model model) {
		MerchantFormDto formDto = new MerchantFormDto();
		model.addAttribute("formDto", formDto);
		return "wx/merchant_form";
	}

	@RequestMapping(value = "form/create", method = RequestMethod.POST)
	public String create(
			@ModelAttribute("formDto") @Valid MerchantFormDto formDto,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "wx/merchant_form";
		}
		merchantService.createMerchant(formDto);
		return "redirect:../list";

	}

	@RequestMapping(value = "edit/{uuid}", method = RequestMethod.GET)
	public String showEditForm(@PathVariable("uuid") String uuid, Model model) {
		MerchantFormDto formDto = merchantService.loadMyMerchantByUUID(uuid);
		model.addAttribute("formDto", formDto);
		return "wx/merchant_form_update";
	}

	@RequestMapping(value = "edit/{uuid}", method = RequestMethod.POST)
	public String edit(
			@ModelAttribute("formDto") @Valid MerchantFormDto formDto,
			BindingResult result, Model model, @PathVariable("uuid") String uuid) {
		formDto.setUuid(uuid);

		if (result.hasErrors()) {
			return "wx/merchant_form_update";
		}

		merchantService.updateMerchant(formDto);

		return "redirect:../list";

	}

	@RequestMapping(value = "delete/{uuid}", method = RequestMethod.GET)
	public String delete(@PathVariable("uuid") String uuid, Model model) {
		merchantService.deleteMerchant(uuid);
		return "redirect:../list";
	}

}

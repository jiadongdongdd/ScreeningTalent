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

import com.idsmanager.xsifter.service.ProductService;
import com.idsmanager.xsifter.service.dto.wx.pay.ProductFormDto;
import com.idsmanager.xsifter.service.dto.wx.pay.ProductPaginated;

@Controller
@RequestMapping("/admin/product/")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping("list")
	public String list(ProductPaginated paginated, Model model) {
		paginated = productService.loadMyProduct(paginated);
		model.addAttribute("paginated", paginated);
		return "product/product_list";
	}

	@RequestMapping(value = "form/create", method = RequestMethod.GET)
	public String showCreateForm(Model model) {
		ProductFormDto formDto = new ProductFormDto();
		model.addAttribute("formDto", formDto);
		return "product/product_form";
	}

	@RequestMapping(value = "form/create", method = RequestMethod.POST)
	public String create(
			@ModelAttribute("formDto") @Valid ProductFormDto formDto,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "product/product_form";
		}

		productService.createProduct(formDto);

		return "redirect:../list";

	}

	@RequestMapping(value = "edit/{uuid}", method = RequestMethod.GET)
	public String showEditForm(@PathVariable("uuid") String uuid, Model model) {
		ProductFormDto formDto = productService.loadMyProductFormDto(uuid);
		model.addAttribute("formDto", formDto);
		return "product/product_form";

	}

	@RequestMapping(value = "edit/{uuid}", method = RequestMethod.POST)
	public String edit(
			@ModelAttribute("formDto") @Valid ProductFormDto formDto,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "product/product_form";
		}

		productService.updateProduct(formDto);

		return "redirect:../list";

	}

	@RequestMapping(value = "delete/{uuid}", method = RequestMethod.GET)
	public String delete(@PathVariable("uuid") String uuid, Model model) {
		productService.deleteProduct(uuid);
		return "redirect:../list";
	}

}

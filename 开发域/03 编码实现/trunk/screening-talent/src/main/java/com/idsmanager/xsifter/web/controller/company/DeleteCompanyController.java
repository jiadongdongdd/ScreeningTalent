package com.idsmanager.xsifter.web.controller.company;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idsmanager.xsifter.domain.company.DeleteCompany;
import com.idsmanager.xsifter.service.DeleteCompanyService;
import com.idsmanager.xsifter.service.dto.company.DeleteCompanyPaginated;

/**
 * 
 * 类名称 创建人 dushaofei 创建时间：2016-1-25 上午11:59:14 类描述：
 * 
 * @version
 */
@Controller
public class DeleteCompanyController {
	@Autowired
	private DeleteCompanyService deleteCompanyService;

	/**
	 * admin 和企业admin 管理后台企业列表 已审核 thinkpad dushaofei
	 * 
	 * @param paginated
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/company/deleteList",
			"/enterpriseAdmin/company/deleteList" })
	public String companyDeleteList(DeleteCompanyPaginated paginated,
			Model model) {
		paginated = deleteCompanyService.findCompanyList(paginated);
		model.addAttribute("paginated", paginated);
		return "company/deleteCompany_list";
	}

	@RequestMapping(value = { "/admin/company/detail/{guid}",
			"/enterpriseAdmin/company/detail/{guid}" })
	public String detail(@PathVariable @Valid String guid, Model model) {
		DeleteCompany deleteCompany = deleteCompanyService.findByGuid(guid);
		model.addAttribute("formDto", deleteCompany);
		return "company/deleteCompany_detail";
	}
}

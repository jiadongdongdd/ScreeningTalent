package com.idsmanager.xsifter.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idsmanager.xsifter.service.MemberPositionService;
import com.idsmanager.xsifter.service.PositionTypeService;
import com.idsmanager.xsifter.service.dto.position.PositionFormDto;
import com.idsmanager.xsifter.service.dto.position.PositionPaginated;
import com.idsmanager.xsifter.service.dto.position.PositionTypeFormDto;
import com.idsmanager.xsifter.service.dto.position.PositionTypePaginated;

@RequestMapping("/admin/position/")
@Controller
public class PositionController {

	@Autowired
	private PositionTypeService positionTypeService;

	@Autowired
	private MemberPositionService memberPositionService;

	@RequestMapping("list")
	public String positionList(PositionPaginated paginated, Model model) {

		paginated = memberPositionService.loadPositionPaginated(paginated);
		model.addAttribute("paginated", paginated);

		return "position/position_list";

	}

	@RequestMapping(value = "form/create", method = RequestMethod.GET)
	public String positionCreateForm(Model model) {

		PositionFormDto formDto = new PositionFormDto();
		model.addAttribute("formDto", formDto);

		return "position/position_form";

	}

	@RequestMapping(value = "form/create", method = RequestMethod.POST)
	public String positionCreate(
			@ModelAttribute("formDto") @Valid PositionFormDto formDto,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "position/position_form";
		}

		memberPositionService.createPosition(formDto);

		return "redirect:../list";

	}

	@RequestMapping(value = "form/edit_{uuid}", method = RequestMethod.GET)
	public String positionFormEdit(@PathVariable("uuid") String uuid,
			Model model) {

		PositionFormDto formDto = memberPositionService
				.loadPositionByUUID(uuid);
		model.addAttribute("formDto", formDto);

		return "position/position_form";

	}

	@RequestMapping(value = "form/edit_{uuid}", method = RequestMethod.POST)
	public String positionEdit(
			@ModelAttribute("formDto") @Valid PositionFormDto formDto,
			BindingResult result, @PathVariable("uuid") String uuid, Model model) {

		formDto.setUuid(uuid);
		if (result.hasErrors()) {
			return "position/position_form";
		}
		memberPositionService.updatePosition(formDto);

		return "redirect:../list";

	}

	@RequestMapping(value = "delete/{uuid}", method = RequestMethod.GET)
	public String positionDelete(@PathVariable("uuid") String uuid, Model model) {

		memberPositionService.deletePosition(uuid);

		return "redirect:../list";

	}

	@RequestMapping("type/list")
	public String typeList(PositionTypePaginated paginated, Model model) {

		paginated = positionTypeService.loadPositionTypePaginated(paginated);
		model.addAttribute("paginated", paginated);

		return "position/position_type_list";

	}

	@RequestMapping(value = "type/form/create", method = RequestMethod.GET)
	public String typeCreateForm(Model model) {
		PositionTypeFormDto formDto = new PositionTypeFormDto();
		model.addAttribute("formDto", formDto);
		return "position/position_type_form";
	}

	@RequestMapping(value = "type/form/create", method = RequestMethod.POST)
	public String typeCreate(
			@ModelAttribute("formDto") @Valid PositionTypeFormDto formDto,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "position/position_type_form";
		}

		positionTypeService.createPositionType(formDto);

		return "redirect:../list";

	}

	@RequestMapping(value = "type/form/edit/{uuid}", method = RequestMethod.GET)
	public String typeEditForm(@PathVariable("uuid") String uuid, Model model) {

		PositionTypeFormDto formDto = positionTypeService
				.loadMyPositionType(uuid);

		model.addAttribute("formDto", formDto);

		return "position/position_type_form";

	}

	@RequestMapping(value = "type/form/edit/{uuid}", method = RequestMethod.POST)
	public String typeEdit(
			@ModelAttribute("formDto") @Valid PositionTypeFormDto formDto,
			BindingResult result, Model model, @PathVariable("uuid") String uuid) {

		formDto.setUuid(uuid);

		if (result.hasErrors()) {
			return "position/position_type_form";
		}

		positionTypeService.editPositionType(formDto);

		return "redirect:../list";

	}

	@RequestMapping(value = "type/delete/{uuid}", method = RequestMethod.GET)
	public String typeDelete(@PathVariable("uuid") String uuid, Model model) {

		positionTypeService.deletePositionType(uuid);
		return "redirect:../list";
	}

}

/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.idsmanager.xsifter.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.MatchUtils;
import com.idsmanager.xsifter.domain.IntentionStatus;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.service.MemberPositionService;
import com.idsmanager.xsifter.service.MemberService;
import com.idsmanager.xsifter.service.dto.menber.MemberFormOneDto;
import com.idsmanager.xsifter.service.dto.menber.MemberFormThreeInterViewDto;
import com.idsmanager.xsifter.service.dto.menber.MemberFormTwoDto;
import com.idsmanager.xsifter.service.dto.menber.RecruitmentFormDto;
import com.idsmanager.xsifter.service.dto.menber.TurnoverFormDto;
import com.idsmanager.xsifter.service.dto.menber.WorkedFormDto;

/**
 * 2016/1/28
 * 
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/enterprise/member/")
public class MemberFormController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberPositionService memberPositionService;

	// 人员基本信息
	@RequestMapping(value = "form_1/{uuid}", method = RequestMethod.GET)
	public String showForm(@PathVariable("uuid") String uuid, Model model) {
		MemberFormOneDto formDto = memberService.loadMemberFormOneDto(uuid);
		model.addAttribute("formDto", formDto);
		return "member/member_one";
	}

	@RequestMapping(value = "form_1/{uuid}", method = RequestMethod.POST)
	public String submit(
			@ModelAttribute("formDto") @Valid MemberFormOneDto formDto,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "member/member_one";
		}
		String uuid = memberService.saveBasicMember(formDto);

		model.addAttribute("alert", "saveOK");
		if (formDto.isNext()) {
			return "redirect:../form_2/" + uuid;
		}
		return "redirect:../list";
	}

	// 教育背景
	@RequestMapping(value = "form_2/{uuid}", method = RequestMethod.GET)
	public String showTwoForm(@PathVariable("uuid") String uuid, Model model) {
		MemberFormTwoDto formDto = memberService.loadMemberFormTwoDto(uuid);
		model.addAttribute("formDto", formDto);
		return "member/member_two";
	}

	@RequestMapping(value = "form_2/{uuid}", method = RequestMethod.POST)
	public String submitTwo(
			@ModelAttribute("formDto") @Valid MemberFormTwoDto formDto,
			BindingResult result, @PathVariable("uuid") String uuid, Model model) {
		formDto.setUuid(uuid);
		if (result.hasErrors()) {
			return "member/member_two";
		}
		memberService.saveMemberEducation(formDto);

		model.addAttribute("alert", "saveOK");
		if (formDto.isNext()) {
			return "redirect:../form_3/" + uuid;
		}
		return "redirect:../form_1/" + uuid;
	}

	// 招聘环节
	@RequestMapping(value = "form_3/{uuid}", method = RequestMethod.GET)
	public String showThreeForm(@PathVariable("uuid") String uuid, Model model) {
		RecruitmentFormDto formDto = memberService.loaRecruitmentFormDto(uuid);
		model.addAttribute("formDto", formDto);
		return "member/member_three";
	}

	@RequestMapping(value = "form_3/{uuid}", method = RequestMethod.POST)
	public String submitThree(
			@ModelAttribute("formDto") @Valid RecruitmentFormDto formDto,
			BindingResult result, Model model, @PathVariable("uuid") String uuid) {
		List<MemberPosition> positionList = this.memberPositionService
				.findPositionList();
		formDto.setPositionList(positionList);
		if (result.hasErrors()) {
			return "member/member_three";
		}

		Turnover turnover = this.memberService.findTurnoverByUuid(uuid);
		if (turnover != null
				&& turnover.getTurnoverDate() != null
				&& (StringUtils.isEmpty(formDto.getEntryDate()) || IntentionStatus.DISABLED
						.equals(formDto.getEntrySuccess()))) {
			model.addAttribute("entryDateEmpty", true);
			return "member/member_three";
		}

		memberService.saveIntentionMember(formDto);

		model.addAttribute("alert", "saveOK");
		if (formDto.isNext()) {
			return "redirect:../form_4/" + uuid;
		}
		return "redirect:../form_2/" + uuid;
	}

	// 离职环节
	@RequestMapping(value = "form_4/{uuid}", method = RequestMethod.GET)
	public String showFourForm(@PathVariable("uuid") String uuid, Model model) {
		TurnoverFormDto formDto = memberService.loadTurnoverFormDto(uuid);
		model.addAttribute("formDto", formDto);
		return "member/member_four";
	}

	@RequestMapping(value = "form_4/{uuid}", method = RequestMethod.POST)
	public String submitFour(
			@ModelAttribute("formDto") @Valid TurnoverFormDto formDto,
			BindingResult result, Model model, @PathVariable("uuid") String uuid) {
		List<MemberPosition> positionList = this.memberPositionService
				.findPositionList();
		formDto.setPositionList(positionList);
		if (result.hasErrors()) {
			return "member/member_four";
		}
		memberService.saveTurnoverMember(formDto);

		Recruitment recruitment = this.memberService.findRecruitment(uuid);

		if (StringUtils.isNotEmpty(formDto.getTurnoverDate())) {
			if (recruitment == null
					|| recruitment.getEntryDate() == null
					|| IntentionStatus.DISABLED.equals(recruitment
							.getEntrySuccess())) {
				model.addAttribute("fillEntryDate", true);
				return "redirect:../form_3/" + uuid;
			}
		}

		model.addAttribute("alert", "saveOK");
		if (formDto.isNext()) {
			return "redirect:../form_5/" + uuid;
		}
		return "redirect:../form_3/" + uuid;
	}

	// 检查turnoverDate
	@RequestMapping(value = "form_4/isValid", method = RequestMethod.GET)
	@ResponseBody
	public boolean isValidTurnoverDate(TurnoverFormDto formDto) {
		Recruitment recruitment = this.memberService.findRecruitment(formDto
				.getMemberUuid());
		if (StringUtils.isNotEmpty(formDto.getTurnoverDate())
				&& MatchUtils.isDate(formDto.getTurnoverDate())) {
			if (recruitment != null
					&& recruitment.getTelIntention().isEnabled()
					&& recruitment.isJoinInterview()
					&& recruitment.getAgreeEntry().isEnabled()
					&& recruitment.getEntrySuccess().isEnabled()
					&& recruitment.getEntryDate() != null) {
				long entryTime = recruitment.getEntryDate().getTime();
				long turnTime = DateUtils.getDate(formDto.getTurnoverDate(),
						"yyyy-MM-dd").getTime();
				if (turnTime < entryTime) {
					return false;
				}

			}
		}
		return true;
	}

	// 工作环节
	@RequestMapping(value = "form_5/{uuid}", method = RequestMethod.GET)
	public String showFiveForm(@PathVariable("uuid") String uuid, Model model) {
		WorkedFormDto formDto = memberService.loadWorkedFormDto(uuid);
		model.addAttribute("formDto", formDto);
		return "member/member_five";
	}

	@RequestMapping(value = "form_5/{uuid}", method = RequestMethod.POST)
	public String submitFive(
			@ModelAttribute("formDto") @Valid WorkedFormDto formDto,
			BindingResult result, Model model, @PathVariable("uuid") String uuid) {
		if (result.hasErrors()) {
			return "member/member_five";
		}
		memberService.saveWorkedMember(formDto);

		model.addAttribute("alert", "saveOK");
		if (formDto.isFinished()) {
			return "redirect:../list";
		}
		return "redirect:../form_4/" + uuid;
	}

}

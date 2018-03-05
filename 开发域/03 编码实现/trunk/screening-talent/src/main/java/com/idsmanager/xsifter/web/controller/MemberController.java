package com.idsmanager.xsifter.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.service.LogService;
import com.idsmanager.xsifter.service.MemberService;
import com.idsmanager.xsifter.service.UserService;
import com.idsmanager.xsifter.service.dto.IdsFileDto;
import com.idsmanager.xsifter.service.dto.log.LogPaginated;
import com.idsmanager.xsifter.service.dto.menber.DetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.EntryRecordPaginated;
import com.idsmanager.xsifter.service.dto.menber.ImportExcelFormDto;
import com.idsmanager.xsifter.service.dto.menber.ImportExcelResultDto;
import com.idsmanager.xsifter.service.dto.menber.InterviewRecordPaginated;
import com.idsmanager.xsifter.service.dto.menber.MemberDetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.MyMemberPaginated;
import com.idsmanager.xsifter.service.dto.menber.QueryMemberDetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.QueryResultDto;
import com.idsmanager.xsifter.service.dto.menber.TelInvitationPaginated;

@Controller
@RequestMapping("/enterprise/member/")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private LogService logService;

	@Autowired
	private UserService userService;

	@RequestMapping("list")
	public String myList(MyMemberPaginated paginated, Model model) {
		paginated = memberService.loadMyMemberPaginated(paginated);
		model.addAttribute("paginated", paginated);
		User user = userService.getUserByGuid(SecurityUtils.currentUserGuid());
		if (user != null) {
			model.addAttribute("userRole", "admin");
		}
		return "member/member_list";
	}

	@RequestMapping(value = "form/import_excel", method = RequestMethod.POST)
	public String showImportPage(
			@ModelAttribute("formDto") @Valid ImportExcelFormDto formDto,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "member/import_form";
		}

		ImportExcelResultDto resultDto = memberService.importExcel(formDto);
		model.addAttribute("resultDto", resultDto);
		if (!resultDto.isResult()) {
			return "member/import_form";
		}
		return "redirect:../list";

	}

	@RequestMapping(value = "form/import_excel", method = RequestMethod.GET)
	public String showImportForm(Model model) {
		ImportExcelFormDto formDto = new ImportExcelFormDto();
		model.addAttribute("formDto", formDto);
		return "member/import_form";

	}

	@RequestMapping(value = "exportModel", method = RequestMethod.GET)
	public void exportModel(HttpServletResponse response) throws IOException {
		IdsFileDto fileDto = memberService.exportModel();

		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename="
				+ new String(fileDto.getName().getBytes(), "iso8859-1")
				+ ".xls");

		final ServletOutputStream out = response.getOutputStream();
		out.write(fileDto.getData());
		out.flush();

	}

	// 全站查询列表
	@RequestMapping("query_list")
	public String queryList(MyMemberPaginated paginated, Model model,
			HttpServletRequest request) {

		Object object = request.getSession().getAttribute("isSwitch");
		boolean isSwitch = false;
		if (null != object) {
			isSwitch = (boolean) object;
		}

		paginated = memberService.loadQueryMemberPaginated(paginated, isSwitch);

		User user = userService.getUserByGuid(SecurityUtils.currentUserGuid());

		// 搜索到结果，扣除积分,返回下次积分是否需要充值 true(不需要) false(需要)
		boolean enough = memberService.queryMemberHadle(paginated, isSwitch);
		model.addAttribute("enough", enough);
		model.addAttribute("paginated", paginated);

		if (user == null && !isSwitch) {
		} else {
			model.addAttribute("userRole", "admin");
		}

		return "member/query_member_list";
	}

	@RequestMapping(value = "query_list/getData", method = RequestMethod.GET)
	@ResponseBody
	public QueryResultDto queryData(MyMemberPaginated paginated) {

		QueryResultDto dto = memberService.validateFiled(paginated);

		return dto;

	}

	/**
	 * basic information detail
	 * 
	 * @param uuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail/{uuid}", method = RequestMethod.GET)
	public String showDetail(@PathVariable("uuid") String uuid, Model model) {
		MemberDetailFormDto formDto = memberService
				.loadMemberDetialFormDto(uuid);
		model.addAttribute("formDto", formDto);
		return "member/member_detail";
	}

	@RequestMapping(value = "wide_detail_{uuid}", method = RequestMethod.GET)
	public String showWideDetail(TelInvitationPaginated telPaginated,
			InterviewRecordPaginated interviewRecordPaginated,
			EntryRecordPaginated entryRecordPaginated,
			@PathVariable("uuid") String uuid, Model model) {
		QueryMemberDetailFormDto formDto = memberService
				.loadWideMemberDetailFormDto(uuid, telPaginated,
						interviewRecordPaginated, entryRecordPaginated);
		model.addAttribute("formDto", formDto);
		return "member/member_query_detail";
	}

	@RequestMapping(value = "delete/{uuid}", method = RequestMethod.GET)
	public String delete(@PathVariable("uuid") String uuid, Model model) {
		memberService.deleteMyMember(uuid);
		return "redirect:../list";

	}

	@RequestMapping(value = "log/{uuid}", method = RequestMethod.GET)
	public String showLogForm(LogPaginated paginated,
			@PathVariable("uuid") String uuid, Model model) {
		paginated = logService.loadMyMemberOperateLogs(paginated, uuid);
		model.addAttribute("paginated", paginated);
		return "member/log_list";

	}

	@RequestMapping(value = "tel_detail_{uuid}", method = RequestMethod.GET)
	public String telDetail(@PathVariable("uuid") String uuid, Model model) {
		DetailFormDto formDto = memberService.loadPartInfo("tel", uuid);
		model.addAttribute("formDto", formDto);
		return "member/member_part_detail";
	}

	@RequestMapping(value = "interview_detail_{uuid}", method = RequestMethod.GET)
	public String interviewQueryHistoryDetail(
			@PathVariable("uuid") String uuid, Model model) {
		DetailFormDto formDto = memberService.loadPartInfo("interview", uuid);
		model.addAttribute("formDto", formDto);
		return "member/member_part_detail";
	}

	@RequestMapping(value = "entry_detail_{uuid}", method = RequestMethod.GET)
	public String entryQueryHistoryDetail(@PathVariable("uuid") String uuid,
			Model model) {
		DetailFormDto formDto = memberService.loadPartInfo("entry", uuid);
		model.addAttribute("formDto", formDto);
		return "member/member_part_detail";
	}

	@RequestMapping(value = "query/tel_detail_{uuid}", method = RequestMethod.GET)
	public String telQueryHistoryDetail(@PathVariable("uuid") String uuid,
			Model model) {
		DetailFormDto formDto = memberService.loadPartInfo("tel", uuid);
		model.addAttribute("formDto", formDto);
		return "member/member_history_part_detail";
	}

	@RequestMapping(value = "query/interview_detail_{uuid}", method = RequestMethod.GET)
	public String interviewDetail(@PathVariable("uuid") String uuid, Model model) {
		DetailFormDto formDto = memberService.loadPartInfo("interview", uuid);
		model.addAttribute("formDto", formDto);
		return "member/member_history_part_detail";
	}

	@RequestMapping(value = "query/entry_detail_{uuid}", method = RequestMethod.GET)
	public String entryDetail(@PathVariable("uuid") String uuid, Model model) {
		DetailFormDto formDto = memberService.loadPartInfo("entry", uuid);
		model.addAttribute("formDto", formDto);
		return "member/member_history_part_detail";
	}

	// 搜索历史
	@RequestMapping("query/history")
	public String queryHistory(LogPaginated paginated, Model model) {
		paginated = logService.loadMyQueryHistoryPaginated(paginated);
		model.addAttribute("paginated", paginated);
		return "member/query_history";

	}

	@RequestMapping(value = "query/wide_detail_{uuid}", method = RequestMethod.GET)
	public String showQueryHistoryDetail(TelInvitationPaginated telPaginated,
			InterviewRecordPaginated interviewRecordPaginated,
			EntryRecordPaginated entryRecordPaginated,
			@PathVariable("uuid") String uuid, Model model) {
		QueryMemberDetailFormDto formDto = memberService
				.loadWideMemberDetailFormDto(uuid, telPaginated,
						interviewRecordPaginated, entryRecordPaginated);
		model.addAttribute("formDto", formDto);
		return "member/member_query_history_detail";
	}

}

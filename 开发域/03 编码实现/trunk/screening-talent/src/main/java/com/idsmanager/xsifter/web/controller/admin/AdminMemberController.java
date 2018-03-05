package com.idsmanager.xsifter.web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idsmanager.xsifter.domain.member.MemberProcess;
import com.idsmanager.xsifter.service.LogService;
import com.idsmanager.xsifter.service.MemberService;
import com.idsmanager.xsifter.service.dto.IdsFileDto;
import com.idsmanager.xsifter.service.dto.log.LogPaginated;
import com.idsmanager.xsifter.service.dto.menber.ImportExcelFormDto;
import com.idsmanager.xsifter.service.dto.menber.ImportExcelResultDto;
import com.idsmanager.xsifter.service.dto.menber.MemberDetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.MemberFormDto;
import com.idsmanager.xsifter.service.dto.menber.MemberPaginated;
import com.idsmanager.xsifter.service.dto.menber.MyMemberPaginated;

@Controller
@RequestMapping("/admin/member/")
public class AdminMemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private LogService logService;

	@RequestMapping("list")
	public String list(MemberPaginated paginated, Model model) {
		paginated.setMemberProcess(MemberProcess.NORMAL);
		paginated = memberService.loadAdminMemberPaginated(paginated);
		model.addAttribute("paginated", paginated);
		return "sysAdmin/admin_member_list";
	}

	@RequestMapping(value = "form/import_excel", method = RequestMethod.POST)
	public String showImportPage(MemberPaginated paginated,
			@ModelAttribute("formDto") @Valid ImportExcelFormDto formDto,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "sysAdmin/import_form";
		}

		ImportExcelResultDto resultDto = memberService.importExcel(formDto);
		model.addAttribute("resultDto", resultDto);
		if (!resultDto.isResult()) {
			return "sysAdmin/import_form";
		}
		return "redirect:../list";

	}

	@RequestMapping(value = "form/import_excel", method = RequestMethod.GET)
	public String showImportForm(Model model) {
		ImportExcelFormDto formDto = new ImportExcelFormDto();
		model.addAttribute("formDto", formDto);
		return "sysAdmin/import_form";

	}

	@RequestMapping(value = "exportModel", method = RequestMethod.GET)
	public void exportModel(HttpServletResponse response) throws IOException {
		IdsFileDto fileDto = memberService.exportModel();

		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename="
				+ new String(fileDto.getName().getBytes(), "UTF-8")
				+ ".xls");

		final ServletOutputStream out = response.getOutputStream();
		out.write(fileDto.getData());
		out.flush();

	}

	@RequestMapping(value = "detail/{uuid}", method = RequestMethod.GET)
	public String showDetail(@PathVariable("uuid") String uuid, Model model) {
		MemberDetailFormDto formDto = memberService
				.loadMemberDetialFormDto(uuid);
		model.addAttribute("formDto", formDto);
		return "sysAdmin/admin_member_detail";
	}

	@RequestMapping(value = "log/{uuid}", method = RequestMethod.GET)
	public String showLogList(LogPaginated paginated,
			@PathVariable("uuid") String uuid, Model model) {
		paginated = logService.loadAdminMemberOperateLogs(paginated, uuid);
		model.addAttribute("paginated", paginated);
		return "sysAdmin/log_list";

	}

	@RequestMapping(value = "delete/{uuid}", method = RequestMethod.GET)
	public String delete(@PathVariable("uuid") String uuid, Model model) {
		memberService.deleteMember(uuid);
		return "redirect:../list";

	}

}

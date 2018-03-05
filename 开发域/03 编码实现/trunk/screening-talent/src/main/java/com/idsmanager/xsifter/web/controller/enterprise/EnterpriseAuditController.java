package com.idsmanager.xsifter.web.controller.enterprise;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.LogService;
import com.idsmanager.xsifter.service.dto.log.LogPaginated;

@Controller
@RequestMapping("/enterprise/audit/")
public class EnterpriseAuditController {

	@Autowired
	private LogService logService;

	@RequestMapping("list")
	public String list(LogPaginated paginated, Model model) {
		paginated = logService.loadMyLogs(paginated);
		model.addAttribute("paginated", paginated);
		return "enterprise/log_list";
	}

	@RequestMapping(value = "excel/export/{operator}/{operateDetail}", method = RequestMethod.GET)
	public void excelAuditExport(@PathVariable("operator") String operator,
			@PathVariable("operateDetail") String operateDetail,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		map.put("operator", operator);
		map.put("operateDetail", operateDetail);
		map.put("userUuid", SecurityUtils.currentUserGuid());
		logService.exportAuditExcel(map, request, response);

	}

}

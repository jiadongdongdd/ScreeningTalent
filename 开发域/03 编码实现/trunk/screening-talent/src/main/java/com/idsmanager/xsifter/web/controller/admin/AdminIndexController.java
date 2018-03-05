package com.idsmanager.xsifter.web.controller.admin;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idsmanager.xsifter.service.LogService;

@Controller
@RequestMapping("/admin/index/")
public class AdminIndexController {

	@Autowired
	private LogService logService;

	@RequestMapping(value = "visit/query", method = RequestMethod.GET)
	@ResponseBody
	public List<JSONObject> visitQuery() {
		List<JSONObject> list = logService.queryVisitTimesByMonthly();
		return list;
	}

}

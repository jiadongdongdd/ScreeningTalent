package com.idsmanager.xsifter.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idsmanager.xsifter.domain.log.Log;
import com.idsmanager.xsifter.service.dto.log.LogPaginated;

import net.sf.json.JSONObject;

public interface LogService {

	List<JSONObject> queryVisitTimesByMonthly();

	List<JSONObject> queryEnterpriseVisitTimesByMonthly();

	LogPaginated loadMyMemberOperateLogs(LogPaginated paginated,String uuid);

	LogPaginated loadAdminMemberOperateLogs(LogPaginated paginated, String uuid);

	LogPaginated loadMyLogs(LogPaginated paginated);

	LogPaginated loadAdminLogs(LogPaginated paginated);

	LogPaginated loadMyQueryHistoryPaginated(LogPaginated paginated);

	void exportAuditExcel(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);

	void saveLog(Log log);

}

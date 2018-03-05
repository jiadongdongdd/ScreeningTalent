package com.idsmanager.xsifter.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.xsifter.domain.log.Log;
import com.idsmanager.xsifter.domain.log.LogRepository;
import com.idsmanager.xsifter.service.LogService;
import com.idsmanager.xsifter.service.business.enterprise.EnterpriseVisitTimeLoader;
import com.idsmanager.xsifter.service.business.log.MyAuditExcelExportor;
import com.idsmanager.xsifter.service.business.log.VisitTimesLoader;
import com.idsmanager.xsifter.service.dto.log.LogPaginated;

@Service("logService")
public class LogServiceImpl implements LogService {

	@Autowired
	private LogRepository logRepository;

	@Override
	public List<JSONObject> queryVisitTimesByMonthly() {
		VisitTimesLoader loader = new VisitTimesLoader();
		return loader.load();
	}

	@Override
	public List<JSONObject> queryEnterpriseVisitTimesByMonthly() {
		EnterpriseVisitTimeLoader loader = new EnterpriseVisitTimeLoader();
		return loader.load();
	}

	@Override
	public LogPaginated loadMyMemberOperateLogs(LogPaginated paginated,
			final String uuid) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<Log>() {

			@Override
			public long loadTotalSize() {
				return logRepository.totalMemberLogs(map, uuid);
			}

			@Override
			public List<Log> loadList() {
				return logRepository.findMemberLogs(map, uuid);
			}
		});
	}

	@Override
	public LogPaginated loadAdminMemberOperateLogs(LogPaginated paginated,
			final String uuid) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<Log>() {

			@Override
			public List<Log> loadList() {
				return logRepository.findAdminMemberLogs(map, uuid);
			}

			@Override
			public long loadTotalSize() {
				return logRepository.totalAdminMemberLogs(map, uuid);
			}
		});
	}

	@Override
	public LogPaginated loadMyLogs(LogPaginated paginated) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<Log>() {

			@Override
			public List<Log> loadList() {
				return logRepository.findEnterpriseLogs(map);
			}

			@Override
			public long loadTotalSize() {
				return logRepository.totalEnterpriseLogs(map);
			}
		});
	}

	@Override
	public LogPaginated loadAdminLogs(LogPaginated paginated) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<Log>() {

			@Override
			public List<Log> loadList() {
				return logRepository.findAdminLogs(map);
			}

			@Override
			public long loadTotalSize() {
				return logRepository.totalAdminLogs(map);
			}
		});
	}

	@Override
	public LogPaginated loadMyQueryHistoryPaginated(LogPaginated paginated) {
		final Map<String, Object> queryMap = paginated.queryMap();
		return paginated.load(new PaginatedLoader<Log>() {

			@Override
			public List<Log> loadList() {
				return logRepository.findQueryHistoryList(queryMap);
			}

			@Override
			public long loadTotalSize() {
				return logRepository.totalQueryHistoryList(queryMap);
			}
		});
	}

	@Override
	public void exportAuditExcel(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) {

		MyAuditExcelExportor exportor = new MyAuditExcelExportor(map);
		exportor.export(request, response);

	}

	@Override
	public void saveLog(Log log) {
		this.logRepository.saveLog(log);

	}

}

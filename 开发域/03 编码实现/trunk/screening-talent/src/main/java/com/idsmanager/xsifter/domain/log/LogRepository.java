package com.idsmanager.xsifter.domain.log;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.idsmanager.xsifter.domain.Repository;

public interface LogRepository extends Repository {

	void saveLog(Log log);

	long getVisitSumByMonthly(Date date);

	long getEnterpriseVisitSumByMonthly(String currentUserGuid, Date date);

	List<Log> findMemberLogs(Map<String, Object> map,String uuid);

	long totalMemberLogs(Map<String, Object> map,String uuid);

	List<Log> findAdminMemberLogs(Map<String, Object> map, String uuid);

	long totalAdminMemberLogs(Map<String, Object> map, String uuid);

	List<Log> findEnterpriseLogs(Map<String, Object> map);

	long totalEnterpriseLogs(Map<String, Object> map);

	List<Log> findAdminLogs(Map<String, Object> map);

	long totalAdminLogs(Map<String, Object> map);

	long findMyTotalStationQuery();

	List<Log> findQueryHistoryList(Map<String, Object> queryMap);

	long totalQueryHistoryList(Map<String, Object> queryMap);

	List<Log> findExportLogList(Map<String, Object> map);

}

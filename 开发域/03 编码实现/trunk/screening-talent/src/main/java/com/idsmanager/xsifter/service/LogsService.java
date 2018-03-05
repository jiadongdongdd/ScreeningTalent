package com.idsmanager.xsifter.service;

import com.idsmanager.xsifter.domain.log.Logs;


public interface LogsService {

	void logsHandle(String username, String ip);

	Logs findLogsByUsername(String username);

	void logsHandleUpdate(String name, String ip);

}

package com.idsmanager.xsifter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idsmanager.xsifter.domain.log.Logs;
import com.idsmanager.xsifter.domain.log.LogsRepository;
import com.idsmanager.xsifter.service.LogsService;
import com.idsmanager.xsifter.service.business.logs.MyLogsHandler;

@Service("logsService")
public class LogsServiceImpl implements LogsService {

	@Autowired
	private LogsRepository logsRepository;

	@Override
	public void logsHandle(String username, String ip) {
		MyLogsHandler handler = new MyLogsHandler(username, ip);
		handler.handleCreate();
	}

	@Override
	public Logs findLogsByUsername(String username) {
		return logsRepository.findLogsByUsername(username);
	}

	@Override
	public void logsHandleUpdate(String name, String ip) {
		MyLogsHandler handler = new MyLogsHandler(name, ip);
		handler.handleUpdate();
	}

}

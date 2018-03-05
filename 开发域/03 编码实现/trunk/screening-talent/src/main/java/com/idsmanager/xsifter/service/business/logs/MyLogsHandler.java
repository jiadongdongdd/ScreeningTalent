package com.idsmanager.xsifter.service.business.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.log.Logs;
import com.idsmanager.xsifter.domain.log.LogsRepository;

public class MyLogsHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyLogsHandler.class);

	private transient LogsRepository logsRepository = BeanProvider
			.getBean(LogsRepository.class);

	private String username;
	private String ip;

	public MyLogsHandler(String username, String ip) {
		super();
		this.username = username;
		this.ip = ip;
	}

	public void handleCreate() {

		Logs logs = this.logsRepository.findLogsByUsername(username);
		if (logs == null) {
			logs = createLogs();
			this.logsRepository.saveLogs(logs);
		}
	}
	
	

	private Logs updateLogs(Logs logs) {
		return logs.setUsername(username).setIp(ip).setDate(DateUtils.now());
	}

	private Logs createLogs() {
		return new Logs().setUsername(username)
				.setIp(ip)
				.setDate(DateUtils.now());
	}

	public void handleUpdate() {
		Logs logs = this.logsRepository.findLogsByUsername(username);
		if(logs!=null) {
			updateLogs(logs);
			this.logsRepository.saveLogs(logs);
		}
	}

}

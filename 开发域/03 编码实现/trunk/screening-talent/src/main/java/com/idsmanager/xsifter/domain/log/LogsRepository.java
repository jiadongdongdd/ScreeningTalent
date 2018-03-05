package com.idsmanager.xsifter.domain.log;

import com.idsmanager.xsifter.domain.Repository;

public interface LogsRepository extends Repository {

	Logs findLogsByUsername(String username);

	void saveLogs(Logs logs);

}

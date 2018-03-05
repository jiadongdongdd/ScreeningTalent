package com.idsmanager.xsifter.domain.login;

import java.util.List;

import com.idsmanager.xsifter.domain.Repository;

public interface LoginRepository extends Repository {

	LoginRecord findLoginRecordByUserUUID(String userUUID);

	void saveLoginRecord(LoginRecord loginRecord);

	List<LoginRecord> findActiveUsers();

	long totalActiveUsers();

	long findTotalStationQueryTimes();

}

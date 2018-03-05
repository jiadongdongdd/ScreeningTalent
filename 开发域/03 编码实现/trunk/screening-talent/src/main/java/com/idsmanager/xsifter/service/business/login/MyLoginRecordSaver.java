package com.idsmanager.xsifter.service.business.login;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.log.Log;
import com.idsmanager.xsifter.domain.log.LogRepository;
import com.idsmanager.xsifter.domain.log.OperateType;
import com.idsmanager.xsifter.domain.login.LoginRecord;
import com.idsmanager.xsifter.domain.login.LoginRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.business.admin.CreditStreamCreater;

public class MyLoginRecordSaver {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyLoginRecordSaver.class);

	private transient LoginRepository loginRepository = BeanProvider
			.getBean(LoginRepository.class);

	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private transient LogRepository logRepository = BeanProvider
			.getBean(LogRepository.class);

	private String userUUID;

	public MyLoginRecordSaver(String userUUID) {
		super();
		this.userUUID = userUUID;
	}

	public void save() {

		Log log = createLog();
		this.logRepository.saveLog(log);

		LoginRecord loginRecord = this.loginRepository
				.findLoginRecordByUserUUID(userUUID);

		Company company = this.companyRepository.findByGuid(userUUID);

		if (loginRecord == null) {
			createLoginRecord(userUUID);
			// 增加积分
			if (company != null) {
				plus(SecurityUtils.username(), userUUID);
			}
		} else {
			if (company != null) {
				loginHandle(loginRecord);
			}
			changeTodayLoginCount(loginRecord);
			updateLoginRecord(loginRecord);
		}

	}

	private Log createLog() {
		return new Log().setOperatorId(SecurityUtils.currentUserGuid())
				.setOperator(SecurityUtils.username())
				.setOperateType(OperateType.LOGIN_SUCCESS)
				.setOperateDetail(OperateType.LOGIN_SUCCESS.getLabel())
				.setEntityName(SecurityUtils.username())
				.setEntityUuid(SecurityUtils.currentUserGuid());
	}

	private void changeTodayLoginCount(LoginRecord loginRecord) {
		Date date = loginRecord.getLastLoginTime();
		String loginDate = DateUtils.toDateText(date, "yyyy-MM-dd");
		String nowDate = DateUtils.toDateText(DateUtils.now(), "yyyy-MM-dd");
		if (!loginDate.equals(nowDate)) {
			loginRecord.setTodayLoginCount(1);
		} else {
			loginRecord
					.setTodayLoginCount(loginRecord.getTodayLoginCount() + 1);
		}
	}

	public void createLoginRecord(String userUUID) {
		LoginRecord loginRecord = getLoginRecordForCreate();
		this.loginRepository.saveLoginRecord(loginRecord);
		LOG.debug("{}|Create LoginRecord{}", SecurityUtils.username(),
				loginRecord);

	}

	public void updateLoginRecord(LoginRecord loginRecord) {
		LoginRecord recordForUpdate = getLoginRecordForUpdate(loginRecord);
		try {
			this.loginRepository.saveLoginRecord(recordForUpdate);
		} catch (Exception e) {
			throw new IllegalStateException("数据正在被其他人编辑");
		}
		LOG.debug("{}|Update LoginRecord{}", SecurityUtils.username(),
				loginRecord);
	}

	public LoginRecord getLoginRecordForCreate() {
		return new LoginRecord().setUserUUID(userUUID)
				.setUsername(SecurityUtils.username()).setLoginCount(1)
				.setTodayLoginCount(1);
	}

	public LoginRecord getLoginRecordForUpdate(LoginRecord loginRecord) {

		return loginRecord.setLastLoginTime(DateUtils.now()).setLoginCount(
				loginRecord.getLoginCount() + 1);

	}

	public void loginHandle(LoginRecord loginRecord) {

		Date date = loginRecord.getLastLoginTime();
		String loginDate = DateUtils.toDateText(date, "yyyy-MM-dd");
		String nowDate = DateUtils.toDateText(DateUtils.now(), "yyyy-MM-dd");
		if (!loginDate.equals(nowDate)) {
			plus(SecurityUtils.username(), SecurityUtils.currentUserGuid());
		}

	}

	public void plus(String username, String uuid) {
		CreditStreamCreater streamCreater = new CreditStreamCreater(
				new CreditStreamHolder(uuid, "签到", CreditAction.SIGN));
		try {
			streamCreater.create();
		} catch (Exception e) {
			LOG.debug("{}|Sign in plus integration failure", username);
			throw new IllegalStateException(e);
		}
	}

}

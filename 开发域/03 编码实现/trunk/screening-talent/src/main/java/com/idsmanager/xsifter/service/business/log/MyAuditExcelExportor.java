package com.idsmanager.xsifter.service.business.log;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.excel.AuditExcelUtils;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.log.Log;
import com.idsmanager.xsifter.domain.log.LogRepository;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;

public class MyAuditExcelExportor {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyAuditExcelExportor.class);

	private transient LogRepository logRepository = BeanProvider
			.getBean(LogRepository.class);

	private transient UserRepository userRepository = BeanProvider
			.getBean(UserRepository.class);

	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private Map<String, Object> map;

	public MyAuditExcelExportor(Map<String, Object> map) {
		super();
		this.map = map;
	}

	public void export(HttpServletRequest request, HttpServletResponse response) {
		String uuid = (String) map.get("userUuid");
		User user = this.userRepository.findByGuid(uuid);
		Company company = this.companyRepository.findByGuid(uuid);
		if (user == null && company == null) {
			throw new IllegalStateException(
					"Your username  is invalid,we will not support your operation");
		}
		if (user != null) {
			map.put("userUuid", null);
		}

		List<Log> list = this.logRepository.findExportLogList(map);

		try {
			AuditExcelUtils.exportAudit(request, response, list);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

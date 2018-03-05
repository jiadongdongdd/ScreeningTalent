package com.idsmanager.xsifter.service.business.enterprise;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.log.LogRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

public class EnterpriseVisitTimeLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(EnterpriseVisitTimeLoader.class);

	private static final String SUM_USER = "a";
	private static final String DATE_DAY = "y";

	private transient LogRepository logRepository = BeanProvider
			.getBean(LogRepository.class);

	public EnterpriseVisitTimeLoader() {
		super();
	}

	public List<JSONObject> load() {
		List<JSONObject> list = new ArrayList<>();
		getData(list);
		return list;

	}

	private void getData(List<JSONObject> list) {
		for (int i = 1; i <= 12; i++) {
			JSONObject json = new JSONObject();
			String year = DateUtils.toDateText(DateUtils.now(), "yyyy");
			String dateStr = "";
			if (i < 10) {
				dateStr = year + "-0" + i;
			} else {
				dateStr = year + "-" + i;
			}
			long sum = logRepository.getEnterpriseVisitSumByMonthly(
					SecurityUtils.currentUserGuid(),
					DateUtils.getDate(dateStr, "yyyy-MM"));
			json.accumulate(DATE_DAY, dateStr);
			json.accumulate(SUM_USER, sum);
			list.add(json);
		}
	}

}

package com.idsmanager.xsifter.service.business.log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.log.LogRepository;

public class VisitTimesLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(VisitTimesLoader.class);

	private LogRepository logRepository = BeanProvider
			.getBean(LogRepository.class);

	private static final String SUM_USER = "a";
	private static final String DATE_DAY = "y";

	public VisitTimesLoader() {
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
			long sum = logRepository.getVisitSumByMonthly(DateUtils.getDate(
					dateStr, "yyyy-MM"));
			json.accumulate(DATE_DAY, dateStr);
			json.accumulate(SUM_USER, sum);
			list.add(json);
		}
	}

}

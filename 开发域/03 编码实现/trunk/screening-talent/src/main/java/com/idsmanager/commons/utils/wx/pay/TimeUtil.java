package com.idsmanager.commons.utils.wx.pay;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.idsmanager.commons.utils.DateUtils;

public class TimeUtil {

	/**
	 * 交易起始时间
	 * 
	 * @return
	 */
	public static String getTimeStart() {
		return DateUtils.toDateText(DateUtils.now(), "yyyyMMddHHmmss");
	}

	/**
	 * 交易结束时间
	 * 
	 * @param timeStart
	 * @param time
	 *            (单位为分钟)
	 * @return
	 */
	public static String getTimeExpire(String timeStart, int time) {
		return DateUtils.toDateText(
				new Date(DateUtils.getDate(timeStart, "yyyyMMddHHmmss")
						.getTime() + time * 60 * 1000), "yyyyMMddHHmmss");
	}

	public static Map<String, String> getTimeMap(int time) {
		Map<String, String> map = new HashMap<String, String>();

		Date now = DateUtils.now();
		String start = DateUtils.toDateText(now, "yyyyMMddHHmmss");
		map.put("start", start);
		
		String expire = DateUtils.toDateText(new Date(now.getTime() + time * 60
				* 1000), "yyyyMMddHHmmss");
		map.put("expire", expire);

		return map;

	}

}

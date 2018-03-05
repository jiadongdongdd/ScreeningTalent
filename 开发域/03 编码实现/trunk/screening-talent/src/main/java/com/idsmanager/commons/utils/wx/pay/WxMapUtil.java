package com.idsmanager.commons.utils.wx.pay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WxMapUtil {

	/**
	 * Map key 排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, String> orderMap(Map<String, String> map) {

		HashMap<String, String> tempMap = new LinkedHashMap<String, String>();
		List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
				map.entrySet());

		Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
			public int compare(Map.Entry<String, String> o1,
					Map.Entry<String, String> o2) {
				return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		for (int i = 0; i < infoIds.size(); i++) {
			Map.Entry<String, String> item = infoIds.get(i);
			tempMap.put(item.getKey(), item.getValue());
		}
		return tempMap;

	}

	/**
	 * url 参数串连
	 * 
	 * @param map
	 * @param keyLower
	 * @param valueUrlencode
	 * @return
	 */
	public static String mapJoin(Map<String, String> map, boolean keyLower,
			boolean valueUrlencode) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : map.keySet()) {
			if (map.get(key) != null && !"".equals(map.get(key))) {
				try {
					String temp = (key.endsWith("_") && key.length() > 1) ? key
							.substring(0, key.length() - 1) : key;
					stringBuilder
							.append(keyLower ? temp.toLowerCase() : temp)
							.append("=")
							.append(valueUrlencode ? URLEncoder.encode(
									map.get(key), "utf-8").replace("+", "%20")
									: map.get(key)).append("&");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		if (stringBuilder.length() > 0) {
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}
		return stringBuilder.toString();
	}

}

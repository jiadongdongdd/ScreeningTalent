package com.idsmanager.commons.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.util.StringUtils;

/**
 * @author Shengzhao Li
 */
public abstract class PinyinUtils {

	// 不包括音标
	public static String chineseToPinyin(String chinese) {
		if (StringUtils.isEmpty(chinese)) {
			return chinese;
		}
		final char[] chars = chinese.toCharArray();

		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		StringBuilder sb = new StringBuilder();
		for (char c : chars) {
			if (c > 128) {
				final String[] strings;
				try {
					strings = PinyinHelper.toHanyuPinyinStringArray(c,
							outputFormat);
					if (strings != null) {
						sb.append(strings[0]);
					} else {
						sb.append(c);
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					throw new IllegalStateException(e);
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 职工姓名转换成拼音用于排序
	 * 
	 * @param chinese
	 * @return
	 */
	public static String memberNameToPinyin(String chinese) {

		if (StringUtils.isEmpty(chinese)) {
			return chinese;
		}
		final char[] chars = chinese.toCharArray();

		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < chars.length; i++) {
			if (chars[i] > 128) {
				final String[] strings;
				try {
					strings = PinyinHelper.toHanyuPinyinStringArray(chars[i],
							outputFormat);

					if (i == 0) {
						if (strings != null) {
							sb.append(strings[0] + " ");
						} else {
							sb.append(chars[i] + " ");
						}
					} else {
						if (strings != null) {
							sb.append(strings[0]);
						} else {
							sb.append(chars[i]);
						}
					}

				} catch (BadHanyuPinyinOutputFormatCombination e) {
					throw new IllegalStateException(e);
				}
			} else {
				sb.append(chars[i]);
			}
		}

		return sb.toString();

	}

}
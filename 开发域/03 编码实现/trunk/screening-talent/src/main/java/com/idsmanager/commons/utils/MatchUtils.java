package com.idsmanager.commons.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author Shengzhao Li
 */
public abstract class MatchUtils {

	/**
	 * BigDecimal regex. 大于0的小数或整数
	 */
	public static final String BIG_DECIMAL_REGEX = "^(\\d+)||(\\d+\\.\\d+)$";

	/**
	 * Positive Number regex. 正整数或0
	 */
	public static final String POSITIVE_NUMBER_REGEX = "^\\d+$";

	public static final String NOT_0_NUMBER = "^-?\\d+$";

	/**
	 * Email regex.
	 */
	public static final String EMAIL_REGEX = ".+@.+\\.[a-z]+";

	// Date pattern, demo: 2013-09-11
	public static final String DATE_PATTERN = "^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}$";
	
	// Date pattern, demo: 2013.09.11
	public static final String DATE_PATTERN_TWO = "^[0-9]{4}\\.[0-9]{1,2}\\.[0-9]{1,2}$";

	// ChName regex.
	public static final String CHNAME_REGEX = "^[\u4E00-\u9FA5]{2,5}$";

	public static final String ID_NUMBER_15_REGEX = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";

	public static final String ID_NUMBER_18_REGEX = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";

	public static final String MOBILE_REGEX = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9]))\\d{8}|[0]{1}[0-9]{2,3}-[0-9]{7,8}$";

	public static boolean isBigDecimal(String text) {
		return StringUtils.isNotEmpty(text) && text.matches(BIG_DECIMAL_REGEX);
	}

	public static boolean isEmail(String email) {
		return StringUtils.isNotEmpty(email) && email.matches(EMAIL_REGEX);
	}

	public static boolean isPositiveNumber(String numberText) {
		return StringUtils.isNotEmpty(numberText)
				&& numberText.matches(POSITIVE_NUMBER_REGEX);
	}

	public static boolean isNotZERO(String numberText) {
		return StringUtils.isNotEmpty(numberText)
				&& numberText.matches(NOT_0_NUMBER);
	}

	public static boolean isDate(String dateAsText) {
		return StringUtils.isNotEmpty(dateAsText)
				&& dateAsText.matches(DATE_PATTERN);
	}
	
	public static boolean isDateTwo(String dateAsText) {
		return StringUtils.isNotEmpty(dateAsText)
				&& dateAsText.matches(DATE_PATTERN_TWO);
	}

	public static boolean isChName(String chNameText) {
		return StringUtils.isNotEmpty(chNameText)
				&& chNameText.matches(CHNAME_REGEX);
	}

	public static boolean isIdNumber(String idNumberText) {
		return StringUtils.isNotEmpty(idNumberText)
				&& (idNumberText.matches(ID_NUMBER_15_REGEX) || idNumberText
						.matches(ID_NUMBER_18_REGEX));
	}

	public static boolean isMobile(String mobileText) {
		return StringUtils.isNotEmpty(mobileText)
				&& mobileText.matches(MOBILE_REGEX);
	}

}
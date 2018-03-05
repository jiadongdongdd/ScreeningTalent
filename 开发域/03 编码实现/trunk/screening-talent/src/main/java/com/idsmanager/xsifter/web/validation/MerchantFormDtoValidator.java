package com.idsmanager.xsifter.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.idsmanager.xsifter.service.dto.wx.pay.MerchantFormDto;

@Component
public class MerchantFormDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MerchantFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MerchantFormDto formDto = (MerchantFormDto) target;
		validateAppId(formDto, errors);
		validateMchId(formDto, errors);
		validateMchKey(formDto, errors);
		validateIp(formDto, errors);
		validatePort(formDto,errors);

	}

	private void validatePort(MerchantFormDto formDto, Errors errors) {
		final String port = formDto.getPort();
		if(StringUtils.isEmpty(port)) {
			errors.rejectValue("port", null, "端口号不能为空");
			return;
		}
		
		if(StringUtils.isNotEmpty(port)) {
			String regex = "^[1-9][0-9]+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(port);
			if(!matcher.matches()) {
				errors.rejectValue("port", null, "端口号格式不正确");
			}
		}
	}

	private void validateIp(MerchantFormDto formDto, Errors errors) {
		final String ip = formDto.getIp();
		if (StringUtils.isEmpty(ip)) {
			errors.rejectValue("ip", null, "ip不能为空");
			return;
		}

		boolean b = isIp(ip);

		if (!b) {
			errors.rejectValue("ip", null, "ip地址格式不正确");
			return;
		}

	}

	public boolean isIp(String ip) {
		String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num
				+ "$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(ip);
		return m.matches();

	}

	private void validateMchKey(MerchantFormDto formDto, Errors errors) {
		final String mchKey = formDto.getMchKey();
		if (StringUtils.isEmpty(mchKey)) {
			errors.rejectValue("mchKey", null, "商户Key不能为空");
			return;
		}
	}

	private void validateMchId(MerchantFormDto formDto, Errors errors) {
		final String mchId = formDto.getMchId();
		if (StringUtils.isEmpty(mchId)) {
			errors.rejectValue("mchId", null, "商户号不能为空");
			return;
		}
	}

	private void validateAppId(MerchantFormDto formDto, Errors errors) {
		final String appId = formDto.getAppId();
		if (StringUtils.isEmpty(appId)) {
			errors.rejectValue("appId", null, "公众号ID不能为空");
			return;
		}
	}

}

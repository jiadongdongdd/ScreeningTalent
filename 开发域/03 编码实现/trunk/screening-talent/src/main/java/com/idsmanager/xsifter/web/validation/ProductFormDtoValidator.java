package com.idsmanager.xsifter.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.idsmanager.xsifter.service.dto.wx.pay.ProductFormDto;

@Component
public class ProductFormDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductFormDto formDto = (ProductFormDto) target;
		validateProductName(formDto, errors);
		validateProductBody(formDto, errors);
		validateProductDetail(formDto, errors);
		validateProductPrice(formDto, errors);
		validateCredit(formDto, errors);
	}

	private void validateCredit(ProductFormDto formDto, Errors errors) {
		final String credit = formDto.getCredit();
		if (StringUtils.isEmpty(credit)) {
			errors.rejectValue("credit", null, "积分不能为空");
			return;
		}

		if (StringUtils.isNotEmpty(credit)) {
			String reg = "^[1-9]\\d*|0$";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(credit);
			if (!matcher.matches()) {
				errors.rejectValue("credit", null, "积分格式不正确");
			}
		}

	}

	private void validateProductPrice(ProductFormDto formDto, Errors errors) {
		final String productPrice = formDto.getProductPrice();
		if (StringUtils.isEmpty(productPrice)) {
			errors.rejectValue("productPrice", null, "价格不能为空");
			return;
		}

		if (StringUtils.isNotEmpty(productPrice)) {
			String regex = "^[1-9]\\d*|[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(productPrice);
			if (!matcher.matches()) {
				errors.rejectValue("productPrice", null, "价格格式不正确");
			}
		}

	}
	

	private void validateProductDetail(ProductFormDto formDto, Errors errors) {
		final String productDetail = formDto.getProductDetail();
		if (StringUtils.isEmpty(productDetail)) {
			errors.rejectValue("productDetail", null, "详细描述不能为空");
			return;
		}

	}

	private void validateProductBody(ProductFormDto formDto, Errors errors) {
		final String productBody = formDto.getProductBody();
		if (StringUtils.isEmpty(productBody)) {
			errors.rejectValue("productBody", null, "简要描述不能为空");
			return;
		}

	}

	private void validateProductName(ProductFormDto formDto, Errors errors) {
		final String productName = formDto.getProductName();
		if (StringUtils.isEmpty(productName)) {
			errors.rejectValue("productName", null, "商品名不能为空");
			return;
		}

	}

}

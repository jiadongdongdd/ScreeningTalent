package com.idsmanager.xsifter.web.validation;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.service.dto.user.UserFormDto;

/**
 * 
 * 类名称 validate user 创建人 dushaofei 创建时间：2016-1-28 下午3:37:12 类描述：
 * 
 * @version
 */
@Component
public class UserFormDtoValidator extends AbstractUserValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserFormDto formDto = (UserFormDto) target;

		validateUsername(formDto.getUsername(), errors);
		validatePassword(formDto.getPassword(), formDto.getRePassword(), errors);

		validatePrivileges(formDto, errors);
	}

	private void validatePrivileges(UserFormDto formDto, Errors errors) {
		final Set<Privilege> privileges = formDto.getPrivileges();
		if (privileges == null || privileges.isEmpty()) {
			errors.rejectValue("privileges", null, "审核企业权限是必需的");
		}
	}
}

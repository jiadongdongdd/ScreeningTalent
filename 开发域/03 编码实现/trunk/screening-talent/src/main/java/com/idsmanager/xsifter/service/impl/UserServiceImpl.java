package com.idsmanager.xsifter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.log.Log;
import com.idsmanager.xsifter.domain.log.LogRepository;
import com.idsmanager.xsifter.domain.log.OperateType;
import com.idsmanager.xsifter.domain.security.IdsUserDetails;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.UserService;
import com.idsmanager.xsifter.service.business.admin.MyAdminPasswordModifier;
import com.idsmanager.xsifter.service.business.user.CreateUserHandler;
import com.idsmanager.xsifter.service.business.user.DefaultUserInitializer;
import com.idsmanager.xsifter.service.business.user.MySettingDtoUpdater;
import com.idsmanager.xsifter.service.dto.password.ModifyAdminPasswordFormDto;
import com.idsmanager.xsifter.service.dto.user.MySettingDto;
import com.idsmanager.xsifter.service.dto.user.UserFormDto;
import com.idsmanager.xsifter.service.dto.user.UserPaginated;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userDao;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private LogRepository logRepository;

	@Override
	public boolean isExistedUsername(String username) {
		final User user = userDao.findByUsername(username);
		return user != null;
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public UserPaginated loadUserPaginated(UserPaginated paginated) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<User>() {
			@Override
			public List<User> loadList() {
				return userDao.findUserPaginated(map);
			}

			@Override
			public long loadTotalSize() {
				return userDao.totalUserPaginated(map);
			}
		});
	}

	@Override
	public String createUser(UserFormDto formDto) {
		CreateUserHandler handler = new CreateUserHandler(formDto);
		return handler.handle();
	}

	@Override
	public void archiveUserByGuid(String guid) {
		final User user = userDao.findByGuid(guid);
		LOG.debug("{}|Delete User: {}", SecurityUtils.username(), user);
		userDao.removeUser(user);
	}

	@Override
	public void updateMySetting(MySettingDto formDto) {
		MySettingDtoUpdater updater = new MySettingDtoUpdater(formDto);
		updater.update();
	}

	@Override
	public User getUserByGuid(String guid) {
		return userDao.findByGuid(guid);
	}

	@Override
	public String initialDefaultUser() {
		DefaultUserInitializer initializer = new DefaultUserInitializer();
		return initializer.initial();
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DisabledException {
		Company company = companyRepository
				.findLoginCompanyByUsername(username);
		if (company != null && company.getVerifyPass().equals(true)
				&& company.getVerifyState().equals(true)) {
			return new IdsUserDetails(company);
		}

		if (company != null
				&& (!company.getVerifyPass().equals(true) || !company
						.getVerifyState().equals(true))) {
			throw new DisabledException("Company is disabled:" + company);
		}

		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Not found by username: "
					+ username);
		}
		return new IdsUserDetails(user);
	}

	@Override
	public void modifyPassword(ModifyAdminPasswordFormDto formDto) {

		MyAdminPasswordModifier modifier = new MyAdminPasswordModifier(formDto);

		modifier.modify();

	}

	@Override
	public Boolean updateUserState(String username) {
		User user = userDao.findByUsername(username);
		if (null == user)
			throw new UsernameNotFoundException("Not found by username: "
					+ username);

		userDao.updateUserState(user);
		updateUserStateLog(user);
		return true;
	}

	private void updateUserStateLog(User user) {

		Log log = new Log()
				.setEntity(user)
				.setEntityClass(user.getClass().toString())
				.setEntityName(user.getUsername())
				.setEntityUuid(user.getGuid())
				.setOperateDetail(
						OperateType.CANCELLATION_ENTERPRISE.getLabel())
				.setOperateType(OperateType.CANCELLATION_ENTERPRISE)
				.setOperator(SecurityUtils.username())
				.setOperatorId(SecurityUtils.currentUserGuid());
		this.logRepository.saveLog(log);

	}

	@Override
	public Boolean updateUserStateOpen(String username) {
		User user = userDao.findByUsernameNoState(username);
		if (null == user)
			return false;

		userDao.updateUserStateOpen(user);
		updateUserStateOpenLog(user);
		return true;
	}

	private void updateUserStateOpenLog(User user) {
		Log log = new Log().setEntity(user)
				.setEntityClass(user.getClass().toString())
				.setEntityName(user.getUsername())
				.setEntityUuid(user.getGuid())
				.setOperateDetail(OperateType.REDUCTION_ENTERPRISE.getLabel())
				.setOperateType(OperateType.REDUCTION_ENTERPRISE)
				.setOperator(SecurityUtils.username())
				.setOperatorId(SecurityUtils.currentUserGuid());
		this.logRepository.saveLog(log);
	}
}

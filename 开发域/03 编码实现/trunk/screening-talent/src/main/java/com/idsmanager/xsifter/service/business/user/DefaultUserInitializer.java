package com.idsmanager.xsifter.service.business.user;

import java.util.ArrayList;
import java.util.List;

import com.idsmanager.commons.utils.PasswordHandler;
import com.idsmanager.commons.utils.PropertyUtils;
import com.idsmanager.commons.web.context.BeanProvider;

import com.idsmanager.xsifter.domain.admin.CreditRule;
import com.idsmanager.xsifter.domain.admin.MemberLevel;
import com.idsmanager.xsifter.domain.admin.SystemAdminRepository;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.domain.wx.pay.Merchant;
import com.idsmanager.xsifter.domain.wx.pay.MerchantRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2015/12/21
 * <p/>
 * <p/>
 * When the system firstly startup, checking and initialed default-user.
 * 
 * @author Shengzhao Li
 */
public class DefaultUserInitializer {

	private static final Logger LOG = LoggerFactory
			.getLogger(DefaultUserInitializer.class);

	private transient UserRepository userDao = BeanProvider
			.getBean(UserRepository.class);
	// init system memberLevel
	private transient SystemAdminRepository systemAdminRepository = BeanProvider
			.getBean(SystemAdminRepository.class);

	private transient MerchantRepository merchantRepository = BeanProvider
			.getBean(MerchantRepository.class);

	public DefaultUserInitializer() {
	}

	public String initial() {

		long count = userDao.countUsers();
		if (count > 0) {
			return "Already initialed, Ignore";
		}

		String username = "admin";
		String password = PasswordHandler.encryptPassword("admin", username);
		User user = new User(username, password);
		user.getPrivileges().add(Privilege.ADMIN);

		userDao.saveUser(user);
		LOG.info("SPG initialed default-user: {}", user);

		// init meberLevel
		List<MemberLevel> addList = initMemberLevel();
		for (MemberLevel memberLevel : addList) {
			systemAdminRepository.saveMemberLevel(memberLevel);
		}
		// init creditRule
		List<CreditRule> creditRules = initCreditRule();
		for (CreditRule creditRule : creditRules) {
			systemAdminRepository.saveCreditRule(creditRule);
		}
		// init merchant
		Merchant merchant = initMerchant();
		this.merchantRepository.saveMerchant(merchant);

		return user.username();
	}

	public static void main(String[] args) {
		String[] levelStr = { "A", "B", "C", "D", "E" };
		System.out.println(levelStr[1]);
	}

	public List<MemberLevel> initMemberLevel() {
		List<MemberLevel> addList = new ArrayList<>();
		String level = PropertyUtils.getInitProp("level");
		int levelLen = Integer.parseInt(level);
		String[] levelStr = { "A", "B", "C", "D", "E" };
		for (int i = 1; i <= levelLen; i++) {
			String reward = PropertyUtils.getInitProp("rewardCoefficient");
			MemberLevel level1 = new MemberLevel()
					.setLevel(levelStr[i - 1])
					.setMaxNumber(
							Integer.parseInt(PropertyUtils
									.getInitProp("maxNumber" + i)))
					.setMixNumber(
							Integer.parseInt(PropertyUtils
									.getInitProp("mixNumber" + i)))
					.setRewardCoefficient(reward + "." + i)
					.setRemarks(PropertyUtils.getInitProp("remarks"));
			addList.add(level1);
		}
		return addList;
	}

	public List<CreditRule> initCreditRule() {
		String ruleName = PropertyUtils.getInitProp("ruleName");
		String[] rule_action = ruleName.split(",");
		List<CreditRule> creditRules = new ArrayList<CreditRule>();
		for (int i = 0; i < rule_action.length; i++) {
			String name = rule_action[i];
			CreditRule creditRule;
			if (("查询职工信息").equals(name) || ("删除职工信息").equals(name)
					|| ("下载职工信息").equals(name)) {
				creditRule = new CreditRule()
						.setRuleName(name)
						.setAction(name)
						.setCreditNumber(
								Integer.parseInt("-"
										+ PropertyUtils
												.getInitProp("creditNumber")))
						.setRemarks(PropertyUtils.getInitProp("remarks"));

			} else {

				creditRule = new CreditRule()
						.setRuleName(name)
						.setAction(name)
						.setCreditNumber(
								Integer.parseInt(PropertyUtils
										.getInitProp("creditNumber")))
						.setRemarks(PropertyUtils.getInitProp("remarks"));
			}
			creditRules.add(creditRule);
		}
		return creditRules;

	}

	public Merchant initMerchant() {

		return new Merchant().setAppId(PropertyUtils.getInitProp("appId"))
				.setIp(PropertyUtils.getInitProp("ip"))
				.setMchId(PropertyUtils.getInitProp("mchId"))
				.setMchKey(PropertyUtils.getInitProp("mchKey"))
				.setPort(PropertyUtils.getInitProp("port"));

	}
}

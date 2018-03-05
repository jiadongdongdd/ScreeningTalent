package com.idsmanager.xsifter.service.business.admin.credit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.admin.CreditRule;
import com.idsmanager.xsifter.domain.admin.SystemAdminRepository;

public class CreditRuleInitiator {

	private static final Logger LOG = LoggerFactory
			.getLogger(CreditRuleInitiator.class);

	private transient SystemAdminRepository repository = BeanProvider
			.getBean(SystemAdminRepository.class);

	public CreditRuleInitiator() {
		super();
	}

	public void init() {

		// 签到
		createRule("签到", "签到", 1);

		// 查询职工
		createRule("查询职工", "查询职工信息", -10);

		// 添加职工
		createRule("添加职工", "添加职工信息", 10);

		// 邀请注册
		createRule("邀请注册", "邀请注册", 20);
		
		

	}

	private void createRule(String ruleName, String action, int creditNumber) {
		CreditRule rule1 = this.repository.findByRuleName(ruleName);
		deleteRule(rule1);
		CreditRule rule2 = this.repository.findCreditRuleByAction(action);
		deleteRule(rule2);

		CreditRule rule = new CreditRule().setAction(action)
				.setCreditNumber(creditNumber).setRuleName(ruleName)
				.setRemarks(ruleName);
		this.repository.saveCreditRule(rule);

	}

	private void deleteRule(CreditRule rule) {
		if (rule != null) {
			this.repository.deleteCreditRule(rule);
		}

	}

}

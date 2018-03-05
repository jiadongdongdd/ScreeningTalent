package com.idsmanager.xsifter.service.business.admin.credit;

import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.CreditRule;

public class DeleteMemberCreditStreamHandler extends
		AbstractCreditStreamHandler {

	@Override
	public boolean support(CreditAction action) {
		return action.getAction().equals(CreditAction.DELETE.getAction());
	}

	@Override
	protected Integer getCreditNumber() throws CreditRuleNotFoundException {
		CreditRule creditRule = systemAdminRepository
				.findCreditRuleByAction(holder.getAction().getAction());
		if (null == creditRule) {
			throw new CreditRuleNotFoundException(holder.getAction()
					.getAction() + "未定义此积分规则");
		}
		return creditRule.getCreditNumber();
	}

}

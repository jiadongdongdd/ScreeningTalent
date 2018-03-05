package com.idsmanager.xsifter.service.business.admin.credit;

import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.CreditRule;

public class MemberSignCreditStreamHandler extends AbstractCreditStreamHandler{

	@Override
	public boolean support(CreditAction action) {
		// TODO Auto-generated method stub
		return action.getAction().equals(CreditAction.SIGN.getAction());
	}

	@Override
	protected Integer getCreditNumber() throws CreditRuleNotFoundException {
		CreditRule rule = systemAdminRepository.findCreditRuleByAction(holder.getAction().getAction());
		if(null==rule){
			throw new CreditRuleNotFoundException(holder.getAction().getAction()+"未定义此积分规则");
		}
		return rule.getCreditNumber();
	}

}

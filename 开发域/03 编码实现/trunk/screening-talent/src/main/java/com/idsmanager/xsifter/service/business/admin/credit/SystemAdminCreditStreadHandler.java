package com.idsmanager.xsifter.service.business.admin.credit;

import com.idsmanager.xsifter.domain.admin.CreditAction;

public class SystemAdminCreditStreadHandler extends AbstractCreditStreamHandler {

	@Override
	public boolean support(CreditAction action) {
		// TODO Auto-generated method stub
		return action.getAction().equals(CreditAction.SYSTEM.getAction());
	}

	@Override
	protected Integer getCreditNumber() {
		// TODO Auto-generated method stub
		return Integer.valueOf(this.holder.getAdminCreditDo());
	}

}

package com.idsmanager.xsifter.service.business.admin.credit;

import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;

public interface CreditStreamHandler {
	
	boolean support(CreditAction action);
	
	void handle(CreditStreamHolder holder) throws Exception;
	
	boolean checkCreditEnough(CreditStreamHolder holder) throws CreditRuleNotFoundException;
}

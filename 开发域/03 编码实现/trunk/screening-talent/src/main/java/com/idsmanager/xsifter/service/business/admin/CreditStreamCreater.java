package com.idsmanager.xsifter.service.business.admin;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.business.admin.credit.CreditRuleNotFoundException;
import com.idsmanager.xsifter.service.business.admin.credit.DeleteMemberCreditStreamHandler;
import com.idsmanager.xsifter.service.business.admin.credit.DownloaderCreditStreamHandler;
import com.idsmanager.xsifter.service.business.admin.credit.InviteSignUpCreditStreamHandler;
import com.idsmanager.xsifter.service.business.admin.credit.MemberSignCreditStreamHandler;
import com.idsmanager.xsifter.service.business.admin.credit.AddMemberCreditStreamHandler;
import com.idsmanager.xsifter.service.business.admin.credit.CreditStreamHandler;
import com.idsmanager.xsifter.service.business.admin.credit.SearchMemberCreditStreamHandler;
import com.idsmanager.xsifter.service.business.admin.credit.SuggestionCreditStreamHandler;
import com.idsmanager.xsifter.service.business.admin.credit.SystemAdminCreditStreadHandler;



public class CreditStreamCreater {
	
	private static final Logger LOG = LoggerFactory.getLogger(CreditStreamCreater.class);
	
	private CreditStreamHolder holder;
	private List<CreditStreamHandler> handlers = new ArrayList<>();
	
	public CreditStreamCreater(CreditStreamHolder holder){
		this.holder = holder;
		initHandlers();
	}
	
	public void create() throws Exception ,CreditNotEnoughException{
		CreditAction action = holder.getAction();
		LOG.debug("{} has create a creditStream {} for company {}",SecurityUtils.username(),holder.getRemarks(),holder.getCompanyUuid());
		for(CreditStreamHandler handler:handlers){
			if(handler.support(action)){
				handler.handle(holder);
				return;
			}
		}
		throw new IllegalArgumentException("no handler found for this action ( "+action.getAction()+") ");
	}

	public boolean checkCreditEnough() throws CreditRuleNotFoundException{
		CreditAction action = holder.getAction();
		for(CreditStreamHandler handler:handlers){
			if(handler.support(action)){		
				return handler.checkCreditEnough(holder);
			}
		}
		throw new IllegalArgumentException("no handler found for this action ( "+action.getAction()+") ");
	}
	
	private void initHandlers(){
		handlers.add(new SystemAdminCreditStreadHandler());
		handlers.add(new AddMemberCreditStreamHandler());
		handlers.add(new DeleteMemberCreditStreamHandler());
		handlers.add(new MemberSignCreditStreamHandler());
		handlers.add(new SuggestionCreditStreamHandler());
		handlers.add(new SearchMemberCreditStreamHandler());
		handlers.add(new DownloaderCreditStreamHandler());
		handlers.add(new InviteSignUpCreditStreamHandler());
		handlers.add(new MemberSignCreditStreamHandler());
	}
	
	
}

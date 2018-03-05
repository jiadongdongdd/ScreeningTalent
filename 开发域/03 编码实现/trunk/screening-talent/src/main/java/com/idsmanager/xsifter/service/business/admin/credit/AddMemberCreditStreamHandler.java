package com.idsmanager.xsifter.service.business.admin.credit;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.CreditRule;
import com.idsmanager.xsifter.domain.admin.MemberLevel;
import com.idsmanager.xsifter.service.MemberService;

public class AddMemberCreditStreamHandler extends AbstractCreditStreamHandler {
	
	private transient MemberService memberService = BeanProvider.getBean(MemberService.class);
	
	@Override
	public boolean support(CreditAction action) {
		// TODO Auto-generated method stub
		return action.getAction().equals(CreditAction.ADD.getAction());
	}



	@Override
	protected Integer getCreditNumber() throws CreditRuleNotFoundException {
		
		CreditRule rule = systemAdminRepository.findCreditRuleByAction(holder.getAction().getAction());
		if(null==rule){
			throw new CreditRuleNotFoundException(holder.getAction().getAction()+"未定义此积分规则");
		}
		
		float  rewardCoefficient = getRewardCoefficient();
		return (int) (rule.getCreditNumber()*rewardCoefficient);
	}

	private float getRewardCoefficient(){
		Integer count =memberService.findMembersCountByCompanyId(holder.getCompanyUuid());
		if(count==0){
			return 1;
		}else{
			MemberLevel memberLevel =systemAdminRepository.findMemberLevelByNumber(count, null);
			if(null==memberLevel){
				return 1;
			}else{
				return Float.parseFloat(memberLevel.getRewardCoefficient());
			}
		}
	}

}

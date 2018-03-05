package com.idsmanager.xsifter.service.business.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.domain.member.Basic;
import com.idsmanager.xsifter.domain.member.Education;
import com.idsmanager.xsifter.domain.member.Entry;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.business.admin.CreditNotEnoughException;
import com.idsmanager.xsifter.service.business.admin.CreditStreamCreater;
import com.idsmanager.xsifter.service.business.admin.credit.CreditRuleNotFoundException;

public class MyMemberRemover {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyMemberRemover.class);

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private transient UserRepository userRepository = BeanProvider
			.getBean(UserRepository.class);

	private String uuid;

	public MyMemberRemover(String uuid) {
		super();
		this.uuid = uuid;
	}

	public void remove() {

		Member member = this.memberRepository.findMemberByUuid(uuid);

		if (member != null) {
			CreditHandle(member);
		}

	}

	public void CreditHandle(Member member) {

		CreditStreamCreater creater = new CreditStreamCreater(
				new CreditStreamHolder(SecurityUtils.currentUserGuid(), "删除职工_"
						+ member.getChName(), CreditAction.DELETE));

		try {
			boolean b = creater.checkCreditEnough();

			if (b) {
				// this.memberRepository.deleteMember(member);
				deleteAllEntities(member);
				LOG.debug("{}|Remove Member: {}", SecurityUtils.username(),
						member);
				User user = this.userRepository.findByGuid(SecurityUtils
						.currentUserGuid());
				if (user == null) {
					creater.create();
				}
			}
		} catch (CreditRuleNotFoundException e) {
			throw new IllegalStateException(e);
		} catch (CreditNotEnoughException e) {
			throw new IllegalStateException(e);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}

	}

	private void deleteAllEntities(Member member) {
		this.memberRepository.deleteMember(member);
		Basic basic = this.memberRepository.findBasicByUuid(uuid);
		if (basic != null) {
			this.memberRepository.deleteBasic(basic);
		}
		Education education = this.memberRepository.findEducationByUuid(uuid);
		if (education != null) {
			this.memberRepository.deleteEducation(education);
		}
		Recruitment recruitment = this.memberRepository
				.findRecruitmentByUuid(uuid);
		if (recruitment != null) {
			this.memberRepository.deleteRecruitment(recruitment);
		}
		Turnover turnover = this.memberRepository.findTurnoverByUuid(uuid);
		if (turnover != null) {
			this.memberRepository.deleteTurnover(turnover);
		}
		Worked worked = this.memberRepository.findWorkedByUuid(uuid);
		if (worked != null) {
			this.memberRepository.deleteWorked(worked);
		}
		Entry entry = this.memberRepository.findEntryByUuid(uuid);
		if (entry != null) {
			this.memberRepository.deleteEntry(entry);
		}

	}
}

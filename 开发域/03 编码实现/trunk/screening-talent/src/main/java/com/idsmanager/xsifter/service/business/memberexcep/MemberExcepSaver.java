package com.idsmanager.xsifter.service.business.memberexcep;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.timer.MemberExcep;
import com.idsmanager.xsifter.domain.timer.MemberExcepRepository;

/**
 * @author andy
 */
public class MemberExcepSaver {
	private static final Logger LOG = LoggerFactory
			.getLogger(MemberExcepRepository.class);
	private transient MemberExcepRepository memberExcepRepository = BeanProvider
			.getBean(MemberExcepRepository.class);

	private Member member;

	private Date entryDate;

	private Date turnoverDate;

	public MemberExcepSaver() {
		super();
	}

	public MemberExcepSaver(Member member) {
		super();
		this.member = member;
	}

	public void save() {
		MemberExcep memberExcep = newMemberExcep(member);
		MemberExcep excep = memberExcepRepository.findMemberExcepByIDNember(member.getIdNumber());
		if(null == excep){
			memberExcepRepository.saveMemberExcep(memberExcep);
		}
		LOG.info("save member Exception info.");
	}

	private MemberExcep newMemberExcep(Member member2) {
		return new MemberExcep().setChName(member.getChName())
				.setCompanyGuid(member.getCompanyGuid())
				.setEmail(member.getEmail()).setIdNumber(member.getIdNumber())
				.setMobile(member.getMobile()).setEntryDate(entryDate)
				.setTurnoverDate(turnoverDate);
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getTurnoverDate() {
		return turnoverDate;
	}

	public void setTurnoverDate(Date turnoverDate) {
		this.turnoverDate = turnoverDate;
	}

}

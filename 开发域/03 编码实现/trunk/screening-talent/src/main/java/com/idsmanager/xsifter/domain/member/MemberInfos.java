package com.idsmanager.xsifter.domain.member;

import com.idsmanager.xsifter.service.dto.menber.RecruitmentFormDto;
import com.idsmanager.xsifter.service.dto.menber.TurnoverFormDto;
import com.idsmanager.xsifter.service.dto.menber.WorkedFormDto;

public class MemberInfos {
	
	private Member member;
	
	private Recruitment recruitment;
	
	private Turnover turnover;
	
	private Worked worked;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Recruitment getRecruitment() {
		return recruitment;
	}

	public void setRecruitment(Recruitment recruitment) {
		this.recruitment = recruitment;
	}

	public Turnover getTurnover() {
		return turnover;
	}

	public void setTurnover(Turnover turnover) {
		this.turnover = turnover;
	}

	public Worked getWorked() {
		return worked;
	}

	public void setWorked(Worked worked) {
		this.worked = worked;
	}

	public MemberInfos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberInfos(Member member, Recruitment recruitment,
			Turnover turnover, Worked worked) {
		super();
		this.member = member;
		this.recruitment = recruitment;
		this.turnover = turnover;
		this.worked = worked;
	}
	
}

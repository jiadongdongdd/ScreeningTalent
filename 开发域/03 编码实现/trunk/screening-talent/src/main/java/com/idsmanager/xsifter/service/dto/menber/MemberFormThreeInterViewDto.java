package com.idsmanager.xsifter.service.dto.menber;

import java.io.Serializable;

import com.idsmanager.xsifter.domain.member.Member;

public class MemberFormThreeInterViewDto implements Serializable {

	private static final long serialVersionUID = 7004422567303627443L;

	private String uuid;
	private String invitationTime;
	private String agree;

	public MemberFormThreeInterViewDto() {
		super();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getInvitationTime() {
		return invitationTime;
	}

	public void setInvitationTime(String invitationTime) {
		this.invitationTime = invitationTime;
	}

	public String getAgree() {
		return agree;
	}

	public void setAgree(String agree) {
		this.agree = agree;
	}
	
	public Member create(Member member) {
		
		
		return member;
		
	}

}

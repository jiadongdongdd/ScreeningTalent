package com.idsmanager.xsifter.service.dto.enterprise;

import java.util.List;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class EnterpriseIndexDto extends AbstractDto {

	private static final long serialVersionUID = -4777907500929185572L;

	private int totalMember;// 录入职工数
	private int memberInfoIntegrity;// 员工信息完整性
	private int inviteCompany;// 邀请企业数量
	private int inviteSuccessCompany;// 邀请企业注册数量
	private String invitedPerson;// 被邀请人
	private int totalStationQueryAmount;// 全站职工查询数

	public EnterpriseIndexDto() {
		super();
	}

	public EnterpriseIndexDto(int inviteCompany, int inviteSuccessCompany,
			int totalMember, String invitedPerson, int totalStationQueryAmount2) {
		this.totalMember = totalMember;
		this.inviteCompany = inviteCompany;
		this.inviteSuccessCompany = inviteSuccessCompany;
		this.invitedPerson = invitedPerson;
		this.totalStationQueryAmount = totalStationQueryAmount2;
	}

	public int getTotalMember() {
		return totalMember;
	}

	public void setTotalMember(int totalMember) {
		this.totalMember = totalMember;
	}

	public int getMemberInfoIntegrity() {
		return memberInfoIntegrity;
	}

	public void setMemberInfoIntegrity(int memberInfoIntegrity) {
		this.memberInfoIntegrity = memberInfoIntegrity;
	}

	public int getInviteCompany() {
		return inviteCompany;
	}

	public void setInviteCompany(int inviteCompany) {
		this.inviteCompany = inviteCompany;
	}

	public int getInviteSuccessCompany() {
		return inviteSuccessCompany;
	}

	public void setInviteSuccessCompany(int inviteSuccessCompany) {
		this.inviteSuccessCompany = inviteSuccessCompany;
	}

	public String getInvitedPerson() {
		return invitedPerson;
	}

	public void setInvitedPerson(String invitedPerson) {
		this.invitedPerson = invitedPerson;
	}

	public int getTotalStationQueryAmount() {
		return totalStationQueryAmount;
	}

	public void setTotalStationQueryAmount(int totalStationQueryAmount) {
		this.totalStationQueryAmount = totalStationQueryAmount;
	}

}

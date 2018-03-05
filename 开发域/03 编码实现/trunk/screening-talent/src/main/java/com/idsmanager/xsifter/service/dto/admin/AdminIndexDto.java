package com.idsmanager.xsifter.service.dto.admin;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class AdminIndexDto extends AbstractDto {

	private static final long serialVersionUID = 2949385114022243165L;

	private int companyRegisterAmount;// 企业用户注册量
	private int totalMemberAmount;// 职工总数
	private int activeUsersAmount;// 活跃用户数
	private int totalStationQueryAmount;// 全站职工查询数

	public AdminIndexDto() {
		super();
	}

	public AdminIndexDto(int totalCompany, int totalMember,
			int totalActiveUsers, int totalStationQueryAmount2) {
		this.companyRegisterAmount = totalCompany;
		this.totalMemberAmount = totalMember;
		this.activeUsersAmount = totalActiveUsers;
		this.totalStationQueryAmount = totalStationQueryAmount2;
	}

	public int getCompanyRegisterAmount() {
		return companyRegisterAmount;
	}

	public void setCompanyRegisterAmount(int companyRegisterAmount) {
		this.companyRegisterAmount = companyRegisterAmount;
	}

	public int getTotalMemberAmount() {
		return totalMemberAmount;
	}

	public void setTotalMemberAmount(int totalMemberAmount) {
		this.totalMemberAmount = totalMemberAmount;
	}

	public int getActiveUsersAmount() {
		return activeUsersAmount;
	}

	public void setActiveUsersAmount(int activeUsersAmount) {
		this.activeUsersAmount = activeUsersAmount;
	}

	public int getTotalStationQueryAmount() {
		return totalStationQueryAmount;
	}

	public void setTotalStationQueryAmount(int totalStationQueryAmount) {
		this.totalStationQueryAmount = totalStationQueryAmount;
	}

}

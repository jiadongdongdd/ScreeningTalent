package com.idsmanager.xsifter.service.dto.admin;

import java.util.List;

import com.idsmanager.xsifter.domain.admin.MemberLevel;
import com.idsmanager.xsifter.service.dto.AbstractDto;

public class MemberLevelDto extends AbstractDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2365300918796211032L;
	
	
	private String uuid;
	
	private String createTime;
	
	private String level;
	
	private String MaxNumber;
	
	private String mixNumber;
	
	private String rewardCoefficient;
	
	private String remarks;

	
	public MemberLevelDto(){
		
	}
	
	public MemberLevelDto(MemberLevel level){
		this.uuid = level.getUuid();
		this.createTime = level.getCreateTime();
		
		this.level = level.getLevel();
		this.MaxNumber = String.valueOf(level.getMaxNumber());
		this.mixNumber = String.valueOf(level.getMixNumber());
		
		this.rewardCoefficient = level.getRewardCoefficient();
		this.remarks = level.getRemarks();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMaxNumber() {
		return MaxNumber;
	}

	public void setMaxNumber(String maxNumber) {
		MaxNumber = maxNumber;
	}

	public String getMixNumber() {
		return mixNumber;
	}

	public void setMixNumber(String mixNumber) {
		this.mixNumber = mixNumber;
	}

	public String getRewardCoefficient() {
		return rewardCoefficient;
	}

	public void setRewardCoefficient(String rewardCoefficient) {
		this.rewardCoefficient = rewardCoefficient;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
	

}

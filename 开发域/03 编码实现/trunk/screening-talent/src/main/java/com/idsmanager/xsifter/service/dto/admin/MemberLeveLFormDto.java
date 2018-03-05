package com.idsmanager.xsifter.service.dto.admin;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class MemberLeveLFormDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8056462721256153239L;
	

	private String level;
	
	private String MaxNumber;
	
	private String mixNumber;
	
	private String rewardCoefficient;
	
	private String remarks;
	
	public MemberLeveLFormDto(){
		
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
	
	

}

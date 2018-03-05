package com.idsmanager.xsifter.domain.admin;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.UUIDGenerator;

@Document(collection="member_level")
public class MemberLevel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4667433139022947069L;
	
	@Id
	private String uuid = UUIDGenerator.generate();
	
	@CreatedDate
	private Date createTime = DateUtils.now();
	
	private String level;
	
	private Integer maxNumber;
	
	private Integer mixNumber;
	
	private String rewardCoefficient;
	
	private String remarks;

	public String getUuid() {
		return uuid;
	}

	public String getCreateTime() {
		return DateUtils.toDateTime(createTime);
	}

	public Date createTime(){
		return createTime;
	}
	
	public String getLevel() {
		return level;
	}

	public Integer getMaxNumber() {
		return maxNumber;
	}

	public Integer getMixNumber() {
		return mixNumber;
	}

	public String getRewardCoefficient() {
		return rewardCoefficient;
	}

	public String getRemarks() {
		return remarks;
	}
	
	public MemberLevel setUuid(String uuid){
		this.uuid = uuid;
		return this;
	}

	public MemberLevel setLevel(String level) {
		this.level = level;
		return this;
	}

	public MemberLevel setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
		return this;
	}

	public MemberLevel setMixNumber(Integer mixNumber) {
		this.mixNumber = mixNumber;
		return this;
	}

	public MemberLevel setRewardCoefficient(String rewardCoefficient) {
		this.rewardCoefficient = rewardCoefficient;
		return this;
	}

	public MemberLevel setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	@Override
	public String toString() {
		return "MemberLevel [uuid=" + uuid + ", createTime=" + createTime
				+ ", level=" + level + ", maxNumber=" + maxNumber
				+ ", mixNumber=" + mixNumber + ", rewardCoefficient="
				+ rewardCoefficient + ", remarks=" + remarks + "]";
	}
	
	
	
	
}

package com.idsmanager.xsifter.service.dto.menber;

import java.util.Date;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.member.Entry;
import com.idsmanager.xsifter.service.dto.AbstractDto;

public class EntryFormDto extends AbstractDto {

	private static final long serialVersionUID = 8588521936365997222L;

	private String uuid;

	private String companGuid;
	private String companyName;
	private Date entryDate;

	/*
	 * 基本信息
	 */
	private String chName;
	private String mobile;
	private String idNumber;
	private String email;

	private TurnoverFormDto turnoverFormDto;
	private WorkedFormDto workedFormDto;

	public EntryFormDto() {
		super();
	}

	public EntryFormDto(Entry entry) {
		this.companGuid = entry.getCompanGuid();
		this.companyName = entry.getCompanyName();
		this.chName = entry.getChNameBasic();
		this.email = entry.getEmailBasic();
		this.mobile = entry.getMobileBasic();
		this.idNumber = entry.getIdNumberBasic();
		this.entryDate = entry.getEntryDate();
		if (entry.getTurnover() != null) {
			this.turnoverFormDto = new TurnoverFormDto(entry.getTurnover());
		}
		if (entry.getWorked() != null) {
			this.workedFormDto = new WorkedFormDto(entry.getWorked());
		}
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCompanGuid() {
		return companGuid;
	}

	public void setCompanGuid(String companGuid) {
		this.companGuid = companGuid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TurnoverFormDto getTurnoverFormDto() {
		return turnoverFormDto;
	}

	public void setTurnoverFormDto(TurnoverFormDto turnoverFormDto) {
		this.turnoverFormDto = turnoverFormDto;
	}

	public WorkedFormDto getWorkedFormDto() {
		return workedFormDto;
	}

	public void setWorkedFormDto(WorkedFormDto workedFormDto) {
		this.workedFormDto = workedFormDto;
	}

	public String getEntryDateTime() {
		return DateUtils.toDateText(entryDate, "yyyy-MM-dd");
	}

}

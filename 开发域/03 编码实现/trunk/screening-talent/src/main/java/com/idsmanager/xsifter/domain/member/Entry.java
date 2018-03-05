package com.idsmanager.xsifter.domain.member;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.AbstractDomain;

@Document(collection = "Entry")
public class Entry extends AbstractDomain {

	private static final long serialVersionUID = 1523168146074818008L;

	private String companGuid;
	private String companyName;
	private Date entryDate;

	/*
	 * 基本信息
	 */
	private String chNameBasic;
	private String mobileBasic;
	private String idNumberBasic;
	private String emailBasic;

	private Worked worked;

	private Turnover turnover;

	public Entry() {
		super();
	}

	public Entry(Turnover turnover2) {
		this.uuid = turnover2.getMemberUuid();
		this.turnover = turnover2;
		this.companGuid = turnover2.getCompanyGuid();
		this.companyName = turnover2.getCompanyName();
		this.chNameBasic = turnover2.getChNameBasic();
		this.idNumberBasic = turnover2.getIdNumberBasic();
		this.emailBasic = turnover2.getEmailBasic();
		this.mobileBasic = turnover2.getMobileBasic();
	}

	public Entry(Worked worked2) {
		this.uuid = worked2.getMemberUuid();
		this.worked = worked2;
		this.chNameBasic = worked2.getChNameBasic();
		this.idNumberBasic = worked2.getIdNumberBasic();
		this.emailBasic = worked2.getEmailBasic();
		this.mobileBasic = worked2.getMobileBasic();
		this.companGuid = worked2.getCompanyGuid();
		this.companyName = worked2.getCompanyName();
	}

	public String getCompanGuid() {
		return companGuid;
	}

	public Entry setCompanGuid(String companGuid) {
		this.companGuid = companGuid;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Entry setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public Worked getWorked() {
		return worked;
	}

	public Entry setWorked(Worked worked) {
		this.worked = worked;
		return this;
	}

	public Turnover getTurnover() {
		return turnover;
	}

	public Entry setTurnover(Turnover turnover) {
		this.turnover = turnover;
		return this;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public Entry setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
		return this;
	}

	public String getEntryDateTime() {
		return DateUtils.toDateText(entryDate, "yyyy-MM-dd");
	}

	public String getChNameBasic() {
		return chNameBasic;
	}

	public Entry setChNameBasic(String chNameBasic) {
		this.chNameBasic = chNameBasic;
		return this;
	}

	public String getMobileBasic() {
		return mobileBasic;
	}

	public Entry setMobileBasic(String mobileBasic) {
		this.mobileBasic = mobileBasic;
		return this;
	}

	public String getIdNumberBasic() {
		return idNumberBasic;
	}

	public Entry setIdNumberBasic(String idNumberBasic) {
		this.idNumberBasic = idNumberBasic;
		return this;
	}

	public String getEmailBasic() {
		return emailBasic;
	}

	public Entry setEmailBasic(String emailBasic) {
		this.emailBasic = emailBasic;
		return this;
	}

}

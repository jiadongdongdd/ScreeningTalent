package com.idsmanager.xsifter.service.dto.menber;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class QueryMemberDetailFormDto extends AbstractDto {

	private static final long serialVersionUID = 1277867800955764468L;

	private String uuid;
	private BasicFormDto basicFormDto;// 基本信息
	private TelInvitationPaginated telPaginated;// 电话邀约
	private InterviewRecordPaginated interviewPaginated;// 面试记录
	private EntryRecordPaginated entryPaginated;// 入职记录

	public QueryMemberDetailFormDto() {
		super();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public BasicFormDto getBasicFormDto() {
		return basicFormDto;
	}

	public void setBasicFormDto(BasicFormDto basicFormDto) {
		this.basicFormDto = basicFormDto;
	}

	public TelInvitationPaginated getTelPaginated() {
		return telPaginated;
	}

	public void setTelPaginated(TelInvitationPaginated telPaginated) {
		this.telPaginated = telPaginated;
	}

	public InterviewRecordPaginated getInterviewPaginated() {
		return interviewPaginated;
	}

	public void setInterviewPaginated(
			InterviewRecordPaginated interviewPaginated) {
		this.interviewPaginated = interviewPaginated;
	}

	public EntryRecordPaginated getEntryPaginated() {
		return entryPaginated;
	}

	public void setEntryPaginated(EntryRecordPaginated entryPaginated) {
		this.entryPaginated = entryPaginated;
	}

}

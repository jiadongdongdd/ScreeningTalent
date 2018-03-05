package com.idsmanager.xsifter.service.dto.menber;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class QueryDetailFormDto extends AbstractDto {

	private static final long serialVersionUID = 2876610343304939367L;

	private String uuid;

	private BasicFormDto basicFormDto;// 基本信息

	private RecruitmentQueryPaginated recruitmentPaginated;// 招聘环节
	private TurnoverQueryPaginated turnoverPaginated;// 离职环节
	private WorkedQueryPaginated workedPaginated;// 工作环节

	public QueryDetailFormDto() {
		super();
	}

	public QueryDetailFormDto(String uuid) {
		this.uuid = uuid;
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

	public RecruitmentQueryPaginated getRecruitmentPaginated() {
		return recruitmentPaginated;
	}

	public void setRecruitmentPaginated(
			RecruitmentQueryPaginated recruitmentPaginated) {
		this.recruitmentPaginated = recruitmentPaginated;
	}

	public TurnoverQueryPaginated getTurnoverPaginated() {
		return turnoverPaginated;
	}

	public void setTurnoverPaginated(TurnoverQueryPaginated turnoverPaginated) {
		this.turnoverPaginated = turnoverPaginated;
	}

	public WorkedQueryPaginated getWorkedPaginated() {
		return workedPaginated;
	}

	public void setWorkedPaginated(WorkedQueryPaginated workedPaginated) {
		this.workedPaginated = workedPaginated;
	}

}

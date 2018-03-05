package com.idsmanager.xsifter.service.dto.menber;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class DetailFormDto extends AbstractDto {

	private static final long serialVersionUID = -5997433110081620782L;

	private String uuid;

	private String partName;

	private BasicFormDto basicFormDto;
	private RecruitmentFormDto recruitmentFormDto;
	private TurnoverFormDto turnoverFormDto;
	private WorkedFormDto workedFormDto;
	private EntryFormDto entryFormDto;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public BasicFormDto getBasicFormDto() {
		return basicFormDto;
	}

	public void setBasicFormDto(BasicFormDto basicFormDto) {
		this.basicFormDto = basicFormDto;
	}

	public RecruitmentFormDto getRecruitmentFormDto() {
		return recruitmentFormDto;
	}

	public void setRecruitmentFormDto(RecruitmentFormDto recruitmentFormDto) {
		this.recruitmentFormDto = recruitmentFormDto;
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

	public EntryFormDto getEntryFormDto() {
		return entryFormDto;
	}

	public void setEntryFormDto(EntryFormDto entryFormDto) {
		this.entryFormDto = entryFormDto;
	}

}

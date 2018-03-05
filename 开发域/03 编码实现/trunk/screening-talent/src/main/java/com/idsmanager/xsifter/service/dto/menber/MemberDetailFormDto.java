package com.idsmanager.xsifter.service.dto.menber;

import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.service.dto.AbstractDto;

public class MemberDetailFormDto extends AbstractDto {

	private static final long serialVersionUID = 8043033401434055907L;

	private String uuid;

	private MemberFormOneDto formOneDto;
	private MemberFormTwoDto formTwoDto;
	private RecruitmentFormDto recruitmentFormDto;
	private TurnoverFormDto turnoverFormDto;
	private WorkedFormDto workedFormDto;

	public MemberDetailFormDto() {
		super();
	}

	public MemberDetailFormDto(Member member) {
		this.formOneDto = new MemberFormOneDto(member);
		this.formTwoDto = new MemberFormTwoDto(member);

	}

	public MemberDetailFormDto(String uuid) {
		this.uuid = uuid;
	}

	public MemberFormOneDto getFormOneDto() {
		return formOneDto;
	}

	public void setFormOneDto(MemberFormOneDto formOneDto) {
		this.formOneDto = formOneDto;
	}

	public MemberFormTwoDto getFormTwoDto() {
		return formTwoDto;
	}

	public void setFormTwoDto(MemberFormTwoDto formTwoDto) {
		this.formTwoDto = formTwoDto;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public void createRecruitmentFormDto(Recruitment recruitment) {

		this.recruitmentFormDto = new RecruitmentFormDto(recruitment);

	}

	public void createTurnoverFormDto(Turnover turnover) {
		this.turnoverFormDto = new TurnoverFormDto(turnover);
	}

	public void createWorkedFormDto(Worked worked) {
		this.workedFormDto = new WorkedFormDto(worked);
	}

}

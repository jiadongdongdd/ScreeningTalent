package com.idsmanager.xsifter.service.dto.menber;

import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.service.dto.AbstractDto;

public class BasicFormDto extends AbstractDto {

	private static final long serialVersionUID = -3620065383019362382L;

	private String uuid;

	private String companyName;

	private MemberFormOneDto formOneDto;
	private MemberFormTwoDto formTwoDto;

	public BasicFormDto() {
		super();
	}

	public BasicFormDto(Member member) {
		this.formOneDto = new MemberFormOneDto(member);
		this.formTwoDto = new MemberFormTwoDto(member);
		this.companyName = member.getCompanyName();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}

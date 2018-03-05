package com.idsmanager.xsifter.service.dto.menber;

import java.util.List;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class ImportExcelResultDto extends AbstractDto {

	private static final long serialVersionUID = -1740226657425622317L;

	private boolean result = true;
	private int importAmount;
	private List<ErrorDataDto> errorDatas;
	private List<String> memberUuids;

	public ImportExcelResultDto() {
		super();
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public int getImportAmount() {
		return importAmount;
	}

	public void setImportAmount(int importAmount) {
		this.importAmount = importAmount;
	}

	public List<ErrorDataDto> getErrorDatas() {
		return errorDatas;
	}

	public void setErrorDatas(List<ErrorDataDto> errorDatas) {
		this.errorDatas = errorDatas;
	}

	public List<String> getMemberUuids() {
		return memberUuids;
	}

	public void setMemberUuids(List<String> memberUuids) {
		this.memberUuids = memberUuids;
	}

}

package com.idsmanager.xsifter.service.dto.menber;

import com.idsmanager.xsifter.service.dto.AbstractDto;

public class QueryResultDto extends AbstractDto {

	private static final long serialVersionUID = -5931882959898816404L;

	private boolean chNameEmpty = true;
	private boolean optionEmpty = true;

	public boolean isChNameEmpty() {
		return chNameEmpty;
	}

	public void setChNameEmpty(boolean chNameEmpty) {
		this.chNameEmpty = chNameEmpty;
	}

	public boolean isOptionEmpty() {
		return optionEmpty;
	}

	public void setOptionEmpty(boolean optionEmpty) {
		this.optionEmpty = optionEmpty;
	}

}

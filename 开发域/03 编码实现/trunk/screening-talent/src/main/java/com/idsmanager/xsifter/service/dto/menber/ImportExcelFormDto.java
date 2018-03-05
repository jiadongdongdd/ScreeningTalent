package com.idsmanager.xsifter.service.dto.menber;

import org.springframework.web.multipart.MultipartFile;

import com.idsmanager.xsifter.service.dto.AbstractDto;

//导入职工Excel
public class ImportExcelFormDto extends AbstractDto {

	private static final long serialVersionUID = -6566842860090742388L;

	private MultipartFile excelFile;

	public MultipartFile getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(MultipartFile excelFile) {
		this.excelFile = excelFile;
	}

}

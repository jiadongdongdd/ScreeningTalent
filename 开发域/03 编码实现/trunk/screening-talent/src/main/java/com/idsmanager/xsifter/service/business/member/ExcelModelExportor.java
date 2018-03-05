package com.idsmanager.xsifter.service.business.member;

import java.io.IOException;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.service.dto.IdsFileDto;

public class ExcelModelExportor {

	private static final Logger LOG = LoggerFactory
			.getLogger(ExcelModelExportor.class);

	private static final String EXPORT_EXCEL_PATH = "/member_template.xls";

	public IdsFileDto export() {

		IdsFileDto fileDto = new IdsFileDto();

		Resource resource = new ClassPathResource(EXPORT_EXCEL_PATH);
		byte[] bytes = null;
		try {
			bytes = IOUtils.toByteArray(resource.getInputStream());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		fileDto.setData(bytes);
		fileDto.setName("member_template");

		LOG.debug("{}| Download the member import Excel Template",
				SecurityUtils.username());

		return fileDto;

	}
}

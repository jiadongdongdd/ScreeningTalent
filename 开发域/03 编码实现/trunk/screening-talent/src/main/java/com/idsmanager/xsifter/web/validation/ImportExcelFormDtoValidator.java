package com.idsmanager.xsifter.web.validation;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.idsmanager.xsifter.service.dto.menber.ImportExcelFormDto;

@Component
public class ImportExcelFormDtoValidator implements Validator {

	public static final List<String> EXCEL_EXTENSIONS = Arrays.asList("xls");

	@Override
	public boolean supports(Class<?> clazz) {
		return ImportExcelFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ImportExcelFormDto formDto = (ImportExcelFormDto) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "excelFile", null,
				"文件不能为空");

		ValidateFileName(formDto, errors);

	}

	private void ValidateFileName(ImportExcelFormDto formDto, Errors errors) {
		final MultipartFile file = formDto.getExcelFile();
		String fileName = file.getOriginalFilename();
		boolean b = isExcel2003FileName(fileName);
		if (!b) {
			errors.rejectValue("excelFile", null, "导入文件必须是.xls格式的文件");
			return;
		}

	}

	private boolean isExcel2003FileName(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return false;
		}
		String extension = FilenameUtils.getExtension(fileName);
		return EXCEL_EXTENSIONS.contains(extension);

	}
}

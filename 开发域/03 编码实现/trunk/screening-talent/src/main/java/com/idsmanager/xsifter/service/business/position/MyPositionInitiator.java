package com.idsmanager.xsifter.service.business.position;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;
import com.idsmanager.xsifter.domain.position.PositionIndustry;

public class MyPositionInitiator {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyPositionInitiator.class);

	private transient MemberPositionRepository memberPositionRepository = BeanProvider
			.getBean(MemberPositionRepository.class);

	public MyPositionInitiator() {
		super();
	}

	public void initial() throws FileNotFoundException, IOException {

		long count = this.memberPositionRepository.countPositions();
		if (count == 0) {
			// 读取 职位.xlsx 进行初始化
			importExcel();
			LOG.debug("System initial default member position");
		}

	}

	private void importExcel() throws FileNotFoundException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(
				MyPositionInitiator.class.getResourceAsStream("/职位.xlsx"));
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
			XSSFRow row = sheet.getRow(i);
			MemberPosition position = new MemberPosition();
			if (i > 0) {
				XSSFCell cell = row.getCell(0);
				XSSFCell cell1 = row.getCell(1);
				XSSFCell cell2 = row.getCell(2);
				position.setPositionName(cell.getStringCellValue().trim())
						.setLevel(cell1.getStringCellValue().trim())
						.setIndustry(cell2.getStringCellValue().trim());
				memberPositionRepository.savePosition(position);
			}
		}
	}

}

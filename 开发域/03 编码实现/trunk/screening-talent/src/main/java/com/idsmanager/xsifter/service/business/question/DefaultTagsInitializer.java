package com.idsmanager.xsifter.service.business.question;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.question.Tag;
import com.idsmanager.xsifter.domain.question.TagRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

/**
 * Created by LZW on 2016/9/18.
 */
public class DefaultTagsInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultTagsInitializer.class);

    private transient TagRepository tagRepository = BeanProvider.getBean(TagRepository.class);

    private static final String PATH = "/标签.xlsx";

    public DefaultTagsInitializer() {
    }

    public void initial() throws IOException {
        List<Tag> tags = tagRepository.findAllTags();
        //如果标签未初始化
        if (null == tags || tags.isEmpty()) {
            //根据 标签.xlxs 进行初始化
            initialExcelData();

        }
    }

    private void initialExcelData() throws IOException {
        Resource resource = new ClassPathResource(PATH);
        XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            Tag tag = new Tag();
            if (i > 0) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                XSSFCell cell = row.getCell(0);
                XSSFCell cell1 = row.getCell(1);
                XSSFCell cell2 = row.getCell(2);
                XSSFCell cell3 = row.getCell(3);
                tag.id(cell.getStringCellValue().trim())
                        .name(cell1.getStringCellValue().trim())
                        .pId(cell2.getStringCellValue().trim())
                        .open(cell3.getBooleanCellValue())
                        .noR(true);
                tagRepository.saveTag(tag);
            }
        }

        LOG.debug("{}|Initial Tags", SecurityUtils.username());

    }


}

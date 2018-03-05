package com.idsmanager.commons.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.DateUtil;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.member.Degree;
import com.idsmanager.xsifter.domain.member.Education;
import com.idsmanager.xsifter.domain.member.PoliticalStatus;

public class MemberExcelUtils {

	/**
	 * 根据cell类型获取String值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(HSSFCell cell) {
		if (cell == null) {
			return "";
		}
		String cellValue = "";
		int cellType = cell.getCellType();
		switch (cellType) {
		case HSSFCell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue().trim();
			break;

		case HSSFCell.CELL_TYPE_NUMERIC:
			if (cell.getColumnIndex() == 2 || cell.getColumnIndex() == 3) {
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cellValue = cell.getStringCellValue().trim();
			} else {
				if (DateUtil.isCellDateFormatted(cell)) {
					if (cell.getNumericCellValue() != 0) {
						cellValue = DateUtils.toDate(HSSFDateUtil
								.getJavaDate(cell.getNumericCellValue())).trim();
					}
				} else {
					cellValue = String.valueOf(cell.getNumericCellValue()).trim();
				}
			}

			break;

		case HSSFCell.CELL_TYPE_BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
			break;

		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = "";
			break;

		default:
			cellValue = "";
			break;
		}

		return cellValue;
	}

	/**
	 * 设置学历
	 * 
	 * @param education
	 * @param value
	 */
	public static void setDegree(Education education, String value) {

		switch (value) {
		case "小学":
			education.setDegree(Degree.PRIMARY_SCHOOL);
			break;
		case "初中":
			education.setDegree(Degree.JUNIOR_MIDDLE_SCHOOL);
			break;
		case "高中":
			education.setDegree(Degree.HIGH_SCHOOL);
			break;
		case "大专":
			education.setDegree(Degree.JUNIOR_COLLEGE);
			break;
		case "本科":
			education.setDegree(Degree.UNDERGRADUATE);
			break;
		case "硕士":
			education.setDegree(Degree.MASTER);
			break;
		case "博士":
			education.setDegree(Degree.DOCTOR);
			break;

		default:
			break;
		}

	}

	/**
	 * 设置政治面貌
	 * 
	 * @param education
	 * @param value
	 */
	public static void setPolitical(Education education, String value) {

		switch (value) {
		case "群众":
			education.setPoliticalStatus(PoliticalStatus.MASSES);
			break;
		case "团员":
			education.setPoliticalStatus(PoliticalStatus.LEAGUE_MEMBER);
			break;
		case "党员":
			education.setPoliticalStatus(PoliticalStatus.PARTY_MEMBER);
			break;

		default:
			break;
		}

	}

}

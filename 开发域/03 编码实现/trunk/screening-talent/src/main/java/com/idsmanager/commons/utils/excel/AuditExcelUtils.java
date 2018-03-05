package com.idsmanager.commons.utils.excel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.log.Log;

public class AuditExcelUtils {

	// 导出审计表
	public static void exportAudit(HttpServletRequest request,
			HttpServletResponse response, List<Log> list) throws IOException {

		// 创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		updateWorkBook(workbook, list);
		writeWorkBook(workbook, response);

	}

	// 创建工作薄&sheet
	private static void updateWorkBook(HSSFWorkbook workbook, List<Log> list) {

		// 总记录数
		int size = list.size();

		int maxRow = 65535;

		HSSFSheet sheet = createSheet(0, workbook);
		for (int i = 0; i < size; i++) {
			if (i != 0 && i % maxRow == 0) {
				sheet = createSheet(((i + 1) / maxRow), workbook);
			}

			updateSheet(sheet, list.get(i), i % maxRow + 1);

		}

	}

	private static void writeWorkBook(HSSFWorkbook workbook,
			HttpServletResponse response) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);

		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader(
				"Content-Disposition",
				"attachment;filename="
						+ new String(("operational_audit_"
								+ DateUtils.toDateText(DateUtils.now(),
										"yyyyMMddHHmmss") + ".xls").getBytes(),
								"iso-8859-1"));

		ServletOutputStream out = response.getOutputStream();

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {

			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);

			byte[] buff = new byte[2048];
			int bytesRead;

			// read data in stream
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}

	}

	private static void updateSheet(HSSFSheet sheet, Log log, int i) {
		HSSFRow row = sheet.createRow(i);
		createContentRow(row, log);
	}

	private static void createContentRow(HSSFRow row, Log log) {

		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				HSSFCell cell0 = row.createCell(i);
				cell0.setCellValue(log.getEntityName() == null ? "" : log
						.getEntityName());
				break;
			case 1:
				HSSFCell cell1 = row.createCell(i);
				cell1.setCellValue(log.getOperateDetail() == null ? "" : log
						.getOperateDetail());
				break;
			case 2:
				HSSFCell cell2 = row.createCell(i);
				cell2.setCellValue(log.getCreateTime() == null ? "" : log
						.getCreateTime());
				break;
			case 3:
				HSSFCell cell3 = row.createCell(i);
				cell3.setCellValue(log.getOperator() == null ? "" : log
						.getOperator());
				break;

			default:
				break;
			}
		}

	}

	/**
	 * 
	 * @param i
	 *            (sheet数)
	 * 
	 * @return
	 */
	private static HSSFSheet createSheet(int i, HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet("第" + (i + 1) + "页");
		HSSFRow row = sheet.createRow(0);
		createHeadRow(row);
		return sheet;
	}

	private static void createHeadRow(HSSFRow row) {
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				HSSFCell cell0 = row.createCell(i);
				cell0.setCellValue("被操作人");
				break;
			case 1:
				HSSFCell cell1 = row.createCell(i);
				cell1.setCellValue("操作详情");
				break;
			case 2:
				HSSFCell cell2 = row.createCell(i);
				cell2.setCellValue("操作时间");
				break;
			case 3:
				HSSFCell cell3 = row.createCell(i);
				cell3.setCellValue("操作人");
				break;

			default:
				break;
			}
		}

	}

}

package com.idsmanager.commons.utils.excel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.GenderStatus;
import com.idsmanager.xsifter.domain.IntentionStatus;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.member.MemberInfos;
import com.idsmanager.xsifter.domain.member.TurnoverReason;

public class ExcelUtils {

	private static Integer numPage = 0;

	public static String _importExcel(HttpServletRequest request,
			HttpServletResponse response, List<MemberInfos> listMember)
			throws Exception {

		// 获得要导出的数据集
		List<MemberInfos> list = listMember;

		// 创建excel工作簿
		Workbook wb = new HSSFWorkbook();
		// 创建第一个sheet（页），并命名
		numPage++;
		Sheet sheet = wb.createSheet("第" + numPage + "页");

		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
		setColumnWidth(sheet);

		// 创建第一行
		Row row = sheet.createRow((short) 0);

		// 创建两种单元格格式
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();
		// DataFormat df = wb.createDataFormat();

		createCellStyle(wb, cs, cs2);

		// cs2.setDataFormat(df.getFormat("text"));

		// 设置标题
		createExcelHeader(row, cs);

		// 填充数据
		try {
			fillInData(wb, sheet, cs2, list);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		/*
		 * for (short i = 0; i < list.size(); i++) {
		 * 
		 * try { // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的 // 创建一行，在页sheet上 row =
		 * sheet.createRow((short) i + 1); // 在row行上创建一个方格 Cell cell =
		 * row.createCell(0); cell.setCellValue(list.get(i).getMember() == null
		 * ? "暂无" : list .get(i).getMember().getChName());
		 * cell.setCellStyle(cs2);
		 * 
		 * cell = row.createCell(1); cell.setCellValue(list.get(i).getMember()
		 * == null ? "暂无" : list .get(i).getMember().getEnName());
		 * cell.setCellStyle(cs2);
		 * 
		 * cell = row.createCell(2); cell.setCellValue(list.get(i).getMember()
		 * == null ? "暂无" : list .get(i).getMember().getMobile());
		 * cell.setCellStyle(cs2);
		 * 
		 * cell = row.createCell(3); cell.setCellValue(list.get(i).getMember()
		 * == null ? "暂无" : list .get(i).getMember().getIdNumber());
		 * cell.setCellStyle(cs2);
		 * 
		 * cell = row.createCell(4); cell.setCellValue(list.get(i).getMember()
		 * == null ? "暂无" : list .get(i).getMember().getEmail());
		 * cell.setCellStyle(cs2);
		 * 
		 * cell = row.createCell(5); cell.setCellValue(list.get(i).getMember()
		 * == null ? "暂无" : (GenderStatus.MALE.equals(list.get(i).getMember()
		 * .getGender()) ? "男" : "女")); cell.setCellStyle(cs2);
		 * 
		 * cell = row.createCell(6); cell.setCellValue(list.get(i).getMember()
		 * == null ? "暂无" : list .get(i).getMember().getCreateTime());
		 * cell.setCellStyle(cs2);
		 * 
		 * cell = row.createCell(7); cell.setCellValue(list.get(i).getTurnover()
		 * == null ? "暂无" : list.get(i).getTurnover()
		 * .isIllegalDestroyCompanyFace() == false ? "否" : "是");
		 * cell.setCellStyle(cs2);
		 * 
		 * cell = row.createCell(8); cell.setCellValue(list.get(i).getTurnover()
		 * == null ? "暂无" : list.get(i).getTurnover().getTurnoverDateTime());
		 * cell.setCellStyle(cs2);
		 * 
		 * cell = row.createCell(9); cell.setCellValue(list.get(i).getTurnover()
		 * == null ? "暂无" :
		 * list.get(i).getTurnover().isBreachTrainingAgreement() == false ? "否"
		 * : "是"); cell.setCellStyle(cs2);
		 * 
		 * cell = row.createCell(10);
		 * cell.setCellValue(list.get(i).getRecruitment() == null ? "暂无" :
		 * list.get(i).getRecruitment().getInvitationTimeText());
		 * cell.setCellStyle(cs2);
		 * 
		 * cell = row.createCell(11);
		 * cell.setCellValue(list.get(i).getRecruitment() == null ? "暂无" :
		 * IntentionStatus.ENABLED.equals(list.get(i)
		 * .getRecruitment().getEntrySuccess()) ? "是" : "否");
		 * cell.setCellStyle(cs2);
		 * 
		 * cell = row.createCell(12);
		 * cell.setCellValue(list.get(i).getRecruitment() == null ? "暂无" :
		 * list.get(i).getRecruitment().getEntryDateTime());
		 * cell.setCellStyle(cs2); } catch (Exception e) { e.printStackTrace();
		 * } }
		 */

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);

		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(("employee_info.xls").getBytes(), "iso-8859-1"));

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
		return null;

	}

	private static void fillInData(Workbook workbook, Sheet sheet,
			CellStyle cs2, List<MemberInfos> list) {
		for (int i = 0; i < list.size(); i++) {
			Row row = sheet.createRow(i + 1);
			// 中文姓名
			Cell cell = row.createCell(0);
			cell.setCellValue(list.get(i).getMember() == null ? "" : list
					.get(i).getMember().getChName());
			cell.setCellStyle(cs2);
			// 英文姓名
			cell = row.createCell(1);
			cell.setCellValue(list.get(i).getMember() == null ? "" : list
					.get(i).getMember().getEnName());
			cell.setCellStyle(cs2);
			// 手机号
			cell = row.createCell(2);
			cell.setCellValue(list.get(i).getMember() == null ? "" : list
					.get(i).getMember().getMobile());
			cell.setCellStyle(cs2);
			// 身份证号
			cell = row.createCell(3);
			cell.setCellValue(list.get(i).getMember() == null ? "" : list
					.get(i).getMember().getIdNumber());
			cell.setCellStyle(cs2);
			// 邮箱
			cell = row.createCell(4);
			cell.setCellValue(list.get(i).getMember() == null ? "" : list
					.get(i).getMember().getEmail());
			cell.setCellStyle(cs2);
			// 性别
			cell = row.createCell(5);
			cell.setCellValue(list.get(i).getMember() == null ? ""
					: (GenderStatus.MALE.equals(list.get(i).getMember()
							.getGender()) ? "男" : "女"));
			cell.setCellStyle(cs2);
			// 出生日期
			cell = row.createCell(6);
			DataFormat format = workbook.createDataFormat();
			cs2.setDataFormat(format.getFormat("yyyy/m/d"));
			cell.setCellStyle(cs2);
			if (list.get(i).getMember() == null
					|| list.get(i).getMember().getBirthday() == null) {
				//cell.setCellValue("");
			} else {

				cell.setCellValue(DateUtils.getDate(list.get(i).getMember()
						.getBirthday(), "yyyy-MM-dd"));

			}
			// 毕业院校
			cell = row.createCell(7);
			cell.setCellValue(list.get(i).getMember() == null ? "" : list
					.get(i).getMember().getSchool());
			cell.setCellStyle(cs2);
			// 学历
			cell = row.createCell(8);
			cell.setCellValue(list.get(i).getMember() == null ? "" : (list
					.get(i).getMember().getDegree() == null ? "" : list.get(i)
					.getMember().getDegree().getLabel()));
			cell.setCellStyle(cs2);
			// 政治面貌
			cell = row.createCell(9);
			cell.setCellValue(list.get(i).getMember() == null ? "" : (list
					.get(i).getMember().getPoliticalStatus() == null ? ""
					: list.get(i).getMember().getPoliticalStatus().getLabel()));
			cell.setCellStyle(cs2);
			// 电话邀约时间
			cell = row.createCell(10);
			cs2.setDataFormat(format.getFormat("yyyy/m/d"));
			cell.setCellStyle(cs2);
			if (list.get(i).getRecruitment() == null
					|| list.get(i).getRecruitment().getTelInvitationTime() == null) {
				//cell.setCellValue("");
			} else {
				cell.setCellValue(DateUtils.getDate(list.get(i)
						.getRecruitment().getTelInvitationTimeText(),
						"yyyy-MM-dd"));
			}
			// 面试职位
			cell = row.createCell(11);
			cell.setCellValue(list.get(i).getRecruitment() == null ? "" : list
					.get(i).getRecruitment().getTelIntentionPosition());
			cell.setCellStyle(cs2);

			// 达成面试意向
			cell = row.createCell(12);
			cell.setCellValue(list.get(i).getRecruitment() == null ? ""
					: (IntentionStatus.ENABLED.equals(list.get(i)
							.getRecruitment().getTelIntention()) ? "是" : "否"));
			cell.setCellStyle(cs2);
			// 是否参加面试
			cell = row.createCell(13);
			cell.setCellValue(list.get(i).getRecruitment() == null ? "" : (list
					.get(i).getRecruitment().isJoinInterview() ? "是" : "否"));
			cell.setCellStyle(cs2);

			// 面试时间
			cell = row.createCell(14);
			cs2.setDataFormat(format.getFormat("yyyy/m/d"));
			cell.setCellStyle(cs2);
			if (list.get(i).getRecruitment() == null
					|| list.get(i).getRecruitment().getInvitationTime() == null) {
				//cell.setCellValue("");
			} else {
				cell.setCellValue(DateUtils
						.getDate(list.get(i).getRecruitment()
								.getInvitationTimeText(), "yyyy-MM-dd"));
			}

			// 是否同意入职
			cell = row.createCell(15);
			cell.setCellValue(list.get(i).getRecruitment() == null ? ""
					: (IntentionStatus.ENABLED.equals(list.get(i)
							.getRecruitment().getAgreeEntry()) ? "是" : "否"));
			cell.setCellStyle(cs2);

			// 是否成功入职
			cell = row.createCell(16);
			cell.setCellValue(list.get(i).getRecruitment() == null ? ""
					: (IntentionStatus.ENABLED.equals(list.get(i)
							.getRecruitment().getEntrySuccess()) ? "是" : "否"));
			cell.setCellStyle(cs2);

			// 入职时间
			cell = row.createCell(17);
			cs2.setDataFormat(format.getFormat("yyyy/m/d"));
			cell.setCellStyle(cs2);
			if (list.get(i).getRecruitment() == null
					|| list.get(i).getRecruitment().getEntryDate() == null) {
				//cell.setCellValue("");
			} else {
				cell.setCellValue(DateUtils.getDate(list.get(i)
						.getRecruitment().getEntryDateTime(), "yyyy-MM-dd"));
			}

			// 离职时间
			cell = row.createCell(18);
			cs2.setDataFormat(format.getFormat("yyyy/m/d"));
			cell.setCellStyle(cs2);
			if (list.get(i).getTurnover() == null
					|| list.get(i).getTurnover().getTurnoverDate() == null) {
				//cell.setCellValue("");
			} else {
				cell.setCellValue(DateUtils.getDate(list.get(i).getTurnover()
						.getTurnoverDateTime(), "yyyy-MM-dd"));
			}

			// 离职时工作职位
			cell = row.createCell(19);
			cell.setCellValue(list.get(i).getTurnover() == null ? "" : list
					.get(i).getTurnover().getTurnoverPosition());
			cell.setCellStyle(cs2);

			// 是否有职位变化
			cell = row.createCell(20);
			cell.setCellValue(list.get(i).getWorked() == null ? "" : (list
					.get(i).getWorked().isWorkChange() ? "是" : "否"));
			cell.setCellStyle(cs2);

			// 是否正常离职
			cell = row.createCell(21);
			cell.setCellValue(list.get(i).getTurnover() == null ? "" : (list
					.get(i).getTurnover().isReasonStatus() ? "是" : "否"));
			cell.setCellStyle(cs2);

			// 离职过程是否正常
			cell = row.createCell(22);
			cell.setCellValue(list.get(i).getTurnover() == null ? "" : (list
					.get(i).getTurnover().isProcessStatus() ? "是" : "否"));
			cell.setCellStyle(cs2);

			// 离职后状态是否正常
			cell = row.createCell(23);
			cell.setCellValue(list.get(i).getTurnover() == null ? "" : (list
					.get(i).getTurnover().isProcessAfterStatus() ? "是" : "否"));
			cell.setCellStyle(cs2);
		}

	}

	private static void createCellStyle(Workbook wb, CellStyle cs, CellStyle cs2) {
		// 创建两种字体
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// 创建第一种字体样式
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.RED.getIndex());
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 创建第二种字体样式
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());
		f2.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 设置第一种单元格的样式
		cs.setFont(f);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		// cs.setDataFormat(df.getFormat("#,##0.0"));

		// 设置第二种单元格的样式
		cs2.setFont(f2);
		cs2.setBorderLeft(CellStyle.BORDER_THIN);
		cs2.setBorderRight(CellStyle.BORDER_THIN);
		cs2.setBorderTop(CellStyle.BORDER_THIN);
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
	}

	private static void setColumnWidth(Sheet sheet) {

		for (int i = 0; i < 24; i++) {
			sheet.setColumnWidth((short) i, (short) (35.7 * 150));
		}

	}

	private static void createExcelHeader(Row row, CellStyle cs) {
		// 创建列（每行里的单元格）
		Cell cell = row.createCell(0);
		cell.setCellValue("中文姓名");
		cell.setCellStyle(cs);

		cell = row.createCell(1);
		cell.setCellValue("英文姓名");
		cell.setCellStyle(cs);

		cell = row.createCell(2);
		cell.setCellValue("手机号");
		cell.setCellStyle(cs);

		cell = row.createCell(3);
		cell.setCellValue("身份证号");
		cell.setCellStyle(cs);

		cell = row.createCell(4);
		cell.setCellValue("邮箱");
		cell.setCellStyle(cs);

		cell = row.createCell(5);
		cell.setCellValue("性别");
		cell.setCellStyle(cs);

		cell = row.createCell(6);
		cell.setCellValue("出生日期");
		cell.setCellStyle(cs);

		cell = row.createCell(7);
		cell.setCellValue("毕业院校");
		cell.setCellStyle(cs);

		cell = row.createCell(8);
		cell.setCellValue("学历");
		cell.setCellStyle(cs);

		cell = row.createCell(9);
		cell.setCellValue("政治面貌");
		cell.setCellStyle(cs);

		cell = row.createCell(10);
		cell.setCellValue("电话邀约时间");
		cell.setCellStyle(cs);

		cell = row.createCell(11);
		cell.setCellValue("面试职位");
		cell.setCellStyle(cs);

		cell = row.createCell(12);
		cell.setCellValue("达成面试意向");
		cell.setCellStyle(cs);

		cell = row.createCell(13);
		cell.setCellValue("是否参加面试");
		cell.setCellStyle(cs);

		cell = row.createCell(14);
		cell.setCellValue("面试时间");
		cell.setCellStyle(cs);

		cell = row.createCell(15);
		cell.setCellValue("是否同意入职");
		cell.setCellStyle(cs);

		cell = row.createCell(16);
		cell.setCellValue("是否成功入职");
		cell.setCellStyle(cs);

		cell = row.createCell(17);
		cell.setCellValue("入职时间");
		cell.setCellStyle(cs);

		cell = row.createCell(18);
		cell.setCellValue("离职时间");
		cell.setCellStyle(cs);

		cell = row.createCell(19);
		cell.setCellValue("离职时工作职位");
		cell.setCellStyle(cs);

		cell = row.createCell(20);
		cell.setCellValue("是否有职位变化");
		cell.setCellStyle(cs);

		cell = row.createCell(21);
		cell.setCellValue("是否正常离职");
		cell.setCellStyle(cs);

		cell = row.createCell(22);
		cell.setCellValue("离职过程是否正常");
		cell.setCellStyle(cs);

		cell = row.createCell(23);
		cell.setCellValue("离职后状态是否正常");
		cell.setCellStyle(cs);
	}

	public static void _importCompanyExcel(HttpServletRequest request,
			HttpServletResponse response, List<Company> companies)
			throws IOException {

		// 获得要导出的数据集
		List<Company> list = companies;

		// 创建excel工作簿
		Workbook wb = new HSSFWorkbook();
		// 创建第一个sheet（页），并命名
		numPage++;
		Sheet sheet = wb.createSheet("第" + numPage + "页");

		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
		sheet.setColumnWidth((short) 0, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 1, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 2, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 3, (short) (35.7 * 100));
		sheet.setColumnWidth((short) 4, (short) (35.7 * 250));
		sheet.setColumnWidth((short) 5, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 6, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 7, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 8, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 9, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 10, (short) (35.7 * 150));

		// 创建第一行
		Row row = sheet.createRow((short) 0);

		// 创建两种单元格格式
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();
		// DataFormat df = wb.createDataFormat();

		// 创建两种字体
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// 创建第一种字体样式
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.RED.getIndex());
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 创建第二种字体样式
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());
		f2.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 设置第一种单元格的样式
		cs.setFont(f);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		// cs.setDataFormat(df.getFormat("#,##0.0"));

		// 设置第二种单元格的样式
		cs2.setFont(f2);
		cs2.setBorderLeft(CellStyle.BORDER_THIN);
		cs2.setBorderRight(CellStyle.BORDER_THIN);
		cs2.setBorderTop(CellStyle.BORDER_THIN);
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
		// cs2.setDataFormat(df.getFormat("text"));

		// 创建列（每行里的单元格）
		Cell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(cs);

		cell = row.createCell(1);
		cell.setCellValue("企业ID");
		cell.setCellStyle(cs);

		cell = row.createCell(2);
		cell.setCellValue("企业名称");
		cell.setCellStyle(cs);

		cell = row.createCell(3);
		cell.setCellValue("企业所在地");
		cell.setCellStyle(cs);

		cell = row.createCell(4);
		cell.setCellValue("行业");
		cell.setCellStyle(cs);

		cell = row.createCell(5);
		cell.setCellValue("规模(空)");
		cell.setCellStyle(cs);

		cell = row.createCell(6);
		cell.setCellValue("成立时间(空)");
		cell.setCellStyle(cs);

		cell = row.createCell(7);
		cell.setCellValue("本网注册时间(年月日)");
		cell.setCellStyle(cs);

		cell = row.createCell(8);
		cell.setCellValue("联系人");
		cell.setCellStyle(cs);

		cell = row.createCell(9);
		cell.setCellValue("联系人电话");
		cell.setCellStyle(cs);

		cell = row.createCell(10);
		cell.setCellValue("联系人邮箱");
		cell.setCellStyle(cs);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
				Locale.ENGLISH);

		for (short i = 0; i < list.size(); i++) {

			try {
				// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
				// 创建一行，在页sheet上
				row = sheet.createRow((short) i + 1);
				// 在row行上创建一个方格
				cell = row.createCell(0);
				cell.setCellValue(i);
				cell.setCellStyle(cs2);

				cell = row.createCell(1);
				cell.setCellValue(list.get(i).getCompanyNo());
				cell.setCellStyle(cs2);

				cell = row.createCell(2);
				cell.setCellValue(list.get(i).getCompanyName());
				cell.setCellStyle(cs2);

				cell = row.createCell(3);
				cell.setCellValue(list.get(i).getProv());
				cell.setCellStyle(cs2);

				cell = row.createCell(4);
				cell.setCellValue(list.get(i).getIndustry());
				cell.setCellStyle(cs2);

				cell = row.createCell(5);
				cell.setCellValue("暂无");
				cell.setCellStyle(cs2);

				cell = row.createCell(6);
				cell.setCellValue("暂无");
				cell.setCellStyle(cs2);

				cell = row.createCell(7);
				cell.setCellValue(DateUtils.toDateText(list.get(i)
						.getCreateTime(), "yyyy-MM-dd"));
				cell.setCellStyle(cs2);

				cell = row.createCell(8);
				cell.setCellValue(list.get(i).getContacts());
				cell.setCellStyle(cs2);

				cell = row.createCell(9);
				cell.setCellValue(list.get(i).getContactsPhone());
				cell.setCellStyle(cs2);

				cell = row.createCell(10);
				cell.setCellValue(list.get(i).getCompanyEmail());
				cell.setCellStyle(cs2);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);

		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(("company_info.xls").getBytes(), "iso-8859-1"));

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
}

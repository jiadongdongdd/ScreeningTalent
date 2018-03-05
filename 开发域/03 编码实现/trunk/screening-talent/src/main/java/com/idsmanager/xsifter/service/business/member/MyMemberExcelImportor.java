package com.idsmanager.xsifter.service.business.member;

import java.io.IOException;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.MatchUtils;
import com.idsmanager.commons.utils.PinyinUtils;
import com.idsmanager.commons.utils.excel.MemberExcelUtils;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.GenderStatus;
import com.idsmanager.xsifter.domain.IntentionStatus;
import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.log.Log;
import com.idsmanager.xsifter.domain.log.LogRepository;
import com.idsmanager.xsifter.domain.log.OperateType;
import com.idsmanager.xsifter.domain.member.Basic;
import com.idsmanager.xsifter.domain.member.Education;
import com.idsmanager.xsifter.domain.member.Entry;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.PoliticalStatus;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.domain.nation.Nation;
import com.idsmanager.xsifter.domain.origin.Origin;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.business.admin.CreditStreamCreater;
import com.idsmanager.xsifter.service.dto.menber.ErrorDataDto;
import com.idsmanager.xsifter.service.dto.menber.ImportExcelFormDto;
import com.idsmanager.xsifter.service.dto.menber.ImportExcelResultDto;

public class MyMemberExcelImportor {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyMemberExcelImportor.class);

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private transient CompanyRepository companyRepository = BeanProvider
			.getBean(CompanyRepository.class);

	private transient UserRepository userRepository = BeanProvider
			.getBean(UserRepository.class);

	private transient LogRepository logRepository = BeanProvider
			.getBean(LogRepository.class);

	private ImportExcelFormDto formDto;

	private Basic basic = null;// 基本信息
	private Education education = null;// 教育信息
	private Recruitment recruitment = null;// 招聘信息
	private Turnover turnover = null;// 离职环节
	private Entry entry = null;
	private Worked worked = null;

	private List<Basic> basicList = null;
	private List<Education> eduList = null;
	private List<Recruitment> recList = null;
	private List<Turnover> turnList = null;
	private List<Entry> enList = null;
	private List<Worked> woList = null;

	ImportExcelResultDto resultDto = null;

	public MyMemberExcelImportor(ImportExcelFormDto formDto) {
		super();
		this.formDto = formDto;
	}

	public ImportExcelResultDto importExcel() {
		final MultipartFile file = formDto.getExcelFile();
		resultDto = new ImportExcelResultDto();
		try {
			importExcel2003(file, resultDto);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		LOG.debug("{}|Import Excel result: {}", SecurityUtils.username(),
				resultDto.isResult());
		return resultDto;
	}

	private void createImportLog() {
		Log log = new Log().setOperator(SecurityUtils.username())
				.setOperatorId(SecurityUtils.currentUserGuid())
				.setOperateType(OperateType.IMPORT_MEMBER_EXCEL)
				.setOperateDetail(OperateType.IMPORT_MEMBER_EXCEL.getLabel());
		this.logRepository.saveLog(log);
	}

	private void importExcel2003(MultipartFile file,
			ImportExcelResultDto resultDto) throws IOException {
		initial();
		// 错误信息
		List<ErrorDataDto> errors = new ArrayList<>();
		HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
		int sheetsNum = workbook.getNumberOfSheets();
		handleSheet(sheetsNum, workbook, errors);
		resultDto.setErrorDatas(errors);

		if (resultDto.isResult()) {
			saveEntity();
			createImportLog();
		}

	}

	// 初始化保存list
	private void initial() {
		basicList = new ArrayList<>();
		eduList = new ArrayList<>();
		recList = new ArrayList<>();
		turnList = new ArrayList<>();
		enList = new ArrayList<>();
		woList = new ArrayList<>();
	}

	private void handleSheet(int sheetsNum, HSSFWorkbook workbook,
			List<ErrorDataDto> errors) {
		for (int sheetNum = 0; sheetNum < sheetsNum; sheetNum++) {
			HSSFSheet sheet = workbook.getSheetAt(sheetNum);
			if (sheet.isActive()) {
				handleRow(sheet, errors);
			}
		}
	}

	private void handleRow(HSSFSheet sheet, List<ErrorDataDto> errors) {
		for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
			if (rowNum > 0) {
				HSSFRow row = sheet.getRow(rowNum);
				if (null != row && null != row.getCell(0)) {
					handleCell(row, errors);
				}
			}
		}
	}

	private void handleCell(HSSFRow row, List<ErrorDataDto> errors) {
		putDataForBasicAndEducation();
		ErrorDataDto errorDataDto = new ErrorDataDto();
		List<Integer> columns = new ArrayList<>();
		for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
			try {
				HSSFCell cell = row.getCell(cellNum);
				createEntities(cellNum);
				String cellValue = MemberExcelUtils.getCellValue(cell);
				putDataForAllEntities(row.getRowNum(), cellValue, cellNum,
						columns);
			} catch (Exception e) {
				addErrorData(cellNum, columns);
			}
		}

		if (!resultDto.isResult() && (null != columns && columns.size() > 0)) {
			errorDataDto.setRow(row.getRowNum() + 1);
			errorDataDto.setColumn(columns);
			errors.add(errorDataDto);
		}
		addDataForList();
	}

	// list中添加数据
	private void addDataForList() {
		basicList.add(basic);
		eduList.add(education);
		recList.add(recruitment);
		turnList.add(turnover);
		woList.add(worked);
		enList.add(entry);
	}

	// 保存数据
	private void saveEntity() {
		if (null != basicList && basicList.size() > 0) {
			for (int i = 0; i < basicList.size(); i++) {
				Member member = this.memberRepository
						.findMemberByBasic(basicList.get(i));
				if (member == null) {
					this.memberRepository.saveBasic(basicList.get(i));
					this.memberRepository.saveEducation(eduList.get(i));
					Member member2 = new Member(basicList.get(i),
							eduList.get(i));
					this.memberRepository.saveMember(member2);
					if (recList.get(i) != null) {
						this.memberRepository.saveRecruitment(recList.get(i));
					}
					if (turnList.get(i) != null) {
						this.memberRepository.saveTurnover(turnList.get(i));
					}
					if (woList.get(i) != null) {
						this.memberRepository.saveWorked(woList.get(i));
					}
					if (enList.get(i) != null) {
						this.memberRepository.saveEntry(enList.get(i));
					}
					addCredit(member2);
				}
			}
		}

	}

	// 增加积分
	private void addCredit(Member member) {
		User user = this.userRepository.findByGuid(SecurityUtils
				.currentUserGuid());

		if (user == null) {
			CreditStreamCreater streamCreater = new CreditStreamCreater(
					new CreditStreamHolder(member.getCompanyGuid(), "添加职工 _"
							+ member.getChName(), CreditAction.ADD));
			try {
				streamCreater.create();
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
	}

	private String handleStringValue(String cellValue) {
		return "".equals(cellValue) ? "" : cellValue;
	}

	// 增加错误信息
	private void addErrorData(int cellNum, List<Integer> columns) {
		resultDto.setResult(false);
		columns.add(cellNum + 1);
	}

	// 填充数据
	private void putDataForAllEntities(int rowNum, String cellValue,
			int cellNum, List<Integer> columns) {
		switch (cellNum) {
		case 0:
			if (MatchUtils.isChName(cellValue)) {
				basic.setChName(handleStringValue(cellValue));
				basic.setPinyin(PinyinUtils.memberNameToPinyin(cellValue));
			} else {
				addErrorData(cellNum, columns);
			}
			break;
		case 1:
			basic.setEnName(handleStringValue(cellValue));
			break;
		case 2:
			if (MatchUtils.isMobile(cellValue)) {
				basic.setMobile(handleStringValue(cellValue));
			} else {
				addErrorData(cellNum, columns);
			}
			break;
		case 3:
			if (MatchUtils.isIdNumber(cellValue)) {
				basic.setIdNumber(handleStringValue(cellValue));
			} else if (!"".equals(cellValue)) {
				addErrorData(cellNum, columns);
			}
			break;
		case 4:
			if (MatchUtils.isEmail(cellValue)) {
				basic.setEmail(handleStringValue(cellValue));
			} else if (!"".equals(cellValue)) {
				addErrorData(cellNum, columns);
			}
			break;

		case 5:
			basic.setGender(("女".equals(handleStringValue(cellValue)) ? GenderStatus.FEMALE
					: GenderStatus.MALE));
			education
					.setGender(("女".equals(handleStringValue(cellValue)) ? GenderStatus.FEMALE
							: GenderStatus.MALE));
			break;
		case 6:
			String value = handleStringValue(cellValue);
			if (MatchUtils.isDate(value)) {
				basic.setBirthday(DateUtils.getDate(value));
			}else if(MatchUtils.isDateTwo(value)){
				basic.setBirthday(DateUtils.getDateTwo(value));
			} else if (!"".equals(value)) {
				addErrorData(cellNum, columns);
			}
			break;
		case 7:
			education.setSchool(handleStringValue(cellValue));
			break;
		case 8:
			MemberExcelUtils.setDegree(education, handleStringValue(cellValue));
			break;
		case 9:
			MemberExcelUtils.setPolitical(education,
					handleStringValue(cellValue));
			break;
		case 10:
			String value2 = handleStringValue(cellValue);
			if (MatchUtils.isDate(value2)) {
				recruitment.setTelInvitationTime(DateUtils.getDate(value2));
			}else if(MatchUtils.isDateTwo(value2)){
				recruitment.setTelInvitationTime(DateUtils.getDateTwo(value2));
			} else if (!"".equals(value2)) {
				addErrorData(cellNum, columns);
			}
			break;
		case 11:
			recruitment.setTelIntentionPosition(handleStringValue(cellValue));
			break;
		case 12:
			recruitment
					.setTelIntention("是".equals(handleStringValue(cellValue)) ? IntentionStatus.ENABLED
							: IntentionStatus.DISABLED);
			break;
		case 13:
			recruitment.setJoinInterview("是"
					.equals(handleStringValue(cellValue)) ? true : false);
			break;
		case 14:
			String value3 = handleStringValue(cellValue);
			if (MatchUtils.isDate(value3)) {
				recruitment.setInvitationTime(DateUtils.getDate(value3));
			}else if(MatchUtils.isDateTwo(value3)){
				recruitment.setInvitationTime(DateUtils.getDateTwo(value3));
			} else if (!"".equals(value3)) {
				addErrorData(cellNum, columns);
			}
			break;
		case 15:
			recruitment
					.setAgreeEntry("是".equals(handleStringValue(cellValue)) ? IntentionStatus.ENABLED
							: IntentionStatus.DISABLED);
			break;
		case 16:
			recruitment
					.setEntrySuccess("是".equals(handleStringValue(cellValue)) ? IntentionStatus.ENABLED
							: IntentionStatus.DISABLED);
			break;
		case 17:
			String value4 = handleStringValue(cellValue);
			if (MatchUtils.isDate(value4)) {
				recruitment.setEntryDate(DateUtils.getDate(value4));
				entry.setEntryDate(DateUtils.getDate(value4));
			}else if(MatchUtils.isDateTwo(value4)){
				recruitment.setEntryDate(DateUtils.getDateTwo(value4));
				entry.setEntryDate(DateUtils.getDateTwo(value4));
			} else if (!"".equals(value4)) {
				addErrorData(cellNum, columns);
			}
			break;
		case 18:
			String value5 = handleStringValue(cellValue);
			if (MatchUtils.isDate(value5)) {
				turnover.setTurnoverDate(DateUtils.getDate(value5));
			}else if(MatchUtils.isDateTwo(value5)){
				turnover.setTurnoverDate(DateUtils.getDateTwo(value5));
			} else if (!"".equals(value5)) {
				addErrorData(cellNum, columns);
			}
			break;
		case 19:
			turnover.setTurnoverPosition(handleStringValue(cellValue));
			break;
		case 20:
			worked.setWorkChange("是".equals(handleStringValue(cellValue)) ? true
					: false);
			break;
		case 21:
			turnover.setReasonStatus("是".equals(handleStringValue(cellValue)) ? true
					: false);
			break;
		case 22:
			turnover.setProcessStatus("是".equals(handleStringValue(cellValue)) ? true
					: false);
			break;
		case 23:
			turnover.setProcessAfterStatus("是"
					.equals(handleStringValue(cellValue)) ? true : false);
			entry.setWorked(worked);
			entry.setTurnover(turnover);
			break;
		case 24:
			education.setMajor("" == cellValue  || null == cellValue ? "无":cellValue);
			break;
		case 25:
			Nation nation = Nation.getValue(cellValue);
			education.setNation(nation);
			break;
		case 26:
			Origin origin = Origin.getValue(cellValue);
			education.setOrigin(origin);
			break;
		default:
			break;
		}
	}

	private void putDataForBasicAndEducation() {
		basic = new Basic();// 基本信息
		String guid = SecurityUtils.currentUserGuid();
		Company company = this.companyRepository.findByGuid(guid);
		User user = this.userRepository.findByGuid(guid);
		basic.setCompanyGuid(SecurityUtils.currentUserGuid());
		basic.setCompanyUsername(SecurityUtils.username());
		if (user != null) {
			basic.setCompanyName(user.getUsername());
		}

		if (company != null) {
			basic.setCompanyName(company.getCompanyName());
		}
		education = new Education(basic);// 教育信息
		education.setCompanyGuid(SecurityUtils.currentUserGuid());
	}

	private void createEntities(int cellNum) {
		if (cellNum == 7) {
			recruitment = new Recruitment(basic);
			turnover = new Turnover(basic);
			worked = new Worked(basic);
			entry = new Entry(turnover);
		}
	}
}

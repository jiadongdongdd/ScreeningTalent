package com.idsmanager.xsifter.service.business.company;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.PasswordHandler;
import com.idsmanager.commons.web.context.BeanProvider;

import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.domain.commons.IdsFile;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompany;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompanyRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.service.business.admin.CreditNotEnoughException;
import com.idsmanager.xsifter.service.business.admin.CreditStreamCreater;
import com.idsmanager.xsifter.service.dto.company.CompanyFormDto;
import com.idsmanager.xsifter.service.dto.company.CompanyReSetFormDto;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 类名称 创建人 dushaofei 创建时间：2016-1-25 上午11:38:23 类描述：
 */
public class CreateCompanyHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(CreateCompanyHandler.class);

	private transient CompanyRepository comPanyDao = BeanProvider
			.getBean(CompanyRepository.class);

	private transient InviteCompanyRepository inviteDao = BeanProvider
			.getBean(InviteCompanyRepository.class);

	private CompanyFormDto formDto;

	private CompanyReSetFormDto setFormDto;

	public CreateCompanyHandler(CompanyReSetFormDto setFormDto) {
		this.setFormDto = setFormDto;
	}

	public CreateCompanyHandler(CompanyFormDto formDto) {
		this.formDto = formDto;
	}

	public String handle() {

		final String username = formDto.getUsername();
		final String password = PasswordHandler.encryptPassword(
				formDto.getPassword(), username);
		formDto.setPassword(password);
		final Company company = newCompany(formDto);
		// 生成企业编号Id
		long companyNum = comPanyDao.countCompany();
		String companyNo = gengerCompanyNo(formDto.getCompanyName(),
				formDto.getOperator(), companyNum);
		company.setCompanyNo(companyNo)
				.setCreateUserUuid(SecurityUtils.currentUserGuid())
				.setLevel("A");

		handleFiles(company);

		comPanyDao.saveCompany(company);

		// 修改邀请企业状态为已同意 .
		updateInviteState(company.getCompanyName());
		LOG.debug("{}|Create Company: {}", SecurityUtils.username(), company);

		return company.guid();
	}

	public String handleReSet() {

		final String username = setFormDto.getUsername();
		final String password = PasswordHandler.encryptPassword(
				setFormDto.getPassword(), username);
		setFormDto.setPassword(password);
		final Company company = newReSetCompany(setFormDto);

		handleReSetFiles(company);

		comPanyDao.saveCompany(company);

		// 修改邀请企业状态为已同意 .
		updateInviteState(company.getCompanyName());
		LOG.debug("{}|Create Company: {}", SecurityUtils.username(), company);

		return company.guid();
	}

	private void handleReSetFiles(Company company) {
		final MultipartFile urlbusi = setFormDto.getUrlbusi();
		final MultipartFile urlauth = setFormDto.getUrlauth();
		try {
			IdsFile file = new IdsFile(urlauth.getOriginalFilename(),
					urlauth.getBytes());
			file.save();
			company.setAuthorityPath(file.getGuid());

			IdsFile urlFile = new IdsFile(urlbusi.getOriginalFilename(),
					urlbusi.getBytes());
			urlFile.save();
			company.setBusinessPath(urlFile.getGuid());
			LOG.debug("upload company img:{}", company.getCompanyName());
		} catch (IOException e) {
			throw new IllegalStateException("Upload error", e);
		}
	}

	private Company newReSetCompany(CompanyReSetFormDto dto) {
		Company com = new Company().setUsername(dto.getUsername())
				.setPassword(dto.getPassword())
				.setCompanyName(dto.getCompanyName())
				.setCompanyEmail(dto.getCompanyEmail())
				.setContacts(dto.getContacts())
				.setContactsPhone(dto.getContactsPhone())
				.setGuid(dto.getGuid()).setCity(dto.getCity())
				.setProv(dto.getProv()).setIndustry(dto.getIndustry());
		if (null != dto.getDist()) {
			com.setDist(dto.getDist());
		}
		com.setVerifyState(false);
		com.setVerifyPass(false);
		return com;
	}

	public Company newCompany(CompanyFormDto dto) {
		// String city = null;
		// String dist = null;
		// String prov = null;
		// String username = null;
		// String companyName = null;
		// String contacts = null;
		// try {
		// city = new String(dto.getCity().getBytes("ISO-8859-1"),"UTF-8");
		//
		// if(null != dto.getDist()) dist = new
		// String(dto.getDist().getBytes("ISO-8859-1"),"UTF-8");
		// prov = new String(dto.getProv().getBytes("ISO-8859-1"),"UTF-8");
		// username = new
		// String(dto.getUsername().getBytes("ISO-8859-1"),"UTF-8");
		// companyName = new
		// String(dto.getCompanyName().getBytes("ISO-8859-1"),"UTF-8");
		// contacts = new
		// String(dto.getContacts().getBytes("ISO-8859-1"),"UTF-8");
		//
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		Company com = new Company().setUsername(dto.getUsername())
				.setPassword(dto.getPassword())
				.setCompanyName(dto.getCompanyName())
				.setCompanyEmail(dto.getCompanyEmail())
				.setContacts(dto.getContacts())
				.setContactsPhone(dto.getContactsPhone())
				.setGuid(dto.getGuid()).setCity(dto.getCity())
				.setProv(dto.getProv()).setIndustry(dto.getIndustry());
		if (null != dto.getDist()) {
			com.setDist(dto.getDist());
		}
		// when admin add company pass state is true
		List<Privilege> privileges = SecurityUtils.privileges();

		if (null != privileges
				&& privileges.toString().contains("[ROLE_ADMIN]")) {
			com.setVerifyPass(true);
			com.setVerifyState(true);
		}
		return com;
	}

	private void updateInviteState(String companyName) {
		InviteCompany inviteCompany = inviteDao.findBycompanyName(companyName);
		if (null != inviteCompany) {
			inviteCompany.setInviteState(true);
			inviteDao.updateInviteCompany(inviteCompany);
			LOG.debug("update invite State companyName:{}", companyName);
		}

	}

	/**
	 * 生成企业编号 thinkpad dushaofei
	 * 
	 * @param companyName
	 * @return
	 */
	public String gengerCompanyNo(String companyName, String operrator,
			long companyNum) {
		Date date = DateUtils.now();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dateStr = format.format(date);
		int num = Integer.parseInt(String.valueOf(companyNum));
		String number = getNumber(num);
		String result = null;
		if (operrator.equalsIgnoreCase("A")) {
			result = dateStr + "A" + number;
		} else if (operrator.equalsIgnoreCase("I")) {
			result = dateStr + "I" + number;
		}
		System.out.println(dateStr + operrator + number);
		return result;
	}

	public static void main(String[] args) {
		Date date = DateUtils.now();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dateStr = format.format(date);
		long companyNum = 16;
		int num = Integer.parseInt(String.valueOf(companyNum));
		String number = getNumber(num);
		System.out.println(dateStr + "I" + number);
	}

	public static String getNumber(int num) {
		num++;
		String result = "";
		switch ((num + "").length()) {
		case 1:
			result = "000" + num;
			break;
		case 2:
			result = "00" + num;
			break;
		case 3:
			result = "0" + num;
			break;
		case 4:
			result = "" + num;
			break;
		// 此处代表编号已经超过了9999，从0重新开始
		default:
			result = "" + num;
			break;
		}
		return result;
	}

	private void handleFiles(Company company) {

		final MultipartFile urlbusi = formDto.getUrlbusi();
		final MultipartFile urlauth = formDto.getUrlauth();
		try {
			IdsFile file = new IdsFile(urlauth.getOriginalFilename(),
					urlauth.getBytes());
			file.save();
			company.setAuthorityPath(file.getGuid());

			IdsFile urlFile = new IdsFile(urlbusi.getOriginalFilename(),
					urlbusi.getBytes());
			urlFile.save();
			company.setBusinessPath(urlFile.getGuid());
			LOG.debug("upload company img:{}", company.getCompanyName());
		} catch (IOException e) {
			throw new IllegalStateException("Upload error", e);
		}

	}
}

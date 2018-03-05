package com.idsmanager.xsifter.service.dto.menber;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.PinyinUtils;
import com.idsmanager.xsifter.domain.GenderStatus;
import com.idsmanager.xsifter.domain.member.Basic;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberProcess;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

public class MemberFormOneDto implements Serializable {

	private static final long serialVersionUID = 7622862946254123250L;

	private String uuid;
	private String chName;
	private String enName;
	private String mobile;
	private String idNumber;
	private String email;
	private String gender;
	private String birthday;

	private boolean next;

	public MemberFormOneDto(Member member) {
		this.uuid = member.getUuid();
		this.chName = member.getChName();
		this.enName = member.getEnName();
		this.mobile = member.getMobile();
		this.idNumber = member.getIdNumber();
		this.email = member.getEmail();
		this.birthday = member.getBirthday();
	}

	public MemberFormOneDto() {
	}

	public MemberFormOneDto(Basic basic) {
		this.uuid = basic.getUuid();
		this.chName = basic.getChName();
		this.enName = basic.getEnName();
		this.mobile = basic.getMobile();
		this.idNumber = basic.getIdNumber();
		this.email = basic.getEmail();
		this.birthday = DateUtils.toDateText(basic.getBirthday(), "yyyy-MM-dd");
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Member create() {

		final Member member = new Member().setChName(chName).setEnName(enName)
				.setMobile(mobile).setIdNumber(idNumber).setEmail(email)
				.setPinyin(PinyinUtils.memberNameToPinyin(chName))
				.setMemberProcess(MemberProcess.NORMAL)
				.setCompanyGuid(SecurityUtils.currentUserGuid());

		setGender(member);
		return member;
	}

	private void setGender(Member member) {
		if (StringUtils.isNotEmpty(idNumber)) {
			boolean b = isMale(idNumber);
			member.setGender(b ? GenderStatus.MALE : GenderStatus.FEMALE);
		}
	}

	public Member update(Member member) {

		member.setChName(chName).setEnName(enName).setMobile(mobile)
				.setIdNumber(idNumber).setEmail(email)
				.setPinyin(PinyinUtils.memberNameToPinyin(chName));

		setGender(member);

		return member;
	}

	private boolean isMale(String idNumber) {

		if (idNumber.length() == 15) {
			String flag = idNumber.substring(14, 15);
			if (Integer.valueOf(flag) % 2 == 1) {
				return true;
			}
		}

		if (idNumber.length() == 18) {
			String flag = idNumber.substring(16, 17);
			if (Integer.valueOf(flag) % 2 == 1) {
				return true;
			}
		}

		return false;

	}

	public Basic updateBasic(Basic basic) {
		basic.setChName(chName).setEmail(email).setEnName(enName)
				.setIdNumber(idNumber).setMobile(mobile)
				.setPinyin(PinyinUtils.memberNameToPinyin(chName));
		setBasicGender(basic);
		return basic;
	}

	private void setBasicGender(Basic basic) {
		if (StringUtils.isNotEmpty(idNumber)) {
			boolean b = isMale(idNumber);
			basic.setGender(b ? GenderStatus.MALE : GenderStatus.FEMALE);
			// 计算年龄
			if (idNumber.length() == 15) {
				int age = getBasicAge15(idNumber);
				basic.setAge(age);
			}

			if (idNumber.length() == 18) {
				int age = getBasicAge18(idNumber);
				basic.setAge(age);
			}

			Date birthday = getBasicBirthday(idNumber);

			basic.setBirthday(birthday);

		}
	}

	private Date getBasicBirthday(String idNumber) {
		if (idNumber.length() == 15) {
			String birthdayStr = idNumber.substring(6, 12);
			return DateUtils.getDate("19" + birthdayStr, "yyyyMMdd");
		}

		if (idNumber.length() == 18) {
			String birthdayStr = idNumber.substring(6, 14);
			return DateUtils.getDate(birthdayStr, "yyyyMMdd");
		}

		return DateUtils.now();

	}

	private int getBasicAge18(String idNumber2) {

		String year = idNumber2.substring(6, 10);
		String month = idNumber2.substring(10, 12);
		String day = idNumber2.substring(12, 14);

		String now = DateUtils.toDateText(DateUtils.now(), "yyyyMMdd");

		String nowYear = now.substring(0, 4);
		String nowMonth = now.substring(4, 6);
		String nowDay = now.substring(6, 8);

		Integer nowYearNum = Integer.valueOf(nowYear);
		Integer yearNum = Integer.valueOf(year);

		Integer ageTemp = nowYearNum - yearNum;

		if (nowMonth.compareTo(month) < 0) {
			return ageTemp - 1;
		}

		if (nowMonth.compareTo(month) == 0) {
			if (nowDay.compareTo(day) == -1) {
				return ageTemp - 1;
			} else {
				return ageTemp;
			}
		}

		if (nowMonth.compareTo(month) > 0) {
			return ageTemp;
		}

		return 0;
	}

	private int getBasicAge15(String idNumber2) {

		String year = idNumber2.substring(6, 8);
		String month = idNumber2.substring(8, 10);
		String day = idNumber2.substring(10, 12);

		String now = DateUtils.toDateText(DateUtils.now(), "yyyyMMdd");

		String nowYear = now.substring(0, 4);
		String nowMonth = now.substring(4, 6);
		String nowDay = now.substring(6, 8);

		Integer nowYearNum = Integer.valueOf(nowYear);
		Integer yearNum = Integer.valueOf("19" + year);

		Integer ageTemp = nowYearNum - yearNum;

		if (nowMonth.compareTo(month) == -1) {
			return ageTemp - 1;
		}

		if (nowMonth.compareTo(month) == 0) {
			if (nowDay.compareTo(day) == -1) {
				return ageTemp - 1;
			} else {
				return ageTemp;
			}
		}

		if (nowMonth.compareTo(month) == 1) {
			return ageTemp;
		}
		

		return 0;
	}

	public Basic createBasic() {
		final Basic basic = new Basic().setChName(chName)
				.setCompanyGuid(SecurityUtils.currentUserGuid())
				.setEmail(email).setEnName(enName).setIdNumber(idNumber)
				.setMobile(mobile)
				.setPinyin(PinyinUtils.memberNameToPinyin(chName))
				.setCompanyUsername(SecurityUtils.username());
		setBasicGender(basic);
		return basic;
	}

}

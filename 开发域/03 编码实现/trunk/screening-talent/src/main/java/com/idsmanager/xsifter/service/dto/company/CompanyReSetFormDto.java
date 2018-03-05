package com.idsmanager.xsifter.service.dto.company;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.UUIDGenerator;
import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.Industry;
import com.idsmanager.xsifter.domain.company.VerifyReason;

import org.springframework.web.multipart.MultipartFile;


public class CompanyReSetFormDto implements Serializable {
	private static final long serialVersionUID = 2825537534246252743L;

	private String guid = UUIDGenerator.generate();

	private String username;

	private String password;

	private String rePassword;

	private String companyName;// 企业名称

	private String companyEmail;// 企业邮箱

	private String contacts;// 联系人

	private String contactsPhone;// 联系人电话

	private String businessPath;// 营业执照路径

	private String authorityPath;// 机构证书路径

	public Company company;

    private MultipartFile urlbusi;

    private MultipartFile urlauth;

    private String urlbusiImg;//validate info
    
    private String urlauthImg;
    
    private String operator;//操作人员
    
  	//行业
  	//private Industry industry;
    private String industry;
  //区域
  	private String prov;
  	
  	private String city;
  	
  	private String dist;
  	
  	private String fall_password;
  	
  	private String re_fall_password;
  	
  	private String fall_img1;
  	
  	private String fall_img2;
  	
	public CompanyReSetFormDto(Company company) {
		this.company = company;
	}

	public CompanyReSetFormDto() {
	}

	public CompanyReSetFormDto(String username, String password, String companyName,
			String companyEmail, String contacts, String contactsPhone,
			String businessPath, String authorityPath) {
		super();
		this.username = username;
		this.password = password;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.contacts = contacts;
		this.contactsPhone = contactsPhone;
		this.businessPath = businessPath;
		this.authorityPath = authorityPath;
	}

    public MultipartFile getUrlbusi() {
        return urlbusi;
    }

    public void setUrlbusi(MultipartFile urlbusi) {
        this.urlbusi = urlbusi;
    }

    public MultipartFile getUrlauth() {
        return urlauth;
    }

    public void setUrlauth(MultipartFile urlauth) {
        this.urlauth = urlauth;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getBusinessPath() {
		return businessPath;
	}

	public void setBusinessPath(String businessPath) {
		this.businessPath = businessPath;
	}

	public String getAuthorityPath() {
		return authorityPath;
	}

	public void setAuthorityPath(String authorityPath) {
		this.authorityPath = authorityPath;
	}


	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getUrlbusiImg() {
		return urlbusiImg;
	}

	public void setUrlbusiImg(String urlbusiImg) {
		this.urlbusiImg = urlbusiImg;
	}

	public String getUrlauthImg() {
		return urlauthImg;
	}

	public void setUrlauthImg(String urlauthImg) {
		this.urlauthImg = urlauthImg;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	
	public Industry[] getIndustries(){
		return Industry.values();
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getFall_password() {
		return fall_password;
	}

	public void setFall_password(String fall_password) {
		this.fall_password = fall_password;
	}

	public String getRe_fall_password() {
		return re_fall_password;
	}

	public void setRe_fall_password(String re_fall_password) {
		this.re_fall_password = re_fall_password;
	}

	public String getFall_img1() {
		return fall_img1;
	}

	public void setFall_img1(String fall_img1) {
		this.fall_img1 = fall_img1;
	}

	public String getFall_img2() {
		return fall_img2;
	}

	public void setFall_img2(String fall_img2) {
		this.fall_img2 = fall_img2;
	}
	
}

package com.idsmanager.xsifter.service.business.company;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.company.DeleteCompany;
import com.idsmanager.xsifter.domain.company.DeleteCompanyRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.service.dto.company.DeleteCompanyFormDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * 类名称 创建人 dushaofei 创建时间：2016-1-25 上午11:38:23 类描述：
 */
public class DeleteCreateCompanyHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(DeleteCreateCompanyHandler.class);

	private transient DeleteCompanyRepository comPanyDao = BeanProvider
			.getBean(DeleteCompanyRepository.class);


	private DeleteCompanyFormDto formDto;
	
	private DeleteCompany company;
	
	public DeleteCreateCompanyHandler(DeleteCompany company) {
		super();
		this.company = company;
	}

	public DeleteCreateCompanyHandler(DeleteCompanyFormDto formDto) {
		this.formDto = formDto;
	}

	public DeleteCreateCompanyHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void handle() { 
		 comPanyDao.savedeleteCompany(newdeleteCompany(formDto));
	}

	public void handleEntity() { 
		 comPanyDao.savedeleteCompany(company);
	}
	
	public DeleteCompany newdeleteCompany(DeleteCompanyFormDto dto) {
		DeleteCompany com = new DeleteCompany()
				.setGuid(dto.getGuid())
				.setUsername(dto.getUsername())
				.setPassword(dto.getPassword())
				.setCompanyName(dto.getCompanyName())
				.setCompanyEmail(dto.getCompanyEmail())
				.setContacts(dto.getContacts())
				.setContactsPhone(dto.getContactsPhone())
				.setGuid(dto.getGuid()).setCity(dto.getCity())
				.setProv(dto.getProv()).setIndustry(dto.getIndustry());
				com.setDist(dto.getDist());
				com.setVerifyPass(dto.getVerifyPass());
				com.setVerifyState(dto.getVerifyState());
		return com;
	}

	public DeleteCompany getCompany() {
		return company;
	}

	public void setCompany(DeleteCompany company) {
		this.company = company;
	}
	

}

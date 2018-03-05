package com.idsmanager.xsifter.service.business.invite;

import java.util.List;
import java.util.Map;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.commons.web.context.BeanProvider;

import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompany;
import com.idsmanager.xsifter.domain.inviteCompany.InviteCompanyRepository;
import com.idsmanager.xsifter.service.dto.company.CompanyFormDto;
import com.idsmanager.xsifter.service.dto.company.CompanyPaginated;
import com.idsmanager.xsifter.service.dto.invite.InviteCompanyPaginated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 类名称 创建人 dushaofei 创建时间：2016-1-25 上午11:38:23 类描述：
 * 
 * @version
 */
public class LoadInviteCompanyHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(LoadInviteCompanyHandler.class);

	private transient InviteCompanyRepository comPanyDao = BeanProvider
			.getBean(InviteCompanyRepository.class);

	private InviteCompanyPaginated paginated;

	private String companyName;

	private String username;

	public LoadInviteCompanyHandler(InviteCompanyPaginated paginated) {
		this.paginated = paginated;
	}

	public InviteCompanyPaginated load() {
		final Map<String, Object> map = paginated.queryMap();

		return paginated.load(new PaginatedLoader<InviteCompany>() {

			@Override
			public long loadTotalSize() {
				return comPanyDao.totalInviteCompanyList(map);
			}

			@Override
			public List<InviteCompany> loadList() {
				return comPanyDao.findCompanyList(map);
			}
		});
	}

	public InviteCompanyRepository getComPanyDao() {
		return comPanyDao;
	}

	public void setComPanyDao(InviteCompanyRepository comPanyDao) {
		this.comPanyDao = comPanyDao;
	}

	public InviteCompanyPaginated getPaginated() {
		return paginated;
	}

	public void setPaginated(InviteCompanyPaginated paginated) {
		this.paginated = paginated;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

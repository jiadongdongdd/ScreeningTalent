package com.idsmanager.xsifter.service.business.company;

import java.util.List;
import java.util.Map;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.commons.web.context.BeanProvider;

import com.idsmanager.xsifter.domain.company.DeleteCompany;
import com.idsmanager.xsifter.domain.company.DeleteCompanyRepository;
import com.idsmanager.xsifter.service.dto.company.DeleteCompanyPaginated;

/**
 * 
 * 类名称 创建人 dushaofei 创建时间：2016-1-25 上午11:38:23 类描述：
 * 
 * @version
 */
public class LoadDeleteCompanyHandler {

	private transient DeleteCompanyRepository comPanyDao = BeanProvider
			.getBean(DeleteCompanyRepository.class);

	private DeleteCompanyPaginated paginated;
	

	public LoadDeleteCompanyHandler(DeleteCompanyPaginated paginated) {
		super();
		this.paginated = paginated;
	}

	public LoadDeleteCompanyHandler() {
		super();
		// TODO Auto-generated constructor stub
	}



	public DeleteCompanyPaginated load() {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<DeleteCompany>() {

			@Override
			public long loadTotalSize() {
				return comPanyDao.totaldeleteCompanyList(map);
			}

			@Override
			public List<DeleteCompany> loadList() {
				return comPanyDao.finddeleteCompanyList(map);
			}
		});
	}

}

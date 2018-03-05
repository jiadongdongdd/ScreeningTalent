package com.idsmanager.xsifter.service.business.memberexcep;

import java.util.List;
import java.util.Map;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.commons.web.context.BeanProvider;

import com.idsmanager.xsifter.domain.timer.MemberExcep;
import com.idsmanager.xsifter.domain.timer.MemberExcepRepository;
import com.idsmanager.xsifter.service.dto.memberexcep.MemberExcepFormDto;
import com.idsmanager.xsifter.service.dto.memberexcep.MemberExcepPaginated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 
 * 类名称 创建人 dushaofei 创建时间：2016-1-25 上午11:38:23 类描述：
 * 
 * @version
 */
public class LoadMemberExcepHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(LoadMemberExcepHandler.class);

	private transient MemberExcepRepository memberExcepRepository  = BeanProvider
			.getBean(MemberExcepRepository.class);

	private MemberExcepPaginated  paginated;

	private String uuid;

	public LoadMemberExcepHandler(MemberExcepPaginated paginated) {
		super();
		this.paginated = paginated;
	}

	public LoadMemberExcepHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberExcepPaginated load() {
		final Map<String, Object> map = paginated.queryMap();

		return paginated.load(new PaginatedLoader<MemberExcep>() {

			@Override
			public long loadTotalSize() {
				return memberExcepRepository.totalMemberExcepList(map);
			}

			@Override
			public List<MemberExcep> loadList() {
				return memberExcepRepository.findMemberExcepList(map);
			}
		});
	}

	public MemberExcepFormDto loadOne(){
		if(!StringUtils.isEmpty(uuid)){
			return new MemberExcepFormDto(memberExcepRepository.findMemberExcepByUuid(uuid));
		}
		return null;
	}



	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}

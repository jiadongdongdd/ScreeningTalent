package com.idsmanager.xsifter.service.business.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.commons.web.context.BeanProvider;

import com.idsmanager.xsifter.domain.company.Company;
import com.idsmanager.xsifter.domain.company.CompanyRepository;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberInfos;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.dto.company.CompanyFormDto;
import com.idsmanager.xsifter.service.dto.company.CompanyPaginated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 类名称 创建人 dushaofei 创建时间：2016-1-25 上午11:38:23 类描述：
 * 
 * @version
 */
public class LoadCompanyHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(LoadCompanyHandler.class);

	private transient UserRepository userRepository = BeanProvider
			.getBean(UserRepository.class);

	private transient CompanyRepository comPanyDao = BeanProvider
			.getBean(CompanyRepository.class);

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private CompanyPaginated paginated;

	private CompanyFormDto formDto;

	private String companyName;

	private String username;

	public LoadCompanyHandler(CompanyPaginated paginated) {
		this.paginated = paginated;
	}

	public CompanyFormDto loadOne() {
		Company com = comPanyDao.findByCompanyName(companyName);
		CompanyFormDto dto = new CompanyFormDto(com);
		return dto;
	}

	public CompanyFormDto loadOneByUsername() {
		Company com = comPanyDao.findByUsername(username);
		CompanyFormDto dto = new CompanyFormDto(com);
		return dto;
	}

	public CompanyPaginated load(Boolean verifyState) {
		final Map<String, Object> map = paginated.queryMap();
		map.put("verifyState", verifyState);

		return paginated.load(new PaginatedLoader<Company>() {

			@Override
			public long loadTotalSize() {
				return comPanyDao.totalCompanyList(map);
			}

			@Override
			public List<Company> loadList() {
				return comPanyDao.findCompanyList(map);
			}
		});
	}

	public LoadCompanyHandler(CompanyFormDto formDto) {
		this.formDto = formDto;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public LoadCompanyHandler() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<MemberInfos> loadMemberAll(String guid) {
		List<MemberInfos> memberInfos = new ArrayList<>();
		List<Member> listMember;
		//admin 导出时
		User user = userRepository.findByGuid(guid);
		if (user != null && user.getPrivileges().contains(Privilege.ADMIN)) {
			listMember = memberRepository.findMemberList();
		}else {
			listMember = comPanyDao.findMemberListByGuid(guid);
		}
		for (Member member : listMember) {
			if (member != null) {
				// 招聘环节
				Recruitment recruitment = memberRepository
						.findRecruitmentByUuid(member.getUuid());
				// 离职环节
				Turnover turnover = memberRepository.findTurnoverByUuid(member
						.getUuid());
				// 工作环节
				Worked worked = memberRepository.findWorkedByUuid(member
						.getUuid());

				MemberInfos infos = new MemberInfos(member, recruitment,
						turnover, worked);
				memberInfos.add(infos);
			}
		}
		return memberInfos;
	}

}

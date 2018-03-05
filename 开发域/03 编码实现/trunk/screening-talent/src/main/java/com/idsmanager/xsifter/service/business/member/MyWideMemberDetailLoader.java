package com.idsmanager.xsifter.service.business.member;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.UUIDGenerator;
import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.service.dto.menber.BasicFormDto;
import com.idsmanager.xsifter.service.dto.menber.MemberDetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.QueryDetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.RecruitmentQueryPaginated;
import com.idsmanager.xsifter.service.dto.menber.TurnoverQueryPaginated;
import com.idsmanager.xsifter.service.dto.menber.WorkedQueryPaginated;

public class MyWideMemberDetailLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyWideMemberDetailLoader.class);

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private String uuid;
	private Map<String, Object> map1;
	private Map<String, Object> map2;
	private Map<String, Object> map3;

	private RecruitmentQueryPaginated recruitmentQueryPaginated;
	private TurnoverQueryPaginated turnoverQueryPaginated;
	private WorkedQueryPaginated workedQueryPaginated;

	public MyWideMemberDetailLoader(String uuid,
			RecruitmentQueryPaginated recruitmentQueryPaginated) {
		super();
		this.uuid = uuid;
		this.recruitmentQueryPaginated = recruitmentQueryPaginated;

	}

	public QueryDetailFormDto load() {

		QueryDetailFormDto formDto = new QueryDetailFormDto();

		Member member = this.memberRepository.findMemberByUuid(uuid);

		if (member == null) {
			formDto = new QueryDetailFormDto(UUIDGenerator.generate());
			return formDto;
		}

		map1 = recruitmentQueryPaginated.queryMap();
		setParamToMap(map1, member);

		turnoverQueryPaginated = new TurnoverQueryPaginated();
		map2 = turnoverQueryPaginated.queryMap();
		setParamToMap(map2, member);

		workedQueryPaginated = new WorkedQueryPaginated();
		map3 = workedQueryPaginated.queryMap();
		setParamToMap(map3, member);

		recruitmentQueryPaginated = getRecruitmentQueryPaginated();
		turnoverQueryPaginated = getTurnoverQueryPaginated();
		workedQueryPaginated = getWorkedQueryPaginated();

		BasicFormDto basicFormDto = new BasicFormDto(member);

		formDto.setBasicFormDto(basicFormDto);

		formDto.setRecruitmentPaginated(recruitmentQueryPaginated);

		formDto.setTurnoverPaginated(turnoverQueryPaginated);

		formDto.setWorkedPaginated(workedQueryPaginated);

		return formDto;
	}

	private void setParamToMap(Map<String, Object> map, Member member) {
		map.put("chName", member.getChName());
		map.put("idNumber", member.getIdNumber());
		map.put("mobile", member.getMobile());
		map.put("email", member.getEmail());

	}

	private WorkedQueryPaginated getWorkedQueryPaginated() {
		return workedQueryPaginated.load(new PaginatedLoader<Worked>() {

			@Override
			public List<Worked> loadList() {
				return memberRepository.findQueryWorkedList(map2);
			}

			@Override
			public long loadTotalSize() {
				return memberRepository.totalQueryWorkedList(map3);
			}
		});
	}

	private TurnoverQueryPaginated getTurnoverQueryPaginated() {
		return turnoverQueryPaginated.load(new PaginatedLoader<Turnover>() {

			@Override
			public List<Turnover> loadList() {
				return memberRepository.findQueryTurnoverList(map2);
			}

			@Override
			public long loadTotalSize() {
				return memberRepository.totalQueryTurnoverList(map2);
			}
		});
	}

	private RecruitmentQueryPaginated getRecruitmentQueryPaginated() {
		return recruitmentQueryPaginated
				.load(new PaginatedLoader<Recruitment>() {

					@Override
					public long loadTotalSize() {
						return memberRepository.totalQueryRecruitmentList(map1);
					}

					@Override
					public List<Recruitment> loadList() {
						return memberRepository.findQueryRecruitmentList(map1);
					}
				});
	}
}

package com.idsmanager.xsifter.service.business.member;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.print.PageableDoc;

import com.google.zxing.client.result.TelParsedResult;
import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.member.Entry;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.service.dto.menber.BasicFormDto;
import com.idsmanager.xsifter.service.dto.menber.EntryRecordPaginated;
import com.idsmanager.xsifter.service.dto.menber.InterviewRecordPaginated;
import com.idsmanager.xsifter.service.dto.menber.QueryMemberDetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.TelInvitationPaginated;

public class MyQueryDetailLoader {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyQueryDetailLoader.class);

	private transient MemberRepository memberRepository = BeanProvider
			.getBean(MemberRepository.class);

	private String uuid;

	private Map<String, Object> map1;
	private Map<String, Object> map2;
	private Map<String, Object> map3;

	private TelInvitationPaginated telInvitationPaginated;
	private InterviewRecordPaginated interviewRecordPaginated;
	private EntryRecordPaginated entryRecordPaginated;

	public MyQueryDetailLoader(String uuid,
			TelInvitationPaginated telInvitationPaginated,
			InterviewRecordPaginated interviewRecordPaginated,
			EntryRecordPaginated entryRecordPaginated) {
		super();
		this.uuid = uuid;
		this.telInvitationPaginated = telInvitationPaginated;
		this.interviewRecordPaginated = interviewRecordPaginated;
		this.entryRecordPaginated = entryRecordPaginated;
	}

	public QueryMemberDetailFormDto load() {

		QueryMemberDetailFormDto formDto = new QueryMemberDetailFormDto();

		Member member = this.memberRepository.findMemberByUuid(uuid);

		if (member == null) {
			return formDto;
		}

		if (telInvitationPaginated == null) {
			telInvitationPaginated = new TelInvitationPaginated();
		}

		if (interviewRecordPaginated == null) {
			interviewRecordPaginated = new InterviewRecordPaginated();
		}

		if (entryRecordPaginated == null) {
			entryRecordPaginated = new EntryRecordPaginated();
		}

		map1 = telInvitationPaginated.queryMap();
		setParamToMap(map1, member);

		map2 = interviewRecordPaginated.queryMap();
		setParamToMap(map2, member);

		map3 = entryRecordPaginated.queryMap();
		setParamToMap(map3, member);

		telInvitationPaginated = getTelInvitationPaginated();
		interviewRecordPaginated = getInterviewRecordPaginated();
		entryRecordPaginated = getEntryRecordPaginated();

		BasicFormDto basicFormDto = new BasicFormDto(member);

		formDto.setBasicFormDto(basicFormDto);
		formDto.setEntryPaginated(entryRecordPaginated);
		formDto.setInterviewPaginated(interviewRecordPaginated);
		formDto.setTelPaginated(telInvitationPaginated);

		return formDto;

	}

	private EntryRecordPaginated getEntryRecordPaginated() {
		return entryRecordPaginated.load(new PaginatedLoader<Entry>() {

			@Override
			public long loadTotalSize() {
				return memberRepository.totalEntryList(map3);
			}

			@Override
			public List<Entry> loadList() {
				return memberRepository.findEntryList(map3);
			}
		});
	}

	private InterviewRecordPaginated getInterviewRecordPaginated() {
		return interviewRecordPaginated
				.load(new PaginatedLoader<Recruitment>() {

					@Override
					public List<Recruitment> loadList() {
						return memberRepository.findRecruitmentList(map2);
					}

					@Override
					public long loadTotalSize() {
						return memberRepository.totalRecruitmentList(map2);
					}
				});
	}

	private TelInvitationPaginated getTelInvitationPaginated() {
		return telInvitationPaginated.load(new PaginatedLoader<Recruitment>() {

			@Override
			public List<Recruitment> loadList() {
				return memberRepository.findRecruitmentTelList(map1);
			}

			@Override
			public long loadTotalSize() {
				return memberRepository.totalRecruitmentTelList(map1);
			}
		});
	}

	private void setParamToMap(Map<String, Object> map, Member member) {
		map.put("chName", member.getChName());
		map.put("idNumber", member.getIdNumber());
		map.put("mobile", member.getMobile());
		map.put("email", member.getEmail());

	}

}

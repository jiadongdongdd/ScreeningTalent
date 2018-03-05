package com.idsmanager.xsifter.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.xsifter.domain.admin.CreditAction;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.domain.log.Log;
import com.idsmanager.xsifter.domain.log.LogRepository;
import com.idsmanager.xsifter.domain.log.OperateType;
import com.idsmanager.xsifter.domain.member.Basic;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.service.MemberService;
import com.idsmanager.xsifter.service.TScAcntPropService;
import com.idsmanager.xsifter.service.business.admin.AdminMemberRemover;
import com.idsmanager.xsifter.service.business.admin.CreditStreamCreater;
import com.idsmanager.xsifter.service.business.member.BasicMemberSaver;
import com.idsmanager.xsifter.service.business.member.ExcelModelExportor;
import com.idsmanager.xsifter.service.business.member.IntentionMemberSaver;
import com.idsmanager.xsifter.service.business.member.MemberEducationSaver;
import com.idsmanager.xsifter.service.business.member.MemberFormDtoLoader;
import com.idsmanager.xsifter.service.business.member.MyMemberDetailLoader;
import com.idsmanager.xsifter.service.business.member.MyMemberExcelImportor;
import com.idsmanager.xsifter.service.business.member.MyMemberFormOneLoader;
import com.idsmanager.xsifter.service.business.member.MyMemberFormTwoLoader;
import com.idsmanager.xsifter.service.business.member.MyMemberRemover;
import com.idsmanager.xsifter.service.business.member.MyQueryDetailLoader;
import com.idsmanager.xsifter.service.business.member.MyWideMemberDetailLoader;
import com.idsmanager.xsifter.service.business.member.MyWideSearchMemberLoader;
import com.idsmanager.xsifter.service.business.member.PartMemberInfoLoader;
import com.idsmanager.xsifter.service.business.member.RecruitmentFormDtoLoader;
import com.idsmanager.xsifter.service.business.member.TurnoverMemberSaver;
import com.idsmanager.xsifter.service.business.member.WorkedMemberSaver;
import com.idsmanager.xsifter.service.dto.IdsFileDto;
import com.idsmanager.xsifter.service.dto.menber.DetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.EntryRecordPaginated;
import com.idsmanager.xsifter.service.dto.menber.ImportExcelFormDto;
import com.idsmanager.xsifter.service.dto.menber.ImportExcelResultDto;
import com.idsmanager.xsifter.service.dto.menber.InterviewRecordPaginated;
import com.idsmanager.xsifter.service.dto.menber.MemberDetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.MemberFormDto;
import com.idsmanager.xsifter.service.dto.menber.MemberFormOneDto;
import com.idsmanager.xsifter.service.dto.menber.MemberFormTwoDto;
import com.idsmanager.xsifter.service.dto.menber.MemberPaginated;
import com.idsmanager.xsifter.service.dto.menber.MyMemberPaginated;
import com.idsmanager.xsifter.service.dto.menber.QueryDetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.QueryMemberDetailFormDto;
import com.idsmanager.xsifter.service.dto.menber.QueryResultDto;
import com.idsmanager.xsifter.service.dto.menber.RecruitmentFormDto;
import com.idsmanager.xsifter.service.dto.menber.RecruitmentQueryPaginated;
import com.idsmanager.xsifter.service.dto.menber.TelInvitationPaginated;
import com.idsmanager.xsifter.service.dto.menber.TurnoverFormDto;
import com.idsmanager.xsifter.service.dto.menber.WorkedFormDto;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	private static final Logger LOG = LoggerFactory
			.getLogger(MemberServiceImpl.class);

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private TScAcntPropService tScAcntPropService;

	@Autowired
	private MemberPositionRepository memberPositionRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public MemberPaginated loadMemberPaginated(MemberPaginated paginated) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<Member>() {
			@Override
			public List<Member> loadList() {

				return memberRepository.findMemberListByWide(map);
			}

			@Override
			public long loadTotalSize() {

				return memberRepository.totalMemberListByWide(map);
			}
		});
	}

	@Override
	public String saveBasicMember(MemberFormOneDto formDto) {

		BasicMemberSaver saver = new BasicMemberSaver(formDto);
		return saver.save();
	}

	@Override
	public MemberFormDto loadMemberFormDto(String uuid) {

		MemberFormDtoLoader dtoLoader = new MemberFormDtoLoader(uuid);
		return dtoLoader.load();
	}

	@Override
	public Member findMemberByMobile(String mobile) {
		return memberRepository.findMemberByMobile(mobile);
	}

	@Override
	public Member findMemberByIDNumber(String idNumber) {
		return memberRepository.findMemberByIDNumber(idNumber);
	}

	@Override
	public void saveMemberEducation(MemberFormTwoDto formDto) {
		MemberEducationSaver saver = new MemberEducationSaver(formDto);
		saver.save();
	}

	@Override
	public MemberFormOneDto loadMyFormOneDto(String uuid) {
		MyMemberFormOneLoader loader = new MyMemberFormOneLoader(uuid);
		return loader.load();
	}

	@Override
	public MemberFormTwoDto loadMemberFormTwoDto(String uuid) {
		MyMemberFormTwoLoader loader = new MyMemberFormTwoLoader(uuid);
		return loader.load();
	}

	@Override
	public RecruitmentFormDto loaRecruitmentFormDto(String uuid) {
		RecruitmentFormDtoLoader loader = new RecruitmentFormDtoLoader(uuid);
		return loader.load();
	}

	@Override
	public void saveIntentionMember(RecruitmentFormDto formDto) {
		IntentionMemberSaver saver = new IntentionMemberSaver(formDto);
		saver.save();

	}

	@Override
	public int findMembersCountByCompanyId(String uuid) {
		return memberRepository.findMembersCountByCompanyId(uuid);
	}

	@Override
	public MemberFormOneDto loadMemberFormOneDto(String uuid) {
		// Member member = memberRepository.findMemberByUuid(uuid);
		Basic basic = memberRepository.findBasicByUuid(uuid);
		return basic == null ? new MemberFormOneDto() : new MemberFormOneDto(
				basic);
	}

	@Override
	public MemberFormDto loadWideMemberFormDto(String uuid) {
		MyWideSearchMemberLoader loader = new MyWideSearchMemberLoader(uuid);
		return loader.load();
	}

	@Override
	public TurnoverFormDto loadTurnoverFormDto(String uuid) {
		Turnover turnover = memberRepository.findTurnoverByUuid(uuid);

		List<MemberPosition> positionList = this.memberPositionRepository
				.findPositionList();
		TurnoverFormDto formDto;
		if (turnover == null) {
			formDto = new TurnoverFormDto(uuid);
			formDto.setAdd(true);
			formDto.setPositionList(positionList);
			return formDto;
		}

		formDto = new TurnoverFormDto(turnover);
		formDto.setPositionList(positionList);

		return formDto;
	}

	@Override
	public String saveTurnoverMember(TurnoverFormDto formDto) {
		TurnoverMemberSaver saver = new TurnoverMemberSaver(formDto);
		return saver.save();
	}

	@Override
	public WorkedFormDto loadWorkedFormDto(String uuid) {
		Worked worked = memberRepository.findWorkedByUuid(uuid);
		return worked == null ? new WorkedFormDto(uuid) : new WorkedFormDto(
				worked);
	}

	@Override
	public String saveWorkedMember(WorkedFormDto formDto) {
		WorkedMemberSaver saver = new WorkedMemberSaver(formDto);
		return saver.save();
	}

	@Override
	public void deleteMyMember(String uuid) {
		MyMemberRemover remover = new MyMemberRemover(uuid);
		remover.remove();

	}

	@Override
	public MemberPaginated loadAdminMemberPaginated(MemberPaginated paginated) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<Member>() {

			@Override
			public long loadTotalSize() {
				return memberRepository.findAdminMemberTotalCount(map);
			}

			@Override
			public List<Member> loadList() {
				List<Member> memberList = memberRepository
						.findAdminMemberList(map);
				for (Member member : memberList) {
					int integrity = tScAcntPropService.loadTScAcntNumber(member
							.getUuid());
					member.setIntegrity(integrity);
				}
				return memberList;
			}
		});
	}

	@Override
	public MemberPaginated loadHomeMemberPaginated(MemberPaginated paginated) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<Member>() {

			@Override
			public List<Member> loadList() {
				return memberRepository.findHomeMemberList(map);
			}

			@Override
			public long loadTotalSize() {
				return memberRepository.totalHomeMemberList(map);
			}
		});
	}

	@Override
	public List<Member> findMembersByCompanyGuid(String companyGuid) {
		return this.memberRepository.findMembersByCompanyGuid(companyGuid);
	}

	@Override
	public long companyTotalMembers(String companyGuid) {
		return this.memberRepository.companyTotalMembers(companyGuid);
	}

	@Override
	public Member findMemberByMobileAndCompanyUuid(String mobile,
			String currentUserGuid) {
		return this.memberRepository.findMemberByMobileAndCompanyUuid(mobile,
				currentUserGuid);
	}

	@Override
	public Member findMemberByIDNumberAndCompanyGuid(String idNumber,
			String currentUserGuid) {
		return this.memberRepository.findMemberByIDNumberAndCompanyGuid(
				idNumber, currentUserGuid);
	}

	@Override
	public Member findMemberByEmailAndCompanGuid(String email,
			String currentUserGuid) {
		return this.memberRepository.findMemberByEmailAndCompanyGuid(email,
				currentUserGuid);
	}

	@Override
	public MemberDetailFormDto loadMemberDetialFormDto(String uuid) {
		MyMemberDetailLoader loader = new MyMemberDetailLoader(uuid);
		return loader.load();
	}

	@Override
	public QueryDetailFormDto loadWideMemberDetailFormDto(String uuid,
			RecruitmentQueryPaginated recruitmentQueryPaginated) {
		MyWideMemberDetailLoader loader = new MyWideMemberDetailLoader(uuid,
				recruitmentQueryPaginated);
		return loader.load();
	}

	@Override
	public MyMemberPaginated loadMyMemberPaginated(MyMemberPaginated paginated) {
		final Map<String, Object> queryMap = paginated.queryMap();
		return paginated.load(new PaginatedLoader<Member>() {

			@Override
			public List<Member> loadList() {
				List<Member> memberList = memberRepository
						.findMyMemberList(queryMap);
				for (Member member : memberList) {
					int integrity = tScAcntPropService.loadTScAcntNumber(member
							.getUuid());
					member.setIntegrity(integrity);
				}
				return memberList;
			}

			@Override
			public long loadTotalSize() {
				return memberRepository.totalMyMemberList(queryMap);
			}
		});
	}

	@Override
	public MyMemberPaginated loadQueryMemberPaginated(
			MyMemberPaginated paginated, final boolean isSwitch) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<Member>() {

			@Override
			public List<Member> loadList() {
				List<Member> list = memberRepository.findQueryMemberList(map);
				List<Member> result = new ArrayList<>();
				if (list != null && list.size() > 0) {
					result.add(list.get(0));
					if (!isSwitch) {
						createTotalStationQueryLog(list.get(0));
					}
				}
				return result;
			}

			@Override
			public long loadTotalSize() {

				long total = memberRepository.totalQueryMemberList(map);

				if (total > 0) {
					total = 1;
				}

				return total;
			}
		});
	}

	private void createTotalStationQueryLog(Member member) {
		Log newLog = new Log()
				.setEntity(member)
				.setEntityClass(member.getClass().toString())
				.setEntityName(member.getChName())
				.setEntityUuid(member.getUuid())
				.setOperateDetail(
						OperateType.TOTAL_STATION_QUERY_MEMBER.getLabel())
				.setOperateType(OperateType.TOTAL_STATION_QUERY_MEMBER)
				.setOperator(SecurityUtils.username())
				.setOperatorId(SecurityUtils.currentUserGuid());
		this.logRepository.saveLog(newLog);
	}

	@Override
	public boolean queryMemberHadle(MyMemberPaginated paginated,
			boolean isSwitch) {
		CreditStreamCreater streamCreater;
		boolean enough = false;
		User user = userRepository.findByGuid(SecurityUtils.currentUserGuid());
		try {
			if (!isSwitch) {

				if (user == null) {
					if (paginated.getList() != null
							&& paginated.getList().size() > 0) {
						streamCreater = new CreditStreamCreater(
								new CreditStreamHolder(
										SecurityUtils.currentUserGuid(),
										"全站查找职工["
												+ paginated.getList().get(0)
														.getChName() + "]",
										CreditAction.SEARCH));
						//如果查到的人不是自己公司的再扣积分.
						String userid = SecurityUtils.currentUserGuid();
						if(!paginated.getList().get(0).getCompanyGuid().equalsIgnoreCase(userid)){							
							streamCreater.create();
						}
					}
					// 下次积分是否充足
					streamCreater = new CreditStreamCreater(
							new CreditStreamHolder(
									SecurityUtils.currentUserGuid(), "全站查找职工",
									CreditAction.SEARCH));
					enough = streamCreater.checkCreditEnough();
				} else {
					enough = true;
				}

			} else {
				enough = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enough;
	}

	@Override
	public QueryMemberDetailFormDto loadWideMemberDetailFormDto(String uuid,
			TelInvitationPaginated telPaginated,
			InterviewRecordPaginated interviewRecordPaginated,
			EntryRecordPaginated entryRecordPaginated) {
		MyQueryDetailLoader loader = new MyQueryDetailLoader(uuid,
				telPaginated, interviewRecordPaginated, entryRecordPaginated);
		return loader.load();
	}

	@Override
	public DetailFormDto loadPartInfo(String partName, String uuid) {
		PartMemberInfoLoader loader = new PartMemberInfoLoader(partName, uuid);
		return loader.load();
	}

	@Override
	public void deleteMember(String uuid) {
		AdminMemberRemover remover = new AdminMemberRemover(uuid);
		remover.remove();
	}

	@Override
	public Recruitment findRecruitment(String uuid) {
		Recruitment recruitment = this.memberRepository
				.findRecruitmentByUuid(uuid);
		return recruitment;
	}

	@Override
	public Turnover findTurnoverByUuid(String uuid) {
		return this.memberRepository.findTurnoverByUuid(uuid);
	}

	@Override
	public ImportExcelResultDto importExcel(ImportExcelFormDto formDto) {
		MyMemberExcelImportor importor = new MyMemberExcelImportor(formDto);
		return importor.importExcel();
	}

	@Override
	public IdsFileDto exportModel(){
		// MyModelExportor exportor = new MyModelExportor();
		// exportor.export(response);
		ExcelModelExportor exportor = new ExcelModelExportor();
		return exportor.export();
	}

	@Override
	public QueryResultDto validateFiled(MyMemberPaginated paginated) {
		QueryResultDto dto = new QueryResultDto();
		String chNameQuery = paginated.getChNameQuery();
		if (StringUtils.isEmpty(chNameQuery)) {
			dto.setChNameEmpty(false);
		}
		String idNumberQuery = paginated.getIdNumberQuery();
		String mobileQuery = paginated.getMobileQuery();
		String emailQuery = paginated.getEmailQuery();

		if (StringUtils.isNotEmpty(chNameQuery)) {
			if (StringUtils.isEmpty(emailQuery)
					&& StringUtils.isEmpty(mobileQuery)
					&& StringUtils.isEmpty(idNumberQuery)) {
				dto.setOptionEmpty(false);

			}
		}

		return dto;
	}

}

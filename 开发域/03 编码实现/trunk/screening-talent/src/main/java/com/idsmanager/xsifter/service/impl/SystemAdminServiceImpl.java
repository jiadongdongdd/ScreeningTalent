package com.idsmanager.xsifter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.xsifter.domain.admin.CreditRule;
import com.idsmanager.xsifter.domain.admin.CreditStream;
import com.idsmanager.xsifter.domain.admin.MemberLevel;
import com.idsmanager.xsifter.domain.admin.SystemAdminRepository;
import com.idsmanager.xsifter.domain.admin.creditstream.CreditStreamHolder;
import com.idsmanager.xsifter.service.SystemAdminService;
import com.idsmanager.xsifter.service.business.admin.CreditNotEnoughException;
import com.idsmanager.xsifter.service.business.admin.CreditRuleCreater;
import com.idsmanager.xsifter.service.business.admin.CreditRuleDeleter;
import com.idsmanager.xsifter.service.business.admin.CreditStreamCreater;
import com.idsmanager.xsifter.service.business.admin.MemberLevelCreater;
import com.idsmanager.xsifter.service.business.admin.MemberLevelDeleter;
import com.idsmanager.xsifter.service.business.admin.credit.CreditRuleInitiator;
import com.idsmanager.xsifter.service.dto.admin.CreditRuleFormDto;
import com.idsmanager.xsifter.service.dto.admin.CreditStreamDto;
import com.idsmanager.xsifter.service.dto.admin.CreditStreamFormDto;
import com.idsmanager.xsifter.service.dto.admin.CreditStreamPaginated;
import com.idsmanager.xsifter.service.dto.admin.MemberLeveLFormDto;
import com.idsmanager.xsifter.service.dto.admin.MemberLevelDto;

@Service("systemAdminService")
public class SystemAdminServiceImpl implements SystemAdminService {

	@Autowired
	private SystemAdminRepository systemAdminRepository;

	@Override
	public String saveMemberLevel(MemberLeveLFormDto formDto) {
		MemberLevelCreater creater = new MemberLevelCreater(formDto);
		return creater.create();
	}

	@Override
	public String updateMemberLevel(MemberLevel memberLevel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMemberLevel(String uuid) {
		MemberLevelDeleter deleter = new MemberLevelDeleter(uuid);
		deleter.delete();
	}

	@Override
	public boolean checkRegionForNumber(Integer number, String uuid) {
		MemberLevel memberLevel = systemAdminRepository
				.findMemberLevelByNumber(number, uuid);
		return memberLevel != null;
	}

	@Override
	public MemberLevelDto getMemberLevelDtoByUUID(String uuid) {
		MemberLevel memberLevel = systemAdminRepository.findByUUID(uuid);
		return new MemberLevelDto(memberLevel);
	}

	@Override
	public MemberLevelDto getMemberLevelDtoByLevel(String level) {
		MemberLevel memberLevel = systemAdminRepository.findByLevel(level);
		if (null != memberLevel) {
			return new MemberLevelDto(memberLevel);
		}
		return null;
	}

	@Override
	public List<MemberLevelDto> getAllMemberLevelDto() {
		List<MemberLevelDto> list = new ArrayList<>();
		List<MemberLevel> dataList = systemAdminRepository
				.findAllMemberLevelList();
		if (null != dataList && dataList.size() > 0) {
			for (MemberLevel single : dataList) {
				list.add(new MemberLevelDto(single));
			}
		}
		return list;
	}

	@Override
	public void deleteMemberLevelByUUIDList(List<String> asList) {
		// TODO Auto-generated method stub
		MemberLevelDeleter deleter = new MemberLevelDeleter(asList);
		deleter.delete();
	}

	@Override
	public String saveCreditRule(CreditRuleFormDto formDto) {
		CreditRuleCreater creater = new CreditRuleCreater(formDto);
		return creater.create();
	}

	@Override
	public void deleteCreditRuleByUUIDList(List<String> list) {
		CreditRuleDeleter deleter = new CreditRuleDeleter(list);
		deleter.delete();
	}

	@Override
	public CreditRuleFormDto getCreditRuleFormDtoByUUID(String uuid) {
		CreditRule creditRule = systemAdminRepository
				.findCreditRuleByUUID(uuid);
		return new CreditRuleFormDto(creditRule);
	}

	@Override
	public CreditRuleFormDto getCreditRuleFormDtoByRuleName(String ruleName) {
		CreditRule creditRule = systemAdminRepository.findByRuleName(ruleName);
		if (null != creditRule) {
			return new CreditRuleFormDto(creditRule);
		}
		return null;
	}

	@Override
	public List<CreditRuleFormDto> getAllCreditRuleFormDtos() {
		List<CreditRuleFormDto> dtoList = new ArrayList<>();
		List<CreditRule> list = systemAdminRepository.findAllCreditRulesList();
		if (null != list && list.size() > 0) {
			for (CreditRule single : list) {
				dtoList.add(new CreditRuleFormDto(single));
			}
		}
		return dtoList;
	}

	@Override
	public void addCreditStream(CreditStreamFormDto formDto) throws Exception {
		// TODO Auto-generated method stub
		CreditStreamHolder holder = new CreditStreamHolder(
				formDto.getCompanyUuid(), formDto.getRemarks(),
				formDto.getCreditDo());
		CreditStreamCreater creater = new CreditStreamCreater(holder);
		creater.create();
	}

	@Override
	public List<CreditStreamDto> findCreditStreamDtoListByCompanyUUID(
			String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditStreamPaginated loadCreditStreamPaginated(
			CreditStreamPaginated paginated, final String companyUUID) {
		final Map<String, Object> map = paginated.queryMap();

		return paginated.load(new PaginatedLoader<CreditStream>() {

			@Override
			public long loadTotalSize() {
				// TODO Auto-generated method stub
				return systemAdminRepository.totalCreditStreamByCompanyUUID(
						map, companyUUID);
			}

			@Override
			public List<CreditStream> loadList() {
				// TODO Auto-generated method stub
				return systemAdminRepository.findCreditStreamListByCompanyUUID(
						map, companyUUID);
			}
		});
	}

	@Override
	public CreditRuleFormDto getCreditStreamFromDtoByAction(String action) {
		CreditRule domain = systemAdminRepository
				.findCreditRuleByAction(action);
		if (null != domain) {
			return new CreditRuleFormDto(domain);
		}
		return null;
	}

	@Override
	public void addBatchCreditStream(CreditStreamFormDto formDto)
			throws CreditNotEnoughException, Exception {
		// TODO Auto-generated method stub
		for (String companyUuid : formDto.getCompanyUuids()) {
			CreditStreamCreater creater = new CreditStreamCreater(
					new CreditStreamHolder(companyUuid, formDto.getRemarks(),
							formDto.getCreditDo()));
			if (creater.checkCreditEnough()) {
				creater.create();
			}
		}
	}

	@Override
	public CreditStreamPaginated loadAllCreditStreamPaginated(
			CreditStreamPaginated paginated) {
		final Map<String, Object> map = paginated.queryMap();

		return paginated.load(new PaginatedLoader<CreditStream>() {

			@Override
			public List<CreditStream> loadList() {
				return systemAdminRepository.findAllCreditStreamList(map);
			}

			@Override
			public long loadTotalSize() {
				return systemAdminRepository.countCreditStream(map);
			}
		});
	}

	@Override
	public void initCreditRule() {

		CreditRuleInitiator initiator = new CreditRuleInitiator();
		initiator.init();

	}
}

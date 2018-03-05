package com.idsmanager.xsifter.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;
import com.idsmanager.xsifter.service.MemberPositionService;
import com.idsmanager.xsifter.service.business.position.MyPositionInitiator;
import com.idsmanager.xsifter.service.business.position.MyPositionLoader;
import com.idsmanager.xsifter.service.business.position.MyPositionRemover;
import com.idsmanager.xsifter.service.business.position.MyPositionSaver;
import com.idsmanager.xsifter.service.dto.position.PositionFormDto;
import com.idsmanager.xsifter.service.dto.position.PositionPaginated;

@Service("memberPositionService")
public class MemberPositionServiceImpl implements MemberPositionService {

	@Autowired
	private MemberPositionRepository memberPositionRepository;

	@Override
	public PositionPaginated loadPositionPaginated(PositionPaginated paginated) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<MemberPosition>() {

			@Override
			public long loadTotalSize() {
				return memberPositionRepository.totalPositionList(map);
			}

			@Override
			public List<MemberPosition> loadList() {
				return memberPositionRepository.findPositionList(map);
			}
		});
	}

	@Override
	public void createPosition(PositionFormDto formDto) {
		MyPositionSaver saver = new MyPositionSaver(formDto);
		saver.save();
	}

	@Override
	public PositionFormDto loadPositionByUUID(String uuid) {
		MyPositionLoader loader = new MyPositionLoader(uuid);
		return loader.load();
	}

	@Override
	public void updatePosition(PositionFormDto formDto) {
		MyPositionSaver saver = new MyPositionSaver(formDto);
		saver.save();
	}

	@Override
	public void deletePosition(String uuid) {
		MyPositionRemover remover = new MyPositionRemover(uuid);
		remover.remove();
	}

	@Override
	public List<MemberPosition> findPositionList() {
		return memberPositionRepository.findPositionList();
	}

	@Override
	public void initPosition() throws FileNotFoundException, IOException {

		MyPositionInitiator initiator = new MyPositionInitiator();
		initiator.initial();

	}

	@Override
	public MemberPosition findPositionByAllOption(PositionFormDto formDto) {
		return memberPositionRepository.findPositionByAllOptions(formDto);
	}

}

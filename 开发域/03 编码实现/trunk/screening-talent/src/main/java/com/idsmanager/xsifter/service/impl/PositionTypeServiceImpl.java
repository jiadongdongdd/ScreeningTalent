package com.idsmanager.xsifter.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.xsifter.domain.position.PositionType;
import com.idsmanager.xsifter.domain.position.PositionTypeRepository;
import com.idsmanager.xsifter.service.PositionTypeService;
import com.idsmanager.xsifter.service.business.position.MyPositionTypeLoader;
import com.idsmanager.xsifter.service.business.position.MyPositionTypeRemover;
import com.idsmanager.xsifter.service.business.position.MyPositionTypeSaver;
import com.idsmanager.xsifter.service.dto.position.PositionTypeFormDto;
import com.idsmanager.xsifter.service.dto.position.PositionTypePaginated;

@Service("positionTypeService")
public class PositionTypeServiceImpl implements PositionTypeService {

	@Autowired
	private PositionTypeRepository positionTypeRepository;

	@Override
	public PositionTypePaginated loadPositionTypePaginated(
			PositionTypePaginated paginated) {
		final Map<String, Object> map = paginated.queryMap();
		return paginated.load(new PaginatedLoader<PositionType>() {

			@Override
			public long loadTotalSize() {
				return positionTypeRepository.totalPositionTypeList(map);
			}

			@Override
			public List<PositionType> loadList() {
				return positionTypeRepository.findPositionTypeList(map);
			}
		});
	}

	@Override
	public void createPositionType(PositionTypeFormDto formDto) {

		MyPositionTypeSaver saver = new MyPositionTypeSaver(formDto);
		saver.save();

	}

	@Override
	public void editPositionType(PositionTypeFormDto formDto) {

		MyPositionTypeSaver saver = new MyPositionTypeSaver(formDto);
		saver.save();

	}

	@Override
	public void deletePositionType(String uuid) {
		MyPositionTypeRemover remover = new MyPositionTypeRemover(uuid);
		remover.remove();

	}

	@Override
	public PositionTypeFormDto loadMyPositionType(String uuid) {
		MyPositionTypeLoader loader = new MyPositionTypeLoader(uuid);
		return loader.load();
	}

}

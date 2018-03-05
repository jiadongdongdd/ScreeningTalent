package com.idsmanager.xsifter.service;

import com.idsmanager.xsifter.service.dto.position.PositionTypeFormDto;
import com.idsmanager.xsifter.service.dto.position.PositionTypePaginated;

public interface PositionTypeService {

	PositionTypePaginated loadPositionTypePaginated(
			PositionTypePaginated paginated);

	void createPositionType(PositionTypeFormDto formDto);

	void editPositionType(PositionTypeFormDto formDto);

	void deletePositionType(String uuid);

	PositionTypeFormDto loadMyPositionType(String uuid);

}

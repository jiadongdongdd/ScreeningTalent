package com.idsmanager.xsifter.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.service.dto.position.PositionFormDto;
import com.idsmanager.xsifter.service.dto.position.PositionPaginated;

public interface MemberPositionService {

	PositionPaginated loadPositionPaginated(PositionPaginated paginated);

	void createPosition(PositionFormDto formDto);

	PositionFormDto loadPositionByUUID(String uuid);

	void updatePosition(PositionFormDto formDto);

	void deletePosition(String uuid);

	List<MemberPosition> findPositionList();

	void initPosition() throws FileNotFoundException, IOException;

	MemberPosition findPositionByAllOption(PositionFormDto formDto);

}

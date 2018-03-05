package com.idsmanager.xsifter.domain.position;

import java.util.List;
import java.util.Map;

import com.idsmanager.xsifter.domain.Repository;
import com.idsmanager.xsifter.service.dto.position.PositionFormDto;

public interface MemberPositionRepository extends Repository {

	long totalPositionList(Map<String, Object> map);

	List<MemberPosition> findPositionList(Map<String, Object> map);

	MemberPosition findPostionByUuid(String uuid);

	void savePosition(MemberPosition position);

	void deletePosition(MemberPosition position);

	List<MemberPosition> findPositionList();

	long countPositions();

	MemberPosition findPositionByAllOptions(PositionFormDto formDto);


}

package com.idsmanager.xsifter.domain.position;

import java.util.List;
import java.util.Map;

import com.idsmanager.xsifter.domain.Repository;

public interface PositionTypeRepository extends Repository {

	long totalPositionTypeList(Map<String, Object> map);

	List<PositionType> findPositionTypeList(Map<String, Object> map);

	PositionType findPositionTypeByUuid(String uuid);

	void saveType(PositionType type);

	void deleteType(PositionType type);

}

package com.mountain.model.dao;

import java.util.List;

import com.mountain.model.dto.Mountain;
import com.mountain.model.dto.SearchConditionForMountain;

public interface MountainDao {

	// 산 전체 목록
	List<Mountain> selectAll();
	
	// '도'로 산 조회
	List<Mountain> selectMountainByState(String state);

	// 산 1개 조회
	Mountain selectOne(int mountainSerial);

	// 산 등록
	void createMountain(Mountain mountain);

	// 산 수정
	void modifyMountain(Mountain mountain);

	// 산 삭제
	void deleteMountain(int mountainSerial);
	
	// 산 검색
	List<Mountain> selectMountainBySearch(SearchConditionForMountain SearchCondition);

	// 등산한 산 검색
	List<Mountain> selectCompleteMountainByUserSerial(int userSerial);
}

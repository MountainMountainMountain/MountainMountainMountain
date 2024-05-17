package com.mountain.model.service;

import java.util.List;

import com.mountain.model.dto.Mountain;
import com.mountain.model.dto.SearchCondition;

public interface MountainService {

	// Mountain 전체 리스트 조회
	public List<Mountain> selectAllMountain();

	// Mountain 도 리스트 조회
	public List<Mountain> selectMountainByState(String state);

	// Mountain 1개 조회
	public Mountain selectOneMountain(int mountainSerial);

	// Mountain 삽입
	public void createMountain(Mountain mountain);

	// Mountain 수정
	public void modifyMountain(Mountain mountain);

	// Mountain 삭제
	public void deleteMountain(int mountainSerial);

	// Mountain 검색
	public List<Mountain> selectMountainBySearch(SearchCondition searchcondition);

}

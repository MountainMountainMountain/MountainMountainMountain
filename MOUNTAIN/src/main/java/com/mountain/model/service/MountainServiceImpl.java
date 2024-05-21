package com.mountain.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mountain.model.dao.MountainDao;
import com.mountain.model.dto.Mountain;
import com.mountain.model.dto.SearchConditionForMountain;

@Service
public class MountainServiceImpl implements MountainService {

	private final MountainDao mountainDao;

	public MountainServiceImpl(MountainDao mountainDao) {
		this.mountainDao = mountainDao;
	}

	@Override
	public List<Mountain> selectAllMountain() {
		return mountainDao.selectAll();
	}

	@Override
	public List<Mountain> selectMountainByState(String state) {
		return mountainDao.selectMountainByState(state);
	}

	@Override
	public Mountain selectOneMountain(int mountainSerial) {
		return mountainDao.selectOne(mountainSerial);
	}

	@Override
	public void createMountain(Mountain mountain) {
		mountainDao.createMountain(mountain);
	}

	@Override
	public void modifyMountain(Mountain mountain) {
		mountainDao.modifyMountain(mountain);
	}

	@Override
	public void deleteMountain(int mountainSerial) {
		mountainDao.deleteMountain(mountainSerial);
	}

	@Override
	public List<Mountain> selectMountainBySearch(SearchConditionForMountain searchcondition) {
		return mountainDao.selectMountainBySearch(searchcondition);
	}

	@Override
	public List<Mountain> selectCompleteMountainByUserSerial(int UserSerial) {
		return mountainDao.selectCompleteMountainByUserSerial(UserSerial);
	}

}

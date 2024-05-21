package com.mountain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mountain.model.dto.Mountain;
import com.mountain.model.dto.SearchCondition;
import com.mountain.model.service.MountainService;

@RestController
@RequestMapping("/api-mountain")
public class MountainRESTController {

	private final MountainService mountainService;

	public MountainRESTController(MountainService mountainService) {
		this.mountainService = mountainService;
	}

	// 전체 산 조회
	@GetMapping("/")
	public ResponseEntity<?> getAllMountains() {
		List<Mountain> list = mountainService.selectAllMountain();

		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Mountain>>(list, HttpStatus.OK);
	}

	// 산 도를 검색하여 조회
	@GetMapping("/state/{mountainState}")
	public ResponseEntity<?> getMountainsByState(@PathVariable("mountainState") String mountainState) {
		System.out.println(mountainState);
		List<Mountain> list = mountainService.selectMountainByState(mountainState);

		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Mountain>>(list, HttpStatus.OK);
	}

	// 산 Serial 번호를 통해 조회
	@GetMapping("/{mountainSerial}")
	public ResponseEntity<?> getMountainsBySerial(@PathVariable("mountainSerial") int mountainSerial) {
		Mountain mountain = mountainService.selectOneMountain(mountainSerial);

		if (mountain == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Mountain>(mountain, HttpStatus.OK);
	}

	// 정복한 산
	@GetMapping("/user/{userSerial}")
	public ResponseEntity<?> selectMountainByUserSerial(@PathVariable("userSerial") int userSerial) {
		List<Mountain> list = mountainService.selectCompleteMountainByUserSerial(userSerial);
		if (list == null || list.size() == 0)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// 산 생성
	@PostMapping("/")
	public ResponseEntity<?> createMountain(@RequestBody Mountain mountain) {
//		if((mountain.getAltitude()==null||
//		mountain.getCourse()
//		mountain.getDifficulty()
//		mountain.getFee()
//		mountain.getLatitude()
//		mountain.getLongitude()
//		mountain.getName()
//		mountain.getState()
//		mountain.getTown()
//		if(mountain.get)

		mountainService.createMountain(mountain);

		return new ResponseEntity<Mountain>(mountain, HttpStatus.OK);
//		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	// 산 searchcondition에 따라 조회
	@PostMapping("/mountain")
	public ResponseEntity<?> getMountainBySearchCondition(@RequestBody SearchCondition searchCondition) {
		System.out.println(searchCondition);
		List<Mountain> list = mountainService.selectMountainBySearch(searchCondition);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<List<Mountain>>(list, HttpStatus.OK);
	}

	// 산 업데이트
	@PutMapping("/{mountainSerial}")
	public ResponseEntity<?> updateMountain(@PathVariable("mountainSerial") int mountainSerial,
			@RequestBody Mountain mountain) {
		mountain.setSerial(mountainSerial);

		mountainService.modifyMountain(mountain);

		return new ResponseEntity<Mountain>(mountain, HttpStatus.OK);
//		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	// 산 삭제
	@DeleteMapping("/{mountainSerial}")
	public ResponseEntity<?> deleteMountain(@PathVariable("mountainSerial") int mountainSerial) {
		mountainService.deleteMountain(mountainSerial);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}

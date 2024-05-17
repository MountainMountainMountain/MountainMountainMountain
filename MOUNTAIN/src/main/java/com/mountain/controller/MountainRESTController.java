package com.mountain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mountain.model.dto.Mountain;
import com.mountain.model.service.MountainService;

@RestController
@RequestMapping("/api-mountain")
public class MountainRESTController {

	private final MountainService mountainService;

	public MountainRESTController(MountainService mountainService) {
		this.mountainService = mountainService;
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllMountains() {
		List<Mountain> list = mountainService.selectAllMountain();

		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Mountain>>(list, HttpStatus.OK);
	}

	@GetMapping("/state/{mountainState}")
	public ResponseEntity<?> getMountainsByState(@PathVariable("mountainState") String mountainState) {
		List<Mountain> list = mountainService.selectMountainByState(mountainState);

		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Mountain>>(list, HttpStatus.OK);
	}

	@GetMapping("/{mountainSerial}")
	public ResponseEntity<?> getMountainsBySerial(@PathVariable("mountainSerial") int mountainSerial) {
		Mountain mountain = mountainService.selectOneMountain(mountainSerial);

		if (mountain == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Mountain>(mountain, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> postMethodName(@RequestBody Mountain mountain) {
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

		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

}

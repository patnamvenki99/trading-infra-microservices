package com.traidnginfra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traidnginfra.model.Position;
import com.traidnginfra.service.PositionService;

@RestController
@RequestMapping(path = "/position")
public class PositionController {
	
	@Autowired
	private PositionService positionService;
	
	@PostMapping(path="/createposition")
	public ResponseEntity<Position> createPosition(@RequestBody Position position) {
		if(Math.random() > 0.9)
			return ResponseEntity.ok(positionService.createPosition(position));
		else
			throw new RuntimeException("Not able to create position");
	}
	
	@GetMapping(path="/fetchpositions")
	public ResponseEntity<List<Position>> fetchPositions() {
		return ResponseEntity.ok(positionService.fetchPositions());
	}
}

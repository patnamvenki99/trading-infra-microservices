package com.traidnginfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traidnginfra.model.Position;
import com.traidnginfra.repository.PositionRepository;

@Service
public class PositionService {

	@Autowired
	private PositionRepository orderRepository;

	public Position createPosition(Position position) {
		return orderRepository.save(position);
	}

	public List<Position> fetchPositions() {
		return orderRepository.findAll();
	}
}

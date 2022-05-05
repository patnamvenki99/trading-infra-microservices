package com.traidnginfra.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.traidnginfra.model.Position;

@Component
public interface PositionRepository extends MongoRepository<Position, String> {

	public List<Position> findAll();
	
	public Position save(Position order);

}

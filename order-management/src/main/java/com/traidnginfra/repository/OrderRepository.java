package com.traidnginfra.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.traidnginfra.model.Order;

@Component
public interface OrderRepository extends MongoRepository<Order, String> {

	public List<Order> findByUserId(String userId);
	
	public List<Order> findAll();
	
	public Order save(Order order);

}

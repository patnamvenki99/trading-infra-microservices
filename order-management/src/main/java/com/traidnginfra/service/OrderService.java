package com.traidnginfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.traidnginfra.model.Order;
import com.traidnginfra.model.Position;
import com.traidnginfra.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	public Order placeOrder(Order order) {
		Position position= Position.builder()
							.price(order.getPrice())
							.quantity(order.getQuantity())
							.side(order.getSide())
							.symbol(order.getSymbol())
							.build();
		ResponseEntity<Position> positionResponse=restTemplate.postForEntity("http://localhost:9001/position/createposition", position, Position.class);

		Position p=positionResponse.getBody();
		return orderRepository.save(Order.builder()
											.positionId(p.getPositionId())
											.price(order.getPrice())
											.quantity(order.getQuantity())
											.side(order.getSide())
											.symbol(order.getSymbol())
											.build());
	}

	public List<Order> fetchOrders() {
		return orderRepository.findAll();
	}
}

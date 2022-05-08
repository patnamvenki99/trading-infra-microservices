package com.traidnginfra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.traidnginfra.model.Order;
import com.traidnginfra.model.Position;
import com.traidnginfra.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("microservices.position-manager.createposition.uri")
	private String createPositionURI;
	
	private Position createPosition(Position position) {
		ResponseEntity<Position> positionResponse= restTemplate.postForEntity(createPositionURI, position, Position.class);
		return positionResponse.getBody();
	}
	
	@CircuitBreaker(name = "createPosition", fallbackMethod = "placeOrderFallback")
	public Order placeOrder(Order order) {
		Position position= Position.builder()
							.price(order.getPrice())
							.quantity(order.getQuantity())
							.side(order.getSide())
							.symbol(order.getSymbol())
							.build();
		Position openPos= this.createPosition(position);
		return orderRepository.save(Order.builder()
								.positionId(openPos.getPositionId())
								.price(order.getPrice())
								.quantity(order.getQuantity())
								.side(order.getSide())
								.symbol(order.getSymbol())
								.status("Success")
								.build());
	}

	private Order placeOrderFallback(Order order, Throwable e) {
		return Order.builder().status("Failure").failureReason("Could not place the order, please check order details").build();
	}
	
	public List<Order> fetchOrders() {
		return orderRepository.findAll();
	}
}

package com.traidnginfra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traidnginfra.model.Order;
import com.traidnginfra.service.OrderService;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping(path="/placeorder")
	public ResponseEntity<Order> placeorder(@RequestBody Order order) {
		return ResponseEntity.ok(orderService.placeOrder(order));
	}
	
	@GetMapping(path="/fetchorders")
	public ResponseEntity<List<Order>> fetchOrders() {
		return ResponseEntity.ok(orderService.fetchOrders());
	}
}

package com.soft.stock.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soft.stock.entities.Order;

@Service
public interface OrderService {

	public Order createOrder(Order order);
	public Order getOrder(String orderNumber);
	public List<Order> getAllOrders();
	public Order updateOrder(Order order);
}

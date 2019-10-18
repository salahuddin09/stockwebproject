/**
 * 
 */
package com.soft.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soft.stock.common.EmailService;
import com.soft.stock.common.StockLogger;
import com.soft.stock.entities.Order;
import com.soft.stock.repositories.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	private static final StockLogger logger = StockLogger.getLogger(OrderServiceImpl.class);

	@Autowired
	EmailService emailService;
	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order createOrder(Order order) {
		// order.setOrderNumber(UUID.randomUUID().toString());
		order.setOrderNumber(String.valueOf(System.currentTimeMillis()));
		Order savedOrder = orderRepository.save(order);
		logger.info("New order created. Order Number : {}", savedOrder.getOrderNumber());
		return savedOrder;
	}

	@Override
	public Order getOrder(String orderNumber) {
		return orderRepository.findByOrderNumber(orderNumber);
	}

	@Override
	public List<Order> getAllOrders() {
		Sort sort = new Sort(Direction.DESC, "createdOn");
		return orderRepository.findAll(sort);
	}

	@Override
	public Order updateOrder(Order order) {
		Order o = getOrder(order.getOrderNumber());
		o.setStatus(order.getStatus());
		Order savedOrder = orderRepository.save(o);
		return savedOrder;
	}

}

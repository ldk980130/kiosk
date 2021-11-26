package com.postick.kiosk.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.postick.kiosk.domain.Order;
import com.postick.kiosk.domain.OrderItem;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderRepository {

	private final EntityManager em;

	@Transactional
	public Order save(List<OrderItem> orderItems) {
		Order order = Order.create(orderItems);
		em.persist(order);
		return order;
	}

	public Order findById(Long id) {
		return em.find(Order.class, id);
	}

	public List<Order> findAll() {
		return em.createQuery("select o from Order o", Order.class)
			.getResultList();
	}
}

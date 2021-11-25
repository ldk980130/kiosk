package com.postick.kiosk.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.postick.kiosk.domain.Order;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;

	public Long save(Order order) {
		em.persist(order);
		return order.getId();
	}

	public Order findById(Long id) {
		return em.find(Order.class, id);
	}

	public List<Order> findAll() {
		return em.createQuery("select o from Order o", Order.class)
			.getResultList();
	}
}

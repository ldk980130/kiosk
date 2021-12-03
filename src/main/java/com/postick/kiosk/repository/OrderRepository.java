package com.postick.kiosk.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.postick.kiosk.domain.Item;
import com.postick.kiosk.domain.Order;
import com.postick.kiosk.domain.OrderItem;
import com.postick.kiosk.web.dto.OrderItemDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderRepository {

	private final EntityManager em;
	private final ItemRepository itemRepository;

	@Transactional
	public Order save(List<OrderItemDto> dtos, boolean takeOut) {

		List<OrderItem> orderItems = new ArrayList<>();

		for (OrderItemDto dto : dtos) {
			Item item = itemRepository.findByName(dto.getItemName()).get();
			orderItems.add(dto.toEntity(item));
		}

		Order order = Order.create(orderItems, takeOut);
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

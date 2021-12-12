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

/**
 * 주문 테이블에 접근할 수 있는 Repository 클래스
 */

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em; // // DB에 접근할 수 있는 엔티티 매니저
	private final ItemRepository itemRepository; // 주문되는 상품을 찾아오기 위한 상품 테이블 접근 repository

	/**
	 * 주문을 저장하는 메소드
	 * 여러 개의 주문상품과 테이크아웃 여부를 받아 주문 테이블에 저장한다.
	 * @Transactional 애노테이션 : 이 메소드가 한 트랜잭션 안에서 수행되어야 함을 의미
	 */
	@Transactional
	public Order save(List<OrderItemDto> dtos, boolean takeOut) {
		List<OrderItem> orderItems = new ArrayList<>();

		dtos.stream().
			forEach(d -> addOrderItemToList(orderItems, d));

		Order order = Order.create(orderItems, takeOut);
		em.persist(order);

		return order;
	}

	private void addOrderItemToList(List<OrderItem> orderItems, OrderItemDto dto) {
		Item item = itemRepository.findByName(dto.getItemName()).get();
		orderItems.add(dto.toEntity(item));
	}

	/**
	 * 모든 주문을 조회하는 메소드
	 * JPQL을 사용해 모든 Order 객체를 가져올 수 있다.
	 */
	public List<Order> findAll() {
		return em.createQuery("select o from Order o order by o.id desc", Order.class)
			.getResultList();
	}

	/**
	 *
	 * @param start : 조회할 주문 테이블이 시작 번호
	 * @param end : 조회할 주문 테테이블의 끝 번호
	 * @return 정해진 개수만큼 주문을 리턴
	 */
	public List<Order> page(int start, int end) {
		return em.createQuery("select o from Order o order by o.id desc", Order.class)
			.setFirstResult(start)
			.setMaxResults(end)
			.getResultList();
	}
}

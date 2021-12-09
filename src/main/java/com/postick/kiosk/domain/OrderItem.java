package com.postick.kiosk.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.postick.kiosk.web.dto.OrderItemDto;

import lombok.Getter;

/**
 * 주문상품 테이블에 매핑되는 클래스
 */

@Entity
@Getter
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_item_id")
	private Long id; // 기본키

	@Column(nullable = false)
	private Integer orderPrice; // 주문 가격

	@Column(nullable = false)
	private Integer count; // 주문 수량

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item; // 주문하는 상품

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order; // 주문상품이 포함되는 주문

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "request_id")
	private Request request; // 주문상품의 요청사항

	/**
	 * 주문상품과 주문을 양방향 참조시키는 메소드
	 */
	public void setOrder(Order order) {
		this.order = order;
		order.getOrderItems().add(this);
	}

	/**
	 * 주문상품 생성 메소드
	 */
	public static OrderItem create(Item item, int count, Request request) {

		OrderItem orderItem = new OrderItem();
		orderItem.item = item;
		orderItem.orderPrice = item.getPrice() * count;
		orderItem.count = count;
		orderItem.request = request;

		return orderItem;
	}

	public OrderItemDto toOrderItemDto() {
		return OrderItemDto.builder()
			.itemName(this.item.getName())
			.count(this.count)
			.size(this.request.getSize().getDescription())
			.temperature(this.request.getTemperature().getDescription())
			.content(request.getContent())
			.build();
	}
}

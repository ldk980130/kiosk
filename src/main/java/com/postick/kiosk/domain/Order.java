package com.postick.kiosk.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.postick.kiosk.converter.BooleanToYNConverter;
import com.postick.kiosk.web.dto.OrderDto;

import lombok.Getter;

/**
 * 주문 테이블에 매핑되는 클래스
 */

@Entity
@Getter
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id; // 기본키

	@Column(nullable = false)
	private Integer totalPrice; // 총 주문 가격

	private String orderDate; // 주문 시간

	@Column(nullable = false)
	@Convert(converter = BooleanToYNConverter.class)
	private Boolean takeOut; // 테이크 아웃 여부

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>(); // 주문된 주문 상품들

	/**
	 * Order 클래스 생성 메소드
	 */
	public static Order create(List<OrderItem> orderItems, boolean takeOut) {

		Order order = new Order();
		order.totalPrice = 0;

		for (OrderItem orderItem : orderItems) {
			orderItem.setOrder(order);
			order.totalPrice += orderItem.getOrderPrice();
		}

		order.orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

		order.takeOut = takeOut;

		return order;
	}

	/**
	 * 웹 화면으로 클래스의 정보를 내보낼 때 필요 없는 메소드나 연관관계를 제외하고
	 * 필요한 정보만을 포함하기 위한 Dto 클래스로 변환하는 메소드
	 */
	public OrderDto toOrderDto() {
		return OrderDto.builder()
			.id(this.id)
			.totalPrice(this.totalPrice)
			.orderDate(this.orderDate)
			.takeOut(this.takeOut)
			.orderItems(this.orderItems)
			.build();
	}
}

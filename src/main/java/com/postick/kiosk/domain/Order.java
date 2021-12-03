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

import com.postick.kiosk.controller.BooleanToYNConverter;
import com.postick.kiosk.domain.dto.OrderDto;

import lombok.Getter;

@Entity
@Getter
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id;

	@Column(nullable = false)
	private Integer totalPrice;

	private String orderDate;

	@Column(nullable = false)
	@Convert(converter = BooleanToYNConverter.class)
	private Boolean takeOut;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

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

	public OrderDto toOrderDto() {
		return OrderDto.builder()
			.id(this.id)
			.totalPrice(this.totalPrice)
			.orderDate(this.orderDate)
			.takeOut(this.takeOut)
			.build();
	}
}

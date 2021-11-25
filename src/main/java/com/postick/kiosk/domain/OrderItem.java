package com.postick.kiosk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Entity
@Getter
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_item_id")
	private Long id;

	@Column(nullable = false)
	private Integer orderPrice;

	@Column(nullable = false)
	private Integer count;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	public void setOrder(Order order) {
		this.order = order;
		order.getOrderItems().add(this);
	}

	public static OrderItem create(Item item, int count) {

		OrderItem orderItem = new OrderItem();
		orderItem.item = item;
		orderItem.orderPrice = item.getPrice() * count;
		orderItem.count = count;

		return orderItem;
	}
}

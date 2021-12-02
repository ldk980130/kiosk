package com.postick.kiosk.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderForm {

	private Long id;
	private int totalPrice;
	private String orderDate;
	private boolean takeOut;

	@Builder
	public OrderForm(Long id, int totalPrice, String orderDate, boolean takeOut) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.takeOut = takeOut;
	}
}

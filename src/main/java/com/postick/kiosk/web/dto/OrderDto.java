package com.postick.kiosk.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 웹 화면으로 주문 정보를 보여주기 위한 Order 클래스의 Dto 클래스
 * Order 클래스의 toDto() 메소드로 생성된다.
 */

@Getter @Setter
public class OrderDto {

	private Long id;
	private int totalPrice;
	private String orderDate;
	private boolean takeOut;

	@Builder
	public OrderDto(Long id, int totalPrice, String orderDate, boolean takeOut) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.takeOut = takeOut;
	}
}

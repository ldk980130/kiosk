package com.postick.kiosk.web.dto;

import javax.validation.constraints.NotEmpty;

import com.postick.kiosk.domain.Item;
import com.postick.kiosk.domain.OrderItem;
import com.postick.kiosk.domain.Request;
import com.postick.kiosk.domain.option.Size;
import com.postick.kiosk.domain.option.Temperature;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 웹으로부터 주문상품 정보를 받아오는 OrderItem의 Dto 클래스.
 */

@Setter @Getter
public class OrderItemDto {

	@NotEmpty
	private String itemName; // 상품 이름
	@NotEmpty
	private Integer count; // 주문 수량
	private String size; // 사이즈
	private String temperature; // 온도
	private String content; // 구체적인 요청사항

	/**
	 * 생성자
	 */
	public OrderItemDto() {
	}

	@Builder
	public OrderItemDto(String itemName, Integer count, String size, String temperature, String content) {
		this.itemName = itemName;
		this.count = count;
		this.size = size;
		this.temperature = temperature;
		this.content = content;
	}

	/**
	 * Dto 클래스를 DB에 저장하기 위한 엔티티 클래스로 변환하는 메소드
	 */
	public OrderItem toEntity(Item item) {
		return OrderItem.create(item, count,
			Request.create(Size.get(size), Temperature.get(temperature), content));
	}

}

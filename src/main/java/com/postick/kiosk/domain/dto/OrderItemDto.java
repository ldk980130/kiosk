package com.postick.kiosk.domain.dto;

import javax.validation.constraints.NotEmpty;

import com.postick.kiosk.domain.Item;
import com.postick.kiosk.domain.OrderItem;
import com.postick.kiosk.domain.Request;
import com.postick.kiosk.domain.option.Size;
import com.postick.kiosk.domain.option.Temperature;

import lombok.Builder;
import lombok.Data;

@Data
public class OrderItemDto {

	@NotEmpty
	private String itemName;
	@NotEmpty
	private Integer count;
	private Size size;
	private Temperature temperature;
	private String content;

	@Builder
	public OrderItemDto(String itemName, Integer count, Size size, Temperature temperature, String content) {
		this.itemName = itemName;
		this.count = count;
		this.size = size;
		this.temperature = temperature;
		this.content = content;
	}

	public OrderItem toEntity(Item item) {
		return OrderItem.create(item, count,
			Request.create(size, temperature, content));
	}

}
